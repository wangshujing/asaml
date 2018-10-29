//截取URL参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
 }


//扩展Date的format方法   
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1,  
        "d+": this.getDate(),  
        "h+": this.getHours(),  
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
        "q+": Math.floor((this.getMonth() + 3) / 3),  
        "S": this.getMilliseconds()  
    };  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    }  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
};

 /**   
    *转换long值为日期字符串   
    * @param l long值   
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
    * @return 符合要求的日期字符串   
    */    
  
    function getFormatDateByLong(l, pattern) {  
        return getFormatDate(new Date(l), pattern);  
    }  
    /**   
    *转换日期对象为日期字符串   
    * @param l long值   
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
    * @return 符合要求的日期字符串   
    */    
    function getFormatDate(date, pattern) {  
        if (date == undefined) {  
            date = new Date();  
        }  
        if (pattern == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        }  
        return date.format(pattern);  
    }  
   
    
Date.prototype.diff = function(date){
	  return (this.getTime() - date.getTime())/(24 * 60 * 60 * 1000);
	};
	

//验证邮箱格式
	function testEmail(str){
		 var reyx= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		 return(reyx.test(str));
	}
	

//验证密码 （6-20字母、数字、下划线）
	function testPassword(s){   
		var patrn=/^(\w){6,20}$/;   
		if (!patrn.exec(s)){
			return false;
		}
		return true;
	}   
	
//验证手机号码，必须以数字开头，除数字外，可含有“-”
	function testMobile(s){   
		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;   
		if (!patrn.exec(s)){
			return false;
		}
		return true;
	}   
	
//验证姓名 是2-15字的汉字
	function testTrueName(str){
		 var patrn = /^\s*[\u4e00-\u9fa5]{1,}[\u4e00-\u9fa5.·]{0,15}[\u4e00-\u9fa5]{1,}\s*$/; 
		    if(!patrn.exec(str))
		    {
		        return false;
		    }
		    return true;
	}
	
	//是1-20字的汉字	
	function testTitle(str){
		 var patrn = /^\s*[\u4e00-\u9fa5.·]{1,20}\s*$/; 
		    if(!patrn.exec(str))
		    {
		        return false;
		    }
		    return true;
	}
	
//验证身份证号
	function testIdCard(s) 
	{
	    var patrn = /^\s*\d{15}\s*$/;
	    var patrn1 = /^\s*\d{16}[\dxX]{2}\s*$/;
	    if(!patrn.exec(s) && !patrn1.exec(s))
	    {
	        return false;
	    }
	    return true;
	}
	
	
//计算字符串长度
	String.prototype.strLen = function() {  
		  var len = 0;  
		  for (var i=0; i<this.length; i++) {  
		    if (this.charCodeAt(i)>127 || this.charCodeAt(i)==94) {  
		       len += 2;  
		     } else {  
		       len ++;  
		     }  
		   }  
		  return len;  
		};
		
//json对象根据key排序
		function sortByKey(array, key) {
		    return array.sort(function(a, b) {
		        var x = a[key]; var y = b[key];
		        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
		    });
		}