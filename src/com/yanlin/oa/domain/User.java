package com.yanlin.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户的实体类
 * @author bubblelin
 *
 */

public class User {
	private Long id;
	private String loginName;
	private String name;
	private int gender;
	private String phone;
	private String email;
	private String description;
	private String password;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();
	
	/**
	 * 根据权限名称判断用户是否权限
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		//超级用户例外，直接返回true
		if(this.isAdmin()){
			return true;
		}
		for(Role role : roles){
			Set<Privilege> privileges = role.getPrivileges();
			for(Privilege p : privileges){
				String pName = p.getName();
				if(pName.equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据指定的 url 判断用户是否有相应的权限
	 * @param url
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String url) {
		//超级用户例外，直接返回true
		if(this.isAdmin()){
			return true;
		}
		
		for(Role role : roles){
			Set<Privilege> privileges = role.getPrivileges();
			for(Privilege p : privileges){
				String pUrl = p.getUrl();
				if(url.equals(pUrl)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否是超级管理员
	 * @return
	 */
	private boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
