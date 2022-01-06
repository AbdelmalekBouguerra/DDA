<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 14/08/2021
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="jquery-dynatable-master/vendor/jquery-1.7.2.min.js"></script>
<script src="jquery-dynatable-master/jquery.dynatable.js"></script>
<link rel="stylesheet" href="jquery-dynatable-master/jquery.dynatable.css">
<html>
<head>
    <%-- jQuery --%>
    <title>INSTITUT ALGERIEN DU PETROLE</title>

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
        </style>
        <script>
            $(document).ready(function() {
                $('#my-ajax-table').dynatable({
                    features: {
                            paginate: true,
                            search: true,
                            recordCount: true
                },
                    inputs: {
                        perPageText: 'Afficher: ',
                        searchText: 'RechercheR: ',
                        paginationPrev: 'Précédent',
                        paginationNext: 'Suivant',
                        recordCountText: 'Affichage',
                        recordCountPageBoundTemplate: '{pageLowerBound} de {pageUpperBound} à',

                    },
                    dataset: {
                        records: [
                            ${HIS_Table}
                        ]
                    }
                },
                );
            });
        </script>
</head>
<body>
<table id="my-ajax-table">
    <thead>
    <th>Matricule</th>
<%--    <th>Nom</th>--%>
    <th>Date</th>
    <th>Action</th>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
</html>
