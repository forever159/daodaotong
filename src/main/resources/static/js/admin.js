/**
 * 新增菜单按钮事件绑定
 */

function  addMenu() {
        layer.open({
            type: 2,
            title: '新增菜单',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '400px'],
            content: '/menu/menuAdd'
        });
}