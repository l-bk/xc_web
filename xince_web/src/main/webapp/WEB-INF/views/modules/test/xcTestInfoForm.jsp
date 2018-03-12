<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>测试模块管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				if(${xcTestInfo.payFlag == 2}){
					$("#payArea").show();
				}
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
				
				$("#pay").change(function() {
					if ($("#pay").is(":checked") == true) {
						$("#payArea").show();
					}
				});
				$("#noPay").change(function(){
					if($("#noPay").is(":checked") == true){
						$("#payArea").hide();
					}
				});
				
			});
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/xcTestInfo/">测试模块列表</a></li>
		<li class="active"><a
			href="${ctx}/test/xcTestInfo/form?testId=${xcTestInfo.testId}">测试模块<shiro:hasPermission
					name="test:xcTestInfo:edit">${not empty xcTestInfo.testId?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="test:xcTestInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="xcTestInfo"
		action="${ctx}/test/xcTestInfo/save" method="post"
		class="form-horizontal">
		<form:hidden path="testId" />
		<sys:message content="${message}" />
		<div class="control-group" style="border:0px;">
			<label class="control-label">测试主题：</label>
			<div class="controls">
				<form:input path="testSubject" htmlEscape="false" maxlength="128"
					class="input-xlarge required" />
			</div>
		</div>
		
		<div class="control-group" style="border:0px;">
			<label class="control-label">在测人数:</label>
			<div class="controls">
				<form:input path="testUseNum" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group" style="border:0px;">
			<label class="control-label">打赏人数:</label>
			<div class="controls">
				<form:input path="rewardNum" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group" style="border:0px;">
			<label class="control-label">测试logo：</label>
			<div class="controls">
				<form:hidden id="testLogo" path="testLogo" htmlEscape="false" class="input-xlarge" disabled="disabled" />
				<sys:ckfinder input="testLogo" type="images" uploadPath="/testLogo"
					selectMultiple="false" maxWidth="100" maxHeight="100" />
			</div>
		</div>
		
		<div class="control-group" style="border:0px;">
			<label class="control-label">测试图片：</label>
			<div class="controls">
				<form:hidden id="testPic" path="testPic" htmlEscape="false" class="input-xlarge" disabled="disabled" />
				<sys:ckfinder input="testPic" type="images" uploadPath="/testPic"
					selectMultiple="false" maxWidth="100" maxHeight="100" />
			</div>
		</div>
		<div class="control-group" style="border:0px;display:none;">
			<label class="control-label">测试类型：</label>
			<div class="controls">
				<form:input path="testType" id="testType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div> 
		<div class="control-group" style="border:0px;">
			<label class="control-label">测试介绍：</label>
			<div class="controls">
				<form:textarea path="testPresentation" htmlEscape="false"
					maxlength="1024" class="input-xlarge" />
			</div>
		</div>
		
		<div class="control-group" style="border:0px;">
			<label class="control-label">是否首页弹窗显示：</label>
			<div class="controls">
				<form:radiobutton path="ifShow" htmlEscape="false" class="input-xlarge required" value="0" label="否"/>
				<form:radiobutton path="ifShow" htmlEscape="false" class="input-xlarge required" value="1" label="是"/>
			</div>
		</div>
	
		<div class="control-group" style="border:0px;">
			<label class="control-label">是否付费：</label>
			<div class="controls">
				<form:radiobutton  id="noPay" path="payFlag" htmlEscape="false"
					class="input-xlarge required" value="1" label="免费" />
				<form:radiobutton id="pay" path="payFlag" htmlEscape="false"
					class="input-xlarge required" value="2" label="付费" />
			</div>
		</div>
		
		
		<div  id="payArea" style="display:none;" style="border:0px;">
			<div class="control-group">
				<label class="control-label">题目数：</label>
				<div class="controls">
					<form:input path="testNum" htmlEscape="false" maxlength="16"
						class="input-xlarge" />
				</div>
			</div>
			<div class="control-group" style="border:0px;">
				<label class="control-label">预计用时：</label>
				<div class="controls">
					<form:input path="testUseTime" htmlEscape="false" maxlength="32"
						class="input-xlarge " />
				</div>
			</div>
			<div class="control-group" style="border:0px;">
				<label class="control-label">原价：</label>
				<div class="controls">
					<form:input path="testPrice" htmlEscape="false"
						class="input-xlarge" placeholder="输入金钱 例：0.1" />
				</div>
			</div>
			<div class="control-group" style="border:0px;">
				<label class="control-label">优惠价：</label>
				<div class="controls">
					<form:input path="testPreferentialPrice" htmlEscape="false"
						class="input-xlarge requried" pattern="^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$" placeholder="输入金钱 例：0.1"/>
				</div>
			</div>
			<div class="control-group" style="border:0px;">
				<label class="control-label">报告长度：</label>
				<div class="controls">
					<form:input path="testReportLength" htmlEscape="false"
						maxlength="16" class="input-xlarge" />
				</div>
			</div>
			<div class="control-group" style="border:0px;">
				<label class="control-label">温馨提示：</label>
				<div class="controls">
					<form:textarea path="testPrompt" htmlEscape="false"
						maxlength="1024" class="input-xlarge" />
				</div>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="test:xcTestInfo:edit">
			<c:if test="${xcTestInfo.testId != null}">
				<input id="btnUpdate" class="btn btn-primary" type="submit" value="修改"/>&nbsp;
			</c:if>
			<c:if test="${xcTestInfo.testId == null}">
				<input id="btnSubmitPoint" class="btn btn-primary" type="submit"
					value="保存并添加分数类型问题 " />&nbsp;
				<input id="btnSubmitSkip" class="btn btn-primary" type="submit"
					value="保存并添加跳题类型问题 " />&nbsp;
			</c:if>		
			<script>
				$("#btnSubmitPoint").click(function(){
					
					$("#testType").val("0");
					
				});
				$("#btnSubmitSkip").click(function(){
					$("#testType").val("1");
				})
			</script>
					</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>