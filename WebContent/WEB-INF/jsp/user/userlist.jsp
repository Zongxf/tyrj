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
    //调用页面的dialog对象(ligerui对象)	
    var dialog = frameElement.dialog;
    var dep =  dialog.get("data");
    
   
    $(function(){
    	$.ajax({
			url:'${ctx}/queryha/queryUserlistJson.do',
			type:'post',
			data:{"xm":$("#selectdata").val(),"dep":dep},
			dataType:'json',
			async:true,//同步执行
			success:function(data){
				var ss = "[";
				for(var i=0;i<getJsonLength(data)-2;i++){
					ss = ss+ "{text:'"+data[i].xm+"'},";
				}
				ss = ss+ "{text:'"+data[getJsonLength(data)-2].xm+"'}";
				ss = ss+"]";
				var indexdata = eval('('+ss+')');
				  //加载树
        		   $("#tree1").ligerTree({
                       data:indexdata,
                       isExpand: true, 
                       checkbox:false,
           			   slide : false,
           			   nodeWidth : 190,
                       attribute : [ 'text' ],
                       /* onCheck:function(node){
                    	   var nn =  $("#selectdata").val();
                    	   nn = nn+"'"+node.data.text+"',";
                    	   $("#selectdata").val(nn);
                    		
                       }, */
                       onSelect:function(node){
                    	   var nn =  $("#selectdata").val();
                    	   nn = nn+"'"+node.data.text+"',";
                    	   $("#selectdata").val(nn);
                       }
        		   });
        		      
			},
			error:function(){
				alert("加载人员列表错误!");
			}
			
		});
    
    	
    });
   
    	/* function  S_user(){
    		$.ajax({
    			url:'${ctx}/queryha/queryUser.do',
    			type:'post',
    			data:{"xm":$("#selectdata").val()},
    			dataType:'json',
    			success:function(){
    			},
    			error:function(){
    				alert("加载人员列表错误!");
    			}
    		
    			
    		});
    	  } */
    	
        		 
          
                                					
    

    function closeDialog()
    {  
    	//关闭dialog 
        dialog.close();
    }
    
        
    </script>
</head>
<body>
   <div style="margin-left:40px;"> 
        <span class="btn-group"style="width:100%;">
        <input type="text"id="selectdata" value="${xm}" style="width:60%;margin-right:0px;padding-right:0px;margin-top:10px;border-radius:0px;"placeholder="请选择查询用户"/>
        <!-- <a class="btn btn-default"href="" style="margin-left:0px;padding-left:0px;" onclick="S_user();"><i class="icon-search" ></i>搜索</a> -->
        </span>
        </div>
  <div style="width:600px; position:relative; height:450px; display:block; margin:10px auto; background:white;   border:1px solid #ccc; overflow:auto;  ">
       <ul id="tree1" style="margin-top: 3px;"></ul>
   </div> 
   <div style="display: none"></div>
    
    
</body>
</html>