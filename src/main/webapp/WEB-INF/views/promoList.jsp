<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='head.html' %>
<style>
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
        <p class="w3-left">Promocje</p>
        <%@include file='header.jsp' %>
    </header>

    <!-- Image header -->

    <div class="w3-container w3-text-grey" id="products">

        <c:if test="${promoProducts.size()==0}">
            <p>znaleziono: ${promoProducts.size()} pozycji</p>
        </c:if>
        <c:if test="${promoProducts.size()==1}">
            <p>znaleziono: ${promoProducts.size()} pozycja</p>
        </c:if>
        <c:if test="${promoProducts.size()==2}">
             <p>znaleziono: ${promoProducts.size()} pozycje</p>
        </c:if>
        <c:if test="${promoProducts.size()==3}">
             <p>znaleziono: ${promoProducts.size()} pozycje</p>
        </c:if>
        <c:if test="${promoProducts.size()==4 || promoProducts.size()==24 || promoProducts.size()==34 || promoProducts.size()==44 }">
             <p>znaleziono: ${promoProducts.size()} pozycje</p>
        </c:if>
        <c:if test="${promoProducts.size()>4}">
            <p>znaleziono: ${promoProducts.size()} pozycji</p>
        </c:if>
    </div>

    <!-- Product grid -->
    <div class="w3-row">
        <c:forEach var="item" items="${promoProducts}">
            <div class="w3-col l3 s6" style="min-height: 270px;">
                <div class="w3-container">
                  <div class="w3-display-container">
                    <img src="${pageContext.request.contextPath}/images/${item.type.id}/${item.id}.jpg" style="width:100%">
                    <p><a href="${pageContext.request.contextPath}/details/${item.id}">${item.namePl}<br><b>${item.price} PLN</b></a></p>
                       <c:if test="${item.creationDate>=newProductPeriod}">
                         <span class="w3-tag w3-display-topleft">Nowość</span>
                       </c:if>
                        <c:if test="${item.discount>0}">
                         <span class="w3-tag w3-red w3-display-topright">-${item.discount}%</span>
                       </c:if>
                     </div>
                </div>
            </div>
        </c:forEach>
    </div>
        <button class="w3-button w3-white w3-border w3-round-large" onclick="goBack()">wróć do strony głównej</button>
           <script>
             function goBack() {
             window.history.back();
                           }
           </script>


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

</body>
</html>

