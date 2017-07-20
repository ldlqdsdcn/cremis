<%--
  Created by 刘大磊.
  Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title><%--区域--%><eidea:label key="product.title"/></title>
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
                .when('/list', {templateUrl: '<c:url value="/base/product/list.tpl.jsp"/>'})
                .when('/edit', {templateUrl: '<c:url value="/base/product/edit.tpl.jsp"/>'})
                .otherwise({redirectTo: '/list'});
        }]);
    app.controller('listCtrl', function ($scope, $rootScope, $http) {
        $scope.modelList = [];
        $scope.delFlag = false;
        $scope.isLoading = true;
        $scope.canDel = PrivilegeService.hasPrivilege('delete');
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
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
            $http.post("<c:url value="/base/product/list"/> ", $scope.queryParams).success(function (response) {
                $scope.loading = false;
                if (response.success) {
                    $scope.updateList(response.data);
                } else {
                    bootbox.alert(response.message);
                }
            })
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
                                ids.push($scope.modelList[i].productCode);
                            }
                        }
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/product/deletes"/>", param).success(function (data) {
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
        $scope.misProductPo = {};
        $scope.canAdd = PrivilegeService.hasPrivilege('add');
        $http.get("<c:url value="/base/product/getPlatformTypeList"/> ").success(function (response) {
            if (response.success){
                var platformType = $.parseJSON(response.data);
                $scope.platformTypeList = platformType.platformTypeList;
            }else {
                bootbox.alert(response.message);
            }
        });
        var url = "<c:url value="/base/product/create"/>";
        if ($routeParams.productCode != null) {
            url = "<c:url value="/base/product/get"/>" + "?productCode=" + $routeParams.productCode;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.misProductPo = response.data;
                    $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.misProductPo.productCode == null) || PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        //验证产品是否存在
        var isExist = true;
        $scope.getExistProduct = function () {
            $http.post("<c:url value="/base/product/getExistProductList"/> ", $scope.misProductPo).success(function (response) {
                if (response.success) {
                    if (response.data) {
                        $scope.message = "<eidea:message key="error.v2017.product.exist"/>";
                    }else {
                        isExist = false;
                    }
                }else{
                    bootbox.alert(response.message);
                }
            })
        }
        $scope.save = function () {
            if(isExist){
                $scope.message = "<eidea:message key="error.v2017.product.exist"/>";
                return false;
            }
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/product/saveForUpdated"/>';
                if ($scope.misProductPo.updateTime == null) {
                    postUrl = '<c:url value="/base/product/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.misProductPo).success(function (data) {
                    if (data.success) {
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.misProductPo = data.data;
                    }
                    else {
                        $scope.message = data.message;
                        $scope.errors = data.data;
                    }
                }).error(function (data, status, headers, config) {
                    alert(JSON.stringify(data));
                });
            }
        }

        $scope.create = function () {
            $scope.message = "";
            $scope.misProductPo = {};
            var url = "<c:url value="/base/product/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.misProductPo = response.data;
                        $scope.canSave = (PrivilegeService.hasPrivilege('add') && $scope.misProductPo.productCode == null) || PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }

    });
    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>
