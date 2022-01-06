<%@ page import="Classes.DATE" %><%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 03/08/2021
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DATE datel = new DATE();
    String opt = datel.optionYear();
    String optM = datel.optionMonth();
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/date.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/locomotive.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <title>INSTITUT ALGERIEN DU PETROLE</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


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

        <section id="home-row" data-scroll-section>
            <h1>choisissez la date de votre document:</h1>
            <div class="datepicker">
                <form method="get" action="BP">
                    <div class="select-date">
                        <select class="select" id="select-month" name="month">
                            <%=optM%>
                        </select>
                        <select class="select" id="select-year" name="year">
                            <%=opt%>
                        </select>
                    </div>
                <button type="submit">Valider</button>
                </form>
            </div>
        </section>
    </div>


    <script src="typed.js"></script>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/ScrollTrigger.min.js"></script>
    <script src="scrollTrigger.js"></script>
    <script src="${pageContext.request.contextPath}/page_accueil/loco.js"></script>
    <script>
        (function() {
            var scroll = new LocomotiveScroll();
        })();
    </script>



</body>