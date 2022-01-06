<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 26/07/2021
  Time: 7:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
</head>
<body>
<h1>VOLET ADMINISTRATEUR</h1>
<%
  response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // for HTTP 1.1

  response.setHeader("Pragma","no-cache"); // HTTP 1.0

  response.setHeader("Expires","0"); // Proxies


  if (session.getAttribute("username") == null){
    response.sendRedirect("index.jsp");
  }

  if (session.getAttribute("admin").equals("SuperAdmin")){
    out.print("<p>");
    out.print("SuperAdmin");
    out.print("</p>");

  }else if(session.getAttribute("admin").equals("AdminPaie")) {
    out.print("<p>");
    out.print("AdminPaie");
    out.print("</p>");
  }else if (session.getAttribute("admin").equals("AdminPers")){
    out.print("<p>");
    out.print("AdminPers");
    out.print("</p>");
  } else {
    response.sendRedirect("index.jsp");
  }
  if (session.getAttribute("admin").equals("SuperAdmin")){
    out.print("<br/>" +
            "<a href=\"PrintUSER\">USER</a>\n");
    out.print("<br/>" +
            "<a href=\"PrintSTR\"> STR Table</a>\n");
    out.print("<br/>" +
            "<a href=\"PrintLOC\"> LOC Table</a>\n");
    out.print("<br/>" +
            "<a href=\"PERSmonth.jsp\"> PERS Table</a>\n");
  out.print("<br/>" +
          "<a href=\"RUBmonth.jsp\"> RUB Table</a>\n");
    out.print("<br/>" +
            "<a href=\"PrintHIS\">Historique</a>\n");
    out.print("<br/>" +
            "<a href=\"PrintSTAT\">Statistics</a>\n");

  }
%>
</body>
</html>
