<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CRM系统首页</title>
    <!-- 通用资源引入 -->
    <%@ include file="/js/common.jsp" %>
    <script type="text/javascript">
        function onChangeTheme(newVal, oldVal) {
            //console.debug(arguments);
            // 获取样式名称
            var themeName = newVal;

            /*
             * 1.修改主页面样式
             */
            // 获取样式引入标签,修改标签的href属性值
            $("#themeLink").attr("href", "/js/jquery-easyui/themes/" + themeName + "/easyui.css");

            /*
             * 2.修改现有iframe样式
             */
            // 获取到页面中所有的iframe元素
            //var iframes = $("iframe");
            // 迭代取出每个iframe
            $("iframe").each(function (index, iframeDom) {
                // 在当前iframe范围内,获取id为themeLink的标签
                var themeLink = $(iframeDom.contentDocument).find("#themeLink");
                //console.debug(themeLink);
                // 统一样式
                themeLink.attr("href", "/js/jquery-easyui/themes/" + themeName + "/easyui.css");
            });

            /*
             * 3.统一新添加iframe样式
             */
            // 缓存当前选中的样式(在顶级页面的缓存)
            window.Config = {
                "themeName": themeName
            };

            /*
             * 4. 标题修改
             background: url(/images/themes/default/banner-pic.gif) no-repeat center bottom
             background-size:cover;
             */
            if (themeName == "default" || themeName == "gray") {
                $("#banner").css("background", "url(/images/themes/" + themeName + "/banner-pic.gif) no-repeat center bottom");
                $("#banner").css("background-size", "cover");
            } else {
                $("#banner").css("background", "");
                $("#banner").css("background-size", "");
            }
        }

        // 在页面加载完毕后
        $(function () {
            var managerInfoDlg, managerInfoFrom;
            managerInfoDlg = $("#managerInfoDlg");
            managerInfoFrom = $("#managerInfoForm");
            var cmdObj = {
                save: function () {
                    var datas = formValues($("#managerInfoForm"));
                    if (datas["area.id"]) {
                        datas.area = {id: datas["area.id"]};
                    }
                    console.log(datas);
                    $.ajax({
                        type: "POST",
                        url: "/sys/manager/changeInfo",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(datas),
                        success: function (jsonResult) {
                            if (jsonResult.code == 1) {
                                managerInfoDlg.dialog("close");
                                $.messager.alert("温馨提示", "修改成功<br/>", "info");
                            } else {
                                var msg = jsonResult.msg;
                                $.messager.alert("温馨提示", "修改失败<br/>" + msg, "error");
                            }
                        }
                    });
                },
                cancel: function () {
                    managerInfoDlg.dialog("close");
                },
            };
            managerInfoDlg.dialog({
                width: 500,
                height: 300,
                title: "修改个人信息",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "保存",
                        iconCls: "icon-save",
                        handler: cmdObj['save']
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cmdObj['cancel']
                    },
                ]

            });

            $.messager.show({
                title: '温馨提示',
                msg: '<font  style="font-size: 13px;"><B><s:property value="#session.user_in_session.trueName"/></B>欢迎回到设备管理平台!!</font>',
                timeout: 3000,
                showType: 'slide'
            });

            $("#systemMenuTree").tree({
                method: "GET",
                url: "/system/menu",
                animate: true,
                onClick: function (node) {
                    console.log(node);
                    if (node.attributes && node.attributes.url) {// 需要打开新tab页的情况
                        var url = node.attributes.url;
                        var text = node.text;
                        var iconCls = node.iconCls;
                        // 判断需要打开的面板是否存在
                        if ($("#mainTabs").tabs("exists", text)) {
                            // 选中现有
                            $("#mainTabs").tabs("select", text);
                        } else {
                            // 获取mainTabs组件
                            // 调用添加方法
                            $("#mainTabs").tabs("add", {
                                title: text,
                                closable: true,
                                iconCls: iconCls,
                                //href : url
                                content: '<iframe src="' + url + '" style="width: 100%;height: 100%;" frameborder="0"></iframe>'
                            });
                        }
                    }
                }
            });
            $("#exitSystem").bind("click", function () {
                window.location.href = "/system/loginOut";
                window.location.href = "/login.jsp";
            });
            $("#managerInfo").bind("click", function () {
                $.ajax({
                    type: "GET",
                    url: "/sys/manager/info",
                    contentType: "application/json",
                    dataType: "json",
                    success: function (jsonResult) {
                        if (jsonResult.code == 1) {
                            var data = jsonResult.data;
                            console.log(data);
                            managerInfoFrom.form("clear");
                            managerInfoDlg.dialog("open");
                            if (data.area) {
                                data['area.id'] = data.area.id;
                            }
                            managerInfoFrom.form("load", data);
                            $("#username").attr("readonly", true);
                        } else {
                            var msg = jsonResult.msg;
                            $.messager.alert("温馨提示", "个人信息加载失败" + msg, "error");
                        }
                    }
                });


            })
        });

    </script>
