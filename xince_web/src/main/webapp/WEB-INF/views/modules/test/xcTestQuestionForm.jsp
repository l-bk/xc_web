<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>测试问题管理</title>
<meta name="decorator" content="default" />
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
		
		//添加选项按钮 单击事件
		var arr=new Array("A","B","C","D","E","F","G","H","I","J","k","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		var num=0;
		var newTestId="optionA";
		var newPointId="pointA";
		var newIfSkip="ifSkipA";
		var newSkipNum="skipNumA"
		$("#addOptions").click(function(){
			newTestId=newTestId.replace(newTestId.charAt(newTestId.length-1),arr[num]); 
			newPointId = newPointId.replace(newPointId.charAt(newPointId.length-1),arr[num]);
			newIfSkip = newIfSkip.replace(newIfSkip.charAt(newIfSkip.length-1),arr[num]);
			$("#options").append("<div class=\"control-group\" style=\"display:none;\"><input id=\""+newIfSkip+"\" value=\"0\"/></div><div class=\"control-group\" style=\"border:0px;\" > <label class=\"control-label\">"+arr[num]+":</label><div class=\"controls\" ><input type=\"text\"  id=\""+newTestId+"\" class=\"required\" /></div></div><div class=\"control-group\"style=\"border:0px;\" ><label class=\"control-label\" >分数:</label><div class=\"controls\" style=\"border:0px;\"><input type=\"text\" id=\""+newPointId+"\" style=\"width:80px;\"  /></div></div> ");	
			
			num+=1; 
		});
		
		$("#addSkipOptions").click(function(){
			newTestId=newTestId.replace(newTestId.charAt(newTestId.length-1),arr[num]); 
			newPointId = newPointId.replace(newPointId.charAt(newPointId.length-1),arr[num]);
			newIfSkip = newIfSkip.replace(newIfSkip.charAt(newIfSkip.length-1),arr[num]);
			newSkipNum = newSkipNum.replace(newSkipNum.charAt(newSkipNum.length-1),arr[num]);
			var allNum=${allNum};
			var newStr="<select name=\""+newSkipNum+"\" id=\""+newSkipNum+"\" style=\"margin-left:5px;width:120px;\"><option value=\"0\" >请选择</option>" 
			for(var i=1;i<allNum+1;i++){
				newStr += "<option value=\""+i+"\">"+i+"</option>";
				if(i==allNum){
					newStr+="</select>";
				}
				
			}
			$("#options").append("<div class=\"control-group\" style=\"display:none;\"><input id=\""+newIfSkip+"\" value=\"1\"/></div><div class=\"control-group\" style=\"border:0px;\"><label class=\"control-label\">"+arr[num]+":</label><div class=\"controls\" ><input type=\"text\"  id=\""+newTestId+"\" class=\"required\" /></div></div><div class=\"controls\"><lable>跳转到第</lable>"+newStr+"<label style=\"margin-left:5px;\">题</label></div><div class=\"control-group\" style=\"border:0px;margin-top:10px;\"><label class=\"control-label\" >分数:</label><div class=\"controls\"><input type=\"text\" id=\""+newPointId+"\" style=\"width:80px;\"  /></div></div> ");	
			  
			num+=1; 
		});
		
		
		$("#btnSubmit").click(function(){$("#type").val("keepSave")});
		$("#btnSave").click(function(){$("#type").val("save")});
		$("#btnSubmit,#btnSave,#btnJustSave").click(function(){
			
			var allOptions="";
			for(var i=0;i<num;i++){
				newTestId=newTestId.replace(newTestId.charAt(newTestId.length-1),arr[i]); 
				newPointId = newPointId.replace(newPointId.charAt(newPointId.length-1),arr[i]);
				newSkipNum = newSkipNum.replace(newSkipNum.charAt(newSkipNum.length-1),arr[i]);
				var pointValue=$("#"+newPointId).val() == ""?0:$("#"+newPointId).val();
				if($("#"+newIfSkip).val() == 0){
					allOptions += arr[i]+"-"+$("#"+newIfSkip).val()+"-"+$("#"+newTestId).val()+"-"+pointValue;
				}else if($("#"+newIfSkip).val()==1){
					allOptions += arr[i]+"-"+$("#"+newIfSkip).val()+"-"+$("#"+newSkipNum).val()+"-"+pointValue+"-"+$("#"+newTestId).val();
				}
				if(i!= num-1){
					allOptions += ",";
				}
			}
			$("#AllOptions").val(allOptions); 
		});
		
		
	
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/xcTestQuestion/?testId=${xcTestQuestion.testId}">测试问题列表</a></li>
			<li class="active"><a id="update"
			href="${ctx}/test/xcTestQuestion/form?testId=${xcTestQuestion.testId}">测试问题<shiro:hasPermission
					name="test:xcTestQuestion:edit">${not empty xcTestQuestion.questionId?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="test:xcTestQuestion:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="xcTestQuestion"
		action="${ctx}/test/xcTestQuestion/save" method="post"
		class="form-horizontal">
		<form:hidden path="questionId" />
		<sys:message content="${message}" />
		<div class="control-group" style="border: 0px;">
			<label class="control-label">问题内容：</label>
			<div class="controls">
				<form:input path="questionDetails" htmlEscape="false"
					maxlength="1024" class="input-xlarge required" id="details" />
			</div>
		</div>
		
		<div class="control-group" style="border:0px; display:none;"> 
			<label class="control-label">操作类型</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false"
					maxlength="1024" class="input-xlarge " id="type"/>
			</div>
		</div>
		
		
		
		<div class="control-group" style="border:0px;display:none;">
			<label class="control-lable">测试id</label>
			<div class="controls">
				<form:input path="testId" htmlEscape="false" class="input-xlarge" id="testId"/>
			</div>
		</div>
		
		<div class="control-group" style="border:0px;display:none;">
			<label class="control-lable">选项</label>
			<div class="controls">
				<form:input path="options" htmlEscape="false" class="input-xlarge" id="AllOptions"/>
			</div>
		</div>
		 
		
		<div style="border: 0px;" id="options"></div>

		<c:if test="${xcTestQuestion.questionId == null}">
			<div class="control-group" style="border: 0px; margin-top: 20px;">
				<div class="controls ">
					<input id="addOptions" class="btn" type="button" value="添加选项" />
					<c:if test="${allNum != 0}">
						<input id="addSkipOptions" class="btn" type="button" value="添加跳题选项" />
					</c:if>
				</div>
			</div>
		</c:if>
		
		
	<%-- 	<div>
		<select name="skipNum" id="skipNum">
			<option value="0" >请选择</option> 
			<c:forEach var="item" begin="1" end="${allNum}">
				<option value="${item}">${item}</option>
				</c:forEach>
				</select>
				</div> --%>
		
		<div class="form-actions">
			<shiro:hasPermission name="test:xcTestQuestion:edit">
				<c:if test="${xcTestQuestion.questionId ==null }">
					<input id="btnJustSave" class="btn btn-primary" type="submit" value="保存" type="submit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保存并继续添加" />&nbsp;
					<input id="btnSave" class="btn btn-primary" type="submit"
						value="保存并添加结果" />&nbsp;
				</c:if>
				<c:if test="${xcTestQuestion.questionId != null }">
					<input id="btnUpdate" class="btn btn-primary" type="submit"
						value="修改" />&nbsp;
				</c:if>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
<script>
		
	</script>
</html>