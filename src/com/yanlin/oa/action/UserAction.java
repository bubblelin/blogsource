package com.yanlin.oa.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Department;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Role;
import com.yanlin.oa.domain.User;
import com.yanlin.oa.utils.DepartmentUtil;
import com.yanlin.oa.utils.HQLHelper;
import com.yanlin.oa.utils.MD5Utils;

/**
 * 用户的action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

	private static final long serialVersionUID = 1L;
	//获取部门的id
	private Long departmentId;
	private Long[] roleIds;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 查询用户列表
	 */
	public String list(){
		//List<User> users = userService.findAll();
		//this.getValueStack().set("users", users);
		
		HQLHelper hql = new HQLHelper(User.class);
		hql.addOrderBy("o.id", true);
		PageBean pb = userService.getPageBean(hql,currentPage);
		this.getValueStack().push(pb);
		return "list";
	}
	
	/**
	 * 删除用户
	 */
	public String delete(){
		userService.delete(model);
		return "toList";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String addUI(){
		//获取顶级的部门，用于回显
		List<Department> topList = departmentService.findTopList();
		List<Department> treeList = DepartmentUtil.getTreeList(topList, null);
		
		List<Role> roleList = roleService.findAll();
		
		this.getValueStack().set("departments", treeList);
		this.getValueStack().set("roleList", roleList);
		return "addUI";
	}
	
	/**
	 * 添加用户
	 */
	public String add(){
		if(departmentId != null){
			Department department = departmentService.getById(departmentId);
			model.setDepartment(department);
		}
		if(roleIds != null && roleIds.length > 0){
			List<Role> roles = roleService.findByIds(roleIds);
			model.setRoles(new HashSet<Role>(roles));
		}
		userService.save(model);
		return "toList";
	}
	
	/**
	 * 跳转到修改页面
	 */
	public String editUI(){
		//获取顶级部门，用于回显
		List<Department> topList = departmentService.findTopList();
		List<Department> treeList = DepartmentUtil.getTreeList(topList, null);
		List<Role> roleList = roleService.findAll();
		//获取需要修改的用户
		User user = userService.getById(model.getId());
		this.getValueStack().set("departments", treeList);
		this.getValueStack().set("roleList", roleList);
		this.getValueStack().push(user);
		
		//如果用户原有部门，则通过设置部门id来回显
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();
		}
		//弱国用户原有岗位，则通过设置岗位id来回显
		Set<Role> roles = user.getRoles();
		if(roles != null && roles.size() > 0){
			int size = roles.size();
			roleIds = new Long[size];
			int c = 0;
			for (Role role : roles) {
				roleIds[c++] = role.getId();
			}
		}
		
		return "editUI";
	}
	
	/**
	 * 修改用户
	 */
	public String edit(){
		User user = userService.getById(model.getId());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhone(model.getPhone());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		
		//是否设置部门
		if(departmentId != null){
			Department department = departmentService.getById(departmentId);
			user.setDepartment(department);
		}else{
			user.setDepartment(null);
		}
		
		//是否设置岗位
		if(roleIds != null && roleIds.length > 0){
			List<Role> roles = roleService.getByIds(roleIds);
			user.setRoles(new HashSet<Role>(roles));
		}else{
			user.setRoles(null);
		}
		userService.update(user);
		return "toList";
	}
	
	/**
	 * 初始化用户密码
	 */
	public String initPassword(){
		User user = userService.getById(model.getId());
		user.setPassword(MD5Utils.md5("1234"));
		userService.update(user);
		return "toList";
	}
	
	/**
	 * 获取用户的登录名
	 */			
	public String findByLoginName(){
		String loginName = model.getLoginName();
		int count = userService.findByLoginName(loginName);
		
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		String flag = "1";
		if(count > 0){
			//该用户名已经存在
			flag = "0";
		}
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 用户登陆
	 */
	public String login(){
		User loginUser = userService.login(model);
		if(loginUser != null){
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
			return "home";
		}else{
			this.addActionError("用户名或密码不对，请重试！");
			return "loginUI";
		}
	}
	
	/**
	 * 用户退出系统
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
		return "loginUI";
	}
}

