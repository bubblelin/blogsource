package com.yanlin.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.yanlin.oa.domain.User;
import com.yanlin.oa.service.IBookService;
import com.yanlin.oa.service.IDepartmentService;
import com.yanlin.oa.service.IForumManageService;
import com.yanlin.oa.service.IForumService;
import com.yanlin.oa.service.IPrivilegeService;
import com.yanlin.oa.service.IReplyService;
import com.yanlin.oa.service.IRoleService;
import com.yanlin.oa.service.ITopicService;
import com.yanlin.oa.service.IUserService;

/**
 * ͨ通用的Action
 * @author bubblelin
 * @param <T>
 *
 */
@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	private static final long serialVersionUID = 1L;
	//获取模型驱动model
	protected BaseAction(){
		ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		Type[] types = genericSuperclass.getActualTypeArguments();
		Class<T> clazz = (Class<T>)types[0];
		try {
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected T model;
	@Override
	public T getModel() {
		return model;
	}
	
	/**
	 * 获取值栈
	 */
	protected ValueStack getValueStack(){
		return ActionContext.getContext().getValueStack();
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	protected User getLoginUser() {
		return (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}

	/**
	 * 获取当前用户的ip地址
	 * @return
	 */
	protected String getIpAddress() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}
	
	protected int currentPage = 1;
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@Resource
	protected IBookService bookService;
	@Resource
	protected IRoleService roleService;
	@Resource
	protected IDepartmentService departmentService;
	@Resource
	protected IUserService userService;
	@Resource
	protected IPrivilegeService privilegeService;
	@Resource
	protected IForumManageService forumManageService;
	@Resource
	protected IForumService forumService;
	@Resource
	protected ITopicService topicService;
	@Resource
	protected IReplyService replyService;
}
