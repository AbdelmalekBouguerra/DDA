<%
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // for HTTP 1.1

        response.setHeader("Pragma","no-cache"); // HTTP 1.0

        response.setHeader("Expires","0"); // Proxies


        if (session.getAttribute("username") == null){
            response.sendRedirect("index.jsp");
        }

        if (session.getAttribute("admin").equals("SuperAdmin")
        || session.getAttribute("admin").equals("AdminPaie")
        || session.getAttribute("admin").equals("AdminPers")){

        } else {
            response.sendRedirect("accueil.jsp");
        }
        %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/adminP.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/locomotive.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Concert+One&family=Erica+One&display=swap" rel="stylesheet">
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/page_accueil/Layer2.png">
    <title>INSTITUT ALGERIEN DU PETROLE</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


</head>



<body id="scroller" class="data-scroll-container">

    <nav>
        <div class="logo">
            <a href="accueil">
                <img src="${pageContext.request.contextPath}/assets/img2/logo-iap.png" class="navlogo">
            </a>
        </div>

        <div class="profile" onclick="menuToggle();">
            <img src="${pageContext.request.contextPath}/page_accueil/male_user_65px.png" alt="">
        </div>

        <div class="menu">
            <h3>
                ${user} Compte
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


    <div class="container-fluid p-0">

        <section id="home-row" data-scroll-section>
            <h1 class="text">VOLET ADMINISTRATEUR</h1>



            <div class='but-container'>
                <div class='row'>
                    <div class='col'>
                        <%
                            if (session.getAttribute("admin").equals("SuperAdmin")){
                                out.print("\n" +
                                        "<button type='button4' onclick=\"window.location.href='PrintUSER';\">Table des Utilisateurs</button>\n" +
                                        "<button type='button1' onclick=\"window.location.href='PrintSTR';\">Table des Structures</button>\n" +
                                        "<button type='button2' onclick=\"window.location.href='PrintLOC';\">Table des Localit&eacute;s</button>\n" +
                                        "<button type='button3' onclick=\"window.location.href='datePickerPERS.jsp';\">Table Personnel</button>\n" +
                                        "<button type='button4' onclick=\"window.location.href='datePickerRUB.jsp';\">Table Rubrique</button>");

                            }else if(session.getAttribute("admin").equals("AdminPaie")) {
                                out.print("\n" +
                                        "<button type='button1' onclick=\"window.location.href='PrintSTR';\">Table des Structures</button>\n" +
                                        "<button type='button2' onclick=\"window.location.href='PrintLOC';\">Table des Localit&eacute;s</button>\n" +
                                        "<button type='button4' onclick=\"window.location.href='datePickerRUB.jsp';\">Table Rubrique</button>");
                            }else if (session.getAttribute("admin").equals("AdminPers")){
                                out.print("<button type='button1' onclick=\"window.location.href='PrintSTR';\">Table des Structures</button>\n" +
                                        "<button type='button2' onclick=\"window.location.href='PrintLOC';\">Table des Localit&eacute;s</button>\n" +
                                        "<button type='button3' onclick=\"window.location.href='datePickerPERS.jsp';\">Table Personnel</button>");
                            }
                        %>
                    </div>
                </div>
            </div>


        </section>
    </div>


    <script src="typed.js"></script>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/ScrollTrigger.min.js"></script>
    <script src="scrollTrigger.js"></script>
    <script src="loco.js"></script>
    <script>
        (function() {
            var scroll = new LocomotiveScroll();
        })();
    </script>




</body>