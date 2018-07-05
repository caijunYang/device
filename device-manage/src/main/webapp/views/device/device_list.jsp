<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>设备管理</title>
    <!-- 1.引入外部资源 -->
    <%@ include file="/js/common.jsp" %>
    <!-- 2.声明页面常用的组件 -->
    <!-- 3.在页面加载完毕后,缓存这些组件 -->
    <!-- 4.声明一个"命令管理对象",声明需要提供的函数支持 -->
    <!-- 5.初始化页面组件 -->

    <script type="text/javascript">
        var openGrid;

        function engineFormater(val, row, index) {
            if (val && val.deviceRoomName) {
                return val.deviceRoomName;
            }
            return "";
        }

        function deivceTypeFormater(val, row, index) {
            switch (val) {
                case "ORDINARY":
                    return "普通传输设备";
                    break;
                case "OLT":
                    return "OLT传输设备";
                    break;
                case "BBU":
                    return "无线传输设备";
                    break;
                case "IPRAN":
                    return "4G传输设备";
                    break;
                default:
                    return "";
            }
        }

        function areaFormatter(val, row, index) {
            if (val) {
                return val.areaName;
            }
            return "";
        }

        $(function () {
            // 3.在页面加载完毕后,缓存这些组件
            // 2.声明页面常用的组件
            var deviceGrid, deviceDlg, deviceForm, ordinaryDlg, oltDlg, bbuDlg, ipRanDlg, ordinaryGrid, oltGrid,
                bbuGrid, ipRanGrid, importDlg, ordinaryEditDlg, oltEditDlg, bbuEditDlg, ipRanEditDlg
                , ordinaryEditForm, oltEditForm, bbuEditForm, ipRanEditForm;
            deviceGrid = $("#deviceGrid");
            deviceDlg = $("#deviceDlg");
            deviceForm = $("#deviceForm");
            ordinaryDlg = $("#ordinaryDlg");
            oltDlg = $("#oltDlg");
            bbuDlg = $("#bbuDlg");
            ipRanDlg = $("#ipRanDlg");
            ordinaryGrid = $("#ordinaryGrid");
            oltGrid = $("#oltGrid");
            bbuGrid = $("#bbuGrid");
            ipRanGrid = $("#ipRanGrid");
            importDlg = $("#importDlg");
            ordinaryEditDlg = $("#ordinaryEditDlg");
            oltEditDlg = $("#oltEditDlg");
            bbuEditDlg = $("#bbuEditDlg");
            ipRanEditDlg = $("#ipRanEditDlg");
            ordinaryEditForm = $("#ordinaryEditForm");
            oltEditForm = $("#oltEditForm");
            bbuEditForm = $("#bbuEditForm");
            ipRanEditForm = $("#ipRanEditForm");

            // 4.声明一个"命令管理对象",声明需要提供的函数支持
            var cmdObj = {
                create: function () {
                    //console.debug("create..");
                    // 清空表单,不能使用以下方法
                    // form的清空会波及到grid下分页栏的显示
                    $("#deviceCode").removeAttr("readonly");
                    deviceForm.form("clear");
                    deviceDlg.dialog("open");

                },
                edit: function () {
                    //console.debug("edit..");
                    // 获取选中数据
                    var rowData = deviceGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    // 清空表单
                    deviceForm.form("clear");
                    // 打开面板
                    deviceDlg.dialog("open");
                    if (rowData.area) {
                        rowData['area.id'] = rowData.area.id;
                    }
                    console.log(rowData)
                    // if(rowData.sex){
                    //     rowData[]
                    // }
                    // 数据加载处理
                    // 加载数据
                    deviceForm.form("load", rowData);
                    $("#deviceCode").attr("readonly", true);
                },
                del: function () {
                    var rowData = deviceGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                        if (r) {
                            if (rowData.id) {
                                $.ajax({
                                    type: "GET",
                                    url: "/dev/device/delete/" + rowData.id,
                                    contentType: "application/json",
                                    dataType: "json",
                                    success: function (jsonResult) {
                                        if (jsonResult.code == 1) {
                                            deviceGrid.datagrid("reload");
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
                    var queryParams = $('#deviceGrid').datagrid('options').queryParams;
                    var realName = $("#realName").val();
                    queryParams.deviceName = "";
                    queryParams.area_id = "";
                    queryParams.engineRoom_id = "";
                    queryParams.deviceType = "";
                    deviceGrid.datagrid("reload");
                },
                save: function () {
                    var datas = formValues($("#deviceForm"));
                    if (datas["engineRoom.id"]) {
                        datas.engineRoom = {id: datas["engineRoom.id"]};
                    }
                    $.ajax({
                        type: "POST",
                        url: "/dev/device/update",
                        contentType: "application/json",
                        dataType: "json",
                        data: JSON.stringify(datas),
                        success: function (jsonResult) {
                            if (jsonResult.code == 1) {
                                deviceDlg.dialog("close");
                                deviceGrid.datagrid("reload");
                            } else {
                                var msg = jsonResult.msg;
                                $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                            }
                        }
                    });
                },
                cancel: function () {
                    deviceDlg.dialog("close");
                },
                search: function () {
                    var queryParams = $('#deviceGrid').datagrid('options').queryParams;
                    var deviceName = $("#deviceName").val();
                    var areaValue = $("#areaTree").combobox("getValue");
                    var deviceType = $("#deviceType").combobox("getValue");
                    var engineValue = $("#engineRoomTree").combobox("getValue");
                    queryParams.deviceName = deviceName;
                    queryParams.area_id = areaValue;
                    queryParams.engineRoom_id = engineValue;
                    queryParams.deviceType = deviceType;
                    $("#dg").datagrid('reload');
                    deviceGrid.datagrid("reload");
                },
                importDlgOpen: function () {
                    importDlg.dialog("open");
                },
                download: function () {
                    window.location.href = "/dev/device/downloadTemplate"
                },
                importData: function () {
                    $("#uploadForm").form("submit", {
                            url: "/dev/device/importData",
                            success: function (data) {
                                var rs = $.parseJSON(data);
                                if (rs.code == 1) {// 登陆成功
                                    // 通过js动态跳转
                                    deviceGrid.datagrid("reload");
                                    $.messager.alert("温馨提示", "导入成功", "success");
                                    $("#importDlg").dialog("close");
                                    $("#uploadForm").form("clear");
                                } else {
                                    $.messager.alert("温馨提示", "登陆失败<br/>" + rs.msg, "error");
                                }
                            }
                        }
                    );
                },
                cancelImport: function () {
                    importDlg.dialog("close");
                    $("#uploadForm").form("clear");
                },
                exportDevice: function () {
                    var rowData = deviceGrid.datagrid("getSelected");
                    if (!rowData) {
                        $.messager.alert("温馨提示", "请选中一行数据!!", "info");
                        return;
                    }
                    var id = rowData.id;
                    window.location.href = "/dev/device/export/" + id
                }
            };
            $(".easyui-linkbutton").bind("click", function () {
                var handler = $(this).attr("handler");
                cmdObj[handler]();
            });


            //5. 初始化组件
            //new Ext.grid({
            deviceGrid.datagrid({
                title: "设备信息管理",
                fit: true,
                border: false,
                url: "/dev/device/list",
                toolbar: $("#tb"),
                queryParams: {area_id: "", deviceName: "", deviceType: "", engineRoom_id: ""},
                rownumbers: false,
                pagination: true,
                pageNumber: "1",
                pageSize: "10",
                pageList: [10, 20, 50],
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'id', title: '设备ID', width: 1},
                    {field: 'deviceCode', title: '设备编号', width: 1},
                    {field: 'deviceName', title: '名称', width: 1},
                    {field: 'deviceType', title: '设备类型', width: 1, formatter: deivceTypeFormater},
                    {field: 'area', title: '区域', width: 1, formatter: areaFormatter},
                    {field: 'engineRoom', title: '机房', width: 1, formatter: engineFormater},
                    {field: 'deviceModel', title: '本段设备型号', width: 1},
                    {field: 'deviceFrame', title: '本端所在机架号', width: 1},
                    {field: 'snCode', title: 'SN码（pran独有）', width: 1}
                ]],
                onDblClickRow: function (index, row) {
                    if (row && row.deviceType && row.id) {
                        showDlg(row.deviceType, row.id, row.deviceName);
                    }
                }
            });
            importDlg.dialog({
                width: 500,
                height: 200,
                title: "添加/编辑设备",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "导入",
                        iconCls: "icon-save",
                        handler: cmdObj['importData']
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cmdObj['cancelImport']
                    },
                ]
            });
            deviceDlg.dialog({
                width: 500,
                height: 200,
                title: "添加/编辑设备",
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
            ordinaryDlg.dialog({
                width: 1000,
                height: 600,
                title: "普通传输设备",
                modal: true,
                closed: true,
                onOpen: function () {
                    openGrid = "ORDINARY";
                }
            });
            oltDlg.dialog({
                width: 1000,
                height: 600,
                title: "OLT传输设备",
                modal: true,
                closed: true,
                onOpen: function () {
                    openGrid = "OLT";
                }
            });
            bbuDlg.dialog({
                width: 1000,
                height: 600,
                title: "无线传输设备",
                modal: true,
                closed: true,
                onOpen: function () {
                    openGrid = "BBU";
                }
            });
            ipRanDlg.dialog({
                width: 1000,
                height: 600,
                title: "4G上网设备",
                modal: true,
                closed: true,
                onOpen: function () {
                    openGrid = "IPRAN";
                }
            });
            ordinaryGrid.datagrid({
                title: "普通传输设备",
                fit: true,
                border: false,
                url: "/dev/ordinaryInfo/list",
                queryParams: {device_id: ""},
                pageSize: "10",
                pagination: true,
                rownumbers: true,
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'port', title: '端口', width: 1},
                    {field: 'fiberFrameAddr', title: '跳纤架位置', width: 1},
                    {field: 'fiberFramePort', title: '跳纤架子端口', width: 1},
                    {field: 'targetDevice', title: '对端设备', width: 1},
                    {field: 'targetDeviceModel', title: '对端设备型号', width: 1},
                    {field: 'physicalPort', title: '对端设备物理端口', width: 1},
                    {field: 'serviceName', title: '业务名称', width: 1}
                ]],
                onRowContextMenu: function (e, rowIndex, rowData) {
                    e.preventDefault();
                    $(this).datagrid("clearSelections");
                    $(this).datagrid("selectRow", rowIndex);
                    $.data(document.body, "selectDatas", rowData);
                    ;//将右击选中的某行数据放在缓存中
                    $('#editMenu').menu('show', {
                        left: e.pageX,         //弹出窗口的方位坐标N
                        top: e.pageY
                    });
                }
            });
            oltGrid.datagrid({
                title: "OLT传输设备",
                fit: true,
                border: false,
                url: "/dev/oltInfo/list",
                queryParams: {device_id: ""},
                pageSize: "10",
                pagination: true,
                rownumbers: true,
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'port', title: '端口', width: 1},
                    {field: 'fiberFrameAddr', title: '对应跳纤架（ODF）', width: 1},
                    {field: 'fiberFramePort', title: '跳纤架框槽端子（ODFB面）', width: 1},
                    {field: 'targetDevice', title: '对端设备', width: 1},
                    {field: 'physicalPort', title: '对端设备端口', width: 1},
                    {field: 'opticalName', title: '光缆名称', width: 1},
                    {field: 'opticalCore', title: '纤芯占用', width: 1},
                    {field: 'serviceName', title: '业务名称', width: 1},
                    {field: 'lable', title: '标签/人工', width: 1}
                ]],
                onRowContextMenu: function (e, rowIndex, rowData) {
                    e.preventDefault();
                    $(this).datagrid("clearSelections");
                    $(this).datagrid("selectRow", rowIndex);
                    $.data(document.body, "selectDatas", rowData);
                    ;//将右击选中的某行数据放在缓存中
                    $('#editMenu').menu('show', {
                        left: e.pageX,         //弹出窗口的方位坐标
                        top: e.pageY
                    });
                }
            });
            bbuGrid.datagrid({
                title: "无线传输设备",
                fit: true,
                border: false,
                url: "/dev/bbuDeviceInfo/list",
                queryParams: {device_id: ""},
                pageSize: "10",
                pagination: true,
                rownumbers: true,
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'serialNo', title: '序列号', width: 1},
                    {field: 'port', title: '端口', width: 1},
                    {field: 'fiberFrameAddr', title: '对应跳纤架（ODF）', width: 1},
                    {field: 'fiberFramePort', title: '跳纤架框槽端子（ODFB面）', width: 1},
                    {field: 'targetDevice', title: '对端设备', width: 1},
                    {field: 'targetDeviceModel', title: '对端设备型号', width: 1},
                    {field: 'targetFiberFrame', title: ' 对端设备架框', width: 1},
                    {field: 'physicalPort', title: '物理端口', width: 1},
                    {field: 'serviceName', title: '业务名称', width: 1},
                    {field: 'context', title: '描述', width: 1}
                ]],
                onDblClickCell: function (index, field, value) {
                    $(this).datagrid('beginEdit', index);
                    var ed = $(this).datagrid('getEditor', {index: index, field: field});
                    $(ed.target).focus();
                },
                onRowContextMenu: function (e, rowIndex, rowData) {
                    e.preventDefault();
                    $(this).datagrid("clearSelections");
                    $(this).datagrid("selectRow", rowIndex);
                    $.data(document.body, "selectDatas", rowData);//将右击选中的某行数据放在缓存中
                    $('#editMenu').menu('show', {
                        left: e.pageX,         //弹出窗口的方位坐标
                        top: e.pageY
                    });
                }
            });
            ipRanGrid.datagrid({
                title: "4G上网设备",
                fit: true,
                border: false,
                url: "/dev/ipRanInfo/list",
                queryParams: {device_id: ""},
                pageSize: "10",
                pagination: true,
                rownumbers: true,
                fitColumns: true,
                singleSelect: true,
                columns: [[
                    {field: 'port', title: '端口', width: 1},
                    {field: 'fiberFrameAddr', title: '对应跳纤架（ODF）', width: 1},
                    {field: 'fiberFramePort', title: '跳纤架框槽端子（ODFB面）', width: 1},
                    {field: 'targetDevice', title: '对端设备', width: 1},
                    {field: 'targetDeviceModel', title: '对端设备型号', width: 1},
                    {field: 'targetFiberFrame', title: ' 对端设备架框', width: 1},
                    {field: 'physicalPort', title: '物理端口', width: 1},
                    {field: 'serviceName', title: '业务名称', width: 1},
                ]],
                onRowContextMenu: function (e, rowIndex, rowData) {
                    e.preventDefault();
                    $(this).datagrid("clearSelections");
                    $(this).datagrid("selectRow", rowIndex);
                    $.data(document.body, "selectDatas", rowData);
                    ;//将右击选中的某行数据放在缓存中
                    $('#editMenu').menu('show', {
                        left: e.pageX,         //弹出窗口的方位坐标
                        top: e.pageY
                    });
                }
            });


            function showDlg(deviceType, id, deviceName) {
                switch (deviceType) {
                    case "ORDINARY":
                        ordinaryDlg.dialog("open");
                        var queryParams = $('#ordinaryGrid').datagrid('options').queryParams;
                        queryParams.device_id = id;
                        $("#ordinaryGrid").datagrid({title: deviceName});
                        $("#ordinaryGrid").datagrid('reload');
                        break;
                    case "OLT":
                        oltDlg.dialog("open");
                        var queryParams = $('#oltGrid').datagrid('options').queryParams;
                        queryParams.device_id = id;
                        $("#oltGrid").datagrid({title: deviceName});
                        $("#oltGrid").datagrid('reload');
                        break;
                    case "BBU":
                        bbuDlg.dialog("open");
                        var queryParams = $('#bbuGrid').datagrid('options').queryParams;
                        queryParams.device_id = id;
                        $("#bbuGrid").datagrid({title: deviceName});
                        // $("#bbuGrid").dialog({title: deviceName});
                        $("#bbuGrid").datagrid('reload');
                        break;
                    case "IPRAN":
                        ipRanDlg.dialog("open");
                        var queryParams = $('#ipRanGrid').datagrid('options').queryParams;
                        queryParams.device_id = id;
                        $("#ipRanGrid").datagrid({title: deviceName});
                        $("#ipRanGrid").datagrid('reload');
                        break;
                    default:
                        return
                }
            }

            $("#editSelData").bind("click", function () {
                // var cacheData = $.data(document.body, "selectDatas");
                switch (openGrid) {
                    case "ORDINARY":
                        var data = $("#ordinaryGrid").datagrid('getSelected');
                        ordinaryEditForm.form("clear");
                        ordinaryEditForm.form("load", data);
                        ordinaryEditDlg.dialog("open");
                        console.log(data);
                        break;
                    case "OLT":
                        var data = $("#oltGrid").datagrid('getSelected');
                        console.log(data);
                        oltEditForm.form("clear");
                        oltEditForm.form("load", data);
                        oltEditDlg.dialog("open");
                        break;
                    case "BBU":
                        var data = $("#bbuGrid").datagrid('getSelected');
                        console.log(data);
                        bbuEditForm.form("clear");
                        bbuEditForm.form("load", data);
                        bbuEditDlg.dialog("open");
                        break;
                    case "IPRAN":
                        var data = $("#ipRanGrid").datagrid('getSelected');
                        ipRanEditForm.form("clear");
                        ipRanEditForm.form("load", data);
                        ipRanEditDlg.dialog("open");
                        console.log(data);
                        break;
                }
            })
            $("#removeSelData").bind("click", function () {
                switch (openGrid) {
                    case "ORDINARY":
                        var data = $("#ordinaryGrid").datagrid('getSelected');
                        $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                            if (r) {
                                if (data.id) {
                                    $.ajax({
                                        type: "GET",
                                        url: "/dev/ordinaryInfo/delete/" + data.id,
                                        contentType: "application/json",
                                        dataType: "json",
                                        success: function (jsonResult) {
                                            if (jsonResult.code == 1) {
                                                ordinaryGrid.datagrid("reload");
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
                        break;
                    case "OLT":
                        var data = $("#oltGrid").datagrid('getSelected');
                        $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                            if (r) {
                                if (data.id) {
                                    $.ajax({
                                        type: "GET",
                                        url: "/dev/oltInfo/delete/" + data.id,
                                        contentType: "application/json",
                                        dataType: "json",
                                        success: function (jsonResult) {
                                            if (jsonResult.code == 1) {
                                                oltGrid.datagrid("reload");
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
                        break;
                    case "BBU":
                        var data = $("#bbuGrid").datagrid('getSelected');
                        $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                            if (r) {
                                if (data.id) {
                                    $.ajax({
                                        type: "GET",
                                        url: "/dev/bbuDeviceInfo/delete/" + data.id,
                                        contentType: "application/json",
                                        dataType: "json",
                                        success: function (jsonResult) {
                                            if (jsonResult.code == 1) {
                                                bbuGrid.datagrid("reload");
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
                        break;
                    case "IPRAN":
                        var data = $("#ipRanGrid").datagrid('getSelected');
                        $.messager.confirm('确认提示', '确认要删除选中数据吗?', function (r) {
                            if (r) {
                                if (data.id) {
                                    $.ajax({
                                        type: "GET",
                                        url: "/dev/ipRanInfo/delete/" + data.id,
                                        contentType: "application/json",
                                        dataType: "json",
                                        success: function (jsonResult) {
                                            if (jsonResult.code == 1) {
                                                ipRanGrid.datagrid("reload");
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
                        break;
                }

                switch (openGrid) {
                    case "ORDINARY":

                        break;
                    case "OLT":
                        break;
                    case "BBU":
                        break;
                    case "IPRAN":
                        break;
                }
            })

            ordinaryEditDlg.dialog({
                width: 700,
                height: 400,
                title: "OLT传输设备",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "保存",
                        iconCls: "icon-save",
                        handler: saveOrdinary
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cancelOrdinary
                    },
                ]
            });
            oltEditDlg.dialog({
                width: 700,
                height: 400,
                title: "OLT传输设备",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "保存",
                        iconCls: "icon-save",
                        handler: saveOlt
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cancelOlt
                    },
                ]
            });
            bbuEditDlg.dialog({
                width: 700,
                height: 400,
                title: "OLT传输设备",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "保存",
                        iconCls: "icon-save",
                        handler: saveBbu
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cancelBbu
                    },
                ]
            });
            ipRanEditDlg.dialog({
                width: 700,
                height: 400,
                title: "OLT传输设备",
                modal: true,
                closed: true,
                buttons: [
                    {
                        text: "保存",
                        iconCls: "icon-save",
                        handler: saveIpRan
                    },
                    {
                        text: "取消",
                        iconCls: "icon-cancel",
                        handler: cancelIpRan
                    }
                ]
            });

            function saveOrdinary() {
                var datas = formValues($("#ordinaryEditForm"));
                $.ajax({
                    type: "POST",
                    url: "/dev/ordinaryInfo/update",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(datas),
                    success: function (jsonResult) {
                        if (jsonResult.code == 1) {
                            ordinaryEditDlg.dialog("close");
                            ordinaryGrid.datagrid("reload");
                        } else {
                            var msg = jsonResult.msg;
                            $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                        }
                    }
                });
            }

            function cancelOrdinary() {
                ordinaryEditDlg.dialog("close");
            }

            function saveOlt() {
                var datas = formValues($("#oltEditForm"));
                $.ajax({
                    type: "POST",
                    url: "/dev/oltInfo/update",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(datas),
                    success: function (jsonResult) {
                        if (jsonResult.code == 1) {
                            oltEditDlg.dialog("close");
                            oltGrid.datagrid("reload");
                        } else {
                            var msg = jsonResult.msg;
                            $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                        }
                    }
                });
            }

            function cancelOlt() {
                oltEditDlg.dialog("close");
            }

            function saveBbu() {
                var datas = formValues($("#bbuEditForm"));
                $.ajax({
                    type: "POST",
                    url: "/dev/bbuDeviceInfo/update",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(datas),
                    success: function (jsonResult) {
                        if (jsonResult.code == 1) {
                            bbuEditDlg.dialog("close");
                            bbuGrid.datagrid("reload");
                        } else {
                            var msg = jsonResult.msg;
                            $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                        }
                    }
                });
            }

            function cancelBbu() {
                bbuEditDlg.dialog("close");
            }

            function saveIpRan() {
                var datas = formValues($("#ipRanEditForm"));
                $.ajax({
                    type: "POST",
                    url: "/dev/ipRanInfo/update",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(datas),
                    success: function (jsonResult) {
                        if (jsonResult.code == 1) {
                            ipRanEditDlg.dialog("close");
                            ipRanGrid.datagrid("reload");
                        } else {
                            var msg = jsonResult.msg;
                            $.messager.alert("温馨提示", "保存失败<br/>" + msg, "error");
                        }
                    }
                });
            }

            function cancelIpRan() {
                ipRanEditDlg.dialog("close");
            }
        });

    </script>
</head>
<body>
<!-- 1. 数据表格 -->
<table id="deviceGrid"></table>
<div id="tb">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" handler="create">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" handler="edit">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" handler="del">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="exportDevice">导出设备</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="importDlgOpen">导入设备</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="refresh">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" handler="download">下载导入模板</a>
    </div>
    <div>
        名称: <input id="deviceName" name="deviceName" class="easyui-validatebox">
        设备类型:<select id="deviceType" class="easyui-combobox" name="deviceType" style="width:200px;">
        <option value="ORDINARY">普通传输设备</option>
        <option value="OLT">OLT传输设备</option>
        <option value="BBU">无线传输设备</option>
        <option value="IPRAN">4G传输设备</option>
    </select>
        区域:<input id="areaTree" class="easyui-combobox" name="area_id" method="GET" ,
                  data-options="valueField:'id',textField:'areaName',url:'/sys/area/list'"/>
        机房:<input id="engineRoomTree" class="easyui-combobox" name="engineRoom_id" method="GET" ,
                  data-options="valueField:'id',textField:'deviceRoomName',url:'/dev/engineRoom/list'"/>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" handler="search">搜索</a>
    </div>
</div>
<!-- 2. 添加/编辑对话框 -->
<div id="deviceDlg">
    <form id="deviceForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    设备编号:<input id="deviceCode" name="deviceCode" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    设备名称:<input name="deviceName" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr style="margin-top: 50%;padding-top: 40px">
                <td>
                    设备型号:<input name="deviceModel" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    所在机架:<input name="deviceFrame" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr style="margin-top: 50%;padding-top: 40px">
                <td>
                    SN &emsp; 码:<input name="snCode" class="easyui-validatebox" required="true">&emsp;
                </td>
            </tr>
            <tr>
                <td>
                    机&emsp;&emsp;房:<input class="easyui-combobox" name="engineRoom.id" method="GET" ,
                                          data-options="valueField:'id',textField:'deviceRoomName',url:'/dev/engineRoom/list'"/>
                </td>
                <td>
                    设备类型:
                    <select class="easyui-combobox" name="deviceType" style="width:170px;">
                        <option value="ORDINARY">普通传输设备</option>
                        <option value="OLT">OLT传输设备</option>
                        <option value="BBU">无线传输设备</option>
                        <option value="IPRAN">4G传输设备</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 设备导入 -->
<div id="importDlg">
    <div style="width: 100%;height: 100%">
        <form id="uploadForm" method="post" enctype="multipart/form-data">
            <table id="importTab">
                选择文件:<input name="file" class="easyui-validatebox" required="true" type="file">&emsp;
            </table>
        </form>
    </div>
</div>


<!-- ORDINARY("Ordinary 普通传输设备") -->
<div id="ordinaryDlg">
    <div style="width: 100%;height: 100%">
        <table id="ordinaryGrid"></table>
    </div>
</div>
<!-- OLT("OLT OLT传输设备") -->
<div id="oltDlg">
    <div style="width: 100%;height: 100%">
        <table id="oltGrid"></table>
    </div>
</div>
<!--    BBU("BBU无线传输设备") -->
<div id="bbuDlg">
    <div style="width: 100%;height: 100%">
        <table id="bbuGrid" fit="true"></table>
    </div>
</div>
<!-- IPRAN("IPRAN 4G传输设备") -->
<div id="ipRanDlg">
    <div style="width: 100%;height: 100%">
        <table id="ipRanGrid"></table>
    </div>
</div>
<div id="editMenu" class="easyui-menu" style="width:120px;">
    <div id="editSelData" data-options="iconCls:'icon-edit'">编辑</div>
    <div id="removeSelData" data-options="iconCls:'icon-remove'">删除</div>
</div>


<div id="ordinaryEditDlg">
    <form id="ordinaryEditForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    端 &emsp; &emsp; &emsp; 口:<input id="ordinary_port" name="port" class="easyui-validatebox"
                                                    required="true">&emsp;
                </td>
                <td>
                    跳 纤 架 位 置:<input name="fiberFrameAddr" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    跳 纤 架 端 口:<input name="fiberFramePort" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    对&emsp;端&emsp;设&nbsp;备:<input name="targetDevice" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    对端设备型号 :<input name="targetDeviceModel" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    对端设备架框 :<input name="targetFiberFrame" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    对端设备端口 :<input name="physicalPort" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    业&emsp;务&emsp;名&nbsp;称:<input name="serviceName" class="easyui-validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="oltEditDlg">
    <form id="oltEditForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    端 &emsp; &emsp; &emsp; 口:<input id="olt_port" name="port" class="easyui-validatebox"
                                                    required="true">&emsp;
                </td>
                <td>
                    跳 纤 架 位 置:<input name="fiberFrameAddr" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    跳 纤 架 端 口:<input name="fiberFramePort" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    对&emsp;端&emsp;设&nbsp;备:<input name="targetDevice" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    对端设备端口:<input name="physicalPort" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    光&emsp;缆&emsp;名&nbsp;称:<input name="opticalName" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    纤&nbsp;芯&emsp;占&nbsp;用 :<input name="opticalCore" class="easyui-validatebox" required="true">
                </td>
                <td>
                    业&emsp;务&emsp;名&nbsp;称:<input name="serviceName" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    标 签 / 人 工 :<input name="lable" class="easyui-validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="bbuEditDlg">
    <form id="bbuEditForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    序 &emsp; &emsp; &emsp; 号:<input name="serialNo" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    端 &emsp; &emsp; &emsp; 口:<input name="port" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    跳 纤 架 位 置:<input name="fiberFrameAddr" class="easyui-validatebox" required="true">
                </td>
                <td>
                    跳 纤 架 端 口:<input name="fiberFramePort" class="easyui-validatebox" required="true">&emsp;
                </td>
            </tr>
            <tr>
                <td>
                    对&nbsp;端&emsp;设&nbsp;备 :<input name="targetDevice" class="easyui-validatebox" required="true">
                </td>
                <td>
                    对端设备型号:<input name="targetDeviceModel" class="easyui-validatebox" required="true">&emsp;
                </td>
            </tr>
            <tr>
                <td>
                    对端设备架框:<input name="targetFiberFrame" class="easyui-validatebox" required="true">
                </td>
                <td>
                    对端设备端口:<input name="physicalPort" class="easyui-validatebox" required="true">&emsp;
                </td>
            </tr>
            <tr>
                <td>
                    业&nbsp;务&emsp;名&nbsp;称 :<input name="serviceName" class="easyui-validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="ipRanEditDlg">
    <form id="ipRanEditForm" method="post">
        <input type="hidden" name="id">
        <table style="margin-top: 10px;">
            <tr>
                <td>
                    端 &emsp; &emsp; &emsp; 口:<input name="port" class="easyui-validatebox" required="true">
                </td>
                <td>
                    跳 纤 架 位 置:<input name="fiberFrameAddr" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    跳 纤 架 端 口:<input name="fiberFramePort" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    对&nbsp;端&emsp;设&nbsp;备 :<input name="targetDevice" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    对端设备型号:<input name="targetDeviceModel" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    对端设备架框:<input name="targetFiberFrame" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>
                    对端设备端口:<input name="physicalPort" class="easyui-validatebox" required="true">&emsp;
                </td>
                <td>
                    业&nbsp;务&emsp;名&nbsp;称 :<input name="serviceName" class="easyui-validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>