package com.yanlin.oa.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IApplicationDao;
import com.yanlin.oa.domain.Application;
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
	
	public void submit(Application appl) {
		//保存申请信息
		applicationDao.save(appl);
		//启动流程，并给其设置变量
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

}
