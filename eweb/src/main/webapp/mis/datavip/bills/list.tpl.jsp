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
        <div class="span12" >
            <table class="table table-hover table-striped table-condensed" >
                <thead class="">
                <tr>
                    <th><%--序号--%><eidea:label key="base.serialNumber"/></th>
                    <th><%--uid--%><eidea:label key="base.v2017.user.label.userId"/></th>
                    <th><%--系统订单号--%><eidea:label key="cityre.mis.datavip.bill.bigBillCode"/></th>
                    <th><%--购物车订单号--%><eidea:label key="cityre.mis.datavip.bill.billCode"/></th>
                    <th><%--支付宝账号--%><eidea:label key="cityre.mis.datavip.bill.alipayCode"/></th>
                    <th><%--开始时间--%><eidea:label key="cityre.mis.datavip.paymentinfo.starttime"/></th>
                    <th><%--结束时间--%><eidea:label key="cityre.mis.datavip.paymentinfo.endtime"/></th>
                    <th><%--支付类型--%><eidea:label key="cityre.mis.datavip.dicpaytype.typename"/></th>
                    <th><%--支付金额--%><eidea:label key="cityre.mis.datavip.bill.productCost"/></th>
                    <th><%--是否支付--%><eidea:label key="cityre.mis.datavip.bill.payflag"/></th>
                    <th><%--支付时间--%><eidea:label key="cityre.mis.datavip.bill.payTime"/></th>
                    <th><%--邮寄发票--%><eidea:label key="cityre.mis.datavip.bill.postInvoiceFlag"/></th>
                    <th><%--发票类型--%><eidea:label key="cityre.mis.datavip.bill.invoiceType"/></th>
                    <th><%--发票抬头--%><eidea:label key="cityre.mis.datavip.bill.invoiceTitle"/></th>
                    <th><%--纳税人识别号--%><eidea:label key="cityre.mis.datavip.bills.invoicetaxno"/></th>
                    <th><%--地址电话--%><eidea:label key="cityre.mis.datavip.bills.invoiceadtel"/></th>
                    <th><%--开户行及账号--%><eidea:label key="cityre.mis.datavip.bill.bankNo"/></th>
                    <th><%--收件人--%><eidea:label key="cityre.mis.datavip.bill.postUser"/></th>
                    <th><%--收件类型--%><eidea:label key="cityre.mis.datavip.bill.postType"/></th>
                    <th><%--发送地址--%><eidea:label key="cityre.mis.datavip.bills.address"/></th>
                    <th><%--联系电话--%><eidea:label key="cityre.mis.datavip.bills.tel"/></th>
                    <th><%--支付人--%><eidea:label key="cityre.mis.datavip.bills.wPayUser"/></th>
                    <th><%--支付电话--%><eidea:label key="cityre.mis.datavip.bills.wPayTel"/></th>
                    <th><%--发票号--%><eidea:label key="cityre.mis.datavip.bills.invoiceNo"/></th>
                    <th><%--开票日期--%><eidea:label key="cityre.mis.datavip.bills.kpInvoiceTime"/></th>
                    <th><%--用户类型--%><eidea:label key="cityre.mis.datavip.bills.usertype"/></th>
                    <th><%--发票操作--%><eidea:label key="cityre.mis.datavip.invoice.operate"/></th>
                    <th><%--服务操作--%><eidea:label key="cityre.mis.datavip.service.operate"/></th>
                </tr>
                </thead>
                <tbody>

                <tr ng-repeat="model in modelList track by $index" ng-class-even="success">
                    <td>{{(queryParams.pageNo-1)*queryParams.pageSize+$index+1}}</td>
                    <td>
                        {{model.userList.uid}}
                    </td>
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
                        {{model.userPaymentInfo.startTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.userPaymentInfo.endTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.wPayType}}
                    </td>
                    <td>
                        {{model.productCost}}
                    </td>
                    <td>
                        {{model.payFlag}}
                    </td>
                    <td>
                        {{model.payUpdateTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.postInvoiceFlag}}
                    </td>
                    <td>
                        {{model.invoiceType}}
                    </td>
                    <td>
                        {{model.invoiceTitle}}
                    </td>
                    <td>
                        {{model.invoiceTaxNo}}
                    </td>
                    <td>
                        {{model.invoiceAdTel}}
                    </td>
                    <td>
                        {{model.invoiceBankNo}}
                    </td>
                    <td>
                        {{model.postUser}}
                    </td>
                    <td>
                        {{model.dicPostType.typeName}}
                    </td>
                    <td>
                        {{model.address}}
                    </td>
                    <td>
                        {{model.tel}}
                    </td>
                    <td>
                        {{model.wPayUser}}
                    </td>
                    <td>
                        {{model.wPayTel}}
                    </td>
                    <td>
                        {{model.invoiceNo}}
                    </td>
                    <td>
                        {{model.kpInvoiceTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.dicUserType.userTypeName}}
                    </td>
                    <td>
                        <a class="btn btn-primary btn-xs" href="#/serviceEdit?id={{model.billCode}}"><eidea:label
                                key="base.mis.datavip.service.edit"/><%--编辑--%></a>
                    </td>
                    <td>
                        <a class="btn btn-primary btn-xs" href="#/invoiceEdit?id={{model.id}}"><eidea:label
                                key="Base.mis.datavip.invoice.edit"/><%--编辑--%></a>
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