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
        <p class="w3-left">biżuteria ręcznie robiona</p>
        <%@include file='../header.jsp' %>
    </header>

    <!-- Login form -->
    <div class="w3-content w3-container" style="max-width:100%">
        <table class="w3-table-all w3-hoverable">
            <a class="w3-bar-item w3-padding" style="color: blue">${success ? 'Rejestracja zakończyła się pomyślnie, sprawdż pocztę by aktywować swojego użytkownia' :''}</a>
            <a class="w3-bar-item w3-padding" style="color: crimson">${userExist ? 'Użytkownik już w naszej bazie istnieje, skorzystać z przypomnienia hasła' :''}</a>
            <a class="w3-bar-item w3-padding" style="color: red">${userExistButNotActive ? 'Użytkownik już w naszej bazie istnieje, ale nie został aktywowany, odszukaj maila z linkiem aktywacyjnym' :''}</a>
            <form method="post" action="<c:url value='${pageContext.request.contextPath}/register' />">
                <p class="w3-large">Rejestracja nowego użytkownika</p>
                <p>zarejestruj się</p>
                <a class="w3-bar-item" style="color: crimson">${noUsername ?  'Pole loginu nie może być puste' :''}</a>
                <a class="w3-bar-item" style="color: crimson">${wrongUsernameChar ?  'W polu wykorzystano niedozwolony znak specjalny lub jest za krótkie, wpisz min 3' :''}</a>
                <p><input class="w3-input w3-border" type="text" name="username" placeholder="Wpisz login" value="${username}"></p>
                <a class="w3-bar-item" style="color: crimson">${noPassword ?  'Pole na hasło nie może być puste' :''}</a>
                <a class="w3-bar-item" style="color: crimson">${wrongPasswordChar ?  'Zbyt słabe hasło, minimum 8 znaków , lub w polu wykorzystano niedozwolony znak specjalny, dozwolone !,?,@,$,&' :''}</a>
                <p><input class="w3-input w3-border" type="password" name="password" placeholder="Wpisz hasło" ></p>
                <a class="w3-bar-item" style="color: crimson">${noFirstName ?  'Pole na imię nie może być puste' :''}</a>
                <a class="w3-bar-item" style="color: crimson">${wrongFirstNameChar ?  'W polu wykorzystano niedozwolony znak specjalny lub jest za krótkie, wpisz min 3' :''}</a>
                <p><input class="w3-input w3-border" type="text" name="firstName" placeholder="Wpisz imię" value="${firstName}"></p>
                <a class="w3-bar-item" style="color: crimson">${noLastName ?  'Pole na nazwisko nie może być puste' :''}</a>
                <a class="w3-bar-item" style="color: crimson">${wrongLastNameChar ?  'W polu wykorzystano niedozwolony znak specjalny lub jest za krótkie, wpisz min 3' :''}</a>
                <p><input class="w3-input w3-border" type="text" name="lastName" placeholder="Wpisz nazwisko" value="${lastName}"></p>
                <a class="w3-bar-item" style="color: crimson">${noEmail ?  'Pole na email nie może być puste' :''} </a>
                <a class="w3-bar-item" style="color: crimson">${emailExist ?  'Ten mail jest już w naszej bazie, pewnie masz już u nas użytkownika :) skorzystaj z opcji zapomniałem hasła.' :''} </a>
                <a class="w3-bar-item" style="color: crimson">${wrongEmailChar ?  'Nieprawidłowy format maila, lub wykorzystano niedozwolone znaki specjalne' :''}</a>
                <p><input class="w3-input w3-border" type="text" name="email" placeholder="Wpisz adres e-mail" value="${email}"></p>

                <div style="padding-bottom: 20px">
                    <a class="w3-bar-item" style="color: crimson">${withoutRulesAcceptation ?  'Akceptacja regulaminu jest obowiązkowa' :''}</a>
                    <p><input class="w3-radio" type="checkbox" name="rulesAccepted" value="true">
                    ${RulesAcceptedStatement}
                    </p>
                    <p><input class="w3-radio" type="checkbox" name="marketingAgreed" value="true">
                    ${MarketingAgreedStatement}
                    </p>
                </div>
                <button type="submit"  class="w3-button w3-padding-large w3-green w3-margin-bottom w3-round-large w3-left"
                        onclick="document.getElementById('login').style.display='none'">zarejestruj
                </button>
            </form>
            <a href="${pageContext.request.contextPath}/" class="w3-button w3-white w3-border w3-round-large" >anuluj</a>
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

