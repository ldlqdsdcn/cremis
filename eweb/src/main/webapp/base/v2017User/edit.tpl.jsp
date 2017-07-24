<%--
User: 刘大磊
Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="area.title"/></a></li>
        </ol>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <br>
            <form role="form" name="editForm" novalidate  ng-submit="save()" class="form-horizontal form-label-left input_mask">
                <table class=" table table-borderless">
                    <tr>
                        <td class="control-label"><%--uniodId--%><eidea:label key="base.v2017.user.label.unionUid"/></td>
                        <td class="form-group" colspan="3">
                            <input type="text" id="unionUid" class="form-control" ng-model="misUserPo.unionUid" placeholder="<eidea:message key="common.please.input">
                                    <eidea:param value="base.v2017.user.label.unionUid" type="label"/></eidea:message> " ng-disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--userId--%><eidea:label key="base.v2017.user.label.userId"/></td>
                        <td class="form-group" colspan="3">
                            <input type="text" class="form-control" ng-model="misUserPo.userId"
                                   placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.userId" type="label"/></eidea:message> ">
                        </td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--nickName--%><eidea:label key="base.v2017.user.label.nickname"/></td>
                        <td class="form-group"><input type="text" class="form-control" ng-model="misUserPo.nickName"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.nickname" type="label"/></eidea:message> " ></td>
                        <td class="control-label"><%--realName--%><eidea:label key="base.v2017.user.label.realname"/></td>
                        <td class="form-group"><input type="text" class="form-control" ng-model="misUserPo.realName"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.realname" type="label"/> </eidea:message> "> </td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--sex--%><eidea:label key="base.v2017.user.label.sex"/></td>
                        <td class="form-group"><select class="form-control" ng-model="misUserPo.sex"
                                                     ng-options="option.key as option.desc for option in sexTypeList"></select></td>
                        <td class="control-label"><%--birthday--%><eidea:label key="base.v2017.user.label.birthday"/></td>
                        <td class="form-group"><input type="text" class="form-control" ng-model="misUserPo.birthday"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.birthday" type="label"/></eidea:message> "></td>
                    </tr>
                    <tr>
                        <td class="control-label"><eidea:label key="base.v2017.user.label.passwordSalt"/></td>
                        <td class="form-group"colspan="3"><input class="form-control" type="text" ng-model="misUserPo.passwordSalt"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.passwordSalt" type="label"/></eidea:message> "  ng-disabled="true"></td>
                    </tr>
                    <tr>
                        <td class="control-label"><eidea:label key="base.v2017.user.label.passwordHash"/></td>
                        <td class="form-group"colspan="3"><input class="form-control" type="text" ng-model="misUserPo.passwordHash"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.passwordHash" type="label"/></eidea:message> " ng-disabled="true"></td>
                    </tr>
                    <tr>
                        <td class="control-label"><eidea:label key="base.v2017.user.label.oldpasswordHash"/></td>
                        <td class="form-group"colspan="3"><input class="form-control" type="text" ng-model="misUserPo.oldpasswordHash"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.oldpasswordHash" type="label"/></eidea:message> "ng-disabled="true"> </td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--sex--%><eidea:label key="base.v2017.user.label.isVerified"/></td>
                        <td class="form-group">
                            <select class="form-control" ng-model="misUserPo.isVerified"
                                    ng-options="option.key as option.value for option in verifiedTypeList"></select> </td>
                        <td class="control-label"><%--birthday--%><eidea:label key="base.v2017.user.label.isValid"/></td>
                        <td class="form-group"><input type="checkbox" ng-model="misUserPo.isValid"
                                                     ng-true-value="1" ng-false-value="0"></td>
                    </tr>
                    <tr>
                        <td class="control-label"><eidea:label key="base.v2017.user.label.accountSecret"/></td>
                        <td class="form-group" colspan="3"><input class="form-control" type="text" ng-model="misUserPo.accountSecret"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.accountSecret" type="label"/></eidea:message> "> </td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--sex--%><eidea:label key="base.v2017.user.label.createTime"/></td>
                        <td class="input-group date bootstrap-datetime"><input type="text" class="form-control" ng-model="misUserPo.createTime"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.createTime"/></eidea:message> " uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span></td>
                        <td class="control-label"><%--birthday--%><eidea:label key="base.v2017.user.label.updateTime"/></td>
                        <td class="input-group date bootstrap-datetime"><input type="text" class="form-control" ng-model="misUserPo.updateTime"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.updateTime" type="label"/></eidea:message> " uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span></td>
                    </tr>
                </table>
                <div class="form-group">
                    <p class="text-right">
                        <button type="reset" ng-click="create()"  class="btn btn-default btn-sm" ng-show="canAdd"><%--新建--%><eidea:label key="common.button.create"/></button>
                        <button type="submit" class="btn btn-default btn-sm" ng-show="canSave"><%--保存--%><eidea:label key="common.button.save"/></button>
                        <a href="#/list" class="btn btn-default btn-sm"><%--返回--%><eidea:label key="common.button.back"/></a>
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
            </form>
        </div>
    </div>
</div>