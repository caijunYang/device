<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<%@ include file="/js/common.jsp" %>
<script type="text/javascript">
	// 菜单按钮
	function create(){
		// 清空数据
		$("#employeeForm").form("clear");
		// 打开对话框
		$("#employeeDlg").dialog("open");
	}
	function edit(){
		// 选中行
		var rowData = $("#employeeGrid").datagrid("getSelected");
		if(!rowData){
			$.messager.alert("温馨提示","请选中一行数据","info");
			return;
		}
		// 显示对话框
		create();
		// 处理加载数据
		rowData['dept.id']=rowData.dept.id;
		//rowData['role.id']=[1,2];
		var roles = rowData.roles;
		var roleArr = [];
		for ( var i = 0; i < roles.length; i++) {
			var role = roles[i];
			roleArr[i]=role.id;
		}
		$('#roleCombo').combobox('setValues',roleArr);
		
		// 加载选中行数据
		$("#employeeForm").form("load",rowData);
	}
	function del(){
		//获取行数据
		var rowData = $("#employeeGrid").datagrid("getSelected");
		if(!rowData){
			$.messager.alert("温馨提示","请选中一行数据","info");
			return;
		}
		if(rowData.status==-1){
			$.messager.alert("温馨提示","请不要重复离职!!","info");
			return;
		}
		$.messager.confirm('温馨提示', '是否要让该用户离职?', function(ok){
			 if (ok){
				// 获取需要删除数据的唯一标示
					var id = rowData.id;
					// 发送ajax请求
					$.get("employee_remove.action",{"id":id},function(data){
						//console.debug(data);
						if(data.success){
							$.messager.alert("温馨提示","用户离职成功!!","info",function(){
								//$("#employeeGrid").datagrid("reload");
								refresh();
							});
						}
					},"json");
			 }
		});
	}
	function refresh(){
		$("#employeeGrid").datagrid("reload");
	}
	// 对话框按钮
	function save(){
		$("#employeeForm").form("submit",{
			url:"employee_save.action",
			success:function(data){
				// 转json对象
				var rs = $.parseJSON(data);
				if(rs.success){
					// 关闭对话框
					$("#employeeDlg").dialog("close");
					// 操作成功提示
					$.messager.alert("温馨提示",rs.message,"info",function(){
						// 刷新页面,回到第一页
						$("#employeeGrid").datagrid("reload");
					});
				}else{
					// 提示错误信息
					$.messager.alert("温馨提示","保存失败<br/>"+rs.message,"info",function(){
						// 定位错误栏
						if(rs.errorCode==-103){
							$("input[name=username]").focus();
						}
					});
				}
			}
			
		});
	}
	
	function cancel(){
		$("#employeeDlg").dialog("close");
	}
	// 搜索
	function doSearch(){
		// 获取查询表单
		//$("#employeeSearchForm")
		var form = $("#employeeSearchForm");
		// 获取查询参数
		console.debug($("#employeeSearchForm").serialize());
		console.debug($("#employeeSearchForm").serializeArray());
		// 把参数封装成json对象格式
		var paramObj = form.serializeJson();
		// 通过datagrid的load方法加载数据
		$("#employeeGrid").datagrid("load",paramObj);
		
	}
	
	// 员工状态格式化
	function statusFormatter(val, row, index){
		//console.debug(arguments);
		switch (val) {
		case -1:
			return "<font color='red'>离职</font>";
			break;

		default:
			return "<font color='green'>正常</font>";
			break;
		}
	}
	
	$(function(){
		var deptTree = $("#deptTree");
		deptTree.tree({
			url : "department_getTreeData.action",
			onClick : function(node){
				// 获取node上的id
				var pid = node.id;
				var text = node.text;
				var dirPath = node.attributes;
				// 把id作为过滤条件,去加载表格数据
				$("#employeeGrid").datagrid("load",{"query.dirPath":dirPath});
				
				// 在表格上缓存,当前查看的父部分信息
				//$("#employeeGrid").parent = {"id":pid,"text":text};
			}
		});
		var deptTreeCombo = $("#deptTreeCombo");
		deptTreeCombo.combotree({
			url : "department_getTreeData.action",
			required : true
		});
	});
