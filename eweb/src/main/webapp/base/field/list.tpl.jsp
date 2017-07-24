<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div  class="container-fluid" ng-controller="listFieldCtrl">
    <div class="page-header" >
        <a ui-sref="editField({fieldId:null})" ui-sref-active="active" class="btn btn-primary btn-sm" ng-show="canAdd"><%--新建--%>
            <eidea:label key="common.button.create"/> </a>
        <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                data-target="#searchModal"><eidea:label key="common.button.search"/></button>
        <button type="button" class="btn  btn-primary btn-sm" ng-disabled="!canDelete()"
                ng-click="deleteRecord()" ng-show="canDel" ><eidea:label key="common.button.delete"/></button>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table  class="table table-hover table-striped table-condensed">
                <thead>
                <tr>
                    <th><input type="checkbox" name="selectAll" style="margin:0px;" ng-change="selectAll()"  ng-model="delFlag"></th>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--seqNo--%><eidea:label key="base.field.label.seqNo"/></th>
                    <th><%--名称--%><eidea:label key="base.field.label.name"/></th>
                    <th><%--字段名--%><eidea:label key="base.field.label.fieldName"/></th>
                    <th><%--是否有效--%><eidea:label key="base.field.label.isactive"/></th>
                    <th><%--inputType--%><eidea:label key="base.field.label.inputType"/></th>
                    <th><%--isDisplayed--%><eidea:label key="base.field.label.isDisplayed"/></th>
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
                        {{model.seqNo}}
                    </td>
                    <td>
                        {{model.name}}
                    </td>
                    <td>
                        {{model.fieldName}}
                    </td>
                    <td>
                        {{model.isactive}}
                    </td>
                    <td>
                        {{model.inputType}}
                    </td>
                    <td>
                        {{model.isDisplayed}}
                    </td>
                    <td>
                        <a ui-sref="editField({fieldId:model.id})" ui-sref-active="active" class="btn btn-primary btn-xs"><%--编辑--%>
                            <i class="fa fa-pencil-square-o" aria-hidden="true"/>&nbsp;<eidea:label key="common.button.edit"/> </a>
                    </td>
                </tr>
                </tbody>
            </table>
            <ul uib-pagination boundary-links="true" total-items="queryParams.totalRecords" ng-model="queryParams.pageNo"
                max-size="maxSize" first-text="<eidea:label key="common.label.firstpage"/>" previous-text="<eidea:label key="common.label.previouspage"/>" next-text="<eidea:label key="common.label.nextpage"/>" last-text="<eidea:label key="common.label.lastpage"/>"
            class="pagination-sm" boundary-link-numbers="true" rotate="false" items-per-page="queryParams.pageSize"
            ng-change="pageChanged()"></ul>
            <div class="text-left ng-binding padding_total_banner"><eidea:message key="common.msg.result.prefix"/><span>{{queryParams.totalRecords}}</span><eidea:message key="common.msg.result.suffix"/></div>
        </div>
    </div>
</div>