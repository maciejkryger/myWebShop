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

        <h2>Edycja produktu</h2>
        <div class="w3-responsive">

            <table class="w3-table-all w3-hoverable">
                <form method="get" action="${pageContext.request.contextPath}/panels/data/products/">
                      <input type="hidden" name="searchWhat" value="${param.searchWhat}">
                      <input type="hidden" name="searchBy" value="${param.searchBy}">
                      <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="anuluj">
                </form>
                <form method="post" action="${pageContext.request.contextPath}/panels/data/product/save"
                      modelAttribute="product">
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>
                    <input type="hidden" name="searchWhat" value="${param.searchWhat}">
                    <input type="hidden" name="searchBy" value="${param.searchBy}">
                    <p>
                        <label><b>id: ${product.id}</b></label>
                        <input type="hidden" name="id" placeholder="id" class="w3-input w3-border"
                               value="${product.id}">
                    </p>
                    <p>
                        <label><b>nazwa po polsku</b></label>
                        <input type="text" name="namePl" placeholder="nazwa po polsku" class="w3-input w3-border"
                               value="${product.namePl}">
                    </p>
                    <p>
                        <label><b>nazwa po angielsku</b></label>
                        <input type="text" name="name" placeholder="nazwa po angielsku" class="w3-input w3-border"
                        value="${product.name}">
                    </p>
                    <p>
                        <label><b>typ</b></label>
                        <select class="w3-input w3-border" name="typeId">
                            <option selected="selected" value="${product.type.id}">${product.type.namePl}</option>
                            <c:forEach var="item" items="${types}">
                                <option value="${item.id}">${item.namePl}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label><b>technika</b></label>
                        <select class="w3-input w3-border" name="makingTechniqueId">
                            <option selected="selected" placeholder="technika"
                                    value="${product.makingTechnique.id}">${product.makingTechnique.namePl}</option>
                            <c:forEach var="item" items="${makingTechniques}">
                                <option value="${item.id}">${item.namePl}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label><b>materiał</b></label>
                        <select class="w3-input w3-border" name="materialId">
                            <option selected="selected" placeholder="materiał"
                                    value="${product.material.id}">${product.material.namePl}</option>
                            <c:forEach var="item" items="${materials}">
                                <option value="${item.id}">${item.namePl}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label><b>kolor materiału</b></label>
                        <select class="w3-input w3-border" name="materialColorId">
                            <option selected="selected" placeholder="kolor"
                                    value="${product.materialColor.id}">${product.materialColor.namePl}</option>
                            <c:forEach var="materialColor" items="${materialColors}">
                                <option value="${materialColor.id}">${materialColor.namePl}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label><b>zapięcie</b></label>
                        <select class="w3-input w3-border" name="fasteningTypeId">
                            <option selected="selected" placeholder="zapięcie"
                                    value="${product.fasteningType.id}">${product.fasteningType.namePl}</option>
                            <c:forEach var="item" items="${fasteningTypes}">
                                <option value="${item.id}">${item.namePl}</option>
                            </c:forEach>
                        </select>
                    </p>
        <!--            <p>
                        <label><b>kolor zapięcia</b></label>
                        <select class="w3-input w3-border" name="fasteningColorId">
                            <option selected="selected" placeholder="kolor zapięcia"
                                    value="${product.fasteningColor.id}">${product.fasteningColor.namePl}</option>
                            <c:forEach var="item" items="${fasteningColors}">
                                <option value="${item.id}">${item.namePl}</option>
                            </c:forEach>
                        </select>
                    </p> -->
                    <p>
                        <label><b>długość [mm]</b></label>
                        <input type="text" name="length" placeholder="długość" class="w3-input w3-border"
                               value="${product.length}">
                    </p>
                    <p>
                        <label><b>szerokość [mm]</b></label>
                        <input type="text" name="width" placeholder="szerokość" class="w3-input w3-border"
                               value="${product.width}">
                    </p>
                    <p>
                        <label><b>cena [PLN]</b></label>
                        <input type="text" name="price" placeholder="cena" class="w3-input w3-border"
                               value="${product.price}">
                    </p>
                    <p>
                        <label><b>opis po polsku</b></label>
                        <input type="text" name="descriptionPl" placeholder="opis po polsku" class="w3-input w3-border"
                               value="${product.descriptionPl}">
                    </p>
                    <p>
                        <label><b>opis po angielsku</b></label>
                        <input type="text" name="description" placeholder="opis opis po angielski" class="w3-input w3-border"
                            value="${product.description}">
                    </p>
                    <p>
                        <label><b>Czy aktywny</b></label>
                        <select class="w3-input w3-border" name="active">
                          <option selected="selected" value="${product.active}">${product.active}</option>
                          <option value="${product.active}">${!product.active}</option>
                        </select>
                    </p>
                    <p>
                        <label><b>Produkt główny</b></label>
                        <select class="w3-input w3-border" name="mainProductId">
                             <option selected="selected" placeholder="główny produkt"
                                     value="${product.mainProduct.id}">${product.mainProduct.id}</option>
                             <c:forEach var="item" items="${mainProducts}">
                                 <option value="${item.id}">${item.id}. ${item.namePl}</option>
                             </c:forEach>
                         </select>
                    </p>
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="zapisz"/>
                </form>
                <form method="get" action="${pageContext.request.contextPath}/panels/data/products/">
                    <input type="hidden" name="searchWhat" value="${param.searchWhat}">
                    <input type="hidden" name="searchBy" value="${param.searchBy}">
                    <input type="submit" class="w3-button w3-white w3-border w3-round-large" value="anuluj">
                </form>

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

