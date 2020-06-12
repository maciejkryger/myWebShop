<%--
  Created by IntelliJ IDEA.
  Author: Maciej Kryger  [https://github.com/maciejkryger]
  Date: 01.03.2020
  Time: 12:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .footer-font{

    }

    .first-logo{
        font-size:48px
     }

    .second-logo{
        width:100px;
     }

    @media only screen and (max-width: 600px) {
          /* For mobile phones: */
        .footer-font{
            font-size: 10px;
        }

        .first-logo{
        font-size:36px;
        <br>
        }

        .second-logo{
        position: next-line;
        width:80px;
        }

</style>
<footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
    <div class="w3-row-padding">
        <div class="w3-col s4">
            <h4>Kontakt</h4>
            <p>Masz pytania? Napisz do mnie.</p>
                   <a class="w3-bar-item" style="color: green">${mailSentWithSuccess ? 'Wiadomość została wysłana.' :''}</a>

            <form method="post" action="${pageContext.request.contextPath}/contactMessage">
                <p><input class="w3-input w3-border" type="text" placeholder="Imię" name="name" required></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Email" name="email" required></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Temat" name="subject" required></p>
                <p><input class="w3-input w3-border" type="text" placeholder="Wiadomość" name="content"
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
            <p class="footer-font"><i class="fa fa-fw fa-envelope"></i>${company.email}</p>
            <br>
            <h6>Facebook</h6>
            <a href="https://www.facebook.com/KoralikowaPasjonatka/"><i
                    class="fab fa-facebook w3-hover-opacity w3-large"></i></a>
            <br><br>
            <h6>Obsługuje nas</h6>
            <table>
            <tr>
            <th><i class='fab fa-dhl first-logo'></i></th>
            <th><img src='https://secure.sitebees.com/file/mediakit/1449812/69/PP_logo_przezrocz+achrom.png' class='second-logo'></th>
            </tr>
            </table>
            <%--                <h4>We accept</h4>--%>
            <%--                <p><i class="fa fa-fw fa-cc-amex"></i> Amex</p>--%>
            <%--                <p><i class="fa fa-fw fa-credit-card"></i> Credit Card</p>--%>

        </div>
    </div>
</footer>
