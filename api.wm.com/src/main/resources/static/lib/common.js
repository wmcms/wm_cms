var W= {
    Success: function (msg,callback) {
        this.TipMsg("success", msg,callback);
    },
    Info: function (msg) {
        this.TipMsg("info", msg);
    },
    Warning: function (msg) {
        this.TipMsg("warning", msg);
    },
    Error: function (msg) {
        this.TipMsg("danger", msg);
    },
    TipMsg: function (type, msg,callback) {
        var _tpl = $('#tplTip').html();
        var _html = juicer(_tpl, {it: {type: type, msg: msg}});
        $('body').prepend(_html);
        window.setTimeout(function () {
            $('.alert').remove();
            callback&&callback();
        }, 5 * 1000);
    },
    Confirm:function (msg,okCall,cancelCall) {
        var _tpl = $('#tplconfirm').html();
        var _html = juicer(_tpl, {it: {msg: msg}});
        $('body').prepend(_html);
        $('#confirm_model').show();
        $('#confirm_model .ok').on('click',function () {
            okCall&&okCall();
            $('#confirm_model .close').click();
        });
        $('#confirm_model .cancel').on('click',function () {
            cancelCall&&cancelCall();
            $('#confirm_model .close').click();
        });
        $('#confirm_model .close').on('click',function () {
            $('#confirm_model').remove();
        })
    }
}
/*
 * @author Wilson
 * @CreateDate 2014/5/24
 * @Description 对$.Ajax进行封装,统一数据验证
 */
$.extend({
    Post: function(options) {
        try {
            $.ajax({
                type: "POST",
                url: options.Url,
                cache: false,
                dataType: "json",
                data: options.Data,
                async: (options.Async == undefined ? true : options.Async),
                success: function(response) {
                    if(typeof(response) == "string")
                        response = JSON.parse(response);
                    options.CallBack(response);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {},
                complete: function(xhr, ts) {},
                beforeSend: function(xhr) {

                }
            });
        } catch(e) {
            console.log(e); //异常提醒
        }
    },
    Get: function(options) {
        try {
            $.ajax({
                type: "GET",
                url: options.Url,
                cache: false,
                dataType: "json",
                data: options.Data,
                async: (options.Async == undefined ? true : options.Async),
                success: function(result) {
                    options.CallBack(result);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {},
                complete: function(xhr, ts) {},
                beforeSend: function(xhr) {
                    // xhr.setRequestHeader("x-api-env", C.API_ENV);
                    // xhr.setRequestHeader("x-api-appid", C.APP_NAME);
                    // xhr.setRequestHeader("x-api-lang", C.LANGUAGE);
                }
            });
        } catch(e) {
            console.log(e); //异常提醒
        }
    },

    ApiUrl: function(url) {
        if(C) return C.API_URL + url;
        console.log('配置文件没有加载');
        return url;
    },
    Request: function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r != null) return unescape(r[2]);
        return null;
    },
    //设置cookie
    SetCookie: function(key, value, timeout) {
        var exp = new Date();
        if(timeout == undefined || timeout == null) {
            exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000);
        } else {
            exp.setTime(exp.getTime() + timeout);
        }
        $.cookie(key, value, {
            expires: exp,
            path: '/'
        });
    },
});
/*
 * @author Wilson
 * @CreateDate 2014/5/24
 * @Description 表单序列化成JSON
 */
(function($) {
    //表单序列化
    $.fn.serializeJson = function() {
        var serializeObj = {};
        $(this.serializeArray()).each(function() {
            serializeObj[this.name] = this.value;
        });
        return serializeObj;
    };
})(jQuery);

var J={
    IsNull:function(val){
        return val==undefined||val==null||val=="null"||val=="undefined";
    },
    Init:function () {
        if(juicer) {
            juicer.register('StatusText', function (val) {
                if (val == 1) return "禁用";
                return "有效";
            });
            juicer.register('GenderText', function (val) {
                if (val == 'M') return "男";
                else if (val == 'F') return "女";
                return "--";
            });
            juicer.register('UserTypeText', function (val) {
                if (val == 0) return "会员";
                return "系统用户";
            });
            juicer.register('MetaTypeText', function (val) {
                if (val == 1) return "新闻";
                if (val == 2) return "附件";
                return "未定义";
            });
            juicer.register('Default', function (val,def) {
                if(J.IsNull(val)){
                    return J.IsNull(def)?"":def;
                }
                return  val;
            });
            juicer.register("Selected",function (val,optVal) {
               return val==optVal?'selected="selected"':'';
            });
            juicer.register("Checked",function (val,optVal) {
                return val==optVal?'checked="checked"':'';
            });
            juicer.register('DefaultText', function (val) {
                return val ? val : "--";
            });
        }
    }
}
$(function () {
    J.Init();
    if ($.validator) {
        $.validator.addMethod("mobile", function(value, element) {
              var length = value.length;
              var mobile = /^(1)\d{10}$/;
              return this.optional(element) || (length == 11 && mobile.test(value));
          }, "请正确填写手机号码");

        $.validator.addClassRules({mobile:true});
        $.validator.setDefaults({
            errorElement: "span",
            errorPlacement: function (error, element) {
                error.addClass("help-block");
                var inputGroup = $(element).parent(".input-group");
                inputGroup.length > 0 ? inputGroup.after(error) : element.after(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).closest(".form-group").addClass("has-error");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).closest(".form-group").removeClass("has-error");
            }
        });
    }
})
