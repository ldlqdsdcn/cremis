<%--
  Created by 刘大磊.
  Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title><%--区域--%><eidea:label key="area.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
</head>
<body>
<div ng-app='myApp' ng-view class="content"></div>
<jsp:include page="/common/searchPage">
    <jsp:param name="uri" value="${uri}"/>
</jsp:include>
</body>
<script type="text/javascript">
    var app = angular.module('myApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/list', {templateUrl: '<c:url value="/mis/datavip/bills/list.tpl.jsp"/>'})
                .when('/edit', {templateUrl: '<c:url value="/mis/datavip/bills/edit.tpl.jsp"/>'})
                .when('/invoiceEdit',{templateUrl:'<c:url value="/mis/datavip/bills/invoice.tpl.jsp"/> '})
                .otherwise({redirectTo: '/list'});
        }]);
    app.controller('listCtrl', function ($scope,$rootScope, $http) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel=PrivilegeService.hasPrivilege('delete');
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };
        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag=$scope.delFlag;
            }
        }
        $scope.canDelete = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                if ($scope.modelList[i].delFlag) {
                    return true;
                }
            }
            return false;
        }
        $scope.pageChanged = function () {
            $http.post("<c:url value="/mis/datavip/bills/list"/>", $scope.queryParams)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    }
                    else {
                        bootbox.alert(response.message);
                    }

                });
        }

//批量删除
        $scope.deleteRecord = function () {
            bootbox.confirm({
                message: "<eidea:message key="common.warn.confirm.deletion"/>",
                buttons: {
                    confirm: {
                        label: '<eidea:label key="common.button.yes"/>',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<eidea:label key="common.button.no"/>',
                        className: 'btn-danger'
                    }
                }, callback: function (result) {
                    if (result) {
                        var ids = [];
                        for (var i = 0; i < $scope.modelList.length; i++) {
                            if ($scope.modelList[i].delFlag) {
                                ids.push($scope.modelList[i].id);
                            }
                        }
                        $scope.queryParams.init=true;
                        var param={"queryParams":$scope.queryParams,"ids":ids};
                        $http.post("<c:url value="/base/area/deletes"/>", param).success(function (data) {
                            if (data.success) {
                                $scope.updateList(data.data);
                                bootbox.alert("<eidea:message key="module.deleted.success"/>");
                            } else {
                                bootbox.alert(data.message);
                            }
                        });
                    }
                }
            });
        };
//        服务开通的判断




//可现实分页item数量
        $scope.maxSize =${pagingSettingResult.pagingButtonSize};
        if ($rootScope.listQueryParams != null) {
            $rootScope.queryParams = $scope.listQueryParams;
            $rootScope.queryParams.init = true;
        }
        else {
            $scope.queryParams = {
                pageSize:${pagingSettingResult.perPageSize},//每页显示记录数
                pageNo: 1, //当前页
                totalRecords: 0,//记录数
                init: true
            };
            $rootScope.listQueryParams = $scope.queryParams;
        }
        $scope.pageChanged();
    });
//    服务操作Ctrl
    app.controller('editCtrl', function ($scope, $http, $routeParams) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });

        $scope.message = '';
        $scope.userPaymentInfo={};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        var url = "<c:url value="/mis/ifmanager/apikey/create"/>";
        if ($routeParams.billCode!= null) {
            url = "<c:url value="/mis/datavip/bills/getUserPaymentInfo"/>" + "?billCode=" + $routeParams.billCode;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.userPaymentInfo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.billsPo.id == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        <%--$scope.save = function () {--%>
            <%--if ($scope.editForm.$valid) {--%>
                <%--var postUrl = '<c:url value="/mis/ifmanager/apikey/saveForUpdated"/>';--%>
                <%--if ($routeParams.id == null) {--%>
                    <%--postUrl = '<c:url value="/mis/ifmanager/apikey/saveForCreated"/>';--%>
                <%--}--%>
                <%--$http.post(postUrl, $scope.misApiKeyPo).success(function (data) {--%>
                    <%--if (data.success) {--%>
                        <%--$scope.message = "<eidea:label key="base.save.success"/>";--%>
                        <%--$scope.misApiKeyPo = data.data;--%>
                    <%--}--%>
                    <%--else {--%>
                        <%--$scope.message = data.message;--%>
                        <%--$scope.errors = data.data;--%>
                    <%--}--%>
                <%--}).error(function (data, status, headers, config) {--%>
                    <%--alert(JSON.stringify(data));--%>
                <%--});--%>
            <%--}--%>
        <%--}--%>
        <%--$scope.create = function () {--%>
            <%--$scope.message = "";--%>
            <%--$scope.misApiKeyPo = {};--%>
            <%--var url = "<c:url value="/mis/ifmanager/apikey/create"/>";--%>
            <%--$http.get(url)--%>
                <%--.success(function (response) {--%>
                    <%--if (response.success) {--%>
                        <%--$scope.misApiKeyPo = response.data;--%>
                        <%--$scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.misApiKeyPo.id == null) || PrivilegeService.hasPrivilege('update');--%>
                    <%--}--%>
                    <%--else {--%>
                        <%--bootbox.alert(response.message);--%>
                    <%--}--%>
                <%--}).error(function (response) {--%>
                <%--bootbox.alert(response);--%>
            <%--});--%>
        <%--}--%>

    });

    app.controller('serviceCtrl',function ($scope,$http,$routeParams) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });
        $scope.billsPo = {};
        if ($routeParams.id==null){
            bootbox.alert("aaa");
        }else {
            var url = "<c:url value="/mis/datavip/bills/get"/> "+"?id="+$routeParams.id
        }
        $http.get(url)
            .success(function (response) {
            if(response.success){
                $scope.billsPo = response.data;
            }else{
                bootbox.alert(response.message);
            }
        });
    });
    app.controller('invoiceCtrl',function ($scope,$http,$routeParams) {
        /**
         * 日期时间选择控件
         * bootstrap-datetime 24小时时间是hh
         */
        $('.bootstrap-datetime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            clearBtn: true
        });
        /**
         * 日期选择控件
         */
        $('.bootstrap-date').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: 1,
            todayBtn: 1,
            clearBtn: true
        });
        $scope.bill={};
        if($routeParams.id==null){
            bootbox.alert("参数错误！")
        }
        var url = "<c:url value="/mis/datavip/bills/get"/> "+"?id="+$routeParams.id
        $http.get(url).success(function (response) {
            if (response.success){
                $scope.bill = response.data;
            }else {
                bootbox.alert(response.message);
            }
        });
        $scope.addInvoice = function () {
            if ($scope.addInvoiceForm.$valid){
                var url='<c:url value = "/mis/datavip/bills/addInvoice"/>'
                $http.post(url,$scope.bill).success(function (response) {
                    if(response.success){
                        $scope.message="<eidea:label key="base.save.success"/> "
                        $scope.bill = response.data;
                    }else {
                        $scope.message = response.message;
                        $scope.errors=response.data;
                    }
                }).errors(function(response,status,header,config){
                    alert(JSON.stringify(response.data));
                })
            }

        }
    })
    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>
