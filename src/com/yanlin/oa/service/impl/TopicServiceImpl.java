package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.ITopicDao;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.service.ITopicService;
import com.yanlin.oa.utils.HQLHelper;
/**
 * 帖子的service实现
 * @author bubblelin
 *
 */
@Service
@Transactional
public class TopicServiceImpl implements ITopicService{

	@Resource
	protected ITopicDao topicDao;

	public List<Topic> findTopicByForum(Forum model) {
		return topicDao.findTopicByForum(model);
	}

	public void save(Topic model) {
		topicDao.save(model);			//当前方法下的model为持久对象
		Forum forum = model.getForum();	//forum也为持久对象

		//当前主题所在的板块的主题数量+1,文章数量+1，最后发表的帖子更新为model
		forum.setTopicCount(forum.getTopicCount()+1);
		forum.setArticleCount(forum.getArticleCount()+1);
		forum.setLastTopic(model);
	}

	public Topic getById(Long id) {
		return topicDao.getById(id);
	}

	public PageBean getPageBean(HQLHelper hql, int currentPage) {
		return topicDao.getPageBean(hql, currentPage);
	}

}
