<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>营业结算</title>
<link  rel="stylesheet" type="text/css" href="css/style.css"></link>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>

<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
table,table tr,table tr td{
	margin:0px;
	padding:0px;
	border:1px solid #E0E0E0;
	border-collapse: collapse;
}
table{
	width:100%;
	margin-top:20px;
}
table tr td{
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
</style>
</head>
<body>
        <span>
                         起始日期： <input type="text" id="dateb" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                         终止日期： <input type="text" id="datee" onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
        </span>
              <span class="btr"> <button>统计</button>
               <button>打印</button>
              </span>
          
          <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt">
         <table>
             <tr>
              <td class="c">栏目</td>
              <td class="c">开户</td>
              <td class="c">撤户</td>
              <td class="c">挂失办卡</td>
              <td class="c">现金存款</td>
              <td class="c">圈存金额</td> 
              <td class="c">补助</td>
              <td class="c">取款</td>
              <td class="c">营业</td>
              <td class="c">总计</td>
             </tr>
            
             <tr>
               <td>账目变化</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
             </tr>
             <tr>
               <td>人数</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td>
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td> 
               <td>0.00</td>  
             </tr>
          </table> 
          </div>
    
</body>
</html>