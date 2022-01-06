<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<nav lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/static.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <title>INSTITUT ALGERIEN DU PETROLE</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/material-dashboard-rtl.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/material-dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/material-dashboard.css.map">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/material-dashboard.min.css">
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
    <script>
        window.onload = function () {
            CanvasJS.addColorSet("greenShades",
                [//colorSet Array

                    "#3ed0d0",
                    "#9f37d5",
                    "#c4d937",
                    "#ef3657",
                    "#90EE90"
                ]);
            CanvasJS.addCultureInfo("fr", {
                decimalSeparator: ",", // Observe ToolTip Number Format
                digitGroupSeparator: ".", // Observe axisY labels
                months: [
                    "janvier",
                    "février",
                    "mars",
                    "avril",
                    "mai",
                    "juin",
                    "juillet",
                    "août",
                    "septembre",
                    "octobre",
                    "novembre",
                    "décembre",
                ],
                days: [
                    "lundi",
                    "mardi",
                    "mercredi",
                    "jeudi",
                    "vendredi",
                    "samedi",
                    "dimanche",
                ],
            });

            var chart = new CanvasJS.Chart("chartContainer", {
                colorSet: "greenShades",
                animationEnabled: true,
                animationDuration: 4000,
                culture: "fr",
                backgroundColor: "rgba(0, 0, 0, 0)",
                theme: "dark2",
                title: {
                    text: "Nombre De Téléchargements Des Documents",
                    fontColor: "#FFFFFF"
                },
                axisX: {
                    valueFormatString: "MMMM",
                    interval: 1,
                    intervalType: "month",
                    titleFontColor: "#FFFFFF"
                },
                axisY: {
                    title: "Nombre de téléchargements",
                    titleFontColor: "#FFFFFF"
                },
                legend: {
                    cursor: "pointer",
                    fontSize: 16,
                    itemclick: toggleDataSeries,
                },
                toolTip: {
                    shared: true,
                },
                data: [
                    {
                        name: "ATS",
                        type: "spline",
                        yValueFormatString: "#0.## Téléchargements",

                        showInLegend: true,
                        dataPoints: [
                            {x: new Date(2021, 0, 1), y: 6},
                            {x: new Date(2021, 1, 1), y: 11},
                            {x: new Date(2021, 2, 1), y: 10},
                            {x: new Date(2021, 3, 1), y: 4},
                            {x: new Date(2021, 4, 1), y: 2},
                            {x: new Date(2021, 5, 1), y: 3},
                            {x: new Date(2021, 6, 1), y: 14},
                            {x: new Date(2021, 7, 1), y: 7},
                            {x: new Date(2021, 8, 1), y: 5},
                            {x: new Date(2021, 9, 1), y: 4},
                            {x: new Date(2021, 10, 1), y: 4},
                            {x: new Date(2021, 11, 1), y: 14},
                        ],
                    },
                    {
                        name: "BP",
                        type: "spline",
                        yValueFormatString: "#0.## Téléchargement",
                        xValueFormatString: "MMMM",
                        showInLegend: true,
                        dataPoints: [
                            {x: new Date(2021, 0, 1), y: 5},
                            {x: new Date(2021, 1, 1), y: 13},
                            {x: new Date(2021, 2, 1), y: 7},
                            {x: new Date(2021, 3, 1), y: 3},
                            {x: new Date(2021, 4, 1), y: 6},
                            {x: new Date(2021, 5, 1), y: 11},
                            {x: new Date(2021, 6, 1), y: 20},
                            {x: new Date(2021, 7, 1), y: 8},
                            {x: new Date(2021, 8, 1), y: 9},
                            {x: new Date(2021, 9, 1), y: 6},
                            {x: new Date(2021, 10, 1), y: 5},
                            {x: new Date(2021, 11, 1), y: 12},
                        ],
                    },
                    {
                        name: "RE",
                        type: "spline",
                        yValueFormatString: "#0.## Téléchargement",
                        xValueFormatString: "MMMM",
                        showInLegend: true,
                        dataPoints: [
                            {x: new Date(2021, 0, 1), y: 10},
                            {x: new Date(2021, 1, 1), y: 12},
                            {x: new Date(2021, 2, 1), y: 6},
                            {x: new Date(2021, 3, 1), y: 2},
                            {x: new Date(2021, 4, 1), y: 5},
                            {x: new Date(2021, 5, 1), y: 10},
                            {x: new Date(2021, 6, 1), y: 2},
                            {x: new Date(2021, 7, 1), y: 5},
                            {x: new Date(2021, 8, 1), y: 6},
                            {x: new Date(2021, 9, 1), y: 5},
                            {x: new Date(2021, 10, 1), y: 2},
                            {x: new Date(2021, 11, 1), y: 3},
                        ],
                    },
                    {
                        name: "ATS CNAS",
                        type: "spline",
                        yValueFormatString: "#0.## Téléchargement",
                        xValueFormatString: "MMMM",
                        showInLegend: true,
                        dataPoints: [
                            {x: new Date(2021, 0, 1), y: 4},
                            {x: new Date(2021, 1, 1), y: 5},
                            {x: new Date(2021, 2, 1), y: 3},
                            {x: new Date(2021, 3, 1), y: 1},
                            {x: new Date(2021, 4, 1), y: 5},
                            {x: new Date(2021, 5, 1), y: 1},
                            {x: new Date(2021, 6, 1), y: 4},
                            {x: new Date(2021, 7, 1), y: 2},
                            {x: new Date(2021, 8, 1), y: 6},
                            {x: new Date(2021, 9, 1), y: 2},
                            {x: new Date(2021, 10, 1), y: 10},
                            {x: new Date(2021, 11, 1), y: 12},
                        ],
                    },
                ],
            });

            var chart1 = new CanvasJS.Chart("chart", {
                theme: "dark1", // "light1", "light2", "dark1", "dark2"
                culture: "fr",
                backgroundColor: "rgba(0, 0, 0, 0)",
                animationEnabled: true,
                title: {
                    text: "Nombre de téléchargements par structure"
                },
                data: [{
                    type: "pie",
                    startAngle: 25,
                    toolTipContent: "<b>{label}</b>: {y}%",
                    showInLegend: "true",
                    legendText: "{label}",
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}%",
                    dataPoints: [
                        { y: 51.08, label: "DGP" },
                        { y: 27.34, label: "EBM" },
                        { y: 10.62, label: "EHM" },
                        { y: 5.02, label: "EAZ" },
                        { y: 4.07, label: "ESK" }
                    ]
                }]
            });

            chart.render();
            chart1.render();


            function toggleDataSeries(e) {
                if (
                    typeof e.dataSeries.visible === "undefined" ||
                    e.dataSeries.visible
                ) {
                    e.dataSeries.visible = false;
                } else {
                    e.dataSeries.visible = true;
                }
                chart.render();
            }
        };
    </script>
    <body id="scroller" class="data-scroll-container">

    <nav>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/page_accueil/Layer0.png" class="navlogo">
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
<div class="wrapper">
    <div class="content">
        <div class="container-fluid">
            <!-- Content here -->
            <div class="row">
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-warning card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">Connexion</i>
                            </div>
                            <p class="card-category">Connexions par mois</p>
                            <h3 class="card-title">
                                ${LoginToday}
                                <small>Utilisateurs</small>
                            </h3>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-success card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">Téléchargement des documents</i>
                            </div>
                            <p class="card-category">Téléchargements par mois</p>
                            <h3 class="card-title">
                                ${totalDownload}
                                <small>fichiers</small>
                            </h3>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-warning card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">file_download</i>
                            </div>
                            <p class="card-category">Attestation de travail</p>
                            <h3 class="card-title">
                                ${ATSDoToday}
                                <small>uploads</small>
                            </h3>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-danger card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">file_download</i>
                            </div>
                            <p class="card-category">Bulletin de paie</p>
                            <h3 class="card-title">
                                ${PBDoToday}
                                <small>uploads</small>
                            </h3>

                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-danger card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">file_download</i>
                            </div>
                            <p class="card-category">Relevé des émoluments</p>
                            <h3 class="card-title">
                                ${REDoToday}
                                <small>uploads</small>
                            </h3>

                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-danger card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">file_download</i>
                            </div>
                            <p class="card-category">Relevé des émoluments détaillé</p>
                            <h3 class="card-title">
                                ${REDDoToday}
                                <small>uploads</small>
                            </h3>

                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="card card-stats">
                        <div class="card-header card-header-danger card-header-icon">
                            <div class="card-icon">
                                <i class="material-icons">file_download</i>
                            </div>
                            <p class="card-category">Attestation de travail cnas</p>
                            <h3 class="card-title">
                                ${ATCDoToday}
                                <small>uploads</small>
                            </h3>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="card card-chart">
                        <div class="card-header card-header-success">
                            <!-- style="height: 300px; width: 700px" -->
                            <div class="ct-chart" id="chartContainer" style="height: 300px; width: 100%"></div>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Téléchargements par mois</h4>
                            <p class="card-category">
                                Nombre de téléchargements pour l'année 2021
                            </p>
                        </div>
                        </div>
                    </div>
                <div class="col-md-4">
                    <div class="card card-chart">
                        <div class="card-header card-header-success">
                            <!-- style="height: 300px; width: 700px" -->
                            <div class="ct-chart" id="chart" style="height: 300px; width: 100%"></div>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Téléchargements par mois</h4>
                            <p class="card-category">
                                Nombre de téléchargements pour l'année 2021
                            </p>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
    </div>





</body>