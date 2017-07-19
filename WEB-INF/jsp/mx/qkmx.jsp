<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>取款明细</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/LodopFuncs.js"></script>
</head>
<body>
<span>
<div class="container center">
		  <div class="searchBox center">
		      <div class="form-inline">
					<label class="control-label" > 起始日期：</label>
			        <input type="text" id="dateb" onclick="WdatePicker({startDate: '%y-%M-01 00:00:00' ,maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>至
			        <label class="control-label" > 终止日期：</label> 
			        <input type="text" id="datee" onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59' ,maxDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
			        <label class="control-label" > 人员编号：</label>
			        <input type="text" id="bh"/>
			    </div>
			    <div class="form-inline" style="margin-top:10px;">
			        <label class="control-label" > 人员姓名：</label>
			        <input type="text" id="xm"/></span> &nbsp; &nbsp; &nbsp; &nbsp;
		               <span class="btn-group">
		               <button onclick="search();" class="btn btn-default"><i class="icon-search"></i>查询</button>
		               <button onclick="exportXml();"class="btn btn-default"><i class="icon-upload"></i>导出</button>
		               <button onclick="doprint();"class="btn btn-default"><i class="icon-print"></i>打印</button>
		               </span>
		        </div>    
		   </div>      
           <div class="contentBox center">
	           <span style="margin-left:10px;">取款总额：</span><input type="text"id="zqkje" style="height:25px;"disabled="disabled"/>
	           <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
           </div>
</div>          
<script type="text/javascript">
        var manager = null;
        var dailog = null;
    	var date = new Date();
       	var year = date.getFullYear();
       	var month = date.getMonth() + 1;
       	var strDate = date.getDate();
        $(function () {
        
	       	var startdate=year+"-"+month+"-"+strDate+" 00:00:00";
	       	var enddate=year+"-"+month+"-"+strDate+" 23:59:59";
	       	$("#dateb").val(startdate);    //默认起始时间为系统当天
	       	$("#datee").val(enddate);  
        	
            manager = $("#tt").ligerGrid({
                checkbox: true,
                columns: [{
                    display: '人员编号',
                    name: 'bh',
                    align: 'center',
                    width: '12.5%'
                   
                }, {
                    display: '姓名',
                    name: 'xm',
                    align: 'center',
                    width: '13.5%'
                }, {
                    display: '部门',
                    name: 'bm',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '取款金额',
                    name: 'qkje',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '取现金',
                    name: 'qxj',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '取补贴',
                    name: 'qbt',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '剩余金额',
                    name: 'syje',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '取款日期',
                    name: 'qksj',
                    align: 'center',
                    width: '13.5%'
                }],
                pageSize: 200,
                pageSizeOptions: [100, 200,400,800,1000,2000],
                url: '${ctx }/query/queryQkmxJson.do',
                width: '99%',
                height: '98%',
                onBeforeShowData:function(){
                	$("#zqkje").val("");
                },
                onAfterShowData:function(data){
                	//alert(JSON.stringify(data));
                	var d = data.ZQK;
                	if(d=="null"){
                		d=0;
                	}
                	$("#zqkje").val(d);
                }
            });


        });
        function search() {
            manager.setOptions({
                parms: [{
                    name: 'bh',
                    value: $("#bh").val()
                },{
                    name: 'xm',
                    value: $("#xm").val()
                },{
                    name: 'dateb',
                    value: $("#dateb").val()
                },{
                    name: 'datee',
                    value: $("#datee").val()
                }
                ]
            });
            manager.changePage("first");
            //按查询条件导入到grid中
            manager.loadData(true);
        }
        
      //导出excel
          function exportXml() {
        	
        	var bh = $("#bh").val();
        	var xm = $("#xm").val();
        	var dateb = $("#dateb").val();
        	var datee = $("#datee").val();
        	 var dqczy = "<%=session.getAttribute("username")%>";
             var time=year+"-"+month+"-"+strDate;
            var url = cjkEncode("${ctx}/ReportServer?reportlet=qkmx.cpt&dateb="+dateb+"&datee="+datee+"&p1="+dqczy+"&p2="+time+"&bh="+bh+"&xm="+xm);
           
            	 window.open(url); 
        }
      
        
        //打印
         function doprint(){
        	CheckIsInstall();
        	var bh = $("#bh").val();
        	var xm = $("#xm").val();
        	var dateb = $("#dateb").val();
        	var datee = $("#datee").val();
        	
        	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}body,table{width:90%;margin-left:40px;background-color:#ffffff;}.t{font-size:20px;font-weight:blod;}</style>"
        	           +"<table><thead><tr><td width='100%' colspan='8'class='t'>取款明细表"
        	           +"<span style='float:left;font-size:15px;margin-left:10px;'>总取款金额："+$("#zqkje").val()+"</span>"
        	           +"<span style='float:right;font-size:15px;margin-right:10px;'>时间："+year+"年"+month+"月"+strDate+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</span></td></tr><tr>"
        	           +"<td width='12.5%'class='t'>人员编号</td>"
        	           +"<td width='12.5%'class='t'>姓名</td>"
        	           +"<td width='12.5%'class='t'>部门</td>"
        	           +"<td width='12.5%'class='t'>取款金额</td>"
        	           +"<td width='12.5%'class='t'>取现金</td>"
        	           +"<td width='12.5%'class='t'>取补贴</td>"
        	           +"<td width='12.5%'class='t'>剩余金额</td>"
        	           +"<td width='12.5%'class='t'>取款时间</td></tr></thead><tbody>";
        	           $.ajax({  
        	                url: '${ctx}/query_print/queryQkmxJson.do',  
        	                data: {  
        	                	"bh":bh,"xm":xm,"dateb":dateb,"datee":datee
        	                },
        	                type:"post",
        	                dataType:"json",
        	                success: function (data) { 
        	                	var LODOP = getLodop(); 
        	                	 for(var i = 0;i<data.length;i++){
        	                		 var t = data[i].bh;
        	                		 var b = data[i].bm;
        	                		 var x = data[i].xm;
        	                		 var c = data[i].qkje;
        	                		 var s = data[i].syje;
        	                		 var ck = data[i].qksj;
        	                		 var qxj = data[i].qxj;
        	                		 var qbt = data[i].qbt;
        	                		 str =str +"<tr><td width='12.5%'>" +t 
                                     + "</td><td width='12.5%'>"+x 
                                     + "</td><td width='12.5%'>"+b 
                                     + "</td><td width='12.5%'>"+c 
                                     + "</td><td width='12.5%'>"+qxj
                                     + "</td><td width='12.5%'>"+qbt
                                     + "</td><td width='12.5%'>"+s 
                                     + "</td><td width='12.5%'>"+ck
                                     +"</td></tr>";  
        	                    	 }
        	                	
        	                	 str=str+"</tbody><tfoot><tr><td width='100%' colspan='8' tindex='1'>"
        	                	 +" 当前是第<font tdata='PageNO' format='0' color='black'>##</font>页</span>/共<font tdata='PageCount' format='0' color='black'>##</font></span>页，"
        	                	 +"</td></tr></tfoot></table>";
        	                	LODOP.PRINT_INIT("取款明细打印表格");
        	                	LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ;
        	                	LODOP.ADD_PRINT_HTM(25,0,"100%","100%",str);
        	                	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
        	                	LODOP.NewPageA();
        	                		LODOP.PREVIEW();
        	                },  
        	                error: function (message) {  
        	                	alert(message);  
        	                }  
        	            });  
         	    
         	 
                 
         }  
</script>         
       
</body>
</html>