<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='../head.html' %>
<style>
    .w3-sidebar a {
        font-family: "Roboto", sans-serif
    }

    body, h1, h2, h3, h4, h5, h6, .w3-wide {
        font-family: "Montserrat", sans-serif;
    }
</style>
<body class="w3-content" style="max-width:2200px">

<!-- Sidebar/menu -->
<%@include file='panelsMenu.jsp' %>

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
        <%@include file='panelsHeader.jsp' %>
        <%@include file='../header.jsp' %>
    </header>

    <!-- Main grid -->
    <div class="w3-container">

        <h2>Edycja danych firmy</h2>
        <div class="w3-responsive">
            <a href="${pageContext.request.contextPath}/panels/data/companies/">
                <button class="w3-button w3-white w3-border w3-round-large">anuluj</button>
            </a>
            <table class="w3-table-all w3-hoverable">
                <form method="post"
                      action="${pageContext.request.contextPath}/panels/data/company/save}"
                      modelAttribute="company">
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>


                    <p>
                        <input type="hidden" name="id" placeholder="id" class="w3-input w3-border"
                               value="${company.id}">
                    </p>
                    <p>
                        <label><b>Nazwa firmy</b></label>
                        <input type="text" name="name" placeholder="nazwa firmy" class="w3-input w3-border"
                               value="${company.name}">
                    </p>
                    <p>
                        <label><b>Adres</b></label>
                        <input type="text" name="address" placeholder="adres" class="w3-input w3-border"
                               value="${company.address}">
                    </p>
                    <p>
                        <label><b>kod pocztowy</b></label>
                        <input type="text" name="postCode" placeholder="kod pocztowy" class="w3-input w3-border"
                               value="${company.postCode}">
                    </p>
                    <p>
                        <label><b>Miasto</b></label>
                        <input type="text" name="city" placeholder="miasto" class="w3-input w3-border"
                               value="${company.city}">
                    </p>
                    <p>
                        <label><b>Numer telefonu</b></label>
                        <input type="text" name="phone" placeholder="phone" class="w3-input w3-border"
                               value="${company.phone}">
                    </p>
                    <p>
                        <label><b>E-mail</b></label>
                        <input type="text" name="email" placeholder="email" class="w3-input w3-border"
                               value="${company.email}">
                    </p>
                    <p>
                        <label><b>Numer NIP</b></label>
                        <input type="text" name="taxNumber" placeholder="numer NIP" class="w3-input w3-border"
                               value="${company.taxNumber}">
                    </p>
                    <p>
                        <label><b>Numer konta bankowego</b></label>
                        <input type="text" name="accountNumber" placeholder="numer konta" class="w3-input w3-border"
                               value="${company.accountNumber}">
                    </p>
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>
                </form>
                <a href="${pageContext.request.contextPath}/panels/data/companies/">
                    <button class="w3-button w3-white w3-border w3-round-large">anuluj</button>
                </a>

            </table>
        </div>

        <!-- Signature -->
        <%@include file='../signature.html' %>

    </div>
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

</body>
</html>

