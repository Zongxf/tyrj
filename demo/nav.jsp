<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Bootstrap 实例 - 带有下拉菜单的标签</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
	<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
	<link rel="stylesheet" href="/tyrj/bootstrap/bootstrap.min.css">  
	<script src="/tyrj/jquery/jquery.min.js"></script>
	<script src="/tyrj/bootstrap/bootstrap.min.js"></script>
</head>
<body>

<ul class="nav nav-tabs">
	<li class="active"><a href="#">Home</a></li>
	<li><a href="#">SVN</a></li>
	<li><a href="#">iOS</a></li>
	<li><a href="#">VB.Net</a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			Java <span class="caret"></span>
		</a>
		<ul class="dropdown-menu">
			<li><a href="#">Swing</a></li>
			<li><a href="#">jMeter</a></li>
			<li><a href="#">EJB</a></li>
			<li class="divider"></li>
			<li><a href="#">分离的链接</a></li>
		</ul>
	</li>
	<li><a href="#">PHP</a></li>
</ul>

</body>
</html>