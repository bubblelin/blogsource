package com.yanlin.oa.domain;

import org.jbpm.api.task.Task;

/**
 * 封装申请和任务
 * @author bubblelin
 *
 */
public class TaskView {

	private Task task;
	private Application application;
	
	
	public TaskView(Task task, Application application) {
		this.task = task;
		this.application = application;
	}
	public TaskView() {
		super();
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
}
