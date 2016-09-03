package com.yanlin.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Department;
import com.yanlin.oa.utils.DepartmentUtil;

/**
 * 部门的Action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{

	private static final long serialVersionUID = 1L;
	//获取部门的parentId
	private Long parentId;
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 查询部门列表
	 * @return
	 */
	public String list(){
		//List<Department> departments = departmentService.findAll();
		List<Department> departments = null;
		if(parentId == null){
			departments = departmentService.findTopList();
		}else{
			departments = departmentService.findChidList(parentId);

			Department department = departmentService.getById(parentId);
			this.getValueStack().set("department", department);
		}
		this.getValueStack().set("departments", departments);
		return "list";
	}
	
	/**
	 * 删除部门
	 */
	public String delete(){
		departmentService.delete(model);
		return "toList";
	}
	
	/**
	 * 添加部门
	 */
	public String addUI(){
		//获取所有的顶级部门
		List<Department> topList = departmentService.findTopList();
		//获取部门的树状结构
		List<Department> treeList = DepartmentUtil.getTreeList(topList,null);
		
		this.getValueStack().set("departments", treeList);
		return "addUI";
	}
	
	/**
	 * 添加部门
	 */
	public String add(){
		//��ѯ�ϼ������Ƿ����
		if(parentId != null){
			Department department = departmentService.getById(parentId);
			model.setParent(department);
		}else{
			model.setParent(null);
		}
		departmentService.save(model);
		return "toList";
	}
	
	/**
	 * 修改部门
	 */
	public String editUI(){
		//2.获取需要修改的部门
		Department department = departmentService.getById(model.getId());
		
		//1.获取所有的顶级部门
		List<Department> topList = departmentService.findTopList();
		List<Department> treeList = DepartmentUtil.getTreeList(topList,department.getId());
		
		this.getValueStack().set("departments", treeList);
		this.getValueStack().push(department);
		
		//3.获取parentId，用于回显
		if(department.getParent() != null){
			parentId = department.getParent().getId();
		}
		return "editUI";
	}
	/**
	 * 修改部门
	 */
	public String edit(){
		//获取需要修改的部门
		Department department = departmentService.getById(model.getId());
		//设置顶级部门
		if(parentId != null){
			Department parent = departmentService.getById(parentId);
			department.setParent(parent);
		}else{
			department.setParent(null);
		}
		//设置部门的信息
		department.setName(model.getName());
		department.setDescription(model.getDescription());;
		
		departmentService.update(department);
		return "toList";
	}
	
}
