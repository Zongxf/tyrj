<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>存款统计</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/LodopFuncs.js"></script>
</head>
<body>
       <span>
                   起始日期： <input type="text" id="dateb" onclick="WdatePicker({startDate: '%y-%M-01 00:00:00' ,maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   终止日期： <input type="text" id="datee" onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59' ,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
        </span>
                <span>存款类型：</span>
               <select class="select" name="lx"id="lx">
                    <option class="select" value="">---请选择---</option>
               <c:forEach items="${cklx}" var ="li" varStatus="l">
					<c:if  test="${li.lx=='1'}"> <option  class="select"value="${li.lx}">现金存款</option></c:if>
					<c:if  test="${li.lx=='2'}"> <option  class="select"value="${li.lx}">补助存款</option></c:if>
				 </c:forEach> 
               </select>
                <span>操作员：</span>
                <select class="select" name="czy"id="czy">
                <option class="select" value="">---请选择---</option>
                <c:forEach items="${czy}" var ="item" varStatus="status">
					<option  class="select"value="${item.czy}">${item.czy}</option>
                </c:forEach>
                </select>
                <span>
               <button onclick="search();">查询</button>
               <button onclick="f_save();">导出</button>
               <button onclick="doprint();">打印</button>
               </span>
         
           <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
         <div id="printdiv" style="display:none;">
			<table id="tableid" class="main" cellspacing="0" cellpadding="0" border="1"
			    bordercolor="#000000" width="800px;">
			      
			   </table>
		</div>
<script type="text/javascript">
        var manager = null;
        var dailog = null;
        //设置默认时间为当天
        var today=new Date();
        var h=today.getFullYear();
        var m=today.getMonth()+1;
        var d=today.getDate();
        $(function () {
        	 
           
            m= m<10?"0"+m:m;   //  这里判断月份是否<10,如果是在月份前面加'0'
            d= d<10?"0"+d:d;        //  这里判断日期是否<10,如果是在日期前面加'0'
            $("#dateb").val(h+"-"+m+"-"+d+" 00:00:00");
            $("#datee").val(h+"-"+m+"-"+d+" 23:59:59");
            
            
            manager = $("#tt").ligerGrid({
                checkbox: true,
                columns: [{
                    display: '存款类型',
                    name: 'lx',
                    align: 'center',
                    width: '33%',
                    render: function (item)
                    {
                        if (parseInt(item.lx) == 1) return '现金存款';
                        return '补助存款';
                    }
                }, {
                    display: '存款金额',
                    name: 'ckje',
                    align: 'center',
                    width: '33%'
                }, {
                    display: '操作员',
                    name: 'czy',
                    align: 'center',
                    width: '33%'
                }],
                pageSize: 200,
                pageSizeOptions: [200, 400,800,1000],
                url: '${ctx }/query/queryCktjJson.do',
                width: '99%',
                height: '98%'
            });
          

        });
        function search() {

            manager.setOptions({
                parms: [{
                    name: 'lx',
                    value: $("#lx").val()
                },{
                    name: 'czy',
                    value: $("#czy").val()
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
        
        
        
        function f_save() {  
        	  
            var data = "";  
            var title = "";
            var count = "";
            for (var i = 0, l = manager.rows.length; i < l; i++) {
            	    var lx=manager.rows[i].lx;
            	    var ckje=manager.rows[i].ckje;
            	    var czy=manager.rows[i].czy;
            	    if(null==czy){
            	    	czy="";
            	    }
            	    if(lx=="1"){
            	    	lx="现金存款";
            	    }else if(lx=="2"){
            	    	lx="补助存款";
            	    }
            	 
                    data =data + lx + "&"+ckje+"&"+czy+"&";  
            }  
            title = "存款统计&存款类型&存款金额&操作员";
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
        	CheckIsInstall();
        	var LODOP = getLodop(); 
        	var n = manager.rows.length;
        	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}table{width:100%;}.t{font-size:20px;font-weight:blod;}</style>"
        	           +""
        	           +"<table><thead><tr><td width='100%' colspan='3'class='t'>存款统计表<span style='float:right;font-size:15px;margin-right:10px;'>时间："+h+"年"+m+"月"+d+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</span></td></tr><tr>"
        	           +"<td width='33.2%'class='t'>类型</td>"
        	           +"<td width='33.2%'class='t'>金额</td>"
        	           +"<td width='33.2%'class='t'>操作员</td></tr></thead><tbody>";
        	
        	  for (var i = 0, l = n; i < l; i++) {
        		  //alert("123");
         	    var lx=manager.rows[i].lx;
         	 
         	    var ckje=manager.rows[i].ckje;
         	    var czy=manager.rows[i].czy;
         	    
         	    
         	    if(lx=="1"){
         	    	lx="现金存款";
         	    }else if(lx=="2"){
         	    	lx="补助存款";
         	    }
         	 
                 str =str +"<tr><td width='33.2%'>" +lx
                          + "</td><td width='33.2%'>"+ckje 
                          + "</td><td width='33.2%'>"+czy 
                          +"</td></tr></tbody>";  
         }  
        	 str=str+"<tfoot><tr><td width='100%' colspan='3' tindex='1'>"
        	 +" 当前是第<font tdata='PageNO' format='ChineseNum' color='blue'>##</font>页</span>/共<font tdata='PageCount' format='ChineseNum' color='blue'>##</font></span>页，"
        	 +"</td></tr></tfoot></table>";
        	LODOP.PRINT_INIT("存款统计打印表格");
        	LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4") ;
        	LODOP.ADD_PRINT_HTM(10,0,"100%","100%",str);
        	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
        	LODOP.NewPageA();
        		LODOP.PREVIEW();		       
        	
        	
        }
</script>
</body>
</html>