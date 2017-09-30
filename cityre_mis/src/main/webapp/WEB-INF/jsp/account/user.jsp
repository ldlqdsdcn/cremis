<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/4
  Time: 10:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--<%@ include file="/inc/topjuiheader.jsp" %>--%>
    <%@ include file="/inc/header.jsp" %>
</head>
<body>
<div id="userPanel" class="easyui-layout" data-options="fit:true,border:false">
</div>
<script type="text/javascript">
    $.ajaxSetup({
        cache : false
    });//ajax不缓存

    function operation(rows, index) {
        var html = "<a href='javascript:void(0)' onclick='accountHelper.edit(" + rows.id + ")' style='margin-left:10px;margin-top:0px'>编辑</a>";
        html=html+"<a href='javascript:void(0)' onclick='accountHelper.showCity(" + rows.id + ")' style='margin-left:10px;margin-top:0px'>城市信息</a>";
        return html;
    }
    var accountHelper = {
        goList: function () {
            var url = "<%=path%>/account/user/showList";
            $("#userPanel").panel({href: url,onLoad:function () {
                
            }});
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
                        url: "<%=path%>/account/user/deleteRepositories",
                        data: JSON.stringify(ids),
                        type: 'POST',
                        dataType: "json",
                        contentType: 'application/json;charset=utf-8',
                        success: function (result) {
                            if (result.success) {
                                $.messager.alert("操作成功", "操作成功！");
                                $('#repositoryTable').datagrid('reload');
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
            var url = "<%=path%>/account/user/showForm";
            if(id!=null)
            {
                url=url+"?id=" + id + "&time=";
            }
            $("#userPanel").panel({
                    href: url, onLoad: function () {
                        $.get("<%=path%>/account/user/get/" + id, function (result) {
                            if (result.success) {
                                var accountUserVo = result.data;
                                var accountUser = accountUserVo.accountUser;
                                $("#id").val(accountUser.id);
                                $("#unionuid").textbox("setValue",accountUser.unionuid);
                                $("#userid").textbox("setValue", accountUser.userid);
                                $("#nickname").textbox("setValue", accountUser.nickname);
                                $("#realname").textbox("setValue", accountUser.realname);
                                if (1 == accountUser.sex) {
                                    $("#nan").attr("checked", "checked");
                                }
                                else if(0==accountUser.sex)
                                {
                                           $("#nv").attr("checked", "checked");
                                }
                                else
                                {
                                    $("#unknown").attr("checked","checked");
                                }
                                $("#birthday").textbox('setValue', accountUser.birthday);
                                $("#userIcon").textbox('setValue', accountUser.userIcon);
                                $("#isVerified").textbox('setValue', accountUser.isverified);
                                $("#isValid").textbox('setValue', accountUser.isvalid);
                                $("#createTime").textbox('setValue', accountUser.createtime);
                                $("#updateTime").textbox('setValue', accountUser.updatetime);
                                var sb = new StringBuffer("");
                                var userRepositoryUnionList = accountUserVo.userRepositoryUnionList;
                                for (var i = 0; i < userRepositoryUnionList.length; i++) {
                                    var gr = userRepositoryUnionList[i];
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
                                $("#userTbodyContent").html(sb.toString());

                                var userGroupSb=new StringBuffer();
                                var userGroupUnionList = accountUserVo.userGroupUnionList;
                               for(var i=0;i<userGroupUnionList.length;i++)
                                {
                                    var usergroup=userGroupUnionList[i];
                                    userGroupSb.append(usergroup.name)
                                    userGroupSb.append("<input type='checkbox' name='group' value='").append(usergroup.groupId).append("' ");
                                    if(usergroup.checked)
                                    {
                                        userGroupSb.append("checked='checked'");
                                    }
                                    userGroupSb.append(">&nbsp;&nbsp;&nbsp;");
                                }
                                $("#userGroupDiv").html(userGroupSb.toString());
                            }
                        });

                    }
                }
            )
        },
        search: function () {
            var nicknameLike = $("#nicknameLike").val();
            var useridLike = $("#useridLike").val();
            var param = {};
            if (eideaValidator.isNotEmpty(nicknameLike)) {
                param.nicknameLike = nicknameLike;
            }
            if (eideaValidator.isNotEmpty(useridLike)) {
                param.useridLike = useridLike;
            }
            $("#userTable").datagrid({
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
            if (!$("#modelForm").form('enableValidation').form('validate')) {
                return;
            }
            var param = {};
            var accountUser = {};
            var userRepositoryUnionList = [];
            var userGroupUnionList=[];

            accountUser.id = $("#id").val();
            accountUser.unionuid = $("#unionuid").textbox("getValue");
            accountUser.userid = $("#userid").textbox("getValue");
            accountUser.nickname = $("#nickname").textbox("getValue");
            accountUser.realname = $("#realname").textbox("getValue");
            if ($('#nan').is(':checked')) {
                accountUser.sex = 1;
            }
            else if($("#nv").is(":checked")){
                accountUser.sex = 0;
            }
            accountUser.birthday=$("#birthday").textbox("getValue");
            accountUser.userIcon = $("#userIcon").textbox("getValue");
            accountUser.isVerified=$("#isVerified").textbox("getValue");
            accountUser.isValid=$("#isValid").textbox("getValue");
            param.accountUser = accountUser;
            $('input[name="privileges"]:checked').each(function () {
                var row = {};
                row.repositoryId = $(this).val();
                userRepositoryUnionList.push(row);
            });

            $('input[name="group"]:checked').each(function () {
                var row = {};
                row.groupId = $(this).val();
                userGroupUnionList.push(row);
            });
            param.userGroupUnionList = userGroupUnionList;
            param.userRepositoryUnionList=userRepositoryUnionList;
            $.ajax({
                url: "<%=path%>/account/user/save",
                data: JSON.stringify(param),
                type: 'POST',
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("操作成功", "操作成功！");
                        accountHelper.goList();
                        return;
                    }
                    else {
                        $.messager.alert("操作失败", result.message);
                    }
                }
            });

        },
        selectAll:function(value)
        {
            var inputArrays=document.getElementsByTagName("input");

            for(var i=0;i<inputArrays.length;i++)
            {
                var input=inputArrays[i];
                if(input.type=='checkbox')
                {
                    input.checked=value;
                }
            }

        },
        citycheck:function (checked,divId) {
                var div=document.getElementById(divId);
            var inputArrays=div.getElementsByTagName("input");
            for(var i=0;i<inputArrays.length;i++)
            {
                var input=inputArrays[i];
                if(input.type=='checkbox')
                {
                    input.checked=checked;
                }
            }
        },
        saveUserCity:function () {
           var userCity={};
           userCity.unionUid=$("#unionUid").val();
            userCity.cities=[];
            $('input:checkbox[name=city]:checked').each(function(i){
                userCity.cities.push(this.value);
            });
            $.ajax({
                url: "<%=path%>/account/user/saveCities",
                data: JSON.stringify(userCity),
                type: 'POST',
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.success) {
                        $.messager.alert("操作成功", "操作成功！");
                        accountHelper.goList();
                        return;
                    }
                    else {
                        $.messager.alert("操作失败", result.message);
                    }
                }
            });


        },showCity: function (id) {
            var url = "<%=path%>/account/user/showCities/"+id;
            $("#userPanel").panel({href: url,onLoad:function () {

            }});
        }
    };
    $(function () {
        accountHelper.goList();
    });
</script>
</body>
</html>
