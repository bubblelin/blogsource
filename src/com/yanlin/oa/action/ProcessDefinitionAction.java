package com.yanlin.oa.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yanlin.oa.service.IProcessDefinitionService;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource
	protected IProcessDefinitionService processDefinitionService;
	
	//上传文件
	private File resource;
	public void setResource(File resource) {
		this.resource = resource;
	}

	//属性驱动，用于删除流程的key
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	//用于文件下载
	private InputStream inputStreamName;
	public InputStream getInputStreamName() {
		return inputStreamName;
	}
	public void setInputStreamName(InputStream inputStreamName) {
		this.inputStreamName = inputStreamName;
	}
	
	//属性驱动，用于查找流程定义
	private String processDefinitionId;
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	/**
	 * 查询流程定义列表
	 */
	public String list(){
		List<ProcessDefinition> processDefinitions = processDefinitionService.findLastList();
		ActionContext.getContext().getValueStack().set("processDefinitions", processDefinitions);
		return "list";
	}
	
	/**
	 * 删除流程定义
	 */
	public String delete(){
		try {
			key = new String(key.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		processDefinitionService.delete(key);
		return "toList";
	}
	
	/**
	 * 跳转到流程定义添加页面
	 */
	public String addUI(){
		return "addUI";
	}
	
	/**
	 * 添加流程定义
	 */
	public String add(){
		processDefinitionService.deploy(resource);
		return "toList";
	}
	
	/**
	 * 查看流程图
	 */
	public String showImage(){
		try {
			processDefinitionId = new String(processDefinitionId.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		inputStreamName = processDefinitionService.getImageInputStream(processDefinitionId);
		return "showImage";
	}
}
