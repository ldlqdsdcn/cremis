<%--
User: 刘大磊
Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="base.phonePin.title"/></a></li>
        </ol>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <br>
            <form role="form" name="editForm" novalidate ng-submit="save()">
                <div class="form-group">
                    <label for="phone"><%--phone--%><eidea:label key="base.v2017.phonepin.label.phone"/></label>
                    <input type="text" class="form-control" id="phone"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.phonepin.label.phone" type="label"/></eidea:message>"
                           ng-model="phonepinPo.phone" ng-minlength="11" ng-maxlength="20" ng-pattern="/^1[3|4|5|7|8][0-9]{9}$/">

                </div>
                <div class="form-group">
                    <label for="pinToken"><%--pinToken--%><eidea:label
                            key="base.v2017.phonepin.label.pinToken"/></label>
                    <input type="text" class="form-control" id="pinToken"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.phonepin.label.pinToken" type="label"/></eidea:message>"
                           ng-model="phonepinPo.pinToken" ng-minlength="2"ng-maxlength="64">

                </div>
                <div class="form-group">
                    <label for="pinCode"><%--pinCode--%><eidea:label key="base.v2017.phonepin.label.pinCode"/></label>
                    <input type="text" class="form-control" id="pinCode"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.phonepin.label.pinCode" type="label"/></eidea:message>"
                           ng-model="phonepinPo.pinCode" ng-minlength="6" ng-mexlength="6" ng-pattern="/^[0-9]{6}$/">

                </div>
                <div class="form-group">
                    <label for="action"><%--action--%><eidea:label key="base.v2017.phonepin.label.action"/></label>
                    <select id="action" ng-model = "phonepinPo.action" class="form-control" ng-options="option.key as option.desc for option in actionTypeList"></select>
                </div>

                <div class="form-group">
                    <label for="createTime"><%--createTime--%><eidea:label
                            key="base.v2017.user.label.createTime"/></label>
                    <input type="datetime" class="form-control" id="createTime"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.createTime" type="label"/></eidea:message>"
                           ng-model="phonepinPo.createTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true">
                    <div class="form-group">

                        <label for="expireTime"><%--expireTime--%><eidea:label
                                key="base.apikey.label.expireTime"/></label>
                        <input type="datetime" class="form-control" id="expireTime"
                               placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.expireTime" type="label"/></eidea:message>"
                               ng-model="phonepinPo.expireTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true">

                        <div class="form-group">
                            <p class="text-right">
                                <button type="reset" ng-click="create()" class="btn btn-default btn-sm"
                                        ng-show="canAdd"><%--新建--%><eidea:label key="common.button.create"/></button>
                                <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%>
                                    <eidea:label key="common.button.save"/></button>
                                <a href="#/list" class="btn btn-default btn-sm"><%--返回--%><eidea:label
                                        key="common.button.back"/></a>
                            </p>
                        </div>
                        <div class="form-group">
                            <p class="text-center" style="color: red">
                                {{message}}
                            </p>
                            <p>
                        <span ng-repeat="error in errors track by $index">
                            {{error.message}}
                        </span>
                            </p>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>