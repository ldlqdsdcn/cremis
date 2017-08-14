<%--
  Created by 刘大磊.
  User: 刘大磊
  Date: 2017/6/30
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>口号</title>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
    <%@include file="/inc/highcharts_js.jsp" %>
    <style type="text/css" media="all">
        #notice tr td p{
            font-size: 22pt;
            text-align: center;
            color: #0000FF;
            font-family:微软雅黑;

        }
        #notice tr td h1{
            font-size: 26pt;
            text-align: center;
            color: #0000FF;
            font-family:微软雅黑;
        }

    </style>
</head>
<body>

<div class="container" >
    <div class="row row-centered">
        <table id="notice" cellpadding="5" width="1000px;" align="center"  style="margin-top: 20px;">
            <tr>
                <td style="width: 50%">
                    <h1 class="text-center">“五用”</h1>
                    <p class="text-center">用职业道德好的人</p>
                    <p class="text-center">用执行能力强的人</p>
                    <p class="text-center">用以团队为主的人</p>
                    <p class="text-center">用善于学习的人</p>
                    <p class="text-center">用勇担责任的人</p>


                </td>
                <td style="width: 50%">
                    <h1 class="text-center">“五不重用”</h1>
                    <p class="text-center">不重用不熟悉业务的人</p>
                    <p class="text-center">不重用不会做小事的人</p>
                    <p class="text-center">不重用不服从大局的人</p>
                    <p class="text-center">不重用不培养下属的人</p>
                    <p class="text-center">不重用不善于变革的人</p>
                </td>
            </tr>

        </table>

    </div>

</div>


</body>
</html>

