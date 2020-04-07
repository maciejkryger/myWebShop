<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 29.03.2020
  Time: 22:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="w3-bar w3-light-grey w3-border">
 <a href="${pageContext.request.contextPath}/${tableURL}/new">
    <button class="w3-button w3-white w3-bar-item w3-border w3-round-large" >dodaj</button>
 </a>
    <c:if test="${isFiltered}">
         <a href="${pageContext.request.contextPath}/${tableURL}s/" class="w3-bar-item w3-button w3-theme-l2 w3-right w3-margin-left">usuń filtr</a>
    </c:if>

 <form>
   <input type="submit" class="w3-bar-item w3-button w3-theme w3-right w3-margin-left" value="szukaj">
   <input type="text" class="w3-bar-item w3-input w3-border w3-right w3-margin-left" placeholder="filtruj widok..." name="searchWhat" value="${param.searchWhat}">
     <select class="w3-bar-item w3-select w3-border w3-right" name="searchBy">
           <c:forEach var="option" items="${searchByOptions}">
              <option value="${option.name()}" ${option.name()==param.searchBy ? ' selected' : ''}>${option.description}</option>
           </c:forEach>
     </select>
 </form>
</div>