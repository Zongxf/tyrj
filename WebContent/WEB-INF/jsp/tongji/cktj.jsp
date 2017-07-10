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
     <div class="container center">
       <div class="searchBox center">
       <div class="form-inline"> 
       <div>
       <label class="control-label" > 起始日期：</label>
       <input type="text" id="dateb" onclick="WdatePicker({startDate: '%y-%M-01 00:00:00' ,maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
       <label class="control-label" > 终止日期：</label>
       <input type="text" id="datee" onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59' ,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
        </div>
        <div style="margin-top:5px;">
       <label class="control-label" > 存款类型：</label>
       <select class="select" name="lx"id="lx">
         <option class="select" value="">---请选择---</option>
         <c:forEach items="${cklx}" var ="li" varStatus="l">
			<c:if  test="${li.lx=='1'}"> <option  class="select"value="${li.lx}">现金存款</option></c:if>
			<c:if  test="${li.lx=='2'}"> <option  class="select"value="${li.lx}">补助存款</option></c:if>
		 </c:forEach> 
       </select>
       <label class="control-label" > 操作员：</label>
       <select class="select" name="czy"id="czy">
           <option class="select" value="">---请选择---</option>
            <c:forEach items="${czy}" var ="item" varStatus="status">
			<option  class="select"value="${item.czy}">${item.czy}</option>
            </c:forEach>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
         <span class="btn-group" >
               <button onclick="search();" class="btn btn-default"><i class="icon-search"></i>查询</button>
               <button onclick="f_save();" class="btn btn-default"><i class="icon-upload"></i>导出</button>
               <button onclick="doprint();" class="btn btn-default"><i class="icon-print"></i>打印</button>
         </span>
         </div>
        </div>
              
         </div>
         <div class="contentBox center">
             <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
		 </div>
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
              	var lx = $("#lx").val();
              	var czy = $("#czy").val();
              	var dateb = $("#dateb").val();
              	var datee = $("#datee").val();
                var dqczy = "<%=session.getAttribute("username")%>";
                var time=h+"-"+m+"-"+d;
               var url = "${ctx}/ReportServer?reportlet=cktj.cpt&dateb="+dateb+"&datee="+datee+"&lx="+lx+"&czy="+czy+"&p1="+dqczy+"&p2="+time;
              
               	 window.open(url); 
              } 
          
        //打印
         function doprint(){
        	CheckIsInstall();
        	
          	var lx = $("#lx").val();
          	var czy = $("#czy").val();
          	var dateb = $("#dateb").val();
          	var datee = $("#datee").val();
          
          	
          	
        	var str = "<style>table,tr,td{font-size:15px;border:1px solid black;text-align:center;border-collapse:collapse;cellspacing='0' ;cellpadding='0';}body,table{width:90%;margin-left:40px;background-color:#ffffff;}.t{font-size:20px;font-weight:blod;}</style>"
        	           +"<table><thead><tr><td width='100%' colspan='3'class='t'>存款统计表<div style='float:right;font-size:15px;margin-right:10px;'>时间："+h+"年"+m+"月"+d+"日"+"&nbsp;&nbsp;操作员："+"<%=session.getAttribute("username")%>"+"</div></td></tr><tr>"
        	           +"<td width='33.2%'class='t'>类型</td>"
        	           +"<td width='33.2%'class='t'>金额</td>"
        	           +"<td width='33.2%'class='t'>操作员</td></tr></thead><tbody>";
          	
              
              $.ajax({  
                  url: '${ctx}/query_print/queryCktjJson.do',  
                  data: {  
                  	"lx":lx,"czy":czy,"dateb":dateb,"datee":datee
                  },
                  type:"post",
                  dataType:"json",
                  success: function (data) {
                	  var LODOP = getLodop(); 
                	 // alert(JSON.stringify(data[0]));
                	  for(var i = 0;i<data.length;i++){
                     	 var t = data[i].lx;
                     	 if(t=="1"){
                     		 t="现金存款";
                     	 }else if(t=="2"){
                     		 t="补助存款";
                     	 }
                     	 var b = data[i].ckje;
                     	 var x = data[i].czy;
                     	  str =str +"<tr><td width='33.2%'>" +t
                          + "</td><td width='33.2%'>"+b 
                          + "</td><td width='33.2%'>"+x
                          +"</td></tr>";  
                      }
                	  str=str+"</tbody><tfoot><tr><td width='100%' colspan='3' tindex='1'>"
                 	 +" 当前是第<font tdata='PageNO' format='0' color='black'>##</font>页</span>/共<font tdata='PageCount' format='0' color='black'>##</font></span>页"
                 	 +"</td></tr></tfoot></table>";
                 	LODOP.PRINT_INIT("存款统计打印表格");
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