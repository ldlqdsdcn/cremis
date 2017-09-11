$.extend($.fn.validatebox.defaults.rules, {
    idcard : {// 验证身份证 
        validator : function(value) { 
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
        }, 
        message : '身份证号码格式不正确' 
    },
    minLength: {
        validator: function(value, param){
            return value.length >= param[0];
        },
        message: '请输入至少（2）个字符.'
    },
    length:{validator:function(value,param){ 
        var len=$.trim(value).length; 
            return len>=param[0]&&len<=param[1]; 
        }, 
            message:"输入内容长度必须介于{0}和{1}之间." 
        }, 
    phone : {// 验证电话号码 
        validator : function(value) { 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[0-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        }, 
        message : '格式不正确,请使用下面格式:020-88888888' 
    }, 
    mobile : {// 验证手机号码 
        validator : function(value) { 
            return /^(13|15|18|17)\d{9}$/i.test(value); 
        }, 
        message : '手机号码格式不正确' 
    },
    tel:{
        validator:function(value){
            return /^(13|15|18|17)\d{9}$/i.test(value)||/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[0-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '电话格式不正确'
    },
    intOrFloat : {// 验证整数或小数 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '请输入数字，并确保格式正确' 
    }, 
    currency : {// 验证货币 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '货币格式不正确' 
    },
    currency2:{
        validator : function(value) {
            return /^\d+(\.([1-9]|[0-9][1-9]))?$/i.test(value);
        },
        message : '货币格式不正确'
    },
    qq : {// 验证QQ,从10000开始 
        validator : function(value) { 
            return /^[1-9]\d{4,9}$/i.test(value); 
        }, 
        message : 'QQ号码格式不正确' 
    }, 
    integer : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[1-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数' 
    },
    deliveryNumber : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[0-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数' 
    },
    cards : {// 15位 
        validator : function(value) { 
           return /^\d{13}([0-9])?$/i.test(value);  
        }, 
        message : '请输入15整数卡号' 
    },
    age : {// 验证年龄
        validator : function(value) { 
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value); 
        }, 
        message : '年龄必须是0到120之间的整数' 
    },
    email : {// 邮箱校验
        validator : function(value) { 
            return /^[a-zA-Z0-9_\.]+@[a-zA-Z0-9-]+[\.a-zA-Z]+$/i.test(value); 
        }, 
        message : '请输入合法的邮箱地址' 
    }, 
    chinese : {// 验证中文 
        validator : function(value) { 
            return /^[\Α-\￥]+$/i.test(value); 
        }, 
        message : '请输入中文[系统提示：用户名称必填中文名称]' 
    }, 
    english : {// 验证英语 
        validator : function(value) { 
            return /^[A-Za-z]+$/i.test(value); 
        }, 
        message : '请输入英文' 
    }, 
    unnormal : {// 验证是否包含空格和非法字符 
        validator : function(value) { 
            return /.+/i.test(value); 
        }, 
        message : '输入值不能为空和包含其他非法字符' 
    }, 
    username : {// 验证用户名 
        validator : function(value) { 
            return /^[a-zA-Z][a-zA-Z0-9_]{0,11}$/i.test(value); 
        }, 
        message : '用户名不合法（字母开头，允许1-12字节，允许字母数字下划线）' 
    }, 
    faxno : {// 验证传真 
        validator : function(value) { 
//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value); 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '传真号码不正确' 
    }, 
    zip : {// 验证邮政编码 
        validator : function(value) { 
            return /^[1-9]\d{5}$/i.test(value); 
        }, 
        message : '邮政编码格式不正确' 
    }, 
    ip : {// 验证IP地址 
        validator : function(value) { 
            return /d+.d+.d+.d+/i.test(value); 
        }, 
        message : 'IP地址格式不正确' 
    },
    spell:{
        //验证大写字母
        validator:function(value){
            return /^[A-Z]$/.test(value);
        },
        message : '只能输入一位大写英文字母'
    },
    nmb:{
        //验证大写字母
        validator:function(value){
            return /^\s*\d*\s*$/.test(value);
        },
        message : '只能输入数字'
    },
    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入姓名[中文或英文]' 
    },
    date : {// 验证日期格式
        validator : function(value) { 
         //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value); 
        },
        message : '请输入合适的日期格式'
    },
    msn:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)' 
    },
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的密码不一致！'    
    },
    province:{
    	validator:function(value){
    		if(value.trim()!= '请选择'){
    			return true;
    		}else{
    			return false;
    		}
    	},
        message : '请选择省份！'
    },
    city:{
    	validator:function(value){
    		if(value.trim()!= '请选择'){//.trim()
    			return true;
    		}else{
    			return false;
    		}
    	},
        message : '请选择城市！'
    }, 
    area:{
    	validator:function(value){
    		if(value.trim()!= '请选择'){
    			return true;
    		}else{
    			return false;
    		}
    	},
        message : '请选择地区！'
    },
    pwd:{
    	  validator : function(value, param){ 
    		  var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
              var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
              var enoughRegex = new RegExp("(?=.{6,}).*", "g"); 
              var flag=false;
              if(strongRegex.test(value)&&(value.length<=18)&&(value.length>=6))
              {
            	flag=true;  
              }else if(mediumRegex.test(value)&&(value.length<=18)&&(value.length>=6)){
            	  flag=true;
              }else if(enoughRegex.test(value)&&(value.length<=18)&&(value.length>=6)){
            	  flag=true;
              }
              return flag;
          }, 
          message : '输入密码格式不对！'    
    },
    minTime:{
        validator:function(value){
            var date = new Date(value).getTime();
            var current = +new Date();
            return date>current;
        },
        message : '选择的时间不能小于当前时间！'
    },
    describLen:{ 
        validator : function(value, param){ 
            var len=value.replace(/[\u0391-\uFFE5]/g,"aa").length;

            if(len>400){
            	return false;
            }else{
            	return true;
            }
        }, 
        message : '长度超过不能超过200字！'    
    }
});