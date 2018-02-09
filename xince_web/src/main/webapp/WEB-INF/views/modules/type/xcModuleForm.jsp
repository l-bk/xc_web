<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模块类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			if($("#picButton").is(":checked") == true){
				$("#picArea").show();
				$("#testArea").hide();
			};
			if($("#testButton").is(":checked") == true){
				$("#picArea").hide();
				$("#testArea").show();
			};
			
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
			
			$("#picButton").change(function(){
				
				if($("#picButton").is(":checked") == true){
					$("#picArea").show();
					$("#testArea").hide();
				}
			})
			$("#testButton").change(function(){
				if($("#testButton").is(":checked") == true){
					$("#picArea").hide();
					$("#testArea").show();
				}
			})
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/type/xcModule/">模块类型列表</a></li>
		<li class="active"><a href="${ctx}/type/xcModule/form?moduleId=${xcModule.moduleId}">模块类型<shiro:hasPermission name="type:xcModule:edit">${not empty xcModule.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="type:xcModule:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xcModule" action="${ctx}/type/xcModule/save" method="post" class="form-horizontal">
		<form:hidden path="moduleId"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="border:0px;">
			<label class="control-label">模块名：</label>
			<div class="controls">
				<form:input path="moduleName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" style="border:0px;">
			<label class="control-label">模块类型：</label>
			<div class="controls">
				<form:radiobutton path="moduleType" id="picButton" htmlEscape="false" class="input-xlarge required" value="0" label="二维码"/>
				<form:radiobutton path="moduleType" id="testButton" htmlEscape="false" class="input-xlarge required" value="1" label="测试数据"/>
			</div>
		</div>
		<div class="control-group" style="border:0px;display:none;" id="testArea" >
			<label class="control-label">测试数据名称：</label>
			<div class="controls">
			<form:select path="testId" htmlEscape="false" class="input-xlarge required">
				<form:option value="" label="请选择"/>
				<c:forEach items="${testInfo}" var="item" >
					<form:option value="${item.testId}"  label="${item.testSubject}"/>
				</c:forEach>
			</form:select>
			</div>
		</div>
		
		<div class="control-group" style="border:0px;display:none;" id="picArea">
			<label class="control-label">图片名称：</label>
			<div class="controls">
				<form:select path="picId" htmlEscape="false"  class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<c:forEach items="${pic}" var="picture">
					<form:option value="${picture.picId}"  label="${picture.picName}"/>
					</c:forEach>
					</form:select>
			</div>
		</div>
		<div class="control-group" style="border:0px;">
			<label class="control-label">模块状态：</label>
			<div class="controls">
				<form:radiobutton path="moduleStatus" htmlEscape="false" class="input-xlarge required" value="1" label="上架"/>
				<form:radiobutton path="moduleStatus" htmlEscape="false" class="input-xlarge required" value="0" label="下架"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="type:xcModule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>