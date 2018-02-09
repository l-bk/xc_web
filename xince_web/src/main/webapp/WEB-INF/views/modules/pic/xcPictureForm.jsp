<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pic/xcPicture/?picType=${xcPicture.picType}">图片模块列表</a></li>
		<li class="active"><a href="${ctx}/pic/xcPicture/form?picId=${xcPicture.picId}&picType=${xcPicture.picType}">图片模块<shiro:hasPermission name="pic:xcPicture:edit">${not empty xcPicture.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pic:xcPicture:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xcPicture" action="${ctx}/pic/xcPicture/save?picType=${xcPicture.picType }" method="post" class="form-horizontal">
		<form:hidden path="picId"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="border:0px;">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden id="picPath" path="picPath" htmlEscape="false" maxlength="512" class="input-xlarge" disabled="disabled"/>
				<sys:ckfinder input="picPath" type="images" uploadPath="/pic" selectMultiple="false" maxWidth="100" maxHeight="100" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">图片类型:</label>
			<div class="controls">
				<form:radiobutton path="picType" htmlEscape="false" maxlength="10" class="input-xlarge required" value="1" label="轮播图"/>
				<form:radiobutton path="picType" htmlEscape="false" maxlength="10" class="input-xlarge required" value="2" label="二维码"/>
			</div>
		</div> --%>
		<div class="control-group" style="display:none;">
			<label class="control-label">图片类型:</label>
			<div class="controls">
				
			</div>
		</div>
		<c:if test="${xcPicture.picType == '2'}">
			<div class="control-group" style="border:0px;">
				<label class="control-label">图片名称：</label>
				<div class="controls">
					<form:input path="picName" htmlEscape="false" class="input-xlarge required" />
				</div>
			</div>
		</c:if>
		<div class="control-group" style="border:0px;">
			<label class="control-label">图片状态:</label>
			<div class="controls">
				<form:radiobutton path="picStatus" htmlEscape="false" maxlength="10" class="input-xlarge required" value="1" label="上架"/>
				<form:radiobutton path="picStatus" htmlEscape="false" maxlength="10" class="input-xlarge required" value="2" label="下架"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pic:xcPicture:edit"><input id="btnSubmit" class="btn btn-primary " type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>