<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <script src="../../jQuery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="../../jQuery/jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../../jQuery/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../../jQuery/date.format.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="../../jQuery/jquery-easyui-1.5/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../jQuery/jquery-easyui-1.5/themes/default/easyui.css" />
    <style>
        li {list-style-type:none;}
    </style>
    <script>


        function findUserSeachFn() {
            var opts = $("#userList_ids").datagrid("options");
            opts.url = '<%=path%>' + "/mis/merchant/registeredUser/list";
            var uidstr = $("#user_ids").val();
            var phoneStr = $("#phone_ids").val();
            var eamilStr = $("#email_ids").val();
            var companyStr = $("#company_ids").val();
            $("#userList_ids").datagrid("load",{
                    uid: uidstr,
                    phone: phoneStr,
                    email: eamilStr,
                    company: companyStr
                });

          /*  $("#userList_ids").datagrid({onLoadSuccess:function(data){
                if (data.total<1){
                    $.messager.alert("提示","表单无数据!");
                }
            }
            });*/
        }
        function  getUserInfo(uid) {
            var html = "";
            html += "<a href='<%=path%>/mis/merchant/agentCheck/getUserInfo?uid="+uid+"' target='_blank'><font color='blue'>用户信息</font></a>"  ;
            return html;
        }
        function getServiceHa(index) {
            var rows = $('#userList_ids').datagrid('getRows');
            var row = rows[index];
            var   html = "<a href='javascript:void(0);' onclick='findServiceHaDetail(" + index + ")'><font color='blue'>服务小区详情</font></a>";
            return html;
        }
        function findServiceHaDetail(index) {
            var rows = $('#userList_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            var cocode = row.cocode;
            $.ajax({
                type:'POST',
                url:'<%=path%>/mis/merchant/registeredUser/serviceHaDetail',
                data:{"cocode":cocode,"uid":uid},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        var ing = json.serviceingHa;
                        var out = json.expiredServiceHa;
                        var html_ing = "";
                        $.each(ing,function(ix,oj){
                            html_ing+="<li>"+oj.haName+"&nbsp;&nbsp;";
                            if(oj.haType == 'sale'){
                                html_ing+="出售";
                            }else if(oj.haType == 'lease'){
                                html_ing+="出租";
                            }
                            html_ing+="&nbsp;&nbsp;"+dateFormat(oj.startDate,'yyyy-mm-dd HH:MM:ss')+"到"+dateFormat(oj.endDate,'yyyy-mm-dd HH:MM:ss')+"</li>";
                        });
                        $("#servicingHa_td").html(html_ing);
                        var html_out="";
                        $.each(out,function(ix,oj){
                            html_out+="<li>"+oj.haName+"&nbsp;&nbsp;";
                            if(oj.haType == 'sale'){
                                html_out+="出售";
                            }else if(oj.haType == 'lease'){
                                html_out+="出租";
                            }
                            html_out+="&nbsp;&nbsp;"+dateFormat(oj.startDate,'yyyy-mm-dd HH:MM:ss')+"到"+dateFormat(oj.endDate,'yyyy-mm-dd HH:MM:ss')+"</li>";
                        });
                        $("#outDateHa_td").html(html_out);
                    }
                }
            });
            $('#haDetailDig').window('open');
        }
       /* function getServiceHa(obj,idx){
            var html = "";
            html = "<ul>";
            $.each(obj,function(ix,oj){
                html+="<li>"+oj.haName+"&nbsp;&nbsp;";
                if(oj.haType == 'sale'){
                    html+="出售";
                }else if(oj.haType == 'lease'){
                    html+="出租";
                }
                html+="&nbsp;&nbsp;"+dateFormat(oj.startDate,'yyyy-mm-dd HH:MM:ss')+"到"+dateFormat(oj.endDate,'yyyy-mm-dd HH:MM:ss')+"</li>";
            });
            html+="</ul>";
            return html;
        }*/
        function getCompanyInfo(row,index){
            var cocode = row.coCode;
            var companyname = row.companyName;
            var html = "";
            html += "<a href='<%=path%>/base/registeredUser/coinfos?cocode="+cocode+"' target='_blank'><font color='blue'>"+companyname+"</font></a>"  ;
            html += "|<a href='"+row.coUrl+"' target='_blank'><font color='blue'>进入店铺</font></a>"  ;
            return html;
        }
    </script>

    <title>账号管理</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 45px;">
                <legend class="legend">查询条件</legend>
                <label>&nbsp;&nbsp;&nbsp;账号:</label>
                <input id="user_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;电话号码:</label>
                <input id="phone_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;邮箱:</label>
                <input id="email_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;公司名称:</label>
                <input id="company_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="seach_qd_contract_ids" onclick="findUserSeachFn()" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >
        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true"
               rownumbers="true" fitColumns="true" border="false" data-options="singleSelect:true,pageSize:20"
               url=""
               id="userList_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true,sortable:true" width="auto"></th>
                <th data-options="field:'uid',sortable:true" width="5%">用户名</th>
                <th data-options="field:'userinfolink',sortable:true,formatter: function(value,row,index){
                                     return getUserInfo(row.uid);
			                         }" width="5%">用户信息</th>
                <th data-options="field:'company',sortable:true,formatter: function(value,row,index){
                				       return getCompanyInfo(row,index);
			                 }" width="auto">公司名称</th>
                <th data-options="field:'coUID',sortable:true,formatter: function(value,row,index){
                				if(row.coUID == row.uid){
                					return '*';
                				}else{
                				   return '';
                				}
			                 }" width="auto">是否管理员</th>
                <th data-options="field:'serviceHa',sortable:true,formatter: function(value,row,index){
                                    return getServiceHa(index);
			                 }" width="auto">服务小区详情</th>
               <%-- <th data-options="field:'serviceingHa',sortable:true,formatter: function(value,row,index){
                				if(row.serviceingHa != null){
                					 return getServiceHa(row.serviceingHa,index);
			                 }
                        }" width="auto">正在服务小区</th>
                <th data-options="field:'expiredServiceHa',sortable:true,formatter: function(value,row,index){
                				if(row.expiredServiceHa != null){
                					 return getServiceHa(row.expiredServiceHa,index);
			                 }
                        }" width="auto">过期服务小区</th>--%>
            </tr>
            </thead>
        </table>
    </div>
    <div id="haDetailDig" class="easyui-window"
         style="width: 50%; height: 50%; text-align: center;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'详情',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <fieldset class="fieldset">
            <legend class="legend">服务小区的详情</legend>
            <table  border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;">
                <tr>
                    <td style="text-align: left">正在服务小区:</td>
                    <td style="text-align: left"  id="servicingHa_td"></td>
                </tr>
                <tr>
                    <td style="text-align: left">过期服务小区:</td>
                    <td style="text-align: left" id="outDateHa_td"></td>
                </tr>
            </table>
        </fieldset>
    </div>
</div>
</body>
</html>
