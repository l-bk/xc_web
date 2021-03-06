<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试问题管理</title>
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
		<li class="active"><a href="${ctx}/test/xcTestQuestion/?testId=${xcTestQuestion.testId}&testType=${xcTestQuestion.testType}">测试问题列表</a></li>
		<shiro:hasPermission name="test:xcTestQuestion:edit"><li><a href="${ctx}/test/xcTestQuestion/form?testId=${xcTestQuestion.testId}&testType=${xcTestQuestion.testType}">
		<c:if test="${xcTestQuestion.testType == '0' }">
			分数类型测试问题添加
		</c:if>
		<c:if test="${xcTestQuestion.testType == '1' }">
			跳题类型测试问题添加
		</c:if>
		</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcTestQuestion" action="${ctx}/test/xcTestQuestion/?testId=${xcTestQuestion.testId}&testType=${xcTestQuestion.testType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label style="margin-left:20px">问题内容:</label>
		<form:input path="questionDetails" htmlEscape="false" class="input-xlarge "/>
		<input id="btnSubmit" style="margin-left:20px;" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:80%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>问题</th>
				<th>选项</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testQuestion">
			<tr>
				<td>${testQuestion.questionNum}</td>
				<td>${testQuestion.questionDetails}</td>
				<th><label>${testQuestion.options}</label></th>
				<td>
					<shiro:hasPermission name="test:xcTestQuestion:edit">
    					<a href="${ctx}/test/xcTestQuestion/form?testId=${xcTestQuestion.testId}&questionId= ${testQuestion.questionId }&testType= ${xcTestQuestion.testType} ">修改</a>
						<a href="${ctx}/test/xcTestQuestion/delete?testId=${xcTestQuestion.testId}&questionId= ${testQuestion.questionId }&testType= ${xcTestQuestion.testType}" onclick="return confirmx('确认要删除该测试问题及选项吗？', this.href)">删除</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="test:xcTestOptions:view">
						<a href="${ctx}/test/xcTestOptions/?testQuestionId=${testQuestion.questionId}&testId=${xcTestQuestion.testId}&testType=${xcTestQuestion.testType}">查看选项列表</a>
					</shiro:hasPermission>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>