<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>腾源测试</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/cookie.js"></script>

</head>
<body>
	<div class="title f3 "><img src="images/logo.png"/>南通腾源智能科技有限公司</div>
	    <form action="login.do" method="post"  id="loginForm">
		<div class="login">
			<p>
				<span>用户名：</span><input type="text" name="username"id="username" /><br/> 
				<span>密&nbsp;码：</span><input type="password" name="password" id="password" /><br />
			</p>
			<p class="loginbt">
				<button type="button"id="login">登陆</button>
				<span>&nbsp;&nbsp;</span>
				<button type="button" onclick="resetNull()">重置</button>
			</p>
		</div>
		</form>
	<script type="text/javascript">
     function resetNull(){
    	$("#username").val("");
    	$("#password").val("");
     }
    
     $("#login").click(function() {
			var username = $("#username").val();
			var password = $("#password").val();
			
				if (username == null || username == undefined
						|| username == '') {
					alert("用户名为空");
				} else if (password == null || password == undefined
						|| password == '') {
					alert("密码为空");
				} else {
					$("#loginForm").submit();
				}
			});
        setCookie("username",userName,24,"/");
		setCookie("password",userPassword,24,"/");
  </script>
</body>
</html>