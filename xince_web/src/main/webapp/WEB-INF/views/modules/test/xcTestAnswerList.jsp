<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试答案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			alert(${xcTestAnswer.testType});
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
		<li class="active"><a href="${ctx}/test/xcTestAnswer/?testId=${xcTestAnswer.testId}&testType=${xcTestAnswer.testType}">测试结果列表</a></li>
		<shiro:hasPermission name="test:xcTestAnswer:edit"><li><a href="${ctx}/test/xcTestAnswer/form?testId=${xcTestAnswer.testId}&testType=${xcTestAnswer.testType}">测试结果添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcTestAnswer" action="${ctx}/test/xcTestAnswer/?testId=${xcTestAnswer.testId}&testType=${xcTestAnswer.testType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label style="margin-left:20px;">结果关键字：</label>
			<form:input path="answerKeyword" htmlEscape="false"  />
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="margin-left:20px;"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>结果关键字</th>
				<th>简述</th>
				<th>图片</th>
				<th>判定条件</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testAnswer">
			<tr>
				<td>${testAnswer.answerKeyword}</td>
				<td>${testAnswer.answerSketch}</td>
				<td><c:if test="${testAnswer.answerSketch}">
					<img src=${testAnswer.answerPic}/>
				</c:if></td>
				<td>${testAnswer.answerPointGt} <label>&nbsp;~&nbsp;</label> ${testAnswer.answerPointLt} </td>
				<shiro:hasPermission name="test:xcTestAnswer:edit"><td>
    				<a href="${ctx}/test/xcTestAnswer/form?testId=${xcTestAnswer.testId}&answerId=${testAnswer.answerId}&testType=${xcTestAnswer.testType}">修改</a>
					<a href="${ctx}/test/xcTestAnswer/delete?testId=${xcTestAnswer.testId}&answerId=${testAnswer.answerId}&testType=${xcTestAnswer.testType}" onclick="return confirmx('确认要删除该测试结果吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>