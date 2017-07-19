<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$("#ff").validate({
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit({
					url : "${ctx }/user/savePasswd.do",
					dataType :'json',
					success : function(data) {
						if (data.success) {
							alert("操作成功");
							//window.location.href="${ctx }/user/logout.do";
						} else {
							alert("操作出错");
						}
						parent.dorefresh();
						doHide();
					}
				});
			}
		})

	});

	function doHide() {
		parent.close();
	}
</script>
</head>
<body>
	<form id="ff" method="post">
		<table width="95%" border="0" cellpadding="0" cellspacing="0" class="subTab">
			<tr>
				<td class="Lable" width="25%">密码：</td>
				<td><input type="password" name="user.Gly_pass" id="user_passwd" class="required"  ></td>
			</tr>
			<tr>
				<td class="Lable" width="25%">确认密码：</td>
				<td><input type="password" name="user.Gly_pass1" id="user_passwd1"  class="{required:true,equalTo:'#user_passwd'}" ></td>
			    <input type="hidden" name="act" value="edit"  >
		        <input type="hidden" name="user.Gly_no" value="super"  >
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" class="buttonbg01"> 
				<input type="button" value="取消" class="buttonbg01" onclick="doHide()"></td>
			</tr>
		</table>
		
	</form>

</body>
</html>