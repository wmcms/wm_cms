var D = {
    Req: {
        pageIndex: 1,
        keyword: "",
        pageSize: 10,
    }
};
var R = {
    LoadData: function () {
        $.Post({
            Url: D.Url.Search_User_Url,
            Data: D.Req,
            CallBack: function (res) {
                if (res.success) {
                    R.RenderList(res.data);
                }
            }
        });
    },
    RenderList: function (data) {
        var tpl = $('#tplUser').html();
        var html = juicer(tpl, {
            Data: data.list
        });
        $('#tbUser').empty().append(html);
        R.InitCheckbox();
        R.InitOperation();
        if (data.total > 0) {
            $('#txtRecordCount').html(data.total);
            $("#pager").pager({
                PageIndex: D.Req.pageIndex || 1,
                PageSize: D.Req.pageSize,
                TotalCount: data.total,
                Callback: R.PageClick
            });
        } else {
            $("#pager").empty();
        }
    },
    PageClick: function (pageIndex) {
        D.Req.pageIndex = pageIndex;
        R.LoadData();
    },
    Search: function () {
        D.Req.keyword = $("#search_keyowrd").val() || "";
        R.PageClick(1);
    },
    Init: function () {
        D = $.extend({}, __Data__, D);
        R.LoadData();
        $('.btnBatchDel').click(function () {
            var userIds =R.GetIds();
            if(userIds.length<=0){
                W.Error("请先至少选择一条数据")
            }
            else{
                W.Confirm("确定要提交吗？",function () {
                    $.Post({
                        Url: D.Url.BatchDelete_User_Url,
                        Data: {userIds:userIds.join(",")},
                        CallBack: function (res) {
                            if (res.success) {
                                W.Success("操作成功",function () {
                                    R.LoadData();
                                });
                            }
                            else{
                                W.Error(res.msg)
                            }
                        }
                    });
                });
            }
        })
    },
    InitOperation:function(){
        $('.operation').on('click',function () {
            var $target=$(this).data();
            if($target) {
                var postData = {userId: $target.id};
                if ($target.type == "disable") {
                    postData = $.extend({}, postData, {status: 1});
                }
                else if($target.type=="enable"){
                    postData = $.extend({}, postData, {status: 0});
                }
                else{
                    return false;
                }
                W.Confirm("确定要提交吗？",function () {
                    $.Post({
                        Url: D.Url.Save_User_Url,
                        Data: postData,
                        CallBack: function (res) {
                            if (res.success) {
                                W.Success("操作成功",function () {
                                    R.LoadData();
                                })
                            }
                            else{
                                W.Error(res.msg)
                            }
                        }
                    });

                });
            }

        })
    },
    GetIds:function(){
        var res = new Array();
        $(":checkbox[name=id]:checked").each(function (i,item) {
            res.push($(item).val()||0);
        });
        return res;
    },
    InitCheckbox: function () {
        $(":checkbox[name=id]").on("click",function (e) {
            if($(e.target).is(":checked")){
                var size=$(":checkbox[name=id]").size();
                var checekdsize=$(":checkbox[name=id]:checked").size();
                $("#checkAll").prop("checked", size==checekdsize);
            }
            else{
                $("#checkAll").prop("checked", false);
            }
        });
        $("#checkAll").on("click",function (e) {
            $(":checkbox[name=id]").prop("checked", $(e.target).is(":checked"));
        });

    }
};

$(function () {
   R.Init();
});
