<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i><eidea:label key="base.email.title"/></a></li>
        </ol>
        <a href="#/edit" class="btn  btn-primary btn-sm" ng-show="canAdd"><eidea:label key="common.button.create"/></a>
        <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                data-target="#searchModal"><eidea:label key="common.button.search"/></button>
        <button type="button" class="btn  btn-primary btn-sm" ng-disabled="!canDelete()"
                ng-click="deleteRecord()" ng-show="canDel"><eidea:label key="common.button.delete"/></button>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-hover table-striped table-condensed">
                <thead>
                <tr>
                    <th><input type="checkbox" name="selectAll" style="margin:0px;" ng-change="selectAll()"
                               ng-model="delFlag"></th>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--unionid--%><eidea:label key="base.v2017.user.label.unionUid"/></th>
                    <th><%--address--%><eidea:label key="base.v2017.useremail.label.address"/></th>
                    <th><%--isverified--%><eidea:label key="base.v2017.user.label.isVerified"/></th>
                    <th><%--isPrimary--%><eidea:label key="base.v2017.useremail.label.isPrimary"/></th>
                    <th><%--pinToken--%><eidea:label key="base.v2017.phonepin.label.pinToken"/></th>
                    <th><%--expireTime--%><eidea:label key="base.apikey.label.expireTime"/></th>
                    <th><%--createTime--%><eidea:label key="base.apikey.label.createTime"/></th>
                    <th><%--updateTime--%><eidea:label key="base.v2017.user.label.updateTime"/></th>
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
                        {{model.unionUid}}
                    </td>
                    <td>
                        {{model.address}}
                    </td>
                    <td>
                        {{model.isVerified}}
                    </td>
                    <td>
                        {{model.isPrimary}}
                    </td>
                    <td>
                        {{model.pinToken}}
                    </td>
                    <td>
                        {{model.expireTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.createTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.updateTime|date:"yyyy-MM-dd HH:mm:ss"}}
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