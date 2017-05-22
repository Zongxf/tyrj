<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/header.jsp" %>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>小组营业统计</title>
<script type="text/javascript" src="${ctx }/jquery/json2.js"></script>
<script type="text/javascript" src="${ctx }/thirdmodule/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
       <span>
                   起始日期： <input type="text" id="dateb" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'datee\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">至
                   终止日期： <input type="text" id="datee" onclick="WdatePicker({minDate:'#F{$dp.$D(\'dateb\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
         </span>
               <span>营业组：</span>
               <select class="select" name="yyz"id="yyz">
                  <option  class="select"value="0">--请选择--</option>
               </select>
               <span class="btr">
               <button onclick="search();">查询</button>
               <button>打印</button>
               </span>
	             <span style="margin-left:40px;">总营业额：</span><input type="text" id="zyye"style="width:80px;height:25px;"disabled="disabled"/>
	             <span style="margin-left:100px;">总营业人次：</span><input type="text" id="zyyrc"style="width:80px;height:25px;"disabled="disabled"/>   
               <div style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px" id="tt"></div>
          
 <script type="text/javascript">
        var manager = null;
        var dailog = null;

        $(function () {
            manager = $("#tt").ligerGrid({
                checkbox: true,
                columns: [{
                    display: '小组名',
                    name: 'lx',
                    align: 'center',
                    width: '14.4%'
                }, {
                    display: '早餐',
                    name: 'ckje',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '午餐',
                    name: 'czy',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '晚餐',
                    name: 'czy',
                    align: 'center',
                    width:'14.2%'
                }, {
                    display: '其他时段',
                    name: 'czy',
                    align: 'center',
                    width: '14.4%'
                }, {
                    display: '营业总额',
                    name: 'czy',
                    align: 'center',
                    width: '14.2%'
                }, {
                    display: '营业人次',
                    name: 'czy',
                    align: 'center',
                    width: '14.2%'
                }],
                pageSize: 200,
                pageSizeOptions: [200, 400],
                url: '${ctx }/query/queryXzyytjJson.do',
                width: '99%',
                height: '98%'
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
</script>      
</body>
</html>