<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="eidea" uri="http://eidea.cn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dsdl.eidea.base.entity.bo.UserBo" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <title><eidea:label key="index.title"/></title>
    <input type="hidden" id="refreshTabs" value="<eidea:label key="bootstrap.addtab.refresh.this.tab"/>">
    <input type="hidden" id="closeTabs" value="<eidea:label key="bootstrap.addtab.close.this.tab"/>">
    <input type="hidden" id="otherTabs" value="<eidea:label key="bootstrap.addtab.close.other.tabs"/>">
    <input type="hidden" id="leftTabs" value="<eidea:label key="bootstrap.addtab.close.left.tabs"/>">
    <input type="hidden" id="rightTabs" value="<eidea:label key="bootstrap.addtab.close.right.tabs"/>">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.min.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap-theme.min.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.addtabs.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="<c:url value="/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"/>" type="text/css" media="all"/>
    <script type='text/javascript' src='<c:url value="/js/jquery-3.1.1.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/bootstrap/bootstrap.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/bootbox.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/bootstrap/datetimepicker/bootstrap-datetimepicker.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/bootstrap/bootstrap.addtabs.js"/>'></script>
    <script type='text/javascript' src="<c:url value="/js/angular/angular.min.js"/>"></script>
    <script type='text/javascript' src="<c:url value="/js/angular/angular-route.min.js"/>"></script>
    <script type='text/javascript' src="<c:url value="/js/angular/jcs-auto-validate.min.js"/>"></script>
    <script type='text/javascript' src="<c:url value="/js/angular/ui-bootstrap-tpls-2.2.0.min.js"/>"></script>
    <script type='text/javascript' src="<c:url value="/js/consts.js"/>"></script>
    <script type='text/javascript' src="<c:url value="/js/eidea.util.js"/>"></script>

    <script type='text/javascript' src="<c:url value="/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"/>"></script>
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/animate.min.css"/>" rel="stylesheet">
    <script type='text/javascript' src="<c:url value="/js/md5.min.js"/>"></script>
    <!--sidebar-->
    <link href="<c:url value="/js/bootstrap/sidebar/jquery.mCustomScrollbar.min.css"/>" rel="stylesheet">
    <script type='text/javascript' src='<c:url value="/js/bootstrap/sidebar/jquery.mCustomScrollbar.js"/>'></script>
    <!--sidebar-->

    <!-- Theme styling -->

    <link href="<c:url value="/css/custom.css"/>" rel="stylesheet">
    <!--[if lt IE 9]>
    <script type="text/javascript" src='<c:url value="/js/ie8-responsive-file-warning.js"/>'></script>
    <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src='<c:url value="/js/html5shiv.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/respond.min.js"/>' type="text/javascript"></script>
    <![endif]-->
</head>
<%
    UserBo user=(UserBo)session.getAttribute("loginUser");
    long systemTimeStamp=System.currentTimeMillis();
    session.setAttribute("systemTimeStamp",systemTimeStamp);
%>
<body class="nav-md gun_dong">
<input type="hidden" id="systemTimeStamp" value="<%=systemTimeStamp%>">
<div class="container body">
    <div class="main_container">
        <!--left menu bgn-->
        <div class="col-md-3 left_col menu_fixed">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="<c:url value="/index.jsp"/> " class="site_title"><li class="fa" style="margin-top: 8px;"><img src="http://www.cityre.cn/images/img2017/cityre_logo.png" style="width:45px;height: 45px;"/></li> <span style="font-family: 微软雅黑"><eidea:label key="index.enterprise_thinking"/></span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="<c:url value="/img/header.png"/>" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2> <p><%=user.getName()%></p></h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->

                <br>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        ${sessionScope.menuString}
                    </div>
                </div>
                <!-- sidebar menu -->
            </div>
        </div>
        <!--left menu end-->
        <!--top navigation-->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="<c:url value="/img/header.png"/>" alt="">
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="javascript:void(0);" data-toggle="modal" data-target="#changePasswordModal"><eidea:label key="index.change_password"/></a></li>
                                <li><a href="javascript:void(0);" onclick="gotoUrl()"><eidea:label key="index.profile"/></a></li>
                                <li>
                                    <a href="javascript:;">
                                        <span class="badge bg-red pull-right"><eidea:label key="index.proportion"/></span>
                                        <span><eidea:label key="index.settings"/></span>
                                    </a>
                                </li>
                                <li><a href="javascript:;"><eidea:label key="index.help"/></a></li>
                                <li><a href="<c:url value="/logout"/>"><i class="fa fa-sign-out pull-right"></i><eidea:label key="index.log_out"/></a></li>
                            </ul>
                        </li>

                        <li role="presentation" class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-volume-up la-ba"></i>
                                <span class="badge bg-green"><eidea:label key="index.email_size"/></span>
                            </a>
                            <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                <li>
                                    <a>
                                        <span class="image"><img src="<c:url value="/img/header.png"/>" alt="Profile Image"></span>
                                        <span>
                                          <span><eidea:label key="index.email_name"/></span>
                                          <span class="time"><eidea:label key="index.email_time"/></span>
                                        </span>
                                        <span class="message"><eidea:label key="index.email_message"/></span>
                                    </a>
                                </li>
                                <li>
                                    <div class="text-center">
                                        <a>
                                            <strong><eidea:label key="index.email_see_all"/></strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li ng-app="changeLanguageApp" ng-controller="changeLanguageCtrl">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <TAG ng-bind="defaultLanguageName"></TAG>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li ng-repeat="language in languages">
                                    <a ng-click="swithLanguage(language.code)">{{language.name}}</a>
                                </li>
                            </ul>

                        </li>
                        <li >
                            <a href="#"  class="user-profile dropdown-toggle" data-toggle="modal" data-target="#selectCityModal">[<c:out value="${sessionScope.currentCity.city}"/>]&nbsp;&nbsp;<eidea:label key="common.header.selectCity"/></a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!--用户修改密码-->
            <%@ include file="/common/change_password.jsp" %>
            <!--用户修改密码-->
            <%@ include file="/common/common_select_city.jsp" %>
        </div>
        <!--top navigation-->
        <!--page content-->
        <div class="right_col" role="main">
            <div id="tabs">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#home" aria-controls="home" role="tab" data-toggle="tab"><eidea:label key="common.button.home.page"/></a>
                    </li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <iframe src="<c:url value="/common/kouhao.jsp"/>" width="100%" height="100%"  frameborder="0"></iframe>
                    </div>
                </div>
            </div>
        </div>
        <!--page content-->
    </div>
