package com.yanlin.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.yanlin.oa.domain.Department;


public class TreeViewPractice{
	/**
	 * 结构如下：
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 * @return 所有最顶层的部门列表
	 */
	public static List<Department> findTopListDepartmentList(){
		
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Long(11));
		dept_1_1.setName("宣传部");
		
		Department dept_1_2 = new Department();
		dept_1_2.setId(new Long(12));
		dept_1_2.setName("业务部");
		
		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Long(121));
		dept_1_2_1.setName("业务一部");
		
		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Long(122));
		dept_1_2_2.setName("业务二部");
		
		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> child_0 = new LinkedHashSet<Department>(); 
		child_0.add(dept_1_2_1);
		child_0.add(dept_1_2_2);
		dept_1_2.setChildren(child_0);
		
		//=========================================
		
		Department dept_1 = new Department();
		dept_1.setId(new Long(1));
		dept_1.setName("市场部");
		
		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> child_1 = new LinkedHashSet<Department>();
		child_1.add(dept_1_1);
		child_1.add(dept_1_2);
		dept_1.setChildren(child_1);
		
		//==========================================
		
		Department dept_2_1 = new Department();
		dept_2_1.setId(new Long(21));
		dept_2_1.setName("开发一部");
		
		Department dept_2_2 = new Department();
		dept_2_2.setId(new Long(22));
		dept_2_2.setName("开发二部");
		
		Department dept_2 = new Department();
		dept_2.setId(new Long(2));
		dept_2.setName("开发部");
		
		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> child_2 = new LinkedHashSet<Department>();
		child_2.add(dept_2_1);
		child_2.add(dept_2_2);
		dept_2.setChildren(child_2);
		
		//===============================================
		
		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		
		return depts;
	}


	/**
	 * 列出所有部门的方式二：遍历集合
	 * @param d
	 */
	@Test
	public void testName3(){
		List<Department> list = findTopListDepartmentList();
		showTree(list,"--");
	}
	public void showTree(Collection<Department> list,String blank) {
		for(Department d : list){
			System.out.println(blank+"┣"+d.getName());
			Set<Department> children = d.getChildren();
			showTree(children,"--" + blank);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}