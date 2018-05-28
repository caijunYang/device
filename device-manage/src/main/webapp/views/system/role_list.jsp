<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	$(function(){
		// 2.声明页面常用的组件 
		var roleGrid,roleDlg,roleForm,selectedPermissionGrid,allPermissionGrid;
		// 3.在页面加载完毕后,缓存这些组件 
		roleGrid = $("#roleGrid");
		roleDlg = $("#roleDlg");
		roleForm = $("#roleForm");
		deptTree = $("#deptTree");
		allPermissionGrid = $("#allPermissionGrid");
		selectedPermissionGrid = $("#selectedPermissionGrid");
		
		// 4.声明一个"命令管理对象",声明需要提供的函数支持 
		var cmdObj = {
			create : function(){
				//console.debug("create..");
				// 清空表单,不能使用以下方法
				// form的清空会波及到grid下分页栏的显示
				//roleForm.form("clear");
                $("input[name=id],input[name=roleCode],input[name=roleName]").val('');
				selectedPermissionGrid.datagrid("loadData",{
					total:0,
					rows:[]
				});
				// 打开面板
				roleDlg.dialog("open");
				
			},
			edit : function(){
				//console.debug("edit..");
				// 获取选中数据
				var rowData = roleGrid.datagrid("getSelected");
				if(!rowData){
					$.messager.alert("温馨提示","请选中一行数据!!","info");
					return;
				}
				// 清空表单
				$("input[name=id],input[name=roleCode],input[name=roleName]").val('');
				selectedPermissionGrid.datagrid("loadData",{
					total:0,
					rows:[]
				});
				// 打开面板
				roleDlg.dialog("open");
				// 数据加载处理
				// 加载数据
				roleForm.form("load",rowData);
				selectedPermissionGrid.datagrid("loadData",{
					total:rowData.permissions.length,
					rows:rowData.permissions
				});
			},
			del : function(){
				console.debug("del..");
			},
			refresh : function(){
				//console.debug("refresh..");
				roleGrid.datagrid("reload");
			},
			save : function(){
                var datas = formValues($("#roleForm"));
                var permissions=[]
                var newData = selectedPermissionGrid.datagrid("getRows");
                console.log(newData);
                if(newData && newData.length){
                    $.each(newData,function(index,rowData){
                   	 var permi={id:rowData.id};
                   	 permissions.push(permi);
                   });
                }
                datas.permissions=permissions;
				console.log(datas);
                $.ajax({
                    type: "POST",
                    url: "/sys/role/update",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(datas),
                    success: function (jsonResult) {
                        if(jsonResult.code==1){
                            // 隐藏对话框
                            roleDlg.dialog("close");
                            // 成功提示
                            $.messager.alert("温馨提示","保存成功","info",function(){
                                // 刷新表格
                                roleGrid.datagrid("load");
                            });
                        }else{
                            $.messager.alert("温馨提示","数据保存失败!!<br/>"+jsonResult.message,"info",function(){
                                // 错误数据定位
                            });
                        }
                    }
                });
			},
			cancel : function(){
				roleDlg.dialog("close");
			}
		};
		
		//5. 初始化组件
		//new Ext.grid({
		roleGrid.datagrid({
				title:"角色信息管理" ,
				fit:true ,
				border:false ,
				url:"/sys/role/list",
				toolbar:[
				      {
				    	  text:"添加",
				    	  iconCls:"icon-add",
				    	  handler : cmdObj['create']
				      },
				      {
				    	  text:"编辑",
				    	  iconCls:"icon-edit",
				    	  handler : cmdObj['edit']
				      },
				      {
				    	  text:"删除 ",
				    	  iconCls:"icon-remove",
				    	  handler : cmdObj['del']
				      },
				      {
				    	  text:"刷新",
				    	  iconCls:"icon-reload",
				    	  handler : cmdObj['refresh']
				      }
	            ],
				rownumbers:false ,
				pagination:true,
				pageNumber:"1" , 
				pageSize:"10" ,
				pageList:[10,20,50],
				fitColumns:true ,
				singleSelect:true,
			    columns:[[    
			            {field:'roleCode',title:'角色编号',width:1},
			            {field:'roleName',title:'角色名称',width:1},
			        ]]    

		});
		
		roleDlg.dialog({
			width:930,
			height: 390,
			title:"添加/编辑角色面板" ,
			modal:true,
			closed:true,
			buttons:[
	         	{
					text:"保存",
					iconCls:"icon-save",
					handler:cmdObj['save']
				},
	         	{
					text:"取消",
					iconCls:"icon-cancel",
					handler:cmdObj['cancel']
				},
			]
			
		});
		
		roleForm.form({
			url:"/sys/role/update",
			onSubmit : function(param){
				//console.debug('param',param);
				// 获取选中grid中的数据
				/*
				*	然后按照指定格式向后台提交
				*   permissions[0].id=1
		            permissions[1].id=2		
				*/
				var newData = selectedPermissionGrid.datagrid("getRows");
				if(newData && newData.length){
					$.each(newData,function(index,rowData){
						param['permissions['+index+'].id']=rowData.id;
					});
				}
			},
			success:function(data){
				var rs = $.parseJSON(data);
				if(rs.success){
					// 隐藏对话框
					roleDlg.dialog("close");
					// 成功提示
					$.messager.alert("温馨提示",rs.message,"info",function(){
						// 刷新表格
						roleGrid.datagrid("load");
					});
				}else{
					$.messager.alert("温馨提示","数据保存失败!!<br/>"+rs.message,"info",function(){
						// 错误数据定位
					});
				}
			}
		});
		
		// 选中权限表格
		selectedPermissionGrid.datagrid({
				title:"已选权限" ,
				width: 450 ,
				height: 260 ,
				rownumbers:false ,
				fitColumns:true ,
				singleSelect:true,
				onDblClickRow:function(rowIndex, rowData){
					//获取左边的表格,把数据删除
					selectedPermissionGrid.datagrid("deleteRow",rowIndex);
				},
			    columns:[[
                    {field: 'id', title: '权限编号', width: 1},
                    {field: 'permissionCode', title: '权限代码', width: 1},
                    {field: 'permissionName', title: '权限名称', width: 1},
			        ]]    

		});
		// 待选权限表格
		allPermissionGrid.datagrid({
			title:"待选权限" ,
			width: 450 ,
			height: 260 ,
			url:"/sys/permission/allMenue",
			rownumbers:true ,
			pagination:true,
			pageNumber:1 , 
			pageSize:5 , 
			pageList:[5,10,20,50],
            columns: [[
                {field: 'id', title: '权限编号', width: 1},
                {field: 'permissionCode', title: '权限代码', width: 1},
                {field: 'permissionName', title: '权限名称', width: 1},
			]],
			onDblClickRow:function(rowIndex, rowData){
				//console.debug(rowData);
				// 检查待新增权限是否已经存在
				var rows = selectedPermissionGrid.datagrid("getRows");
				var flag= false;
				$.each(rows,function(index,row){
					if(row.id==rowData.id){
						//$.messager.alert("温馨提示","请不要重复选中【<font color='red'>"+rowData.name+"</font>】权限");						
						 $.messager.show({
							title:'温馨提示',
							msg:"请不要重复选中【<font color='red'>"+rowData.permissionName+"</font>】权限",
							showType:'fade',
							timeout:1000,
							style:{
							right:'',
							bottom:''
							}
						});
						
						flag = true;
						return;						
					}
				});
				if(flag){// 重复，则退出添加
					return;
				}
				
				//获取左边的表格,把数据添加过去
				selectedPermissionGrid.datagrid("appendRow",rowData);
			},
			fitColumns:true ,
			singleSelect:true

	});
	});
	</script>
</head>
<body>
	<!-- 1. 数据表格 -->
	<table id="roleGrid"></table>
	<!-- 2. 添加/编辑对话框 -->
	<div id="roleDlg">
		<form id="roleForm" method="post">
			<input type="hidden" name="id">
			<table  style="margin-top: 10px;">
				<tr>
					<td colspan="2">
						角色编号:<input name="roleCode" class="easyui-validatebox" data-options="required:true,validType:'english'" >&emsp;
						角色名称:<input name="roleName" class="easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td>
						<table id="selectedPermissionGrid"></table>
					</td>
					<td>
						<table id="allPermissionGrid"></table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>