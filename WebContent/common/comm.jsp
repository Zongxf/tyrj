<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/header.jsp"%>
<style>
<!--
@import url("${ctx }/common/style.css");
.buttonbg01 {
	background-image: url(${ctx }/img/btn_bg01.gif);
	height: 20px;
	width: 63px;
	border: 0px;
	background-position: center;
	font-size:12px;
}	 
.buttonbg02 {
	background-image: url(${ctx }/img/btn_bg02.png);
	height: 20px;
	width: 114px;
	border: 0px;
	background-position: center;
	font-size:12px;
}	
button {
	background-image:url(${ctx }/img/btn_bg01.gif) ;
}

.unityTabTitle{ width:100%; height:27px; background:url('${pageContext.request.contextPath}/images/img/add_bg2.gif') repeat-x;}
.unityTabTitle th{width:200px; height:27px; background:url(${pageContext.request.contextPath}/images/img/add_bg1.gif) no-repeat left top; text-align:left; color:#0066cc; text-indent:1em;}
.unityTabTitle td{ height:27px; text-align:right; background:url(${pageContext.request.contextPath}/images/img/add_bg3.gif) no-repeat right top;}
.unityTabTitle td input{ margin-right:5px;}
.unityTabTitle td a{display:block; float:right; width:12px; height:7px; background:url(${pageContext.request.contextPath}/images/img/up_btn01.gif) no-repeat; margin-right:10px; margin-top:9px;}
.unityTabTitle td a.upBtn{  background:url(${pageContext.request.contextPath}/images/img/up_btn02.gif) no-repeat;}

*{font-size:15px;}
			.box th{padding-left:10px;font-size:14px;color:#005FA9;font-weight:normal}
			.box td{line-height:28px; border-left:#FCFAF6 1px solid;border-top:#FCFAF6 1px solid;border-right: #EFEADA 1px solid;border-bottom: #EFEADA 1px solid;}
			.T { padding-left:50px; color:#FFF; font-weight:bold; font-size:14px; line-height:35px; padding-top:6px;}
			
-->

</style>



<script type="text/javascript" src="${ctx }/jquery/jquery-1.7.2.min.js"></script>

<%--  <script type="text/javascript" src="${ctx }/ligerUI/ligerui.all.js"></script>
<link href="${ctx }/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
 --%>

<%-- <script type="text/javascript" src="${ctx }/ligerUI/123/js/ligerui.all.js"></script> --%>

<script src="${ctx }/ligerUI/123/js/core/base.js" type="text/javascript"></script>
<script src="${ctx }/ligerUI/123/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${ctx }/ligerUI/123/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="${ctx }/ligerUI/123/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="${ctx }/ligerUI/123/js/plugins/ligerTree.js" type="text/javascript"></script>
<script src="${ctx }/ligerUI/123/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${ctx }/ligerUI/123/js/plugins/ligerResizable.js" type="text/javascript"></script>
<link href="${ctx }/ligerUI/123/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

   


<link rel="stylesheet" type="text/css" media="screen"	href="${ctx}/ajaxform/css/reset.css" />
<link rel="stylesheet" type="text/css" media="screen"	href="${ctx}/ajaxform/css/core.css" />
<link rel="stylesheet" type="text/css" media="screen"	href="${ctx}/ajaxform/css/cmxform.css" />


<script type="text/javascript" src="${ctx }/jquery/select.js"></script>

<script type="text/javascript" src="${ctx }/ajaxform/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/ajaxform/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx }/ajaxform/messages_cn.js"></script>
<script type="text/javascript" src="${ctx }/ajaxform/jquery.metadata.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>

 <script type="text/javascript" src="${ctx }/thirdmodule/ztree35/jquery.ztree.all-3.5.min.js"></script>
<link href="${ctx }/thirdmodule/ztree35/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
