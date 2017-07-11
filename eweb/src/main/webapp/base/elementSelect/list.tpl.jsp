<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div  class="container-fluid" ng-controller="listSelectCtrl">
    <%--<jsp:include page="/common/common_list_button.jsp"/>--%>
    <div class="row-fluid"> <div class="page-header">
        <a ui-sref="itemEdit({itemId:null})" ui-sref-active="active" class="btn btn-primary btn-sm"><%--新建--%>
            <eidea:label key="common.button.create"/></a>
        <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                data-target="#searchModal"><eidea:label key="common.button.search"/></button>
        <button type="button" class="btn  btn-primary btn-sm" ng-disabled="!canDelete()"
                ng-click="deleteRecord()" ng-show="canDel"><eidea:label key="common.button.delete"/></button>
    </div>
        <div class="span12">
            <table  class="table table-hover table-striped table-condensed">
                <thead>
                <tr>
                    <th><input type="checkbox" name="selectAll" style="margin:0px;" ng-change="selectAll()"
                               ng-model="delFlag"></th>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--sql--%><eidea:label key="base.elementSelect.label.sql"/></th>
                    <th><%--element_id--%><eidea:label key="base.element.label.id"/></th>
                    <th><%--keyType--%><eidea:label key="base.elementSelect.label.keyType"/></th>
                    <th><%--valueType--%><eidea:label key="base.elementSelect.label.valueType"/></th>
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
                        {{model.sql}}
                    </td>
                    <td>
                        {{model.elementId}}
                    </td>
                    <td>
                        {{model.keyType}}
                    </td>
                    <td>
                        {{model.valueType}}
                    </td>
                    <td>
                            <a ui-sref="itemEdit({itemId:model.id})" ui-sref-active="active" class="btn btn-primary btn-xs">
                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;<eidea:label key="common.button.edit"/></a>
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