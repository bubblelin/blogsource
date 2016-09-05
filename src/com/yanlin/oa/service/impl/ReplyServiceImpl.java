package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IReplyDao;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.service.IReplyService;
import com.yanlin.oa.utils.HQLHelper;

/**
 * 回帖的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class ReplyServiceImpl implements IReplyService{

	@Resource
	private IReplyDao replyDao;

	public void save(Reply model) {
		
		replyDao.save(model);
		
		Topic topic = model.getTopic();
		Forum forum = topic.getForum();
		//设置主题帖子所在的板块的帖子数量
		forum.setArticleCount(forum.getArticleCount()+1);
		//设置主题帖子的最后回复，最后回复时间，回复数量
		topic.setLastReply(model);
		topic.setLastUpdateTime(model.getPostTime());
		topic.setReplyCount(topic.getReplyCount()+1);
	}

	public List<Reply> getReplyByTopic(Topic model) {
		return replyDao.getReplyByTopic(model);
	}

	public PageBean getPageBean(HQLHelper hql, int currentPage) {
		return replyDao.getPageBean(hql, currentPage);
	}

}
