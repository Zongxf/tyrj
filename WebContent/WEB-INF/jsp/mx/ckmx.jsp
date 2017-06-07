<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>存款明细</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript"src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/LodopFuncs.js"></script>
</head>
<body>
	<div class="container center">
		<div class="searchBox center">
		   <div class="form-inline">
			<label class="control-label" > 起始日期：</label>
			<input type="text" id="dateb"onclick="WdatePicker({startDate: '%y-%M-01 00:00:00' ,maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
			<label class="control-label" > 终止日期：</label>
			<input type="text" id="datee"onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59' ,maxDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
			<label class="control-label" > 姓名：</label>
			<input type="text" id="xm"  /> 
			</div>
			<div class="form-inline" style="margin-top:10px;">
			<label class="control-label" > 人员编号：</label>
			<input type="text" id="bh"  /> 
			<label class="control-label" > 存款类型：</label>
			<select class="select" name="lx" id="lx">
				<option class="select" value="">---请选择---</option>
				<c:forEach items="${cklx}" var="li" varStatus="l">
					<c:if test="${li.lx=='1'}">
						<option class="select" value="${li.lx}">现金存款</option>
					</c:if>
					<c:if test="${li.lx=='2'}">
						<option class="select" value="${li.lx}">补助存款</option>
					</c:if>
				</c:forEach>
			</select> 
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="btn-group">
				<button onclick="search();"class="btn btn-default"><i class="icon-search"></i>查询</button>
				<button onclick="exportXml();"class="btn btn-default"><i class="icon-upload"></i>导出</button>
				<button onclick="doprint();"class="btn btn-default"><i class="icon-print"></i>打印</button>
			</span> 
			</div>
		</div>
		<div class="contentBox center">
		<span>存款总额：</span> <input type="text" id="zckje" value="" style="height: 25px;" disabled="disabled" />
			<div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
		</div>
	</div>
