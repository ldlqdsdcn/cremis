<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.cityre.edi.mis.base.entity.cpo.CityPo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    CityPo cityPo =(CityPo)session.getAttribute("currentCity");
    String citycode = cityPo.getCityid();
    String citypinyin = cityPo.getCityPinYin();
    String couid = request.getParameter("couid");
    if(couid == null ){
        couid = "";
    }
    String flag_select = request.getParameter("flag_select");
    if(flag_select == null ){
        flag_select = "";
    }
    String user_type = request.getParameter("user_type");
    if(user_type == null ){
        user_type = "";
    }

    String coname = request.getParameter("coname");
    if(coname == null ){
        coname = "";
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

            var couid = '<%=couid%>';
            if(couid != ''){
                $("#couseruid_ids").textbox('setValue',couid);
            }
            var flag_select = '<%=flag_select%>';
            if(flag_select != ''){
                if(flag_select == 'all'){
                    $("#statusValue").combobox('setValue','');
                }else{
                    $("#statusValue").combobox('setValue',flag_select);
                }

            }
            var user_type = '<%=user_type%>';
            if(user_type != ''){
                if(user_type == 'all'){
                    $("#usertype_cbx").combobox('setValue','');
                }else{
                    $("#usertype_cbx").combobox('setValue',user_type);
                }

            }
            var coname = '<%=coname%>';
            if(coname != ''){
                $("#coname_ids").textbox('setValue',coname);
            }
            findCouserInfoSeachFn();
        });


        function findCouserInfoSeachFn() {
            var opts = $("#couserInfo_ids").datagrid("options");
            opts.url = "<%=path%>/mis/merchant/userInfo/list";



            var usertype = $("#usertype_cbx").combobox('getValue');
            var status = $("#statusValue").combobox('getValue');
            var certp = $("#certp_cbx").combobox('getValue');
            var permission = $("#permission_cbx").combobox('getValue');
            var regtimeStartDate = $("#regtimeStartDate").datebox('getValue');
            var regtimeEndDate = $("#regtimeEndDate").datebox('getValue');
            var certtimeStartDate = $("#certtimeStartDate").datebox('getValue');
            var certtimeEndDate = $("#certtimeEndDate").datebox('getValue');
            var logintimeStartDate = $("#logintimeStartDate").datebox('getValue');
            var logintimeEndDate = $("#logintimeEndDate").datebox('getValue');
            var couseruid = $("#couseruid_ids").val();
            var username = $("#username_ids").val();
            var phone = $("#phone_ids").val();
            var email = $("#email_ids").val();
            var coname = $("#coname_ids").val();
            var order = $("#order_cbx").combobox('getValue');
            var turn = false;
            if($('#turn_cb').is(':checked')) {
                 turn = true;
            }

            $("#couserInfo_ids").datagrid(
                "load",{
                    usertype:usertype,
                    status:status,
                    certp:certp,
                    permission:permission,
                    regtimeStartDate:regtimeStartDate,
                    regtimeEndDate:regtimeEndDate,
                    certtimeStartDate:certtimeStartDate,
                    certtimeEndDate:certtimeEndDate,
                    logintimeStartDate:logintimeStartDate,
                    logintimeEndDate:logintimeEndDate,
                    couseruid:couseruid,
                    username:username,
                    phone:phone,
                    email:email,
                    coname:coname,
                    order:order,
                    turn:turn
            });
        }



        function findCouserInfoReload() {
            var opts = $("#couserInfo_ids").datagrid("options");
            opts.url = "<%=path%>/mis/merchant/userInfo/list";



            var usertype = $("#usertype_cbx").combobox('getValue');
            var status = $("#statusValue").combobox('getValue');
            var certp = $("#certp_cbx").combobox('getValue');
            var permission = $("#permission_cbx").combobox('getValue');
            var regtimeStartDate = $("#regtimeStartDate").datebox('getValue');
            var regtimeEndDate = $("#regtimeEndDate").datebox('getValue');
            var certtimeStartDate = $("#certtimeStartDate").datebox('getValue');
            var certtimeEndDate = $("#certtimeEndDate").datebox('getValue');
            var logintimeStartDate = $("#logintimeStartDate").datebox('getValue');
            var logintimeEndDate = $("#logintimeEndDate").datebox('getValue');
            var couseruid = $("#couseruid_ids").val();
            var username = $("#username_ids").val();
            var phone = $("#phone_ids").val();
            var email = $("#email_ids").val();
            var coname = $("#coname_ids").val();
            var order = $("#order_cbx").combobox('getValue');
            var turn = false;
            if($('#turn_cb').is(':checked')) {
                turn = true;
            }

            $("#couserInfo_ids").datagrid(
                "load",{
                    usertype:usertype,
                    status:status,
                    certp:certp,
                    permission:permission,
                    regtimeStartDate:regtimeStartDate,
                    regtimeEndDate:regtimeEndDate,
                    certtimeStartDate:certtimeStartDate,
                    certtimeEndDate:certtimeEndDate,
                    logintimeStartDate:logintimeStartDate,
                    logintimeEndDate:logintimeEndDate,
                    couseruid:couseruid,
                    username:username,
                    phone:phone,
                    email:email,
                    coname:coname,
                    order:order,
                    turn:turn
                });
        }


        //uid链接     链接到用户个人信息
        function getUidURL(row){
            var uid = row.uid;
            var html = "";
            html += "<a href='<%=path%>/mis/merchant/agentCheck/getUserInfo?uid="+uid+"' target='_blank'><font color='blue'>"+uid+"</font></a>";
            return html;
        }
        //显示姓名或昵称链接
        function getUsernameURL(row){
            var uid = row.uid;
            var nickname = row.nickName;

            var html = "";
            if(nickname == null || nickname == ''){
                html ="无姓名或昵称";
            }else{
                var name = row.name;
                if(name == null || name ==''){
                    html += "<a target='_blank\' href='http://<%=citycode%>.cityhouse.cn/agency.html?uid="+uid+"'>昵称："+nickname+"</a>";
                }else{
                    html += "<a target='_blank\' href='http://<%=citycode%>.cityhouse.cn/agency.html?uid="+uid+"'>"+name+"</a>";
                }

            }
            return html;
        }
        //
        function getCoURL(row) {

        }

        //
        function getCertURL(row) {
            var certPhoto = row.certPhoto;
            var uid = row.uid;
            var html="";
            if(certPhoto != null && certPhoto != ''){
                var certification  = row.certification;
                if(certification == '1'){
                    html += "<a href='<%=path%>/base/agentCheck/agentCheck.jsp?state=all&co_name="+uid+"' target='_blank'><font color='blue'>√</font></a>";
                }else if(certification == '-1'){
                    html += "<a href='<%=path%>/base/agentCheck/agentCheck.jsp?state=all&co_name="+uid+"' target='_blank'><font color='blue'>×</font></a>";
                }else{
                    html += "<a href='<%=path%>/base/agentCheck/agentCheck.jsp?state=all&co_name="+uid+"' target='_blank'><font color='blue'>待认证</font></a>";
                }
            }
            return html;
        }
        //显示头像链接
        function getHeadportraitURL(row) {
            var headportrait = row.headportrait;
            var uid = row.uid;
            var html = "";
            if(headportrait != null && headportrait !=''){
                var headportrait_flag = row.headportrait_flag;
                if(headportrait_flag == 1){
                    html += "<a href='<%=path%>/base/headportrait/headportrait.jsp?head_type=couser&confirm_status=all&useuid="+uid+"' target='_blank'><font color='blue'>√</font></a>";
                }else if(headportrait_flag == -1){
                    html += "<a href='<%=path%>/base/headportrait/headportrait.jsp?head_type=couser&confirm_status=all&useuid="+uid+"' target='_blank'><font color='blue'>×</font></a>";
                }else{
                    html += "<a href='<%=path%>/base/headportrait/headportrait.jsp?head_type=couser&confirm_status=all&useuid="+uid+"' target='_blank'><font color='blue'>*</font></a>";
                }
            }
            return html;
        }
        //操作
        function getCouserInfoOperation(index) {
            var rows = $('#couserInfo_ids').datagrid('getRows');
            var row = rows[index];
            var  html ="";
            html += "<a href='javascript:void(0);' onclick='userlist_agree(" + index + ")'><font color='blue'>通过</font></a>"  ;
            html += "|<a href='javascript:void(0);' onclick='userlist_refuse(" + index + ")'><font color='blue'>拒绝</font></a>";
            html += "|<a href='javascript:void(0);' onclick='editTel(" + index + ")'><font color='blue'>修改手机</font></a>";
            var uid = row.uid;
            var cocode = row.cocode;
            if(uid == cocode){
                html += "|<a href='javascript:void(0);' onclick='changetoreguser(" + index + ")'><font color='blue'>经纪人转个人</font></a>";
            }
            return html;
        }
        //通过审核
        function userlist_agree(index){
            var rows = $('#couserInfo_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            $.ajax({
                type:'POST',
                url:'<%=path%>/mis/merchant/userInfo/userlist_agree',
                data:{"uid":uid},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.result == 'OK'){
                            $.messager.alert("提示","通过审核成功!");
                            findCouserInfoReload();
                        }

                    }
                }
            });
        }
        //弹出拒绝审核的对话框
        function userlist_refuse(index){
            var rows = $('#couserInfo_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            var adminstr = row.adminstr;
            var userlist_remark = row.userlist_remark;
            $("#uid_td").html(uid);
            if(adminstr == '*'){
                $("#isadmin_td").html("公司管理员");
                $("#warning_tr").show();
            }else{
                $("#isadmin_td").html("非公司管理员");
                $("#warning_tr").hide();
            }
            $("#refuseReason_IN").textbox("setValue",userlist_remark);
            $('#refuseReasonDig').window('open');
        }
        //填写拒绝审核 确认拒绝审核
        function eidtRefuseReason(){
            var reason =  $("#refuseReason_IN").val();
            if(reason == ''){
                $.messager.alert("提示","请填写拒绝原因!");
                return;
            }else{
                var uid = $("#uid_td").text();
                $.ajax({
                    type:'POST',
                    url:'<%=path%>/mis/merchant/userInfo/userlist_refuse',
                    data:{"uid":uid,"reason":reason},
                    dataType:"json",
                    success:function(data){
                        var json=data;
                        if(json != ""){
                            if(json.result == 'OK'){
                                $.messager.alert("提示","拒绝审核成功!");
                                $('#refuseReasonDig').window('close');
                                findCouserInfoReload();
                            }

                        }
                    }
                });

            }
        }
        //弹出编辑电话的对话框
        function editTel(index){

//            var rows = $('#couserInfo_ids').datagrid('getRows');
//            var row = rows[index];
//            var uid = row.uid;
//            var realname = row.name;
//            var nickname = row.nickName;
//            var phone = row.
//            $('#puid_td').html(uid);
//            $('#prealname_td').html(uid);
//            $('#pnickname_td').html(uid);
//            $('#pphone_IN').val(uid);
        }
        //经纪人转个人
        function changetoreguser(index){
            var rows = $('#couserInfo_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;

            $.messager.confirm('Confirm', '确认进行经纪人转个人的操作？注意：一旦进行此项操作将无法逆转', function(r){
                if (r){
                    $.ajax({
                        type:'POST',
                        url:'<%=path%>/mis/merchant/userInfo/changetoreguser',
                        data:{"uid":uid},
                        dataType:"json",
                        success:function(data){
                            var json=data;
                            if(json != ""){
                                    $.messager.alert("提示",json.result);
                                    findCouserInfoReload();
                            }
                        }
                    });
                }
            });
        }
        function closeDig() {
            $('#refuseReasonDig').window('close');
        }
        function closePhoneDig() {
            $('#changePhoneDig').window('close');
        }
    </script>

    <title>经纪人审核</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div data-options="region:'north',border:false" style="height:90px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:85px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 62px;">
                <legend class="legend">查询条件</legend>

                <label>&nbsp;&nbsp;&nbsp;用户类型:</label>
                <input id="usertype_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '全部',value: '',selected:true},
                   		  {label: '独立经纪人',value: '2'},
                   		  {label: '公司经纪人',value: '3'},
                   		   {label: '公司管理员',value: '4'},
                   		  ]" />
                <label>&nbsp;&nbsp;&nbsp;审核状态:</label>
                <input id="statusValue" name="statusValue" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '未审核',value: '0',selected:true},
                   		  {label: '通过审核',value: '1'},
                   		  {label: '拒绝审核',value: '-1'},
                   		  {label: '全部',value: ''}]" />

                <label>&nbsp;&nbsp;&nbsp;认证资料:</label>
                <input id="certp_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '全部',value: '',selected:true},
                   		  {label: '待认证',value: '0'},
                   		  {label: '已认证',value: '2'},
                   		  {label: '通过认证',value: '1'},
                   		  {label: '拒绝认证',value: '-1'}]" />

                <label>&nbsp;&nbsp;&nbsp;开通小区中介:</label>
                <input id="permission_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '全部',value: '',selected:true},
                   		  {label: '开通',value: '1'},
                   		  {label: '未开通',value: '0'}]" />
                <label>&nbsp;&nbsp;&nbsp;申请经纪人日期:</label>
                <input id="regtimeStartDate"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="regtimeEndDate" style="width: 120px; height: 20px;"
                        class="easyui-datebox" />
                <label>&nbsp;&nbsp;&nbsp;审核日期:</label>
                <input id="certtimeStartDate"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="certtimeEndDate" style="width: 120px; height: 20px;"
                        class="easyui-datebox" />
                <label>&nbsp;&nbsp;&nbsp;最后登录日期:</label>
                <input id="logintimeStartDate"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="logintimeEndDate" style="width: 120px; height: 20px;"
                        class="easyui-datebox" /><br>
                <label>&nbsp;&nbsp;&nbsp;uid:</label>
                <input id="couseruid_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;姓名或昵称:</label>
                <input id="username_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;电话:</label>
                <input id="phone_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;email:</label>
                <input id="email_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;所属公司:</label>
                <input id="coname_ids" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;排序:</label>
                <input id="order_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '默认',value: '0',selected:true},
                   		  {label: '最后登录时间',value: '1'},
                   		  {label: '电话认证时间',value: '2'}]" />
                <label>&nbsp;&nbsp;&nbsp;倒序:</label>
                <input type="checkbox" id="turn_cb" name="turn" value="1"   />
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"  onclick="findCouserInfoSeachFn()" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >

        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true" fit="true"
               rownumbers="true" fitColumns="true" border="false" data-options="pageSize:50"
               url=""
               id="couserInfo_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true,sortable:true" width="2%"></th>
                <th data-options="field:'uidURL',sortable:true,formatter:function(value,row,index){
                				    return getUidURL(row);
			                 }" width="auto">用户名</th>
                <th data-options="field:'usernameURL',sortable:true,formatter:function(value,row,index){
                				    return getUsernameURL(row);
			                 }" width="auto">姓名</th>
                <th data-options="field:'coURL',sortable:true,formatter:function(value,row,index){
                				    return getCoURL(row);
			                 }" width="auto">所属公司</th>
                <th data-options="field:'adminstr',sortable:true" width="auto">是否管理员</th>
                <th data-options="field:'address',sortable:true" width="auto">公司地址</th>
                <th data-options="field:'tel',sortable:true" width="auto">电话</th>
                <th data-options="field:'email',sortable:true" width="auto">Email</th>
                <th data-options="field:'applytime',sortable:true,formatter: function(value,row,index){
                                if(null != row.applytime && row.applytime != ''){
			                      	return dateFormat(row.applytime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
                        }" width="auto">成为经纪人时间</th>
                <th data-options="field:'logintm',sortable:true,formatter: function(value,row,index){
                                if(null != row.logintm && row.logintm != ''){
			                      	return dateFormat(row.logintm,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
                        }" width="auto">最后登录时间</th>
                <th data-options="field:'adapplay',sortable:true" width="auto">小区中介申请</th>
                <th data-options="field:'certInfo',sortable:true,formatter:function(value,row,index){
                				    return getCertURL(row);
			                 }" width="auto">认证资料</th>
                <th data-options="field:'headportraitURL',sortable:true,formatter:function(value,row,index){
                				    return getHeadportraitURL(row);
			                 }" width="auto">个人头像</th>
                <th data-options="field:'userlist_flag',sortable:true,formatter:function(value,row,index){
                				   if(row.userlist_flag != null){
                				        if(row.userlist_flag == '-1'){
                				          return '拒绝审核';
                				        }else if(row.userlist_flag == '1'){
                                      return '通过审核';
                				        }else{
                				           return '待审核';
                				        }
                				   }else{
                				       return '待审核';
                				   }
			                 }" width="auto">状态</th>
                <th data-options="field:'comStatus',sortable:true,formatter:function(value,row,index){
                				   if(row.coname != null ){
                                    if(row.costate != null ){
                                        if(row.costate == '1'){
                                            return '通过审核';
                                        }else if(row.costate == '-1'){
                                            return '拒绝审核';
                                        }else{
                                             return '待审核';
                                        }
                                    }
                				   }
			                 }" width="auto">加入公司状态</th>
                <th data-options="field:'userlist_flag_uid',sortable:true" width="auto">审核人</th>
                <th data-options="field:'userlist_flag_time',sortable:true,formatter:function(value,row,index){
                				    if(null != row.userlist_flag_time && row.userlist_flag_time != ''){
			                      	return dateFormat(row.userlist_flag_time,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="auto">审核时间</th>
                <th data-options="field:'tel_cert_state',sortable:true,formatter:function(value,row,index){
                				   if(row.tel_cert_state != null ){
                				        if(row.tel_cert_state == '1'){
                				            return '已通过认证';
                				        }else if(row.tel_cert_state == '-1'){
                                        return '未通过认证';
                				        }else{
                				            return '尚未认证';
                				        }
                				   }
			                 }" width="auto">电话认证</th>
                <th data-options="field:'imageurl',sortable:true,formatter:function(value,row,index){
                				    if(null != row.tel_cert_updatetime && row.tel_cert_updatetime != ''){
			                      	return dateFormat(row.tel_cert_updatetime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="auto">电话认证时间</th>
                <th data-options="field:'operation',sortable:true,formatter: function(value,row,index){
                			return getCouserInfoOperation(index);
			        }" width="10%">操作</th>
            </tr>
            </thead>
        </table>
    </div>


    <div id="refuseReasonDig" class="easyui-window"
         style="width: 50%; height: 60%; text-align: center;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'拒绝理由',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <fieldset class="fieldset">
            <legend class="legend">拒绝审核</legend>
            <table cellpadding="10px">
                <tr>
                    <td style="text-align: right">用户UID:</td>
                    <td style="text-align: left" id="uid_td"></td>
                </tr>
                <tr>
                    <td style="text-align: right">审核状态:</td>
                    <td style="text-align: left">
                      拒绝审核
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right">是否为公司管理员</td>
                    <td style="text-align: left" id="isadmin_td"></td>
                </tr>

                <tr id="warning_tr">
                    <td colspan="2"><font color="red">注：该经纪人为公司管理员，如果拒绝该经纪人，则该经纪人管理的公司自动成为拒绝审核状态，公司下的所有经纪人自动成为独立经纪人。</font></td>

                </tr>
                <tr>
                    <td style="text-align: right">拒绝理由</td>
                    <td style="text-align: left" >
                        <input id="refuseReason_IN" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:200px" />
                    </td>
                </tr>
            </table>
            <a  href="javascript:void(0)"
               class="easyui-linkbutton" onclick="eidtRefuseReason();">拒绝审核</a>
            &nbsp;&nbsp;
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDig();">取消</a>
        </fieldset>
    </div>


    <div id="changePhoneDig" class="easyui-window"
         style="width: 50%; height: 60%; text-align: center;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'修改',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <fieldset class="fieldset">
            <legend class="legend">修改电话</legend>
            <input type="hidden" id="orderId" value=""/>
            <table cellpadding="10px">
                <tr>
                    <td style="text-align: right">用户UID:</td>
                    <td style="text-align: left" id="puid_td"></td>
                </tr>
                <tr>
                    <td style="text-align: right">真实姓名：:</td>
                    <td style="text-align: left" id="prealname_td">

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right">昵称：</td>
                    <td style="text-align: left" id="pnickname_td"></td>
                </tr>
                <tr>
                    <td style="text-align: right">电话</td>
                    <td style="text-align: left" >
                        <input id="pphone_IN" class="easyui-textbox"  style="width:300px;height:200px" />
                    </td>
                </tr>
            </table>
            <a  href="javascript:void(0)"
                class="easyui-linkbutton" onclick="eidtPhone();">拒绝审核</a>
            &nbsp;&nbsp;
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closePhoneDig();">取消</a>
        </fieldset>
    </div>


</div>
</body>
</html>
