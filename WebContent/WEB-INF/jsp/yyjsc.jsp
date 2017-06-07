<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>营业结算</title>

<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script src="${ctx }/echarts/js/echarts.js"></script>
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
.w{
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
.bt-group{
   border:1px solid #ccc;
   height:25px;
   margin:0px;
   padding:0px;
}
.bt-group .bt-l{
   margin:0px;
   padding:0px;
   width:55px;
   height:25px;
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
               </span>
               
           </form>
           <div class="bt-group"style="float:right;margin-right:40%;margin-top:-24px;">
               <button class="bt-l" onclick="show('1');">表格</button>
               <button class="bt-l" onclick="show('2');">图表</button> 
           </div>
           <div>
         <table id="sss" style="display:block;width:100%;">
             <tr>
              <td class="c w">栏目</td>
              <td class="c w">开户</td>
              <td class="c w">撤户</td>
              <td class="c w">挂失办卡</td>
              <td class="c w">现金存款</td>
              <td class="c w">圈存金额</td> 
              <td class="c w">补助存款</td>
              <td class="c w">取款</td>
              <td class="c w">营业</td>
              <td class="c w">总计</td>
             </tr>
            
             <tr>
               <td>账目变化</td> 
               <td class="bh1 w">0.0</td> 
               <td class="bh2 w">${chrsbh}</td> 
               <td class="bh3 w">${gsrsbh}</td> 
               <td class="bh4 w">${xjbh}</td> 
               <td class="bh5 w">${qcbh}</td> 
               <td class="bh6 w">${btbh}</td> 
               <td class="bh7 w">${qkbh}</td> 
               <td class="bh8 w">0.0</td> 
               <td class="bh9 w">${zbh}</td> 
             </tr>
             <tr>
               <td>人数</td> 
               <td class="rs1 w">${khrs}</td> 
               <td class="rs2 w">${chrs}</td> 
               <td class="rs3 w">${gsrs}</td>
               <td class="rs4 w">${xjckrs}</td> 
               <td class="rs5 w">${qcrs}</td> 
               <td class="rs6 w">${btckrs}</td> 
               <td class="rs7 w">${qkrs}</td> 
               <td class="rs8 w">0</td> 
               <td class="rs9 w">${zrs}</td>  
             </tr>
          </table>  
          </div>
          <div id="main" style="height:500px;display:block;width:100%;"></div> 
<script type="text/javascript">

        //创建ECharts图表方法  
       
       function drawEchart(){  
    	   var zmbh = [$(".bh1").text(),$(".bh2").text(),$(".bh3").text(),$(".bh4").text(),$(".bh5").text(),$(".bh6").text(),$(".bh7").text(),$(".bh8").text(),$(".bh9").text()];
           var rs = [$(".rs1").text(),$(".rs2").text(),$(".rs3").text(),$(".rs4").text(),$(".rs5").text(),$(".rs6").text(),$(".rs7").text(),$(".rs8").text(),$(".rs9").text()];
        	      require.config({
        		    paths: {
        		       echarts: 'http://echarts.baidu.com/build/dist'
        		    }
        		  });
        		  
        		  require([
        		      'echarts',
        		      'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        		    ], 
        		    function(ec){
        		      var myChart = ec.init(document.getElementById('main')); 
        		      
        		      var option = {
        		    	title : {
        		    	text: '报表统计数据'
        		    	},
        		    	tooltip: {
                            trigger: 'axis',

                            textStyle: { fontSize: '30px' }
                        },
        		        legend: {
        		          data:['账目变化','人数']
        		        },
                        dataZoom: {
                            backgroundColor: '#eee',
                            dataBackgroundColor: '#ccc',
                            fillerColor: 'rgba(50,205,50,0.4)',
                            handleColor: 'rgba(70,130,180,0.8)',
                            show: true
                        },
        		        calculable : true,
        		        itemStyle : { normal: {label : {show: true, position: 'top'}}},
        		        xAxis : [
        		          {
        		            type : 'category',
        		            data : ["开户","撤户","挂失办卡","现金存款","圈存","补助存款",'取款','营业额']
        		          }
        		        ],
        		        yAxis : [
        		          {
        		            type : 'value'
        		          }
        		        ],
        		        series : [
        		          {
        		            "name":"账目变化",
        		            "type":"bar",
        		            "data":zmbh
        		          },
        		          {
          		            "name":"人数",
          		            "type":"bar",
          		            "data":rs
          		          }
        		        ]
        		      };
        		      
        		      myChart.setOption(option); 
        		    }
        		  );
        		
        }
        
        //搜索
       function search(){
    	   $("#ff").submit();
       }
        //点击切换事件
        function show(e){
        	    if(e=='1'){
        	    	document.getElementById("sss").style.display="block";
            		document.getElementById("main").style.display="none";
        	    }else if(e=='2'){
        	    	if(document.getElementById("main").innerHTML==''){
                		drawEchart();
                	};
        	    	document.getElementById("sss").style.display="none";
            		document.getElementById("main").style.display="block";
        	    }
        }
        
     
</script>       
    
</body>
</html>