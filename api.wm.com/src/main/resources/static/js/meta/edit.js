var D = {
    FormTpl:'',
};
var R = {
    LoadData: function () {
        $.Post({
            Url: D.Url.Get_Meta_Url,
            Data: {metaId:D.Item.metaId},
            CallBack: function (res) {
                if (res.success) {
                    D = $.extend({}, D,res.data);
                    R.RenderForm();
                    $('#dblType').change(function () {
                        var type=$(this).val();
                        $('#dblParentId').empty().append($('<option>').text("请选择"));
                        if(type>0){
                            $(D.Items).each(function (i,item) {
                                if(item.type==type && item.metaId!=D.Item.metaId){
                                    $('#dblParentId').append($('<option>').text(item.name).val(item.metaId));
                                }
                            })
                        }
                    });
                }
            }
        });
    },
    RenderForm: function () {
        var html = juicer(D.FormTpl, { it: $.extend({},D.Item,{Items:D.Items})});
        $('form').empty().append(html).validate();
        if(D.Item.parentId>0) {
            $('#dblParentId').empty().append($('<option>').text("请选择"));
            $(D.Items).each(function (i, item) {
                if (D.Item.type == item.type && item.metaId != D.Item.metaId) {
                    var $opt=$('<option>').text(item.name).val(item.metaId);
                    if(item.metaId==D.Item.parentId)
                        $opt.attr("selected","selected");
                    $('#dblParentId').append($opt);
                }
            })
        }
    },
    Init: function () {
        D = $.extend({}, __Data__, D);
        D.FormTpl=$('form script').html();
        D.Item=$('form').serializeJson();
        R.LoadData();


    },
    Save:function () {
        if ($('form').valid()) {
            D.Item=$('form').serializeJson();
            if(D.Item.parentId>0){
                $(D.Items).each(function (i,item) {
                    if(item.metaId==D.Item.parentId){
                        D.Item.level =item.level+1;
                    }
                })
            }
            $.Post({
                Url: D.Url.Save_Meta_Url,
                Data: D.Item,
                CallBack: function (res) {
                    if (res.success) {
                        D.Item.metaId=res.data;
                        R.RenderForm();
                        W.Success("保存成功",function () {

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