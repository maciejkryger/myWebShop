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

        .myData-container{
            padding-bottom: 20px;
            max-width:500px;
            border-radius: 10px;
            margin: 10px;
        }

        @media only screen and (max-width: 600px) {
              /* For mobile phones: */
            .myData-container{
                font-size: 11px;
                padding-bottom: 10px;
                border-radius: 10px;
                max-width:45%;
            }

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

        <h2>Konfiguracja</h2>

            <a href="${pageContext.request.contextPath}/panels/superpanel/">
                <button class="w3-button w3-white w3-border w3-round-large">zamknij okno</button>
            </a>
        <div class="w3-responsive w3-row" style="padding-top: 10px;">

            <c:forEach var="config" items="${configs}">
            <div class="w3-col l6 s6 w3-container myData-container w3-border">

                <form method="post" action="${pageContext.request.contextPath}/panels/data/config/save"
                      modelAttribute="config" >

                    <p>
                        <input type="hidden" name="id" class="w3-input w3-border" value="${config.id}">
                        <input type="hidden" name="name" class="w3-input w3-border" value="${config.name}">
                        <label><b>Nazwa pola: ${config.name}</b></label>
                    </p>
                    <p>
                         <label><b>Opis</b></label>
                         <input type="text" name="description" placeholder="nazwa" class="w3-input w3-border"
                                   value='${config.description}'>
                    </p>
                    <p>
                         <label><b>Wartość pola</b></label>
                         <input type="text" name="value" placeholder="wartość pola" class="w3-input w3-border"
                            value='${config.value}' >
                    </p>
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large w3-right" value="zapisz"/>
                </form>

            </div>
            </c:forEach>
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

