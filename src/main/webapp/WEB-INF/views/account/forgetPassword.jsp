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
        <p class="w3-left">zmiana hasła</p>
        <%@include file='../header.jsp' %>
    </header>

    <!-- Change password form -->
    <div class="w3-margin">
        <h3>Zapomniałeś hasła?</h2>
        <h5>Wprowadź swój adres e-mail podany przy rejestracji. <br> Wyślemy Ci link umożliwiający zmianę hasła.</h4>
    </div>

        <div class="w3-responsive w3-margin">

            <table class="w3-table-all w3-hoverable">
                <form method="post" action="${pageContext.request.contextPath}/forgetPassword">
                   <a class="w3-bar-item" style="color: crimson">${userNotExist ? 'Email, który podałeś nie istnieje w naszej bazie!!!' :''}</a>
                   <a class="w3-bar-item" style="color: green">${emailSent ? 'Link do zmiany hasła został wysłany na podany adres mailowy.' :''}</a>
                   <a class="w3-bar-item" style="color: crimson">${noEmail ?  'Pole na email nie może zostać puste!' :''} </a>
                   <a class="w3-bar-item" style="color: crimson">${wrongEmailChar ?  'Nieprawidłowy format maila, lub wykorzystano niedozwolone znaki specjalne.' :''}</a>
                   <p>
                        <label><b>Adres email podany podczas rejestracji:</b></label>
                        <input type="text" name="email" placeholder="wpisz email" class="w3-input w3-border" value="${email}">
                   </p>
                    <input type="submit" class="w3-button w3-green w3-border w3-round-large" value="zatwierdź"/>
                </form>
                  <button class="w3-button w3-white w3-border w3-round-large" onclick="goBack()">anuluj</button>

                        <script>
                           function goBack() {
                            window.history.back();
                           }
                        </script>

            </table>
        </div>

    <br>


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

