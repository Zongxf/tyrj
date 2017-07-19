<%@page import="org.nutz.lang.Lang"%>
<%@page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%
  String s = request.getParameter("s");
  session.setAttribute("username", s);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appname }</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>


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
			height : '98%',
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
				{id: '4.1', pid: '4',isexpand:false,url: '${ctx }/user/changePasswd.do', text: '修改密码'}
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
		
		/* //alert(ss);
		if(ss=="super"){
			document.getElementById("role").innerHTML="超级管理员";
		}else{ */
			document.getElementById("role").innerHTML=ss;
		//}
		showAll();
		
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
	
	
   window.setInterval(showAll, 1000); 
	function showAll(){
		var myDate = new Date();
		var m = myDate.getMonth()+1;//月
		var d = myDate.getDate();//日
		var h = myDate.getHours();//时
		var mi = myDate.getMinutes()//分
		var s = myDate.getSeconds();//秒
		if(m<10){
			m = "0"+m;
		}
		if(d<10){
			d = "0"+d;
		}
		if(h<10){
			h = "0"+h;
		}
		if(mi<10){
			mi = "0"+mi;
		}
		if(s<10){
			s = "0"+s;
		}
		var hehe= myDate.getFullYear()+"-"+m+"-"+d+"  "+h+":"+mi+":"+s;
		document.getElementById("datetime").innerHTML="时间："+hehe;
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
	padding-left: 0px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 0px;
	margin-right: 0px;
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
	padding-left: 0px;
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
	height: 70px;
	line-height: 50px;
	background: url('${ctx }/img/top.jpg') repeat-x bottom;
	position: relative;
	
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
<style type="text/css">
    /* 导航条设置 */
    .nav{
       margin:0px;
       padding:0px;
       width:100%;
       height:30px;
    }
   
    /* .nav li a:hover{
     background:white;
     color:black;
     text-decoration:none;
     text-align:center;
    } */
    .nav-tabs li a{
    padding-bottom:0px;
    }
    .dropdown-menu{
    margin-top:0px;
    }

</style>
</head>
<body style="padding: 0px; background: #EAEEF5;">
	<ul class="nav nav-tabs">
	   <li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				圈存报表
			</a>
			<ul class="dropdown-menu" >
				<li><a href="javascript:f_addTab('qcdzmx','圈存对账明细','${ctx}/queryha/queryQcdzmx.do');">圈存对账明细</a></li>
				<li><a href="javascript:f_addTab('skgrqchz','水控个人圈存汇总','${ctx}/queryha/queryGrqchz.do');">水控个人圈存汇总</a></li>
				<li><a href="javascript:f_addTab('qcdzb','圈存对账表','${ctx}/queryha/queryQcdzb.do');">圈存对账表</a></li>
			</ul>
		</li>
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				营业报表 
			</a>
			<ul class="dropdown-menu"  >
				<li><a href="javascript:f_addTab('yymx','营业明细表','${ctx}/queryha/queryYymxb.do');">营业明细表</a></li>
				<li><a href="javascript:f_addTab('yyhz','营业汇总表','${ctx}/queryha/queryYyhzb.do');">营业汇总表</a></li>
				<li><a href="javascript:f_addTab('glyrz','管理员日志','${ctx}/queryha/queryGlyrz.do');">管理员日志</a></li>
			</ul>
		</li>
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				其他报表 
			</a>
			<ul class="dropdown-menu" >
				<li><a href="javascript:f_addTab('tkb','退款表','${ctx}/queryha/queryTkb.do');">退款表</a></li>
				<li><a href="javascript:f_addTab('cnbb','出纳报表','${ctx}/queryha/queryCnbb.do');">出纳报表</a></li>
				<li><a href="javascript:f_addTab('qcdzb','就餐情况统计表','${ctx}/queryha/queryJcqktjb.do');">就餐情况统计表</a></li>
				<li><a href="javascript:f_addTab('jcxqb','就餐详情表','${ctx}/queryha/queryJcxqb.do');">就餐详情表</a></li>
				<li><a href="javascript:f_addTab('ckcbrb','窗口餐别日报','${ctx}/queryha/queryCkcbrb.do');">窗口餐别日报</a></li>
				<li><a href="javascript:f_addTab('chb','撤户表','${ctx}/queryha/queryChb.do');">撤户表</a></li>
				<li><a href="javascript:f_addTab('gskyecx','挂失卡余额查询','${ctx}/queryha/queryGskyecx.do');">挂失卡余额查询</a></li>
			</ul>
		</li>
		
	</ul>
	<div id="pageloading"></div>
	
	<div id="topmenu" class="l-topmenu">
		<div class="l-topmenu-logo">腾源一卡通报表</div>
		
		<div class="l-topmenu-welcome">
			<span class="space">欢迎，<span id="role"></span>&nbsp;&nbsp;|
			</span> &nbsp;&nbsp;&nbsp;&nbsp; <span class="space"> <a href="${ctx }/user/logout.do"> 注销 </a>
			</span><br/>
			<span id="datetime" class="space"></span>
			
		</div>
	</div>
	<div id="layout1" style="width: 99.5%; margin: 0 auto; ">
		<div position="left" title="主要菜单" id="accordion1" style="height: 97%; overflow-y: auto;">
            <ul id="tree1" style="margin-top:3px;"></ul>
		</div>
		<div position="center" id="framecenter">
			<div tabid="home" title="我的主页" style="height:97%;">
				<iframe frameborder="0" name="home" id="home" src="${ctx }/frame/welcome.jsp"></iframe>
			</div>
		</div>

	</div>
	<div style="height: 20px; line-height: 15px; text-align: center;margin-top:5px;">南通腾源智能科技有限公司@1.1</div>
</body>
</html>
