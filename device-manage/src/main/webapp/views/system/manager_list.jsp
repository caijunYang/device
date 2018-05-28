<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员管理</title>
    <!-- 1.引入外部资源 -->
    <%@ include file="/js/common.jsp" %>
    <!-- 2.声明页面常用的组件 -->
    <!-- 3.在页面加载完毕后,缓存这些组件 -->
    <!-- 4.声明一个"命令管理对象",声明需要提供的函数支持 -->
    <!-- 5.初始化页面组件 -->

    <script type="text/javascript">
        function genderFormatter(val, row, index) {
            switch (val) {
                case "MAN":
                    return "男";
                    break;
                case "WOMAN":
                    return "女";
                    break;
                default:
                    return "";
                    break;
            }
        }

        function areaFormatter(val, row, index) {
            console.log(val);
            if (val) {
                return val.areaName;
            }
            return "";
        }

        $(function () {
            // 3.在页面加载完毕后,缓存这些组件
            // 2.声明页面常用的组件
            var managerGrid, managerDlg, managerForm, rolerDlg;
            managerGrid = $("#managerGrid");
            managerDlg = $("#managerDlg");
            managerForm = $("#managerForm");
            rolerDlg = $("#rolerDlg");

            // 4.声明一个"命令管理对象",声明需要提供的函数支持
            var cmdObj = {
                create: function () {
                    //console.debug("create..");
                    // 清空表单,不能使用以下方法
                    // form的清空会波及到grid下分页栏的显示
                    $("#username").removeAttr("readonly");
                    managerForm.form("clear");
                    managerDlg.dialog("open");

                },
                edit: function () {
                    //console.debug("edit..");
                    // 获取选中数据
                    var rowData = managerGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    // 清空表单
                    managerForm.form("clear");
                    // 打开面板
                    managerDlg.dialog("open");
                    if (rowData.area) {
                        rowData['area.id'] = rowData.area.id;
                    }
                    console.log(rowData)
                    // if(rowData.sex){
                    //     rowData[]
                    // }
                    // 数据加载处理
                    // 加载数据
                    managerForm.form("load", rowData);
                    $("#username").attr("readonly", true);
                },
                del: function () {
                    var rowData = managerGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                        if (r) {
                            if (rowData.id) {
                                $.ajax({
                                    type: "GET",
                                    url: "/sys/manager/delete/" + rowData.id,
                                    contentType: "application/json",
                                    dataType: "json",
                                    success: function (jsonResult) {
                                        if (jsonResult.code == 1) {
                                            managerGrid.datagrid("reload");
                                            $.messager.alert("温馨提示", "删除成功", "success");
                                        } else {
                                            var msg = jsonResult.msg;
                                            $.messager.alert("温馨提示", "删除失败<br/>" + msg, "error");
                                        }
                                    }
                                });
                            }
                        }
                    });
                },
                refresh: function () {
                    var queryParams = $('#managerGrid').datagrid('options').queryParams;
                    var realName = $("#realName").val();
                    queryParams.realName = "";
                    queryParams.area_id = "";
                    managerGrid.datagrid("reload");
                },
                save: function () {
                    var datas = formValues($("#managerForm"));
                    if (datas["area.id"]) {
                        datas.area = {id: datas["area.id"]};
                    }
                    $.ajax({
                        type: "POST",
                        url: "/sys/manager/update",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(datas),
                        success: function (jsonResult) {
                            if (jsonResult.code == 1) {
                                managerDlg.dialog("close");
                                managerGrid.datagrid("reload");
                            } else {
                                var msg = jsonResult.msg;
                                $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                            }
                        }
                    });
                },
                cancel: function () {
                    managerDlg.dialog("close");
                },
                search: function () {
                    var queryParams = $('#managerGrid').datagrid('options').queryParams;
                    var realName = $("#realName").val();
                    var areaValue = $("#areaTree").combobox("getValue");
                    queryParams.realName = realName;
                    queryParams.area_id = areaValue;
                    $("#dg").datagrid('reload');
                    managerGrid.datagrid("reload");
                },
                roler: function () {
                    var rowData = managerGrid.datagrid("getSelected");
                    rolerDlg.dialog("open");
                    $("#rolerTree").tree({url: "/sys/manager/role/" + rowData.id});
                    $("#rolerTree").tree("reload");

                },
                rolerSave: function () {
                    var rowData = managerGrid.datagrid("getSelected");
                    var manager = {id: rowData.id};
                    var roles = [];
                    var nodes = $('#rolerTree').tree('getChecked');
                    console.log(nodes);
                    if (nodes.length > 0) {
                        for (var i=0;i < nodes.length; i++) {
                            roles.push({id: nodes[i].id})
                        }
                    }
                    manager.roles = roles;

                    $.ajax({
                        type: "POST",
                        url: "/sys/manager/role",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(manager),
                        success: function (jsonResult) {
                            if (jsonResult.code == 1) {
                                $.messager.alert("温馨提示", "操作成功<br/>", "info");
                                rolerDlg.dialog("close");
                            } else {
                                var msg = jsonResult.msg;
                                $.messager.alert("温馨提示", "操作失败<br/>" + msg, "error");
                            }
                        }
                    });
                },
                rolerCancel: function () {
                    rolerDlg.dialog("close");
                }
            };
            $(".easyui-linkbutton").bind("click", function () {
                var handler = $(this).attr("handler");
                cmdObj[handler]();
            });


            //5. 初始化组件
            //new Ext.grid({
            managerGrid.datagrid({
                title: "管理员信息管理",
                fit: true,
                border: false,
                url: "/sys/manager/list",
                toolbar:${"tb"},
                queryParams: {area_id: "", realName: ""},
                rownumbers: false,
                pagination: true,
                pageNumber: "1",
                pageSize: "10",
                pageList: [10, 20, 50],
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'id', title: '编号', width: 1},
                    {field: 'username', title: '用户名', width: 1},
                    {field: 'realName', title: '姓名', width: 1},
                    {field: 'sex', title: '性别', width: 1, formatter: genderFormatter},
                    {field: 'area', title: '区域', width: 1, formatter: areaFormatter},
                    {field: 'email', title: 'Email', width: 1},
                    {field: 'mobile', title: '电话', width: 1},
                    {field: 'addr', title: '地址', width: 1}
                ]]
            });

            managerDlg.dialog({
                width: 500,
                height: 200,
                title: "添加/编辑管理员",
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
            rolerDlg.dialog({
                width: 300,
                height: 500,
                title: "赋予角色",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "保存",
                        iconCls: "icon-save",
                        handler: cmdObj['rolerSave']
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cmdObj['rolerCancel']
                    },
                ]

            });
            $("#rolerTree").tree({
                method: "GET",
                url: "/system/menu",
                animate: true,
                checkbox: true,
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
        });

    </script>
</head>
<body>
<!-- 1. 数据表格 -->
<table id="managerGrid"></table>
<div id="tb">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" handler="create">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" handler="edit">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" handler="roler">赋予角色</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" handler="del">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="refresh">刷新</a>
    </div>
    <div>
        姓名: <input id="realName" name="realName" class="easyui-validatebox">
        <%--区域:  <select id="areaTree" class="easyui-combobox" panelHeight="auto" style="width:100px">--%>

        区域:<input id="areaTree" class="easyui-combobox" name="area_id" method="GET" ,
                  data-options="valueField:'id',textField:'areaName',url:'/sys/area/list'"/>
        <%--<option value="java">Java</option>--%>
        <%--<option value="c">C</option>--%>
        <%--<option value="basic">Basic</option>--%>
        <%--<option value="perl">Perl</option>--%>
        <%--<option value="python">Python</option>--%>
        </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" handler="search">搜索</a>
    </div>
</div>
<!-- 2. 添加/编辑对话框 -->
<div id="managerDlg">
    <form id="managerForm" method="post">
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
            </tr>
        </table>
    </form>
</div>
<div id="rolerDlg">
    <ul id="rolerTree"></ul>
</div>
</body>
</html>