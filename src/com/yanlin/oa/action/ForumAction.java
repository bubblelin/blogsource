package com.yanlin.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.utils.HQLHelper;

/**
 * 参与板块操作的action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{

	private static final long serialVersionUID = 1L;
	//条件查询的属性驱动
	private int viewType;
	private int orderBy;
	private boolean asc = true;
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	/**
	 * 查询板块列表
	 * @return
	 */
	public String list(){
		//List<Forum> forumList = forumService.findAll();
		
		HQLHelper hql = new HQLHelper(Forum.class);
		hql.addOrderBy("o.position", asc);
		PageBean pb = forumService.getPageBean(hql,currentPage);

		this.getValueStack().push(pb);
		return "list";
	}
	
	/**
	 * 查询某个板块的帖子列表
	 */
	public String forumShow(){
		//根据板块id查询板块，用于回显
		Forum forum = forumService.getById(model.getId());
		this.getValueStack().push(forum);

		//List<Topic> topicList = topicService.findTopicByForum(model);
		//this.getValueStack().set("topicList", topicList);
		
		
		//viewType="0"默认全部主题 
		//viewType="1"全部精华贴
		
		//orderBy="0"默认排序（按最后更新时间排序，但所有置顶帖都在前面）        
		//orderBy="1"按最后更新时间排序                                  
		//orderBy="2"按主题发表时间排序                                  
		//orderBy="3"按回复数量排序
		
		// asc="false">默认升序</option>
		// asc="true">降序</option> 
		HQLHelper hql = new HQLHelper(Topic.class);
		hql.addWhere("o.forum=?", model);
		if(viewType == 1){
			hql.addWhere("o.type=?", 1);
		}
		if(orderBy == 0){
			hql.addOrderBy("(case o.type when 2 then 2 else 1 end)", false);
			hql.addOrderBy("o.postTime", asc);
		}else if(orderBy == 1){
			hql.addOrderBy("o.lastUpdateTime", asc);
		}else if(orderBy == 2){
			hql.addOrderBy("o.postTime", asc);
		}else if(orderBy == 3){
			hql.addOrderBy("o.replyCount", asc);
		}
		PageBean pb = topicService.getPageBean(hql,currentPage);
		this.getValueStack().push(pb);
		
		return "forumShow";
	}
}
