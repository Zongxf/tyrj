<%@page import="org.nutz.lang.Lang"%>
<%@page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<html>
<head>
<title>Bootstrap 实例 - 标签页（Tab）插件</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<link href="/tyrj/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="/tyrj/jquery/jquery.min.js"></script>
<script src="tyrj/bootstrap/bootstrap.min.js"></script>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<ul id="myTab" class="nav nav-tabs">
<li class="active">
<a href="/demo/bootstrap3-plugin-tab.htm#home" data-toggle="tab">
自强学堂
</a>
</li>
<li><a href="/demo/bootstrap3-plugin-tab.htm#ios" data-toggle="tab">iOS</a></li>
<li class="dropdown">
<a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown">Java
<b class="caret"></b>
</a>
<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
<li><a href="/demo/bootstrap3-plugin-tab.htm#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
<li><a href="/demo/bootstrap3-plugin-tab.htm#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
</ul>
</li>
</ul>
<div id="myTabContent" class="tab-content">
<div class="tab-pane fade in active" id="home">
<p>自强学堂是一个提供最新的web技术的站点，我们将将国外的精华教程收集到国内, 让每个人享受平等学习的机会!我们的目标是只要你坚持来自强学堂,我们提供的教程就能让你变得更强!</p>
</div>
<div class="tab-pane fade" id="ios">
<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
</div>
<div class="tab-pane fade" id="jmeter">
<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
</div>
<div class="tab-pane fade" id="ejb">
<p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
</p>
</div>
</div>
</body>
</html>