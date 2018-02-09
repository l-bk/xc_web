<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试答案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				badInput:false,
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
			
			$("#answerPointGt").change(function(){ 
				var gtValue=$("#answerPointGt").val();
				if(parseInt(gtValue) != gtValue){
					alert("分数范围请填入整数");
				}else{
					var lt= $("#answerPointLt").val();
					if(parseInt(lt) == lt){
						if(parseInt(gtValue)> parseInt(lt)){
							alert("分数范围格式由小到大，例：0~10");
						}
					}
				}
			});
			$("#answerPointLt").change(function(){
				var ltValue= $("#answerPointLt").val();
				if(parseInt(ltValue) != ltValue){
					alert("分数范围请填入整数");
				}else{
					var gt=$("#answerPointGt").val();
					if(parseInt(gt) == gt){
						if(parseInt(gt)> parseInt(ltValue)){
							alert("分数范围格式由小到大，例：0~10");
						}
					}
				}
				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/xcTestAnswer/?testId=${xcTestAnswer.testId}">测试结果列表</a></li>
		<li class="active"><a href="${ctx}/test/xcTestAnswer/form?id=${xcTestAnswer.answerId}">测试结果<shiro:hasPermission name="test:xcTestAnswer:edit">${not empty xcTestAnswer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="test:xcTestAnswer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xcTestAnswer" action="${ctx}/test/xcTestAnswer/save?testId=${xcTestAnswer.testId}" method="post" class="form-horizontal">
		<form:hidden path="answerId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">结果关键字：</label>
			<div class="controls">
				<form:input path="answerKeyword" htmlEscape="false" maxlength="64" class="input-xlarge requried"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结果简述：</label>
			<div class="controls">
				<form:input path="answerSketch" htmlEscape="false" maxlength="64" class="input-xlarge requried"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总分数范围内：</label>
			<div class="controls">
				<form:input path="answerPointGt" htmlEscape="false" class="input-xlarge required" style="width:80px;" pattern="^[1-9]*$" placeholder="输入整数"/>
					<label>&nbsp;~ &nbsp;</label>
				<form:input path="answerPointLt" htmlEscape="false" class="input-xlarge required" style="width:80px;" pattern="^[1-9]*$" placeholder="输入整数"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">总分数大于：</label>
			<div class="controls">
				<form:input path="answerPointGt" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">结果详情：</label>
			<div class="controls">
				<form:input path="answerDetails" htmlEscape="false" maxlength="1024" class="input-xlarge requried"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">答案图片：</label>
			<div class="controls">
							<form:hidden id="answerPic" path="answerPic" htmlEscape="false" class="input-xlarge" disabled="disabled" />
				<sys:ckfinder input="answerPic" type="images" uploadPath="/test"
					selectMultiple="false" maxWidth="100" maxHeight="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二维码：</label>
			<div class="controls">
			<form:hidden id="answerQRCode" path="answerQRCode" htmlEscape="false" class="input-xlarge" disabled="disabled" />
				<sys:ckfinder input="answerQRCode" type="images" uploadPath="/test"
					selectMultiple="false" maxWidth="100" maxHeight="100" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="test:xcTestAnswer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>