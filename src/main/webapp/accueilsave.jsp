<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/styling.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/locomotive.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/page_accueil/Layer2.png">
    <title> INSTITUT  ALGERIEN DU PETROLE</title>

</head>



<body class="scrol">



    <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fas fa-arrow-up"></i></button>


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
                ${name}
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

        <section id="home-row">

            <div class="box-col">
                <p>Bienvenue<span class="typed-text"></span><span class="cursor">&nbsp;</span></p>
            </div>

            <div class="header-row">
                choisissez votre document
            </div>



            <div class='but-container'>
                <div class='row'>
                    <div class='col'>
                        <button type='button1' onclick="window.location.href='#ats';">Attestation de travail</button>

                        <button type='button2' onclick="window.location.href='#bulletin';">Bulletin de paie</button>

                        <button type='button3' onclick="window.location.href='#releve';">Relevé des émoluments</button>

                        <button type='button4' onclick="window.location.href='#cnas';">ATS CNAS</button>


                    </div>
                </div>
            </div>


        </section>


        <section id='ats' class="ats">

            <h1 div="text">Attestation de travail</h1>
            <div class="box box1"><img class=img1 src="${pageContext.request.contextPath}/page_accueil/paper-2.png"></div>
                ${but1}
<%--            <button  type="but1" class="slide" onClick="javascript:window.open(href='AT', '_blank');">--%>
<%--                <div >Consulter</div> --%>
<%--                <i class="icon-arrow-right"></i>--%>
<%--              </button>--%>



        </section>

        <section id="bulletin" class="bulletin">
            <h1>Bulletin de paie</h1>
            <div class="box box2"><img class=img2 src="${pageContext.request.contextPath}/page_accueil/bull-1.png"></div>
            ${but2}
        <%--            <button type="but2" class="slide2" onClick="javascript:window.open(href='datePicker.jsp', '_blank');">--%>
<%--                <div >Consulter</div>--%>
<%--                <i class="icon-arrow-right"></i>--%>
<%--              </button>--%>


            </main>

        </section>
        <section id="releve" class="releve">
            <h1>Relevé des émoluments annuel</h1>
            <div class="box box3"><img class=img3 src="${pageContext.request.contextPath}/page_accueil/rea-2.png"></div>


            <div class="dropdown bt3">
                ${but3}

            <%--                <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="false" aria-expanded="false">--%>
<%--                  choisissez--%>
<%--                </button>--%>
<%--                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
<%--                    <a class="dropdown-item" href="RE" target="_blank">Normale</a>--%>
<%--                    <a class="dropdown-item" href="RED" target="_blank">Détaillé</a>--%>
<%--                </div>--%>
            </div>



        </section>

        <section id="cnas" class="cnas">
            <h1>ATS CNAS</h1>
            <div class="box box4"><img class=img4 src="${pageContext.request.contextPath}/page_accueil/A4_cnas.png"></div>
            <div class="box box5"><img class=img5 src="${pageContext.request.contextPath}/page_accueil/pen-2.png"></div>

            ${CNAS}

<%--            <div class="dropdown">--%>
<%--                <button class="btn dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="false" aria-expanded="false">--%>
<%--                    choisissez--%>
<%--                </button>--%>
<%--                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
<%--                    <a class="dropdown-item" href="dateCNAS.jsp" target="_blank">Mon ATS CNAS</a>--%>
<%--                    <a class="dropdown-item" href="CNASADMIN" target="_blank">Les demandes</a>--%>
<%--                    <a class="dropdown-item" href="#" target="_blank">personnaliser</a>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--            <button type="but4" class="slide4" onClick="javascript:window.open(href='${CNAS}', '_blank');">--%>
<%--                <div >Demander</div>--%>
<%--                <i class="icon-arrow-right"></i>--%>
<%--              </button>--%>



        </section>


    </div>

    <footer class="footer">
        <div class="footer-container">
            <div class="footer-row">
                <div class="footer-col">
                    <h4 id="add">Adresse</h4>
                    <ul>
                        <li>Avenue du 1er Novembre 1954 Boumerdès, Algérie
                        </li>
                        <li><a href="https://www.google.com/maps/place/Sonatrach%2FIAP%2FEcole+de+Boumerd%C3%A8s/
							@36.7591281,3.4687454,17z/data=!3m1!4b1!4m5!3m4!1s0x128e68ee16214031
							:0x700418cc081683ca!8m2!3d36.7591281!4d3.4709341" target="_blank" rel="noopener noreferrer">Maps  <i class="fas fa-arrow-right"></i></a></li>

                    </ul>
                </div>
                <div class="footer-col">
                    <h4 id="cont">Contacts</h4>
                    <ul>
                        <li>Tel: 024 79 57 07</li>
                        <li>Fax: 024 79 57 05</li>
                        <li>Email:iap@iapdz</li>

                    </ul>
                </div>
                <div class="footer-col">
                    <h4 id="link">Documents</h4>
                    <ul>
                        <li><a href="#ats">Attestation de travail</a></li>
                        <li><a href="#bulletin">Bulletin de paie</a></li>
                        <li><a href="#releve">Relevé des émoluments annuel</a></li>
                        <li><a href="#cnas">Attestation de travail CNAS</a></li>
                    </ul>
                </div>
                <div class="footer-col">
                    <h4 id="foll">Suivez-nous</h4>
                    <div class="social-links">
                        <a href=""><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
                <div class="footer-col">
                    <p>© Copyright 2021 par Rachid & Bouguerra - Tous les droits sont réservés</p>
                </div>
            </div>
        </div>
    </footer>


    <script>
        const typedTextSpan = document.querySelector(".typed-text");
        const cursorSpan = document.querySelector(".cursor");

        const textArray = ["${name}"];
        const typingDelay = 200;
        const erasingDelay = 100;
        const newTextDelay = 2000; // Delay between current and next text
        let textArrayIndex = 0;
        let charIndex = 0;

        function type() {
            if (charIndex < textArray[textArrayIndex].length) {
                if (!cursorSpan.classList.contains("typing")) cursorSpan.classList.add("typing");
                typedTextSpan.textContent += textArray[textArrayIndex].charAt(charIndex);
                charIndex++;
                setTimeout(type, typingDelay);
            } else {
                cursorSpan.classList.remove("typing");
                setTimeout(erase, newTextDelay);
            }
        }


        document.addEventListener("DOMContentLoaded", function() { // On DOM Load initiate the effect
            if (textArray.length) setTimeout(type, newTextDelay + 250);
        });
    </script>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/ScrollTrigger.min.js"></script>
    <script src="${pageContext.request.contextPath}/page_accueil/scrollTrigger.js"></script>
<%--    <script src="${pageContext.request.contextPath}/page_accueil/loco.js"></script>--%>
<%--    <script>--%>
<%--        (function() {--%>
<%--            var scroll = new LocomotiveScroll();--%>
<%--        })();--%>
<%--    </script>--%>

    <script>
        //Get the button
        var mybutton = document.getElementById("myBtn");

        // When the user scrolls down 20px from the top of the document, show the button
        window.onscroll = function() {
            scrollFunction()
        };

        function scrollFunction() {
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                mybutton.style.display = "block";
            } else {
                mybutton.style.display = "none";
            }
        }

        // When the user clicks on the button, scroll to the top of the document
        function topFunction() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
    </script>


    </body>



</html>