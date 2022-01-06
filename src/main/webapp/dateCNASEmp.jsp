<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="en" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
    <!--try map box-->




    <!-- meta charec set -->
    <meta charset="utf-8">
    <!-- Always force latest IE rendering engine or request Chrome Frame -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- Page Title -->

    <link rel="shortcut icon" type="image/png" href="img2/Layer 2.png">
    <title> DOC IAP SONATRACH</title>

    <!-- Meta Description -->

    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Google Font -->

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <!-- CSS
		================================================== -->
    <!-- Fontawesome Icon font -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <!-- Twitter Bootstrap css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- jquery.fancybox  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.fancybox.css">
    <!-- animate -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/p3cnas.css">
    <!-- media-queries -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css">

    <!-- Modernizer Script for old Browsers -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


</head>

<body id="body">

    <!-- preloader -->
    <div id="preloader">
        <img src="${pageContext.request.contextPath}/img2/sona-load.gif" alt="Preloader">
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
                        <img class="g" src="${pageContext.request.contextPath}/img/sona1.png" alt="Brandi">
                    </h1>
                </a>
                <!-- /logo -->
            </div>

            <nav>
                <div class="profile" onclick="menuToggle();">
                    <img src="${pageContext.request.contextPath}/img2/male_user_65px.png" alt="">
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
            <!-- main nav -->





            <!-- /main nav -->

        </div>
    </header>
    <!--
        End Fixed Navigation
        ==================================== -->



    <!--
        Home Slider
        ==================================== -->

    <section id="slider">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">



            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">

                <!-- single slide -->
                <div class="item active" style="background-image: url(img2/sonatrach-1.jpg);">

                    <div class="home-row">
                        <form method="get" action="ATCEmp">

                        <h1 data-wow-duration="700ms" data-wow-delay="500ms" class="wow bounceInDown text">choisissez une date</h1>

                        <div data-wow-duration="1000ms" class="wow slideInLeft animated" class="dates-wrapper group">

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



        </div>

        </div>

        </div>
        <!-- End Wrapper for slides -->

        </div>
    </section>



    <!-- Essential jQuery Plugins
		================================================== -->
    <!-- Main jQuery -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <!-- Single Page Nav -->
    <script src="${pageContext.request.contextPath}/js/jquery.singlePageNav.min.js"></script>
    <!-- Twitter Bootstrap -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- jquery.fancybox.pack -->
    <script src="${pageContext.request.contextPath}/js/jquery.fancybox.pack.js"></script>
    <!-- jquery.mixitup.min -->
    <script src="${pageContext.request.contextPath}/js/jquery.mixitup.min.js"></script>
    <!-- jquery.parallax -->
    <script src="${pageContext.request.contextPath}/js/jquery.parallax-1.1.3.js"></script>
    <!-- jquery.countTo -->
    <script src="${pageContext.request.contextPath}/js/jquery-countTo.js"></script>
    <!-- jquery.appear -->
    <script src="${pageContext.request.contextPath}/js/jquery.appear.js"></script>
    <!-- Contact form validation -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.32/jquery.form.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.11.1/jquery.validate.min.js"></script>
    <!-- Google Map -->
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <!-- jquery easing -->
    <script src="${pageContext.request.contextPath}/js/jquery.easing.min.js"></script>
    <!-- jquery easing -->
    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
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
    <script src="${pageContext.request.contextPath}/js/custom.js"></script>
    <script src="${pageContext.request.contextPath}/js/typed.js"></script>
    <script src="${pageContext.request.contextPath}/js/click.js"></script>
    <script>
        if ($('[type="date"]').prop('type') != 'date') {}

        if ($('html').hasClass('no-touch')) {
            var $input, $btn;
            $(".date-wrapper").each(function(index) {
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

</body>

</html>