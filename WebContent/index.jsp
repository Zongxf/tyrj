<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${appname }</title>
<script type="text/javascript">
	var dailog = null;
	var dailog1 = null;

	$(function()
	{
		$("#ff").validate({
			submitHandler : function(form)
			{
				jQuery(form).ajaxSubmit({
					url : "${ctx }/user/login.do",
					dataType : 'json',
					success : function(data)
					{
						if (data.success)
						{      var username=data.user_name;
						       //alert(username);
							   window.location.href="${ctx}/frame/layout.jsp?s="+username; 

						} else
						{
							alert("用户名或密码错误！");
						}
					}

				});
			}
		});
		dailog = $.ligerDialog.open({
			target : $("#uff"),
			title : '登陆'
		});

	});
	
</script>
</head>
<body>



<div id="uff">
	<form id="ff" method="post" action="${ctx }/user/login.do",>

		<table width="30%" border="0" cellpadding="0" cellspacing="0" class="subTab">
			<tr>
				<td class="Lable" width="25%">用户：</td>
				<td><input type="text" name="user_name" class="required"></td>
			</tr>
			<tr>
				<td class="Lable" width="25%">密码：</td>
				<td><input type="password" name="user_passwd" class="required"> </td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" class="buttonbg01"></td>
			</tr>
		</table>

	</form>

</div>

</body>
</html>