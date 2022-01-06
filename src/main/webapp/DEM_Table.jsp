<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 17/07/2021
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // for HTTP 1.1

    response.setHeader("Pragma","no-cache"); // HTTP 1.0

    response.setHeader("Expires","0"); // Proxies


    if (session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }

    if (session.getAttribute("admin") == null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
</head>

<body>
<table style="width: 100%">
    <tr>
        <th>ID</th>
        <th>Matricule</th>
        <th>Date</th>
        <th>termine</th>
    </tr>
        ${DEM_Table}
</table>

</body>
</html>
