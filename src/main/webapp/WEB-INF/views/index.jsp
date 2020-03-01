<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>qunsztowna.pl</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style>
    .mySlides {
        display: none
    }

    .w3-left, .w3-right, .w3-badge {
        cursor: pointer
    }

    .w3-badge {
        height: 13px;
        width: 13px;
        padding: 0
    }

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
        <p class="w3-left">biżuteria ręcznie robiona</p>
        <%@include file='header.jsp' %>
    </header>

    <!-- Image header -->

    <%--    <div class="w3-display-container w3-container">--%>
    <%--        <img src="${pageContext.request.contextPath}/images/1.webp" alt="mainPage" style="width:100%">--%>
    <%--        <div class="w3-display-topleft w3-text-black" style="padding:24px 48px">--%>
    <%--            <h1 class="w3-jumbo w3-hide-small">Witam na mojej stronie :)</h1>--%>
    <%--            <h1 class="w3-hide-large w3-hide-medium">Witam na mojej stronie :)</h1>--%>
    <%--            <h1 class="w3-hide-small">Sylwia</h1>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <div class="w3-content w3-display-container w3-responsive" style="max-width:100%">
        <img class="mySlides" src="${pageContext.request.contextPath}/images/start1.jpg" style="width:100%">
        <img class="mySlides" src="${pageContext.request.contextPath}/images/start2.jpg" style="width:100%">
        <img class="mySlides" src="${pageContext.request.contextPath}/images/start3.jpg" style="width:100%">
        <div class="w3-center w3-container w3-section w3-large w3-text-black w3-display-bottommiddle"
             style="width:100%">
            <div class="w3-left w3-hover-text-khaki" onclick="plusDivs(-1)">&#10094;</div>
            <div class="w3-right w3-hover-text-khaki" onclick="plusDivs(1)">&#10095;</div>
            <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(1)"></span>
            <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(2)"></span>
            <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(3)"></span>
        </div>
    </div>


    <br>
    <%--    <div class="w3-container w3-text-grey" id="jeans">--%>
    <%--        <p>8 items</p>--%>
    <%--    </div>--%>

    <%--    <!-- Product grid -->--%>
    <%--wykasowane--%>

    <!-- Footer -->
    <%@include file='footer.jsp' %>

    <!-- Signature -->
    <%@include file='signature.html' %>

    <!-- End page content -->
</div>

<!-- Login Modal -->
<%@include file='loginModal.jsp' %>


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
<script>
    var slideIndex = 1;
    showDivs(slideIndex);

    function plusDivs(n) {
        showDivs(slideIndex += n);
    }

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
            dots[i].className = dots[i].className.replace(" w3-white", "");
        }
        x[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " w3-white";
    }
</script>
</body>
</html>

