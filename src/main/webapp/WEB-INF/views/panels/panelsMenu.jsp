<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 12:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
    <div class="w3-container w3-display-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
        <a href="${pageContext.request.contextPath}/"><h3 class="w3-wide"><b>QUNSZTOWNA</b></h3></a>
    </div>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <a href="${pageContext.request.contextPath}/panels/data/products" class="w3-bar-item w3-button">Produkty</a>
        <a href="${pageContext.request.contextPath}/panels/data/types" class="w3-bar-item w3-button">Asortyment</a>
        <a href="${pageContext.request.contextPath}/panels/data/materials" class="w3-bar-item w3-button">Materiały</a>
        <a href="${pageContext.request.contextPath}/panels/data/materialColors" class="w3-bar-item w3-button">Kolory
            materiałów</a>
        <a href="${pageContext.request.contextPath}/panels/data/fasteningTypes"
           class="w3-bar-item w3-button">Zapięcia</a>
  <!--  <a href="${pageContext.request.contextPath}/panels/data/fasteningColors" class="w3-bar-item w3-button">Kolory
            zapięć</a> -->
        <a href="${pageContext.request.contextPath}/panels/data/makingTechniques" class="w3-bar-item w3-button">Techniki
            wykonania</a>
        <!--<a href="${pageContext.request.contextPath}/panels/data/colorPerMaterials" class="w3-bar-item w3-button">Tabela
                        dostępności kolorów materiału</a>-->
        ------------------------------------
        <a href="${pageContext.request.contextPath}/panels/data/companies" class="w3-bar-item w3-button">Dane firmy</a>
        <a href="${pageContext.request.contextPath}/panels/data/rules" class="w3-bar-item w3-button">Regulaminy</a>
        <c:if test="${sessionScope.user.role.id<='1'}">
         <a href="${pageContext.request.contextPath}/panels/data/users" class="w3-bar-item w3-button">Użytkownicy</a>
         <a href="${pageContext.request.contextPath}/panels/data/roles" class="w3-bar-item w3-button">Grupy uprawnień</a>
         <a href="${pageContext.request.contextPath}/panels/data/config" class="w3-bar-item w3-button">Konfiguracja</a>
        </c:if>
    </div>
</nav>
