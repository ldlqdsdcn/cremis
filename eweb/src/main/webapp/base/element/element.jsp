<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<html>
<head>
    <title><%--组织设置--%><eidea:label key="org.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/inc/inc_ang_js_css.jsp" %>
    <%@include file="/common/common_header.jsp" %>
</head>
<body >
<div ng-app='myApp'  class="container-fluid">
    <div class="nav nav-tab vertical-tab" ng-controller="tabCtrl">
        <uib-tabset vertical="true" active="active" type="tabs">
            <uib-tab index="0" heading="<eidea:label key="element.title"/>"  select="elementGo()" disable="elementShow"></uib-tab>
            <uib-tab index="1" heading="<eidea:label key="elementSelect.title"/>"select = "selectGo()" disable="selectShow"></uib-tab>
            <uib-tab index="2" heading="<eidea:label key="elementCheckbox.title"/>"select="checkboxGo()" disable="checkboxShow"></uib-tab>
            <uib-tab index="3" heading="<eidea:label key="elementLinked.title"/>"select="linkedGo()" disable ="linkedShow"></uib-tab>
        </uib-tabset>
    </div>
    <div ui-view class="tab-content vertical-tab-content"></div>
</div>
<jsp:include page="/common/searchPage">
    <jsp:param name="uri" value="${uri}"/>
</jsp:include>

</body>

