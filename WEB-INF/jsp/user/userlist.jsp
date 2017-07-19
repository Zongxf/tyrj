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
				 var str = "";
        		 for(var i=0;i<getJsonLength(data);i++){
        			
        			      str+="<tr><td><input type='checkbox' name='check'value='"+data[i].xm+"'onclick='writeName();'/></td><td>"+data[i].xm+"</td></tr>";
        			 
        		 }
        		 $("#aa").html(str);
        		 
			},
			error:function(){
				alert("加载人员列表错误!");
			}
			
		});
    
    	
    });

	function writeName(){
		var str = "";
		var tag = document.getElementsByName("check");
		for(var i=0; i<tag.length; i++){ 
	 		if(tag[i].checked){
	 		str+="'"+tag[i].value+"',";
	 		}
	 	} 
		 $("#selectdata").val(str);
	}
    	 function  S_user(){
    		$.ajax({
    			url:'${ctx}/queryha/queryUserlistJson.do',
    			type:'post',
    			data:{"xm":$("#selectdata").val(),"dep":dep},
    			dataType:'json',
    			success:function(data){
    				var str = "";
    				 $("#aa").html(str);
           		 for(var i=0;i<getJsonLength(data);i++){
           			
           			      str+="<tr><td><input type='checkbox' name='check'value='"+data[i].xm+"'onclick='writeName();'/></td><td>"+data[i].xm+"</td></tr>";
           			 
           		 }
           		 $("#aa").html(str);
           		// window.loaction.href=window.loaction.href;
    			},
    			error:function(){
    				alert("加载人员列表错误!");
    			}
    		
    			
    		});
    	  } 
    	
        		 
          
    function selectAll(){
    		  var str="";
    		  var selectAll = document.getElementById("selectAll");
    	 	  var tag = document.getElementsByName("check");
    	 	  if(selectAll.checked){
    	 		 for(var i=0; i<tag.length; i++){ 
    	    	 		tag[i].checked=true;
    	    	 		str+="'"+tag[i].value+"',";
    	    	 	} 
    	 		 $("#selectdata").val(str);
    	 	  }else{
    	 		 for(var i=0; i<tag.length; i++){ 
 	    	 		tag[i].checked=false;
 	    	 	} 
    	 		 $("#selectdata").val("");
    	 	  }
    	 	
      		  
    }                          					
    

    function closeDialog()
    {  
    	//关闭dialog 
        dialog.close();
    }
    
        
    </script>
    <style type="text/css">
        tr td,tr th{
         border:1px solid #ccc;
         width:80px;
        }
        #aa input{
           background:white;
           height:20px;
           width:20px;
           border-radius:3px;
        }
    </style>
</head>
<body>
   <div style="margin-left:40px;"> 
        <span class="btn-group"style="width:100%;">
        <input type="text"id="selectdata" value="${xm}" style="width:60%;margin-right:0px;padding-right:0px;margin-top:10px;border-radius:0px;"placeholder="请选择查询用户"/>
        <a class="btn btn-default"href="javascript:S_user();" style="margin-left:0px;padding-left:0px;"><i class="icon-search" ></i>搜索</a> 
        </span>
        </div>
  <div style="width:530px; position:relative; height:430px; display:block; margin:10px auto; background:white;   border:1px solid #ccc; overflow:auto;  ">
        <table style="margin-left:20px;margin-top:10px;border:1px solid #ccc;">
          <thead>
	        <tr>
		        <th><input type='checkbox'id="selectAll" onclick="selectAll();" />全选</th>
		        <th>姓名</th>
	        </tr>
	      </thead>
	      <tbody id="aa">
	      </tbody>  
        </table>
   </div> 
    
    
</body>
</html>