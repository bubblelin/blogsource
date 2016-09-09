package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.ApproveInfo;

public interface IApproveInfoService {

	List<ApproveInfo> findApproveInfoListByApplicationId(Long applicationId);

}
