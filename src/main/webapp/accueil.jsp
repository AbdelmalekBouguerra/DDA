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

    <script src='https://api.mapbox.com/mapbox-gl-js/v2.3.1/mapbox-gl.js'></script>
    <link href='https://api.mapbox.com/mapbox-gl-js/v2.3.1/mapbox-gl.css' rel='stylesheet' />

    


    <!-- meta charec set -->
    <meta charset="utf-8">
    <!-- Always force latest IE rendering engine or request Chrome Frame -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- Page Title -->

    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img2/Layer_2.png">
    <title> DOC IAP SONATRACH</title>

    <!-- Meta Description -->
    <meta name="description" content="Blue One Page Creative HTML5 Template">
    <meta name="keywords" content="one page, single page, onepage, responsive, parallax, creative, business, html5, css3, css3 animation">
    <meta name="author" content="Muhammad Morshed">
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Google Font -->

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <!-- CSS
		================================================== -->
    <!-- Fontawesome Icon font -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <!-- Twitter Bootstrap css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- jquery.fancybox  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.fancybox.css">
    <!-- animate -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <!-- media-queries -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css">

    <!-- Modernizer Script for old Browsers -->
    <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>

</head>

<body id="body">

${error}

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
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-bars fa-2x"></i>
                    </button>
                <!-- /responsive nav button -->

                <!-- logo -->
                <a class="navbar-brand" href="#body">
                    <h1 id="logo">
                        <img class="g" src="${pageContext.request.contextPath}/img2/logo-iap.png" alt="Brandi">

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
            <!-- main nav -->
            <nav class="collapse navbar-collapse navbar-right" role="navigation">
                <ul id="nav" class="nav navbar-nav" style="font-weight: bold;">
                    <li class="current"><a href="#body">Accueil</a></li>
                    <!--<li><a href="#features">Présentation de l’IAP</a></li>-->
                    <li><a href="#works">Documents Administratifs</a></li>
                    <li><a href="#features">Documents utils</a>
                      
                         <!--<li><a href="#">Règlementation</a>
                            <ul  class="sous-menu">
                                 <li><a href ="#">Règlement Intérieur de l’Entreprise</a></li>
                               <li><a href ="#">Convention Collective de l’Entreprise</a></li>
                               <li><a href ="#">Charte d’éthique de l’Entreprise</a></li>
                               <li><a href ="#">Plan Développement IAP (Note d’Orientation Stratégique)</a></li> 

                            </ul>


                             </li>
                         <li><a href="#">Avantages sociaux</a></li>
                         <li></li>
                         <li></li>
                </ul>-->

                    </li>
                    <li><a href="#footer">Contactez-nous</a></li>
                    <!-- <li class="nav-item dropdown">
                        <a href="#works" class="nav-link dropdown-toggle" data-toggle="dropdown">Documents <i class="fa fa-angle-down"></i></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="services.html">Services All</a></li>
                            <li><a href="service-single.html">Services Single</a></li>
                        </ul>
                    </li> -->
                </ul>
            </nav>




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
                <div class="item active" style="background-image: url(img/sonatrach-1.jpg);">
                    <div class="carousel-caption">
                        <h2 data-wow-duration="700ms" data-wow-delay="500ms" class="wow bounceInDown animated">Bienvenue ${name}</h2>
                        <h3 data-wow-duration="1000ms" class="wow slideInLeft animated"><span class="color">|Digitalisation</span> des Documents Administratifs.</h3>
                        <p data-wow-duration="1000ms" class="wow slideInRight animated">IAP SONATRACH</p>


                    </div>
                </div>


            </div>
            <!-- End Wrapper for slides -->

        </div>
    </section>

    <!--
        End Home SliderEnd
        ==================================== -->

   
    <!--
        Our Works
        ==================================== -->

    <section id="works" class="works clearfix">
        <div class="container">
            <div class="row">

                <div class="sec-title text-center">
                    <h2>Documents Administratifs</h2>
                    <div class="devider"><img src="img2/icon-make.png" class="sona-icon"></div>
                </div>

                <div class="sec-sub-title text-center">
                    <p>Consultez et téléchargez vos documents administratifs</p>
                </div>



            </div>
        </div>

        <div class="project-wrapper">

            <figure class="mix work-item branding">
                <img src="img2/1doc-try.png" alt="" style="width:120%;">
                <figcaption class="overlay">
                    <!-- <a class="fancybox" rel="works" title="Write Your Image Caption Here" href="img/works/item-1.jpg"><i class="fa fa-eye fa-lg"></i></a> -->




                    <h4>Attestation de travail</h4>
                        ${but1}
                </figcaption>
            </figure>

            <figure class="mix work-item web">
                <img src="img2/2doc-try.png" alt="" style="width:120%;">
                <figcaption class="overlay">

                    <h4>Bulletin de paie</h4>
                    ${but2}
                </figcaption>
            </figure>
    
