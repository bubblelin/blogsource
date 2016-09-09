package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Application;
import com.yanlin.oa.domain.ApproveInfo;
import com.yanlin.oa.domain.TaskView;
import com.yanlin.oa.domain.User;

public interface IFlowService {

	void submit(Application appl);

	List<TaskView> findTaskList(User loginUser);

	void approve(ApproveInfo appr, String taskId);

}
