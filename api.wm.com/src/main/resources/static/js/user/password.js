var D = {
};
var R = {
    Init: function () {
        D = $.extend({}, __Data__, D);
        $("form").validate();
    },
    Save:function () {
        if ($('form').valid()) {
            var postData=$('form').serializeJson();
            $.Post({
                Url: D.Url.Save_Password_Url,
                Data: postData,
                CallBack: function (res) {
                    if (res.success) {
                        W.Success("修改成功",function () {
                            window.location.reload();
                        });
                    }
                    else{
                        W.Error(res.msg);
                    }
                }
            });
        }
    }
};

$(function () {
    R.Init();
});