<!------------------------------------------------>

            <figure class="mix work-item logo-design">
                <img src="img2/trydoc1.png" alt="" style="width:120%;">
                <figcaption class="overlay">

                    <h4>Relevé des émoluments annuel</h4>
                    ${but3}
                </figcaption>
            </figure>

            <figure class="mix work-item photography">
                <img src="img2/4doc-try.png" alt="" style="width:120%;">
                <figcaption class="overlay">
                    <h4>ATS CNAS</h4>
                    ${CNAS}
                </figcaption>
            </figure>



        </div>


    </section>

    <!--
        End Our Works
        ==================================== -->


<section id="features" class="features">
        <div class="container">
            <div class="row">

                <div class="sec-title text-center mb50 wow bounceInDown animated" data-wow-duration="500ms">
                    <h2>Documents Utils</h2>
                    <div class="devider"><img src="img2/icon-make.png" class="sona-icon"></div>
                </div>

                <!-- service item -->
                <div class="col-md-4 wow fadeInLeft" data-wow-duration="500ms">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="fa fa-bullhorn fa-2x"></i>
                        </div>

                        <div class="service-desc">

                            <h4 style="color:#f56d1f;">Réglementation</h4>
                          <p>  -&nbsp;&nbsp;<a href="doc-dgp/Reglement interieur.pdf" target="_blank" >Règlement Intérieur de l’Entreprise</a><br>
                               -&nbsp;&nbsp;<a href="doc-dgp/Conv.pdf" target="_blank" >Convention Collective de l’Entreprise</a><br>
                               -&nbsp;&nbsp;<a href="doc-dgp/Code d'ethique de SONATRACH.pdf" target="_blank" >Charte d’éthique de l’Entreprise</a><br>
                               -&nbsp;&nbsp;<a href="doc-dgp/Note d'orientation stratégique IAP.pdf" target="_blank" >Plan Développement IAP</a><br>



                            </p>
                        </div>
                    </div>
                </div>
                <!-- end service item -->

                <!-- service item -->
                <div class="col-md-4 wow fadeInUp" data-wow-duration="500ms" data-wow-delay="500ms">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="fa fa-bullhorn fa-2x"></i>
                        </div>

                        <div class="service-desc">

                            <h4 style="color:#f56d1f;">Autres Documents</h4>
                            <p> 
                               -&nbsp;&nbsp;<a href="doc-dgp/DECISION N 2 PORTANT ORGANISATION DE DETAIL DE l'iap.pdf" target="_blank" >Organisation de l'IAP</a><br>
                               -&nbsp;&nbsp;<a href="" target="_blank" >Historique de l’IAP</a><br>
                               -&nbsp;&nbsp;<a href="" target="_blank" >Catalogues de formation</a><br>
                               -&nbsp;&nbsp;<a href="" target="_blank" title="En Production" >Plan de Développement de la DGP<br></a><br>

                                         
                            </p>
                        </div>
                    </div>
                </div>
                <!-- end service item -->

                <!-- service item -->
                <div class="col-md-4 wow fadeInRight" data-wow-duration="500ms" data-wow-delay="900ms">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="fa fa-bullhorn fa-2x"></i>
                        </div>

                        <div class="service-desc">
                          
                            <h4 style="color:#f56d1f;">Avantages Sociaux</h4>
                            <p>
                               -&nbsp;&nbsp;<a href=""  > Duppléant Numérique en Arabe</a><br>
                               -&nbsp;&nbsp;<a href="" > Duppléant Numérique en Français</a><br>
                              
                            </p>
                        </div>
                    </div>
                </div>
                <!-- end service item -->

            </div>
        </div>
    </section>

    <!--
        End Features
        ==================================== -->


    <!--
        Meet Our Team
        ==================================== -->

   <!--  <section id="team" class="team">
        <div class="container">
            <div class="row">

                <div class="sec-title text-center wow fadeInUp animated" data-wow-duration="700ms">
                    <h2>Documents-2</h2>
                    <div class="devider"><img src="img2/icon-make.png" class="sona-icon">
                    </div>
                </div>

                <div class="sec-sub-title text-center wow fadeInRight animated" data-wow-duration="500ms">
                    <p>telecharger et consulter vos documents a tout moment et n'importe où </p>
                </div>

                <!- single member 

                <figure class="team-member col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms">
                    <div class="member-thumb">
                        <img src="img2/1doc-try.png" alt="Team Member" class="img-responsive">
                        <figcaption class="overlay">
                            <h5>voluptatem quia voluptas </h5>
                            <p>sit aspernatur aut odit aut fugit,</p>
                            <button type="but2" class="but2">
                                <div >Telecharger</div>
                                <i class="icon-arrow-right"></i>
                             </button>
                        </figcaption>
                    </div>
                    <h4>Attestation de travaille</h4>

                </figure>
            end single member 

               single member
                <figure class="team-member col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="300ms">
                    <div class="member-thumb">
                        <img src="img2/2doc-try.png" alt="Team Member" class="img-responsive">
                        <figcaption class="overlay">
                            <h5>voluptatem quia voluptas </h5>
                            <p>sit aspernatur aut odit aut fugit,</p>
                            <button type="but2" class="but2" onClick="javascript:window.open(href='p2.html', '_blank');">
                                <div >Telecharger</div>
                                <i class="icon-arrow-right"></i>
                             </button>
                        </figcaption>
                    </div>
                    <h4>Bulletin de paie</h4>

                </figure>
                 end single member 

                 single member 
                <figure class="team-member col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="600ms">
                    <div class="member-thumb">
                        <img src="img2/trydoc1.png" alt="Team Member" class="img-responsive">
                        <figcaption class="overlay">
                            <h5>voluptatem quia voluptas </h5>
                            <p>sit aspernatur aut odit aut fugit,</p>
                            <div class="dropdown">
                                <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="false" aria-expanded="false">
                                  choisissez
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" href="page2.html" target="_blank">Normale</a>
                                    <a class="dropdown-item" href="page2.html" target="_blank">Détaillé</a>
                                </div>
                            </div>
                        </figcaption>
                    </div>
                    <h4>Relevé des émoluments annuel</h4>

                </figure>
                end single member 

                 single member 
                <figure class="team-member col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="900ms">
                    <div class="member-thumb">
                        <img src="img2/4doc-try.png" alt="Team Member" class="img-responsive">
                        <figcaption class="overlay">
                            <h5>voluptatem quia voluptas </h5>
                            <p>sit aspernatur aut odit aut fugit,</p>
                            <button type="but2" class="but2">
                                <div >Demander</div>
                                <i class="icon-arrow-right"></i>
                             </button>
                        </figcaption>
                    </div>
                    <h4>ATS CNAS</h4>

                </figure>
            end single member 

            </div>
        </div>
    </section>
 -->
    <!--
        End Meet Our Team
        ==================================== -->

    <!--
        Some fun facts
        ==================================== -->

    <section id="facts" class="facts">
        <div class="parallax-overlay">
            <div class="container">
                <div class="row number-counters">

                    <div class="sec-title text-center mb50 wow rubberBand animated" data-wow-duration="1000ms">
                        <h2>Quelques Statistiques</h2>
                        <div class="devider"><img src="img2/icon-make.png" class="sona-icon">
                        </div>

                        <!-- first count item -->
                        <div class="col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms">
                            <div class="counters-item">
                                <i class="fa fa-users fa-3x"></i>
                                <strong data-to="${usersCount}">0</strong>
                                <!-- Set Your Number here. i,e. data-to="56" -->
                                <p>Employés</p>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="300ms">
                            <div class="counters-item">
                                <i class="fa fa-clock fa-3x"></i>
                                <strong data-to="420">0</strong>
                                <!-- Set Your Number here. i,e. data-to="56" -->
                                <p>Employés Satisfaits</p>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="600ms">
                            <div class="counters-item">
                                <i class="fa fa-rocket fa-3x"></i>
                                <strong data-to="${downloads}">0</strong>
                                <!-- Set Your Number here. i,e. data-to="56" -->
                                <p> Documents Téléchargés </p>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12 text-center wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="900ms">
                            <div class="counters-item">
                                <i class="fa fa-trophy fa-3x"></i>
                                <strong data-to="6454">0</strong>
                                <!-- Set Your Number here. i,e. data-to="56" -->
                                <p>Temps Gagné</p>
                            </div>
                        </div>
                        <!-- end first count item -->

                    </div>
                </div>
            </div>
    </section>

    <!--
        End Some fun facts
        ==================================== -->


    <!--
        Contact Us
        ==================================== -->

    <section id="contact" class="contact">
        <div class="container">
            <div class="row mb50">

                <div class="sec-title text-center mb50 wow fadeInDown animated" data-wow-duration="500ms">
                    <h2> Contactez-nous</h2>
                    <div class="devider"><img src="img2/icon-make.png" class="sona-icon">
                    </div>
                </div>

                <div class="sec-sub-title text-center wow rubberBand animated" data-wow-duration="1000ms">
                   <!-- <p></p>-->
                </div>
                <!-- 
               contact address -->
                <!--
                <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 wow fadeInLeft animated" data-wow-duration="500ms">
                    <div class="contact-address">
                        <h3>Cras at ultrices erat, sed vulputate!</h3>
                        <p>2345 Setwant natrer, 1234,</p>
                        <p>Washington. United States.</p>
                        <p>(401) 1234 567</p>
                    </div>
                </div>
                end contact address -->

                <!-- contact form -->
                <!-- <div class="col-lg-8 col-md-8 col-sm-7 col-xs-12 wow fadeInDown animated" data-wow-duration="500ms" data-wow-delay="300ms">
                    <div class="contact-form">
                        <h3>Say hello!</h3>
                        <form action="#" id="contact-form">
                            <div class="input-group name-email">
                                <div class="input-field">
                                    <input type="text" name="name" id="name" placeholder="Name" class="form-control">
                                </div>
                                <div class="input-field">
                                    <input type="email" name="email" id="email" placeholder="Email" class="form-control">
                                </div>
                            </div>
                            <div class="input-group">
                                <textarea name="message" id="message" placeholder="Message" class="form-control"></textarea>
                            </div>
                            <div class="input-group">
                                <input type="submit" id="form-submit" class="pull-right" value="Send message">
                            </div>
                        </form>
                    </div>
                </div> -->
                <!-- end contact form -->

                <!-- footer social links -->
                <!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 wow fadeInRight animated" data-wow-duration="500ms" data-wow-delay="600ms">
                    <ul class="footer-social">
                        <li><a href="https://www.behance.net/Themefisher"><i class="fa fa-behance fa-2x"></i></a></li>
                        <li><a href="https://www.twitter.com/Themefisher"><i class="fa fa-twitter fa-2x"></i></a></li>
                        <li><a href="https://dribbble.com/themefisher"><i class="fa fa-dribbble fa-2x"></i></a></li>
                        <li><a href="https://www.facebook.com/Themefisher"><i class="fa fa-facebook fa-2x"></i></a></li>
                    </ul>
                </div>
                end footer social links -->

            </div> 
        </div>


        <!-- Google map -->
      <!--  <div id="map_canvas" class="wow bounceInDown animated" data-wow-duration="500ms">-->
       
            <!-- <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3196.476782636504!2d3.468745415288716!3d36.759128079956945!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x128e68ee16214031%3A0x700418cc081683ca!2sSonatrach%2FIAP%2FEcole%20de%20Boumerd%C3%A8s!5e0!3m2!1sfr!2sdz!4v1637008179943!5m2!1sfr!2sdz"
                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe> -->
        </div>
        <!-- End Google map -->


    </section>
    <!-- mapbox -->
    <!-- <div id='map_canvas' class="wow bounceInDown animated" data-wow-duration="500ms"></div> -->

    <!-- <div id='map' style='width: 400px; height: 300px;'></div>
    <script>
        mapboxgl.accessToken = 'pk.eyJ1IjoiaXpsYSIsImEiOiJja3cxN2FzMmwwMzU5Mm9wYWM4eHR4aTR4In0.zv1FqjsKC-qFwjrYLxT3AQ';
        var map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11'
        });
    </script> -->
    <!--
        End Contact Us
        ==================================== -->


    <footer id="footer" class="footer" style="height: 400px;">
        <div class="container">
            <div class="row">

                <div class="col-md-3 col-sm-6 col-xs-12 wow fadeInUp animated" data-wow-duration="500ms">
                    <div class="footer-single">
                        <img src="img2/sona1.png" alt="">

                    </div>
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12 wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="300ms">
                    <div class="footer-single">
                        <h6>Contactez-nous </h6>
                        <ul>
                            <li>Chef du Départemnt ADM :5011</li>
                            <li>Chef du Département Développement du personnel :3498 </li>
                            <li>CHef Service Paie :5059 </li>


                        </ul>

                    </div>
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12 wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="600ms">
                    <div class="footer-single">
                        <h6>Liens</h6>
                        <ul>
                            <li><a href="#ats">Attestation de travail</a></li>
                            <li><a href="#bulletin">Bulletin de paie</a></li>
                            <li><a href="#releve">Relevé des émoluments</a></li>
                            <li><a href="#cnas">Attestation de travail CNAS</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12 wow fadeInUp animated" data-wow-duration="500ms" data-wow-delay="900ms">
                    <div class="footer-single">
                        <h3>SONATRACH<span>.</span></h3>
              <p>
                Adresse <br>
                Djenane El Malik Hydra (3,94 km)16035 Alger, Algérie<br><br>
                <strong>Phone:</strong> +213 (0) 023 48 32 32<br>
                <strong>Lien:</strong><a href="https://sonatrach.com/" onclick="window.open(this.href); return false;" >www.sonatrach.dz</a> <br>
              </p>
              <div class="social-links mt-3">
              <a href="https://www.youtube.com/channel/UCNZPL_sNE1nQ2azMKyZX3xQ" class="fab fa-youtube" onclick="window.open(this.href); return false;"><i class="bx bxl-youtube"></i></a>
                <a href="https://www.facebook.com/SONATRACH/" class="facebook" onclick="window.open(this.href); return false;"><i class="fab fa-facebook-f"></i></a>
                
              </div>
                        
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-12">
                    <p class="copyright text-center">
                        Copyright © 2021. All rights reserved. Designed & developed by .....
                    </p>
                </div>
            </div>
        </div>
    </footer>

    <a href="javascript:void(0);" id="back-top"><i class="fa fa-angle-up fa-3x"></i></a>

    <!-- Essential jQuery Plugins
		================================================== -->
    <!-- Main jQuery -->
    <script src="js/jquery-1.11.1.min.js"></script>
    <!-- Single Page Nav -->
    <script src="js/jquery.singlePageNav.min.js"></script>
    <!-- Twitter Bootstrap -->
    <script src="js/bootstrap.min.js"></script>
    <!-- jquery.fancybox.pack -->
    <script src="js/jquery.fancybox.pack.js"></script>
    <!-- jquery.mixitup.min -->
    <script src="js/jquery.mixitup.min.js"></script>
    <!-- jquery.parallax -->
    <script src="js/jquery.parallax-1.1.3.js"></script>
    <!-- jquery.countTo -->
    <script src="js/jquery-countTo.js"></script>
    <!-- jquery.appear -->
    <script src="js/jquery.appear.js"></script>
    <!-- Contact form validation -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.32/jquery.form.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.11.1/jquery.validate.min.js"></script>
    <!-- Google Map -->
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <!-- jquery easing -->
    <script src="js/jquery.easing.min.js"></script>
    <!-- jquery easing -->
    <script src="js/wow.min.js"></script>
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
    <script src="js/custom.js"></script>
    <script src="js/typed.js"></script>
    <script src="js/click.js"></script>


</body>

</html>