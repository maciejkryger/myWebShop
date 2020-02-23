<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
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
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
    <div class="w3-container w3-display-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
        <a href="/"><h3 class="w3-wide"><b>QUNSZTOWNA</b></h3></a>
    </div>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <c:forEach var="productType" items="${productTypesList}">
            <a href="/types/${productType.name}" class="w3-bar-item w3-button">${productType.namePL}</a>
        </c:forEach>
    </div>
    <a href="#footer" class="w3-bar-item w3-button w3-padding">Kontakt</a>
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"
       onclick="document.getElementById('newsletter').style.display='block'">Newsletter</a>
    <%--    <a href="#footer"  class="w3-bar-item w3-button w3-padding">Subskrybuj</a>--%>
</nav>

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
        <p class="w3-left">${productType.namePL}</p>
        <p class="w3-right">
            <i class="fa fa-user w3-margin-right"></i>
            <i class="fa fa-search"></i>
            <i class="fa fa-shopping-cart"></i>
        </p>
    </header>

    <!-- Image header -->
    <div class="w3-display-container w3-container">
        <img src="/images/${product.id}.webp" alt="${product.name}" style="width:100%">
        <p>${product.name} - cena: <b>${product.price} PLN</b></p>
    </div>


    <!-- Product grid -->
    <div class="w3-row w3-grayscale">
        <div class="w3-col">
            <div class="w3-container">
                <p>
                    <b>Specyfikacja produktu:</b>
                </p>
                <p>
                    technika wykonania: <b>${product.makingTechnique.namePl}</b><br>
                    użyty materiał: <b>${product.material.namePl}</b><br>
                    kolor materiału: <b>
                    <select name="colorId" class="" onchange="this.form.submit()">
                        <option selected="selected"
                                value="${product.materialColor.id}">
                            ${product.materialColor.namePl}
                        </option>
                        <c:forEach var="color" items="${colors}">
                            <option value="${color.materialColor.namePl}">${color.materialColor.namePl} - dostępność w ${color.availability}</option>
                        </c:forEach>
                    </select></b><br>
                    zapięcie: <b>${product.fasteningType.namePl}</b><br>
                    kolor zapięcia: <b>${product.fasteningColor.namePl}</b><br>
                    długość: <b>${product.length} mm</b><br>
                    szerokość: <b>${product.width} mm</b><br>
                    <b>Opis:</b><br> ${product.description}
                </p>
            </div>
        </div>

        <c:forEach var="color" items="${colors}">
            <div class="w3-col l3 s6">
                <div class="w3-container">
                    <img src="/images/${product.id}_${color.materialColor.id}.webp" style="width:100%">
                    <p>${color.materialColor.namePl}</p>
                </div>
            </div>
        </c:forEach>
        <div class="w3-container">
            <i class="fa fa-arrow-left"></i>
            <a href="${pageContext.request.contextPath}/types/${product.type.name}">cofnij</a>
        </div>
    </div>


    <%--        <div class="w3-col l3 s6">--%>
    <%--            <div class="w3-container">--%>
    <%--                <div class="w3-display-container">--%>
    <%--                    <img src="/w3images/jeans2.jpg" style="width:100%">--%>
    <%--                    <span class="w3-tag w3-display-topleft">New</span>--%>
    <%--                    <div class="w3-display-middle w3-display-hover">--%>
    <%--                        <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--                <p>Mega Ripped Jeans<br><b>$19.99</b></p>--%>
    <%--            </div>--%>
    <%--            <div class="w3-container">--%>
    <%--                <img src="/w3images/jeans3.jpg" style="width:100%">--%>
    <%--                <p>Washed Skinny Jeans<br><b>$20.50</b></p>--%>
    <%--            </div>--%>
    <%--        </div>--%>

    <%--        <div class="w3-col l3 s6">--%>
    <%--            <div class="w3-container">--%>
    <%--                <img src="/w3images/jeans3.jpg" style="width:100%">--%>
    <%--                <p>Washed Skinny Jeans<br><b>$20.50</b></p>--%>
    <%--            </div>--%>
    <%--            <div class="w3-container">--%>
    <%--                <div class="w3-display-container">--%>
    <%--                    <img src="/w3images/jeans4.jpg" style="width:100%">--%>
    <%--                    <span class="w3-tag w3-display-topleft">Sale</span>--%>
    <%--                    <div class="w3-display-middle w3-display-hover">--%>
    <%--                        <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--                <p>Vintage Skinny Jeans<br><b class="w3-text-red">$14.99</b></p>--%>
    <%--            </div>--%>
    <%--        </div>--%>

    <%--        <div class="w3-col l3 s6">--%>
    <%--            <div class="w3-container">--%>
    <%--                <img src="/w3images/jeans4.jpg" style="width:100%">--%>
    <%--                <p>Vintage Skinny Jeans<br><b>$14.99</b></p>--%>
    <%--            </div>--%>
    <%--            <div class="w3-container">--%>
    <%--                <img src="/w3images/jeans1.jpg" style="width:100%">--%>
    <%--                <p>Ripped Skinny Jeans<br><b>$24.99</b></p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
</div>

<!-- Subscribe section -->
<%--    <div class="w3-container w3-black w3-padding-32">--%>
<%--        <h1>Subskrybuj</h1>--%>
<%--        <p>By otrzymywać specjalne oferty oraz zyskać status klienta VIP:</p>--%>
<%--        <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail" style="width:100%"></p>--%>
<%--        <button type="button" class="w3-button w3-red w3-margin-bottom">subskrybuj</button>--%>
<%--    </div>--%>

<!-- Footer -->
<footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
    <div class="w3-row-padding">
        <div class="w3-col s4">
            <h4>Kontakt</h4>
            <p>Masz pytania? Napisz do mnie.</p>
            <form action="/action_page.php" target="_blank">
                <p><input class="w3-input w3-border" type="text" placeholder="Name" name="Imię" required></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Email" name="Email" required></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Subject" name="Temat" required></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Message" name="Treść wiadomości"
                          required></p>
                <button type="submit" class="w3-button w3-block w3-black">Wyślij</button>
            </form>
        </div>

        <div class="w3-col s4">
            <h4>Przydatne informacje</h4>
            <c:forEach var="ruleSubject" items="${rules}">
                <p><a href="/rules/${ruleSubject.name}">${ruleSubject.namePl}</a></p>
            </c:forEach>
        </div>

        <div class="w3-col s4 w3-justify">
            <h4>Dane sprzedającego</h4>
            <p><i class="fa fa-fw fa-map-marker"></i>${company.name}</p>
            <p><i class="fa fa-fw fa-phone"></i>${company.phone}</p>
            <p><i class="fa fa-fw fa-envelope"></i>${company.email}</p>
            <br>
            <h6>Media społecznościowe</h6>
            <a href="https://www.facebook.com/KoralikowaPasjonatka/"><i class="fab fa-facebook w3-hover-opacity w3-large"></i></a>
            <br><br>
            <h6>Obsługuje nas</h6>
            <i class='fab fa-dhl' style='font-size:48px'></i>
            <%--                <h4>We accept</h4>--%>
            <%--                <p><i class="fa fa-fw fa-credit-card"></i> Credit Card</p>--%>
        </div>
    </div>
</footer>

<div class="w3-black w3-center w3-padding-24">Strona stworzona przez JAVArun na podstawie szablonu <a
        href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a>
</div>

<!-- End page content -->
</div>

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

