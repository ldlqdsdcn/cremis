<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.cityre.edi.mis.base.entity.cpo.CityPo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    CityPo cityPo =(CityPo)session.getAttribute("currentCity");
    String citycode = cityPo.getCityid();
    String citypinyin = cityPo.getCityPinYin();
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
            getcocl_select();

          findCrmcoSeachFn();
        });
        function getcocl_select(){
            var url ="<%=path%>/merchant/crmco/getcocl";
            $("#cocl_cbx").combobox({
                panelHeight: "300px",
                mode:"remote",
                url:url,
                valueField: "cocl",
                textField: "coinfo",
                loadFilter:function(data){
                    data.unshift({
                        cocl:"",
                        coinfo:"--请选择--"
                    });
                    return data;
                }
            });
        }
        function getcocl_edit(){
            var url ="<%=path%>/merchant/crmco/getcocl";
            $("#ed1_cbx").combobox({
                panelHeight: "300px",
                mode:"remote",
                url:url,
                valueField: "cocl",
                textField: "coinfo",
                loadFilter:function(data){
                    data.unshift({
                        cocl:"",
                        coinfo:"--请选择--"
                    });
                    return data;
                }
            });
        }
        function findCrmcoSeachFn() {
            var opts = $("#crmco_ids").datagrid("options");
            opts.url = "<%=path%>/merchant/crmco/list";

            var d1 = $("#d1_cbx").combobox('getValue');
            var source = $("#source_cbx").combobox('getValue');
            var certp = $("#certp_cbx").combobox('getValue');
            var copermission = $("#copermission_cbx").combobox('getValue');
            var cocl = $("#cocl_cbx").combobox('getValue');
            var cert_from = $("#cert_from").datebox('getValue');
            var cert_to = $("#cert_to").datebox('getValue');
            var time_reg_from = $("#time_reg_from").datebox('getValue');
            var time_reg_to = $("#time_reg_to").datebox('getValue');
            var begin_time = $("#begin_time").datebox('getValue');
            var end_time = $("#end_time").datebox('getValue');
            var lastlogin_begin_time = $("#lastlogin_begin_time").datebox('getValue');
            var lastlogin_end_time = $("#lastlogin_end_time").datebox('getValue');
            var couid = $("#couid").val();
            var Sconame = $("#Sconame").val();
            var cotel = $("#cotel").val();
            $("#crmco_ids").datagrid(
                "load",{
                    d1:d1,
                    source:source,
                    certp:certp,
                    copermission:copermission,
                    cocl:cocl,
                    cert_from:cert_from,
                    cert_to:cert_to,
                    time_reg_from:time_reg_from,
                    time_reg_to:time_reg_to,
                    begin_time:begin_time,
                    end_time:end_time,
                    lastlogin_begin_time:lastlogin_begin_time,
                    lastlogin_end_time:lastlogin_end_time,
                    couid:couid,
                    Sconame:Sconame,
                    cotel:cotel
            });
        }

        function findCrmcoReload() {
            var opts = $("#crmco_ids").datagrid("options");
            opts.url = "<%=path%>/merchant/crmco/list";

            var d1 = $("#d1_cbx").combobox('getValue');
            var source = $("#source_cbx").combobox('getValue');
            var certp = $("#certp_cbx").combobox('getValue');
            var copermission = $("#copermission_cbx").combobox('getValue');
            var cocl = $("#cocl_cbx").combobox('getValue');
            var cert_from = $("#cert_from").datebox('getValue');
            var cert_to = $("#cert_to").datebox('getValue');
            var time_reg_from = $("#time_reg_from").datebox('getValue');
            var time_reg_to = $("#time_reg_to").datebox('getValue');
            var begin_time = $("#begin_time").datebox('getValue');
            var end_time = $("#end_time").datebox('getValue');
            var lastlogin_begin_time = $("#lastlogin_begin_time").datebox('getValue');
            var lastlogin_end_time = $("#lastlogin_end_time").datebox('getValue');
            var couid = $("#couid").val();
            var Sconame = $("#Sconame").val();
            var cotel = $("#cotel").val();
            $("#crmco_ids").datagrid(
                "reload",{
                    d1:d1,
                    source:source,
                    certp:certp,
                    copermission:copermission,
                    cocl:cocl,
                    cert_from:cert_from,
                    cert_to:cert_to,
                    time_reg_from:time_reg_from,
                    time_reg_to:time_reg_to,
                    begin_time:begin_time,
                    end_time:end_time,
                    lastlogin_begin_time:lastlogin_begin_time,
                    lastlogin_end_time:lastlogin_end_time,
                    couid:couid,
                    Sconame:Sconame,
                    cotel:cotel
                });
        }


        //操作
        function getCoOperation(index) {
            var rows = $('#crmco_ids').datagrid('getRows');
            var row = rows[index];
            var  html ="";
            html += "<a href='javascript:void(0);' onclick='crmco_agree(" + index + ")'><font color='blue'>通过</font></a>"  ;
            html += "|<a href='javascript:void(0);' onclick='crmco_refuse(" + index + ")'><font color='blue'>拒绝</font></a>";
            html += "|<a href='javascript:void(0);' onclick='editDetail(" + index + ")'><font color='blue'>修改</font></a>";
            /*var uid = row.uid;
            var cocode = row.cocode;
            if(uid == cocode){*/
                html += "|<a href='javascript:void(0);' onclick='changeAdmin(" + index + ")'><font color='blue'>变更管理员</font></a>";
//            }
            return html;
        }

        //通过审核
        function crmco_agree(index) {
            var rows = $('#crmco_ids').datagrid('getRows');
            var row = rows[index];
            var cocode = row.cocode;
            var couid = row.couid;
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/crmco/crmco_agree',
                data:{"cocode":cocode,"couid":couid},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.result == 'OK'){
                            $.messager.show({
                                title:'提示',
                                msg:'通过审核成功!',
                                timeout:5000,
                                showType:'slide'
                            });
                           // $.messager.alert("提示","通过审核成功!");
                            findCrmcoReload();
                        }

                    }
                }
            });
        }
        //弹出拒绝 对话框
        function crmco_refuse(index) {
            var rows = $('#crmco_ids').datagrid('getRows');
            var row = rows[index];
            var uid = row.uid;
            var coname = row.coname;
            var cocode = row.cocode;
            $("#crmco_cocode").val(cocode);
            $("#crmco_uid_td").html(uid);
            $("#crmco_coname_td").html(coname);
            $('#crmco_refuseDig').window('open');
        }
        //拒绝操作
        function eidt_crmco_refuse() {
            var reason =  $('#crmco_refuse_IN').val();
            if(reason == null || reason ==''){
                $.messager.alert("提示","请填写拒绝原因!");
                return;
            }
            var cocode = $("#crmco_cocode").val();
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/crmco/crmco_refuse',
                data:{"cocode":cocode,"reason":reason},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.result == 'OK'){
                            $.messager.show({
                                title:'提示',
                                msg:'已拒绝！',
                                timeout:5000,
                                showType:'slide'
                            });
                            findCrmcoReload();
                        }
                    }
                }
            });
        }
        //弹出修改co信息的 对话框
        function editDetail(index) {
            getcocl_edit();
            var rows = $('#crmco_ids').datagrid('getRows');
            var row = rows[index];
            $("#econame_IN").textbox("setValue",row.coname);
            $("#ed1_cbx").combobox("setValue",row.cocl);
            $("#ecoaddr_IN").textbox("setValue",row.coaddr);
            $("#ecobrief_IN").textbox("setValue",row.cobrief);
            $("#ecowebsite_IN").textbox("setValue",row.cowebsite);
            $("#ecoemail_IN").textbox("setValue",row.coEmail);
            $("#ecotel_IN").textbox("setValue",row.coTel);
            $("#ecoMob_IN").textbox("setValue",row.coMob);
            $("#ecofax_IN").textbox("setValue",row.cofax);
            $("#ecocode_IN").val(row.cocode);
            $('#editCoDetailDig').window('open');
        }
        //修改co 的信息
        function eidt_crmco_detail(){
           var coname= $("#econame_IN").textbox("getValue");
            var d1= $("#ed1_cbx").combobox("getValue");
            var coaddr= $("#ecoaddr_IN").textbox("getValue");
            var cobrief=  $("#ecobrief_IN").textbox("getValue");
            var cowebsite=   $("#ecowebsite_IN").textbox("getValue");
            var coemail=   $("#ecoemail_IN").textbox("getValue");
            var cotel=   $("#ecotel_IN").textbox("getValue");
            var coMob=   $("#ecoMob_IN").textbox("getValue");
            var cofax=   $("#ecofax_IN").textbox("getValue");
            var cocode =$("#ecocode_IN").val();
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            if(coname ==''){
                $.messager.alert("提示","请输入公司名称！");
                return ;
            }else if(coemail != ''){
                if(!reg.test(coemail)){
                $.messager.alert("提示","请输入正确的邮箱！");
                return ;
                }
            }
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/crmco/crmco_edit',
                data:{"coname":coname,"d1":d1,"coaddr":coaddr,"cobrief":cobrief,"cowebsite":cowebsite,"coemail":coemail,"cotel":cotel,"coMob":coMob,"cofax":cofax,"cocode":cocode},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.flag){
                            $('#editCoDetailDig').window('close');

                            $.messager.show({
                                title:'提示',
                                msg:json.msg,
                                timeout:5000,
                                showType:'slide'
                            });

                           // $.messager.alert("提示",,"",);
                            findCrmcoReload();

                        }else{
                            $.messager.alert("提示",json.msg);
                        }
                    }
                }
            });
        }
        //删除 co
        function delete_crmco(){
            var cocode =$("#ecocode_IN").val();
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/crmco/crmco_delete',
                data:{"cocode":cocode},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.flag){
                            $.messager.show({
                                title:'提示',
                                msg:json.msg,
                                timeout:5000,
                                showType:'slide'
                            });
                            findCrmcoReload();

                        }else{
                            $.messager.alert("提示",json.msg);
                        }
                    }
                }
            });
        }



        //店铺链接
        function getConameURL(row){
            var cocode = row.cocode;
            var coname = row.coname;
            var html = "";
            html  =html+ "<a href='http://<%=citycode%>.cityhouse.cn/shop/"+cocode+".html' target='_blank'><font color='blue'>"+coname+"</font></a>"  ;
            return html;
        }
        //跳转到  经纪人审核页面     （未做）
        function getAdminURL(row) {
            var html ="";
            var name = row.name;
            var nickname = row.nickname;
            var couid= row.couid;
            var coname_new = "";
            if(name != null && name != ''){
                coname_new = name;
            }else  if(nickname != null && nickname != ''){
                coname_new = nickname;
            }else  if(couid != null && couid != ''){
                coname_new =couid;
            }
            if(coname_new != ''){
                html = html+"<a href='<%=path%>/base/couserInfo/couserInfo.jsp?couid="+couid+"&flag_select=all&user_type=all' target='_blank'><font color='blue'>"+coname_new+"</font></a>";
            }
            return html;
        }
        //经纪人数量链接     跳转到   经纪人审核界面
        function  getCouserCntURL(row) {
            var couserCnt = row.couserCnt;
            var coname = row.coname;
            var html = "";
            if(couserCnt != null && couserCnt != ''){
                html = html+"<a href='<%=path%>/base/couserInfo/couserInfo.jsp?coname="+coname+"&flag_select=all' target='_blank'><font color='blue'>"+(couserCnt+1)+"</font></a>";
            }else{
                html = html+"<a href='<%=path%>/base/couserInfo/couserInfo.jsp?coname="+coname+"&flag_select=all' target='_blank'><font color='blue'>1</font></a>";
            }
            return html;
        }

        //认证资料链接
        function getCertPhotoURL(row) {
            var certPhoto = row.certPhoto;
            var str = "";
            var html="";
            if(certPhoto != null && certPhoto != ''){
                var certification = row.certification;
                if(certification == '1'){
                    str = "√";
                }else if(certification == '-1'){
                    str = "×";
                }else{
                    str = "待认证";
                }
            }
            var coname = row.coname;
            html = html+"<a target='_blank' href='<%=path%>/base/companyCheck/companyCheck.jsp?state=all&coname="+coname+"'><font color='green'>"+str+"</font></a>";
            return html;
        }

        function closeDig() {
            $('#crmco_refuseDig').window('close');
        }
        //弹出变更公司管理员 对话框
        function changeAdmin(index) {
            getCoManage();
            var rows = $('#crmco_ids').datagrid('getRows');
            var row = rows[index];
            $("#rconame_IN").textbox("setValue",row.coname);
            $("#ruid_IN").textbox("setValue",row.couid);
            $("#rname_IN").textbox("setValue",row.name);
            $("#rnickname_IN").textbox("setValue",row.nickname);
            $('#remanageDig').window('open');
        }
        function getCoManage(){
            var url ="<%=path%>/merchant/crmco/getCoManage";
            $("#ruser_cbx").combobox({
                panelHeight: "300px",
                mode:"remote",
                url:url,
                valueField: "uid",
                textField: "uid",
                loadFilter:function(data){
                    data.unshift({
                        uid:"",
                        uid:"--请选择--"
                    });
                    return data;
                }
            });
        }
        function change_co_manager() {
            var olduid = $("#ruid_IN").val();
            var newuid = $("#ruser_cbx").combobox("getValue");
            if(newuid == ''){
                $.messager.alert("提示","请选择管理员！");
                return;
            }
            var cocode = $("#rcocode_IN").val();
            $.ajax({
                type:'POST',
                url:'<%=path%>/merchant/crmco/crmco_remanage',
                data:{"cocode":cocode,"olduid":olduid,"newuid":newuid},
                dataType:"json",
                success:function(data){
                    var json=data;
                    if(json != ""){
                        if(json.flag){
                            $.messager.show({
                                title:'提示',
                                msg:json.msg,
                                timeout:5000,
                                showType:'slide'
                            });
                            findCrmcoReload();

                        }else{
                            $.messager.alert("提示",json.msg);
                        }
                    }
                }
            });
        }
    </script>

    <title>公司用户审核</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">

    <div data-options="region:'north',border:false" style="height:90px;overflow:hidden;background:#fafafa;">
        <div class="easyui-panel activityListClass" style="overflow:hidden;font-size:12px;height:85px"
             data-options="border:false,iconCls:'icon-save'">
            <fieldset class="fieldset" style="height: 62px;">
                <legend class="legend">查询条件</legend>

                <label>&nbsp;&nbsp;&nbsp;机构状态:</label>
                <input id="d1_cbx" class="easyui-combobox"
                       style="width: 120px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '等待审核机构',value: '0',selected:true},
                   		  {label: '已通过审核机构',value: '1'},
                   		  {label: '未通过审核机构',value: '2'},
                   		   {label: '全部',value: ''},
                   		  ]" />
                <label>&nbsp;&nbsp;&nbsp;用户来源:</label>
                <input id="source_cbx" name="statusValue" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '网站注册',value: '0',selected:true},
                   		  {label: '全部',value: ''}]" />

                <label>&nbsp;&nbsp;&nbsp;是否认证:</label>
                <input id="certp_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '全部',value: '',selected:true},
                   		  {label: '待认证',value: '0'},
                   		  {label: '已认证',value: '2'},
                   		  {label: '通过认证',value: '1'},
                   		  {label: '拒绝认证',value: '-1'}]" />

                <label>&nbsp;&nbsp;&nbsp;开通小区中介:</label>
                <input id="copermission_cbx" class="easyui-combobox"
                       style="width: 100px;"
                       data-options="panelHeight:'auto',valueField:'value',textField: 'label',
                   data: [{label: '全部',value: '',selected:true},
                   		  {label: '开通',value: '1'},
                   		  {label: '未开通',value: '0'}]" />
                <label>&nbsp;&nbsp;&nbsp;类别:</label>
                <input id="cocl_cbx" class="easyui-combobox"
                       style="width: 100px;"/>
                <label>&nbsp;&nbsp;&nbsp;认证日期:</label>
                <input id="cert_from"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="cert_to" style="width: 120px; height: 20px;"
                        class="easyui-datebox" />
                <label>&nbsp;&nbsp;&nbsp;注册日期::</label>
                <input id="time_reg_from"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="time_reg_to" style="width: 120px; height: 20px;"
                        class="easyui-datebox" />
                <label>&nbsp;&nbsp;&nbsp;审核日期:</label>
                <input id="begin_time"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="end_time" style="width: 120px; height: 20px;"
                        class="easyui-datebox" /><br>
                <label>&nbsp;&nbsp;&nbsp;最后登录日期:</label>
                <input id="lastlogin_begin_time"
                       style="width: 120px; height: 20px;" class="easyui-datebox" />
                -
                <input
                        id="lastlogin_end_time" style="width: 120px; height: 20px;"
                        class="easyui-datebox" />
                <label>&nbsp;&nbsp;&nbsp;uid:</label>
                <input id="couid" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp;公司名称：</label>
                <input id="Sconame" style="width:150px;height:23px;" class="easyui-textbox" />
                <label>&nbsp;&nbsp;&nbsp; 电话：</label>
                <input id="cotel" style="width:150px;height:23px;" class="easyui-textbox" />
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"  onclick="findCrmcoSeachFn()" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </fieldset>
        </div>
    </div>
    <div data-options="region:'center',border:false" >

        <table class="easyui-datagrid"  toolbar="#activityTools" pagination="true" fit="true"
               rownumbers="true" fitColumns="true" border="false" data-options="pageSize:50"
               url=""
               id="crmco_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'couid',sortable:true" width="auto">用户名</th>
                <th data-options="field:'conameURL',sortable:true,formatter:function(value,row,index){
                				    return getConameURL(row);
			                 }" width="auto">店铺名称</th>
                <th data-options="field:'adminURL',sortable:true,formatter:function(value,row,index){
                				    return getAdminURL(row);
			                 }" width="auto">管理员</th>
                <th data-options="field:'couserCntURL',sortable:true,formatter:function(value,row,index){
                				    return getCouserCntURL(row);
			                 }" width="auto">经纪人数量</th>
                <th data-options="field:'coinfo',sortable:true" width="auto">类别</th>
                <th data-options="field:'coaddr',sortable:true" width="auto">办公地址</th>
                <th data-options="field:'coTel',sortable:true" width="auto">电话</th>
                <th data-options="field:'coEmail',sortable:true" width="auto">Email</th>
                <th data-options="field:'applytime',sortable:true,formatter: function(value,row,index){
                                if(row.costate != null){
                				        if(row.costate == '0'){
                				          return '拒绝审核';
                				        }else if(row.costate == '1'){
                                      return '通过审核';
                				        }else{
                				           return '待审核';
                				        }
                				   }else{
                				       return '待审核';
                				   }
                        }" width="auto">状态</th>
                <th data-options="field:'regTime',sortable:true,formatter: function(value,row,index){
                                if(null != row.regTime && row.regTime != ''){
			                      	return dateFormat(row.regTime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
                        }" width="auto">注册时间</th>
                <th data-options="field:'verifyuid',sortable:true" width="auto">审核人</th>
                <th data-options="field:'verifytime',sortable:true,formatter:function(value,row,index){
                				   if(null != row.verifytime && row.verifytime != ''){
			                      	return dateFormat(row.verifytime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="auto">审核时间</th>
                <th data-options="field:'updatetime',sortable:true,formatter:function(value,row,index){
                				    if(null != row.updatetime && row.updatetime != ''){
			                      	return dateFormat(row.updatetime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="auto">最后登录时间</th>
                <th data-options="field:'userlist_flag',sortable:true,formatter:function(value,row,index){
                				  if(row.uid != null && row.uid !=''){
                				     return '*';
                				  }else{
                                  return '';
                				    }
			                 }" width="auto">小区中介申请</th>
                <th data-options="field:'certPhotoURL',sortable:true,formatter:function(value,row,index){
                				   return getCertPhotoURL(row);
			                 }" width="auto">认证资料</th>
                <th data-options="field:'userlist_flag_uid',sortable:true,formatter:function(value,row,index){
                				   if(row.coimagefile != null && row.coimagefile !=''){
                				      return '*';
                				   }else{
                                 return '';
                				   }
			                 }" width="auto">上传logo</th>
                <th data-options="field:'tel_cocert_state',sortable:true,formatter:function(value,row,index){
                				   if(row.tel_cocert_state != null ){
                				        if(row.tel_cocert_state == '1'){
                				            return '已通过认证';
                				        }else if(row.tel_cocert_state == '-1'){
                                        return '未通过认证';
                				        }else{
                				            return '尚未认证';
                				        }
                				   }
			                 }" width="auto">电话认证</th>
                <th data-options="field:'tel_cocert_updatetime',sortable:true,formatter:function(value,row,index){
                				    if(null != row.tel_cocert_updatetime && row.tel_cocert_updatetime != ''){
			                      	return dateFormat(row.tel_cocert_updatetime,'yyyy-mm-dd HH:MM:ss');
			                      }else{
			                      	return '';
			                      }
			                 }" width="auto">电话认证时间</th>
                <th data-options="field:'operation',sortable:true,formatter: function(value,row,index){
                			return getCoOperation(index);
			        }" width="10%">操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="crmco_refuseDig" class="easyui-window"
         style="width: 50%; height: 60%; text-align: center;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'拒绝理由',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <fieldset class="fieldset">
            <legend class="legend">拒绝审核</legend>
            <table cellpadding="10px">
                <tr>
                    <td style="text-align: right">用户UID:</td>
                    <td style="text-align: left" id="crmco_uid_td"></td>
                </tr>

                <tr>
                    <td style="text-align: right">公司名称</td>
                    <td style="text-align: left" id="crmco_coname_td"></td>
                </tr>
                <tr>
                    <td style="text-align: right">拒绝理由</td>
                    <td style="text-align: left" >
                        <input id="crmco_refuse_IN" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:200px" />
                    </td>
                </tr>
            </table>
            <input type="hidden" id="crmco_cocode" />
            <a  href="javascript:void(0)"
                class="easyui-linkbutton" onclick="eidt_crmco_refuse();">拒绝审核</a>
            &nbsp;&nbsp;
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDig();">取消</a>
        </fieldset>
    </div>

    <div id="editCoDetailDig" class="easyui-window"
         style="width: 50%; height: 75%; text-align: center;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'编辑',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <fieldset class="fieldset">
            <legend class="legend">修改注册资料</legend>
            <table cellpadding="10px">
                <tr>
                    <td style="text-align: right">店铺名称:</td>
                    <td style="text-align: left" ><input id="econame_IN" class="easyui-textbox" /></td>
                </tr>

                <tr>
                    <td style="text-align: right">类别</td>
                    <td style="text-align: left" ><input id="ed1_cbx" class="easyui-combobox" /> </td>
                </tr>
                <tr>
                    <td style="text-align: right">办公地址</td>
                    <td style="text-align: left" ><input id="ecoaddr_IN" class="easyui-textbox"   /></td>
                </tr>
                <tr>
                    <td style="text-align: right">店铺介绍</td>
                    <td style="text-align: left" >
                        <input id="ecobrief_IN" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:200px" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right">网址</td>
                    <td style="text-align: left" ><input id="ecowebsite_IN" class="easyui-textbox"  /></td>
                </tr>
                <tr>
                    <td style="text-align: right">邮箱</td>
                    <td style="text-align: left" ><input id="ecoemail_IN" class="easyui-textbox"  /></td>
                </tr>
                <tr>
                    <td style="text-align: right">服务热线</td>
                    <td style="text-align: left" ><input id="ecotel_IN" class="easyui-textbox"   /></td>
                </tr>
                <tr>
                    <td style="text-align: right">值班手机</td>
                    <td style="text-align: left" ><input id="ecoMob_IN" class="easyui-textbox"  /></td>
                </tr>
                <tr>
                    <td style="text-align: right">传真</td>
                    <td style="text-align: left" ><input id="ecofax_IN" class="easyui-textbox"  /></td>
                </tr>
            </table>
            <input  type="hidden" id="ecocode_IN" />
            <a  href="javascript:void(0)"
                class="easyui-linkbutton" onclick="eidt_crmco_detail();">确认</a>
            &nbsp;&nbsp;
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="delete_crmco();">删除</a>
        </fieldset>
    </div>

    <div id="remanageDig" class="easyui-window"
         style="width: 20%; height: 40%; text-align: center;"
         data-options="closed:true,closable:true,maximinzable:false,minimizable:false,title:'编辑',
		    collapsible:false,modal:true,resizable:false,draggable:false,draggable:true ">
        <fieldset class="fieldset">
            <legend class="legend"></legend>
            <table cellpadding="10px">
                <tr>
                    <td style="text-align: right">公司名称:</td>
                    <td style="text-align: left" ><input id="rconame_IN" class="easyui-textbox"   readonly="readonly"/></td>
                </tr>
                <tr>
                    <td style="text-align: right">当前管理员:</td>
                    <td style="text-align: left" ><input id="ruid_IN" class="easyui-textbox"  readonly="readonly" /></td>
                </tr>
                <tr>
                    <td style="text-align: right">姓名:</td>
                    <td style="text-align: left" ><input id="rname_IN" class="easyui-textbox"  readonly="readonly" /></td>
                </tr>
                <tr>
                    <td style="text-align: right">昵称:</td>
                    <td style="text-align: left" ><input id="rnickname_IN" class="easyui-textbox"  readonly="readonly" /></td>
                </tr>
                <tr>
                    <td style="text-align: right">转换为</td>
                    <td style="text-align: left" ><input id="ruser_cbx" class="easyui-combobox" /> </td>
                </tr>

            </table>
            <input  type="hidden" id="rcocode_IN" />
            <a  href="javascript:void(0)"
                class="easyui-linkbutton" onclick="change_co_manager();">变更</a>
        </fieldset>
    </div>
</div>
</body>
</html>
