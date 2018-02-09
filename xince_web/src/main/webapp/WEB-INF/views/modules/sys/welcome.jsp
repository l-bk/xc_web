<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>欢迎页面</title>
<meta name="decorator" content="default" />

<style type="text/css">
body {
	background: #f1f1f1;
}

.content {
	padding: 20px;
}

.content h3 {
	padding: 10px;
}

.link-panel {
	display: table;
	width: 100%;
	padding: 20px 0px;
	border-top: 1px solid #ccc;
}

.link-panel a {
	float: left;
	color: #000;
	margin: 0 10px;
	padding: 17px 5px;
	width: 100px;
	border: 1px solid #ccc;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
}

.link-panel a:hover {
	background: #f8f8f8;
	color: #333;
}

.link-panel a i {
	font-size: 40px;
	padding-bottom: 20px;
}

.link-panel a span {
	padding-top: 10px;
}
</style>
</head>
<body>

	<div class="content">
		<h3>欢迎登陆心测开发平台</h3>
		<div class="link-panel ">
			<a href="${ctx}/pic/xcPicture/list?picType=1"> <i class="icon-picture"></i><br />
				<span>轮播图列表</span>
			<a href="${ctx}/pic/xcPicture/list?picType=2"> <i class="icon-picture"></i><br />
				<span>二维码图列表</span>
			</a> <a href="${ctx}/test/xcTestInfo/list"> <i
				class=" icon-list"></i><br /> <span>测试数据列表</span>
			</a>
		</div>
	</div>
</body>
</html>