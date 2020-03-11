<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 12:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                <p><a href="${pageContext.request.contextPath}/rules/${ruleSubject.name}">${ruleSubject.namePl}</a>
                </p>
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
