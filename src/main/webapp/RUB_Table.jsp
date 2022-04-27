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
    <link rel="stylesheet" href="assets/notiflix/dist/notiflix-3.2.5.min.css"/>
<%--    <style><%@include file="/assets/notiflix/dist/notiflix-3.2.5.min.css"%></style>--%>

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
        div {
            margin-bottom: 10px;
            position: relative;
        }

        input[type="number"] {
            width: 100px;
        }

        input + span {
            padding-right: 30px;
        }

        input:invalid+span:after {
            position: absolute;
            content: '✖';
            padding-left: 5px;
        }

        input:valid+span:after {
            position: absolute;
            content: '✓';
            padding-left: 5px;
        }

        .modal-confirm {
            color: #636363;
            width: 325px;
            margin: 30px auto;
        }
        .modal-confirm .modal-content {
            padding: 20px;
            border-radius: 5px;
            border: none;
        }
        .modal-confirm .modal-header {
            border-bottom: none;
            position: relative;
        }
        .modal-confirm h4 {
            text-align: center;
            font-size: 26px;
            margin: 30px 0 -15px;
        }
        .modal-confirm .form-control, .modal-confirm .btn {
            min-height: 40px;
            border-radius: 3px;
        }
        .modal-confirm .close {
            position: absolute;
            top: -5px;
            right: -5px;
        }
        .modal-confirm .modal-footer {
            border: none;
            text-align: center;
            border-radius: 5px;
            font-size: 13px;
        }
        .modal-confirm .icon-box {
            color: #fff;
            position: absolute;
            margin: 0 auto;
            left: 0;
            right: 0;
            top: -70px;
            width: 95px;
            height: 95px;
            border-radius: 50%;
            z-index: 9;
            background: #82ce34;
            padding: 15px;
            text-align: center;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
        }
        .modal-confirm .icon-box i {
            font-size: 58px;
            position: relative;
            top: 3px;
        }
        .modal-confirm.modal-dialog {
            margin-top: 80px;
        }
        .modal-confirm .btn {
            color: #fff;
            border-radius: 4px;
            background: #82ce34;
            text-decoration: none;
            transition: all 0.4s;
            line-height: normal;
            border: none;
        }
        .modal-confirm .btn:hover, .modal-confirm .btn:focus {
            background: #6fb32b;
            outline: none;
        }
        .trigger-btn {
            display: inline-block;
            margin: 100px auto;
        }

    </style>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
</head>

<body>

${msg}

<h3>la table rub de ${month}-${year}</h3>
    Séléctionnez un document a télécharger: <br />
    <!-- HTML5 Input Form Elements -->
    <input id="ajaxfile" type="file"/> <br/>
    <button onclick="uploadFile()"> Télécharger </button>

<!-- Ajax to Java File Upload Logic -->
<!-- preloader -->
<script src="assets/notiflix/dist/notiflix-3.2.5.min.js"></script>
<script>
    async function uploadFile() {
        Notiflix.Loading.dots({
            backgroundColor: 'rgba(0,0,0,0.8)',
            svgColor: '#c68b32',
        });
        let formData = new FormData();
        formData.append("file", ajaxfile.files[0]);
        await fetch('rubupload', {
            method: "POST",
            body: formData,
        });
        window.location.reload();
    }
</script>
</script>

<table style="width: 100%">
    <tr>
        <th>Matricule</th>
        <th>DateExpl</th>
        <th>NumRub</th>
        <th>LibRub</th>
        <th>DateDeb</th>
        <th>DateFin</th>
        <th>MontantMois</th>
        <th>Taux</th>
        <th>Base</th>

    </tr>
        ${RUB_Table}
</table>


</body>
</html>
