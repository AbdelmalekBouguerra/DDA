<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/gerer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/jquery.dynatable.css">
    <script src="${pageContext.request.contextPath}/page_accueil/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/page_accueil/jquery.dynatable.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
          rel="stylesheet">
    <title>INSTITUT ALGERIEN DU PETROLE</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/page_accueil/Layer2.png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


</head>


<body id="scroller" class="data-scroll-container">

<nav>
    <div class="logo">
        <a href="accueil">
            <img src="${pageContext.request.contextPath}/page_accueil/Layer0.png" class="navlogo">
        </a>
    </div>

    <div class="profile" onclick="menuToggle();">
        <img src="${pageContext.request.contextPath}/page_accueil/male_user_65px.png" alt="">
    </div>

    <div class="menu">
        <h3>
            ${user} Account
            <div>
                ${Fonction}
            </div>
        </h3>


        <ul>

            ${admin}

            <li>
                <span class="material-icons icons-size">lock</span>
                <a href="Logout">Déconnexion</a>
            </li>

        </ul>

    </div>

    <script>
        function menuToggle() {
            const toggleMenu = document.querySelector('.menu');
            toggleMenu.classList.toggle('active')
        }
    </script>
</nav>


<div class="container-fluid p-0">
    </style>
    <script>
        $(document).ready(function () {
            $('#my-ajax-table').dynatable({
                    features: {
                        paginate: true,
                        search: true,
                        recordCount: true
                    },
                    inputs: {
                        perPageText: 'Afficher: ',
                        searchText: 'Rechercher: ',
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

    <table id="my-ajax-table">
        <thead>
        <th>Matricule</th>
        <%--<th>Nom</th>--%>
        <th>Date</th>
        <th>Action</th>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>