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
        $(function(){
            var borndate  = '${userInfo.bornDate}';
            if(borndate != null && borndate != ""){
                borndate = dateFormat(borndate,'yyyy-mm-dd HH:MM:ss');
                $("#borndateTD").html(borndate);
            }
            var regtime = '${userInfo.regtime}';
            if(regtime != null && regtime != ""){
                regtime =dateFormat(regtime,'yyyy-mm-dd HH:MM:ss');
                $("#regtimeTD").html(regtime);
            }
            /*var cnt ='${userInfo.sourceCnt}';

            var uid = '${userInfo.uid}';
            var html = "";
            if(cnt >0){
                html += "<a href='<%=path%>/base/couserInfo/perdaycount.jsp?uid="+uid+"' target='_blank'><font color='blue'>"+cnt+"</font></a>";
            }
            $("#cntTD").html(html);*/
        });
    </script>

    <title>用户信息详情</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height:400px;overflow:hidden;background:#fafafa;">

        <fieldset class="fieldset" style="height:380px">
            <legend class="legend">个人信息</legend>
            <table id="userInfoTb" >
                <tr><td>信息类型:</td>
                    <td colspan="3"> 注册用户</td><td rowspan = "5"><img width='250' height='250' src="${userInfo.headportrait}"/></td>
                </tr>
                <tr><td>姓名：</td>
                    <td>${userInfo.name} </td>
                    <td>昵称：</td>
                    <td >${userInfo.nickname} </td>
                </tr>
                <tr><td>性别：</td>
                    <td>${userInfo.sex}</td>
                    <td>出生日期：</td>
                    <td id="borndateTD"></td>
                </tr>
                <tr><td>证件类型：</td>
                    <td>${userInfo.idcl}</td>
                    <td>证件号码：</td>
                    <td>${userInfo.idno}</td>
                </tr>
                <tr><td>电话：</td><td> ${userInfo.telno}</td>
                    <td>邮箱：</td><td>${userInfo.email}</td>
                </tr>
                <tr><td>注册时间：</td><td id="regtimeTD"></td>
                </tr>
                <tr><td>房源数量：</td>
                    <td>
                    <a href='<%=path%>/base/registeredUser/perdaycount.jsp?uid=${userInfo.uid}' target='_blank'><font color='blue'>${userInfo.sourceCnt}</font></a>
                </td>
                </tr>
            </table>
        </fieldset>
    </div>
</div>
</body>
</html>
