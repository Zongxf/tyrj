<%@page import="org.nutz.lang.Lang"%>
<%@page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%
  String s = request.getParameter("s");
  session.setAttribute("username", s);
%>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appname }</title>
<script type="text/javascript">


<c:if test="${empty userInfo}">
window.location.href = "${ctx}/";
</c:if>
var tab = null;
	var accordion = null;
	var tree = null;
	var dailog = null;
	$(function() {
		
		   
		//布局
		$("#layout1").ligerLayout({
			leftWidth : 190,
			height : '95%',
			heightDiff : -14,
			space : 4,
			onHeightChanged : f_heightChanged
		});

		var height = $(".l-layout-center").height();

		//Tab
		$("#framecenter").ligerTab({
			height : height
		});
		
		
	                
		var indexdata =
			[
				 <c:forEach items= "${sessionScope.nodelist}"   var= "node">
				 {id: '${node.id}', pid: '${node.pid}',isexpand:false,<c:if test="${not empty node.url}">url:'${ctx }/${node.url}',</c:if> text: '${node.text}'},
				 </c:forEach> 
				{id: '999', pid: '0',isexpand:false,url: '${ctx }/user/changePasswd.do', text: '修改密码'}
			]; 
		


		//树
		$("#tree1").ligerTree({
			data : indexdata,
			checkbox : false,
			 isExpand: false, 
			slide : false,
			nodeWidth : 120,
			idFieldName :'id',
            parentIDFieldName :'pid',
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});

		tab = $("#framecenter").ligerGetTabManager();
		tree = $("#tree1").ligerGetTreeManager();
		$("#pageloading").hide();
		
		var url=window.location.href;
		index=url.indexOf("?")+3;
		ss=url.substr(index);
		
		//alert(ss);
		if(ss=="super"){
			document.getElementById("role").innerHTML="超级管理员";
		}else{
			document.getElementById("role").innerHTML=ss;
		}
		
		
		var xjck = "<%= session.getAttribute("xjbh")%>";
		var bzck = "<%= session.getAttribute("btbh")%>";
		var qcje = "<%= session.getAttribute("qcbh")%>";
		if(xjck!="null"){
			$("#xjck").val(xjck);
		}else{
			$("#xjck").val("0.0");
		}
		if(bzck!="null"){
			$("#bzck").val(bzck);
		}else{
			$("#bzck").val("0.0");
		}
		
		if(qcje!="null"){
			$("#qcje").val(qcje);
		}else{
			$("#qcje").val("0.0");
		}
		
	});
	
	
	
	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
	}
	
	
	window.setInterval(showAll, 60000); 
	function showAll(){
		$.ajax({
			url:"${ctx}/timer/showAll.do",
			type:"post",
			dataType:"json",
			success:function(){
				var xjck = "<%= session.getAttribute("xjbh")%>";
				var bzck = "<%= session.getAttribute("btbh")%>";
				var qcje = "<%= session.getAttribute("qcbh")%>";
				if(xjck!="null"){
					$("#xjck").val(xjck);
				}else{
					$("#xjck").val("0.0");
				}
				if(bzck!="null"){
					$("#bzck").val(bzck);
				}else{
					$("#bzck").val("0.0");
				}
				
				if(qcje!="null"){
					$("#qcje").val(qcje);
				}else{
					$("#qcje").val("0.0");
				}
			},
			error:function(){
				alert("定时器加载失败！");
			}
		});
	
	}
	
	
	
</script>
<style type="text/css">
body, html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background:url('${ctx }/img/loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}
/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 50px;
	line-height: 50px;
	background: url('${ctx }/img/top.jpg') repeat-x bottom;
	position: relative;
	border-top: 1px solid #1D438B;
	
}

.l-topmenu-logo {
	color: #E7E7E7;
	padding-left: 60px;
	line-height: 50px;
	font-size:30px;
	background: url('${ctx }/img/logo.png') no-repeat 20px 10px;
}
.l-topmenu-dsq{
    position: absolute;
	height: 26px;
	line-height: 24px;
	right: 30%;
	top: 10px;
	color: #ffffff;
}
.l-topmenu-dsq input{
   width:80px;
   height:30px;
   line-height: 26px;
   border-radius:10px;
   font-size:20px;
}


.l-topmenu-welcome {
	position: absolute;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 20px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: #E7E7E7;
	text-decoration: none;
}
</style>
</head>
<body style="padding: 0px; background: #EAEEF5;">
	<div id="pageloading"></div>
	<div id="topmenu" class="l-topmenu">
		<div class="l-topmenu-logo">${appname }</div>
		<div class="l-topmenu-dsq">
		     现金存款<input type="text"id="xjck"value="" disabled="disabled"/>
		    圈存金额<input type="text"id="qcje" value=""disabled="disabled"/>
		    补助存款<input type="text"id="bzck"value=""disabled="disabled"/></div>
		<div class="l-topmenu-welcome">
			<span class="space">欢迎，<span id="role"></span>&nbsp;&nbsp;|
			
			<%-- <c:if test="${userInfo.Gly_no eq 'super'}">管理员</c:if> --%>

			
			</span> &nbsp;&nbsp;&nbsp;&nbsp; <span class="space"> <a href="${ctx }/user/logout.do"> 注销 </a>
			</span>
		</div>
	</div>
	<div id="layout1" style="width: 99.2%; margin: 0 auto; margin-top: 4px;">
		<div position="left" title="主要菜单" id="accordion1" style="height: 97%; overflow-y: auto;">

			<ul id="tree1" style="margin-top: 3px;">
		</div>
		<div position="center" id="framecenter">
			<div tabid="home" title="我的主页" style="height: 300px">
				<iframe frameborder="0" name="home" id="home" src="${ctx }/frame/welcome.jsp"></iframe>
			</div>
		</div>

	</div>
	<div style="height: 25px; line-height: 20px; text-align: center;margin-top:5px;">南通腾源智能科技有限公司</div>
</body>
</html>