<script type="text/javascript">
    var app = angular.module('myApp', ['ngFileUpload','ui.router', 'ui.bootstrap', 'jcs-autoValidate'])
            .config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider) {
                $urlRouterProvider.otherwise('/list')
                    $stateProvider
                        .state('list',{
                            url:'/list',
                            templateUrl:'<c:url value="/base/element/list.tpl.jsp"/> '
                        })
                        .state('edit',{
                            url:'/edit?id',
                            templateUrl:'<c:url value="/base/element/edit.tpl.jsp"/> '
                        })
                        .state('itemList',{
                            url:'/itemList',
                            templateUrl:'<c:url value="/base/elementSelect/list.tpl.jsp"/> '
                        })
                        .state('itemEdit',{
                            url:'/itemEdit?itemId',
                            templateUrl:'<c:url value="/base/elementSelect/edit.tpl.jsp"/> '
                        })
                        .state('checkboxList',{
                            url:'/checkboxList',
                            templateUrl:'<c:url value="/base/elementCheckbox/list.tpl.jsp"/> '
                        })
                        .state('checkboxEdit',{
                            url:'/checkboxEdit?checkboxId',
                            templateUrl:'<c:url value="/base/elementCheckbox/edit.tpl.jsp"/> '
                        })
                        .state('linkedList',{
                            url:'/linkedList',
                            templateUrl:'<c:url value="/base/elementLinked/list.tpl.jsp"/> '
                        })
                        .state('linkedEdit',{
                            url:'/linkedEdit?linkedId',
                            templateUrl:'<c:url value="/base/elementLinked/edit.tpl.jsp"/> '
                        })
            }]);
    app.controller('listCtrl', function ($rootScope,$scope,$http,$window) {
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
            $http.post("<c:url value="/base/element/list"/>", $scope.queryParams)
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
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/element/deletes"/>", param).success(function (data) {
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

        buttonHeader.listInit($scope,$window);
    });
    app.controller('editCtrl', function ($rootScope,$stateParams,$scope, $http,$window,$timeout, Upload) {
        $scope.message = '';
        $scope.elementPo = {};
        $scope.elementList = [];
        $scope.errorCode=-1;
        $scope.errorMessages=[];
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.canSave=false;
        $http.get("<c:url value="/base/field/selectInputType"/>").success(function (response) {
            if (response.success){
                var selectInputTypeList=$.parseJSON(response.data);
                $scope.inputTypeList=selectInputTypeList.fieldInputType;
            }else{
                bootbox.alert(response.message);
            }
        });
        $http.get("<c:url value="/base/field/selectShowType"/> ").success(function (response) {
            if(response.success){
                var selectShowType=$.parseJSON(response.data);
                $scope.showTypeList=selectShowType.fieldShowType;
            }else{
                bootbox.alert(response.message);
            }
        });
        var url = "<c:url value="/base/element/create"/>";
        if ($stateParams.id != null) {
            $rootScope.elementId=$stateParams.id;
            url = "<c:url value="/base/element/get"/>" + "?id=" + $stateParams.id;
        }
        $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.elementPo = response.data;
                        switch($scope.elementPo.elementInputType){
                            case "SELECT":
                                $rootScope.selectShow=false;
                                break;
                            case "CHECKBOX":
                                $rootScope.checkboxShow = false;
                                break;
                            case "CHECKBOXS":
                                $rootScope.checkboxShow = false;
                                break;
                            case "AUTO_COMPLISH":
                                $rootScope.linkedShow=false;
                                break;
                        }
//                       if($scope.elementPo.elementInputType=='SELECT'){
//                           $rootScope.selectShow=false;
//                       }else if ($scope.elementPo.elementInputType == 'CHECKBOX'){
//                           $rootScope.checkboxShow=false;
//                       }else {
//                           $rootScope.linkedShow=false;
//                       }
                        $scope.canSave=(PrivilegeService.hasPrivilege('add')&&$scope.elementPo.id==null)||PrivilegeService.hasPrivilege('update');
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            $scope.message="";
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/element/saveForUpdate"/>';
                if ($scope.elementPo.id == null) {
                    postUrl = '<c:url value="/base/element/saveForCreate"/>';
                }
                $http.post(postUrl, $scope.elementPo).success(function (data) {
                    if (data.success) {
                        $scope.errorCode=-1;
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.elementPo = data.data;
                    }
                    else {
                        $scope.message=data.message;
                        $scope.errorCode=data.errorCode;
                        if (data.errorCode == ERROR_VALIDATE_PARAM) {
                            $scope.errorMessages=data.data;
                        }
                        else {
                            $scope.errorMessages = [data.message];
                        }

                    }


                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.elementPo = {};
            var url = "<c:url value="/base/element/create"/>";
            $http.get(url)
                    .success(function (response) {
                        if (response.success) {
                            $scope.elementPo = response.data;
                        }
                        else {
                            bootbox.alert(response.message);
                        }
                    }).error(function (response) {
                bootbox.alert(response);
            });
        }

        buttonHeader.editInit($scope,$http,$window,$timeout, Upload,"/base");
    });
    app.controller('tabCtrl',function ($rootScope,$scope,$state) {
        $scope.elementGo=function () {
            $rootScope.elementShow=false;
            $rootScope.selectShow=true;
            $rootScope.checkboxShow=true;
            $rootScope.linkedShow = true;
            $state.go('list');
        }
        $scope.selectGo=function (){
            $rootScope.selectShow=false;
            $state.go('itemList');
        }
        $scope.checkboxGo=function () {
            $rootScope.checkboxShow = false;
            $state.go('checkboxList');
        }
        $scope.linkedGo=function () {
            $rootScope.linkedShow = false;
            $state.go('linkedList');
        }
    })
    app.controller('listSelectCtrl', function ($rootScope,$scope,$http,$window) {
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

        $scope.pageChanged = function () {
            var params={"queryParams":$scope.queryParams,"itemPK":$rootScope.elementId};
            $http.post("<c:url value="/base/elementSelect/list"/>",params)
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
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/elementSelect/deletes"/>",param).success(function (data) {
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


        $scope.pageChanged();

        buttonHeader.listInit($scope,$window);
    });
    app.controller('editSelectCtrl', function ($stateParams,$state,$scope, $http,$window,$timeout, Upload,$rootScope) {
        $scope.message = '';
        $scope.elementSelectPo = {};
        $scope.elementSelectList = [];
        $scope.errorCode=-1;
        $scope.errorMessages=[];
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.canSave=false;
        var url = "<c:url value="/base/elementSelect/create"/>";
        if ($stateParams.itemId != null) {
            url = "<c:url value="/base/elementSelect/get"/>" + "?id=" + $stateParams.itemId;
        }
        <%--获取javaDataType--%>
        $http.get("<c:url value="/base/elementSelect/getDataType"/> ").success(function (response) {
            if (response.success){
                var dataTypeList=$.parseJSON(response.data);
                $scope.javaDataTypeList=dataTypeList.javaDataType;
            }else{
                bootbox.alert(response.message);
            }
        })
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.elementSelectPo = response.data;
                    $scope.canSave=(PrivilegeService.hasPrivilege('add')&&$scope.elementSelectPo.id==null)||PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            $scope.message="";
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/elementSelect/saveForUpdated"/>';
                if ($scope.elementSelectPo.id == null) {
                    postUrl = '<c:url value="/base/elementSelect/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.elementSelectPo).success(function (data) {
                    if (data.success) {
                        $scope.errorCode=-1;
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.elementSelectPo = data.data;
                    }
                    else {
                        $scope.message=data.message;
                        $scope.errorCode=data.errorCode;
                        if (data.errorCode == ERROR_VALIDATE_PARAM) {
                            $scope.errorMessages=data.data;
                        }
                        else {
                            $scope.errorMessages = [data.message];
                        }

                    }


                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.elementSelectPo = {};
            var url = "<c:url value="/base/elementSelect/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.elementSelectPo = response.data;
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }
        buttonHeader.editInit($scope,$http,$window,$timeout, Upload,"/base");
    });
    app.controller('listCheckboxCtrl', function ($rootScope,$scope,$http,$window) {
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
            var params={"queryParams":$scope.queryParams,"itemPK":$rootScope.elementId};
            $http.post("<c:url value="/base/elementCheckbox/list"/>", params)
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
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/elementCheckbox/deletes"/>", param).success(function (data) {
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

        buttonHeader.listInit($scope,$window);
    });
    app.controller('editCheckboxCtrl', function ($stateParams,$scope, $http,$window,$timeout, Upload,$rootScope) {
        $scope.message = '';
        $scope.elementCheckboxPo = {};
        $scope.errorCode=-1;
        $scope.errorMessages=[];
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.canSave=false;
        var url = "<c:url value="/base/elementCheckbox/create"/>";
        if ($stateParams.checkboxId != null) {
            url = "<c:url value="/base/elementCheckbox/get"/>" + "?id=" + $stateParams.checkboxId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.elementCheckboxPo = response.data;
                    $scope.canSave=(PrivilegeService.hasPrivilege('add')&&$scope.elementCheckboxPo.id==null)||PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            $scope.message="";
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/elementCheckbox/saveForUpdated"/>';
                if ($scope.elementSelectPo.id == null) {
                    postUrl = '<c:url value="/base/elementCheckbox/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.elementCheckboxPo).success(function (data) {
                    if (data.success) {
                        $scope.errorCode=-1;
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.elementCheckboxPo = data.data;
                    }
                    else {
                        $scope.message=data.message;
                        $scope.errorCode=data.errorCode;
                        if (data.errorCode == ERROR_VALIDATE_PARAM) {
                            $scope.errorMessages=data.data;
                        }
                        else {
                            $scope.errorMessages = [data.message];
                        }

                    }


                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.elementCheckboxPo = {};
            var url = "<c:url value="/base/elementSelect/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.elementCheckboxPo = response.data;
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }

        buttonHeader.editInit($scope,$http,$window,$timeout, Upload,"/base");
    });
    app.controller('listLinkedCtrl', function ($rootScope,$scope,$http,$window) {
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
            var params={"queryParams":$scope.queryParams,"itemPK":$rootScope.elementId};
            $http.post("<c:url value="/base/elementLinked/list"/>",params)
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
                        $scope.queryParams.init = true;
                        var param = {"queryParams": $scope.queryParams, "ids": ids};
                        $http.post("<c:url value="/base/elementLinked/deletes"/>", param).success(function (data) {
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

        buttonHeader.listInit($scope,$window);
    });
    app.controller('editLinkedCtrl', function ($stateParams,$scope, $http,$window,$timeout, Upload) {
        $scope.message = '';
        $scope.elementLinkedPo = {};
        $scope.errorCode=-1;
        $scope.errorMessages=[];
        $scope.canAdd=PrivilegeService.hasPrivilege('add');
        $scope.canSave=false;
        var url = "<c:url value="/base/elementLinked/create"/>";
        if ($stateParams.linkedId != null) {
            url = "<c:url value="/base/elementLinked/get"/>" + "?id=" + $stateParams.linkedId;
        }
        $http.get(url)
            .success(function (response) {
                if (response.success) {
                    $scope.elementLinkedPo = response.data;
                    $scope.canSave=(PrivilegeService.hasPrivilege('add')&&$scope.elementLinkedPo.id==null)||PrivilegeService.hasPrivilege('update');
                }
                else {
                    bootbox.alert(response.message);
                }
            }).error(function (response) {
            bootbox.alert(response);
        });
        $scope.save = function () {
            $scope.message="";
            if ($scope.editForm.$valid) {
                var postUrl = '<c:url value="/base/elementLinked/saveForUpdated"/>';
                if ($scope.elementLinkedPo.id == null) {
                    postUrl = '<c:url value="/base/elementLinked/saveForCreated"/>';
                }
                $http.post(postUrl, $scope.elementLinkedPo).success(function (data) {
                    if (data.success) {
                        $scope.errorCode=-1;
                        $scope.message = "<eidea:label key="base.save.success"/>";
                        $scope.elementLinkedPo = data.data;
                    }
                    else {
                        $scope.message=data.message;
                        $scope.errorCode=data.errorCode;
                        if (data.errorCode == ERROR_VALIDATE_PARAM) {
                            $scope.errorMessages=data.data;
                        }
                        else {
                            $scope.errorMessages = [data.message];
                        }

                    }


                });
            }
        }
        $scope.create = function () {
            $scope.message = "";
            $scope.elementLinkedPo = {};
            var url = "<c:url value="/base/elementLinked/create"/>";
            $http.get(url)
                .success(function (response) {
                    if (response.success) {
                        $scope.elementLinkedPo = response.data;
                    }
                    else {
                        bootbox.alert(response.message);
                    }
                }).error(function (response) {
                bootbox.alert(response);
            });
        }

        buttonHeader.editInit($scope,$http,$window,$timeout, Upload,"/base");
    });
    app.run([
        'bootstrap3ElementModifier',
        function (bootstrap3ElementModifier) {
            bootstrap3ElementModifier.enableValidationStateIcons(true);
        }]);
</script>
</html>

