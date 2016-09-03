package com.yanlin.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IUserDao;
import com.yanlin.oa.domain.User;
import com.yanlin.oa.utils.MD5Utils;
/**
 * 用户dao的实现类
 * @author bubblelin
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	/**
	 * 根据登陆的用户登陆名查询数据库是否存在，若存在则返回有几个
	 */
	public int findByLoginName(String loginName) {
		String hql = "SELECT COUNT(id) FROM User u WHERE u.loginName=?";
		List<Long> list = this.getSession().createQuery(hql).setParameter(0, loginName).list();
		return list != null && list.size() > 0 ? list.get(0).intValue() : 0;
	}

	public User login(User model) {
		String hql = "FROM User u WHERE u.loginName=? AND u.password=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, model.getLoginName());
		if(model.getPassword() == null){
			query.setParameter(1, null);
		}else{
			query.setParameter(1, MD5Utils.md5(model.getPassword()));
		}
		List<User> list = query.list();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	

}
