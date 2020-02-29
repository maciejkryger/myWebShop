<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>qunsztowna.pl</title>
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
        <a href="${pageContext.request.contextPath}/"><h3 class="w3-wide"><b>QUNSZTOWNA</b></h3></a>
    </div>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <c:forEach var="productType" items="${productTypesList}">
            <a href="${pageContext.request.contextPath}/types/${productType.name}" class="w3-bar-item w3-button">${productType.namePl}</a>
        </c:forEach>
    </div>
    <a href="#footer" class="w3-bar-item w3-button w3-padding">Kontakt</a>
<%--    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"--%>
<%--       onclick="document.getElementById('newsletter').style.display='block'">Newsletter</a>--%>
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
        <p class="w3-left">${productType.namePl}</p>
        <p class="w3-right">
            <c:if test="${sessionScope.user.enabled}">
                <a class="w3-bar-item w3-padding w3-margin-right">Witaj <b>${sessionScope.user.firstName}</b>!!!</a>
            </c:if>
            <a class="w3-bar-item w3-padding w3-margin-right">${error ?  'Błędny użytkownik lub hasło' :''}</a>
            <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"
               onclick="document.getElementById('login').style.display='block'"><i
                    class="fa fa-user"></i></a>
            <i class="fa fa-search"></i>
            <i class="fa fa-shopping-cart"></i>
        </p>
    </header>

    <!-- Image header -->
    <div class="w3-display-container w3-container">
        <img src="${pageContext.request.contextPath}/images/type${productType.id}.jpg" alt="${productType.namePl}" style="width:100%">
        <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">
            <p><a href="#products" class="w3-button w3-black w3-padding-large w3-large">PRZEJDŹ DO PRODUKTÓW</a></p>
        </div>
    </div>

    <div class="w3-container w3-text-grey" id="products">
        <c:if test="${productsCounter==0}">
            <p>${productsCounter} pozycji</p>
        </c:if>
        <c:if test="${productsCounter==1}">
            <p>${productsCounter} pozycja</p>
        </c:if>
        <c:if test="${productsCounter>1}&&${productsCounter<=4}">
            <p>${productsCounter} pozycje</p>
        </c:if>
        <c:if test="${productsCounter>4}">
            <p>${productsCounter} pozycji</p>
        </c:if>
    </div>

    <!-- Product grid -->
    <div class="w3-row w3-grayscale">
        <c:forEach var="item" items="${products}">
            <div class="w3-col l3 s6">
                <div class="w3-container">
                    <img src="${pageContext.request.contextPath}/images/${productType.id}/${item.id}.jpg" style="width:100%">
                    <p><a href="${pageContext.request.contextPath}/details/${item.id}">${item.name}<br><b>${item.price} PLN</b></a></p>
                </div>
            </div>
        </c:forEach>
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
                    <p><input class="w3-input w3-border" type="text" placeholder="Imię" name="Imię" required></p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Email" name="Email" required></p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Temat" name="Temat" required></p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Wiadomość" name="Treść wiadomości"
                              required></p>
                    <button type="submit" class="w3-button w3-block w3-black">Wyślij</button>
                </form>
            </div>

            <div class="w3-col s4">
                <h4>Przydatne informacje</h4>
                <c:forEach var="ruleSubject" items="${rules}">
                    <p><a href="${pageContext.request.contextPath}/rules/${ruleSubject.name}">${ruleSubject.namePl}</a></p>
                </c:forEach>
            </div>

            <div class="w3-col s4 w3-justify">
                <h4>Dane kontaktowe</h4>
                <p><i class="fa fa-fw fa-map-marker"></i>${company.name}</p>
                <p><i class="fa fa-fw fa-phone"></i>${company.phone}</p>
                <p><i class="fa fa-fw fa-envelope"></i>${company.email}</p>
                <br>
                <h6>Facebook</h6>
                <a href="https://www.facebook.com/KoralikowaPasjonatka/"><i
                        class="fab fa-facebook w3-hover-opacity w3-large"></i></a>
                <br><br>
                <h6>Obsługuje nas</h6>
                <i class='fab fa-dhl' style='font-size:48px'></i>
                <%--                <h4>We accept</h4>--%>
                <%--                <p><i class="fa fa-fw fa-cc-amex"></i> Amex</p>--%>
                <%--                <p><i class="fa fa-fw fa-credit-card"></i> Credit Card</p>--%>

            </div>
        </div>
    </footer>

    <div class="w3-black w3-center w3-padding-24">Strona stworzona przez JAVArun na podstawie szablonu <a
            href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a>
    </div>

    <!-- End page content -->
</div>

<!-- Login Modal -->
<div id="login" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
        <div class="w3-container w3-white w3-center">
            <i onclick="document.getElementById('login').style.display='none'"
               class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>
            <c:if test="${sessionScope.user.enabled}">
                <p class="w3-bar-item w3-padding">Jesteś zalogowany jako: ${sessionScope.user.username}</p>
                <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button">wyloguj</a>
            </c:if>
            <c:if test="${!sessionScope.user.enabled}">
                <h2 class="w3-wide">Logowanie</h2>
                <p>zaloguj się</p>
                <form method="post" action="<c:url value='${pageContext.request.contextPath}/login' />">
                    <p><input class="w3-input w3-border" type="text" name="username" placeholder="Wpisz login"></p>
                    <p><input class="w3-input w3-border" type="password" name="password" placeholder="Wpisz hasło"></p>
                    <button type="submit" class="w3-button w3-padding-large w3-red w3-margin-bottom"
                            onclick="document.getElementById('login').style.display='none'">Zaloguj
                    </button>
                </form>
            </c:if>
        </div>
    </div>
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

