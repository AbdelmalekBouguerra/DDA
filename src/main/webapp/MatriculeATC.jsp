<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 04/10/2021
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
</head>
<body>
<form method="get" action="Matricule">
    <input name="type" type="hidden" value="ATCmat">
<label for="mat"> Entrez le matricule pour créer l'attestion de travail CNAS:</label>
    <label for="mat"> </label>
    <br>
<input type="text" id="mat" name="mat" required
       minlength="6" maxlength="6" size="6">
    <button type="submit"> Envoyer </button>
    ${invalidUname}

</form>
</body>
</html>
