<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>测试选项管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//alert(${xcTestOptions.testType});
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
			
			if($("#yesSkip").attr("checked") == "checked"){
					$("#returnQues").show();
			};
			
			if($("#noSkip").attr("checked") == "checked"){
				$("#returnQues").hide();
				$("#detailsArea").show();
								
			};
			
			if($("#yesReturn").attr("checked") == "checked"){
				$("#returnArea").show();
				$("#returnQues").hide();
		};
		
		if($("#noReturn").attr("checked") == "checked"){
			$("#returnArea").hide();
			$("#returnQues").show();
		};
			
			noReturn
			
			var arr=new Array("A","B","C","D","E","F","G","H","I","J","k","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
			var num = ${num};
			$("#btnSubmit").click(function(){
				$("#optionsKeyword").val(arr[num]);
			});
			
			 $("#yesSkip").change(function(){
				 
				if($("#yesSkip").attr("checked") == "checked"){
					if(${quesNum}.length> 0){
						$("#returnQues").show();
					}else{
						alert("没有其他问题可跳转");
					}
				}
			}); 
			$("#noSkip").change(function(){
			if($("#noSkip").attr("checked") == "checked"){
					$("#returnQues").hide();
					$("#detailsArea").show();
									
			}
			});
				
			 $("#yesReturn").change(function(){
				 
					if($("#yesReturn").attr("checked") == "checked"){
						if(${quesNum}.length> 0){
							$("#returnQues").hide();
							$("#returnArea").show();
						}else{
							alert("没有结果可跳");
						}
					}
				}); 
				$("#noReturn").change(function(){
				if($("#noReturn").attr("checked") == "checked"){
						$("#returnArea").hide();
						$("#returnQues").show();
						
				}
				});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/test/xcTestOptions/?testQuestionId=${xcTestOptions.testQuestionId}&testId=${xcTestOptions.testId}&testType=${xcTestOptions.testType}">测试选项列表</a></li>
		<li class="active"><a
			href="${ctx}/test/xcTestOptions/form?optionsId=${xcTestOptions.optionsId}&testQuestionId=${xcTestOptions.testQuestionId}&testId=${xcTestOptions.testId}&testType=${xcTestOptions.testType}">测试选项<shiro:hasPermission
					name="test:xcTestOptions:edit">${not empty xcTestOptions.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="test:xcTestOptions:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="xcTestOptions"
		action="${ctx}/test/xcTestOptions/save?testQuestionId=${xcTestOptions.testQuestionId}&testId=${testId}&testType=${xcTestOptions.testType}"
		method="post" class="form-horizontal">
		<form:hidden path="optionsId" />
		<sys:message content="${message}" />

		<div class="control-group" style="display: none;">
			<label class="control-label">选项序号：</label>
			<div class="controls">
				<form:input path="optionsKeyword" htmlEscape="false"
					class="input-xlarge required" id="optionsKeyword" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<c:if test="${xcTestOptions.testType == '0' }">
			<div class="control-group" style="border: 0px;">
				<label class="control-label">是否跳题:</label>
				<div class="controls">
					<form:radiobutton path="ifSkip" id="noSkip" htmlEscape="false"
						class="input-xlarge required" value="0" label="否" />
					<form:radiobutton path="ifSkip" id="yesSkip" htmlEscape="false"
						class="input-xlarge required" value="1" label="是" />
				</div>
			</div>
		</c:if>

		<div class="control-group" style="border: 0px;">
			<label class="control-label">选项内容：</label>
			<div class="controls" id="detailsArea">
				<form:input path="optionsDetails" htmlEscape="false"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			<%-- <div class="controls" id="skipArea"
				style="display: none; margin-top: 10px;">
				<span>跳转到第&nbsp;</span>
				<form:select path="skipNum" htmlEscape="false"
					class="input-xlarge required" style="width:120px;">
					<form:option value="0">请选择</form:option>
					<c:forEach var="item" items="${quesNum}">
						<form:option value="${item}">${item}</form:option>
					</c:forEach>
				</form:select>
				<span>&nbsp;题</span>
			</div> --%>
		</div>
		<c:if test="${xcTestOptions.testType == '0' }">

			<div class="control-group" style="border: 0px;">
				<label class="control-label">选项分数：</label>
				<div class="controls">
					<form:input path="optionsPoint" htmlEscape="false"
						class="input-xlarge " placeholder="输入分数，限制整数" />
				</div>
			</div>
		</c:if>

			<div class="control-group" id="returnQues" style="border:0px;">
				<label class="control-label">跳题：</label>
				<div class="controls">
					<form:select path="skipNum" htmlEscape="false"
						class="input-xlarge required" style="width:120px;">
						<form:option value="0">请选择</form:option>
						<c:forEach var="item" items="${quesNum}">
							<form:option value="${item}">${item}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>

		<c:if test="${xcTestOptions.testType == '1' }">

			

			<div class="control-group" style="border: 0px;">
				<label class="control-label">是否跳结果:</label>
				<div class="controls">
					<form:radiobutton path="ifReturn" id="noReturn" htmlEscape="false"
						class="input-xlarge required" value="0" label="否" />
					<form:radiobutton path="ifReturn" id="yesReturn" htmlEscape="false"
						class="input-xlarge required" value="1" label="是" />
				</div>
			</div>
		</c:if>
		<c:if test="${xcTestOptions.testType == '1' }">
			<div class="control-group" style="border: 0px; display: none;"
				id="returnArea">
				<label class="control-label">跳结果： </label>
				<div class="controls">
					<span>跳到&nbsp;</span>
					<form:select path="returnAnswerId" htmlEscape="false"
						class="input-xlarge required" style="width:120px;">
						<form:option value="0">请选择</form:option>
						<c:forEach var="answer" items="${answer}">
							<form:option value="${answer.answerId}">${answer.answerNum}</form:option>
						</c:forEach>
						<span>结果</span>
					</form:select>
				</div>
			</div>
		</c:if>

		<div class="form-actions">
			<shiro:hasPermission name="test:xcTestOptions:edit">
				<c:if test="${xcTestOptions.optionsId == null }">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp;
		</c:if>
				<c:if test="${xcTestOptions.optionsId != null }">
					<input id="btnUpdate" class="btn btn-primary" type="submit"
						value="修 改" />&nbsp;
		</c:if>
			</shiro:hasPermission>

			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>