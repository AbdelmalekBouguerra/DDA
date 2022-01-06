<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 13/05/2021
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // for HTTP 1.1

    response.setHeader("Pragma","no-cache"); // HTTP 1.0

    response.setHeader("Expires","0"); // Proxies


    if (session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style>
        @import url('https://fonts.googleapis.com/css?family=Montserrat|Courgette|Roboto');
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        #scroller::-webkit-scrollbar {
            display: none;
        }

        #scroller {
            -ms-overflow-style: none;
            /* IE and Edge */
            scrollbar-width: none;
            /* Firefox */
        }

        body,
        html {
            font-family: Verdana, Geneva, Tahoma, sans-serif;
            width: 100vw;
            scrollbar-width: none;
        }

        section {
            display: flex;
            flex-direction: column;
            justify-content: center;
            text-align: center;
            align-items: center;
            width: 100%;
            height: 100vh;
        }

        section h1 {
            font-family: 'Erica One', cursive;
            font-size: calc(18px + 2vw);
            position: relative;
            left: 14%;
            top: 30%;
            color: rgb(7, 0, 0);
        }

        @media (max-width:760px) {
            section h1 {
                display: none;
            }
        }


        /* but2 {
            margin: 50px;
            font-family: inherit;
        }

        .slide2 {
            font-size: 24px;
            font-weight: 200;
            letter-spacing: 1px;
            padding: 13px 30px 13px;
            outline: 0;
            border: 2px solid black;
            cursor: pointer;
            position: absolute;
            color: black;
            top: 59%;
            left: 58%;
            background-color: rgba(0, 0, 0, 0);
        } */

        section .but2 {
            position: absolute;
            cursor: pointer;
            align-items: center;
            left: 57.5%;
            top: 57%;
            margin: 22px;
            letter-spacing: 1px;
            overflow: hidden;
            border: 2px solid;
            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            width: 155px;
            height: 40px;
            font-weight: bold;
            font-size: 12px;
            transition: 0.5s;
            border-radius: 8px;
            background: rgba(22, 109, 0, 0.932);
        }

        section .but2:hover {
            box-shadow: 3px 5px 10px 4px rgba(4, 241, 4, 0.3);
            cursor: pointer;
            background-color: rgb(76, 205, 50);
            color: white;
        }

        section page {
            background: url("${filePathSVG}") no-repeat center center/cover;
            width: 11cm;
            position: absolute;
            top: 11%;
            left: 3%;
            display: block;
            margin: 25px;
            margin-bottom: 0.5cm;
            box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5);
        }

        section page[size="A4"] {
            width: 11cm;
            height: 16cm;
        }

        @media (max-width:700px) {
            section page {
                width: 9cm;
                position: absolute;
                top: 11%;
                left: -4%;
            }
            section page[size="A4"] {
                width: 9cm;
                height: 13cm;
            }
            section .but2 {
                position: absolute;
                left: 24.5%;
                top: 84%;
                margin: 22px;
                letter-spacing: 1px;
                overflow: hidden;
                border: 2px solid;
                transition: 0.5s;
                border-radius: 8px;
                background: rgb(76, 205, 50);
                box-shadow: 3px 5px 10px 4px rgba(4, 241, 4, 0.3);
                color: white;
            }
            section .but2:hover {
                display: none;
            }
        }

        @media print {
            body,
            page {
                margin: 0;
                box-shadow: 0;
            }
        }

        section#home-row {
            background: url("${pageContext.request.contextPath}/page_accueil/p3.jpg") no-repeat center center/cover;
            width: 100%;
        }

        .container {
            width: 100%;
            height: 60%;
        }

        nav {
            list-style: none;
            background: rgb(0, 0, 0);
            width: 100vw;
            overflow: hidden;
            right: 30px;
            top: 20px;
            z-index: 1;
        }

        .navlogo {
            height: 42px;
            float: left;
            margin: 16px 30px;
            cursor: pointer;
        }

        .icons-size {
            color: #333;
            font-size: 14px;
        }

        nav .profile {
            cursor: pointer;
            height: 50px;
            overflow: hidden;
            position: relative;
            width: 50px;
            float: right;
            margin: 15px 30px;
        }

        nav .profile img {
            width: 100%;
            top: 0;
            position: absolute;
            object-fit: cover;
            left: 0;
            height: 100%;
        }

        nav .profile img:hover {
            opacity: 0.5;
        }

        nav .menu {
            background-color: #FFF;
            box-sizing: 0 5px 25px rgba(0, 0, 0, 0.1);
            border-radius: 15px;
            padding: 10px 20px;
            position: absolute;
            right: -10px;
            width: 200px;
            transition: 0.5s;
            top: 120px;
            visibility: hidden;
            opacity: 0;
            margin: 25px;
            z-index: 999;
        }

        nav .menu.active {
            opacity: 1;
            top: 80px;
            visibility: visible;
        }

        nav .menu::before {
            background-color: #fff;
            content: '';
            height: 20px;
            position: absolute;
            right: 30px;
            transform: rotate(45deg);
            top: -5px;
            width: 20px;
        }

        nav .menu h3 {
            color: #555;
            font-size: 16px;
            font-weight: 600;
            line-height: 1.3em;
            padding: 20px 0px;
            text-align: left;
            width: 100%;
        }

        nav .menu h3 div {
            color: #818181;
            font-size: 14px;
            font-weight: 400;
        }

        nav .menu ul li {
            align-items: center;
            border-top: 1px solid rgba(0, 0, 0, 0.05);
            display: flex;
            justify-content: left;
            list-style: none;
            padding: 10px 0px;
        }

        nav .menu ul li img {
            max-width: 20px;
            margin-right: 10px;
            opacity: 0.5;
            transition: 0.5s
        }

        nav .menu ul li a {
            display: inline-block;
            color: #555;
            font-size: 14px;
            font-weight: 600;
            padding-left: 15px;
            text-decoration: none;
            text-transform: uppercase;
            transition: 0.5s;
        }

        nav .menu ul li:hover img {
            opacity: 1;
        }

        nav .menu ul li:hover a {
            color: #F26526;
        }

        .lead {
            font-size: 1.5rem;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/locomotive.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Concert+One&family=Erica+One&display=swap" rel="stylesheet">
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
        <h1 div="text">Téléchargez votre <br> document</h1>

        <button onclick="window.location.href='Download';" type="but2" class="but2">
            <div >Télécharger</div>
            <i class="icon-arrow-right"></i>
        </button>
        <page size="A4"></page>

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