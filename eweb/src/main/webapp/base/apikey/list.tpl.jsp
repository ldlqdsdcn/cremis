<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i><eidea:label key="apikey.title"/></a></li>
        </ol>
        <a href="#/edit" class="btn  btn-primary btn-sm" ng-show="canAdd"><eidea:label key="common.button.create"/></a>
        <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                data-target="#searchModal"><eidea:label key="common.button.search"/></button>
        <button type="button" class="btn  btn-primary btn-sm" ng-disabled="!canDelete()"
                ng-click="deleteRecord()" ng-show="canDel"><eidea:label key="common.button.delete"/></button>
        <button type="button" class="btn btn-primary btn-sm" ng-disabled="!canDelete()"
                ng-click="deleteLogic()" ng-show="canDel">逻辑删除</button>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-hover table-striped table-condensed">
                <thead>
                <tr>
                    <th><input type="checkbox" name="selectAll" style="margin:0px;" ng-change="selectAll()"
                               ng-model="delFlag"></th>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--apikey--%><eidea:label key="base.apikey.label.apikey"/></th>
                    <th><%--scopeMask--%><eidea:label key="base.apikey.label.scopeMask"/></th>
                    <th><%--blackList--%><eidea:label key="base.apikey.label.blackList"/></th>
                    <th><%--whiteList--%><eidea:label key="base.apikey.label.whiteList"/></th>
                    <th><%--note--%><eidea:label key="base.apikey.label.note"/></th>
                    <th><%--limitMap--%><eidea:label key="base.apikey.label.limitMap"/></th>
                    <th><%--productCode--%><eidea:label key="base.apikey.label.productCode"/></th>
                    <th><%--version--%><eidea:label key="base.apikey.label.version"/></th>
                    <th><%--isValid--%><eidea:label key="base.apikey.label.isValid"/></th>
                    <th><%--createTime--%><eidea:label key="base.apikey.label.createTime"/></th>
                    <th><%--expireTime--%><eidea:label key="base.apikey.label.expireTime"/></th>
                    <th><%--编辑--%><eidea:label key="common.button.edit"/></th>
                </tr>
                </thead>
                <tbody>

                <tr ng-repeat="model in modelList track by $index" ng-class-even="success">
                    <td>
                        <input type="checkbox" ng-model="model.delFlag">
                    </td>
                    <td>{{(queryParams.pageNo-1)*queryParams.pageSize+$index+1}}</td>
                    <td>
                        {{model.apiKey}}
                    </td>
                    <td>
                        {{model.scopeMask}}
                    </td>
                    <td>
                        {{model.blackList}}
                    </td>
                    <td>
                        {{model.whiteList}}
                    </td>
                    <td>
                        {{model.note}}
                    </td>
                    <td>
                        {{model.limitMap}}
                    </td>
                    <td>
                        {{model.productCode}}
                    </td>
                    <td>
                        {{model.version}}
                    </td>
                    <td>
                        {{model.isValid}}
                    </td>
                    <td>
                        {{model.createTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.expireTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        <a class="btn btn-primary btn-xs" href="#/edit?id={{model.id}}"><eidea:label
                                key="common.button.edit"/><%--编辑--%></a>
                    </td>
                </tr>
                </tbody>
            </table>
            <ul uib-pagination boundary-links="true" total-items="queryParams.totalRecords"
                ng-model="queryParams.pageNo"
                max-size="maxSize" first-text="<eidea:label key="common.label.firstpage"/>"
                previous-text="<eidea:label key="common.label.previouspage"/>"
                next-text="<eidea:label key="common.label.nextpage"/>"
                last-text="<eidea:label key="common.label.lastpage"/>"
                class="pagination-sm" boundary-link-numbers="true" rotate="false" items-per-page="queryParams.pageSize"
                ng-change="pageChanged()"></ul>
            <div class="text-left ng-binding padding_total_banner"><eidea:message key="common.msg.result.prefix"/><span>{{queryParams.totalRecords}}</span><eidea:message
                    key="common.msg.result.suffix"/></div>
        </div>
    </div>
</div>