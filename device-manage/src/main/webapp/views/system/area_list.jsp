<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>角色管理</title>
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
            var areaGrid, areaDlg, areaForm;
            areaGrid = $("#areaGrid");
            areaDlg = $("#areaDlg");
            areaForm = $("#areaForm");

            // 4.声明一个"命令管理对象",声明需要提供的函数支持
            var cmdObj = {
                create: function () {
                    //console.debug("create..");
                    // 清空表单,不能使用以下方法
                    // form的清空会波及到grid下分页栏的显示
                    $("#areaCode").removeAttr("readonly");
                    areaForm.form("clear");
                    areaDlg.dialog("open");

                },
                edit: function () {
                    //console.debug("edit..");
                    // 获取选中数据
                    var rowData = areaGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    // 清空表单
                    areaForm.form("clear");
                    // 打开面板
                    areaDlg.dialog("open");
                    if (rowData.area) {
                        rowData['area.id'] = rowData.area.id;
                    }
                    console.log(rowData)
                    // if(rowData.sex){
                    //     rowData[]
                    // }
                    // 数据加载处理
                    // 加载数据
                    areaForm.form("load", rowData);
                    $("#username").attr("readonly", true);
                },
                del: function () {
                    var rowData = areaGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                        if (r) {
                            if (rowData.id) {
                                $.ajax({
                                    type: "GET",
                                    url: "/sys/area/delete/" + rowData.id,
                                    contentType: "application/json",
                                    dataType: "json",
                                    success: function (jsonResult) {
                                        if (jsonResult.code == 1) {
                                            areaGrid.datagrid("reload");
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
                    var queryParams = $('#areaGrid').datagrid('options').queryParams;
                    var realName = $("#realName").val();
                    queryParams.areaName = "";
                    areaGrid.datagrid("reload");
                },
                save: function () {
                    var datas = formValues($("#areaForm"));
                    $.ajax({
                        type: "POST",
                        url: "/sys/area/update",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(datas),
                        success: function (jsonResult) {
                            if (jsonResult.code == 1) {
                                areaDlg.dialog("close");
                                areaGrid.datagrid("reload");
                            } else {
                                var msg = jsonResult.msg;
                                $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                            }
                        }
                    });
                },
                cancel: function () {
                    areaDlg.dialog("close");
                },
                search: function () {
                    var queryParams = $('#areaGrid').datagrid('options').queryParams;
                    var areaName = $("#areaName").val();
                    queryParams.areaName = areaName;

                    $("#dg").datagrid('reload');
                    areaGrid.datagrid("reload");
                }
            };
            $(".easyui-linkbutton").bind("click", function () {
                var handler = $(this).attr("handler");
                cmdObj[handler]();
            });



            //5. 初始化组件
            //new Ext.grid({
            areaGrid.datagrid({
                title: "区域信息管理",
                fit: true,
                border: false,
                url: "/sys/area/list",
                toolbar:${"tb"},
                queryParams: {areaName: ""},
                rownumbers: false,
                pagination: true,
                pageNumber: "1",
                pageSize: "10",
                pageList: [10, 20, 50],
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'id', title: '编号', width: 1},
                    {field: 'areaCode', title: '区域编号', width: 1},
                    {field: 'areaName', title: '区域名称', width: 1}
                ]]
            });

            areaDlg.dialog({
                width: 500,
                height: 200,
                title: "添加/编辑区域",
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

            areaForm.form({
                url: "/sys/area/update",
                onSubmit: function (param) {

                },
                success: function (data) {
                    var rs = $.parseJSON(data);
                    if (rs.success) {
                        // 隐藏对话框
                        areaDlg.dialog("close");
                        // 成功提示
                        $.messager.alert("温馨提示", rs.message, "info", function () {
                            // 刷新表格
                            areaGrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", "数据保存失败!!<br/>" + rs.message, "info", function () {
                            // 错误数据定位
                        });
                    }
                }
            });
        });
    </script>
</head>
<body>
<!-- 1. 数据表格 -->
<table id="areaGrid"></table>
<div id="tb">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" handler="create">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" handler="edit">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" handler="del">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="refresh">刷新</a>
    </div>
    <div>
        区域名称: <input id="areaName" name="areaName" class="easyui-validatebox">
        <%--区域:  <select id="areaTree" class="easyui-combobox" panelHeight="auto" style="width:100px">--%>
        </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" handler="search">搜索</a>
    </div>
</div>
<!-- 2. 添加/编辑对话框 -->
<div id="areaDlg">
    <form id="areaForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    区域编号:<input id="areaCode" name="areaCode" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    区域名称:<input name="areaName" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr style="margin-top: 50%;padding-top: 40px">
        </table>
    </form>
</div>
</body>
</html>