package com.yanlin.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface IProcessDefinitionService {

	List<ProcessDefinition> findLastList();

	void deploy(File resource);

	void delete(String key);

	InputStream getImageInputStream(String processDefinitionId);

}
