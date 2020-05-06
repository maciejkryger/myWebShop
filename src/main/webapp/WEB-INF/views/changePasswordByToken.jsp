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
        <p class="w3-left">zmiana hasła</p>
        <%@include file='header.jsp' %>
    </header>

    <!-- Change password form -->
        <h2>Zmień hasło</h2>

        <div class="w3-responsive">

            <table class="w3-table-all w3-hoverable">
                <form method="post" action="${pageContext.request.contextPath}/changePasswordByToken">
                    <input type="hidden" name="token" value="${token}">
                    <input type="hidden" name="email" value="${email}">
                    <a class="w3-bar-item" style="color: blue">${passwordChanged ? 'Hasło zostało poprawnie zmienione' :''}</a>
                    <a class="w3-bar-item" style="color: crimson">${newPasswordsNotTheSame ? 'Nowe hasło w obu polach musi być identyczne!!!' :''}</a>
                    <a class="w3-bar-item" style="color: crimson">${noPassword ?  'Pole na hasło nie może być puste' :''}</a>
                    <a class="w3-bar-item" style="color: crimson">${userNotExist ?  'Uwaga! Próbujesz wykorzystać błędny token. Hasło nie zostało zmienione.' :''}</a>
                    <a class="w3-bar-item" style="color: crimson">${noSuccess ?  'Uwaga! Token i email do siebie nie pasują. Hasło nie zostało zmienione.' :''}</a>
                                    <a class="w3-bar-item" style="color: crimson">${wrongPasswordChar ?  'Zbyt słabe hasło, minimum 8 znaków , lub w polu wykorzystano niedozwolony znak specjalny, dozwolone !,?,@,$,&' :''}</a>
                    <p>
                    <p>
                        <label><b>wpisz nowe hasło</b></label>
                        <input type="password" name="newPassword" placeholder="nowe hasło" class="w3-input w3-border">
                    </p>
                    <p>
                        <label><b>wpisz nowe hasło ponownie</b></label>
                        <input type="password" name="newPassword2" placeholder="powtórz nowe hasło" class="w3-input w3-border">
                    </p>

                    <input type="submit" class="w3-button w3-red w3-border w3-round-large" value="zapisz"/>
                </form>
                  <button class="w3-button w3-white w3-border w3-round-large" onclick="goBack()">anuluj</button>
                 <!--       <a href="/" class="w3-button w3-white w3-border w3-round-large">Homepage</a>-->
                        <script>
                           function goBack() {
                            window.history.back();
                           }
                        </script>

            </table>
        </div>

    <br>


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

