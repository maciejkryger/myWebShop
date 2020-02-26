<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
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
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
    <div class="w3-container w3-display-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
        <a href="${pageContext.request.contextPath}/"><h3 class="w3-wide"><b>QUNSZTOWNA</b></h3></a>
    </div>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <a href="${pageContext.request.contextPath}/panels/data/products" class="w3-bar-item w3-button">Produkty</a>
        <a href="${pageContext.request.contextPath}/panels/data/types" class="w3-bar-item w3-button">Asortyment</a>
        <a href="${pageContext.request.contextPath}/panels/data/materials" class="w3-bar-item w3-button">Materiały</a>
        <a href="${pageContext.request.contextPath}/panels/data/materialColors" class="w3-bar-item w3-button">Kolory materiałów</a>
        <a href="${pageContext.request.contextPath}/panels/data/fasteningTypes" class="w3-bar-item w3-button">Zapięcia</a>
        <a href="${pageContext.request.contextPath}/panels/data/fasteningColors" class="w3-bar-item w3-button">Kolory zapięć</a>
        <a href="${pageContext.request.contextPath}/panels/data/makingTechniques" class="w3-bar-item w3-button">Techniki wykonania</a>
        ----------------------
        <a href="${pageContext.request.contextPath}/panels/data/users" class="w3-bar-item w3-button">Użytkownicy</a>
        <a href="${pageContext.request.contextPath}/panels/data/company" class="w3-bar-item w3-button">Dane firmy</a>
        <a href="${pageContext.request.contextPath}/panels/data/rules" class="w3-bar-item w3-button">Regulaminy</a>
    </div>

</nav>

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
        <p class="w3-left">superPanel</p>
        <p class="w3-right">
            <i class="fa fa-user w3-margin-right"></i>
            <i class="fa fa-search"></i>
            <i class="fa fa-shopping-cart"></i>
        </p>
    </header>

    <!-- Main grid -->
    <div class="w3-container">

        <h2>Edycja technik wykonania</h2>
        <div class="w3-responsive">
            <a href="${pageContext.request.contextPath}/panels/data/makingTechniques/">
                <button class="w3-button w3-white w3-border w3-round-large">anuluj</button>
            </a>
            <table class="w3-table-all w3-hoverable">
                <form method="post" action="${pageContext.request.contextPath}/panels/data/makingTechniques/save/${makingTechnique.id}"
                      modelAttribute="product">
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>


                    <p>
                        <label><b>id: ${makingTechnique.id}</b></label>
                        <input type="hidden" name="id" placeholder="id" class="w3-input w3-border"
                               value="${makingTechnique.id}">
                    </p>
                    <p>
                        <label><b>Nazwa ENG</b></label>
                        <input type="text" name="name" placeholder="nazwa ENG" class="w3-input w3-border"
                               value="${makingTechnique.name}">
                    </p>
                    <p>
                        <label><b>sNazwa PL</b></label>
                        <input type="text" name="namePl" placeholder="nazwa PL" class="w3-input w3-border"
                               value="${makingTechnique.namePl}">
                    </p>
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>
                </form>
                <a href="${pageContext.request.contextPath}/panels/data/makingTechniques/">
                    <button class="w3-button w3-white w3-border w3-round-large">anuluj</button>
                </a>

            </table>
        </div>

        <div class="w3-black w3-center w3-padding-24">Strona stworzona przez JAVArun na podstawie szablonu <a
                href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank"
                class="w3-hover-opacity">w3.css</a>
        </div>

        <!-- End page content -->
    </div>


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

