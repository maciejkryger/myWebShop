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
        width: 5000px;
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
 <h2>moje zamówienia</h2>

        <div class="w3-responsive w3-padding">
                    <c:if test="${sessionScope.user.username!=null}">
                        <label><b>Jesteś zalogowany jako: </b>${sessionScope.user.username}</label>
                    </c:if>
                    <button class="w3-button w3-white w3-border w3-round-large w3-right" onclick="goBack()">cofnij</button>
        </div>


<div class="w3-responsive">
        Szczegóły zamówienia nr: ${webOrder.orderNumber}
                <table class="w3-table-all w3-hoverable">
                    <thead>
                    <tr class="w3-light-grey ">
                        <th>zdjęcie</th>
                        <th>nazwa produktu</th>
                        <th>numer zamówienia</th>
                        <th>cena</th>
                        <th>ilość</th>
                        <th>suma</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${orderItems}">
                        <tr>
                            <td style="width:7%">
                                <img src="${pageContext.request.contextPath}/images/${item.product.type.id}/${item.product.id}.jpg" style="width:100%">
                             </td>
                            <td>${item.product.namePl}</td>
                            <td>${item.webOrder.orderNumber}</td>
                            <td>${item.productPrice} PLN</td>
                            <td>${item.quantity}</td>
                            <td>${item.quantity*item.productPrice} PLN</td>
                        </tr>
                   </tbody>
                     </c:forEach>

                     <tfoot>
                       <tr class="w3-light-grey ">
                         <th colspan="4" style="text-align:right;">podsumowanie:</th>
                         <th>${sumQuantity} szt</th>
                         <th>${sumToPay} PLN</th>
                       </tr>
                       <tr class="w3-light-grey ">
                         <th colspan="4" style="text-align:right;">koszt transportu:</th>
                         <th></th>
                         <th>${webOrder.deliveryOption.price} PLN</th>
                       </tr>
                       <tr class="w3-light-grey ">
                         <th colspan="4" style="text-align:right;">łącznie do zapłaty:</th>
                         <th></th>
                         <th>${sumToPay+webOrder.deliveryOption.price} PLN</th>
                       </tr>
                </table>


            </div>

<div class="w3-responsive w3-margin">
  <p class="w3-large"><strong>Dane do wysyłki:</strong></p>
  <p>${webOrder.user.firstName} ${webOrder.user.lastName}</p>
  <p>${address.street} ${address.houseNo}<c:if test="${address.flatNo!='' && address.flatNo!=null}">/${address.flatNo}</c:if></p>
  <p>${address.postCode} ${address.city}</p>
  <p>numer telefonu${webOrder.deliveryOption.id<4 ? ' dla kuriera: ' :':'} <b>${webOrder.user.phone}</b></p>
  <p>uwagi do zamówienia: <c:if test="${webOrder.comment!=null && webOrder.comment!=''}"><b>${webOrder.comment}</b></c:if>
  <c:if test="${webOrder.comment==null || webOrder.comment==''}">BRAK</c:if></p>
</div>

<div class="w3-responsive w3-margin w3-row">
    <p class="w3-large"><strong>Metoda płatności:</strong></p>
    <p>${webOrder.paymentMethod.namePl}</p>
</div>
<div class="w3-responsive w3-margin w3-row">
    <p class="w3-large"><strong>Opcja dostawy:</strong></p>
    <p>${webOrder.deliveryOption.namePl}</p>
</div>


<div class="w3-container w3-responsive" style="padding: 10px">
   <button class="w3-button w3-white w3-border w3-round-large w3-left" onclick="goBack()">cofnij</button>
          <script>
             function goBack() {
              window.history.back();
             }
          </script>
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

