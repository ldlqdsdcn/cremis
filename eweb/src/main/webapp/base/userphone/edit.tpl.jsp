<%--
User: 刘大磊
Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="base.phone.title"/></a></li>
        </ol>
    </div>
    <form role="form" name="editForm" novalidate ng-submit="save()">
        <div class="row-fluid">
            <div class="span12">
                <br>
                <div class="form-group">
                    <label for="unionUid"><%--unionUid--%><eidea:label
                            key="base.v2017.user.label.unionUid"/></label>
                    <input class="form-control" id="unionUid" type="text" ng-model="userphonePo.unionUid"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.v2017.user.label.unionUid" type="label"/></eidea:message> " required />
                </div>
                <div class="form-group">
                    <label for="phone"><%--phone--%><eidea:label
                            key="base.v2017.phonepin.label.phone"/></label>
                    <input class="form-control" id="phone" type="text" ng-model="userphonePo.phone"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.v2017.phonepin.label.phone" type="label"/></eidea:message> " required/>
                </div>
                <div class="form-group">
                    <label for="isVerified"><%--isVerified--%><eidea:label key="base.v2017.user.label.isVerified"/>
                    <input type="checkbox" class="form-control" id="isVerified" ng-model="userphonePo.isVerified"
                           ng-true-value="1" ng-false-value="0"/></label>
                </div>
                <div class="form-group">
                <label for="isPrimary"><%--isPrimary--%><eidea:label key="base.v2017.useremail.label.isPrimary"/>
                <input class="form-control" id="isPrimary" type="checkbox" ng-model="userphonePo.isPrimary"
                       ng-true-value="1" ng-false-value="0"/></label>

            </div>

                <div class="form-group">
                    <label for="createTime"><%--createTime--%><eidea:label key="base.product.label.createTime"/></label>
                    <input class="form-control" id="createTime" type="text" ng-model="userphonePo.createTime"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.product.label.createTime" type="label"/></eidea:message> "
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true"/>
                    <%--<span class="input-group-addon"><span--%>
                    <%--class="glyphicon glyphicon-calendar"></span></span>--%>
                </div>
                <div class="form-group">
                    <label for="updateTime"><%--createTime--%><eidea:label key="base.product.label.updateTime"/></label>
                    <input class="form-control" id="updateTime" type="text" ng-model="userphonePo.updateTime"
                           placeholder="<eidea:message key="common.please.input">
                    <eidea:param value="base.product.label.updateTime" type="label"/></eidea:message> "
                           uib-datepicker-popup="yyyy-MM-dd HH:mm:ss" ng-disabled="true"/>
                    <%--<span class="input-group-addon"><span--%>
                    <%--class="glyphicon glyphicon-calendar"></span></span>--%>
                </div>
                <div class="form-group">
                    <p class="text-right">
                        <button type="reset" ng-click="create()" class="btn btn-default btn-sm"
                                ng-show="canAdd"><%--新建--%>
                            <eidea:label key="common.button.create"/></button>
                        <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label
                                key="common.button.save"/></button>
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
