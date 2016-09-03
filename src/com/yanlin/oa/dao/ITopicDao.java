package com.yanlin.oa.dao;

import java.util.List;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.Topic;

public interface ITopicDao extends IBaseDao<Topic>{

	List<Topic> findTopicByForum(Forum model);

}
