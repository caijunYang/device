<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>设备管理系统</title>
    <!-- 1.引入外部资源 -->
    <%@ include file="/js/common.jsp" %>
    <script type="text/javascript">
        // 判断当前页面是否为顶级页面
        if (window != top) {
            top.location.href = window.location.href;
        }

        $(function () {
            //console.debug(document.documentElement);
            // 键盘事件
            $(document.documentElement).bind("keydown", function (e) {
                // 获取按钮信息
                //console.debug(e);
                if (e.keyCode === 13) {// 这是回车
                    login();
                    //$("#loginForm").submit();
                }
            });
        });

        function formValues(form) {
            var o = {};
            $.each(form.serializeArray(), function (index) {
                if (o[this['name']]) {
                    o[this['name']] = o[this['name']] + "," + this['value'];
                } else {
                    o[this['name']] = this['value'];
                }
            });
            return o;
        }

        function login() {
            var values = formValues($("#loginForm"));
            $.ajax({
                type: "POST",
                url: "/system/login",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(formValues($("#loginForm"))),
                success: function (jsonResult) {
                    if (jsonResult.code == 1) {
                        location.href = "/system/main";
                    } else {
                        var msg = jsonResult.msg;
                        $.messager.alert("温馨提示", "登陆失败<br/>" + msg, "error", function () {

                        });
                    }
                }
            });


            // $("#loginForm").ajaxSubmit({
            //     type: "post", //提交方式
            //     dataType: "json", //数据类型
            //     contentType: "application/json",
            //     // data: data,//自定义数据参数，视情况添加
            //     url: "/system/login", //请求url
            //     success: function (data) { //提交成功的回调函数
            //         alert(data.code);
            //     }
            // });

            // $("#loginForm").form("submit", {
            //         url: "/system/login",
            //         success: function (data) {
            //
            //             console.log(data);
            //             //console.debug(data);
            //             // 转换json
            //             var rs = $.parseJSON(data);
            //             if (rs.success) {// 登陆成功
            //                 // 通过js动态跳转
            //                 location.href = "main.action";
            //             } else {
            //                 $.messager.alert("温馨提示", "登陆失败<br/>" + rs.message, "error", function () {
            //                     var errorCode = rs.errorCode;
            //                     if (errorCode == -101) {// 用户名错误
            //                         $("input[name=username]").focus();
            //                     } else if (errorCode == -102) {
            //                         $("input[name=password]").focus();
            //                     }
            //                 });
            //             }
            //         }
            //     }
            // );
        }
    </script>
</head>
<body>
<div align="center" style="margin-top:150px;">
    <div title="登陆" class="easyui-panel" style="width:250px;height:200;">
        <form id="loginForm" method="post">
            <table align="center">
                <tr>
                    <td>用户名:</td>
                    <td><input name="username" class="easyui-validatebox" required="true" value="admin"></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input name="password" type="password" class="easyui-validatebox" value="123456"
                               required="true">
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="#" class="easyui-linkbutton" onclick="login();">登陆</a>
                        <a href="#" class="easyui-linkbutton" onclick="reset();">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>