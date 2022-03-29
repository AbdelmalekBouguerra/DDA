<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // for HTTP 1.1

  response.setHeader("Pragma", "no-cache"); // HTTP 1.0

  response.setHeader("Expires", "0"); // Proxies


  if (session.getAttribute("username") == null) {
    response.sendRedirect("index.jsp");
  }

  if (session.getAttribute("admin") == null) {
    response.sendRedirect("index.jsp");
  }
%>
<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <script src="jquery-dynatable-master/vendor/jquery-1.7.2.min.js"></script>
    <script src="jquery-dynatable-master/jquery.dynatable.js"></script>
    <link
      rel="stylesheet"
      href="jquery-dynatable-master/jquery.dynatable.css"
    />

    <style>
      table,
      th,
      td {
        border: 1px solid black;
        border-collapse: collapse;
      }

      th,
      td {
        padding: 5px;
        text-align: left;
      }
    </style>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
  </head>

  <body>

  ${msg}

    <h3>Téléchargez  un document</h3>
    Sélectionnez le document a télécharger: <br />
    <!-- HTML5 Input Form Elements -->
    <input id="ajaxfile" type="file" /> <br />
    <button onclick="uploadFile()">Télécharger</button>

    <!-- Ajax to Java File Upload Logic -->
  <!-- end preloader -->
    <script>
      async function uploadFile() {
        let formData = new FormData();
        formData.append("file", ajaxfile.files[0]);
        await fetch("persupload", {
          method: "POST",
          body: formData,
        });
        window.location.reload();
      }
    </script>
    <script>
      $(document).ready(function () {
        $("#my-ajax-table").dynatable({
          features: {
            paginate: true,
            search: true,
            recordCount: true,
          },
          inputs: {
            perPageText: "Afficher: ",
            searchText: "Rechercher: ",
            paginationPrev: "Précédent",
            paginationNext: "Suivant",
            recordCountText: "Affichage",
            recordCountPageBoundTemplate:
              "{pageLowerBound} de {pageUpperBound} à",
          },
          dataset: {
            records: [
              ${PERS_Table}
            ],
          },
        });
      });
    </script>
    <table id="my-ajax-table">
      <thead>
        <th>Matricule</th>
        <th>Nom</th>
        <th>STR</th>
        <th>CodeLieuNais</th>
        <th>DateNais</th>
        <th>SEXE</th>
        <th>DateRec</th>
        <th>Fonction</th>
        <th>Adresse</th>
        <th>GSang</th>
        <th>SF</th>
        <th>SCJT</th>
        <th>NbrEnfS10</th>
        <th>NbrEnfM10</th>
        <th>RIB</th>
        <th>NSSAGT</th>
        <th>NSSEMP</th>
        <th>CPAIEM</th>
        <th>Echelle</th>
        <th>Groupe</th>
        <th>SalBase</th>
        <th>IAG</th>

      </thead>
      <tbody></tbody>
    </table>
  </body>
</html>
