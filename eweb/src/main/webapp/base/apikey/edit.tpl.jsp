<%--
User: 刘大磊
Date: 2017-06-28 15:50:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="editCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon-fire"></i><eidea:label key="apikey.title"/></a></li>
        </ol>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <br>
            <form role="form" name="editForm" novalidate ng-submit="save()"
                  class="form-horizontal form-label-left input_mask">
                <table class="table table-borderless">
                    <tr>
                        <td class="control-label"><eidea:label key="base.apikey.label.apikeyId"/></td>
                        <td class="form-group">
                            <input type="text" class="form-control" id="apikeyId"
                                   placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.apikeyId" type="label"/></eidea:message>"
                                   ng-model="misApiKeyPo.id">
                        </td>
                        <td class="control-label"><eidea:label key="base.apikey.label.scopeMask"/></td>
                        <td class="form-group"><input type="text" class="form-control" id="scopeMask"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.scopeMask" type="label"/></eidea:message>"
                                                      ng-model="misApiKeyPo.scopeMask"></td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--apiKey--%><eidea:label key="base.apikey.label.apikey"/></td>
                        <td class="form-group" colspan="3"><input type="text" id="apiKey" class="form-control"
                                                                  ng-model="misApiKeyPo.apiKey" ng-disabled="true"
                                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.apikey" type="label"/></eidea:message> "/>
                        </td>
                    </tr>
                    <tr>
                        <td class="control-label"><eidea:label key="base.apikey.label.blackList"/></td>
                        <td class="form-group"><input type="text" class="form-control" id="blackList"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.blackList" type="label"/></eidea:message>"
                                                      ng-model="misApiKeyPo.blackList"></td>
                        <td class="control-label"><%--whiteList--%><eidea:label key="base.apikey.label.whiteList"/></td>
                        <td class="form-group"><input type="text" class="form-control" id="whiteList"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.whiteList" type="label"/></eidea:message>"
                                                      ng-model="misApiKeyPo.whiteList">
                        </td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--note--%><eidea:label key="base.apikey.label.note"/></td>
                        <td class="form-group">
                            <input type="text" class="form-control" id="note"
                                   placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.note" type="label"/></eidea:message>"
                                   ng-model="misApiKeyPo.note">
                        </td>
                        <td class="control-label"><%--isValid--%><eidea:label
                                key="base.apikey.label.isValid"/></td>
                        <td>
                        <input type="checkbox"  ng-model="misApiKeyPo.isValid" ng-true-value="1"
                               ng-false-value="0"/></td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--productCode--%><eidea:label
                                key="base.apikey.label.productCode"/></td>
                        <td class="form-group"><input type="text" class="form-control" id="productCode"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.productCode" type="label"/></eidea:message>"
                                                      ng-model="misApiKeyPo.productCode">
                        </td>
                        <td class="control-label"><%--version--%><eidea:label key="base.apikey.label.version"/></td>
                        <td class="form-group"><input type="text" class="form-control" id="version"
                                                      placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.version" type="label"/></eidea:message>"
                                                      ng-model="misApiKeyPo.version" ng-disabled="true"></td>
                    </tr>
                    <tr>
                        <td class="control-label"><%--limitMap--%><eidea:label key="base.apikey.label.limitMap"/></td>
                        <td class="form-group" colspan="3"><input type="text" class="form-control" id="limitMap"
                                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.limitMap" type="label"/></eidea:message>"
                                                                  ng-model="misApiKeyPo.limitMap">
                        </td>

                    </tr>
                    <tr>
                        <td class="control-label"><%--createTime--%><eidea:label
                                key="base.apikey.label.createTime"/></td>
                        <td class="input-group date bootstrap-datetime"><input type="text" class="form-control"
                                                                               id="createTime"
                                                                               placeholder="<eidea:message key="common.please.input"><eidea:param value="base.apikey.label.createTime" type="label"/></eidea:message>"
                                                                               ng-model="misApiKeyPo.createTime"
                                                                               uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span></td>
                        <td class="control-label"><%--expireTime--%><eidea:label
                                key="base.apikey.label.expireTime"/></td>
                        <td class="input-group date bootstrap-datetime"><input type="text" class="form-control"
                                                                               id="expireTime"
                                                                               placeholder="<eidea:message key="common.please.input">
                        <eidea:param value="base.apikey.label.expireTime" type="label"/></eidea:message>"
                                                                               ng-model="misApiKeyPo.expireTime"
                                                                               uib-datepicker-popup="yyyy-MM-dd HH:mm:ss">
                            <span class="input-group-addon"><span
                                    class="glyphicon glyphicon-calendar"></span></span></td>
                    </tr>
                </table>
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
            </form>
        </div>
    </div>
</div>