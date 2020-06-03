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

        <h2>Edycja asortymentu</h2>
        <div class="w3-responsive">
            <a href="${pageContext.request.contextPath}/panels/data/deliveryOptions/">
                <button class="w3-button w3-white w3-border w3-round-large">anuluj</button>
            </a>
            <table class="w3-table-all w3-hoverable">
                <form method="post" action="${pageContext.request.contextPath}/panels/data/deliveryOption/save"
                      modelAttribute="type">
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>


                    <p>
                        <label><b>id: ${type.id}</b></label>
                        <input type="hidden" name="id" placeholder="id" class="w3-input w3-border"
                               value="${deliveryOption.id}">
                    </p>
                    <p>
                        <label><b>Nazwa ENG</b></label>
                        <input type="text" name="name" placeholder="nazwa ENG" class="w3-input w3-border"
                               value="${deliveryOption.name}">
                    </p>
                    <p>
                        <label><b>Nazwa PL</b></label>
                        <input type="text" name="namePl" placeholder="nazwa PL" class="w3-input w3-border"
                               value="${deliveryOption.namePl}">
                    </p>
                    <p>
                        <label><b>Typ płatności</b></label>
                        <select class="w3-input w3-border" name="paymentTypeId">
                           <option selected="selected" value="${deliveryOption.paymentType.id}">${deliveryOption.paymentType.namePl}</option>
                             <c:forEach var="item" items="${paymentTypes}">
                               <option value="${item.id}">${item.namePl}</option>
                             </c:forEach>
                           </select>
                    </p>
                    <p>
                        <label><b>Cena</b></label>
                        <input type="number" name="price" placeholder="cena" class="w3-input w3-border"
                               value="${deliveryOption.price}">
                    </p>
                    <p>
                        <label><b>Aktywny</b></label>
                        <input type="text" name="active" placeholder="czy aktywny" class="w3-input w3-border"
                               value="${deliveryOption.active}">
                    </p>
                    <p>
                        <label><b>Domyślny</b></label>
                        <input type="text" name="checked" placeholder="czy domyślny" class="w3-input w3-border"
                               value="${deliveryOption.checked}">
                    </p>
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>
                </form>
                <a href="${pageContext.request.contextPath}/panels/data/deliveryOptions/">
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

