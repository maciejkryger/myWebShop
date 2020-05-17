<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='head.html' %>
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
<%@include file='menu.jsp' %>


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
        <p class="w3-left">podsumowanie</p>
        <%@include file='header.jsp' %>
    </header>


<!-- Delivery grid -->
 <h2>Podsumowanie zamówienia</h2>
        <div class="w3-responsive">
                    <c:if test="${sessionScope.user.username!=null}">
                        <label><b>Jesteś zalogowany jako: </b>${sessionScope.user.username}</label>
                    </c:if>
        </div>

 <div class="w3-responsive">
                 <table class="w3-table-all w3-hoverable">
                     <thead>
                     <tr class="w3-light-grey ">
                         <th style="width:25%">Zdjęcie</th>
                         <th>nazwa produktu</th>
                         <th>cena produktu</th>
                         <th>ilość</th>
                         <th>suma</th>

                     </tr>
                     </thead>
                     <tbody>
                     <c:forEach var="item" items="${productsInBasket}">
                         <tr>
                             <td style="width:25%">
                             <img src="${pageContext.request.contextPath}/images/${item.product.type.id}/${item.product.id}.jpg" style="width:100%">
                             </td>
                             <td>${item.product.namePl}</td>
                             <td>${item.product.price} PLN</td>
                             <td>
                                 <form method="POST" action="${pageContext.request.contextPath}/basket/addFromBasket" onclick="submit">
                                     <input type="hidden" name="productId" value="${item.product.id}">
                                     <button class="w3-button"><i class="fas fa-plus"></i></button>
                                 </form>
                                 <a style="margin: 17px">${item.quantity}</a>
                                 <form method="POST" action="${pageContext.request.contextPath}/basket/removeFromBasket" onclick="submit">
                                    <input type="hidden" name="productId" value="${item.product.id}">
                                    <button class="w3-button"><i class="fas fa-minus"></i></button>
                                 </form>

                             </td>
                             <td>${item.product.price*item.quantity} PLN</td>

                         </tr>
                     </c:forEach>
                     </tbody>
                     <tfoot>
                       <tr class="w3-light-grey ">
                         <th</th>
                         <th></th>
                         <th></th>
                         <th>PODSUMOWANIE:</th>
                         <th>${sumQuantity} szt</th>
                         <th>${sumToPay} PLN</th>

                       </tr>
                       <tr class="w3-light-grey ">
                          <th</th>
                          <th></th>
                          <th></th>
                          <th>Koszty przesyłki:</th>
                          <th></th>
                          <th>${deliveryCostsToPay} PLN</th>

                          </tr>
                       <tr class="w3-light-grey ">
                          <th</th>
                          <th></th>
                          <th></th>
                          <th>Razem do zapłaty:</th>
                          <th></th>
                          <th>${sumToPay+deliveryCostsToPay} PLN</th>

                          </tr>
                     </tfoot>
                 </table>
             </div>

  <div class="w3-responsive w3-margin">
  <p class="w3-large"><strong>Dane do wysyłki:</strong></p>
  <p>${user.firstName} ${user.lastName}</p>
  <p>${address.street} ${address.houseNo} <c:if test="${address.flatNo==null}">/${address.flatNo}</c:if></p>
  <p>${address.postCode} ${address.city}</p>
  </div>

   <div class="w3-responsive w3-margin">
    <p class="w3-large"><strong>Metoda płatności:</strong></p>
    <p>${webOrder.paymentMethod.namePl}</p>
    </div>


<div class="w3-container w3-responsive" style="padding: 10px">


   <button class="w3-button w3-white w3-border w3-round-large" onclick="goBack()">cofnij</button>
          <script>
             function goBack() {
              window.history.back();
             }
          </script>
   <form method="POST" action="${pageContext.request.contextPath}/confirmation">
   <input type="hidden" name="orderId" value="${webOrder.id}">
   <input type="submit" class="w3-button w3-white w3-border w3-round-large w3-right" value="zamów">
   </form>


</div>
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

