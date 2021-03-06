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

    .main-img {
      width: 50%;
    }

    @media only screen and (max-width: 600px) {
          /* For mobile phones: */
              .main-img {
                width: 100%;
              }

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
        <a href="${pageContext.request.contextPath}/types/${productType.name}">
        <p class="w3-left">${productType.namePl}</p></a>
        <%@include file='header.jsp' %>
    </header>

    <!-- Image header -->
    <div class="w3-content" style="max-width:1200px">
        <div class="w3-container w3-right">
            <div class="w3-display-container">
                <img src="${pageContext.request.contextPath}/images/${productType.id}/${product.id}.jpg" alt="${product.name}" class="main-img">
                <c:if test="${product.creationDate>=newProductPeriod}">
                   <span class="w3-tag w3-display-topleft">Nowość</span>
                </c:if>
                <c:if test="${product.discount>0}">
                   <span class="w3-tag w3-red w3-display-topmiddle">-${product.discount}%</span>
                </c:if>
            </div>
                    <p>${product.namePl} - cena:
                <c:if test="${product.discount==0}">
                    <b>${product.price} PLN</b></p>
                </c:if>
                <c:if test="${product.discount>0}">
                   <s><b>${product.price} PLN</s>
                   <a class="w3-padding-small" style="color:red"><fmt:formatNumber type="number" maxFractionDigits="2"
                          value="${product.price*(1-((product.discount)*0.01))}"/> PLN</a></b></p>
                </c:if>
        </div>
    </div>

    <!-- Product grid -->
    <div class="w3-row">
        <div class="w3-col">
            <div class="w3-container">
                <p>
                    <b>Specyfikacja produktu:</b>
                </p>
                <p>
                    technika wykonania: <b>${product.makingTechnique.namePl}</b><br>
                    użyty materiał: <b>${product.material.namePl}</b><br>
                    kolor materiału: <b>${product.materialColor.namePl}</b><br>
               <!--     <select name="colorId" class="" onchange="this.form.submit()">
                        <option selected="selected"
                                value="${product.materialColor.id}">
                            ${product.materialColor.namePl}
                        </option>
                        <c:forEach var="color" items="${colors}">
                            <option value="${color.materialColor.namePl}">${color.materialColor.namePl} - dostępność
                                w ${color.availability}</option>
                        </c:forEach>
                    </select></b><br>                                      -->
                    zapięcie: <b>${product.fasteningType.namePl}</b><br>
            <!--    kolor zapięcia: <b>${product.fasteningColor.namePl}</b><br> -->
                    długość: <b>${product.length} mm</b><br>
                    szerokość: <b>${product.width} mm</b><br>
                    <b>Opis:</b><br> ${product.descriptionPl}
                </p>
            </div>
            <c:if test="${userWishListProduct.product!=product}">
            <form method="POST" action="${pageContext.request.contextPath}/wishList/addFromDetail">
                 <input type="hidden" name="productId" value="${product.id}">
                 <button style="margin-left:10px" class="w3-button w3-border w3-white w3-left w3-round-large">dodaj do ulubionych <i class="far fa-heart"></i></button>
            </form>
            </c:if>
            <c:if test="${userWishListProduct.product==product}">
             <form method="POST" action="${pageContext.request.contextPath}/wishList/removeFromDetail">
                 <input type="hidden" name="productId" value="${product.id}">
                 <button class="w3-button w3-border w3-white w3-left w3-round-large">usuń z ulubionych <i class="fas fa-heart"></i></button>
              </form>
            </c:if>
            <form method="POST" action="${pageContext.request.contextPath}/basket/addFromDetail">
                <input type="hidden" name="productId" value="${product.id}">
                <button class="w3-button w3-border w3-green w3-left w3-round-large">dodaj do koszyka <i class="fa fa-shopping-cart"></i></button>
            </form>
          <c:if test="${sessionScope.user.role.id<='2'}">
             <a href="${pageContext.request.contextPath}/panels/data/product/${product.id}">
                    <button style="margin-left:10px" class="w3-button w3-red w3-border w3-round-large" >edytuj <i class="far fa-edit"></i></button>
             </a>
          </c:if>
        </div>
        <div class="w3-row w3-margin" >
                   <p>Produkt występuje w następujących kolorach:</p>
                    <c:forEach var="item" items="${productsGroup}">
                        <div class="w3-col l3 s6" style="min-height: 275px;">
                          <div class="w3-container">
                            <div class="w3-display-container">
                                <a href="${pageContext.request.contextPath}/details/${item.id}">
                                <img src="${pageContext.request.contextPath}/images/${productType.id}/${item.id}.jpg" style="width:100%"></a>
                                <c:if test="${item.creationDate>=newProductPeriod}">
                                    <span class="w3-tag w3-display-topleft">Nowość</span>
                                </c:if>
                                <c:if test="${item.discount>0}">
                                    <span class="w3-tag w3-red w3-display-topright">-${item.discount}%</span>
                                </c:if>
                                <p><a href="${pageContext.request.contextPath}/details/${item.id}">${item.namePl}<br>
                                <c:if test="${item.discount==0}">
                                   <b>${item.price} PLN</b></a></p>
                                </c:if>
                                <c:if test="${item.discount>0}">
                                   <s><b>${item.price} PLN</s></a>
                                   <a class="w3-padding-small" style="color:red"><fmt:formatNumber type="number" maxFractionDigits="2"
                                      value="${item.price*(1-((item.discount)*0.01))}"/> PLN</a></b></p>
                                </c:if>
                            </div>
                           </div>
                        </div>
                    </c:forEach>
        </div>
        <div class="w3-container">
            <i class="fa fa-arrow-left"></i>
            <a href="${pageContext.request.contextPath}/types/${product.type.name}">cofnij</a>
        </div>
    </div>



    <!-- Footer -->
    <%@include file='footer.jsp' %>

    <!-- Signature -->
    <%@include file='signature.html' %>

    <!-- End page content -->
</div>

<!-- Login Modal -->
<%@include file='loginModal.jsp' %>



<script>
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
            dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
        }
        x[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " w3-opacity-off";
    }
</script>


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

