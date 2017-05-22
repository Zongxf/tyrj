<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/code.tld"%>  

<script type="text/javascript">
	function attadd() {
		$("#atttab")
				.append(
						'<tr><td><input id="attchk" name="attchk" type="checkbox" value="1" /></td><td><input type="file" name="myFile"/></td></tr>');
	}
	function delatt(id) {//删除事件   
		alert(11);
		$(this).parent("td").parent("tr").remove();
		/* var ids = [];
		$("[name='attchk'][checked]").each(function() {
			ids.push($(this).val());
			$(this).parent("td").parent("tr").remove();
		});
		$.ajax({
			url : '${ctx }/user/delAtt.do',
			type : "post",
			data : "ids=" + ids.join(','),
			cache : false,
			success : function(msg) {
				//var jsonobj = eval('(' + msg + ')');
			}
		}); */
	}
	
	function download(filepath,filename) {
		 filename=encodeURI(encodeURI(filename));
		window.location.href="${ctx}/file/download.do?filepath="+filepath+"&filename="+filename;
	}
</script>
<table id="atttab" border="0" cellpadding="0" cellspacing="0" width="100%">

	<c:if test="${act!='view'}">
		<tr>
			<td colspan="2" align="left">
			<input type="button" id="att_add" value="添加" onclick="attadd()" class="buttonbg01" /> 
			<input type="button" id="att_del" value="删除" onclick="delatt()" class="buttonbg01" />
			</td>
		</tr>
	</c:if>
	<c:forEach items="${atts}" var="items">
		<tr>
			<td width="25"><input id="attchk" name="attchk" type="checkbox" value="${items.id }" /></td>
			<td><a href="${ctx}/file/download.do?filepath=${items.file_path}&filename=${code:encode(items.filename)}" target="_blank">${items.filename }</a></td>
		</tr>
	</c:forEach>
	<c:if test="${act=='add'}">
		<tr>
			<td width="25"><input id="attchk" name="attchk" type="checkbox" value="1"  /></td>
			<td><input type="file" name="myFile"/></td>
		</tr>
	</c:if>

</table>

