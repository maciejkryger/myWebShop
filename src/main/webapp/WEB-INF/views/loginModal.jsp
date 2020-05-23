<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 11:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="login" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
        <div class="w3-container w3-white w3-center">
            <i onclick="document.getElementById('login').style.display='none'"
               class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>
            <c:if test="${sessionScope.user.enabled}">
                <p class="w3-bar-item w3-padding">Jesteś zalogowany jako: ${sessionScope.user.username}</p>
                <c:if test="${sessionScope.user.role.id<='2'}">
                    <a href="${pageContext.request.contextPath}/panels/superpanel" class="w3-bar-item w3-button">superPanel</a>
                    <a href="${pageContext.request.contextPath}/orderCenter" class="w3-bar-item w3-button">zamówienia</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/changeUserDetails" class="w3-bar-item w3-button">edycja danych</a>
                <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button">wyloguj</a>
                 <a href="${pageContext.request.contextPath}/changePassword" class="w3-bar-item w3-button">zmień hasło</a>
            </c:if>
            <c:if test="${!sessionScope.user.enabled}">
                <h2 class="w3-wide">Logowanie</h2>
                <p>zaloguj się</p>
                <form method="post" action="<c:url value='${pageContext.request.contextPath}/login' />">
                    <p><input class="w3-input w3-border" type="text" name="username" placeholder="Wpisz login"></p>
                    <p><input class="w3-input w3-border" type="password" name="password" placeholder="Wpisz hasło"></p>
                    <button type="submit" class="w3-button w3-padding-large w3-green w3-margin-bottom w3-round-large"
                            onclick="document.getElementById('login').style.display='none'">Zaloguj
                    </button>
                </form>
                  <a href="${pageContext.request.contextPath}/register"
                                         style=padding-bottom:250px class="w3-button w3-white w3-border w3-round-large w3-center w3-padding-large">Rejestracja</a>

            </c:if>
        </div>
    </div>
</div>
