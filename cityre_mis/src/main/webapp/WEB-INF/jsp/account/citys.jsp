<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: 刘大磊
  Date: 2017/9/12
  Time: 15:26
  用户城市权限维护
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userPanel" class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;">
    <c:out value="${userCity.unionUid}"/><br>
    <c:out value="${userCity.name}"/>
    <br>
    <input type="button" class="easyui-linkbutton" onclick="accountHelper.saveUserCity()" value="保存">
    <br>
    城市列表：<br>
    <table style="width: 100%">
    <c:forEach items="${userCity.provinceBoList}" var="userCity">
    <tr>
        <td><c:out value="${userCity.name}"/>
            <input name="province" onclick="citycheck(this.value,'${userCity.code}')">
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