var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;
var util = layui.util;
var table = layui.table;
var form = layui.form;
var common = layui.common;
var common_async = layui.common_async;
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
function  getMenuList() {
    $.ajax({
        url:'/menu/getMenuList',
        type:'GET',
        dataType:'JSON',
        success:function (result) {
            if(result.code == 200){
                // layer.alert("数据加载成功!", {icon: 1});
                $.each(result,function (n,value) {
                    if(n=="data"){
                        for(var i = 0;i<value.length;i++){
                            var opt = "<option value='"+value[i].id+"'>"+value[i].menuName+"</option>";
                            $("#menuList").append(opt);
                            form.render();
                        }
                        pidS();
                    }
                });
            }else{
                layer.alert("数据加载错误!", {icon: 5});
            }
        }
    });
}


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
                     //同步刷新table
                     parent.layui.table.reload('menu',{page:{curr:1}});
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



/**
 * 监听更新菜单表单提交
 * 作者：penglei
 * 日期：2018年8月7日
 */
//监听提交
form.on('submit(addu)', function(data){
    var fileds = $(data.form).serialize();
    layui.use(['common'], function () {
        var common = layui.common;
        common.ajax('/menu/updateMenuTree','get','json',fileds,function (res) {
            var index = parent.layer.getFrameIndex(window.name);
            setTimeout(function () {
                parent.layer.close(index);
            },1000);
            //同步刷新table
            parent.layui.table.reload('menu',{page:{curr:1}});
        });
    });
    return false;
});



//--------------角色页面--------------
/**
 * 生成树结构
 */
function treeRole(){
        var treeNode = nodesTree(" ");
        layui.tree({
            elem: '#demo1' //指定元素
            ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
            ,click: function(item){ //点击节点回调
                // layer.alert(JSON.stringify(item.children));
                // layer.msg('当前节名称：'+ item.name+":"+item.children.length + '<br>全部参数：'+ JSON.stringify(item));
                // var treeNode = nodesTree(item.id);
            }
            ,nodes: treeNode
        });
}


//回显加载节点
function nodesTree(data) {
    var tree;
    $.ajax({
        url:'/role/roleTree',
        type:'GET',
        dataType:'JSON',
        async:false,
        success:function (res) {
            tree =  res;
            // layer.msg('<br>全部参数：'+ JSON.stringify(tree));
        }
    });
    return tree;
}

//--------------------权限页面----------------------

/**
 * 生成树结构
 */
function treePermission(){
    common.ajax('/permission/getPermissionList','POST','JSON',function (res) {
        layui.tree({
            elem: '#permission' //指定元素
            ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
            ,click: function(item){ //点击节点回调
                layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
                console.log(item);
            }
            ,nodes: [ //节点
                {
                    name: '常用文件夹'
                    ,id: 1
                    ,alias: 'changyong'
                    ,children: [
                        {
                            name: '所有未读（设置跳转）'
                            ,id: 11
                            ,href: 'http://www.layui.com/'
                            ,alias: 'weidu'
                        }, {
                            name: '置顶邮件'
                            ,id: 12
                        }, {
                            name: '标签邮件'
                            ,id: 13
                        }
                    ]
                }, {
                    name: '我的邮箱'
                    ,id: 2
                    ,spread: true
                    ,children: [
                        {
                            name: 'QQ邮箱'
                            ,id: 21
                            ,spread: true
                            ,children: [
                                {
                                    name: '收件箱'
                                    ,id: 211
                                    ,children: [
                                        {
                                            name: '所有未读'
                                            ,id: 2111
                                        }, {
                                            name: '置顶邮件'
                                            ,id: 2112
                                        }, {
                                            name: '标签邮件'
                                            ,id: 2113
                                        }
                                    ]
                                }, {
                                    name: '已发出的邮件'
                                    ,id: 212
                                }, {
                                    name: '垃圾邮件'
                                    ,id: 213
                                }
                            ]
                        }, {
                            name: '阿里云邮'
                            ,id: 22
                            ,children: [
                                {
                                    name: '收件箱'
                                    ,id: 221
                                }, {
                                    name: '已发出的邮件'
                                    ,id: 222
                                }, {
                                    name: '垃圾邮件'
                                    ,id: 223
                                }
                            ]
                        }
                    ]
                }
                ,{
                    name: '收藏夹'
                    ,id: 3
                    ,alias: 'changyong'
                    ,children: [
                        {
                            name: '爱情动作片'
                            ,id: 31
                            ,alias: 'love'
                        }, {
                            name: '技术栈'
                            ,id: 12
                            ,children: [
                                {
                                    name: '前端'
                                    ,id: 121
                                }
                                ,{
                                    name: '全端'
                                    ,id: 122
                                }
                            ]
                        }
                    ]
                }
            ]
        });
    });
}









