<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.cityre.edi.mis.base.entity.cpo.CityPo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    CityPo cityPo =(CityPo)session.getAttribute("currentCity");
    String citycode = cityPo.getCityid();
    String citypinyin = cityPo.getCityPinYin();
    String uid = request.getParameter("useuid");
    if(uid ==null){
        uid ="";
    }
    String head_type = request.getParameter("head_type");
    if(head_type == null){
        head_type="";
    }
    String confirm_status = request.getParameter("confirm_status");
    if(confirm_status == null){
        confirm_status="";
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
            $("#headportrait_ids").datagrid({
                toolbar : [ {

                    text : '全选',

                    handler : function() {

                        $("#headportrait_ids").datagrid("checkAll");

                    }

                },'-', {

                    text : '取消',

                    handler : function() {

                        $("#headportrait_ids").datagrid("uncheckAll");

                    }

                }, '-', {

                    text: '反选',

                    handler: function () {

                        var pageRow = $("#headportrait_ids").datagrid("getRows")

                        var selecRow = $("#headportrait_ids").datagrid("getSelections")


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

                        $("#headportrait_ids").datagrid("clearSelections");
                        var mylen = mycars.length;
                        for (var i = 0; i < mylen; i++) {
                            var idx =  $('#headportrait_ids').datagrid('getRowIndex', mycars[i]);
                            $("#headportrait_ids").datagrid("checkRow",idx);

                        }

                    }

                }, '-',{

                    text : '全部通过审核',

                    handler : function() {
                        var selecRow = $("#headportrait_ids").datagrid("getSelections");
                        if(selecRow != null && selecRow.length >0){
                            var coCodeStr = "";
                            var headtype = selecRow[0].headtype;
                            for(var i=0;i<selecRow.length;i++){
                                coCodeStr = coCodeStr+selecRow[i].id+",";

                            }
                            coCodeStr = coCodeStr.substring(0,coCodeStr.length-1);
                            $.ajax({
                                type:'POST',
                                url:'<%=path%>/merchant/headportrait/checkAll',
                                data:{"idStr":coCodeStr,"certification":1,"headtype":headtype},
                                dataType:"json",
                                success:function(data){
                                    var json=data;
                                    if(json != ""){
                                        if(json == 'OK'){
                                            $.messager.alert("提示","全部通过审核成功!");
                                            findHeadportraitReload();
                                        }

                                    }
                                }
                            });
                        }else{
                            $.messager.alert("提示","请先选择头像!");
                        }
                    }

                }, '-',{

                text : '全部拒绝审核',

                handler : function() {
                    var selecRow = $("#headportrait_ids").datagrid("getSelections");
                    if(selecRow != null && selecRow.length >0){
                        var coCodeStr = "";
                        var headtype = selecRow[0].headtype;
                        for(var i=0;i<selecRow.length;i++){
                            coCodeStr = coCodeStr+selecRow[i].id+",";
                        }
                        coCodeStr = coCodeStr.substring(0,coCodeStr.length-1);
                        $.ajax({
                            type:'POST',
                            url:'<%=path%>/merchant/headportrait/checkAll',
                            data:{"idStr":coCodeStr,"certification":-1,"headtype":headtype},
                            dataType:"json",
                            success:function(data){
                                var json=data;
                                if(json != ""){
                                    if(json == 'OK'){
                                        $.messager.alert("提示","全部拒绝审核成功!");
                                        findHeadportraitReload();
                                    }

                                }
                            }
                        });
                    }else{
                        $.messager.alert("提示","请先选择头像!");
                    }


                }

            }]

            });
            var head_type = '<%=head_type%>';
            if(head_type != ''){
                $("#head_cbx").combobox('setValue',head_type);
            }
            var confirm_status = '<%=confirm_status%>';
            if(confirm_status != ''){
                if(confirm_status == 'all'){
                    $("#statusValue").combobox('setValue','');
                }else{
                    $("#statusValue").combobox('setValue',confirm_status);
                }
            }


            findHeadportraitSeachFn();
        });


        function findHeadportraitSeachFn() {
            var opts = $("#headportrait_ids").datagrid("options");
            opts.url = "<%=path%>/merchant/headportrait/list";
            var uid = '<%=uid%>';
            var headType = $("#head_cbx").combobox('getValue');
            var phone = $("#phone_ids").val();
            var status = $("#statusValue").combobox('getValue');
            var startDate = $("#startDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            $("#headportrait_ids").datagrid(
                "load",{
                    uid:uid,
                    phone:phone,
                    headType:headType,
                    status: status,
                    startDate: startDate,
                    endDate: endDate
            });
        }
        function findHeadportraitReload() {
            var opts = $("#headportrait_ids").datagrid("options");
            opts.url = "<%=path%>/merchant/headportrait/list";
            var uid = '<%=uid%>';
            var headType = $("#head_cbx").combobox('getValue');
            var phone = $("#phone_ids").val();
            var status = $("#statusValue").combobox('getValue');
            var startDate = $("#startDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            $("#headportrait_ids").datagrid(
                "load",{
                    uid:uid,
                    phone:phone,
                    headType:headType,
                    status: status,
                    startDate: startDate,
                    endDate: endDate
                });
        }
        function  getHeadImg(row) {
            var imageURL = row.imageURL;
            var html = "";
            html += "<a  target='_blank' href='"+imageURL+"'><img width='100' height='100' src = '"+imageURL+"' /></a>";
            return html;
        }
        function  getInfoURL(row) {
            var headtype = row.headtype;
            var uid = row.uid;
            var html = "";
            if(headtype == 'reguser'|| headtype == 'couser'){
                html += "<a href='<%=path%>/merchant/agentCheck/getUserInfo?uid="+uid+"' target='_blank'><font color='blue'>"+uid+"</font></a>";
            }else if(headtype == 'co'){
                var cocode=row.cocode;
                var coname = row.coname;
                html += "<a href='http://<%=citycode%>.cityhouse.cn/shop/"+cocode+".html' target='_blank'><font color='blue'>"+coname+"</font></a>";
            }
            return html;
        }
        function getHeadportraitOperation(index){
            var html = "";
            html += "<a href='javascript:void(0);' onclick='passHeadImage(" + index + ")'><font color='blue'>通过审核</font></a>"  ;
            html += "|<a href='javascript:void(0);' onclick='refuseHeadImage(" + index + ")'><font color='blue'>拒绝审核</font></a>"  ;
            return html;
        }


        function passHeadImage(index){
            var rows = $('#headportrait_ids').datagrid('getRows');
            var row = rows[index];
            var id = row.id;
            var headtype = row.headtype;
            var imagefile = row.imagefile;
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/headportrait/checkOne',
                data:{"id":id,"flag":1,"headtype":headtype},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.result  == 'OK'){
                           // thumbnail(headtype,imagefile);
                            $.messager.alert("提示","通过审核成功!");
                            findHeadportraitReload();
                        }

                    }
                }
            });
        }
        function refuseHeadImage(index){
            var rows = $('#headportrait_ids').datagrid('getRows');
            var row = rows[index];
            var id = row.id;
            var headtype = row.headtype;
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/headportrait/checkOne',
                data:{"id":id,"flag":-1,"headtype":headtype},
                dataType:"json",
                success:function(data){
                    var json= data;
                    if(json != ""){
                        if(json.result == 'OK'){
                            $.messager.alert("提示","拒绝审核成功!");
                            findHeadportraitReload();
                        }
                    }
                }
            });
        }

        function thumbnail(headtype,picname){
            var str1 = $.trim(picname);
            var str2 = $.replace(str1,"'","");
            var str3 = encodeURIComponent(str2);
            if(headtype == 'reguser'|| headtype=='couser'){
                window.location.href='http://10.10.2.1:9000/thumbnail.jsp?type=user&picname='+str3+'&citycode=<%=citypinyin%>';
            }else if(headtype == 'co'){
                window.location.href='http://10.10.2.1:9000/thumbnail.jsp?type=co&picname='+str3+'&citycode=<%=citypinyin%>';
            }
        }
    </script>

    <title>头像审核</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div data-options="region:'north',border:false" style="height:70px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:65px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 45px;">
                <legend class="legend">查询条件</legend>
                <label>&nbsp;&nbsp;&nbsp;审核状态:</label>
                <input id="head_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '个人头像',value: 'reguser',selected:true},
                   		  {label: '经纪人头像',value: 'couser'},
                   		  {label: '公司logo',value: 'co'}]" />

                <label>&nbsp;&nbsp;&nbsp;审核状态:</label>
                <input id="statusValue" name="statusValue" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '未审核',value: '0',selected:true},
                   		  {label: '通过审核',value: '1'},
                   		  {label: '拒绝审核',value: '-1'},
                   		  {label: '全部',value: ''}]" />
                <label>&nbsp;&nbsp;&nbsp;手机号:</label>
                <input id="phone_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;上传时间:</label>
                <input id="startDate"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                    id="endDate" style="width: 120px; height: 20px;"
                    class="easyui-datebox" />
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"  onclick="findHeadportraitSeachFn()" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >

        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true" fit="true"
               rownumbers="true" fitColumns="true" border="false" data-options=" emptyMsg:'<span style=\'color:red\' >无记录</span>',pageSize:20"
               url=""
               id="headportrait_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true,sortable:true" width="2%"></th>
                <th data-options="field:'imageurl',sortable:true,formatter:function(value,row,index){
                				    return getHeadImg(row);
			                 }" width="auto">头像</th>
                <th data-options="field:'flag',sortable:true,formatter: function(value,row,index){
                				if(row.flag != null){
                					 if(row.flag == '0' || row.flag == ''){
                					    return '待审核';
                					 }else if(row.flag == '1'){
                                    return '通过审核';
                					 }else if(row.flag == '-1'){
                					    return '拒绝审核';
                					 }
			                 }else{
                                 return '待审核';
			                 }
                        }" width="5%">审核状态</th>
                <th data-options="field:'headtype_s',sortable:true,formatter: function(value,row,index){
                				if(row.headtype != null ){
                				  return getInfoURL(row);
                				}
                        }" width="10%">用户名</th>


                <th data-options="field:'uploadtime',sortable:true,formatter: function(value,row,index){
                                if(null != row.uploadtime && row.uploadtime != ''){
			                      	return dateFormat(row.uploadtime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
                        }" width="10%">上传时间</th>
                <th data-options="field:'operation',sortable:true,formatter: function(value,row,index){
                			return getHeadportraitOperation(index);
			        }" width="10%">操作</th>
            </tr>
            </thead>
        </table>
    </div>

</div>
</body>
</html>
