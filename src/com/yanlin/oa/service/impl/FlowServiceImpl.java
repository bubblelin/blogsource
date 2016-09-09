package com.yanlin.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IApplicationDao;
import com.yanlin.oa.dao.IApproveInfoDao;
import com.yanlin.oa.domain.Application;
import com.yanlin.oa.domain.ApproveInfo;
import com.yanlin.oa.domain.TaskView;
import com.yanlin.oa.domain.User;
import com.yanlin.oa.service.IFlowService;

/**
 * 审批流转的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class FlowServiceImpl implements IFlowService{
	@Resource
	private IApplicationDao applicationDao;
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private IApproveInfoDao approveInfoDao;
	
	public void submit(Application appl) {
		//保存申请信息
		applicationDao.save(appl);
		//启动流程，并给其设置变量，将申请实体封装成变量
		String processDefinitionKey = appl.getTemplate().getProcessDefinitionKey();
		Map<String, Application> variables = new HashMap<String, Application>();
		variables.put("application", appl);
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(processDefinitionKey, variables);
		//根据任务id办理流程
		TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
		taskQuery.processInstanceId(pi.getId());//获取当前流程的实例的唯一任务
		Task task = taskQuery.uniqueResult();
		String taskId = task.getId();
		processEngine.getTaskService().completeTask(taskId);
		
		
	}
	/**
	 * 查询我的任务列表
	 */
	public List<TaskView> findTaskList(User loginUser) {
		List<TaskView> taskView = new ArrayList<TaskView>();
		//根据登陆用户名查询相应的任务列表
		List<Task> taskList = processEngine.getTaskService().findPersonalTasks(loginUser.getLoginName());
		
		//从流程变量中获取对应的申请实体
		for(Task t : taskList){
			Application application = (Application)processEngine.getTaskService().getVariable(t.getId(), "application");
			TaskView tv = new TaskView(t, application);
			taskView.add(tv);
		}
		return taskView;
	}

	/**
	 * 审批处理
	 */
	public void approve(ApproveInfo appr, String taskId) {
		//保存一个新的审批信息
		approveInfoDao.save(appr);
		//先获取当前任务的执行id，再办理任务
		Task task = processEngine.getTaskService().getTask(taskId);
		String executionId = task.getExecutionId();
		processEngine.getTaskService().completeTask(taskId);
		
		//根据执行id，查询任务的流程是否存在
		ProcessInstanceQuery piQuery = processEngine.getExecutionService().createProcessInstanceQuery();
		piQuery.processInstanceId(executionId);
		ProcessInstance pi = piQuery.uniqueResult();
		
		//审批是否通过,修改申请状态
		if(appr.getApproval()){
			//1.根据执行id，查询任务的流程是否存在,若不存在，可知当前流程是最后一个节点
			if(pi == null){
				//设置申请状态为通过
				appr.getApplication().setStatus(Application.STATUS_APPROVED);
			}
		}else{
			appr.getApplication().setStatus(Application.STATUS_UNAPPROVED);
			//2.根据执行id，查询任务的流程是否存在,若存在 需要结束
			if(pi != null){
				processEngine.getExecutionService().endProcessInstance(executionId, ProcessInstance.STATE_ENDED);
			}
		}
	}

}
