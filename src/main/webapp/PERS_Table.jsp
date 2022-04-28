<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="utf-8">
    <!-- Always force latest IE rendering engine or request Chrome Frame -->
    
    <!-- Page Title -->
    <link rel="shortcut icon" type="image/png" href="assets/img2/Layer 2.png">
    <title> DOC IAP SONATRACH</title>
    <!-- Meta Description -->
    <meta name="description" content="Blue One Page Creative HTML5 Template">
    <meta name="keywords" content="one page, single page, onepage, responsive, parallax, creative, business, html5, css3, css3 animation">

    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Google Font -->
    <link href="css/font.css" rel="stylesheet">
    <link href="css/font1.css" rel="stylesheet">
    <!-- CSS-->
    <!-- Fontawesome Icon font -->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/all.css">
    <!-- Twitter Bootstrap css -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!--jquery.fancybox  -->
    <!-- animate -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="assets/css/tabtry.css">
    <script src="assets/js/jquery-1.7.2.min.js"></script>
    <script src="assets/js/jquery.dynatable.js"></script>
   <!-- <script src="assets/js/tabdoc.js"></script>-->
    <link rel="stylesheet" href="assets/css/jquery.dynatable.css">
    <!-- notiflix -->
    <link rel="stylesheet" href="assets/notiflix/dist/notiflix-3.2.5.min.css"/>

</head>


<body id="body">

    <!-- preloader -->
    <div id="preloader">
        <img src="assets/img2/sona-load.gif" alt="Preloader">
    </div>
    <!-- end preloader -->

    <!-- 
        Fixed Navigation
        ==================================== -->
    <header id="navigation" class="navbar-fixed-top navbar">
        <div class="container">
            <div class="navbar-header">
                <!-- responsive nav button -->

                <!-- /responsive nav button -->
                <!-- logo -->
                <a class="navbar-brand" href="#body">
                    <h1 id="logo">
                        <img class="g" src="assets/img2/logo-iap.png">
                    </h1>
                </a>
                <!-- /logo -->
            </div>

            <nav>
                <div class="profile" onclick="menuToggle();">
                    <img src="${pageContext.request.contextPath}/assets/img2/male_user_65px.png" alt="">
                </div>

                <div class="menu">
                    <h3>
                      Compte:  ${name} ${user} 
                        <div>
                            ${Fonction}
                        </div>
                    </h3>


                    <ul>

                        ${admin}

                        <li>
                            <span class="material-icons icons-size">lock</span>
                            <a href="Logout">D&eacute;connexion</a>
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


        </div>
    </header>

    <!-- =========================================Home Slider ==================================== -->

    <section id="slider">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">



            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">

                <!-- single slide -->
                <div class="item active" style="background-color:rgb(243, 243, 243);">


                    <input id="ajaxfile" type="file" accept=".xls, .xlsx" required/> <br/>
                    <button onclick="uploadFile()"> T&eacute;l&eacute;charger</button>
                    <br />

                    <script src="assets/notiflix/dist/notiflix-3.2.5.min.js"></script>
                    <script>
                            async function uploadFile() {
                                Notiflix.Loading.dots({
                                    backgroundColor: 'rgba(0,0,0,0.8)',
                                    svgColor: '#c68b32',
                                });
                                let formData = new FormData();
                                formData.append("file", ajaxfile.files[0]);
                                    await fetch("persupload", {
                                        method: "POST",
                                        body: formData,
                                    })
                            .then(async response => {
                                    const isJson = response.headers.get('content-type')?.includes('application/json');
                                    const data = isJson ? await response.json() : null;

                                    // check for error response
                                    if (!response.ok) {
                                        // get error message from body or default to response status
                                        const error = (data && data.message) || response.status;
                                        Notiflix.Report.failure(
                                            'Error',
                                            'Veuillez verifier si vous avez selectionne le bon fichier ou si le fichier ne contient pas de cases vides',
                                            'D\'accord',
                                            function cb() {
                                                window.location.reload();
                                            },
                                        );
                                        return Promise.reject(error);
                                    } else {
                                        window.location.reload();
                                    }
                                })
                            }
                    </script>
                     <script>
                        $(document).ready(function() {
                            $("#my-ajax-table").dynatable({
                                features: {
                                    paginate: true,
                                    //search: true,
                                    recordCount: false,
                                },
                                inputs: {
                                    perPageText: "Afficher: ",
                                    //searchText: "Rechercher: ",
                                    
                                    paginationPrev: "Pr&eacute;cedent",
                                    paginationNext: "Suivant",
                                    
                                    recordCountPageBoundTemplate: "{pageLowerBound} de {pageUpperBound} à",
                                },
                                dataset: {
                                    records: [
                                        ${PERS_Table}
                                       
                                    ],
                                },
                            });
                        });
                    </script>
                    <br> <br>
                   
                    <table id="my-ajax-table">
                        <thead>
                            <th>Matricule</th>
                            <th>Nom</th>
                            
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
                           <!-- <th>NSSAGT</th>
                            <th>NSSEMP</th>
                            <th>CPAIEM</th>-->
                            <th>Echelle</th>
                            <th>Groupe</th>
                            <th>SalBase</th>
                            <th>IAG</th>
                        </thead>
                    </table>


                </div>

            </div>

        </div>
        <!-- End Wrapper for slides -->

        </div>
    </section>



    <!-- Essential jQuery Plugins
		================================================== -->
    <!-- Main jQuery -->

    <!-- Single Page Nav -->
    <script src="assets/js/jquery.singlePageNav.min.js "></script>
    <!-- Twitter Bootstrap -->
    <script src="assets/js/bootstrap.min.js "></script>
    <!-- jquery.fancybox.pack -->
    <script src="assets/js/jquery.fancybox.pack.js "></script>
    <!-- jquery.mixitup.min -->
    <script src="assets/js/jquery.mixitup.min.js "></script>
    <!-- jquery.parallax -->
    <script src="assets/js/jquery.parallax-1.1.3.js "></script>
    <!-- jquery.countTo -->
    <script src="assets/js/jquery-countTo.js "></script>
    <!-- jquery.appear -->
    <script src="assets/js/jquery.appear.js "></script>
    <!-- Contact form validation -->
    <script src="js/jquery.form.js "></script>
    <script src="js/jquery.validate.min.js "></script>
    <!-- Google Map -->
    <script type="text/javascript " src="js/maps.js"></script>
    <!-- jquery easing -->
    <script src="assets/js/jquery.easing.min.js "></script>
    <!-- jquery easing -->
    <script src="assets/js/wow.min.js "></script>
    <script>
        var wow = new WOW({
            boxClass: 'wow', // animated element css class (default is wow)
            animateClass: 'animated', // animation css class (default is animated)
            offset: 120, // distance to the element when triggering the animation (default is 0)
            mobile: false, // trigger animations on mobile devices (default is true)
            live: true // act on asynchronously loaded content (default is true)
        });
        wow.init();
    </script>
    <!-- Custom Functions -->
    <script src="assets/js/custom.js "></script>
    <script src="assets/js/typed.js "></script>
    <script src="assets/js/click.js "></script>

</body>

</html>