<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='head.html' %>
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
            <a href="${pageContext.request.contextPath}/types/${productType.name}" class="w3-bar-item w3-button">${productType.namePl}</a>
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
        <p class="w3-left">${rule.namePl}</p>
        <%@include file='header.jsp' %>
    </header>

    <!-- Image header -->
<%--    <div class="w3-display-container w3-container">--%>
<%--        <img src="#" alt="test" style="width:100%">--%>
<%--        <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">--%>
<%--            <h1 class="w3-jumbo w3-hide-small">New arrivals</h1>--%>
<%--            <h1 class="w3-hide-large w3-hide-medium">New arrivals</h1>--%>
<%--            <h1 class="w3-hide-small">COLLECTION 2020</h1>--%>
<%--            &lt;%&ndash; <p><a href="#jeans" class="w3-button w3-black w3-padding-large w3-large">PRZEJDŹ DO ZAKUPÓW</a></p>&ndash;%&gt;--%>
<%--        </div>--%>
<%--    </div>--%>

    <%--    <div class="w3-container w3-text-grey" id="jeans">--%>
    <%--        <p>8 items</p>--%>
    <%--    </div>--%>

        <!-- Product grid -->
        <div class="w3-row w3-grayscale">
            <div class="w3-col l3 s6">
                <div class="w3-container">
                    <h3 class="w3-wide"><b>${rule.descriptionPl}</b></h3>
                </div>
            </div>
        </div>

<%--        <!-- Subscribe section -->--%>
<%--        <div class="w3-container w3-black w3-padding-32">--%>
<%--            <h1>Subskrybuj</h1>--%>
<%--            <p>By otrzymywać specjalne oferty oraz zyskać status klienta VIP:</p>--%>
<%--            <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail" style="width:100%"></p>--%>
<%--            <button type="button" class="w3-button w3-red w3-margin-bottom">subskrybuj</button>--%>
<%--        </div>--%>

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

</body>
</html>

