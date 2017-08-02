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
                    <th><%--系统订单号--%><eidea:label key="base.apikey.label.apikey"/></th>
                    <th><%--购物车订单号--%><eidea:label key="base.apikey.label.scopeMask"/></th>
                    <th><%--支付宝账号--%><eidea:label key="base.apikey.label.blackList"/></th>
                    <th><%--开始时间--%><eidea:label key="base.apikey.label.whiteList"/></th>
                    <th><%--结束时间--%><eidea:label key="base.apikey.label.note"/></th>
                    <th><%--支付类型--%><eidea:label key="base.apikey.label.limitMap"/></th>
                    <%--<th>&lt;%&ndash;支付金额&ndash;%&gt;<eidea:label key="base.apikey.label.productCode"/></th>--%>
                    <%--<th>&lt;%&ndash;是否支付&ndash;%&gt;<eidea:label key="base.apikey.label.version"/></th>--%>
                    <%--<th>&lt;%&ndash;支付时间&ndash;%&gt;<eidea:label key="base.apikey.label.isValid"/></th>--%>
                    <%--<th>&lt;%&ndash;邮寄发票&ndash;%&gt;<eidea:label key="base.apikey.label.createTime"/></th>--%>
                    <%--<th>&lt;%&ndash;发票类型&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;发票抬头&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;纳税人识别号&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;地址电话&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;开户行及账号&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;收件人&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;收件类型&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;发送地址&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;联系电话&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;支付人&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;支付电话&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;发票号&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;开票日期&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;操作&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;服务操作&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
                    <%--<th>&lt;%&ndash;用户类型&ndash;%&gt;<eidea:label key="base.apikey.label.expireTime"/></th>--%>
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
                        {{model.bigBillCode}}
                    </td>
                    <td>
                        {{model.billCode}}
                    </td>
                    <td>
                        {{model.alipayBillCode}}
                    </td>
                    <td>
                        {{model.whiteList}}
                    </td>
                    <%--<td>--%>
                        <%--{{model.note}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.wPayType}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.productCost}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.payFlag}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.payUpdateTime|data:"yyyy-MM-dd HH:mm:ss"}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.id}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.typeName}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.postInvoiceFlag}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceType}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceTitle}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.postUser}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.postType}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.address}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.tel}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.wPayUser}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.wPayTel}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceNo}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.kpInvoiceTime|date:"yyyy-MM-dd HH:mm:ss"}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceNoFlag}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceTaxNo}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceAdTel}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.invoiceBankNo}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.dicUserType}}--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--{{model.userTypeName}}--%>
                    <%--</td>--%>
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