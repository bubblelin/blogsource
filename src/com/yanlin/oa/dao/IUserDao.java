package com.yanlin.oa.dao;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.User;

public interface IUserDao extends IBaseDao<User>{

	int findByLoginName(String loginName);

	User login(User model);

}
