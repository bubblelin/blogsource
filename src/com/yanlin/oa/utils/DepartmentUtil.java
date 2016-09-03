package com.yanlin.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.yanlin.oa.domain.Department;

public class DepartmentUtil {
	/**
	 * 将部门列表设置成树状的工具
	 * @param topList	所有的顶级部门
	 * @param removeId	需要删除的部门的id
	 * @return
	 */
	public static List<Department> getTreeList(List<Department> topList, Long removeId) {

		List<Department> treeList = new ArrayList<Department>();
		
		showTree(topList,treeList,"┣", removeId);
		
		return treeList;
	}

	private static void showTree(Collection<Department> topList,
			List<Department> treeList, String prefix, Long removeId) {
		for(Department dept : topList){
			if(removeId != null && dept.getId().equals(removeId)){
				//跳过不需要的部门
				continue;
			}
			
			//拷贝一份部门对象
			Department copy = new Department();
			copy.setId(dept.getId());
			copy.setName(prefix+dept.getName());
			treeList.add(copy);
			//子树：下级部门
			Set<Department> children = dept.getChildren();
			
			showTree(children, treeList, "　┣", removeId);
		}
	}

	
}
