<%--
  Created by IntelliJ IDEA.
  User: snowhome
  Date: 01.03.2020
  Time: 11:15
  To change this template use File | Settings | File Templates.
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
                </c:if>
                <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button">wyloguj</a>
            </c:if>
            <c:if test="${!sessionScope.user.enabled}">
                <h2 class="w3-wide">Logowanie</h2>
                <p>zaloguj się</p>
                <form method="post" action="<c:url value='${pageContext.request.contextPath}/login' />">
                    <p><input class="w3-input w3-border" type="text" name="username" placeholder="Wpisz login"></p>
                    <p><input class="w3-input w3-border" type="password" name="password" placeholder="Wpisz hasło"></p>
                    <button type="submit" class="w3-button w3-padding-large w3-red w3-margin-bottom"
                            onclick="document.getElementById('login').style.display='none'">Zaloguj
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</div>
