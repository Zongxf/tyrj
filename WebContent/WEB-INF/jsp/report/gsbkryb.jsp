<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>挂失办卡人员表</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/LodopFuncs.js"></script>
</head>
<body>
       <span>
                   起始日期： <input type="text" id="dateb" onclick="WdatePicker({startDate: '%y-%M-01 00:00:00' ,maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   终止日期： <input type="text" id="datee" onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59' ,maxDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                  人员编号：<input type="text" id="bh" style="width:100px;"/>
                   姓名：<input type="text" id="xm" style="width:100px;"/>
       </span>
           <span class="btr">
               <button onclick="search();">查询</button>
               <button onclick="exportXml();">导出</button>
               <button onclick="doprint();">打印</button>
           </span>
           <span style="margin-left:40px;">挂失办卡人数：</span><input type="text" id="zgsrs"style="width:80px;height:25px;"disabled="disabled"/>
          
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
                    display: '人员编号',
                    name: 'rybh',
                    align: 'center',
                    width: '16.6%'
                }, {
                    display: '姓名',
                    name: 'xm',
                    align: 'center',
                    width: '17.8%'
                }, {
                    display: '部门',
                    name: 'bm',
                    align: 'center',
                    width: '17.8%'
                }, {
                    display: '办卡日期',
                    name: 'bksj',
                    align: 'center',
                    width: '16.8%'
                }, {
                    display: '剩余金额',
                    name: 'syje',
                    align: 'center',
                    width: '16.8%'
                }, {
                    display: '卡号',
                    name: 'kh',
                    align: 'center',
                    width: '16.8%'
                   
                }],
                pageSize: 200,
                pageParmName :'page',
                pageSizeOptions: [100, 200,400,800,1000,2000],
                url: '${ctx }/report/gsrybbReportJson.do',
                width: '99%',
                height: '98%',
                onBeforeShowData:function(){
                	$("#zgsrs").val("");
                },
                onAfterShowData:function(data){
                	//alert(JSON.stringify(data));
                	var d = data.Total;
                	$("#zgsrs").val(d);
                }
            });
            
        	 
        });
       
        function search() {
        	
            manager.setOptions({
                parms: [{
                    name: 'rybh',
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
        
        function exportXml() {
        	var data="";
        	var title="";
        	var count="";
            for (var i = 0, l = manager.rows.length; i < l; i++) {
            	    var rybh=manager.rows[i].rybh;
            	    var xm=manager.rows[i].xm;
            	    var bm=manager.rows[i].bm;
            	    var bksj=manager.rows[i].bksj;
            	    var syje=manager.rows[i].syje;
            	    var kh=manager.rows[i].kh;
            	    
                    data =data + rybh + "&"+xm+ "&"+bm+ "&"+bksj+ "&"+syje+ "&"+kh+ "&";  
            }  
            title = "挂失办卡人员报表&人员编号&姓名&部门&办卡日期&剩余金额&卡号&";
            var zgsrs = $("#zgsrs").val();
    	    if(zgsrs==""){
    	    	zgsrs="0";
    	    }
            count = "挂失总人数：&"+zgsrs+"&";
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
       	//alert(content);
       	var LODOP = getLodop(); 
       	var n = manager.rows.length;
       	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}table{width:100%;}.t{font-size:20px;font-weight:blod;}</style>"
       	           +""
       	           +"<table><thead><tr><td width='100%' colspan='6'class='t'>挂失办卡人员报表<span style='float:right;font-size:15px;margin-right:10px;'>时间："+year+"年"+month+"月"+strDate+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</span></td></tr><tr>"
       	           +"<td width='14.2%'class='t'>人员编号</td>"
       	           +"<td width='14.2%'class='t'>姓名</td>"
       	           +"<td width='14.2%'class='t'>部门</td>"
       	           +"<td width='14.2%'class='t'>办卡时间</td>"
       	           +"<td width='15.2%'class='t'>剩余金额</td>"
       	           +"<td width='14.2%'class='t'>卡号</td></tr></thead><tbody>";
       	
       	  for (var i = 0, l = n; i < l; i++) {
       		  //alert("123");
        	        var rybh=manager.rows[i].rybh;
            	    var xm=manager.rows[i].xm;
            	    var bm=manager.rows[i].bm;
            	    var bksj=manager.rows[i].bksj;
            	    var syje=manager.rows[i].syje;
            	    var kh=manager.rows[i].kh;
        	    
        	    
                str =str +"<tr><td width='14.2%'>" +rybh 
                         + "</td><td width='14.2%'>"+xm 
                         + "</td><td width='14.2%'>"+bm 
                         + "</td><td width='14.2%'>"+bksj
                         + "</td><td width='15.2%'>"+syje 
                         + "</td><td width='14.2%'>"+kh 
                         +"</td></tr></tbody>";  
        }  
       	 str=str+"<tfoot><tr><td width='100%' colspan='6' tindex='1'>"
       	 +" 当前是第<font tdata='PageNO' format='ChineseNum' color='blue'>##</font>页</span>/共<font tdata='PageCount' format='ChineseNum' color='blue'>##</font></span>页，"
       	 +"</td></tr></tfoot></table>";
       	LODOP.PRINT_INIT("挂失办卡人员报表打印表格");
       	LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ;
       	LODOP.ADD_PRINT_HTM(10,0,"100%","100%",str);
       	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
       	LODOP.NewPageA();
       		LODOP.PREVIEW();		       
       	
       	
       }
</script>          
</body>
</html>