<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2017/9/28
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content='easyui,jui,jquery easyui,easyui demo,easyui中文'/>
    <meta name="description" content='TopJUI前端框架，基于最新版EasyUI前端框架构建，纯HTML调用功能组件，不用写JS代码的EasyUI，专注你的后端业务开发！'/>
    <title>TopJUI前端框架 - 静态演示</title>
    <!-- 浏览器标签图片 -->

    <link rel="shortcut icon" href="<c:url value="/topjui/image/favicon.ico"/>"/>
    <!-- TopJUI框架样式 -->

    <link type="text/css" href="<c:url value="/topjui/css/topjui.core.min.css"/>" rel="stylesheet">
    <link type="text/css" href="<c:url value="/topjui/themes/default/topjui.green.css"/>" rel="stylesheet"
          id="dynamicTheme"/>
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="<c:url value="/topjui/plugins/font-awesome/css/font-awesome.min.css"/>"
          rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="<c:url value="/topjui/plugins/jquery/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/topjui/plugins/jquery/jquery.cookie.js"/>"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="<c:url value="/static/public/js/topjui.config.js"/>"></script>
    <!-- TopJUI框架核心 -->
    <script type="text/javascript" src="<c:url value="/topjui/js/topjui.core.min.js"/>"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="<c:url value="/topjui/js/locale/topjui.lang.zh_CN.js"/>"></script>
    <script language="javascript" type="text/javascript" src="<c:url value="/js/eidea.validate.js"/>"></script>
    <script language="javascript" type="text/javascript" src="<c:url value="/js/eidea.util.js"/>"></script>
    <!-- 首页js -->
    <script type="text/javascript" src=" <c:url value="/static/public/js/topjui.index.js"/>" charset="utf-8"></script>
</head>

<body>
<div id="loading" class="loading-wrap">
    <div class="loading-content">
        <div class="loading-round"></div>
        <div class="loading-dot"></div>
    </div>
</div>

<div id="mm" class="submenubutton" style="width: 140px;">
    <div id="mm-tabclose" name="6" iconCls="fa fa-refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="Div1" name="1" iconCls="fa fa-close">关闭</div>
    <div id="mm-tabcloseother" name="3">关闭其他</div>
    <div id="mm-tabcloseall" name="2">关闭全部</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright" name="4">关闭右侧标签</div>
    <div id="mm-tabcloseleft" name="5">关闭左侧标签</div>
</div>

<style type="text/css">
    /* right */
    .top_right {
        /*width: 748px;*/
    }

    /* top_link */
    .top_link {
        padding-top: 24px;
        height: 26px;
        line-height: 26px;
        padding-right: 35px;
        text-align: right;
    }

    .top_link i {
        color: #686868;
    }

    .top_link span, .top_link a {
        color: #46AAFE;
    }

    .top_link a {
        font-size: 13px;
    }

    .top_link a:hover {
        text-decoration: underline;
    }

    .nav_bar {
        position: relative;
        z-index: 999;
        color: #333;
        margin-right: 10px;
        height: 50px;
        line-height: 50px;
    }

    .nav_bar ul {
        padding: 0;
    }

    .nav {
        position: relative;
        margin: 0 auto;
        font-family: "Microsoft YaHei", SimSun, SimHei;
        font-size: 14px;
    }

    .nav a {
        color: #333;
    }

    .nav h3 {
        font-size: 100%;
        font-weight: normal;
        height: 50px;
        line-height: 50px;
    }

    .nav h3 a {
        display: block;
        padding: 0 20px;
        text-align: center;
        font-size: 14px;
        color: #fff;
        height: 50px;
        line-height: 50px;
    }

    .nav .m {
        float: left;
        position: relative;
        z-index: 1;
        height: 50px;
        line-height: 50px;
        list-style: none;
    }

    .nav .s {
        float: left;
        width: 3px;
        text-align: center;
        color: #D4D4D4;
        font-size: 12px;
        height: 50px;
        line-height: 50px;
        list-style: none;
    }

    .nav .sub, ul.sub {
        display: none;
        position: absolute;
        left: -3px;
        top: 42px;
        z-index: 999;
        width: 128px;
        border: 1px solid #E6E4E3;
        border-top: 0;
        background: #fff;
    }

    .nav .sub li {
        text-align: center;
        padding: 0 8px;
        margin-bottom: -1px;
        list-style: none;
    }

    .nav .sub li a {
        display: block;
        border-bottom: 1px solid #E6E4E3;
        padding: 8px 0;
        height: 28px;
        line-height: 28px;
        color: #666;
    }

    .nav .sub li a:hover {
        color: #1E95FB;
        cursor: pointer;
    }

    .nav .block {
        height: 3px;
        background: #1E95FB;
        position: absolute;
        left: 0;
        top: 47px;
        overflow: hidden;
    }

    .sub {
        padding: 0;
        background: #f5f5f5;
    }

    .sub li {
        padding: 0 8px;
        list-style: none;
    }

    .sub li:hover {
        background: #f3f3f3;
    }

    .sub li a {
        display: block;
        color: #000;
        height: 34px;
        line-height: 34px;
    }

    .sub li a:hover {
        text-decoration-line: none;
    }

    /* 重用类样式 */
    .f_l {
        float: left !important;
    }

    .f_r {
        float: right !important;
    }

    .no_margin {
        margin: 0px !important;
    }

    .no_border {
        border: 0px !important;
    }

    .no_bg {
        background: none !important;
    }

    .clear_both {
        clear: both !important;
    }

    .display_block {
        display: block !important;
    }

    .text_over {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        -o-text-overflow: ellipsis;
        -moz-binding: url('ellipsis.xml#ellipsis');
    }

    /* 重用自定义样式 */
    .w_100 {
        width: 100%;
    }

    .w_95 {
        width: 95%;
    }

    .indextx {
        width: 980px;
        margin: 0 auto;
        margin-top: 10px;
        background: #FFFFFF;
    }

    .w_min_width {
        min-width: 1200px;
    }

    .w_1200 {
        width: 1200px;
    }

    .w_1067 {
        width: 1067px;
    }

    .w_980 {
        width: 980px;
    }

    .header {
        overflow: hidden
    }
