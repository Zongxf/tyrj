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
					url : "${ctx }/user/restPassword.do",
					dataType :'json',
					success : function(data) {
						if (data.success) {
							alert("操作成功");
						} else {
							alert("操作出错");
						}
						doHide();
					}
				});
			}
		})

	});

	function doHide() {
		parent.dailog1.hide();
	}
</script>
</head>
<body>
	<form id="ff" method="post">
		<table width="95%" border="0" cellpadding="0" cellspacing="0" class="subTab">
			<tr>
				<td class="Lable" width="25%">用户名：</td>
				<td><input type="text" name="username"  class="required"  ></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" class="buttonbg01"></td>
			</tr>
		</table>
		<input type="hidden" name="act" value="edit"  >
		<input type="hidden" name="user.user_id" value="${userInfo.user_id}"  >
		<input type="hidden" name="user.dep_id" value="${userInfo.dep_id}"  >
	</form>

</body>
</html>