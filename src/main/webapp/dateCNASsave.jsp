<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 03/08/2021
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/dateCNAS.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Concert+One&family=Erica+One&display=swap" rel="stylesheet">
    <title>INSTITUT ALGERIEN DU PETROLE</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/page_accueil/Layer2.png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

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
        <form method="get" action="ATC">
            <h1 class="text">choisissez une date</h1>

            <div class="dates-wrapper group">

                <div class="field clearfix date-range-start date-wrapper">
                    <div class="label">
                        <label for="datepicker-start">Date du dernier jour de travail:</label>
                    </div>
                    <div class="input">
                        <input type="date" name="experience-start" id="datepicker-start" class="input-text"
                               placeholder="dd/mm/yyyy">
                    </div>
                    <a href="#" class="calendar-btn calendar-start hide-text">Afficher Le calendrier</a>
                </div>

                <div class="field clearfix date-range-start date-wrapper">
                    <div class="label">
                        <label for="datepicker-end">Date reprise de travail:</label>
                    </div>
                    <div class="input">
                        <input type="date" name="experience-end" id="datepicker-end" class="input-text"
                               placeholder="dd/mm/yyyy">
                    </div>
                    <a href="#" class="calendar-btn hide-text">Afficher Le calendrier</a>
                </div>

                <div class="field clearfix date-range-start date-wrapper">
                    <div class="label">
                        <label for="baseSS-start">Date Debut:</label>
                    </div>
                    <div class="input">
                        <input type="date" name="baseSS-start" id="baseSS-start" class="input-text"
                               placeholder="dd/mm/yyyy">
                    </div>
                    <a href="#" class="calendar-btn hide-text">Afficher Le calendrier</a>
                </div>
                <div class="field clearfix date-range-start date-wrapper">
                    <div class="label">
                        <label for="baseSS-end">Date fin:</label>
                    </div>
                    <div class="input">
                        <input type="date" name="baseSS-end" id="baseSS-end" class="input-text"
                               placeholder="dd/mm/yyyy">
                    </div>
                    <a href="#" class="calendar-btn hide-text">Afficher Le calendrier</a>
                </div>
            </div>


<%--            <select name="pets" id="sl">--%>
<%--                <option value="">--svp choissisez la durée--</option>--%>
<%--                <option value="3">3 mois</option>--%>
<%--                <option value="6">6 mois</option>--%>
<%--                <option value="12">12 mois</option>--%>
<%--            </select>--%>
            <button type="submit">Valider</button>
        </form>
</div>
</div>


</section>
</div>


<script>
    if ($('[type="date"]').prop('type') != 'date') {
    }

    if ($('html').hasClass('no-touch')) {
        var $input, $btn;
        $(".date-wrapper").each(function (index) {
            $input = $(this).find('input');
            $btn = $(this).find('.calendar-btn');
            $input.attr('type', 'text');
            var pickerStart = new Pikaday({
                field: $input[0],
                trigger: $btn[0],
                container: $(this)[0],
                format: 'DD/MM/YYYY',
                firstDay: 1
            });
            $btn.show();
        });
    } else {
        $('.date-wrapper input').attr('type', 'date');
        $('.calendar-btn').hide();
    }
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js "></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/ScrollTrigger.min.js "></script>
<script src="scrollTrigger.js "></script>
<script src="${pageContext.request.contextPath}/page_accueil/loco.js "></script>
<script>
    (function () {
        var scroll = new LocomotiveScroll();
    })();
</script>


</body>