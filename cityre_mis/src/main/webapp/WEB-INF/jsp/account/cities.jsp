
<%--
  User: 刘大磊
  Date: 2017/9/12
  Time: 15:26
  用户城市权限维护
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/inc/taglib.jsp" %>
<div id="userPanel" class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;overflow-y:scroll;height: 95%">
    <c:out value="${userCity.unionUid}"/>&nbsp;&nbsp;<c:out value="${userCity.name}"/>
    <input type="hidden" name="unionUid" id="unionUid" value="${userCity.unionUid}"/>
    <br>
    <shiro:hasPermission name="account:edit">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="accountHelper.saveUserCity()" style="width:80px">保存</a>
    </shiro:hasPermission>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="accountHelper.goList()" style="width:80px">返回</a>
    <br>
    城市列表：<br>
    全选<input type="checkbox" onclick="accountHelper.selectAll(this.checked)" >
    <table style="width: 100%">
    <c:forEach items="${userCity.provinceBoList}" var="userCity">
    <tr>
        <td style="width: 100px;"><c:out value="${userCity.name}"/><input name="province" onclick="accountHelper.citycheck(this.checked,'${userCity.code}')" type="checkbox" >
        </td>
        <td id="${userCity.code}">
            <c:forEach items="${userCity.cityBoList}" var="city">
                <c:out value="${city.name}"/>
                <input type="checkbox" value="${city.code}" ${city.checked?"checked":""} name="city">&nbsp;&nbsp;
            </c:forEach>
        </td>
    </tr>
    </c:forEach>
    </table>
</div>