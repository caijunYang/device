<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>机房管理</title>
    <!-- 1.引入外部资源 -->
    <%@ include file="/js/common.jsp" %>
    <!-- 2.声明页面常用的组件 -->
    <!-- 3.在页面加载完毕后,缓存这些组件 -->
    <!-- 4.声明一个"命令管理对象",声明需要提供的函数支持 -->
    <!-- 5.初始化页面组件 -->

    <script type="text/javascript">

        function areaFormatter(val, row, index) {
            if (val) {
                return val.areaName;
            }
            return "";
        }

        $(function () {
            // 3.在页面加载完毕后,缓存这些组件
            // 2.声明页面常用的组件
            var roomGrid, roomDlg, roomForm;
            roomGrid = $("#roomGrid");
            roomDlg = $("#roomDlg");
            roomForm = $("#roomForm");

            // 4.声明一个"命令管理对象",声明需要提供的函数支持
            var cmdObj = {
                create: function () {
                    //console.debug("create..");
                    // 清空表单,不能使用以下方法
                    // form的清空会波及到grid下分页栏的显示
                    $("#roomCode").removeAttr("readonly");
                    roomForm.form("clear");
                    roomDlg.dialog("open");

                },
                edit: function () {
                    //console.debug("edit..");
                    // 获取选中数据
                    var rowData = roomGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    // 清空表单
                    roomForm.form("clear");
                    // 打开面板
                    roomDlg.dialog("open");
                    if (rowData.area) {
                        rowData['area.id'] = rowData.area.id;
                    }
                    console.log(rowData)
                    // if(rowData.sex){
                    //     rowData[]
                    // }
                    // 数据加载处理
                    // 加载数据
                    roomForm.form("load", rowData);
                    $("#roomCode").attr("readonly", true);
                },
                del: function () {
                    var rowData = roomGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                        if (r) {
                            if (rowData.id) {
                                $.ajax({
                                    type: "GET",
                                    url: "/dev/engineRoom/delete/" + rowData.id,
                                    contentType: "application/json",
                                    dataType: "json",
                                    success: function (jsonResult) {
                                        if (jsonResult.code == 1) {
                                            roomGrid.datagrid("reload");
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
                    var queryParams = $('#roomGrid').datagrid('options').queryParams;
                    queryParams.roomName = "";
                    queryParams.area_id = "";
                    roomGrid.datagrid("reload");
                },
                save: function () {
                    var datas = formValues($("#roomForm"));
                    if (datas["area.id"]) {
                        datas.area = {id: datas["area.id"]};
                    }
                    $.ajax({
                        type: "POST",
                        url: "/dev/engineRoom/update",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(datas),
                        success: function (jsonResult) {
                            if (jsonResult.code == 1) {
                                roomDlg.dialog("close");
                                roomGrid.datagrid("reload");
                            } else {
                                var msg = jsonResult.msg;
                                $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                            }
                        }
                    });
                },
                cancel: function () {
                    roomDlg.dialog("close");
                },
                search: function () {
                    var queryParams = $('#roomGrid').datagrid('options').queryParams;
                    var roomName = $("#roomName").val();
                    var areaValue = $("#areaTree").combobox("getValue");
                    queryParams.roomName = roomName;
                    queryParams.area_id = areaValue;
                    $("#dg").datagrid('reload');
                    roomGrid.datagrid("reload");
                }
            };
            $(".easyui-linkbutton").bind("click", function () {
                var handler = $(this).attr("handler");
                cmdObj[handler]();
            });


            //5. 初始化组件
            //new Ext.grid({
            roomGrid.datagrid({
                title: "机房信息管理",
                fit: true,
                border: false,
                url: "/dev/engineRoom/list",
                toolbar:${"tb"},
                queryParams: {area_id: "", roomName: ""},
                rownumbers: false,
                pagination: true,
                pageNumber: "1",
                pageSize: "10",
                pageList: [10, 20, 50],
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'id', title: '编号', width: 1},
                    {field: 'roomCode', title: '机房编号', width: 1},
                    {field: 'deviceRoomName', title: '机房名称', width: 1},
                    {field: 'area', title: '区域', width: 1, formatter: areaFormatter}
                ]]
            });

            roomDlg.dialog({
                width: 500,
                height: 200,
                title: "添加/编辑机房",
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
        });

    </script>
</head>
<body>
<!-- 1. 数据表格 -->
<table id="roomGrid"></table>
<div id="tb">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" handler="create">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" handler="edit">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" handler="del">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="refresh">刷新</a>
    </div>
    <div>
        名称: <input id="roomName" name="roomName" class="easyui-validatebox">
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
<div id="roomDlg">
    <form id="roomForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    机房编号:<input id="roomCode" name="roomCode" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    机房名称:<input name="deviceRoomName" class="easyui-validatebox" required="true">
                </td>
            </tr>

            <tr>

                <td>
                    区&emsp;&emsp;域: <input id="areaSel" class="easyui-combobox" name="area.id" method="GET" ,
                                           data-options="valueField:'id',textField:'areaName',url:'/sys/area/list'"
                                           style="width:150px;"/>
                </td>
                <td>
                    描&emsp;&emsp;述:<input name="roomDesc" class="easyui-validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>