</style>
<script>
    $(function () {
        $('#ulMenu>li').hover(
            function () {
                var m = $(this).data('menu');
                if (!m) {
                    m = $(this).find('ul').clone();
                    m.appendTo(document.body);
                    $(this).data('menu', m);
                    var of = $(this).offset();
                    m.css({left: of.left, top: of.top + this.offsetHeight});
                    m.hover(function () {
                        clearTimeout(m.timer);
                    }, function () {
                        m.hide()
                    });
                }
                m.show();
            }, function () {
                var m = $(this).data('menu');
                if (m) {
                    m.timer = setTimeout(function () {
                        m.hide();
                    }, 100);//延时隐藏，时间自定义，100ms
                }
            }
        );
    });
</script>
<div data-toggle="topjui-layout" data-options="id:'index_layout',fit:true">
    <div id="north" class="banner" data-options="region:'north',border:false,split:false"
         style="height: 50px; padding:0;margin:0; overflow: hidden;">
        <table style="float:left;border-spacing:0px;">
            <tr>
                <td class="webname">
                    <span class="fa fa-home" style="font-size:26px; padding-right:8px;"></span>禧泰数据Mis系统
                </td>
                <td class="collapseMenu" style="text-align: center;cursor: pointer;">
                    <span class="fa fa-chevron-circle-left" style="font-size: 18px;"></span>
                </td>
                <td>
                    <table id="topmenucontent" cellpadding="0" cellspacing="0">
                        <td id="1325" title="这只是静态演示" class="topmenu selected systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa  fa-hand-pointer-o"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">静态演示</span></p>
                            </a>
                        </td>
                        <td id="60" title="TopJUI开发文档" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa fa-puzzle-piece"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">开发文档</span></p>
                            </a>
                        </td>
                        <td title="人力资源" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa fa-sort-amount-asc"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">人力资源</span></p>
                            </a>
                        </td>
                        <td title="系统设置" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa  fa-shield"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">系统设置</span></p>
                            </a>
                        </td>
                    </table>
                </td>
            </tr>
        </table>
        <div class="top_right f_r">
            <!-- menu -->
            <div class="nav_bar">
                <ul class="nav clearfix" id="ulMenu">

                    <!-- 单一菜单 | end -->
                    <li class="m">
                        <h3><a title="官方网站" class="l-btn-text bannerbtn" href="http://www.topjui.com"
                               target="_blank"><i class="fa fa-home"></i></a></h3>
                    </li>
                    <li class="s">|</li>

                    <li class="m">
                        <h3>
                            <a title="切换主题" id="setThemes" class="l-btn-text bannerbtn"
                               href="javascript:void(0)"><i class="fa fa-tree"></i></a>
                        </h3>
                    </li>
                    <li class="s">|</li>

                    <li class="m">
                        <h3>
                            <a class="l-btn-text bannerbtn"
                               href="javascript:void(0)"><i class="fa fa-cog"></i></a>
                        </h3>
                        <ul class="sub">
                            <li><a class="fa fa-info-circle" href="http://www.topjui.com/about.html" target="_blank">
                                关于系统</a></li>
                            <li><a class="fa fa-user" href="http://www.ewsd.cn/contact.html" target="_blank"> 联系我们</a>
                            </li>
                        </ul>
                    </li>
                    <li class="s">|</li>

                    <li class="m">
                        <h3>
                            <a id="showUserInfo" style="display:inline-block;" class="fa bannerbtn"
                               href="javascript:void(0)">
                                <img src="../../topjui/image/avatar.jpg" class="user-image" alt="User Image">
                                <span class="user-name">TopJUI</span>
                            </a>
                        </h3>
                        <ul class="sub">
                            <li><a class="fa fa-info-circle" href="javascript:void(0)"> 个人信息</a></li>
                            <li><a class="fa fa-key" href="javascript:modifyPwd(0)">修改密码</a></li>
                            <li><a class="fa fa-power-off" href="javascript:logout()"> 退出系统</a></li>
                        </ul>
                    </li>
                    <li class="block"></li><!-- 滑动块 -->

                </ul>
            </div>
            <!-- menu | end -->
        </div>
    </div>

    <div id="west"
         data-options="region:'west',split:true,width:230,border:false,headerCls:'border_right',bodyCls:'border_right'"
         title="" iconCls="fa fa-dashboard">
        <div id="RightAccordion"></div>
        <!--<div id="menuTab" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="导航菜单" data-options="iconCls:'fa fa-sitemap'" style="padding:0;">
                <div id="RightAccordion" class="easyui-accordion"></div>
            </div>
            <div title="常用链接" data-options="iconCls:'fa fa-star',closable:true">
                <ul id="channgyongLink"></ul>
            </div>
        </div>-->
    </div>

    <div id="center" data-options="region:'center',border:false" style="overflow:hidden;">
        <div id="index_tabs" style="width:100%;height:100%">
            <div title="系统首页" iconCls="fa fa-home" data-options="border:true,
            content:'<iframe src=\'<c:url value="/kouhao"/>\' scrolling=\'auto\' frameborder=\'0\' style=\'width:100%;height:100%;\'></iframe>'"></div>
        </div>
    </div>

    <div data-options="region:'south',border:true"
         style="text-align:center;height:30px;line-height:30px;border-bottom:0;overflow:hidden;">
        <input type="hidden" id="userNameId" name="userNameId" value="${userNameId}"/>
        <span style="float:left;padding-left:5px;width:30%;text-align: left;">当前用户：${MIS_USER.name}</span>
        <span style="padding-right:5px;width:40%">
            版权所有 © 2014-2017
            <a href="http://www.cityre.cn/" target="_blank">青岛禧泰房地产数据有限公司</a>
            <a href="http://www.miitbeian.gov.cn" target="_blank">鲁ICP 备05026887号</a>
        </span>
        <span style="float:right;padding-right:5px;width:30%;text-align: right;">正式版v1.0</span>
    </div>
