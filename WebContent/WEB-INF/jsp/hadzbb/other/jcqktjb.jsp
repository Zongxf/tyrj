<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>就餐情况统计表</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/LodopFuncs.js"></script>
<style type="text/css">
table,table tr,table tr td{
	margin:0px;
	padding:0px;
	border:1px solid #E0E0E0;
	border-collapse: collapse;
}
table{
	width:99%;
	margin-top:10px;
}
table tr td,th{
	width:10%;
	height:20px;
	font-size:15px;
	text-align:center;
}
.c{
  line-height:25px;
  height:20px;
  font-size:15px;
  font-weight:blod;
  background:url('${ctx }/img/11.png') repeat-x bottom;
}
.btn{
  width:80px;
  padding:5px;
  border-radius:5px;
  height:30px;
  
}
.contentBox{
height:78%;
}

</style>
</head>
<body>
     <div class="container center">
         <div class="searchBox center">
	             <form action="${ctx}/queryHa/queryJcqktjb.do" method="get" id="ff" class="form-inline">
				    <label class="control-label" > 起始日期：</label>
				    <input type="text" id="dateb"name="dateb" value="${Dateb }"onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"class="input-l">至
				    <label class="control-label" > 终止日期：</label>
				      <input type="text" id="datee"name="datee"  value="${Datee }"onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"class="input-l">
				     &nbsp;&nbsp;&nbsp;&nbsp;
	               <span class="btn-group">
		               <button onclick="search();" class="btn btn-default"><i class="icon-search"></i>查询</button>
		               <button onclick="exportXml();" class="btn btn-default"><i class=" icon-upload"></i>导出</button>
		               <button onclick="doprint();" class="btn btn-default"><i class="icon-print"></i>打印</button>
	               </span>
			     
	           </form>
           </div>
           <div class="contentBox center">
	         <table>
	             <tr>
	              <th class="c">操作项目</th>
	              <th class="c">早餐</th>
	              <th class="c">中餐</th>
	              <th class="c">晚餐</th>
	              <th class="c">早中晚三餐</th>
	             </tr>
	            <c:forEach items="${Jcqktjb }" var="l">
	             <tr>
	               <td>未就餐人数</td> 
	               <td>${l.fzc}</td> 
	               <td>${l.fwc}</td> 
	               <td>${l.fwwc}</td> 
	               <td>${l.fzzw}</td> 
	             </tr>
	              <tr>
	               <td>未就餐比率</td> 
	               <td>${l.fzcl}%</td> 
	               <td>${l.fwcl}%</td> 
	               <td>${l.fwwcl}%</td> 
	               <td>${l.fzzwl}%</td> 
	             </tr>
	              <tr>
	               <td>就餐人数</td> 
	               <td>${l.zc}</td> 
	               <td>${l.wc}</td> 
	               <td>${l.wwc}</td> 
	               <td>${l.zzw}</td> 
	             </tr>
	              <tr>
	               <td>就餐比率</td> 
	               <td>${l.zcl}%</td> 
	               <td>${l.wcl}%</td> 
	               <td>${l.wwcl}%</td> 
	               <td>${l.zzwl}%</td> 
	             </tr>
	              </c:forEach>
	          </table> 
          </div>
          </div>
          <script type="text/javascript">
        var manager = null;
        var dailog = null;
        var date = new Date();
	   	 var year = date.getFullYear();
	   	 var month = date.getMonth() + 1;
	   	 var strDate = date.getDate();
       
        
        function search(){
        	$("#ff").submit();
        }
        
       <%--   //导出excel
        function exportXml() {
          var dateb = $("#dateb").val();
          var datee = $("#datee").val();
          var dqczy = "<%=session.getAttribute("username")%>";
          var time=year+"-"+month+"-"+strDate;
         var url = cjkEncode("${ctx}/ReportServer?reportlet=yyjs.cpt&dateb="+dateb+"&datee="+datee+"&p1="+dqczy+"&p2="+time+"&p3="+yce+"&p4="+xce+"&p5="+phyc);
        
         	 window.open(url); 
        } 
         
         
      //打印
        function doprint(){
       	//CheckIsInstall();
       	
       	var LODOP = getLodop(); 
       	var n = document.getElementsByTagName("td");
       	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}body,table{width:90%;margin-left:40px;background-color:#ffffff;}.t{font-size:20px;font-weight:blod;}</style>"
       	           +"<table><thead><tr><td width='100%' colspan='10'class='t'>营业结算<span style='float:right;font-size:15px;margin-right:10px;'>时间："+year+"年"+month+"月"+strDate+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</span></td></tr><tr>"
       	           +"<td width='10%'class='t'>栏目</td>"
       	           +"<td width='10%'class='t'>开户</td>"
       	           +"<td width='10%'class='t'>撤户</td>"
       	           +"<td width='10%'class='t'>挂失办卡</td>"
       	           +"<td width='10%'class='t'>现金存款</td>"
       	           +"<td width='10%'class='t'>圈存</td>"
       	           +"<td width='10%'class='t'>补贴存款</td>"
       	           +"<td width='10%'class='t'>取款</td>"
       	           +"<td width='10%'class='t'>营业额</td>"
       	           +"</tr></thead><tbody>";
       	        
       	           str =str +"<tr><td width='10%'>" +n[0].innerHTML 
		                + "</td><td width='10%'>"+n[1].innerHTML 
		                + "</td><td width='10%'>"+n[2].innerHTML  
		                + "</td><td width='10%'>"+n[3].innerHTML  
		                + "</td><td width='10%'>"+n[4].innerHTML 
		                + "</td><td width='10%'>"+n[5].innerHTML 
		                + "</td><td width='10%'>"+n[6].innerHTML 
		                + "</td><td width='10%'>"+n[7].innerHTML
		                + "</td><td width='10%'>"+n[8].innerHTML
		             
		                +"</td></tr>";
		       	
                str =str +"<tr><td width='10%'>" +n[9].innerHTML 
                         + "</td><td width='10%'>"+n[10].innerHTML 
                         + "</td><td width='10%'>"+n[11].innerHTML  
                         + "</td><td width='10%'>"+n[12].innerHTML  
                         + "</td><td width='10%'>"+n[13].innerHTML 
                         + "</td><td width='10%'>"+n[14].innerHTML 
                         + "</td><td width='10%'>"+n[15].innerHTML 
                         + "</td><td width='10%'>"+n[16].innerHTML
                         + "</td><td width='10%'>"+n[17].innerHTML
                         +"</td></tr></tbody>";  
       	 str=str+"<tfoot><tr><td width='100%' colspan='10' tindex='1'>"
       	 +" 当前是第<font tdata='PageNO' format='0' color='black'>##</font>页</span>/共<font tdata='PageCount' format='0' color='black'>##</font></span>页，"
       	 +"</td></tr></tfoot></table>";
       	LODOP.PRINT_INIT("营业结算打印表格");
       	LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ;
       	LODOP.ADD_PRINT_HTM(25,0,"100%","100%",str);
       	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
       	LODOP.NewPageA();
       		LODOP.PREVIEW();		       
       	
       	
       } --%>
</script>       
    
</body>
</html>