</script>
</head>
<body>
<div class="easyui-layout" fit="true" border="false">
	<div data-options="region:'west',width:150" title="部门菜单树" headerCls="border-right-none" bodyCls="border-right-none">
		<ul id="deptTree"></ul>
	</div>
	<div data-options="region:'center'">
			<!-- 1.数据表格 -->
			<table id="employeeGrid" title="员工信息管理" class="easyui-datagrid"
				fit="true" 
				border="false"
				url="employee_list.action"
				toolbar="#employeeToolbar"  
				rownumbers="true"
				pagination="true"
				pageNumber="1" 
				pageSize="2" 
				pageList="[2,5,10,20,50]"
				
				fitColumns="true" 
				singleSelect="true">
				<thead>
					<tr>
						<th field="username" width="1">用户名</th>
						<th field="trueName" width="1">真实姓名</th>
						<th field="tel" width="1">电话</th>
						<th field="email" width="1">电邮</th>
						<th field="dept" width="1" formatter="objectFormatter">部门</th>
						<th field="inputTime" width="2">录入时间</th>
						<th field="lastLoginTime" width="2">上次登陆时间</th>
						<th field="lastLoginIp" width="2">上次登陆IP</th>
						<th field="status" width="2" formatter="statusFormatter">状态</th>
					</tr>
				</thead>
			</table>
			<!-- 2.数据表格的菜单按钮 -->
			 <div id="employeeToolbar" style="padding:5px;height:auto">
			 	  <div style="margin-bottom:5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="create()">新建</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refresh()">刷新</a>
				   </div>
				   <div>
				       <form id="employeeSearchForm" method="post">
			            	关键字:<input name="query.searchKey" style="width: 100px;">
							最后登录时间: <input name="query.lastLoginTime1"  class="easyui-datebox" style="width:120px">
							到: <input name="query.lastLoginTime2" class="easyui-datebox" style="width:120px">
							状态:
							<select name="query.status" class="easyui-combobox" panelHeight="auto" style="width:100px">
							<option value="">---请选择---</option>
							<option value="0">正常</option>
							<option value="-1">离职</option>
							</select>
							<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch();">查询</a>
				       </form>
					</div>
			</div>
			<!-- 3.添加/编辑对话框 -->
			<div id="employeeDlg" class="easyui-dialog" style="width:300px;height: 240px;" 
					title="添加/编辑员工面板" 
					modal="true"
					buttons="#employeeDlg_btn" 
					closed="true">
				<form id="employeeForm" method="post">
					<input type="hidden" name="id">
					<table align="center" style="margin-top: 10px;">
						<tr>
							<td>用户名&emsp;:</td>
							<td><input name="username" class="easyui-validatebox" required="true"></td>
						</tr>
						<tr>
							<td>真实姓名:</td>
							<td><input name="trueName" class="easyui-validatebox" required="true"></td>
						</tr>
						<tr>
							<td>电话号码:</td>
							<td><input name="tel" class="easyui-validatebox" required="true"></td>
						</tr>
						<tr>
							<td>电子邮件:</td>
							<td><input name="email"></td>
						</tr>
						<tr>
							<td>所属部门:</td>
							<td>
								<input name="dept.id" id="deptTreeCombo" class="easyui-combotree"  style="width:155px;">
							</td>
						</tr>
						<tr>
							<td>角色:</td>
							<td>
								 <input id="roleCombo" class="easyui-combobox" style="width:155px;"
									name="roleArr"
									data-options="
									url:'role_listAll.action',
									method:'get',
									valueField:'id',
									textField:'name',
									multiple:true,
									panelHeight:'auto'
									">
							</td>
						</tr>
						
					</table>
				</form>
			</div>
			<!-- 4. 对话框按钮 -->
		    <div id="employeeDlg_btn">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a>
			</div>
	</div>
</div>
</body>
</html>