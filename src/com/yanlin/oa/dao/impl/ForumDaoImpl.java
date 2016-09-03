package com.yanlin.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IForumDao;
import com.yanlin.oa.domain.Forum;

/**
 * 参与板块操作的Dao实现
 * @author bubblelin
 *
 */
@Repository
public class ForumDaoImpl extends BaseDaoImpl<Forum> implements IForumDao{

	/**
	 * 重写findAll()方法，按position属性排序
	 */
	@SuppressWarnings("unchecked")
	public List<Forum> findAll() {
		String hql = "from Forum f order by f.position";
		return this.getSession().createQuery(hql).list();
	}

	
}