</div>
<script src='<c:url value="/js/custom.js"/>' type="text/javascript"></script>
<script type="text/javascript">
    var app = angular.module('changeLanguageApp', ['ui.bootstrap', 'jcs-autoValidate']);
    app.controller('changeLanguageCtrl',function ($scope,$http) {
        //获取语言列表
        $http.get("<c:url value="/languages"/>").success(function (data) {
            if (data.success) {
                $scope.languages = data.data;
                var languageCode="<%=(request.getSession().getAttribute("language")==null)?request.getLocale().toString():request.getSession().getAttribute("language")%>";
                $.each($scope.languages,function (index,value) {
                    if(value.code == languageCode){
                        $scope.defaultLanguageName=value.name;
                    }
                })
            }else {
                $scope.serverReturnMessage = data.message;
            }
        });
        //切换语种
        $scope.swithLanguage=function (languageCode) {
            $.each($scope.languages,function (index,value) {
                if(value.code == languageCode){
                    $scope.defaultLanguageName=value.name;
                }
            })
            window.parent.location.href = "<c:url value="/common/changeLanguageCode"/>?language="+languageCode;
        }
    });
</script>
<script type="text/javascript">

    function resizeIframe(obj) {
        obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
    }
    function gotoUrl() {
        var flag=false;
        $(".nav-tabs").find("li").each(function () {
            if(this.id == "tab_tab_persionSettging"){
                flag=true;
                $(this).attr("class","active");
            }else if(this.className == "active"){
                $(this).attr("class","");
            }
        })
        $(".tab-content").find("div").each(function () {
            if(this.id == "tab_persionSettging"){
                $(this).attr("class","tab-pane active");
            }else {
                $(this).attr("class","tab-pane");
            }
        })
        if(flag){
            return false;
        }
        var bufferLi=new StringBuffer();
        var bufferDiv=new StringBuffer();
        bufferLi.append('<li id="tab_tab_persionSettging" role="presentation" class="active">')
                .append('<a href="#tab_persionSettging" aria-controls="tab_persionSettging" role="tab" data-toggle="tab"><eidea:label key="index.profile"/></a>')
                .append('<i class="close-tab glyphicon glyphicon-remove"></i>')
                .append('</li>');
        bufferDiv.append('<div role="tabpanel" class="tab-pane active" id="tab_persionSettging">')
                .append('<iframe src="<c:url value="/common/profile.jsp"/>" width="100%" height="100%"  frameborder="0"></iframe>')
                .append('</div>');
        $(".nav-tabs").append(bufferLi.toString());
        $(".tab-content").append(bufferDiv.toString());
    }
    $('body').click(function () {
        $.ajax({
            url:"<c:url value="/checkTimeout"/>",
            data:"systemTimeStamp="+$("#systemTimeStamp").val(),
            type:"POST",
            dataType:"JSON",
            success:function(data){
                if(data.data){
                    window.location.href="<c:url value="/login.jsp"></c:url>";
                }
            }
        })
    })
    <c:if test="${sessionScope.currentCity==null}">
            $("#selectCityModal").modal('show');
    </c:if>
</script>
</body>
</html>
