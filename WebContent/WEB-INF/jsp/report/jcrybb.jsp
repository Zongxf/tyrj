<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>纠错人员报表</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
   <span>
                   起始日期： <input type="text" id="dateb" onclick="WdatePicker({startDate: '%y-%M-01 00:00:00' ,maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   终止日期： <input type="text" id="datee" onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59' ,maxDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                  人员编号：<input type="text" id="bh"style="width:100px;"/>
                   姓名：<input type="text" id="xm"style="width:100px;"/>
     </span>
               <span class="btr">
               <button onclick="search();">查询</button>
               <button onclick="exportXml();">导出</button>
               <button>打印</button>
               </span>
             <span style="margin-left:40px;">纠错人数：</span><input type="text" id="zjcrs"style="width:80px;height:25px;"disabled="disabled"/>
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
                    width: '12.5%'
                }, {
                    display: '姓名',
                    name: 'xm',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '部门',
                    name: 'bm',
                    align: 'center',
                    width: '13.5%'
                }, {
                    display: '变化金额',
                    name: 'bh',
                    align: 'center',
                    width: '12.5%'
                   
                }, {
                    display: '纠错金额',
                    name: 'jcje',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '原始金额',
                    name: 'ysje',
                    align: 'center',
                    width: '12.5%'
                }, {
                    display: '卡号',
                    name: 'kh',
                    align: 'center',
                    width: '12.5%'
                   
                }, {
                    display: '纠错日期',
                    name: 'jcsj',
                    align: 'center',
                    width: '13.5%'
                    
                }],
                pageSize: 200,
                pageParmName :'page',
                pageSizeOptions: [100, 200],
                url: '${ctx }/report/jcrybbReportJson.do',
                width: '99%',
                height: '98%',
                onAfterShowData:function(data){
                	//alert(JSON.stringify(data));
                	var d = data.Total;
                	$("#zjcrs").val(d);
                }
            });
            
         
        	 
        });
        function showData(){
        	   var data=manager.getData();
               alert(JSON.stringify(data));
        }
       
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
            	    var bh=manager.rows[i].bh;
            	    var jcje=manager.rows[i].jcje;
            	    var ysje=manager.rows[i].ysje;
            	    var jcsj=manager.rows[i].jcsj;
            	    var kh=manager.rows[i].kh;
                    data =data + rybh + "&"+xm+"&"+bm+"&"+bh+"&"+jcje+"&"+ysje+"&"+jcsj+"&"+kh+"&";  
            }  
            title = "纠错人员报表&人员编号&姓名&部门&变化金额&纠错金额&原始金额&纠错日期&卡号&";
            var zjcrs = $("#zjcrs").val();
    	    if(zjcrs==""){
    	    	zjcrs="0";
    	    }
            count = "纠错总人数：&"+zjcrs+"&";
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