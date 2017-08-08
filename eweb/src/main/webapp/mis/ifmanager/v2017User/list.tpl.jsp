<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <ol class="breadcrumb">
            <li><a href="javascript:;"><i class="icon icon-tasks"></i><eidea:label key="area.title"/></a></li>
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
                    <%--<th>&lt;%&ndash;id&ndash;%&gt;<eidea:label key="base.area.label.areaid"/></th>--%>
                    <th><%--unionUid--%><eidea:label key="base.v2017.user.label.unionUid"/></th>
                    <th><%--userId--%><eidea:label key="base.v2017.user.label.userId"/></th>
                    <th><%--nickName--%><eidea:label key="base.v2017.user.label.nickname"/></th>
                    <th><%--realName--%><eidea:label key="base.v2017.user.label.realname"/></th>
                    <th><%--sex--%><eidea:label key="base.v2017.user.label.sex"/></th>
                    <th><%--birthday--%><eidea:label key="base.v2017.user.label.birthday"/></th>
                    <th><%--userIcon--%><eidea:label key="base.v2017.user.label.userIcon"/></th>
                    <th><%--passwordSalt--%><eidea:label key="base.v2017.user.label.passwordSalt"/></th>
                    <th><%--passwordHash--%><eidea:label key="base.v2017.user.label.passwordHash"/></th>
                    <th><%--oldpasswordHash--%><eidea:label key="base.v2017.user.label.oldpasswordHash"/></th>
                    <th><%--isVerified--%><eidea:label key="base.v2017.user.label.isVerified"/></th>
                    <th><%--isValid--%><eidea:label key="base.v2017.user.label.isValid"/></th>
                    <th><%--accountSecret--%><eidea:label key="base.v2017.user.label.accountSecret"/></th>
                    <th><%--createTime--%><eidea:label key="base.v2017.user.label.createTime"/></th>
                    <th><%--updateTime--%><eidea:label key="base.v2017.user.label.updateTime"/></th>
                    <%--<th>&lt;%&ndash;编辑&ndash;%&gt;<eidea:label key="common.button.edit"/></th>--%>
                </tr>
                </thead>
                <tbody>

                <tr ng-repeat="model in modelList track by $index" ng-class-even="success">
                    <%--<td>--%>
                        <%--<input type="checkbox" ng-model="model.delFlag">--%>
                    <%--</td>--%>
                    <td>{{(queryParams.pageNo-1)*queryParams.pageSize+$index+1}}</td>
                    <td>
                        {{model.unionUid}}
                    </td>
                    <td>
                        {{model.userId}}
                    </td>
                    <td>
                        {{model.nickName}}
                    </td>
                    <td>
                        {{model.realName}}
                    </td>
                    <td>
                        {{model.sex}}
                    </td>
                    <td>
                        {{model.birthday|date:"yyyy-MM-dd"}}
                    </td>
                    <td>
                        {{model.userIcon}}
                    </td>
                    <td>
                        {{model.passwordSalt}}
                    </td>
                    <td>
                        {{model.passwordHash}}
                    </td>
                    <td>
                        {{model.oldpasswordHash}}
                    </td>
                    <td>
                        {{model.isVerified}}
                    </td>
                    <td>
                        {{model.isValid}}
                    </td>
                    <td>
                        {{model.accountSecret}}
                    </td>
                    <td>
                        {{model.createTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.updateTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <%--<td>--%>
                        <%--<a class="btn btn-primary btn-xs" href="#/edit?id={{model.id}}"><eidea:label--%>
                                <%--key="common.button.edit"/>&lt;%&ndash;编辑&ndash;%&gt;</a>--%>
                    <%--</td>--%>
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