package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IApproveInfoDao;
import com.yanlin.oa.domain.ApproveInfo;
import com.yanlin.oa.service.IApproveInfoService;

/**
 * 审批的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class ApproveInfoServiceImpl implements IApproveInfoService{

	@Resource
	private IApproveInfoDao approveInfoDao;

	/**
	 * 根据申请的id查询审批
	 */
	public List<ApproveInfo> findApproveInfoListByApplicationId(Long applicationId) {
		return approveInfoDao.findApproveInfoByApplication(applicationId);
	}
}
