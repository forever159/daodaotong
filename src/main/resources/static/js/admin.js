var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;
var util = layui.util;
var table = layui.table;
var form = layui.form;
/**
 * 新增菜单按钮事件绑定，home.html
 * 作者：penglei
 * 日期：2018年8月5日
 */

function  addMenu() {
        layer.open({
            type: 2,
            title: '新增菜单',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '400px'],
            content: '/menu/menuAdd?key=add'
        });
}


/**
 * 更新菜单按钮事件绑定，home.html
 * 作者：penglei
 * 日期：2018年8月6日
 */

function  updateMenu(data) {
    layer.open({
        type: 2,
        title: '编辑菜单',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '400px'],
        content: '/menu/menuAdd?key=update&id='+data.id
    });
}

/**
 * 新增菜单异步加载菜单列表信息，add.html
 * 作者：penglei
 * 日期：2018年8月6日
 */
$.ajax({
    url:'/menu/getMenuList',
    type:'GET',
    dataType:'JSON',
    success:function (result) {
        if(result.code == 200){
            layer.alert("数据加载成功!", {icon: 1});
            $.each(result,function (n,value) {
                if(n=="data"){
                    for(var i = 0;i<value.length;i++){
                        var opt = "<option value='"+value[i].id+"'>"+value[i].menuName+"</option>";
                        $("#menuList").append(opt);
                        form.render();
                    }
                }
            })
        }else{
            layer.alert("数据加载错误!", {icon: 5});
        }
    }
});


/**
 * 监听表单提交
 * 作者：penglei
 * 日期：2018年8月6日
 */
 //监听提交
 form.on('submit(adda)', function(data){
     var fileds = $(data.form).serialize();
         layui.use(['common'], function () {
             var common = layui.common;
             common.ajax('/menu/addMenuTree','get','json',fileds,function (res) {
                 var index = parent.layer.getFrameIndex(window.name);
                 setTimeout(function () {
                     parent.layer.close(index);
                     table.reload('menu',{
                         page:{
                             curr:1
                         }
                     });
                 },1000);
             });
         });
         return false;
  });

/**
 * 删除菜单操作
 * 作者：penglei
 * 日期：2018年8月6日
 */
function delMenu(data) {
    layui.use(['common'], function () {
        var common = layui.common;
        common.ajax('/menu/delMenu','get','json',{"id":data.id},function (res) {
            if(res.code == 200){
                layer.alert(res.msg);
                setTimeout(function () {
                    table.reload('menu',{
                        page:{
                            curr:1
                        }
                    });
                },1000);
            }
        });
    });
}











