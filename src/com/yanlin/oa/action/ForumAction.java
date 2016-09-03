package com.yanlin.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.Topic;

/**
 * 参与板块操作的action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询板块列表
	 * @return
	 */
	public String list(){
		List<Forum> forumList = forumService.findAll();
		this.getValueStack().set("forumAll", forumList);
		return "list";
	}
	
	/**
	 * 查询某个板块的帖子列表
	 */
	public String forumShow(){
		//根据板块id查询板块，用于回显
		Forum forum = forumService.getById(model.getId());
		
		List<Topic> topicList = topicService.findTopicByForum(model);
		this.getValueStack().push(forum);
		this.getValueStack().set("topicList", topicList);
		return "forumShow";
	}
}
