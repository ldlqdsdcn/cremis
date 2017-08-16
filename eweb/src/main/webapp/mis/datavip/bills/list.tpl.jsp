<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<div class="container-fluid" ng-controller="listCtrl">
    <div class="page-header">
        <form role="form" name="editForm" novalidate ng-submit="pageChanged()" class="form-inline form-label-left">
            <table class="table table-borderless">
                <tr>
                    <td class="control-label"><%--订购单号--%><eidea:label key="cityre.mis.datavip.bill.bigBillCode"/>:</td>
                    <td class="form-group"><input type="text" class="form-control" ng-model="bigBillCode"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="cityre.mis.datavip.bill.bigBillCode" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label"><%--购物车订单号--%><eidea:label key="cityre.mis.datavip.bill.billCode"/>:</td>
                    <td class="form-group"><input type="text" class="form-control" ng-model="billCode"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="cityre.mis.datavip.bill.billCode" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label"><%--用户UID--%><eidea:label key="base.v2017.user.label.userId"/>:</td>
                    <td class="form-group"><input type="text" class="form-control" ng-model="uid"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="base.v2017.user.label.userId" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label"><%--支付宝账号--%><eidea:label key="cityre.mis.datavip.bill.alipayCode"/>:</td>
                    <td class="form-group"><input type="text" class="form-control" ng-model="alipayBillCode"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="cityre.mis.datavip.bill.alipayCode" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label"><%--支付类型--%><eidea:label key="cityre.mis.datavip.dicpaytype.typename"/>:
                    </td>
                    <td class="form-group"><select class="form-control" ng-model="typeCode" ng-init="typeCode='null'"><option value="null">请选择</option><option ng-repeat="option in payTypeList" value="{{option.typeCode}}">{{option.typeName}}</option></select>
                    </td>
                    <td class="control-label"><%--支付状态--%><eidea:label key="cityre.mis.datavip.bill.payflag"/></td>
                    <td class="form-group"><select class="form-control" ng-model="payFlag" ng-init="payFlag='null'"
                    >   <option value="null">请选择</option>
                        <option ng-repeat="option in billsFlag" value="{{option.key}}">{{option.value}}</option></select></td>

                </tr>
                <tr>
                    <td class="control-label"><%--开票与否--%><eidea:label key="cityre.mis.datavip.bill.kpInvoiceType"/></td>
                    <td class="form-group"><select class="form-control" ng-model="invoiceNoFlag"  ng-init="invoiceNoFlag='null'"><option value="null">请选择</option><option ng-repeat="option in invoiceStateList" value="{{option.key}}">{{option.value}}</option></select></td>
                    <td class="control-label"><%--邮寄发票--%><eidea:label
                            key="cityre.mis.datavip.bill.postInvoiceFlag"/></td>
                    <td class="form-group"><select class="form-control" ng-model="postInvoiceFlag" ng-init="postInvoiceFlag='null'"><option value="null">请选择</option><option ng-repeat="option in falgList" value="{{option.key}}">{{option.value}}</option></select></td>
                    <td class="control-label"><%--收件类型--%><eidea:label key="cityre.mis.datavip.bill.postType"/></td>
                    <td class="form-group"><select class="form-control" ng-model="postTypeCode" ng-init="postTypeCode='null'"><option value="null">请选择</option>
                        <option ng-repeat="postType in postTypeList" value="{{postType.typeCode}}">{{postType.typeName}}</option></select>
                    </td>
                    <td class="control-label"><eidea:label key="cityre.mis.datavip.bill.invoiceType"/></td>
                    <td><select class="form-control" ng-model="invoiceType"  ng-init="invoiceType='null'"><option value="null">请选择</option><option ng-repeat="option in invoiceFlagType" value="{{option.key}}">{{option.value}}</option></select>
                    <td class="control-label"><%--发票号--%><eidea:label key="cityre.mis.datavip.bills.invoiceNo"/>:</td>
                    <td class="form-group"><input type="text" class="form-control"
                                                  ng-model="invoiceNo"
                                                  placeholder="<eidea:message key="common.please.input"><eidea:param value="cityre.mis.datavip.bills.invoiceNo" type="label"/></eidea:message>">
                    </td>
                    <td class="control-label">
                        <button type="submit" class="btn"><eidea:label key="common.button.search"/></button>
                        <a href="<c:url value ="/mis/datavip/bills/exportExl"/>" target="_blank">导出</a>
                    </td>
                </tr>
            </table>

        </form>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-bordered table-hover table-striped table-condensed">
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
                        {{model.uid}}
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
                        {{model.startTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.endTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td>
                        {{model.typeName}}
                    </td>
                    <td>
                        {{model.productCost}}
                    </td>
                    <td ng-if="model.payFlag==1">
                        已支付
                    </td>
                    <td ng-if="model.payFlag==0">
                        未支付
                    </td>
                    <td ng-if="model.payFlag!=1&&model.payFlag!=0">
                        支付失败
                    </td>
                    <td>
                        {{model.payUpdateTime|date:"yyyy-MM-dd HH:mm:ss"}}
                    </td>
                    <td ng-if="model.postInvoiceFlag==1">
                        是
                    </td>
                    <td ng-if="model.postInvoiceFlag==0">
                        否
                    </td>
                    <td ng-if="model.invoiceType==1">
                        个人
                    </td>
                    <td ng-if="model.invoiceType==2">
                        单位
                    </td>
                    <td ng-if="model.invoiceType==null">
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
                        {{model.postTypeName}}
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
                        {{model.userTypeName}}
                    </td>

                    <td ng-if="model.serviceState==2">
                        <a ng-if="model.endTime!=nul" class="btn btn-primary btn-xs"
                           href="#/edit?billCode={{model.billCode}}">关闭服务<%--关闭服务--%></a>
                    </td>
                    <td ng-if="model.serviceState==0">
                        未开通
                        <a ng-if="model.payFlag==1" class="btn btn-primary btn-xs"
                           href="#/edit?billCode={{model.billCode}}">开通服务</a>
                    </td>
                    <td ng-if="model.serviceState==1">
                        已关闭
                    </td>

                    <td>
                        <a class="btn btn-primary btn-xs" href="#/invoiceEdit?id={{model.id}}" ng-show="!model.confirmPassword"><eidea:label
                        key="base.mis.datavip.invoice.edit"/><%--开通发票--%></a>
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="modal"
                                data-target="#confirmModal" ng-show="model.confirmPassword" ng-click="getBillsId(model.id)"><eidea:label
                                key="base.mis.datavip.invoice.edit"/></button>
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