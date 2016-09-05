package com.yanlin.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.utils.HQLHelper;

/**
 * 板块管理的Action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum>{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询板块列表
	 */
	public String list(){
		//List<Forum> forumList = forumManageService.findAll();
		//this.getValueStack().set("forumList", forumList);
		
		//带分页查询
		HQLHelper hql = new HQLHelper(Forum.class);
		hql.addOrderBy("o.position", true);
		PageBean pb = forumManageService.getPageBean(hql,currentPage);
		this.getValueStack().push(pb);
		
		return "list";
	}
	
	/**
	 * 板块删除
	 */
	public String delete(){
		forumManageService.delete(model.getId());
		return "delete";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String addUI(){
		
		return "addUI";
	}
	/**
	 * 板块添加
	 */
	public String add(){
		forumManageService.save(model);
		return "toList";
	}
	/**
	 * 板块修改页面
	 */
	public String editUI(){
		Forum forum = forumManageService.getById(model.getId());
		this.getValueStack().push(forum);
		return "editUI";
	}
	/**
	 * 修改板块
	 */
	public String edit(){
		Forum forum = forumManageService.getById(model.getId());
		this.getValueStack().push(forum);
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumManageService.update(forum);
		return "toList";
	}
	
	/**
	 * 上移操作
	 */
	public String moveUp(){
		forumManageService.moveUp(model.getId());
		return "toList";
	}
	
	/**
	 * 下移
	 */
	public String moveDown(){
		forumManageService.moveDown(model.getId());;
		return "toList";
	}
	
	
	
}
