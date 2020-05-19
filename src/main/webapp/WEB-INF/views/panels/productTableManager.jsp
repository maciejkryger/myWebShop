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

        <h2>Tabela produktów</h2>

        <%@include file='panelsSearchBy.jsp' %>


        <div class="w3-responsive">
            <table class="w3-table-all w3-hoverable">
                <thead>
                <tr class="w3-light-grey ">
                    <th style="width:7%">Zdjęcie</th>
                    <th>id</th>
                    <th>nazwa</th>
                    <th>typ</th>
                    <th>technika</th>
                    <th>materiał</th>
                    <th>kolor</th>
                    <th>zapięcie</th>
                 <!--   <th>kolor zapięcia</th> -->
                    <th>długość</th>
                    <th>szerokość</th>
                    <th>cena</th>
                    <th>opis</th>
                    <th>czy aktywny</th>
                    <th>opcje</th>
                    <th>produkt główny<th>
                </tr>
                </thead>
                <c:forEach var="item" items="${products}">
                    <tr>
                        <td style="width:7%">
                           <img src="${pageContext.request.contextPath}/images/${item.type.id}/${item.id}.jpg" style="width:100%">
                        </td>
                        <td>${item.id}</td>
                        <td>${item.namePl}</td>
                        <td>${item.type.namePl}</td>
                        <td>${item.makingTechnique.namePl}</td>
                        <td>${item.material.namePl}</td>
                        <td>${item.materialColor.namePl}</td>
                        <td>${item.fasteningType.namePl}</td>
                    <!--    <td>${item.fasteningColor.namePl}</td> -->
                        <td>${item.length}</td>
                        <td>${item.width}</td>
                        <td>${item.price}</td>
                        <td>${item.descriptionPl}</td>
                        <td><i class="${item.active ? 'far fa-eye' : 'far fa-eye-slash'}"></i></td>
                        <td>
                            <form method="GET" action="${pageContext.request.contextPath}/panels/data/product/${item.id}">
                                <input type="hidden" name="searchWhat" value="${param.searchWhat}">
                                <input type="hidden" name="searchBy" value="${param.searchBy}">
                                <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="edytuj">
                            </form>
                            <form method="post" >
                                <input type="hidden" name="id" value="${item.id}">
                                <input type="hidden" name="active" value="${!item.active}">
                                <input type="submit" value="${item.active ? 'wyłącz' : 'włącz'}" class="w3-button w3-white w3-border w3-round-large">
                            </form>
                            <a href="${pageContext.request.contextPath}/details/${item.id}">
                                <button class="w3-button w3-white w3-border w3-round-large">podgląd</button>
                            </a>
                        </td>
                         <td>${item.mainProduct.id==null ? 'TAK' : item.mainProduct.id}</td>
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

