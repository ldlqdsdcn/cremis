<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.cityre.edi.mis.base.entity.cpo.CityPo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    CityPo cityPo =(CityPo)session.getAttribute("currentCity");
    String citycode = cityPo.getCityid();
    String agentUid = request.getParameter("co_name");
    if(agentUid == null){
        agentUid="";
    }
    String status = request.getParameter("state");
    if(status == null){
        status="";
    }
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
        $(function(){
            $("#checkAgent_ids").datagrid({
                toolbar : [ {

                    text : '全选',

                    handler : function() {

                        $("#checkAgent_ids").datagrid("checkAll");

                    }

                },'-', {

                    text : '取消',

                    handler : function() {

                        $("#checkAgent_ids").datagrid("uncheckAll");

                    }

                }, '-', {

                    text: '反选',

                    handler: function () {

                        var pageRow = $("#checkAgent_ids").datagrid("getRows")

                        var selecRow = $("#checkAgent_ids").datagrid("getSelections")


                        var pageRowSize = pageRow.length;

                        var selecRowSize = selecRow.length;

                        var mycars = new Array();

                        for (var i = 0; i < pageRowSize; i++) {
                            //便利页面数据，和选中的数据做比较。
                            var flag = true;
                            for (var j = 0; j < selecRowSize; j++) {

                                //如果找到被选中数据直接跳出。否则，将参与比较的选中数据放入数组。

                                if (pageRow[i] == selecRow[j]) {

                                    flag = false;

                                }

                            }
                            if(flag){
                                mycars.push(pageRow[i]);
                            }
                        }

                        $("#checkAgent_ids").datagrid("clearSelections");
                        var mylen = mycars.length;
                        for (var i = 0; i < mylen; i++) {
                            var idx =  $('#checkAgent_ids').datagrid('getRowIndex', mycars[i]);
                            $("#checkAgent_ids").datagrid("checkRow",idx);

                        }

                    }

                }, '-',{

                    text : '全部通过审核',

                    handler : function() {
                        var selecRow = $("#checkAgent_ids").datagrid("getSelections");
                        if(selecRow != null && selecRow.length >0){
                            var coCodeStr = "";
                            for(var i=0;i<selecRow.length;i++){
                                coCodeStr = coCodeStr+selecRow[i].id+",";
                            }
                            coCodeStr = coCodeStr.substring(0,coCodeStr.length-1);
                            $.ajax({
                                type:'POST',
                                url:'<%=path%>/merchant/agentCheck/checkAll',
                                data:{"idStr":coCodeStr,"certification":1},
                                dataType:"json",
                                success:function(data){
                                    var json=data;
                                    if(json != ""){
                                        if(json.result == 'OK'){
                                            $.messager.alert("提示","全部通过审核成功!");
                                            findAgentCheckReload();
                                        }

                                    }
                                }
                            });
                        }else{
                            $.messager.alert("提示","请先选择公司!");
                        }
                    }

                }, '-',{

                text : '全部拒绝审核',

                handler : function() {
                    var selecRow = $("#checkAgent_ids").datagrid("getSelections");
                    if(selecRow != null && selecRow.length >0){
                        var coCodeStr = "";
                        for(var i=0;i<selecRow.length;i++){
                            coCodeStr = coCodeStr+selecRow[i].id+",";
                        }
                        coCodeStr = coCodeStr.substring(0,coCodeStr.length-1);
                        $.ajax({
                            type:'POST',
                            url:'<%=path%>/merchant/agentCheck/checkAll',
                            data:{"idStr":coCodeStr,"certification":-1},
                            dataType:"json",
                            success:function(data){
                                var json=data;
                                if(json != ""){
                                    if(json.result == 'OK'){
                                        $.messager.alert("提示","全部拒绝审核成功!");
                                        findAgentCheckReload();
                                    }

                                }
                            }
                        });
                    }else{
                        $.messager.alert("提示","请先选择公司!");
                    }


                }

            }]

            });
            var status ='<%=status%>';
            if( status != ''){
                if(status == 'all'){
                    $("#statusValue").combobox('setValue','');
                }else{
                    $("#statusValue").combobox('setValue',status);
                }
            }
            var agentUid = '<%=agentUid%>';
            if(agentUid != ''){
                $("#agentUid_ids").textbox('setValue',agentUid);
            }
            findAgentCheckSeachFn();
        });


        function findAgentCheckSeachFn() {
            var opts = $("#checkAgent_ids").datagrid("options");
            opts.url = "<%=path%>/merchant/agentCheck/list";
            var status = $("#statusValue").combobox('getValue');
            var agentName = $("#agentName_ids").val();
            var agentUid = $("#agentUid_ids").val();
            var subStartDate = $("#subStartDate").datebox('getValue');
            var subEndDate = $("#subEndDate").datebox('getValue');
            var chkStartDate = $("#chkStartDate").datebox('getValue');
            var chkEndDate = $("#chkEndDate").datebox('getValue');
            $("#checkAgent_ids").datagrid("load",{
                    status: status,
                    agentName: agentName,
                    agentUid: agentUid,
                    subStartDate: subStartDate,
                    subEndDate: subEndDate,
                    chkStartDate: chkStartDate,
                    chkEndDate: chkEndDate
            });
        }

        function findAgentCheckReload() {
            var opts = $("#checkAgent_ids").datagrid("options");
            opts.url = "<%=path%>/merchant/agentCheck/list";
            var status = $("#statusValue").combobox('getValue');
            var agentName = $("#agentName_ids").val();
            var agentUid = $("#agentUid_ids").val();
            var subStartDate = $("#subStartDate").datebox('getValue');
            var subEndDate = $("#subEndDate").datebox('getValue');
            var chkStartDate = $("#chkStartDate").datebox('getValue');
            var chkEndDate = $("#chkEndDate").datebox('getValue');
            $("#checkAgent_ids").datagrid("load",{
                status: status,
                agentName: agentName,
                agentUid: agentUid,
                subStartDate: subStartDate,
                subEndDate: subEndDate,
                chkStartDate: chkStartDate,
                chkEndDate: chkEndDate
            });
        }


        function  getAgentImg(row) {
            var certPhoto = row.certPhoto;
            var html = "";
            html += "<a  target='_blank' href='"+certPhoto+"'><img width='100' height='100' src = '"+certPhoto+"' /></a>";
            return html;
        }

        function getCheckOperation(index){
            var html = "";
            html += "<a href='javascript:void(0);' onclick='passImage(" + index + ")'><font color='blue'>通过审核</font></a>"  ;
            html += "|<a href='javascript:void(0);' onclick='refuseImage(" + index + ")'><font color='blue'>拒绝审核</font></a>"  ;
            return html;
        }
        function passImage(index){
            var rows = $('#checkAgent_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/agentCheck/checkOne',
                data:{"uid":uid,"certification":1},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.result  == 'OK'){
                            $.messager.alert("提示","通过审核成功!");
                            findAgentCheckReload();
                        }

                    }
                }
            });
        }
        function refuseImage(index){
            var rows = $('#checkAgent_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/agentCheck/checkOne',
                data:{"uid":uid,"certification":-1},
                dataType:"json",
                success:function(data){
                    var json= data;
                    if(json != ""){
                        if(json.result == 'OK'){
                            $.messager.alert("提示","拒绝审核成功!");
                            findAgentCheckReload();
                        }
                    }
                }
            });
        }
        function  getAgentInfo(index) {
            var rows = $('#checkAgent_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            var html = "";
            html += "<a href='<%=path%>/merchant/agentCheck/getUserInfo?uid="+uid+"' target='_blank'><font color='blue'>"+uid+"</font></a>";
            html+="| <a target='_blank\' href='http://<%=citycode%>.cityhouse.cn/agency.html?uid="+uid+"'>进入空间</a>";
            return html;
        }
    </script>

    <title>经纪人认证审核</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 45px;">
                <legend class="legend">查询条件</legend>
                <label>&nbsp;&nbsp;&nbsp;审核状态:</label>
                <input id="statusValue" name="statusValue" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '未审核',value: '0',selected:true},
                   		  {label: '已审核',value: '2'},
                   		  {label: '通过审核',value: '1'},
                   		  {label: '拒绝审核',value: '-1'},
                   		  {label: '全部',value: ''}]" />
                <label>&nbsp;&nbsp;&nbsp;经纪人uid:</label>
                <input id="agentUid_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;经纪人姓名:</label>
                <input id="agentName_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;认证提交时间:</label>
                <input id="subStartDate"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                    id="subEndDate" style="width: 120px; height: 20px;"
                    class="easyui-datebox" />
                <label>&nbsp;&nbsp;&nbsp;审核时间:</label>
                <input id="chkStartDate"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="chkEndDate" style="width: 120px; height: 20px;"
                        class="easyui-datebox" />
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"  onclick="findAgentCheckSeachFn()" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >

        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true" fit="true"
               rownumbers="true" fitColumns="true" border="false" data-options=" emptyMsg:'<span style=\'color:red\' >无记录</span>',pageSize:20"
               url=""
               id="checkAgent_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true,sortable:true" width="2%"></th>
                <th data-options="field:'imageurl',sortable:true,formatter:function(value,row,index){
                				    return getAgentImg(row);
			                 }" width="auto">图片链接</th>
                <th data-options="field:'coNameUrl',sortable:true,formatter: function(value,row,index){
                			                    return getAgentInfo(index);
			                    }" width="15%">经纪人名称</th>
                <th data-options="field:'name',sortable:true" width="10%">姓名</th>
                <th data-options="field:'idno',sortable:true" width="10%">身份证号</th>
                <th data-options="field:'headportrait_flag',sortable:true,formatter: function(value,row,index){
                				    if(row.headportrait != null && row.headportrait != ''){
                                    if(row.headportrait_flag == '1'){
                                        return '√';
                                    }else if(row.headportrait_flag == '-1'){
                                        return '×';
                                    }else{
                                        return '*';
                                    }
                				    }
			                 }" width="5%">个人头像</th>
                <th data-options="field:'certphoto_uploadtime',sortable:true,formatter: function(value,row,index){
                                if(null != row.certphoto_uploadtime && row.certphoto_uploadtime != ''){
			                      	return dateFormat(row.certphoto_uploadtime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
                        }" width="10%">认证上传时间</th>
                <th data-options="field:'certification',sortable:true,formatter: function(value,row,index){
                				if(row.certification != null){
                					 if(row.certification == '0'){
                					    return '待审核';
                					 }else if(row.certification == '1'){
                                    return '通过审核';
                					 }else if(row.certification == '-1'){
                					    return '拒绝审核';
                					 }
			                 }
                        }" width="5%">审核状态</th>
                <th data-options="field:'usercert_updateuid',sortable:true" width="5%">审核人</th>
                <th data-options="field:'usercert_updatetime',sortable:true,formatter: function(value,row,index){
                                if(null != row.usercert_updatetime && row.usercert_updatetime != ''){
			                      	return dateFormat(row.usercert_updatetime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
                        }" width="10%">审核时间</th>
                <th data-options="field:'operation',sortable:true,formatter: function(value,row,index){
                			return getCheckOperation(index);
			        }" width="10%">操作</th>
            </tr>
            </thead>
        </table>
    </div>

</div>
</body>
</html>
