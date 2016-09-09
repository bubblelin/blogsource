package com.yanlin.oa.dao;

import java.util.List;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.ApproveInfo;

public interface IApproveInfoDao extends IBaseDao<ApproveInfo>{

	List<ApproveInfo> findApproveInfoByApplication(Long applicationId);

}
