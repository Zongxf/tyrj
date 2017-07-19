<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appname }</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<jsp:include page="/common/comm.jsp" flush="false"></jsp:include>
<script type="text/javascript">

//获取场所列表，加载树   
$(function(){
	
$.ajax({
	   url:'${ctx}/queryha/queryPlaceJson.do',
	   type:'post',
	   dataType:'json',
	   success:function(){
		   $("#cslb1").val("");
		   var indexdata =
				[
					 <c:forEach items= "${sessionScope.placelist}"   var= "node">
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
                     onCheck:function(node){
                  	   var str = "";
                  	   var all = JSON.stringify(node.data);
                  	   var st = "''},";
                  	   all=all.replace(/\[/g,st);
                  	   var re = ",\n{\"text\":\"\",";
                  	   all=all.replace(/],/g,re);
                  	   all="["+all+"]";
                  	   all=eval('('+all+')');
                  	
                  	   //去掉消除的选项
                  	   var nr =  $("#cslb1").val();
                  	   nr = nr.split(",");
							 for(var j=0;j<nr.length;j++){
								 if(nr[j]!=""){
									 for(var i=0;i<getJsonLength(all);i++){
										 var a = "'"+all[i].text+"'";
										 if(nr[j]==a){
											 nr[j]="";
											 all[i].text="";
										 }
									}
								 }
							 }
							 //获取选中的选项
							 for(var i = 0;i<nr.length;i++){
								 if(nr[i]!=""){
								     str+=nr[i]+",";
								 } 
							 }
							 for(var i = 0;i<getJsonLength(all);i++){
								 if(all[i].text!=""){
								     str+="'"+all[i].text+"',";
								 } 
							 }
                  	   
                  	  
                  	   $("#cslb1").val(str);
                     }
                    
                   
                 });
 
      	   },
	   error:function(){
		   alert("获取场所信息出错！");
	   }
 });


//加载部门列表
$.ajax({
	 url:'${ctx}/queryha/queryDepartmentJson.do',
	 type:'post',
	dataType:'json',
	 success:function(){
		 $("#bmlb1").val("");
		  var indexdata =
				 [
				         <c:forEach items= "${sessionScope.departlist}"   var= "node">
				        		 {id: '${node.id}', pid: '${node.pid}',isexpand:false, text: '${node.text}' },
				         </c:forEach> 
				      	 {id: '999999', pid: '0',isexpand:false,checkbox:false, text: '其他' }
				    	]; 
			  //加载树
			 $("#tree2").ligerTree({
				 data:indexdata,
				 isExpand: false, 
			     checkbox:true,
				 slide : false,
				  nodeWidth : 190,
				 idFieldName :'id',
				 parentIDFieldName :'pid',
			     attribute : [ 'text' ],
				onCheck:function(node){
					var str1= "";
				 var all = JSON.stringify(node.data);
				  var st = "''},";
				  all=all.replace(/\[/g,st);
				  var re = ",\n{\"text\":\"\",";
				  all=all.replace(/],/g,re);
				  all="["+all+"]";
				 all=eval('('+all+')');
				 
				 var nt =$("#bmlb1").val(); 
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
					     str1+=nt[i]+",";
					 } 
				 }
				 for(var i = 0;i<getJsonLength(all);i++){
					 if(all[i].text!=""){
					     str1+="'"+all[i].text+"',";
					 } 
				 }
				        			                    	
				        			                    	 
	   $("#bmlb1").val(str1);
}
				        			                      
				        			                     
	});
},
error:function(){
	  alert("获取部门信息出错！");
    	    }
}); 

//加载人员列表                                 					
$.ajax({
	url:'${ctx}/queryha/queryUserlistJson.do',
  type:'post',
  dataType:'json',
  //async:true,//同步执行
  success:function(data){
  var ss = "[";
  for(var i=0;i<getJsonLength(data)-2;i++){
      ss = ss+ "{text:'"+data[i].xm+"'},";
    }
  ss = ss+ "{text:'"+data[getJsonLength(data)-2].xm+"'}";
  ss = ss+"]";
  var indexdata = eval('('+ss+')');
      //加载树
     $("#tree3").ligerTree({
      	 data:indexdata,
      	 isExpand: true, 
      	 checkbox:true,
      	 slide : false,
      	 nodeWidth : 190,
      	 attribute : [ 'text' ],
      	/*  onSelect:function(node){
      	 var nn =  $("#rylb1").val();
      	 nn = nn+"'"+node.data.text+"',";
      	 $("#rylb1").val(nn);
      	 }, */
      	 onCheck:function(node){
      		 var str2 = "";
				 var all = JSON.stringify(node.data);
				  all="["+all+"]";
				 all=eval('('+all+')');
				  
				 var nt =$("#rylb1").val(); 
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
					     str2+=nt[i]+",";
					 } 
				 }
				 for(var i = 0;i<getJsonLength(all);i++){
					 if(all[i].text!=""){
					     str2+="'"+all[i].text+"',";
					 } 
				 }
				 
				
				
				   $("#rylb1").val(str2);
			  }
      	 });
     },
     error:function(){
      	alert("加载人员列表错误!");
      	}
      				
});
});
</script>
</head>
<body>

 <ul id="myTab" class="nav nav-tabs" onactivechanged="showBmlb" >
				<li class="active">
				<a href="#cslb" data-toggle="tab" >
				场所
				</a>
				</li>
				<li>
				<a href="#bmlb" data-toggle="tab">
				部门
				</a>
				</li>
				<li>
				<a href="#rylb" data-toggle="tab"  >
				人员
				</a>
				</li>
			</ul>	
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="cslb">
				   <ul id="tree1" style="margin-top:3px;"></ul>
				   <input  type="text" id="cslb1" value="" style="display:block"/>
				</div>
				
				<div class="tab-pane fade" id="bmlb">
				   <ul id="tree2" style="margin-top:3px;"></ul>
				   <input  type="text" id="bmlb1" value="" style="display:block"/>
				</div>
				
				<div class="tab-pane fade" id="rylb">
				   <input  type="text" id="rylb1" value="" style="display:block"/>
				   <ul id="tree3" style="margin-top:3px;"></ul>
				   
				</div>
				
			</div>
</body>
</html>			