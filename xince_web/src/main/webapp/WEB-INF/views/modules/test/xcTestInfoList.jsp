<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/xcTestInfo/">测试信息列表</a></li>
		<shiro:hasPermission name="test:xcTestInfo:edit"><li><a href="${ctx}/test/xcTestInfo/form">测试信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcTestInfo" action="${ctx}/test/xcTestInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>测试主题：</label>
				<form:input path="testSubject" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>测试主题</th>
				<th>图片</th>	
				<th>是否收费</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xcTestInfo">
			<tr>
				<td>${xcTestInfo.testSubject }</td>
				<td>
					<c:if test="${xcTestInfo.testPic != null }">
						<img src=${xcTestInfo.testPic} width="100px" />
					</c:if>
				</td>
				<td>
					<c:if test="${xcTestInfo.payFlag == '1' }">
						免费
					</c:if>
					<c:if test="${xcTestInfo.payFlag == '2' }">
						付费
					</c:if>
				</td>
				<td>
				<shiro:hasPermission name="test:xcTestInfo:edit">
    				<a href="${ctx}/test/xcTestInfo/form?testId=${xcTestInfo.testId}">修改</a>
					<a href="${ctx}/test/xcTestInfo/delete?testId=${xcTestInfo.testId}" onclick="return confirmx('确认要删除该测试模块吗？', this.href)">删除</a>
				</shiro:hasPermission>
				<br/>
				<shiro:hasPermission name="test:xcTestQuestion:view">
					<a href="${ctx}/test/xcTestQuestion/list?testId=${xcTestInfo.testId}">查看问题列表</a>
				</shiro:hasPermission>
				<br/>
				<shiro:hasPermission name="test:xcTestAnswer:view">
					<a href="${ctx}/test/xcTestAnswer/list?testId=${xcTestInfo.testId}">查看结果列表</a>
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>