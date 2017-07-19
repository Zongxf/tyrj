<%@page import="org.nutz.lang.Lang"%>
<%@page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>部门列表</title>
    <script type="text/javascript">
       $(document).ready(function(){
            $.ajax({
        	   url:'${ctx}/queryha/queryDepartmentJson.do',
        	   type:'post',
        	   dataType:'json',
        	   success:function(){
        		   var indexdata =
        				[
        					 <c:forEach items= "${sessionScope.departlist}"   var= "node">
        					 {id: '${node.id}', pid: '${node.pid}',isexpand:false, text: '${node.text}' },
        					 </c:forEach> 
        					{id: '999999', pid: '0',isexpand:false,checkbox:false, text: '其他' }
        				]; 
        		           //加载树
		        		   $("#tree1").ligerTree({
		                       data:indexdata,
		                       isExpand: false, 
		                       checkbox:true,
		           			   slide : false,
		           			   nodeWidth : 190,
		                       idFieldName :'id',
		                       parentIDFieldName :'pid',
		                       attribute : [ 'text' ],
		                      /*  onSelect:function(node){
		                    	   str+="'"+node.data.text+"',";
		                    	   $("#selectdata").val(str);
		                       } , */
		                       onCheck:function(node){
		                    	   var str="";
		                    	   var all = JSON.stringify(node.data);
		                    	   var st = "''},";
		                    	   all=all.replace(/\[/g,st);
		                    	   //all=replaceAll(all,'\[',st);
		                    	   var re = ",\n{\"text\":\"\",";
		                    	   all=all.replace(/],/g,re);
		                    	   //all=replaceAll(all,'\],',re);
		                    	   all="["+all+"]";
		                    	   all=eval('('+all+')');
		                    	
		                    	   
		                    	   var nt =$("#selectdata").val(); 
									 nt = nt.split(",");
									 for(var j=0;j<nt.length;j++){
										 if(nt[j]!=""){
											 for(var i=0;i<getJsonLength(all);i++){
												 var a = "'"+all[i].text+"'";
												 if(nt[j]==a){
													 nt[j]="";
													 all[i].text="";
												 }
											}
										 }
									 }
									 for(var i = 0;i<nt.length;i++){
										 if(nt[i]!=""){
										     str+=nt[i]+",";
										 } 
									 }
									 for(var i = 0;i<getJsonLength(all);i++){
										 if(all[i].text!=""){
										     str+="'"+all[i].text+"',";
										 } 
									 }
		                    	 
		                    	   $("#selectdata").val(str);
		                       }
		                      
		                     
		                   });
           
		        	   },
        	   error:function(){
        		   alert("获取部门信息出错！");
        	   }
           });
                                        					
       });
    
   //调用页面的dialog对象(ligerui对象)	
    var dialog = frameElement.dialog;
    function closeDialog()
    {  
    	//关闭dialog 
        dialog.close();
    }
    
        
    </script>
</head>
<body>
   <div style="margin-left:30px;"> <span>已选择：</span><input type="text"id="selectdata" value=""style="width:60%;"placeholder="请选择查询部门"/></div>
   <div style="width:600px; position:relative; height:450px; display:block; margin:10px auto; background:white;   border:1px solid #ccc; overflow:auto;  ">
       <ul id="tree1" style="margin-top: 3px;"></ul>
   </div> 
   <div style="display: none"></div>
    
</body>
</html>