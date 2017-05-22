<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>消费明细</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
       <span>
                   起始日期： <input type="text" id="dateb" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   终止日期： <input type="text" id="datee" onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                  人员编号：<input type="text" id="bh"style="width:100px;"/>
                   姓名：<input type="text" id="xm"style="width:100px;"/>    </span>   
               <span class="btr">
               <button onclick="search();">查询</button>
               <button onclick="exportXml();">导出</button>
               <button>打印</button>
               </span>
             <span style="margin-left:40px;">消费总额：</span><input type="text" id="zxfje"style="width:80px;height:25px;"disabled="disabled"/>
            <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
         
<script type="text/javascript">
        var manager = null;
        var dailog = null;
        
        $(function () {
             var date = new Date();
        	 var year = date.getFullYear();
        	 var month = date.getMonth() + 1;
        	 var strDate = date.getDate();
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
                    width: '14.2%'
                   
                }, {
                    display: '姓名',
                    name: 'xm',
                    align: 'center',
                    width: '14.4%'
                }, {
                    display: '部门',
                    name: 'bm',
                    align: 'center',
                    width: '14.4%'
                }, {
                    display: '次数',
                    name: 'cs',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '消费日期',
                    name: 'xfsj',
                    align: 'center',
                    width: '14.4%'
                }, {
                    display: '消费机号',
                    name: 'xfjh',
                    align: 'center',
                    width: '14.2%'
                   
                }],
                pageSize: 200,
                pageSizeOptions: [100, 200],
                url: '${ctx }/query/queryXfjcmxJson.do',
                width: '99%',
                height: '98%',
                onAfterShowData:function(data){
                	//alert(JSON.stringify(data));
                	var d = data.ZXFJC;
                	$("#zxfje").val(d);
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
        
        
        //导出excel
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
        }
</script>       
</body>
</html>