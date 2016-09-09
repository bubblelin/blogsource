package com.yanlin.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IApproveInfoDao;
import com.yanlin.oa.domain.ApproveInfo;

/**
 * 审批的dao实现
 * @author bubblelin
 *
 */
@Repository
public class ApproveInfoDaoImpl extends BaseDaoImpl<ApproveInfo> implements IApproveInfoDao{
	/**
	 * 查询审批记录
	 */
	@SuppressWarnings("unchecked")
	public List<ApproveInfo> findApproveInfoByApplication(Long applicationId) {
		String hql = "from ApproveInfo ai where ai.application.id=? order by ai.approveTime desc";
		return this.getSession().createQuery(hql).setParameter(0, applicationId).list();
	}

}
