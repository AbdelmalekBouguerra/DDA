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
<h3>Téléchargez un document</h3>
Séléctionnez le document a télécharger: <br />
<!-- HTML5 Input Form Elements -->
<input id="ajaxfile" type="file"/> <br/>
<button onclick="uploadFile()"> Télécharger </button>

<!-- Ajax to Java File Upload Logic -->
<script>
    async function uploadFile() {
        let formData = new FormData();
        formData.append("file", ajaxfile.files[0]);
        await fetch('strupload', {
            method: "POST",
            body: formData
        });
        window.location.reload();
    }
</script>

<table style="width: 100%">
    <tr>
        <th>CodeStr</th>
        <th>Structure</th>
    </tr>
        ${STR_Table}
</table>

</body>
</html>
