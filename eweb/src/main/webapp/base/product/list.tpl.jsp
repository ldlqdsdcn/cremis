<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i><eidea:label key="base.product.title"/></a></li>
        </ol>
        <a href="#/edit" class="btn  btn-primary btn-sm" ng-show="canAdd"><eidea:label key="common.button.create"/></a>
        <button type="button" class="btn  btn-primary btn-sm" id="search_but" data-toggle="modal"
                data-target="#searchModal"><eidea:label key="common.button.search"/></button>
        <%--<button type="button" class="btn  btn-primary btn-sm" ng-disabled="!canDelete()"--%>
                <%--ng-click="deleteRecord()" ng-show="canDel"><eidea:label key="common.button.delete"/></button>--%>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-hover table-striped table-condensed">
                <thead>
                <tr>
                    <%--<th><input type="checkbox" name="selectAll" style="margin:0px;" ng-change="selectAll()"--%>
                               <%--ng-model="delFlag"></th>--%>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--productCode--%><eidea:label key="base.product.label.productCode"/></th>
                    <th><%--productName--%><eidea:label key="base.product.label.productName"/></th>
                    <th><%--paltform--%><eidea:label key="base.product.label.platform"/></th>
                    <th><%--createTime--%><eidea:label key="base.product.label.createTime"/></th>
                    <th><%--updateTime--%><eidea:label key="base.product.label.updateTime"/></th>
                    <th><%--编辑--%><eidea:label key="common.button.edit"/></th>
                </tr>
                </thead>
                <tbody>

                <tr ng-repeat="model in modelList track by $index" ng-class-even="success">
                    <%--<td>--%>
                        <%--<input type="checkbox" ng-model="model.delFlag">--%>
                    <%--</td>--%>
                    <td>{{(queryParams.pageNo-1)*queryParams.pageSize+$index+1}}</td>
                    <td>
                        {{model.productCode}}
                    </td>
                    <td>
                        {{model.productName}}
                    </td>
                    <td>
                        {{model.platform}}
                    </td>
                    <td>
                        {{model.createTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.updateTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        <a class="btn btn-primary btn-xs" href="#/edit?productCode={{model.productCode}}"><eidea:label
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