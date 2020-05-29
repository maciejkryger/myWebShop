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

</style>
<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<%@include file='../menu.jsp' %>


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
        <p class="w3-left">krok 2 - adres wysyłki</p>
        <%@include file='../header.jsp' %>
    </header>


<!-- Delivery grid -->
 <h2>Podaj adres do wysyłki</h2>
        <div class="w3-responsive">
                    <c:if test="${sessionScope.user.username!=null}">
                        <label><b>Jesteś zalogowany jako: </b>${sessionScope.user.username}</label>
                    </c:if>
        </div>

 <div class="w3-container w3-responsive w3-margin">
 <p>Wartość koszyka: <strong> ${sumToPay} PLN </strong></p>
 <p>Koszty dostawy: <strong> ${deliveryCostsToPay} PLN </strong></p>
 <p>-------------------------------------------------</p>
 <p>Łącznie do zapłaty: <strong> ${sumToPay+deliveryCostsToPay} PLN </strong></p>
 </div>

 <div class="w3-responsive w3-container w3-padding w3-margin">

     <form method="POST" action="${pageContext.request.contextPath}/address" id="addressForm">
      <label class="w3-row w3-large"><b>Adres dostawy:</b></label>
      <p><label class="w3-row"><b>ulica, nr domu i numer mieszkania:</b></label></p>
      <p>
      <input type="text" name="street" placeholder="ulica" value="${address.street}" class="myInput">
      <input type="text" name="houseNo" placeholder="nr dom" maxlength="4" size="4" value="${address.houseNo}" class="myInput">
      <input type="text" name="flatNo" placeholder="nr m." maxlength="3" size="4" value="${address.flatNo}" class="myInput"><br>
      </p>
      <a class="w3-bar-item w3-row" style="color: red">${streetWrong ? 'Pole ulicy posiada niedozwolone znaki.' :''} ${streetEmpty ? 'Pole ulicy nie może zostać puste.' :''}</a>
      <a class="w3-bar-item w3-row" style="color: red">${houseNoWrong ? 'Pole numeru domu posiada niedozwolone znaki.' :''} ${houseNoEmpty ? 'Pole numeru domu  nie może zostać puste.' :''}</a>
      <p><label class="w3-row"><b>kod pocztowy(00-000) i miasto:</b></label></p>
      <p>
      <input type="text" name="postCode" placeholder="kod poczt." maxlength="6" size="6"  value=${address.postCode} class="myInput">
      <input type="text" name="city" placeholder="miasto" value="${address.city}" class="myInput">
      </p>
      <a class="w3-bar-item w3-row" style="color: red">${postCodeWrong ? 'Pole kodu pocztowego ma zły format' :''} ${postCodeEmpty ? ', nie może zostać puste.' :''}</a>
            <a class="w3-bar-item w3-row" style="color: red">${cityWrong ? 'Pole miasta ma zły format.' :''} ${cityEmpty ? 'Pole miasta nie może zostać puste.' :''}</a>
    </form>
</div>


<div class="w3-container w3-responsive" style="padding: 10px">


   <button class="w3-button w3-white w3-border w3-round-large" onclick="goBack()">cofnij</button>
          <script>
             function goBack() {
              window.history.back();
             }
          </script>
   <input type="submit" form="addressForm" class="w3-button w3-green w3-border w3-round-large w3-right" value="dalej"/>

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

