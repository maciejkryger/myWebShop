<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 11:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p class="w3-right">

    <a href="javascript:void(0)" class="w3-bar-item w3-button"
       onclick="document.getElementById('login').style.display='block'">
       <i class="fa fa-user"></i>
    </a>
    <a href="${pageContext.request.contextPath}/search" class="w3-bar-item w3-button"><i class="fa fa-search"></i></a>
    <a href="${pageContext.request.contextPath}/wishList" class="w3-bar-item w3-button"><i class="fa fa-heart"></i></a>
    <a href="${pageContext.request.contextPath}/basket" class="w3-bar-item w3-button"><i class="fa fa-shopping-cart"></i></a>
</p>
