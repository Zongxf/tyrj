
	//打开部门选择弹出框
    function f_choose()
    {
        $.ligerDialog.open({
            height:600,
            width: 700,
            title : '选择部门',
            url: '/tyrj/queryha/queryDepart.do', 
            showMax: false,
            showToggle: true,
            showMin: false,
            isResize: true,
            slide: false,
            //自定义参数
            myDataName:'选择部门',
            buttons: [
             { text: '取消', onclick: function (item, dialog) { dialog.close(); } },
             { text: '确定', onclick: function (item, dialog) {
           	  var ss = dialog.frame.$("#selectdata").val() ;
                 $("#bm").val(ss);
                 dialog.close(); 
            }
            }]

        });

    } 
    
    
	//打开部门选择弹出框
    function f_Place()
    {
        $.ligerDialog.open({
            height:600,
            width: 700,
            title : '选择场所',
            url: '/tyrj/queryha/queryPlace.do', 
            showMax: false,
            showToggle: true,
            showMin: false,
            isResize: true,
            slide: false,
            //自定义参数
            myDataName:'选择场所',
            buttons: [
             { text: '取消', onclick: function (item, dialog) { dialog.close(); } },
             { text: '确定', onclick: function (item, dialog) {
           	  var ss = dialog.frame.$("#selectdata").val() ;
                 $("#cs").val(ss);
                 dialog.close(); 
            }
            }]

        });

    } 
    
    
    
  //打开人员选择弹出框
    function f_User()
    {
        $.ligerDialog.open({
            height:600,
            width: 700,
            title : '选择人员',
            url: '/tyrj/queryha/queryUser.do', 
            data:$("#bm").val(),
            showMax: false,
            showToggle: true,
            showMin: false,
            isResize: true,
            slide: false,
            //自定义参数
            myDataName:'选择人员',
            buttons: [
             { text: '取消', onclick: function (item, dialog) { dialog.close(); } },
             { text: '确定', onclick: function (item, dialog) {
           	  var ss = dialog.frame.$("#selectdata").val() ;
                 $("#xm").val(ss);
                 dialog.close(); 
            }
            }]

        });

    } 
    
    //获取json字符串长度
    function getJsonLength(json){
    	  var jsonLength=0;
          for (var i in json) {
              jsonLength++;
          }
          return jsonLength;
    }
    
    //刷新当前页面
    function reload(){
    	window.location.reload() ;
    }
    
    
 
	

