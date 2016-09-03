package com.yanlin.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 帖子的实体
 * @author bubblelin
 *
 */

public class Topic extends Article {
	private String title;
	private Date lastUpdateTime;
	private int type;		//主题类型：0表示普通贴，1表示精华帖，2表示置顶帖
	private Forum forum;
	private Set<Reply> replies = new HashSet<Reply>();
	private int replyCount;
	private Reply lastReply;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
}
