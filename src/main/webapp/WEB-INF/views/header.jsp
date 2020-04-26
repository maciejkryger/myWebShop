<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 11:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p class="w3-right">
    <c:if test="${sessionScope.user.enabled}">
        <a class="w3-bar-item w3-margin-right">Witaj <b>${sessionScope.user.firstName}</b>!!!</a>
    </c:if>
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"
       onclick="document.getElementById('login').style.display='block'">
       <i class="fa fa-user"></i>
    </a>
    <!--<i class="fa fa-search"></i>-->
    <!--<a href="${pageContext.request.contextPath}/basket" class="w3-bar-item w3-button w3-padding"><i class="fa fa-shopping-cart"></i></a>-->
</p>
