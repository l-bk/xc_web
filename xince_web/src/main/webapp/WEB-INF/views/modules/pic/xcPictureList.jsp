<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片模块管理</title>
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
		<li class="active"><a href="${ctx}/pic/xcPicture/?picType=${picType}">
		<c:if test="${picType == '1'}">
			轮播图列表
		</c:if>
		<c:if test="${picType == '2'}">
			二维码列表
		</c:if>
		</a></li>
		<shiro:hasPermission name="pic:xcPicture:edit"><li><a href="${ctx}/pic/xcPicture/form?picType=${picType}">
		<c:if test="${picType == '1'}">
			添加轮播图
		</c:if>
		<c:if test="${picType == '2'}">
			添加二维码
		</c:if>
		</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xcPicture" action="${ctx}/pic/xcPicture/?picType=${picType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			&nbsp;&nbsp;<label>图片状态:</label>
			<form:select path="picStatus" style="width:100px; margin-left:10px;">
				<form:option value="" >请选择</form:option>
				<form:option value="1">上架</form:option>
				<form:option value="2">下架</form:option>
			</form:select>
			<input id="btnSubmit" class="btn btn-primary" style="margin-left:30px;"  type="submit" value="查询"/>
			
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed"
	style="margin-left: 10px; width: 80%">
		<thead>
			<tr>
				<th>图片</th>
				<th>图片类型</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xcPicture">
			<tr>
				<td ><img src="${xcPicture.picPath}" width="100px"  /></td>
				<td>
					<c:if test="${xcPicture.picType == '1' }">
						轮播图
					</c:if>
					<c:if test="${xcPicture.picType == '2'}">
						二维码
					</c:if>
				</td>
				<td>
					<c:if test="${xcPicture.picStatus == '1' }">
						上架
					</c:if>
					<c:if test="${xcPicture.picStatus == '2' }">
						下架
					</c:if>
				</td>
				<shiro:hasPermission name="pic:xcPicture:edit"><td>
					<c:if test="${xcPicture.picStatus == '1' }">
						<a href="${ctx}/pic/xcPicture/updateStatus?picId=${xcPicture.picId}&picStatus=2&picType=${picType}">下架</a>
					</c:if>
					<c:if test="${xcPicture.picStatus == '2' }">
						<a href="${ctx}/pic/xcPicture/updateStatus?picId=${xcPicture.picId}&picStatus=1&picType=${picType}">上架</a>
					</c:if>
    				<%-- <a href="${ctx}/pic/xcPicture/form?id=${xcPicture.picId}">修改</a> --%>
					<a href="${ctx}/pic/xcPicture/delete?picId=${xcPicture.picId}&picType=${picType}" onclick="return confirmx('确认要删除该图片模块吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>