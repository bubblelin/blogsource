package com.yanlin.oa.dao;

import java.util.List;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.Privilege;

public interface IPrivilegeDao extends IBaseDao<Privilege>{

	List<Privilege> findTopList();

	List<String> findAllUrl();

}
