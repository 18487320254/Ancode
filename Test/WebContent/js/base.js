
//全局URL
//var server_host='http://supersystem.anguangkeji.com/Super_system/';
var server_host='';
//Ajax请求
// 注意参数 callback 函数为从服务器端成功取到数据后的回调函数
function An_ajax(callback,API,par,reptype) {
    // 使用 jQuery 向服务器端发送 Ajax 请求
    $.ajax({
        url: server_host+API,          // 对应 Code-10.1 第 17 行, 注意多了 "/wl" 前缀
        type: reptype,                 // 声明以 Post 方式发送请求
        dataType: "text",
        data:par,             // 告诉 jQuery, 服务器端返回的数据是 JSON 格式
        beforeSend: function () {     // 发送请求前的回调函数
            //$("#progress").show();    // 发送请求前显示进度提示(左下角一个绕圈圈的动画)
        },
        success: function (rs) {    // 请求成功时的回调函数    // 若服务器端回应数据中状态码 code >= 0, 说明服务器端一切正常
            if (rs=='n_login') {
                An_go('login.html');
            }else if(rs=='n_do'){
                alert('操作失败!'); 
            } else if(rs=='n_psd') {
                alert('账号或密码错误!');   
            } else {
                callback(rs);// 回调 callback 函数, 并将服务器端返回的数据传入
            }
        },
        error: function () {          // 请求失败的回调函数
            alert('请求失败，请重试!');     
        },
        complete: function () {       // 请求完成时的回调函数
            //$("#progress").hide();    // 隐藏进度提示
        }
    });
}


//go(string 跳转方式，string 目标url)  （支持各种页面跳转）
function An_go(){
    if (arguments.length>1) {
        if (arguments[1]=='top') {
            top.location.href=arguments[0];
        }else if(arguments[1]=='topop'){
            top.open(arguments[0]);
        } else {
            window.open(arguments[0]);
        }
    } else {
        window.location.href=arguments[0];
    }
}