<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
<%@ include file="/common/header.jsp"%>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<title>${appname }</title>
<style type="text/css">

</style>
</head>
<body>

<div id="uff">
	<form id="ff" method="post" action="${ctx }/user/login.do">

		<table width="30%" border="0" cellpadding="0" cellspacing="0" class="subTab">
			<tr>
				<td class="Lable" width="25%">用户：</td>
				<td><input type="text" name="user_name" id="username"style="width:180px;"class="required"></td>
			</tr>
			<tr>
				<td class="Lable" width="25%">密码：</td>
				<td><input type="password" name="user_passwd" id="password"style="width:180px;"class="required"> </td>
			</tr>
			<tr>
			    <td class="Lable" width="25%">验证：</td>
			    <td>
				<img src="${ctx}/PictureCheckCodeServlet" id="imageCode"/> <input type="text"name="yzm"style="width:50%;" placeholder="不区分大小写" id="yzm"value=""autocomplete="off" />
	            <a href="" onclick="myReload()"> 换一个</a> 
	            </td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" style="width:30%;"class="btn"></td>
			</tr>
		</table>

	</form>
</div>
<script type="text/javascript">
	var dailog = null;
	var dailog1 = null;
	
	
	$(function()
	{
		
		dailog = $.ligerDialog.open({
			target : $("#uff"),
			title : '登陆'
		});
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
							       if(checkYZM()){
								   window.location.href="${ctx}/frame/layout1.jsp?s="+username; 
							       } 

							} else{
								alert("用户名或密码错误！");
							}
						}
				
				});
			}
		});
		
		
		
	});
	//验证码匹配
	function checkYZM(){
		var code = $("#yzm").val();
		var a=false;
		$.ajax({
			url:"${ctx}/user/checkYZM.do",
			data:{"yzm":code},
			type:"post",
			dataType:"json",
			async: false,
			success:function(data){
				if(data){
					$("#yzm").val("验证成功");
					a = true;
				}else{
					alert("验证码不匹配");
				}
				
				
				
			},
			error:function(){
				alert("验证错误");
			}
		});
		return a;
	}
	
	
</script>
<script language="javascript">  
function myReload() {  
    document.getElementById("imageCode").src = "${ctx}/PictureCheckCodeServlet";  
}  


</script> 
</body>
</html>