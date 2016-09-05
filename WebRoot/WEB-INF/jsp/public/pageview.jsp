<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div id=PageSelectorBar>
	<div id=PageSelectorMemo>
		页次：${currentPage }/${totalPage}页 &nbsp;
		每页显示：${pageSize }条 &nbsp;
		总记录数：${totalSize }条
	</div>
	<div id=PageSelectorSelectorArea>
		<a href="#" onclick="gotoPageNum(1)" title="首页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
		
		<s:iterator begin="beginPageIndex" end="endPageIndex" var="s">
			<s:if test="currentPage == #s">
				<span class="PageSelectorNum PageSelectorSelected"><s:property value="#s"/></span>
			</s:if>
			<s:else>
				<span class="PageSelectorNum" style="cursor: pointer;" onClick="gotoPageNum(<s:property value='#s'/>);">
					<s:property value="#s"/>
				</span>
			</s:else>
		</s:iterator>
		
		<a href="#" onclick="gotoPageNum(${totalPage})" title="尾页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>
		
		转到：
		<select id="pageSelect" onchange="gotoPageNum(this.value)">
			<s:iterator begin="1" end="totalPage" var="s">
				<option value="<s:property value='#s'/>">
					<s:property value="#s"/>
				</option>
			</s:iterator>
		</select>
		<input onFocus="this.select();" maxlength="2" class="inputStyle" type="text" value="1" name="currPage" tabindex="0"/>
		<input type="submit" name="goBtn" value="Go" class="MiddleButtonStyle" />
	</div>
</div>
		
<script type="text/javascript">
		$("#pageSelect").val(${currentPage});

</script>
<script type="text/javascript">
	function gotoPageNum(pageNum){
		
		//动态添加一个隐藏输入框，传递页码值
		$("#pageForm").append('<input type="hidden" value="'+pageNum+'" name="currentPage">');
		$("#pageForm").submit();
	}

</script>