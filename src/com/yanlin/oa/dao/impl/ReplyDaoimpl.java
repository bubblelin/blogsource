package com.yanlin.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IReplyDao;
import com.yanlin.oa.domain.PageBean;
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
		String hql = "from Reply r where r.topic=? order by r.postTime asc";
		return this.getSession().createQuery(hql).setParameter(0, model).list();
	}

	/**
	 * 根据主题查询带分页的回复内容的信息
	 */
	/*public PageBean getPageBean(int currentPage, Topic model) {
		int pageSize = 10;
		int firstResult = (currentPage - 1) * pageSize; 
		String hql = "from Reply r where r.topic=? order by r.postTime asc";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, model);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List listData = query.list();
		
		hql = "select count(id) from Reply r where r.topic=?";
		query = this.getSession().createQuery(hql);
		query.setParameter(0, model);
		Long totalSize = (Long)query.uniqueResult();
		
		PageBean pageBean = new PageBean(currentPage, pageSize, listData, totalSize.intValue());
		System.out.println("开始："+pageBean.getBeginPageIndex());
		System.out.println("结束"+pageBean.getEndPageIndex());
		return pageBean;
	}*/
	
	

}
