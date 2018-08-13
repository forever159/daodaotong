layui.define(['jquery'], function(exports){
    var $ = layui.jquery;
    var obj = {
        ajax: function (url, type, dataType,async, data, callback) {
            $.ajax({
                url: url,
                type: type,
                async:async,
                dataType: dataType,
                data: data,
                success: callback
            });
        }
    };
    //输出接口
    exports('common_async', obj);
});