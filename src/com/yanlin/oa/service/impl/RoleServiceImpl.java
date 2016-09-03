package com.yanlin.oa.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IRoleDao;
import com.yanlin.oa.domain.Role;
import com.yanlin.oa.service.IRoleService;

/**
 * 岗位的service实现类
 * @author bubblelin
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

	@Resource
	private IRoleDao roleDao;

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public void delete(Role model) {
		roleDao.delete(model.getId());
	}

	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public void save(Role model) {
		roleDao.save(model);
	}

	public List<Role> findByIds(Long[] roleIds) {
		return roleDao.getByIds(roleIds);
	}

	public List<Role> getByIds(Long[] roleIds) {
		return roleDao.getByIds(roleIds);
	}

}
