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
        $(function () {
            // 3.在页面加载完毕后,缓存这些组件
            // 2.声明页面常用的组件
            var logGrid, logDlg, logForm;
            logGrid = $("#logGrid");
            logDlg = $("#logDlg");
            logForm = $("#logForm");

            // 4.声明一个"命令管理对象",声明需要提供的函数支持
            var cmdObj = {
                showInfo: function () {
                    //console.debug("edit..");
                    // 获取选中数据
                    var rowData = logGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    // 清空表单
                    logForm.form("clear");
                    // 打开面板
                    logDlg.dialog("open");
                    if (rowData.area) {
                        rowData['area.id'] = rowData.area.id;
                    }
                    console.log(rowData)
                    // if(rowData.sex){
                    //     rowData[]
                    // }
                    // 数据加载处理
                    // 加载数据
                    logForm.form("load", rowData);
                    $("#username").attr("readonly", true);
                },
                cancel:function(){
                    logDlg.dialog("close");
                },
                search: function () {
                    var queryParams = $('#logGrid').datagrid('options').queryParams;
                    var name = $("#name").val();
                    var content = $("#content").val();
                    queryParams.name = name;
                    queryParams.content = content;
                    $("#dg").datagrid('reload');
                    logGrid.datagrid("reload");
                }
            };
            $(".easyui-linkbutton").bind("click", function () {
                var handler = $(this).attr("handler");
                cmdObj[handler]();
            });


            //5. 初始化组件
            //new Ext.grid({
            logGrid.datagrid({
                title: "日志管理",
                fit: true,
                border: false,
                url: "/sys/systemLog/list",
                toolbar:${"tb"},
                queryParams: {name: "", content: ""},
                rownumbers: false,
                pagination: true,
                pageNumber: "1",
                pageSize: "10",
                pageList: [10, 20, 50],
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'id', title: '编号', width: 1},
                    {field: 'name', title: '用户名', width: 1},
                    {field: 'loginip', title: '登录ip', width: 1},
                    {field: 'content', title: '操作内容', width: 1},
                    {field: 'accessUri', title: '访问路径', width: 1},
                    {field: 'createDate', title: '日期', width: 1}
                ]]
            });

            logDlg.dialog({
                width: 500,
                height: 300,
                title: "日志详情",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cmdObj['cancel']
                    }
                ]
            });
        });

    </script>
</head>
<body>
<!-- 1. 数据表格 -->
<table id="logGrid"></table>
<div id="tb">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" handler="showInfo">详情</a>
    </div>
    <div>
        用户名: <input id="name" name="name" class="easyui-validatebox">
        操作: <input id="content" name="content" class="easyui-validatebox">
        <%--区域:  <select id="areaTree" class="easyui-combobox" panelHeight="auto" style="width:100px">--%>
        </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" handler="search">搜索</a>
    </div>
</div>
<!-- 2. 添加/编辑对话框 -->
<div id="logDlg">
    <form id="logForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    用户名称:<input  name="name" class="easyui-validatebox" readonly="readonly">&emsp;
                </td>
                <td>
                    登录的ip:<input name="loginip" class="easyui-validatebox" readonly="readonly">
                </td>
            </tr>
            <tr style="margin-top: 50%;padding-top: 40px">
                <td>
                    日志代码:<input name="permissionCode" class="easyui-validatebox" readonly="readonly">&emsp;
                </td>
                <td>
                    日志内容:<input name="content" class="easyui-validatebox" readonly="readonly">
                </td>
            </tr>
            <tr style="margin-top: 50%;padding-top: 40px">
                <td>
                    访问时间:<input name="createDate" class="easyui-validatebox" readonly="readonly">&emsp;
                </td>
                <td>
                    访问路径:<input name="accessUri" class="easyui-validatebox" readonly="readonly">
                </td>
            </tr>
            <tr style="margin-top: 50%;padding-top: 40px">

                <td colspan="2">
                    访问参数:<input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px;height:30px;">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>