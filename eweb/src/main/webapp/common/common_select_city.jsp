<%--
  Created by IntelliJ IDEA.
  User: cityre
  Date: 2017/6/28
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="selectCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div id="changePassword" class="modal-dialog" ng-app='passwordApp'>
        <div class="modal-content" ng-controller="listCtrl">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">请选择城市</h4>
            </div>
            <form name="editForm" novalidate ng-submit="save()" method="post" class="form-horizontal form-label-left">
                <div class="modal-body">
                    <div class="form-group">
                        <select id="provinceList" ng-model="datadictPo.dataType"
                                ng-options="datadictTypePo.value as datadictTypePo.value for datadictTypePo in datadictTypeList" required></select>
                    </div>
                    <div class="form-group">
                        <select id="cityList"></select>
                    </div>

                </div>
                <div class="modal-footer">
                       <button type="submit" class="btn btn-primary" ><eidea:label key="common.button.save"/></button>
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
    var app = angular.module('passwordApp', ['ngRoute', 'ui.bootstrap', 'jcs-autoValidate']).config(['$routeProvider', function ($routeProvider) {
    }]);
    app.controller('listCtrl', function ($scope, $http) {
        $scope.save=function () {
            if($scope.userBo.oldPassword == $scope.userBo.password){
                $scope.userBo.password="";
                $scope.userBo.repassword="";
                $scope.message="<eidea:message key="change.password.msg.new.old.password.can.not.same"/>";
                return false;
            }else{
                $scope.message="";
            }
            if($scope.userBo.password == $scope.userBo.repassword){
                $scope.message="";
            }else{
                $scope.message="<eidea:message key="change.password.msg.new.confirm.password.not.same"/>";
                $scope.userBo.repassword="";
                return false;
            }
            //用户密码修改
            if ($scope.editForm.$valid) {
                $scope.unableChange=true;
                changePasswordVo={oldPassword:md5($scope.userBo.oldPassword),password:md5($scope.userBo.password)};
                $http.post("<c:url value="/common/userCenter/changePassword"/>",changePasswordVo).success(function (data) {
                    if (data.success) {
                        $scope.message=data.data;
                    }else {
                        $scope.message=data.message;
                    }
                    $scope.unableChange=false;
                });
            }
        }
    });
    angular.bootstrap(document.getElementById("changePassword"), ['passwordApp']);
</script>
