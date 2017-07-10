<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>小组营业统计</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/LodopFuncs.js"></script>
</head>
<body>
       <div class="container center">
       <div class="searchBox center">
       <div class="form-inline">
                   <label class="control-label" > 起始日期：</label>
                   <input type="text" id="dateb" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   <label class="control-label" > 终止日期：</label> 
                   <input type="text" id="datee" onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                   <label class="control-label" > 营业组：</label>
	               <select class="select" name="yyz"id="yyz">
	                  <option  class="select"value="">--请选择--</option>
	                  <option  class="select"value="400003">VIP餐厅</option>
	                  <option  class="select"value="200002">本工食堂</option>
	                  <option  class="select"value="200003">外包工食堂</option>
	                  <option  class="select"value="100001">默认场所</option>
	               </select>
	               &nbsp; &nbsp; &nbsp; &nbsp;
	               <span class="btn-group">
	               <button onclick="search();" class="btn btn-default"><i class="icon-search"></i>查询</button>
	               <button onclick="f_save();" class="btn btn-default"><i class="icon-upload"></i>导出</button>
	               <button onclick="doprint();" class="btn btn-default"><i class="icon-print"></i>打印</button>
	               </span>
               </div>
               </div>
               <div class="contentBox center">
	             <span style="margin-left:10px;">总营业额：</span><input type="text" id="zyye"style="width:80px;height:25px;"disabled="disabled"/>
	             <span style="margin-left:100px;">总营业人次：</span><input type="text" id="zyyrs"style="width:80px;height:25px;"disabled="disabled"/>   
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
                    display: '小组名',
                    name: 'dep_name',
                    align: 'center',
                    width: '25%'
                }, {
                    display: '餐别类型',
                    name: 'lname',
                    align: 'center',
                    width: '25%'
                },{
                    display: '营业总额',
                    name: 'summoney',
                    align: 'center',
                    width: '25%'
                }, {
                    display: '营业人次',
                    name: 'zrs',
                    align: 'center',
                    width: '25%'
                }],
                pageSize: 200,
                pageSizeOptions: [200, 400,800,1000],
                url: '${ctx }/query/queryXzyytjJson.do',
                width: '99%',
                height: '98%',
                onBeforeShowData:function(){
                	$("#zyye").val("");
                	$("#zyyrs").val("");
                },
                onAfterShowData:function(data){
                	//alert(JSON.stringify(data));
                	
                	var d = data.zyye;
                	var s = data.zyyrs;
                		$("#zyye").val(d);
                		$("#zyyrs").val(s);
                	
                }
            });


        });
        function search() {

            manager.setOptions({
                parms: [{
                    name: 'yyz',
                    value: $("#yyz").val()
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
        
        //导出
         
        function f_save() {  
              	var yyz = $("#yyz").val();
              	var dateb = $("#dateb").val();
              	var datee = $("#datee").val();
              	 var dqczy = "<%=session.getAttribute("username")%>";
                 var time=year+"-"+month+"-"+strDate;
                var url = "${ctx}/ReportServer?reportlet=xzyytj.cpt&dateb="+dateb+"&datee="+datee+"&yyz="+yyz+"&p1="+dqczy+"&p2="+time;
               
                	 window.open(url); 
              	
              }
           
           
        
        //打印
        function doprint(){
       	CheckIsInstall();
       		var yyz = $("#yyz").val();
            var dateb = $("#dateb").val();
            var datee = $("#datee").val();
     
       	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}body,table{width:90%;margin-left:40px;background-color:#ffffff;}.t{font-size:20px;font-weight:blod;height:60px;}</style>"
       	           +"<table><thead><tr><td width='100%' colspan='4'class='t'>小组营业统计表"
       	           +"<span style='float:left;font-size:15px;margin-left:10px;'>总营业额："+$("#zyye").val()+"&nbsp;&nbsp;总营业人数："+$("#zyyrs").val()+"</span>"
       	           +"<span style='float:right;font-size:15px;margin-right:10px;'>时间："+year+"年"+month+"月"+strDate+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</span></td></tr><tr>"
       	           +"<td width='25%'class='t'>小组名</td>"
       	           +"<td width='25%'class='t'>餐别类型</td>"
       	           +"<td width='25%'class='t'>营业总额</td>"
       	           +"<td width='25%'class='t'>营业人次</td></tr></thead><tbody>";
       	        $.ajax({  
                    url: '${ctx}/query_print/queryXzyytjJson.do',  
                    data: {  
                    	"yyz":yyz,"dateb":dateb,"datee":datee
                    },
                    type:"post",
                    dataType:"json",
                    success: function (data) {  
                      	var LODOP = getLodop(); 
                    	 for(var i = 0;i<data.length;i++){
                    		 var t = data[i].dep_name;
                    		 var b = data[i].lname;
                    		 var x = data[i].summoney;
                    		 var xx = data[i].zrs;
                    		  str =str +"<tr><td width='25%'>" +t
                              + "</td><td width='25%'>"+b 
                              + "</td><td width='25%'>"+x 
                              + "</td><td width='25%'>"+xx 
                              +"</td></tr>";  
                         }
                    	 str=str+"</tbody><tfoot><tr><td width='100%' colspan='4' tindex='1'>"
                       	 +" 当前是第<font tdata='PageNO' format='0' color='black'>##</font>页</span>/共<font tdata='PageCount' format='0' color='black'>##</font></span>页，"
                       	 +"</td></tr></tfoot></table>";
                       	LODOP.PRINT_INIT("小组营业统计打印表格");
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