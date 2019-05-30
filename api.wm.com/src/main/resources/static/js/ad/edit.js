var D = {
    FormTpl:'',
    User:null
};
var R = {
    LoadData: function () {
        $.Post({
            Url: D.Url.Get_User_Url,
            Data: {userId:D.User.userId},
            CallBack: function (res) {
                if (res.success) {
                    D.User=res.data;
                    R.RenderForm();
                }
            }
        });
    },
    RenderForm: function () {
        var html = juicer(D.FormTpl, { it: D.User });
        $('form').empty().append(html).validate();

    },
    Init: function () {
        D = $.extend({}, __Data__, D);
        D.FormTpl=$('form script').html();
        D.User=$('form').serializeJson();
        R.LoadData();

    },
    SaveUser:function () {
        if ($('form').valid()) {
            D.User=$('form').serializeJson();
            $.Post({
                Url: D.Url.Save_User_Url,
                Data: D.User,
                CallBack: function (res) {
                    if (res.success) {
                        D.User.userId=res.data;
                        R.RenderForm();
                        W.Success("保存成功");
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
//     W.Confirm("imylasdfadsfa",function () {
//     W.Success("OK")
//     },function () {
//     W.Error("cancel")
// });

});
