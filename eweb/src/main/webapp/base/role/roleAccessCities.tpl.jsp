<%--
  Created by IntelliJ IDEA.
  User:刘大磊
  Date: 2017/6/29
  Time: 11:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/taglib.jsp" %>
<script type="text/javascript">
    var userAccessCitiesHelper={selectAll:function (value) {
       if($("#allChecked").attr("checked"))
       {
           $("input:checkbox").attr("checked",false);
       }
       else
       {
           $("input:checkbox").attr("checked",true);
       }

    }};

</script>
<div class="container-fluid" ng-controller="accessCitiesCtrl">
    <form role="form" name="editForm" novalidate  class="form-horizontal form-label-left">
        <div class="page-header button-css">
            <button type="button" class="btn btn-primary btn-sm btn-hg" ng-show="canSave" ng-click="save()"
                    title="<eidea:label key="common.button.save"/>"><%--保存--%>
                <i class="fa fa-save fa-1x" aria-hidden="true"></i>&nbsp;<eidea:label key="common.button.save"/>
            </button>
            <a href="#/list" class="btn btn-primary btn-sm btn-hg"
               title="<eidea:label key="common.button.back"/>"><%--返回--%>
                <i class="fa fa-mail-reply fa-1x" aria-hidden="true"></i>&nbsp;<eidea:label
                        key="common.button.back"/></a>
            <button type="button" class="btn  btn-primary btn-sm btn-hg"
                    title="<eidea:label key="common.button.refresh"/>" ng-click="commonEditHeader('refresh')">
                <i class="fa fa-refresh fa-1x" aria-hidden="true"></i>&nbsp;<eidea:label
                    key="common.button.refresh"/>
            </button>
            <button type="button" class="btn  btn-primary btn-sm btn-hg"
                    title="<eidea:label key="common.button.operation.log"/>" ng-click="commonEditHeader('log')">
                <i class="fa fa-file-text-o fa-1x" aria-hidden="true"></i>&nbsp;<eidea:label
                    key="common.button.operation.log"/>
            </button>
        </div>
        <div class="row-fluid">
            <div class="row">

                <label class="col-sm-2 text-right">{{userBo.username}}</label>
                <div class="col-sm-10">
                    {{userBo.name}}
                </div>

            </div>
                <div class="row">
                    <label class="col-sm-2 text-right">{{userBo.clientName}}</label>
                    <div class="col-sm-10">
                        {{userBo.orgName}}
                    </div>
                </div>


                <p class="form-group">
                    <p class="text-center" style="color: red"  >
                        {{message}}
                    </p>
                    <p class="text-center" style="color: red" ng-repeat="msg in errorMessages track by $index" ng-show="errorCode==3">
                        {{msg.message}}
                    </p>
                </p>
                <div id="cityPrivilegeList" class="container-fluid">
                    <table  class="table table-striped table-condensed">
                        <tr>
                            <td class="col-sm-1 "></td>
                            <td class="col-sm-11 ">
                                全选<input type="checkbox"  ng-click="selectAll()" onclick="userAccessCitiesHelper.selectAll()" id="allChecked"  />
                            </td>
                        </tr>
                        <tr ng-repeat="letter in userBo.letterBoList track by $index" ng-class-even="success">
                            <td class="col-sm-1">{{letter.firstLetter|uppercase}}</td>
                            <td class="col-sm-11">
                                <table class="table-bordered">

                                    <tr ng-repeat="province in letter.provinceAccessBoList track by $index">
                                        <td class="col-sm-2 " >{{province.name}}<br>
                                          全选<input type="checkbox" ng-click="selectAllByProvince(province.provinceId)" /> </td>
                                        <td class="col-sm-9">
                                         <div ng-repeat="city in province.cityCanAccessedBoList track by $index" class="col-sm-2 form-group" >
                                                 {{city.cityName}}   <input type="checkbox" ng-model="city.selected">&nbsp;&nbsp;&nbsp;
                                          </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                </div>
            </div>
        </div>
    </form>
    <jsp:include page="/common/common_upload.jsp"/>

</div>
