<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<script type="text/javascript">
	function download(filepath,filename) {
		 filename=encodeURI(encodeURI(filename));
		window.location.href="${ctx}/file/download.do?filepath="+filepath+"&filename="+filename;
	}
</script>

<table id="atttab" border="0" cellpadding="0" cellspacing="0" width="100%">
	<c:forEach items="${atts}" var="items">
		<tr>
			<td width="25"><input id="attchk" name="attchk" type="checkbox" value="${items.id }" /></td>
			<td><a href="#" onclick="download('${items.file_path}','${items.filename }')">${items.filename }</a></td>
		</tr>
	</c:forEach>

</table>

