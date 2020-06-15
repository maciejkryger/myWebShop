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
        <p class="w3-left">ulubione</p>
        <%@include file='../header.jsp' %>
    </header>

    <!-- user session  -->
        <h2 class="w3-margin">Twoje ulubione produkty</h2>

        <div class="w3-responsive w3-margin">
                    <p>
                    <c:if test="${sessionScope.user.username!=null}">
                        <label><b>Ulubione produkty użytkownika: </b>${sessionScope.user.username}</label>
                        <input type="hidden" name="username"  value="${sessionScope.user.username}">
                    </c:if>
                    </p>
        </div>

    <!-- Product grid -->
    <div class="w3-row w3-grayscale w3-margin">
        <a>${userWishList.isEmpty() ? 'Lista produktów ulubionych jest pusta.' :''}</a>
    </div>

    <c:if test="${!userWishList.isEmpty()}">
    <!--
        <c:forEach var="item" items="${userWishList}">
            <div class="w3-col l3 s6">
                <div class="w3-display-container">
                <form method="POST" action="${pageContext.request.contextPath}/wishList/removeFromWishList" onclick="submit">
                   <input type="hidden" name="productId" value="${item.product.id}">
                    <input type="hidden" name="userId"  value="${sessionScope.user.id}">
                    <button class="w3-button w3-display-topright w3-display-hover"><i class="fas fa-trash"></i></button>
                 </form>
                    <img src="${pageContext.request.contextPath}/images/${item.product.type.id}/${item.product.id}.jpg" style="width:100%">
                    <p><a href="${pageContext.request.contextPath}/details/${item.product.id}">${item.product.namePl}<br><b>${item.product.price} PLN</b></a></p>
               </div>
            </div>
        </c:forEach>
    -->

     <div class="w3-responsive">
                <table class="w3-table-all w3-hoverable">
                    <thead>
                    <tr class="w3-light-grey ">
                        <th style="width:25%">Zdjęcie</th>
                        <th>nazwa produktu</th>
                        <th>cena</th>
                        <th>usuń</th>
                        <th>kup</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${userWishList}">
                        <tr>
                            <td style="width:25%">
                                <img src="${pageContext.request.contextPath}/images/${item.product.type.id}/${item.product.id}.jpg" style="width:100%">
                            </td>
                            <td>${item.product.namePl}</td>
                            <td style="width:25%">${item.product.price} PLN</td>
                            <td>
                               <form method="POST" action="${pageContext.request.contextPath}/wishList/removeFromWishList" onclick="submit">
                                  <input type="hidden" name="productId" value="${item.product.id}">
                                  <button class="w3-button w3-hover"><i class="fas fa-trash"></i></button>
                               </form>
                            </td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/basket/addFromWishList">
                                   <input type="hidden" name="productId" value="${item.product.id}">
                                   <button class="w3-button w3-hover"><i class="fa fa-shopping-cart"></i></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
       </div>

    </c:if>
        <div class="w3-container w3-responsive" style="padding: 10px">
                 <a href="${pageContext.request.contextPath}/" class="w3-button w3-white w3-border w3-round-large">wróć do zakupów</a>
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

