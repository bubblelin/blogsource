package com.yanlin.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.service.IProcessDefinitionService;

/**
 * 流程定义的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService{

	@Resource
	private ProcessEngine processEngine;

	@Override
	public List<ProcessDefinition> findLastList() {
		//获取流程定义的查询对象
		ProcessDefinitionQuery pdQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
		pdQuery.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);
		List<ProcessDefinition> list = pdQuery.list();
		
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd : list){
			map.put(pd.getKey(), pd);
		}
		
		return new ArrayList<ProcessDefinition>(map.values());
	}

	public void deploy(File resource) {
		//获取流程定义的部署对象
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		ZipInputStream zIn = null;
		try {
			zIn = new ZipInputStream(new FileInputStream(resource));
			deployment.addResourcesFromZipInputStream(zIn);
			deployment.deploy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(zIn != null){
					zIn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void delete(String key) {
		//根据key获取流程定义
		ProcessDefinitionQuery pdQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
		pdQuery.processDefinitionKey(key);
		List<ProcessDefinition> list = pdQuery.list();
		
		for(ProcessDefinition pd : list){
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());;
		}
	}

	//根据流程定义的id和文件资源名获取输入流，读取文件
	public InputStream getImageInputStream(String processDefinitionId) {
		ProcessDefinitionQuery pdQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
		pdQuery.processDefinitionId(processDefinitionId);
		ProcessDefinition pd = pdQuery.uniqueResult();

		String deploymentId = pd.getDeploymentId();
		String imageResourceName = pd.getImageResourceName();
		InputStream inputstream = processEngine.getRepositoryService().getResourceAsStream(deploymentId, imageResourceName);
		return inputstream;
	}
	
	
}
