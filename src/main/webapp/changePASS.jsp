<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 11/05/2021
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // for HTTP 1.1

    response.setHeader("Pragma","no-cache"); // HTTP 1.0

    response.setHeader("Expires","0"); // Proxies


    if (session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
<h4>entrez votre nouveau mot de passe</h4>
<form action="ChangePASS" method="post">
    Nouveau mot de pass <input type="password" name="nvpassword">
    Confirmez le mot de passe <input type="password" name="snvpassword">
<input type="submit" value="confirmer">
</form>
<p>${erreur}</p>
<a href="Attestation.jsp"> Attestation de Travail</a>
</body>
</html>