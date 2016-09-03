package com.yanlin.oa.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Privilege;
import com.yanlin.oa.domain.Role;

/**
 * 岗位的Acion
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	private static final long serialVersionUID = 1L;
	private Long[] privilegeIds;
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/**
	 * 查询岗位列表
	 */
	public String list(){
		List<Role> roles = roleService.findAll();
		this.getValueStack().set("roles", roles);
		return "list";
	}
	
	/**
	 * 删除岗位
	 */
	public String delete(){
		roleService.delete(model);
		return "toList";
	}
	
	/**
	 * 跳转到修改页面
	 */
	public String editUI(){
		//���id��ѯ����λ��Ϣ�����ڻ���
		Role role = roleService.getById(model.getId());
		this.getValueStack().push(role);
		return "editUI";
	}
	
	/**
	 * 修改岗位
	 */
	public String edit(){
		Role role = roleService.getById(model.getId());
		
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role );
		
		return "toList";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String addUI(){
		return "addUI";
	}
	
	/**
	 * 添加岗位
	 */
	public String add(){
		roleService.save(model);
		return "toList";
	}
	
	/**
	 * 跳转到权限修改页面
	 */
	public String setPrivilegeUI(){
		//1.获取需要修改权限的的岗位
		Role role = roleService.getById(model.getId());
		this.getValueStack().push(role);
		
		//2.获取所有的顶级权限
		List<Privilege> topList = privilegeService.findTopList();
		this.getValueStack().set("privilegeList", topList);
		
		//3.获取该岗位的权限
		Set<Privilege> privilegeSet = role.getPrivileges();
		int size = privilegeSet.size();
		if(privilegeSet != null && size>0){
			privilegeIds = new Long[size];
			int i = 0;
			for(Privilege p : privilegeSet){
					privilegeIds[i++] = p.getId();
			}
		}
		
		return "setPrivilegeUI";
	}
	
	/**
	 * 修改权限
	 */
	public String setPrivilege(){
		//1.获取需要修改权限的岗位
		Role role = roleService.getById(model.getId());
		//2.修改权限
		if(privilegeIds != null && privilegeIds.length > 0){
			List<Privilege> privilegeList = privilegeService.findByIds(privilegeIds);
			role.setPrivileges(new HashSet<Privilege>(privilegeList));
		}else{
			role.setPrivileges(null);
		}
		//3.修改岗位的信息（权限）
		roleService.update(role);
		
		return "toList";
	}
}
