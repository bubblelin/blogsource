<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<title>导航菜单</title>
<%@include file="/WEB-INF/jsp/public/header.jsp" %>
<script language="JavaScript" src="script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
		<s:iterator value="#application.privilegeTopList">
		<!-- 从session中获取登陆的用户，根据用户的角色，判断其对应的权限是否和遍历出来的一致 -->
			<!-- 通过OGNL表达式调用对象的方法 -->
			<s:if test="#session.loginUser.hasPrivilegeByName(name)">
		        <li class="level1">
		            <div onClick="menuClick(this);" class="level1Style">
		            	<img src="${pageContext.request.contextPath}/style/images/MenuIcon/top_${id}.gif" class="Icon" />${name}
		            </div>
		            <ul style="display: none;" class="MenuLevel2">
			            <s:iterator value="children">
			                <li class="level2">
			                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif"/> 
				                    <a target="right" href="${pageContext.request.contextPath}${url}.do">${name }</a>
			                    </div>
			                </li>       
			            </s:iterator>
		            </ul>
		        </li>
			</s:if>
		</s:iterator>
    </ul>
</div>
</body>
</html>
