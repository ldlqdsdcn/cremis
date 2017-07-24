<%--
User: 刘大磊
Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="base.email.title"/></a></li>
        </ol>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <br>
            <form role="form" name="editForm" novalidate ng-submit="save()">
                <div class="form-group">
                    <label for="unionUid"><%--unionUid--%><eidea:label key="base.v2017.user.label.unionUid"/></label>
                    <input type="text" class="form-control" id="unionUid"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.unionUid" type="label"/></eidea:message>"
                           ng-model="useremailPo.unionUid">

                </div>
                <div class="form-group">
                    <label for="address"><%--address--%><eidea:label
                            key="base.v2017.useremail.label.address"/></label>
                    <input type="text" class="form-control" id="address"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.useremail.label.address" type="label"/></eidea:message>"
                           ng-model="useremailPo.address">

                </div>
                <div class="form-group">
                    <label for="isVerified"><%--isVerified--%><eidea:label key="base.v2017.user.label.isVerified"/>
                    <input type="checkbox" id="isVerified" ng-model="useremailPo.isVerified" ng-true-value="1" ng-false-value="0" class="form-control" >
                    </label>
                    &nbsp;&nbsp;
                    <label for="isPrimary"><%--isPrimary--%><eidea:label key="base.v2017.useremail.label.isPrimary"/>
                    <input type="checkbox" class="form-control" id="isPrimary" ng-model="useremailPo.isPrimary" ng-true-value="1" ng-false-value="0">
                    </label>
                </div>
                <div class="form-group">
                    <label for="pinToken"><%--pinToken--%><eidea:label key="base.v2017.phonepin.label.pinToken"/></label>
                    <input type="text" class="form-control" id="pinToken"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.phonepin.label.pinToken"/></eidea:message> "
                           ng-model="useremailPo.pinToken">
                </div>
                <label for="expireTime"><%--expireTime--%><eidea:label
                        key="base.apikey.label.expireTime"/></label>
                <input type="text" class="form-control" id="expireTime"
                       placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.expireTime" type="label"/></eidea:message>"
                       ng-model="useremailPo.expireTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true">
                <div class="form-group">
                    <label for="createTime"><%--createTime--%><eidea:label
                            key="base.v2017.user.label.createTime"/></label>
                    <input type="text" class="form-control" id="createTime"
                           placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.createTime" type="label"/></eidea:message>"
                           ng-model="useremailPo.createTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true">
                    <div class="form-group">

                        <label for="updateTime"><%--updateTime--%><eidea:label
                                key="base.v2017.user.label.updateTime"/></label>
                        <input type="text" class="form-control" id="updateTime"
                               placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.updateTime" type="label"/></eidea:message>"
                               ng-model="useremailPo.updateTime" uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true">

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