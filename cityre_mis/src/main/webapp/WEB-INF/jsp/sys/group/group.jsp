<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/1
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/inc/header.jsp" %>
</head>
<body>
<div id="groupPanel" class="easyui-layout" data-options="fit:true,border:false"
     style="min-width: 400px;overflow-x: scroll;overflow-y: scroll;">
</div>
<script type="text/javascript">
    $.ajaxSetup({
        cache : false
    });//ajax不缓存

    function operation(rows, index) {
        var html = "<a href='javascript:void(0)' onclick='groupHelper.edit(" + rows.id + ")' style='margin-left:10px;margin-top:0px'>编辑</a>";
        html = html + "<a href='javascript:void(0)' onclick='groupHelper.showCity(" + rows.id + ")' style='margin-left:10px;margin-top:0px'>城市信息</a>";
        return html;
    }
    var groupHelper = {
        goList: function () {
            var url = "<%=path%>/sys/group/showList/";
            $("#groupPanel").panel({href: url});
        },
        remove: function () {
            var rows = $('#groupTable').datagrid('getSelections');
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
                        url: "<%=path%>/sys/group/delete",
                        data: JSON.stringify(ids),
                        type: 'POST',
                        dataType: "json",
                        contentType: 'application/json;charset=utf-8',
                        success: function (result) {
                            if (result.success) {
                                $.messager.alert("操作成功", "操作成功！");
                                $('#groupTable').datagrid('reload');
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
        edit: function (id) {
            var url = "<%=path%>/sys/group/showForm/";
            if(id!=null)
            {
                url=url+"?id=" + id + "&time=";
            }
           // var url = "<%=path%>/sys/group/showForm/?id=" + id + "&time=";
            $("#groupPanel").panel({
                    href: url, onLoad: function () {
                        $.get("<%=path%>/sys/group/get/" + id, function (result) {
                            if (result.success) {
                                var groupVo = result.data;
                                var group = groupVo.group;
                                $("#groupId").val(group.id);
                                $("#groupName").textbox("setValue", group.name);
                                $("#groupNo").textbox("setValue", group.no);
                                $("#groupDesc").textbox("setValue", group.desc);
                                if ('Y' == group.isactive) {
                                    $("#groupIsactive").attr("checked", "checked");
                                }
                                $("#groupCreated").textbox('setValue', group.created);
                                $("#groupUpdated").textbox('setValue', group.updated);
                                $("#groupCreatedby").textbox('setValue', group.createdby);
                                $("#groupUpdatedby").textbox('setValue', group.updatedby);
                                var sb = new StringBuffer("");
                                var groupRepositoryUnionList = groupVo.groupRepositoryUnionList;
                                for (var i = 0; i < groupRepositoryUnionList.length; i++) {
                                    var gr = groupRepositoryUnionList[i];
                                    sb.append("<tr>");
                                    sb.append("<td>");
                                    sb.append("<input type='checkbox' name='privileges'");
                                    if (gr.checked) {
                                        sb.append("checked='checked'");
                                    }
                                    sb.append(" value='");
                                    sb.append(gr.repositoryId).append("'>")
                                    sb.append("</td>");
                                    sb.append("<td>");
                                    sb.append(gr.repositoryNo);
                                    sb.append("</td><td>");
                                    sb.append(gr.repositoryName)
                                    sb.append("</td>");
                                    sb.append("</tr>")
                                }
                                $("#groupTbodyContent").html(sb.toString());
                            }
                        });

                    }
                }
            )
        },
        search: function () {
            var no = $("#groupNoLike").val();
            var name = $("#groupNameLike").val();
            var param = {};
            if (eideaValidator.isNotEmpty(no)) {
                param.noLike = no;
            }
            if (eideaValidator.isNotEmpty(name)) {
                param.nameLike = name;
            }
           $("#groupTable").datagrid({
                queryParams: param,
                cache: false
           });
        },
        add: function () {
           groupHelper.edit(-1);
        },
        closeEditDialog: function () {
            $('#addRepository_dig').dialog('close');
        },
        save: function () {
            if (!$("#groupModelForm").form('enableValidation').form('validate')) {
                return;
            }
            var param = {};
            var group = {};
            var groupRepositoryUnionList = [];
            group.id = $("#groupId").val();
            group.no = $("#groupNo").textbox("getValue");
            group.name = $("#groupName").textbox("getValue");
            if ($('#groupIsactive').is(':checked')) {
                group.isactive = "Y";
            }
            else {
                group.isactive = "N";
            }
            group.desc = $("#groupDesc").textbox("getValue");
            param.group = group;
            $('input[name="privileges"]:checked').each(function () {
                var row = {};
                row.repositoryId = $(this).val();
                groupRepositoryUnionList.push(row);
            });
            param.groupRepositoryUnionList = groupRepositoryUnionList;
            $.ajax({
                url: "<%=path%>/sys/group/save",
                data: JSON.stringify(param),
                type: 'POST',
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.success) {

                        $.messager.alert("操作成功", "操作成功！");
                        groupHelper.goList();
                        return;
                    }
                    else {
                        $.messager.alert("操作失败", result.message);
                    }
                }
            });

        }
        , selectAll: function (value) {
            var inputArrays = document.getElementsByTagName("input");

            for (var i = 0; i < inputArrays.length; i++) {
                var input = inputArrays[i];
                if (input.type == 'checkbox') {
                    input.checked = value;
                }
            }

        },
        citycheck: function (checked, divId) {
            var div = document.getElementById(divId);
            var inputArrays = div.getElementsByTagName("input");
            for (var i = 0; i < inputArrays.length; i++) {
                var input = inputArrays[i];
                if (input.type == 'checkbox') {
                    input.checked = checked;
                }
            }
        }, showCity: function (id) {

            var url = "<%=path%>/sys/group/showCities/" + id;
            $("#groupPanel").panel({
                href: url, onLoad: function () {

                }
            });
        },
        saveGroupCity: function () {
            var userCity = {};
            userCity.groupId = $("#groupId").val();
            userCity.cities = [];
            $('input:checkbox[name=city]:checked').each(function (i) {
                userCity.cities.push(this.value);
            });
            $.ajax({
                url: "<%=path%>/sys/group/saveCities",
                data: JSON.stringify(userCity),
                type: 'POST',
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("操作成功", "操作成功！");
                        groupHelper.goList();
                        return;
                    }
                    else {
                        $.messager.alert("操作失败", result.message);
                    }
                }
            });


        }
    };
    $(function () {
        groupHelper.goList();
    })
</script>
</body>
</html>