</head>
<body>
<div id="mainLayout" class="easyui-layout" fit="true">
    <div id="banner" data-options="region:'north'" border="false"
         style="height:65px;background: url(/images/themes/default/banner-pic.gif) no-repeat center bottom;background-size:cover;">
        <!-- <div data-options="region:'north'" border="false" style="height:65px;" > -->
        <div style="float:left;"><h1>设备管理平台</h1></div>
        <div style="float: right;">
            <a href="#" iconCls="icon-mini-settings" class="easyui-linkbutton" id="managerInfo" plain="true"><b>个人信息</b></a>
            <a href="#" class="easyui-linkbutton" id="exitSystem" plain="true"><b>退出</b></a>
        </div>
        <div style="float: right;padding-right: 10px;padding-top: -50px"></div>
    </div>
    <div data-options="region:'south'" border="false" style="height:25px;">
        <div align="center" style="font-family:monospace;">
            @itplayer.cn<br/>
        </div>
    </div>
    <div data-options="region:'west'" title="系统菜单树" headerCls="border-right-none" bodyCls="border-right-none"
         style="width:170px;">
        <ul id="systemMenuTree"></ul>
    </div>
    <div data-options="region:'center',iconCls:'icon-tip'">
        <div id="mainTabs" tabWidth="140" class="easyui-tabs" style="width:700px;height:250px" fit="true"
             border="false">
            <div title="系统首页" style="padding:10px" class="panel-body">
                <p style="font-weight:bold;font-size:40px;color: blue;margin-top: 120px;" align="center">欢迎登陆设备管理平台</p>
            </div>
        </div>
    </div>
    <%--修改个人信息弹窗--%>
    <div id="managerInfoDlg">
        <form id="managerInfoForm" method="post">
            <input type="hidden" name="id">
            <table style="margin-top: 10px;">
                <tr>
                    <td>
                        设备编号:<input id="username" name="username" class="easyui-validatebox" required="true">&emsp;
                    </td>
                    <td>
                        真实姓名:<input name="realName" class="easyui-validatebox" required="true">
                    </td>
                </tr>
                <tr style="margin-top: 50%;padding-top: 40px">
                    <td>
                        联系方式:<input name="mobile" class="easyui-validatebox" required="true">&emsp;
                    </td>
                    <td>
                        联系地址:<input name="addr" class="easyui-validatebox" required="true">
                    </td>
                </tr>
                <tr>
                    <td>
                        性&emsp;&emsp;别:<select id="sex" class="easyui-combobox" name="sex" style="width:150px;">
                        <option value="MAN">男</option>
                        <option value="WOMAN">女</option>
                    </select> &emsp;
                    </td>
                    <td>
                        区&emsp;&emsp;域: <input id="areaSel" class="easyui-combobox" name="area.id" method="GET" ,
                                               data-options="valueField:'id',textField:'areaName',url:'/sys/area/list'"
                                               style="width:150px;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        电子邮件:<input name="email" class="easyui-validatebox" required="true">&emsp;
                    </td>
                    <td>
                        原  密  码 :<input type="password" name="oldPwd" class="easyui-validatebox">&emsp;
                    </td>
                </tr>
                <tr>
                    <td>
                        新 密 码 :<input id="newPwd" type="password" name="password" class="easyui-validatebox">&emsp;
                    </td>
                    <td>
                        确认密码:<input id="surePwd" type="password"  class="easyui-validatebox">&emsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>