<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试选项管理</title>
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
		<li class="active"><a href="${ctx}/test/xcTestOptions/?testQuestionId=${xcTestOptions.testQuestionId}">测试选项列表</a></li>
		<shiro:hasPermission name="test:xcTestOptions:edit"><li><a href="${ctx}/test/xcTestOptions/form?testQuestionId=${xcTestOptions.testQuestionId}">测试选项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcTestOptions" action="${ctx}/test/xcTestOptions/?testQuestionId=${xcTestOptions.testQuestionId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>选项内容 ：</label>
		<form:input path="optionsDetails" htmlEscape="false"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  style="margin-left:20px;"/>
		<input id="btnUpdate" class="btn btn-primary" type="button" value="查询"  style="margin-left:20px;"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:80%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>选项内容</th>
				<th>分数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xcTestOptions">
			<tr>
				<td>${xcTestOptions.optionsKeyword }</td>
				<td>${xcTestOptions.optionsDetails }</td>
				<td>${xcTestOptions.optionsPoint }</td>
				<shiro:hasPermission name="test:xcTestOptions:edit"><td>
    				<a href="${ctx}/test/xcTestOptions/form?id=${xcTestOptions.optionsId}">修改</a>
					<a href="${ctx}/test/xcTestOptions/delete?id=${xcTestOptions.optionsId}" onclick="return confirmx('确认要删除该测试选项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>