<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>测试选项管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		//alert(${xcTestOptions.ifReturn});

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/test/xcTestOptions/?testQuestionId=${xcTestOptions.testQuestionId}&testId=${xcTestOptions.testId}&testType=${xcTestOptions.testType}">测试选项列表</a></li>
		<shiro:hasPermission name="test:xcTestOptions:edit">
			<li><a
				href="${ctx}/test/xcTestOptions/form?testQuestionId=${xcTestOptions.testQuestionId}&testId=${xcTestOptions.testId}&testType=${xcTestOptions.testType}">测试选项添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcTestOptions"
		action="${ctx}/test/xcTestOptions/?testQuestionId=${xcTestOptions.testQuestionId}&testId=${xcTestOptions.testId}&testType=${xcTestOptions.testType}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>选项内容 ：</label>
		<form:input path="optionsDetails" htmlEscape="false" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"
			style="margin-left: 20px;" />
		<input id="returnUpdate" class="btn btn-primary" type="button"
			value="返回问题列表"
			onclick="window.location.href='${ctx}/test/xcTestQuestion/list?testId=${xcTestOptions.testId}&testType=${xcTestOptions.testType}'"
			style="margin-left: 20px;" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed"
		style="width: 80%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>选项内容</th>
				<c:if test="${testType=='0'}">
					<th>分数</th>
				</c:if>
				<c:if test="${testType=='1'}">
					<th>跳转</th>
				</c:if>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="xcTestOptions">
				<tr>
					<td>${xcTestOptions.optionsKeyword }</td>
					<td>${xcTestOptions.optionsDetails }</td>
					<c:if test="${testType == '0' }">
						<td>${xcTestOptions.optionsPoint}分</td>
					</c:if>
					<c:if test="${testType == '1' }">
						<c:choose>
							<c:when test="${xcTestOptions.ifReturn == '0' }">
								<td>跳转到第&nbsp;${xcTestOptions.skipNum}&nbsp;题</td>
							</c:when>
							<c:when test="${xcTestOptions.ifReturn == '1' }">
								<td>跳转到 &nbsp;${xcTestOptions.answerNum} &nbsp;结果</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<%-- <c:if test="${xcTestOptions.ifReturn == '0' }">
						<c:if test="${xcTestOptions.skipNum != null}">
							<td>跳转到第&nbsp;${xcTestOptions.skipNum}&nbsp;题</td>
						</c:if>
					</c:if>
					<c:if test="${xcTestOptions.ifReturn == '1' }">
						<c:if test="${xcTestOptions.answerNum != null }">
						<td>跳转到 &nbsp;${xcTestOptions.answerNum} &nbsp;结果</td>
						</c:if>
					</c:if> --%>

					</c:if>
					<shiro:hasPermission name="test:xcTestOptions:edit">
						<td><a
							href="${ctx}/test/xcTestOptions/form?optionsId=${xcTestOptions.optionsId}&testId=${testId}&testType=${testType}">修改</a>
							<a
							href="${ctx}/test/xcTestOptions/delete?testQuestionId=${xcTestOptions.testQuestionId}&testId=${testId}&optionsId=${testOptions.optionsId}&testType=${testType}"
							onclick="return confirmx('确认要删除该测试选项吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<script>
		/* $("#returnUpdate").click(function(){
			$.ajax({
				url:"${ctx}/test/xcTestQuestion/?testId=${xcTestOptions.testId}",
				success:function(){}
			});
		}); */
	</script>
</body>
</html>