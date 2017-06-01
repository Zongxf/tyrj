<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>营业结算</title>
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
             <form action="${ctx}/query/query.do" method="get" id="ff">
		      <span>
		                   起始日期： <input type="text" id="dateb"name="dateb" value="${dateb }"onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
		                   终止日期： <input type="text" id="datee"name="datee" value="${datee }"onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		       </span>   
               <span class="btr">
               <button onclick="search();">查询</button>
              <!--  <button onclick="exportXml();">导出</button>
               <button>打印</button> -->
               </span>
           </form>
         <table>
             <tr>
              <td class="c">栏目</td>
              <td class="c">开户</td>
              <td class="c">撤户</td>
              <td class="c">挂失办卡</td>
              <td class="c">现金存款</td>
              <td class="c">圈存金额</td> 
              <td class="c">补助存款</td>
              <td class="c">取款</td>
              <td class="c">营业</td>
              <td class="c">总计</td>
             </tr>
            
             <tr>
               <td>账目变化</td> 
               <td>0.00</td> 
               <td>${chrsbh}</td> 
               <td>${gsrsbh}</td> 
               <td>${xjbh}</td> 
               <td>${qcbh}</td> 
               <td>${btbh}</td> 
               <td>${qkbh}</td> 
               <td>0.00</td> 
               <td>${zbh}</td> 
             </tr>
             <tr>
               <td>人数</td> 
               <td>${khrs}</td> 
               <td>${chrs}</td> 
               <td>${gsrs}</td>
               <td>${xjckrs}</td> 
               <td>${qcrs}</td> 
               <td>${btckrs}</td> 
               <td>${qkrs}</td> 
               <td>0.00</td> 
               <td>${zrs}</td>  
             </tr>
          </table> 
          <script type="text/javascript">
        var manager = null;
        var dailog = null;
        
       
        
        function search(){
        	$("#ff").submit();
        }
        
       /*  //导出excel
        function exportXml() {
        	var data="";
        	var title="";
        	var count="";
            for (var i = 0, l = manager.rows.length; i < l; i++) {
            	    var rybh=manager.rows[i].rybh;
            	    var xm=manager.rows[i].xm;
            	    var bm=manager.rows[i].bm;
            	    var xfje=manager.rows[i].xfje;
            	    var syje=manager.rows[i].syje;
            	    var xfsj=manager.rows[i].xfsj;
            	    var xfjh=manager.rows[i].xfjh;
            	    
                    data =data + rybh + "&"+xm+ "&"+bm+ "&"+xfje+ "&"+syje+ "&"+xfsj+ "&"+xfjh+"&";  
            }  
            title = "计次消费明细&人员编号&姓名&部门&消费金额&剩余金额&消费时间&消费机号&";
            var zxfje = $("#zxfje").val();
            //alert(zxfje);
    	    if(zxfje==""){
    	    	zxfje="0";
    	    }
            count = "消费总金额：&"+zxfje+"&";
            $.ajax({  
                url: '${ctx}/export/exportCktj.do',  
                data: {  
                	"data":data,"title":title,"count":count
                },
                type:"post",
                dataType:"json",
                success: function (data) {  
                	if(data.success){
                		alert(data.fileName);
                		window.location.href="${ctx}/export/downloadFile.do";
                	}else{
                		alert("导出失败！");
                	}
                },  
                error: function (message) {  
                	alert(message);  
                }  
            });  
        } */
</script>       
    
</body>
</html>