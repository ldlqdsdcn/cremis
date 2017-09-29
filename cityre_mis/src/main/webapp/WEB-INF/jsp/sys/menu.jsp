<%@include file="/inc/taglib.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/8/28
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/inc/header.jsp" %>
</head>
<body>
<script type="text/javascript">
    var menuHelper = {
        editorColumnConverter: function (value, row, index) {
            return "<a href='javascript:void(0)' onclick='menuHelper.edit(" + index + ")' style='margin-left:10px;margin-top:0px'>编辑</a>";
        },
        convertMenuType: function (value, rec, index) {
            if (value == 0) {
                return "菜单文件夹";
            }
            else if (value == 1) {
                return "菜单";
            }
        },
        remove: function () {
            var rows = $('#menuTable').datagrid('getSelections');
            if (rows == null || rows.length == 0) {
                $.messager.alert("警告", "请先选中再删除");
                return;
            }
            var ids = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                ids.push(row.id);
            }
            $.messager.confirm('提示:', '您确认要删除选中记录吗?', function (event) {
                if (event) {
                    $.ajax({
                        url: "<%=path%>/sys/menu/deletes",
                        data: JSON.stringify(ids),
                        type: 'POST',
                        dataType: "json",
                        contentType: 'application/json;charset=utf-8',
                        success: function (result) {
                            if (result.success) {
                                $.messager.alert("操作成功", "操作成功！");
                                $('#menuTable').datagrid('reload');
                                return;
                            }
                            else {
                                $.messager.alert("删除失败", result.message);
                            }
                        }
                    });
                }
            });
        },
        edit: function (index) {
            var data = $('#menuTable').datagrid('getData');
            var row = data.rows[index];
            //赋值
            $("#id").val(row.id);
            $("#name").textbox("setValue", row.name);
            $("#sortNo").textbox("setValue", row.sortNo);
            $("#icon").textbox("setValue", row.icon);
            $('#type').combobox('setValue', row.type);
            $('#repositoryId').combobox('setValue', row.repositoryId);
            $('#parentMenuId').combobox('setValue', row.parentMenuId);
            $("#desc").textbox("setValue", row.desc);
            $("#url").textbox("setValue", row.url);
            document.getElementById("name").value = row.name;
            if (row.isactive == 'Y') {
                $("#isactive").attr('checked', 'checked');
            }
            $('#menu_dig').dialog('open').dialog('setTitle', '编辑菜单');
        },
        search: function () {
            var nameLike = $("#nameLike").val();
            var parentNameLike = $("#parentNameLike").val();
            var param = {};
            if (eideaValidator.isNotEmpty(nameLike)) {
                param.nameLike = nameLike;
            }
            if (eideaValidator.isNotEmpty(parentNameLike)) {
                param.parentNameLike = parentNameLike;
            }
            $("#menuTable").datagrid({
                queryParams: param,
                cache: false
            });
        },
        add: function () {
            $("#id").val('');
            $('#menu_dig').dialog('open').dialog('setTitle', '添加菜单');
            $('#modelForm').form('reset');

        },
        closeEditDialog: function () {
            $('#menu_dig').dialog('close');
        },
        save: function () {
            if (!$("#modelForm").form('enableValidation').form('validate')) {
                return;
            }
            var param = {};
            param.id = $("#id").val();
            param.sortNo = $("#sortNo").textbox("getValue");
            param.name = $("#name").textbox("getValue");
            param.icon = $("#icon").textbox("getValue");
            param.url = $("#url").textbox("getValue");
            param.type = $("#type").combobox("getValue");
            param.repositoryId = $("#repositoryId").combobox("getValue");
            param.parentMenuId = $("#parentMenuId").combobox("getValue");
            param.desc = $("#desc").textbox("getValue");
            if ($('#isactive').is(':checked')) {
                param.isactive = "Y";
            }
            else {
                param.isactive = "N";
            }
            $.ajax({
                url: "<%=path%>/sys/menu/save",
                data: JSON.stringify(param),
                type: 'POST',
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.success) {
                        $('#menu_dig').dialog('close')
                        $.messager.alert("操作成功", "操作成功！");
                        $('#menuTable').datagrid('reload');
                        return;
                    }
                    else {
                        $.messager.alert("操作失败", result.message);
                    }
                }
            });

        }
    };
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 45px;">
                <legend class="legend">查询条件</legend>
                <label>&nbsp;&nbsp;&nbsp;菜单名:</label>
                <input id="nameLike" style="width:150px;height:23px;" class="easyui-textbox"/>
                <label>&nbsp;&nbsp;&nbsp;父菜单名称:</label>
                <input id="parentNameLike" style="width:150px;height:23px;" class="easyui-textbox"/>
                &nbsp;&nbsp;&nbsp;<a href="#" id="seach_user" onclick="menuHelper.search()" class="easyui-linkbutton"
                                     data-options="iconCls:'icon-search'">查询</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <shiro:hasPermission name="menu:edit">
                    <a href="javascript:void(0)" onclick="menuHelper.add()" class="easyui-linkbutton"
                       data-options="iconCls:'icon-add',plain:true" style="margin-left:10px;margin-top:0px">添加</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0)" onclick="menuHelper.remove()" class="easyui-linkbutton"
                       data-options="iconCls:'icon-remove',plain:true" style="margin-left:10px;margin-top:0px">删除</a>
                </shiro:hasPermission>
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <table class="easyui-datagrid" toolbar="#activityTools" pagination="true"
               rownumbers="true" fitColumns="true" border="false"
               data-options="rownumbers:true,singleSelect:false,pageSize:20,pageList:[20]"
               url="<%=path %>/sys/menu/list"
               method="post"
               id="menuTable"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'sortNo'">序号</th>
                <th data-options="field:'name'">名称</th>
                <th data-options="field:'icon'">图标</th>
                <th data-options="field:'type',formatter:menuHelper.convertMenuType">类型</th>
                <th data-options="field:'repositoryName'">repository</th>
                <th data-options="field:'url'">连接</th>
                <th data-options="field:'parentMenu'">父菜单</th>
                <th data-options="field:'isactive'">是否有效</th>
                <th data-options="field:'created'">创建时间</th>
                <th data-options="field:'createdby'">创建人</th>
                <th data-options="field:'created'">修改时间</th>
                <th data-options="field:'createdby'">修改建人</th>
                <th data-options="field:'operator',formatter:menuHelper.editorColumnConverter" width="auto">相关操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="menu_dig" class="easyui-window" style="width: 400px;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'添加授权点',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <form id="modelForm" class="easyui-form" method="post" data-options="novalidate:true">
            <fieldset class="fieldset">
                <legend class="legend" id="title">菜单信息</legend>
                <input type="hidden" id="id">
                <table>
                    <tr>
                        <td><label>sortNo：</label></td>
                        <td><input type="text" class="easyui-textbox" id="sortNo" data-options="required:true"/></td>
                    </tr>
                    <tr>
                        <td><label>名称：</label></td>
                        <td><input type="text" class="easyui-textbox" id="name" data-options="required:true"/></td>
                    </tr>
                    <tr>
                        <td><label>图标：</label></td>
                        <td><input class="easyui-textbox" id="icon"/></td>
                    </tr>
                    <tr>
                        <td><label>类型：</label></td>
                        <td>
                            <select class="easyui-combobox" id="type" style="width:100%;">
                                <option value="0">菜单</option>
                                <option value="1">超链接</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>url：</label></td>
                        <td>
                            <input class="easyui-textbox" id="url"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>repository：</label></td>
                        <td><input class="easyui-combobox" id="repositoryId"
                                   data-options="url: '<%=path%>/sys/menu/repositories',method: 'get',valueField: 'id',textField: 'name'"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>父菜单：</label></td>
                        <td><input class="easyui-combobox" id="parentMenuId"
                                   data-options="url: '<%=path%>/sys/menu/menuFolders',method: 'get',valueField: 'id',textField: 'name'"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>是否有效：</label></td>
                        <td><input type="checkbox" id="isactive"></td>
                    </tr>
                    <tr>
                        <td><label>描述：</label></td>
                        <td><input class="easyui-textbox" id="desc"></td>
                    </tr>
                </table>
            </fieldset>
            <div id="dlg-buttons" style="text-align: right">
                <shiro:hasPermission name="menu:edit">
                    <a id="addUser_btn" href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="iconCls:'icon-save',plain:true" onclick="menuHelper.save();">保存</a>
                </shiro:hasPermission>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
                   onclick="menuHelper.closeEditDialog();">取消</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>