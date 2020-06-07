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
    .myInput{
        border: 2px solid green;
        border-radius: 8px;
        color: black;
        text-align: center;
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

        .shipmentInput{
            border: 2px solid grey;
            border-radius: 8px;
            color: black;
            text-align: center;
            width: 100px;
            }

        .dateInput{
            border: 2px solid grey;
            border-radius: 8px;
            color: black;
            text-align: center;
            width: 150px;
            }

           .addressInput{
              border: 2px solid green;
              border-radius: 8px;
              color: black;
              text-align: center;
              width: 300px
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
<div class="w3-main" style="margin-left:250px; width: 100%">

    <!-- Push down content on small screens -->
    <div class="w3-hide-large" style="margin-top:83px"></div>

    <!-- Top header -->
    <header class="w3-container w3-xlarge">
        <p class="w3-left">centrum zamówień</p>
        <%@include file='../header.jsp' %>
    </header>


<!-- Delivery grid -->
 <h2>Zamówienia gotowe do skompletowania</h2>
        <div class="w3-responsive w3-padding">
            <a href="${pageContext.request.contextPath}/orderCenter/" class="w3-button w3-white w3-border w3-round-large w3-right">cofnij</a>
        </div>

<div class="w3-responsive" >
<table class="w3-table-all w3-hoverable" >
                    <thead>
                    <tr class="w3-light-grey ">
                        <th>data zamówienia</th>
                        <th>numer zamówienia</th>
                        <th>metoda płatności</th>
                        <th>czy opłacone</th>
                        <th>opcja dostawy</th>
                        <th>klient</th>
                        <th>uwagi</th>
                        <th>szczegóły</th>
                        <th>potwierdź wysłanie/skompletowanie</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${ordersReady}">

                        <tr>
                            <td>${order.confirmDate}</td>
                            <td>${order.orderNumber}</td>
                            <td>${order.paymentMethod.namePl}</td>
                            <td>${order.paid==true?'TAK':'NIE'}</td>
                            <td>${order.deliveryOption.namePl}</td>
                            <td>${order.user.firstName} ${order.user.lastName}</td>
                            <td>${order.comment!=null && order.comment!="" ? 'TAK' : 'NIE'}</td>
                            <td>
                            <a href="${pageContext.request.contextPath}/orderCenter/orderItems/${order.id}">
                                <button class="w3-button w3-white w3-border w3-round-large">szczegóły</button>
                            </a>
                            </td>
                            <td>

                            <form method="post" action="${pageContext.request.contextPath}/orderCenter/paid/">
                              <input type="hidden" name="id" value="${order.id}">
                              <input type="hidden" name="completed" value="true">
                              <input type="hidden" name="isItToSend" value="${order.deliveryOption.id<4 ? 'true' : 'false'}">
                              <c:if test="${order.deliveryOption.id<4}">
                                <input type="text" name="shipmentNumber" class="shipmentInput" placeholder="nr LP">
                                <input type="date" name="shipmentDate" class="dateInput">
                              </c:if>

                              <input type="submit" value="${order.deliveryOption.id<4 ?'potwierdź wysłanie' :'potwierdź gotowe do OWL'}" class="w3-button w3-white w3-border w3-round-large">
                            </form>
                            <c:if test="${order.deliveryOption.id<4}">
                                <c:if test="${address==null}">
                                <a href="${pageContext.request.contextPath}/orderCenter/paid/${order.id}" class="w3-button w3-white w3-border w3-round-large">
                                pokaż adres wysyłki</a> </c:if>
                                <c:if test="${address!=null}">
                                <a href="${pageContext.request.contextPath}/orderCenter/paid/" class="w3-button w3-white w3-border w3-round-large">
                                ukryj adres wysyłki</a></c:if>
                                <c:if test="${order.user.id==address.user.id}">
                                    <p class="addressInput">
                                    ${address.street} ${address.houseNo} <c:if test="address.flatNo!=null"> /${address.flatNo}</c:if>,
                                    ${address.postCode} ${address.city}</p>
                                </c:if>
                            </c:if>
                            </td>
                        </tr>


                    </c:forEach>
                    </tbody>
                </table>

</div>

<div class="w3-container w3-responsive" style="padding: 10px">
    <a href="${pageContext.request.contextPath}/orderCenter/" class="w3-button w3-white w3-border w3-round-large w3-left">cofnij</a>
</div>


    <!-- Footer -->
    <%@include file='../footer.jsp' %>

    <!-- Signature -->
    <%@include file='../signature.html' %>

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

