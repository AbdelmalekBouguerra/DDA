<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page_accueil/email.css">
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


<body class="scrol">
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
        <h1 class="text">Volet CNAS </h1>

        <div class="dates-wrapper group">
            <form method="post" action="CNAS">
                <select id="raison" name="raison">
                    <option value="">--Veuillez choisir une option--</option>
                    <option value="Mise à jour de la carte chiffa.">Mise à jour de la carte chiffa.</option>
                    <option value="Dossier allocations familiales">Dossier allocations familiales</option>
                    <option value="Congé de maladie">Congé de maladie</option>
                    <option value="Accident de travail">Accident de travail</option>
                    <option value="Congé de maternité">Congé de maternité</option>
                    <option value="Dossier capital décès">Dossier capital décès</option>
                </select>
                <div id="ab">
                    <input id="email" type="email" name="email" placeholder="Entrez votre Email ici !!!">
                </div>
                <div>
                    <button>Valider</button>
                </div>

            </form>
        </div>
    </section>
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
<script src="loco.js "></script>
<script>
    (function () {
        var scroll = new LocomotiveScroll();
    })();
</script>


</body>
</html>