</body>
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
                    width: '14.2%'
                   
                }, {
                    display: '姓名',
                    name: 'xm',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '部门',
                    name: 'bm',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '存款金额',
                    name: 'ckje',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '剩余金额',
                    name: 'syje',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '存款日期',
                    name: 'ckrq',
                    align: 'center',
                    width: '14.6%'
                }, {
                    display: '存款类型',
                    name: 'lx',
                    align: 'center',
                    width: '14.4%',
                    render:function (item)
                    {
                        if (parseInt(item.lx) == 1){ return '现金存款';}
                        else if(parseInt(item.lx) == 2){return '补助存款';}
                    }
                }],
                pageSize: 200,
                pageParmName :'page',
                pageSizeOptions: [100, 200,400,1000,2000],
                url: '${ctx }/query/queryCkmxJson.do',
                width: '99%',
                height: '98%',
                onBeforeShowData:function(){
                	$("#zckje").val("");
                },
                onAfterShowData:function(data){
                	//alert(JSON.stringify(data));
                	var d = data.ZCK;
                	if(d=="null"){
                		d=0;
                	}
                	$("#zckje").val(d);
                }
            });
            
        	 
        });
       
        function search() {
        	
            manager.setOptions({
                parms: [{
                    name: 'lx',
                    value: $("#lx").val()
                },{
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
        
        function exportXml() {
        	var data="";
        	var title="";
        	var count="";
        	var lx = $("#lx").val();
        	var bh = $("#bh").val();
        	var xm = $("#xm").val();
        	var dateb = $("#dateb").val();
        	var datee = $("#datee").val();
        	if(lx!=""){
        		data =data+ "lx:"+lx+"&";
        	}
        	if(bh!=""){
        		data =data+"bh:"+bh+"&";
        	}
        	if(xm!=""){
        		data =data+"xm:"+xm+"&";
        	}
        	if(dateb!=""){
        		data =data+"dateb:"+dateb+"&";
        	}
        	if(datee!=""){
        		data =data+"datee:"+datee+"&";
        	}
        	
        	
        	
            
            title = "存款明细&人员编号&姓名&部门&存款金额&剩余金额&存款日期&存款类型&";
            var zckje = $("#zckje").val();
            //alert(zckje);
    	    if(zckje==""){
    	    	zckje="0";
    	    }
            count = "存款总金额：&"+zckje+"&";
            $.ajax({  
                url: '${ctx}/export_new/exportCkmx.do',  
                data: {  
                	"data":data,"title":title,"count":count
                },
                type:"post",
                dataType:"json",
                success: function (data) {  
                	if(data.success){
                		//alert(data.fileName);
                		window.location.href="${ctx}/export_new/downloadFile.do";
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
        	CheckIsInstall();
        	var lx = $("#lx").val();
        	var bh = $("#bh").val();
        	var xm = $("#xm").val();
        	var dateb = $("#dateb").val();
        	var datee = $("#datee").val();
        	
        	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}body,table{width:90%;margin-left:40px;background-color:#ffffff;}.t{font-size:20px;font-weight:blod;}</style>"
        	           +"<table><thead><tr><td width='100%' colspan='7'class='t'>存款明细表"
        	           +"<span style='float:left;font-size:15px;margin-left:10px;'>总存款金额："+$("#zckje").val()+"</span>"
        	           +"<span style='float:right;font-size:15px;margin-right:10px;'>时间："+year+"年"+month+"月"+strDate+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"
						+ "</span></td></tr><tr>"
						+ "<td width='14.2%'class='t'>人员编号</td>"
						+ "<td width='14.2%'class='t'>姓名</td>"
						+ "<td width='14.2%'class='t'>部门</td>"
						+ "<td width='14.2%'class='t'>存款金额</td>"
						+ "<td width='14.2%'class='t'>剩余金额</td>"
						+ "<td width='15.2%'class='t'>存款日期</td>"
						+ "<td width='14.2%'class='t'>存款类型</td></tr></thead><tbody>";
						 $.ajax({  
				                url: '${ctx}/query_print/queryCkmxJson.do',  
				                data: {  
				                	"lx":lx,"bh":bh,"xm":xm,"dateb":dateb,"datee":datee
				                },
				                type:"post",
				                dataType:"json",
				                success: function (data) {  
				                	var LODOP = getLodop();
				                	for(var i = 0;i<data.length;i++){
				                		var t = data[i].bh;
				                		var b = data[i].bm;
				                		var x = data[i].xm;
				                		var c = data[i].ckje;
				                		var s = data[i].syje;
				                		var ck = data[i].ckrq;
				                		var lx = data[i].lx;
				                		if(lx=="1"){
				                			lx="现金存款";
				                		}else if(lx=="2"){
				                			lx="补助存款";
				                		}
				                		str = str + "<tr><td width='14.2%'>" + t
										+ "</td><td width='14.2%'>" + x
										+ "</td><td width='14.2%'>" + b
										+ "</td><td width='14.2%'>" + c
										+ "</td><td width='14.2%'>" + s
										+ "</td><td width='15.2%'>" + ck
										+ "</td><td width='14.2%'>" + lx + "</td></tr>";
				                   	 }
				                	
				                	str = str
				    				+ "</tbody><tfoot><tr><td width='100%' colspan='7' tindex='1'>"
				    				+ " 当前是第<font tdata='PageNO' format='0' color='black'>##</font>页</span>/共<font tdata='PageCount' format='0' color='black'>##</font></span>页，"
				    				+ "</td></tr></tfoot></table>";
				    		LODOP.PRINT_INIT("存款明细打印表格");
				    		LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
				    		LODOP.ADD_PRINT_HTM(25, 0, "100%", "100%", str);
				    		LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
				    		LODOP.NewPageA();
				    		LODOP.PREVIEW();
				                },  
				                error: function (message) {  
				                	alert(message);  
				                }  
				            });  


	}
</script>
</html>