<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String flag = (String)session.getAttribute("flag")==null?"":(String)session.getAttribute("flag");  
   
String name = "";  
String password = "";  
try{   
    Cookie[] cookies=request.getCookies();   
    if(cookies!=null){   
    for(int i=0;i<cookies.length;i++){   
        if(cookies[i].getName().equals("cookie_user")){   
        String value =  cookies[i].getValue();  
        if(value!=null&&!"".equals(value)){  
            name=cookies[i].getValue().split("-")[0];   
            if(cookies[i].getValue().split("-")[1]!=null && !cookies[i].getValue().split("-")[1].equals("null")){  
     password=cookies[i].getValue().split("-")[1];   
             }  
                        
            }  
            }   
        request.setAttribute("name",name);   
        request.setAttribute("passward",password);   
    }   
    }   
}catch(Exception e){   
    e.printStackTrace();   
}   
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>腾源测试</title>
<link  rel="stylesheet" type="text/css" href="css/style.css"></link>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>

</head>
<body onload="username();">
    <div class="title f3 ">
          <span>南通腾源智能科技有限公司</span>
          <span style="float:right;margin-right:50px;margin-bottom:40px;">
          <span class="f2">登录人：</span><input value="${name}"type="text"style="width:60px;"disabled="disabled">
          <button onclick="logout();">退出</button>
          </span> 
    </div>
    <div class="left">
        <ul>
          <li><a href="yyjs.jsp">营业结算</a></li>
           <li><a href="cktj.jsp">存款统计</a></li>
           <li><a href="xzyytj.jsp">小组营业统计</a></li>
           <li><a href="ckmx.jsp">存款明细</a></li>
           <li><a href="qkmx.jsp">取款明细</a></li>
           <li><a href="xfmx.jsp">消费明细</a></li>
           <li><a href="khrybb.jsp">开户人员报表</a></li>
           <li><a href="gsbkryb.jsp">挂失办卡人表</a></li>
           <li><a href="chrybb.jsp">撤户人员报表</a></li>
           <li><a href="jcrybb.jsp">纠错人员表</a></li>
        </ul>
      <!--   <ul>
           <li><a href="yyjs.html">营业结算</a></li>
           <li><a href="cktj.html">存款统计</a></li>
           <li><a href="xzyytj.html">小组营业统计</a></li>
           <li><a href="ckmx.html">存款明细</a></li>
           <li><a href="qkmx.html">取款明细</a></li>
           <li><a href="xfmx.html">消费明细</a></li>
           <li><a href="khrybb.html">开户人员报表</a></li>
           <li><a href="gsbkryb.html">挂失办卡人表</a></li>
           <li><a href="chrybb.html">撤户人员报表</a></li>
           <li><a href="jcrybb.html">纠错人员表</a></li>
        </ul> -->
    
    </div>
    <div class="right" >
        报表。。。
    </div>
<script type="text/javascript">
    
    function logout(){
    	
    	window.location.href="http://127.0.0.1:8080/springMvc/login.jsp";
    }
</script>
   
</body>
</html>