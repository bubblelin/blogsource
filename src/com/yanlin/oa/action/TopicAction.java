package com.yanlin.oa.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.domain.User;

/**
 * 帖子的Action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{

	private static final long serialVersionUID = 1L;
	//属性驱动，获取板块的id
	private Long forumId;
	/**
	 * 跳转到发布新贴的页面
	 */
	public String addUI(){
		Forum forum = forumService.getById(forumId);
		this.getValueStack().push(forum);
		return "addUI";
	}
	
	/**
	 * 发布新帖
	 */
	public String add(){
		Forum forum = forumService.getById(forumId);
		model.setForum(forum);
		
		model.setIpAddress(this.getIpAddress());
		model.setPostTime(new Date());
		model.setType(0);
		model.setAuthor(this.getLoginUser());
		model.setReplyCount(0);
		model.setLastReply(null);
		model.setLastUpdateTime(model.getPostTime());
		
		topicService.save(model);
		return "toTopicList";
	}

	/**
	 * 查看帖子和回复
	 */
	public String topicShow(){
		//根据id查询帖子
		Topic topic = topicService.getById(model.getId());
		this.getValueStack().push(topic);
		
		//根据帖子查询其回复内容
		List<Reply> replyList = replyService.getReplyByTopic(model);
		this.getValueStack().set("replyList", replyList);
		return "topicShow";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
