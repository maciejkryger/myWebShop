<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 11:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .icon-size{
     font-size: 24px;
    }

    @media only screen and (max-width: 600px) {
              /* For mobile phones: */

       .icon-size{
        font-size: 18px;
       }

    }
</style>
<p class="w3-right">

    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-display-container icon-size"
       onclick="document.getElementById('login').style.display='block'">
       <i class="${sessionScope.user.enabled ? 'fas fa-user-lock' : 'fa fa-user'}"></i>
    </a>

    <a href="${pageContext.request.contextPath}/search"  class="w3-bar-item w3-button icon-size"><i class="fa fa-search"></i></a>
    <a href="${pageContext.request.contextPath}/wishList" class="w3-bar-item w3-button icon-size"><i class="fa fa-heart"></i><span class="w3-bar-item w3-medium">${userWishListSize}</span></a>
    <a href="${pageContext.request.contextPath}/basket" class="w3-bar-item w3-button w3-display-container icon-size"><i class="fa fa-shopping-cart"></i><span class="w3-bar-item w3-medium ">${productsInBasketSize}</span></a>
</p>
