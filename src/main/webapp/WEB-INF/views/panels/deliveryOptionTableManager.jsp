<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='../head.html' %>
<style>
    .w3-sidebar a {
        font-family: "Roboto", sans-serif
    }

    body, h1, h2, h3, h4, h5, h6, .w3-wide {
        font-family: "Montserrat", sans-serif;
    }
    .default-btn{
        background-color: rgba(0%, 0%, 100%, 0); /* Blue background */
        border: none; /* Remove borders */
        padding: 12px 16px; /* Some padding */
        cursor: pointer; /* Mouse pointer on hover */
    }
</style>
<body class="w3-content" style="max-width:2200px">

<!-- Sidebar/menu -->
<%@include file='panelsMenu.jsp' %>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
    <div class="w3-bar-item w3-padding-24 w3-wide">QUNSZTOWNA</div>
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i
            class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"
     id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

    <!-- Push down content on small screens -->
    <div class="w3-hide-large" style="margin-top:83px"></div>

    <!-- Top header -->
    <header class="w3-container w3-xlarge">
        <%@include file='panelsHeader.jsp' %>
        <%@include file='../header.jsp' %>
    </header>

    <!-- Main grid -->
    <div class="w3-container">

        <h2>Tabela asortymentu</h2>

        <div class="w3-responsive">
            <table class="w3-table-all w3-hoverable">
                <thead>
                <tr class="w3-light-grey ">
                    <th>id</th>
                    <th>nazwa ENG</th>
                    <th>nazwa PL</th>
                    <th>typ płatności</th>
                    <th>cena</th>
                    <th>czy aktywny</th>
                    <th>czy domyślny<th>
                    <th>opcje</th>
                </tr>
                </thead>
                <c:forEach var="item" items="${deliveryOptions}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.namePl}</td>
                        <td>${item.paymentType.namePl}</td>
                        <td>${item.price}</td>
                        <td><i class="${item.active ? 'far fa-eye' : 'far fa-eye-slash'}"></i></td>
                        <td>
                        <form method="post" action="${pageContext.request.contextPath}/panels/data/deliveryOption/save">
                          <input type="hidden" name="id" value="${item.id}">
                          <input type="hidden" name="active" value="${item.active}">
                          <input type="hidden" name="checked" value="${!item.checked}">

                          <button type="submit" class="default-btn"><i class="${item.checked ? 'fas fa-toggle-on' : 'fas fa-toggle-off'}" style="font-size:24px;"></i></button>

                        </form>
                        </td>
                        <td></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/panels/data/deliveryOption/${item.id}">
                                <button class="w3-button w3-white w3-border w3-round-large">edytuj</button>
                            </a>
                            <form method="post" action="${pageContext.request.contextPath}/panels/data/deliveryOption/save">
                                <input type="hidden" name="id" value="${item.id}">
                                <input type="hidden" name="active" value="${!item.active}">
                                <input type="hidden" name="checked" value="${item.checked}">
                                <input type="submit" value="${item.active ? 'wyłącz' : 'włącz'}" class="w3-button w3-white w3-border w3-round-large">
                            </form>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Signature -->
        <%@include file='../signature.html' %>

    </div>
    <!-- End page content -->
</div>

<!-- Login Modal -->
<%@include file='../loginModal.jsp' %>

<script>
    // Accordion
    function myAccFunc() {
        var x = document.getElementById("demoAcc");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }

    // Click on the "Jeans" link on page load to open the accordion for demo purposes
    document.getElementById("myBtn").click();


    // Open and close sidebar
    function w3_open() {
        document.getElementById("mySidebar").style.display = "block";
        document.getElementById("myOverlay").style.display = "block";
    }

    function w3_close() {
        document.getElementById("mySidebar").style.display = "none";
        document.getElementById("myOverlay").style.display = "none";
    }
</script>

</body>
</html>

