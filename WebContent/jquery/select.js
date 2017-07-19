(function() {
	$.extend($.fn, {
		AutoComboBox : function(op) {
			op = $.extend({
				url : false, //获取数据的地址     
				type : "GET", //请求远程数据的方式 get/post
				data : true, //本地数据(Json格式)  只有当url参数设置为false时该参数才生效 否则优先从远程url获取数据
				firstValue : [ true, "", "请选择" ], //是否自动创建第一个值  这个值通常为 请选择 ,不限等
				defaultValue : false,
				defaultText : false,
				valueField: 'id',
		        textField: 'text'
			//如果此值不为false  将根据该值设置默认值 并且递归向上设置所有的下拉框默认值
			}, op);
			var c = this;
			var tempOption = '';
			var checked = "";
			if (op.defaultValue == '' || op.defaultValue.length < 1) {
				op.defaultValue = false;
			}
			var data;
			if (op.url) { //从远程获取数据
				$.ajax({
					type : op.type,
					url : op.url,
					dataType : 'json',
					success : function(msg) {
						data = msg.Rows;
						if (op.firstValue[0]) {
							if (op.defaultValue)
								checked = 'selected="selected"';
							tempOption += "<option value='" + op.firstValue[1]+ "' " + checked + ">" + op.firstValue[2]+ "</option>";
						}
						
						$(data).each(function(i, item) {
							if (item[op.valueField] == op.defaultValue)
								checked = 'selected="selected"';
							else
								{
								checked = '';
								if (item[op.textField] == op.defaultText)
									checked = 'selected="selected"';
								else
									checked = '';
								}
							
								
							
							
							
							tempOption += "<option value='" +item[op.valueField] + "' "+ checked + ">" + item[op.textField] + "</option>";
						});
						c.append(tempOption);
					},
					error : function() {
						alert("not connection server");
					}
				});
			} else {
				if (op.firstValue[0]) {
					if (op.defaultValue)
						checked = 'selected="selected"';
					tempOption += "<option value='" + op.firstValue[1] + "' "+ checked + ">" + op.firstValue[2] + "</option>";
				}
				$.each(op.data, function(i, item) {
					if (item[op.valueField] == op.defaultValue)
						checked = 'selected="selected"';
					else
						{
							checked = '';
							if (item[op.textField] == op.defaultText)
								checked = 'selected="selected"';
							else
								checked = '';
						}
					
						
					tempOption += "<option value='" +item[op.valueField] + "' "+ checked + ">" + item[op.textField] + "</option>";
				});
				c.append(tempOption);

			}
		}
	})
})(jQuery);

var yyyy = [ {
	"id" : 2011,
	"text" : "2011"
}, {
	"id" : 2012,
	"text" : "2012"
}, {
	"id" : 2013,
	"text" : "2013"
} ];

var mm = [ {
	"id" : "01",
	"text" : "01"
}, {
	"id" : "02",
	"text" : "02"
}, {
	"id" : "03",
	"text" : "03"
}, {
	"id" : "04",
	"text" : "04"
}, {
	"id" : "05",
	"text" : "05"
}, {
	"id" : "06",
	"text" : "06"
}, {
	"id" : "07",
	"text" : "07"
}, {
	"id" : "08",
	"text" : "08"
}, {
	"id" : "09",
	"text" : "09"
}, {
	"id" : "10",
	"text" : "10"
}, {
	"id" : "11",
	"text" : "11"
}, {
	"id" : "12",
	"text" : "12"
} ];
function doGetDD() {
	var dd = (new Date().getMonth() + 1);
	if (dd < 10) {
		dd = "0" + dd;
	}
	return dd;
}
