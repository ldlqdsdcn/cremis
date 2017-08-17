<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.cityre.edi.mis.base.entity.cpo.CityPo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String  uid = request.getParameter("uid").toString();
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
            findCouserInfoSeachFn();
        });


        function findCouserInfoSeachFn() {
            var opts = $("#perdaycount_ids").datagrid("options");
            opts.url = "perdaycount";
            var uid = '<%=uid%>';
            $("#perdaycount_ids").datagrid(
                "load",{
                    uid:uid
            });
        }

    </script>

    <title>当前月每日房源数量</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">


    <div data-options="region:'center',border:false" >

        <table class="easyui-datagrid"  pagination="false" fit="true"
               rownumbers="true" fitColumns="true" border="false" data-options=" emptyMsg:'<span style=\'color:red\' >无记录</span>'"
               url=""
               id="perdaycount_ids"
               style="overflow:auto;width:auto; ">
            <thead>
            <tr>
                <th data-options="field:'applytime',sortable:true,formatter: function(value,row,index){
                if(null != row.ym && row.ym != ''){
                    return dateFormat(row.ym,'yyyy-mm-dd');
                        }else{
                            return '';
                        }
                }" width="auto">日期</th>

                <th data-options="field:'qnt',sortable:true" width="auto">房源数量</th>
            </tr>
            </thead>
        </table>
    </div>

</div>
</body>
</html>
