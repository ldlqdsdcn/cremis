<%--
  Created by IntelliJ IDEA.
  User: cityre
  Date: 2017/6/28
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="selectCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div id="selectCity" class="modal-dialog" ng-app='selectCityApp'>
        <div class="modal-content" ng-controller="selectCityCtrl">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"><eidea:message key="common.selectCity.title"/></h4>
            </div>
            <form name="editForm" novalidate ng-submit="save()" method="post" class="form-horizontal form-label-left">
                <div class="modal-body">
                    <div class="form-group">
                        <select id="provinceList" class="form-control"   ng-change="changeCity()"
                                ng-options="province.id as province.province for province in provinceList" ng-model="provinceId"></select>
                    </div>
                    <div class="form-group">
                        <select id="cityList" class="form-control" ng-options="city.id as city.city for city in cityList" ng-model="cityId"></select>
                    </div>

                </div>
                <div class="modal-footer">
                       <button type="submit" class="btn btn-primary" ><eidea:label key="common.button.confirm"/></button>
                </div>
                <div class="form-group">
                    <p class="text-center" style="color: red"  >
                        {{message}}
                    </p>
                    <p class="text-center" style="color: red" ng-repeat="msg in errorMessages track by $index" ng-show="errorCode==3">
                        {{msg.message}}
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var app = angular.module('selectCityApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate']).config(['$routeProvider', function ($routeProvider) {
    }]);
    app.controller('selectCityCtrl', function ($scope, $http) {
        $scope.provinceList=[];
        $scope.cityList=[];
        $scope.provinceId=null;
        $scope.cityId=null;
        $scope.getProvinceList = function () {
            $http.get("<c:url value="/common/userCenter/getProvinceList"/>").success(function (data) {
                if (data.success) {
                    $scope.provinceList = data.data;
                    if($scope.cityId==null)
                    {
                        $scope.provinceId=$scope.provinceList[0].id;
                        $scope.getCityList($scope.provinceList[0].id);
                    }
                } else {
                    bootbox.alert(data.message);
                }
            });
        }
        $scope.changeCity=function () {
            $scope.getCityList($scope.provinceId);
        }
        $scope.getCityList = function (provinceId) {
            $http.get("<c:url value="/common/userCenter/getCityList/"/>"+provinceId).success(function (data) {
                if (data.success) {
                    $scope.cityList = data.data;
                    $scope.cityId=$scope.cityList[0].id;
                } else {
                    bootbox.alert(data.message);
                }
            });
        }

        $scope.save=function () {
                $http.get("<c:url value="/common/userCenter/selectCity/"/>"+$scope.cityId).success(function (data) {
                    if (data.success) {
                        $scope.message=data.data;
                        $('#selectCityModal').modal('hide');
                        window.location.href="<c:url value="/index.jsp"/>";
                    }else {
                        $scope.message=data.message;
                    }
                });

        }
        $scope.getProvinceList();
    });
    angular.bootstrap(document.getElementById("selectCity"), ['selectCityApp']);
</script>
