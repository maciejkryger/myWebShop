<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='../head.html' %>
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
    .amountInput{
        border: 2px solid grey;
        border-radius: 8px;
        color: black;
        text-align: center;
        width: 65px;
        }

    .dateInput{
        border: 2px solid grey;
        border-radius: 8px;
        color: black;
        text-align: center;
        width: 150px;
        }

    .myButton{
        height: 100px;
        min-width: 400px;
        position: relative;
        overflow: auto;
        text-align:center;
        padding: 34px 50px;
        border: 3px solid green;
    }

        .myData-container{
            padding-bottom: 50px;
            width: 50%;
            margin-bottom: 20px;
            margin-left:20px;
            min-width:45%;
            min-height: 344px;
            border-radius: 10px;
        }

        @media only screen and (max-width: 600px) {
              /* For mobile phones: */
            .myData-container{
                padding-bottom: 50px;
                width: 100%
                margin-bottom: 20px;
                margin-left:20px;
                margin-right:20px;
                min-height: 344px;
                min-width:92%;
                border-radius: 10px;
            }

        }

</style>

<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<%@include file='../menu.jsp' %>


<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
    <div class="w3-bar-item w3-padding-24 w3-wide"><a href="${pageContext.request.contextPath}/">QUNSZTOWNA</a></div>
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
        <p class="w3-left">moje zamówienia</p>
        <%@include file='../header.jsp' %>
    </header>


<!-- Delivery grid -->
 <h2>historia zamówień</h2>
<div class="w3-responsive w3-padding">
    <a href="${pageContext.request.contextPath}/account/" class="w3-button w3-white w3-border w3-round-large w3-right">cofnij</a>
</div>


<div class="w3-responsive w3-row">

    <c:if test="${historyOrders.isEmpty()}">
        <p class="w3-row w3-margin">nie masz historii zamówień :(</p>
        <p class="w3-row w3-margin">koniecznie coś sobie kup ładnego jeszcze dzisiaj ! :)</p>
    </c:if>

    <c:forEach var="order" items="${historyOrders}">
        <div class="w3-col l5 s6 w3-display-container w3-padding w3-border myData-container">
              <p class="w3-row">numer zamówienia: <b>${order.orderNumber}</b></p>
              <p class="w3-row">data zamówienia: <b>${order.confirmDate}</b></p>
              <p class="w3-row">metoda płatności: <b>${order.paymentMethod.namePl}</b></p>
              <p class="w3-row">opcja dostawy: <b>${order.deliveryOption.namePl}</b></p>
              <p class="w3-row">status zamówienia: <b>${order.status.namePl}</b></p>
           <c:if test="${order.shipmentNumber!=null}">
              <p class="w3-row">data wysłania: <b>${order.shipmentDate}</b></p>
              <p class="w3-row">list przewozowy: <b>${order.shipmentNumber}</b></p>
              <p class="w3-row">data doręczenia: <b>${order.deliveryDate}</b></p>
           </c:if>
           <c:if test="${order.shipmentNumber==null}">
              <p class="w3-row">data odbioru: <b>${order.deliveryDate}</b></p>
           </c:if>
              <p class="w3-row">
                <a href="${pageContext.request.contextPath}/account/orderItems/${order.id}">
                    <button class="w3-button w3-white w3-border w3-round-large w3-display-topright">szczegóły</button>
                </a>
              </p>
        </div>

    </c:forEach>

</div>



<div class="w3-container w3-responsive" style="padding: 10px">
    <a href="${pageContext.request.contextPath}/account/" class="w3-button w3-white w3-border w3-round-large w3-left">cofnij</a>
</div>

    <!-- Footer -->
    <%@include file='../footer.jsp' %>

    <!-- Signature -->
    <%@include file='../signature.html' %>

    <!-- End page content -->
</div>

<!-- Login Modal -->
<%@include file='../loginModal.jsp' %>


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

