<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 30/04/2022
  Time: 7:04 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- jquery.fancybox  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.fancybox.css">
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
        <th>Son</th>
        <th>Matricule</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    ${USER_Table}
</table>

<button onclick="addAdmin(this);" data-toggle="modal" data-target="#exampleModal">Ajouter</button>


<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <form id="form" method="get" action="EditAdmin">
                <div class="modal-body">
                    <input type="text" name="SonInput" id="SonInput" required>
                    <br>
                    <select name="role">
                        <option value="Admin_DGP">Admin_DGP</option>
                        <option value="Admin_EBM">Admin_EBM</option>
                        <option value="Admin_EAZ">Admin_EAZ</option>
                        <option value="Admin_EHM">Admin_EHM</option>
                        <option value="Admin_ESK">Admin_ESK</option>
                    </select>
                    ${invalide}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.1.min.js"></script>

<!-- ------------------------------- DONT DELETE ------------------------------------------- -->
<script>
    function showValue(el) {
        console.log(el.value);
        document.getElementById("SonInput").value = el.value;
        document.getElementById("SonInput").readOnly = true;
    }

    function addAdmin(el) {
        document.getElementById("SonInput").readOnly = false;
        document.getElementById("SonInput").value = "";

    }
</script>
<!-- ------------------------------- DONT DELETE ------------------------------------------- -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