</div>

<!--[if lte IE 8]>
<div id="ie6-warning">
    <p>您正在使用低版本浏览器，在本页面可能会导致部分功能无法使用，建议您升级到
        <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">IE9或以上版本的浏览器</a>
        或使用<a href="http://se.360.cn/" target="_blank">360安全浏览器</a>的极速模式浏览
    </p>
</div>
<![endif]-->

<div id="themeStyle" data-options="iconCls:'fa fa-tree'" style="width:600px;height:340px">
    <table style="width:100%; padding:20px; line-height:30px;text-align:center;">
        <tr>
            <td>
                <div class="skin-common skin-black"></div>
                <input type="radio" name="themes" value="black" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-red"></div>
                <input type="radio" name="themes" value="red" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-green"></div>
                <input type="radio" name="themes" value="green" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-purple"></div>
                <input type="radio" name="themes" value="purple" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-blue"></div>
                <input type="radio" name="themes" value="blue" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-yellow"></div>
                <input type="radio" name="themes" value="yellow" class="topjuiTheme"/>
            </td>
        </tr>
        <tr>
            <td>
                <div class="skin-common skin-blacklight"></div>
                <input type="radio" name="themes" value="blacklight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-redlight"></div>
                <input type="radio" name="themes" value="redlight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-greenlight"></div>
                <input type="radio" name="themes" value="greenlight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-purplelight"></div>
                <input type="radio" name="themes" value="purplelight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-bluelight"></div>
                <input type="radio" name="themes" value="bluelight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-yellowlight"></div>
                <input type="radio" name="themes" value="yellowlight" class="topjuiTheme"/>
            </td>
        </tr>
    </table>
    <table style="width: 100%; padding: 20px; line-height: 30px; text-align: center;">
        <tr>
            <td>
                <input type="radio" name="menustyle" value="accordion" checked="checked"/>手风琴
            <td>
                <input type="radio" name="menustyle" value="tree"/>树形
            </td>
            <td>
                <input type="checkbox" checked="checked" name="topmenu" value="topmenu"/>开启顶部菜单
            </td>
        </tr>
    </table>
</div>

<form id="pwdDialog"
      data-options="title: '修改密码',
      iconCls:'fa fa-key',
      width: 400,
      height: 260,
      href: 'profile/showChangePassword'"></form>
</body>
</html>