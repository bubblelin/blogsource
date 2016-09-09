# Office Auto System

### 需求
作为一种信息化的工具，通过统一办公规范，加快信息流动，可以有效地提高员工工作效率，降低企业运营成本，OA系统实现了系统管理、系统权限、内部论坛、审批流转等4大功能模块。

### 开发环境
Windows7/10、MyEclipse2014、MySQL5.5、Tomcat7、IE、Firefox

### 系统架构
基于J2ee、mvc模式分层开发，采用以下技术

* Struts2 （控制层技术）
* Hibernate3.6 （持久层技术）
* Spring3.2 （业务逻辑层，框架整合）
* JBPM4.4 （流程管理技术）
* jQuery （表单校验）
* Junit4 （测试）

### 业务逻辑

项目实现了以下功能：
1. 系统管理分为岗位管理、部门管理和用户管理用于组织内部划分级别；
2. 论坛模块分为论坛管理和论坛主页用于组织内部讨论；
3. 审批流转分为审批流程管理和表单模板管理，以及起草申请流程、我的申请查阅、待我审批，实现了申请模板管理，再由成员申请的发起，到上级审批等功能；
4. 系统权限实现每一位组织内成员具有使用该系统的不同模块，由系统管理员根据成员等级分配。

### 开发流程，螺旋模式开发：一个一个模块开发
1. 系统管理模块：department/role/user
2. 论坛模块：forumManage/forum/topic/reply
3. 审批流转: template/process/flow
4. 系统权限: privilege
![Alt text](http://ww1.sinaimg.cn/mw690/0062aWY4gw1f7n3ta8e23j30rr0jggwx.jpg)
