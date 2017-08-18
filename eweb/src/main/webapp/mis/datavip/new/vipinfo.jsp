<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title>会员信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
    <%@include file="/common/common_header.jsp" %>
</head>
<body>
<div ng-app='myApp' ng-view class="content"></div>
<jsp:include page="/common/searchPage">
    <jsp:param name="uri" value="${uri}"/>
</jsp:include>
</body>

<script type="text/javascript">
    var app = angular.module('myApp', ['ngFileUpload','ngRoute', 'ui.bootstrap', 'jcs-autoValidate'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/list', {templateUrl: '<c:url value="/mis/datavip/new/list.tpl.jsp"/>'})
                .when('/edit', {templateUrl: '<c:url value="/base/directory/edit.tpl.jsp"/>'})
                .otherwise({redirectTo: '/list'});
        }]);

    app.controller('listCtrl', function ($rootScope,$scope,$http,$window) {
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
//        查询条件
        $scope.uid=null;
        $scope.userType=null;
        $scope.regStartTime=null;
        $scope.regEndTime=null;
        $scope.payTel=null;
        $scope.payFlag=null;
        $scope.serviceStartTime=null;
        $scope.serviceEndTime=null;
        $scope.newUser=false;
//select查询条件
        $scope.userTypeList=[];
        $scope.billsFlagList=[];
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading=true;
        $scope.canDel=PrivilegeService.hasPrivilege('delete');
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.updateList = function (result) {
            $scope.modelList = result.data;
            $scope.queryParams.totalRecords = result.totalRecords;
            $scope.queryParams.init = false;
        };

        $scope.selectAll = function () {
            for (var i = 0; i < $scope.modelList.length; i++) {
                $scope.modelList[i].delFlag = $scope.delFlag;
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
            var searchParams={"uid":$scope.uid,"userType":$scope.userType,"payTel":$scope.payTel,"payFlag":$scope.payFlag,"newUser":$scope.newUser,"regStartTime":$scope.regStartTime,
                "regEndTime":$scope.regEndTime,"serviceStartTime":$scope.serviceStartTime,"serviceEndTime":$scope.serviceEndTime,"queryParams":$scope.queryParams}
            $http.post("<c:url value="/mis/datavip/new/list"/>", searchParams)
                .success(function (response) {
                    $scope.isLoading = false;
                    if (response.success) {
                        $scope.updateList(response.data);
                    } else {
                        bootbox.alert(response.message);
                    }

                });
        }
        $rootScope.exportExcel=function(){
            var url = "<c:url value="/mis/datavip/new/exportExl"/>"+"?uid="+$scope.uid+"&userType="+$scope.userType
                +"&payTel="+$scope.payTel+"&payFlag="+$scope.payFlag+"&newUser="+$scope.newUser+"&regStartTime="+$scope.regStartTime+
                    "&regEndTime="+$scope.regEndTime+"&serviceStartTime="+$scope.serviceStartTime+"&serviceEndTime="+$scope.serviceEndTime;
            window.open(url);
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
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/directory/deletes"/>", param).success(function (data) {
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

        //获取userTypeKList
        $http.get("<c:url value="/mis/datavip/new/getUserType"/>").success(function (response) {
            if (response.success){
                $scope.userTypeList=response.data;
            }else{
                bootbox.alert(response.message);
            }
        });
        $http.get("<c:url value = "/mis/datavip/new/getBillsFlag"/>").success(function(response){
            if (response.success){
                var billsFlagList= $.parseJSON(response.data);
                $scope.billsFlagList=billsFlagList.billsFlag;
            }else{
                bootbox.alert(response.message);
            }
        })
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

        buttonHeader.listInit($scope,$window);
    });
    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>
