<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='panels/head.html' %>
<style>
    .w3-sidebar a {
        font-family: "Roboto", sans-serif
    }

    body, h1, h2, h3, h4, h5, h6, .w3-wide {
        font-family: "Montserrat", sans-serif;
    }
</style>
<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
    <div class="w3-container w3-display-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
        <a href="${pageContext.request.contextPath}/"><h3 class="w3-wide"><b>QUNSZTOWNA</b></h3></a>
    </div>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <c:forEach var="productType" items="${productTypesList}">
            <a href="${pageContext.request.contextPath}/types/${productType.name}"
               class="w3-bar-item w3-button">${productType.namePl}</a>
        </c:forEach>
    </div>
    <a href="#footer" class="w3-bar-item w3-button w3-padding">Kontakt</a>
<%--    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"--%>
<%--       onclick="document.getElementById('newsletter').style.display='block'">Newsletter</a>--%>
    <%--    <a href="#footer"  class="w3-bar-item w3-button w3-padding">Subskrybuj</a>--%>
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
        <p class="w3-left">${productType.namePl}</p>
        <%@include file='panels/header.jsp' %>
    </header>

    <!-- Image header -->
    <div class="w3-content" style="max-width:1200px">
        <div class="w3-display-container w3-container">
            <img src="${pageContext.request.contextPath}/images/${productType.id}/${product.id}.jpg" alt="${product.name}" style="width:100%">
            <p>${product.name} - cena: <b>${product.price} PLN</b></p>
        </div>
<%--        <img class="mySlides" src="${pageContext.request.contextPath}/images/${product.id}.1.jpg"--%>
<%--             style="width:100%">--%>
<%--        <img class="mySlides" src="${pageContext.request.contextPath}/images/${product.id}.2.jpg"--%>
<%--             style="width:100%;display:none">--%>
<%--        <img class="mySlides" src="${pageContext.request.contextPath}/images/${product.id}.3.jpg"--%>
<%--             style="width:100%;display:none">--%>

<%--        <div class="w3-row-padding w3-section">--%>
<%--            <div class="w3-col s4">--%>
<%--                <img class="demo w3-opacity w3-hover-opacity-off"--%>
<%--                     src="${pageContext.request.contextPath}/images/${product.id}.1.jpg"--%>
<%--                     style="width:100%;cursor:pointer" onclick="currentDiv(1)">--%>
<%--            </div>--%>
<%--            <div class="w3-col s4">--%>
<%--                <img class="demo w3-opacity w3-hover-opacity-off"--%>
<%--                     src="${pageContext.request.contextPath}/images/${product.id}.2.jpg"--%>
<%--                     style="width:100%;cursor:pointer" onclick="currentDiv(2)">--%>
<%--            </div>--%>
<%--            <div class="w3-col s4">--%>
<%--                <img class="demo w3-opacity w3-hover-opacity-off"--%>
<%--                     src="${pageContext.request.contextPath}/images/${product.id}.3.jpg"--%>
<%--                     style="width:100%;cursor:pointer" onclick="currentDiv(3)">--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>

    <!-- Product grid -->
    <div class="w3-row w3-grayscale">
        <div class="w3-col">
            <div class="w3-container">
                <p>
                    <b>Specyfikacja produktu:</b>
                </p>
                <p>
                    technika wykonania: <b>${product.makingTechnique.namePl}</b><br>
                    użyty materiał: <b>${product.material.namePl}</b><br>
                    kolor materiału: <b>
                    <select name="colorId" class="" onchange="this.form.submit()">
                        <option selected="selected"
                                value="${product.materialColor.id}">
                            ${product.materialColor.namePl}
                        </option>
                        <c:forEach var="color" items="${colors}">
                            <option value="${color.materialColor.namePl}">${color.materialColor.namePl} - dostępność
                                w ${color.availability}</option>
                        </c:forEach>
                    </select></b><br>
                    zapięcie: <b>${product.fasteningType.namePl}</b><br>
                    kolor zapięcia: <b>${product.fasteningColor.namePl}</b><br>
                    długość: <b>${product.length} mm</b><br>
                    szerokość: <b>${product.width} mm</b><br>
                    <b>Opis:</b><br> ${product.description}
                </p>
            </div>
        </div>

        <%--        <c:forEach var="color" items="${colors}">--%>
        <%--            <div class="w3-col l3 s6">--%>
        <%--                <div class="w3-container">--%>
        <%--                    <img src="/images/${product.id}_${color.materialColor.id}.webp" style="width:100%">--%>
        <%--                    <p>${color.materialColor.namePl}</p>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </c:forEach>--%>
        <div class="w3-container">
            <i class="fa fa-arrow-left"></i>
            <a href="${pageContext.request.contextPath}/types/${product.type.name}">cofnij</a>
        </div>
    </div>


    <!-- Subscribe section -->
    <%--    <div class="w3-container w3-black w3-padding-32">--%>
    <%--        <h1>Subskrybuj</h1>--%>
    <%--        <p>By otrzymywać specjalne oferty oraz zyskać status klienta VIP:</p>--%>
    <%--        <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail" style="width:100%"></p>--%>
    <%--        <button type="button" class="w3-button w3-red w3-margin-bottom">subskrybuj</button>--%>
    <%--    </div>--%>

    <!-- Footer -->
    <%@include file='footer.jsp' %>

    <!-- Signature -->
    <%@include file='panels/signature.html' %>

    <!-- End page content -->
</div>

<!-- Login Modal -->
<%@include file='panels/loginModal.jsp' %>


<!-- Newsletter Modal -->
<div id="newsletter" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
        <div class="w3-container w3-white w3-center">
            <i onclick="document.getElementById('newsletter').style.display='none'"
               class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>
            <h2 class="w3-wide">NEWSLETTER</h2>
            <p>Dołącz do mojej listy mailingowej by otrzymywać aktualności o nowościach i ofertach specjalnych.</p>
            <p><input class="w3-input w3-border" type="text" placeholder="Wpisz e-mail"></p>
            <button type="button" class="w3-button w3-padding-large w3-red w3-margin-bottom"
                    onclick="document.getElementById('newsletter').style.display='none'">Subscribe
            </button>
        </div>
    </div>
</div>

<script>
    function currentDiv(n) {
        showDivs(slideIndex = n);
    }

    function showDivs(n) {
        var i;
        var x = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("demo");
        if (n > x.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = x.length
        }
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
        }
        x[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " w3-opacity-off";
    }
</script>


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

