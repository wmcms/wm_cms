(function($) {
    $.fn.pager = function(options) {
        var opts = $.extend({}, $.fn.pager.defaults, options);
        return this.each(function() {
            options.TotalCount=parseInt(opts.TotalCount);
            options.PageSize=parseInt(opts.PageSize);
            options.PageCount=parseInt(options.TotalCount/options.PageSize);
            if((options.TotalCount%options.PageSize)>0)
                options.PageCount++;
            $(this).empty().append(renderpager(parseInt(options.PageIndex), options.PageCount, options.Callback));
            $('.pages li').mouseover(function() {
                document.body.style.cursor = "pointer";
            }).mouseout(function() {
                document.body.style.cursor = "auto";
            });
        });
    };

    function renderpager(index, count, callback) {
        var $pager = $('<ul class="pagination"></ul>');
        $pager.append(renderButton('首页', index, count, callback));
        $pager.append(renderButton('上一页', index, count, callback));
        var startPoint = 1;
        var endPoint = 5;
        if(index > 4 && index < count - 1) {
            startPoint = index - 2;
            endPoint = index + 2;
        }
        if(index > count - 2 && index < count + 1) {
            startPoint = count - 4;
            endPoint = count;
        }
        if(endPoint > count) {
            startPoint = count - 4;
            endPoint = count;
        }
        if(startPoint < 1) {
            startPoint = 1;
        }

        for(var page = startPoint; page <= endPoint; page++) {
            var currentButton = $('<li  data-page="'+(page)+'"><a href="javascript:void(0);">' + (page) + '</a></li>');
            page == index ? currentButton.addClass('active') : currentButton.click(function() {
                callback($(this).data().page);
            });
            currentButton.appendTo($pager);
        }
        $pager.append(renderButton('下一页', index, count, callback));
        $pager.append(renderButton('尾页', index, count, callback));
        return $pager;
    }

    function renderButton(buttonLabel, index, count, callback) {
        var $Button = $('<li/>').append($('<a href="javascript:void(0);" />').html(buttonLabel));
        var destPage = 1;
        switch(buttonLabel) {
            case "首页":
                destPage = 1;
                if(index <= 1)
                    $Button.addClass('nodatapage');
                break;
            case "上一页":
                destPage = index - 1;
                if(index <= 1)
                    $Button.addClass('nodatapage');
                break;
            case "下一页":
                destPage = index + 1;
                if(index >= count)
                    $Button.addClass('nodatapage');
                break;
            case "尾页":
                destPage = count;
                if(index >= count)
                    $Button.addClass('nodatapage');
                break;
        }
        if(buttonLabel == "首页" || buttonLabel == "上一页") {
            //index <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() {
            //	callback(destPage);
            //});
            $Button.click(function() {
                callback(destPage);
            });
        } else {
            $Button.click(function() {
                callback(destPage);
            });
            //index >= count ? $Button.addClass('pgEmpty') : $Button.click(function() {
            //	callback(destPage);
            //});
        }

        return $Button;
    }
    $.fn.pager.defaults = {
        PageIndex: 1,
        PageCount: 1,
        PageSize: 10,
        TotalCount: 1,
    };

})(jQuery);