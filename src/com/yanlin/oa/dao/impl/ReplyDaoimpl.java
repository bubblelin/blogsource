package com.yanlin.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IReplyDao;
import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;

/**
 * 回帖的dao实现
 * @author bubblelin
 *
 */
@Repository
public class ReplyDaoimpl extends BaseDaoImpl<Reply> implements IReplyDao{

	/**
	 * 根据topic查询其回复的内容
	 */
	@SuppressWarnings("unchecked")
	public List<Reply> getReplyByTopic(Topic model) {
		String hql = "from Reply r where r.topic=? order by postTime asc";
		return this.getSession().createQuery(hql).setParameter(0, model).list();
	}

}
