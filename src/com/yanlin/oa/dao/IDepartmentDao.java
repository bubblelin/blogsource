package com.yanlin.oa.dao;

import java.util.List;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.Department;

public interface IDepartmentDao extends IBaseDao<Department>{

	List<Department> findTopList();

	List<Department> findChidList(Long parentId);

}
