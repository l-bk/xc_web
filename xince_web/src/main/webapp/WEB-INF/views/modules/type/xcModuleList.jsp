<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模块类型管理</title>
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
		<li class="active"><a href="${ctx}/type/xcModule/">模块类型列表</a></li>
		<shiro:hasPermission name="type:xcModule:edit"><li><a href="${ctx}/type/xcModule/form">模块类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcModule" action="${ctx}/type/xcModule/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label style="margin-left:20px;">模块名称:</label>
		<form:input path="moduleName" htmlEscape="false"  class="input-xlarge "/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" style="margin-left:20px;" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模块名称</th>
				<th>模块图片</th>
				<th>模块状态</th>
				<th>模块关联</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xcModule">
			<tr>
				<td>${xcModule.moduleName}</td>
				<td>
					<c:if test="${xcModule.modulePic != null }">
						<img src=${xcModule.modulePic} width="100px" />
					</c:if>
				</td>
				<td>
					<c:if test="${xcModule.moduleType == '0' }">
						${xcModule.picName}
					</c:if>
					<c:if test="${xcModule.moduleType == '1' }">
						${xcModule.testName}
					</c:if>
				</td>
				<td>
					<c:if test="${xcModule.moduleStatus == 0 }">
						下级
					</c:if>
					<c:if test="${xcModule.moduleStatus == 1 }">
						上架
					</c:if>
				</td>
				<shiro:hasPermission name="type:xcModule:edit"><td>
    				<a href="${ctx}/type/xcModule/form?moduleId=${xcModule.moduleId}">修改</a>
					<a href="${ctx}/type/xcModule/delete?moduleId=${xcModule.moduleId}" onclick="return confirmx('确认要删除该模块类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>