package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IPrivilegeDao;
import com.yanlin.oa.domain.Privilege;
import com.yanlin.oa.service.IPrivilegeService;
/**
 * 权限的service显示类
 * @author bubblelin
 *
 */
@Service
@Transactional
public class PrivilegeServiceImpl implements IPrivilegeService{

	@Resource
	private IPrivilegeDao privilegeDao;

	public List<Privilege> findAll() {
		return privilegeDao.findAll();
	}

	public List<Privilege> findByIds(Long[] privilegeIds) {
		return privilegeDao.getByIds(privilegeIds);
	}

	public List<Privilege> findTopList() {
		return privilegeDao.findTopList();
	}

	public List<String> findAllUrl() {
		return privilegeDao.findAllUrl();
	}
}
