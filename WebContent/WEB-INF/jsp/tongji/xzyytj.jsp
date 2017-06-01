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
       <span>
                   起始日期： <input type="text" id="dateb" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   终止日期： <input type="text" id="datee" onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
         </span>
               <span>营业组：</span>
               <select class="select" name="yyz"id="yyz">
                  <option  class="select"value="">--请选择--</option>
                  <option  class="select"value="400003">VIP餐厅</option>
                  <option  class="select"value="200002">本工食堂</option>
                  <option  class="select"value="200003">外包工食堂</option>
                  <option  class="select"value="100001">默认场所</option>
               </select>
               <span class="btr">
               <button onclick="search();">查询</button>
               <button onclick="f_save();">导出</button>
               <button onclick="doprint();">打印</button>
               </span>
	             <span style="margin-left:40px;">总营业额：</span><input type="text" id="zyye"style="width:80px;height:25px;"disabled="disabled"/>
	             <span style="margin-left:100px;">总营业人次：</span><input type="text" id="zyyrs"style="width:80px;height:25px;"disabled="disabled"/>   
               <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
          
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
        	  
            var data = "";  
            var title = "";
            var count = "";
            for (var i = 0, l = manager.rows.length; i < l; i++) {
            	    var dep_name=manager.rows[i].dep_name;
            	    var lname=manager.rows[i].lname;
            	    var summoney=manager.rows[i].summoney;
            	    var zrs=manager.rows[i].zrs;
            	 
                    data =data + dep_name + "&"+lname+"&"+summoney+"&"+zrs+"&";  
            }  
            title = "小组营业统计&小组名&餐别类型&营业总额&营业人次";
            
            var zyye = $("#zyye").val();
            //alert(zckje);
    	    if(zyye==""){
    	    	zyye="0";
    	    }
            count = "总营业金额：&"+zyye+"&";
            $.ajax({  
                url: '${ctx}/export/exportCktj.do',  
                data: {  
                	"data":data,"title":title,"count":count
                },
                type:"post",
                dataType:"json",
                success: function (data) {  
                	if(data.success){
                		//alert(data.fileName);
                		window.location.href="${ctx}/export/downloadFile.do";
                	}else{
                		alert("导出失败！");
                	}
                },  
                error: function (message) {  
                	alert(message);  
                }  
            });  
        }  
        
        //打印
        function doprint(){
       	//CheckIsInstall();
       	var LODOP = getLodop(); 
       	var n = manager.rows.length;
       	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}table{width:100%;}.t{font-size:20px;font-weight:blod;}</style>"
       	           +""
       	           +"<table><thead><tr><td width='100%' colspan='4'class='t'>小组营业统计表<span style='float:right;font-size:15px;margin-right:10px;'>时间："+year+"年"+month+"月"+strDate+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</span></td></tr><tr>"
       	           +"<td width='25%'class='t'>小组名</td>"
       	           +"<td width='25%'class='t'>餐别类型</td>"
       	           +"<td width='25%'class='t'>营业总额</td>"
       	           +"<td width='25%'class='t'>营业人次</td></tr></thead><tbody>";
       	
       	  for (var i = 0, l = n; i < l; i++) {
       		  //alert("123");
        	    var dep_name=manager.rows[i].dep_name;
        	 
        	    var lname=manager.rows[i].lname;
        	    var summoney=manager.rows[i].summoney;
        	    var zrs=manager.rows[i].zrs;
        	    
        	    
        	 
                str =str +"<tr><td width='25%'>" +dep_name
                         + "</td><td width='25%'>"+lname 
                         + "</td><td width='25%'>"+summoney 
                         + "</td><td width='25%'>"+zrs 
                         +"</td></tr></tbody>";  
        }  
       	 str=str+"<tfoot><tr><td width='100%' colspan='4' tindex='1'>"
       	 +" 当前是第<font tdata='PageNO' format='ChineseNum' color='blue'>##</font>页</span>/共<font tdata='PageCount' format='ChineseNum' color='blue'>##</font></span>页，"
       	 +"</td></tr></tfoot></table>";
       	LODOP.PRINT_INIT("小组营业统计打印表格");
       	LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ;
       	LODOP.ADD_PRINT_HTM(10,0,"100%","100%",str);
       	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
       	LODOP.NewPageA();
       		LODOP.PREVIEW();		       
       	
       	
       }
</script>      
</body>
</html>