package com.yanlin.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.ITopicDao;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.Topic;

/**
 * 帖子的dao实现
 * @author bubblelin
 *
 */
@Repository
public class TopicdDaoImpl extends BaseDaoImpl<Topic> implements ITopicDao{

	/**
	 * 根据所属板块查询帖子,置顶帖最上，其他按lastUpdateTime排序
	 */
	@SuppressWarnings("unchecked")
	public List<Topic> findTopicByForum(Forum model) {
		//select * from oa_topic order by (case type when 2 then 2 else 1 end) desc,lastUpdateTime desc;
		String hql = "from Topic t where t.forum=? order by (case t.type when 2 then 2 else 1 end) desc,t.lastUpdateTime desc";
		return this.getSession().createQuery(hql).setParameter(0, model).list();
	}

}
