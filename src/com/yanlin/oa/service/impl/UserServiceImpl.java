package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IUserDao;
import com.yanlin.oa.domain.User;
import com.yanlin.oa.service.IUserService;
import com.yanlin.oa.utils.MD5Utils;
/**
 * 用户的service实现类
 * @author bubblelin
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void delete(User model) {
		userDao.delete(model.getId());
	}

	public void save(User model) {
		//�½��û�����Ĭ������
		model.setPassword(MD5Utils.md5("1234"));
		userDao.save(model);
	}

	public User getById(Long id) {
		return userDao.getById(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public int findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public User login(User model) {
		return userDao.login(model);
	}
}
