<%--
  Created by IntelliJ IDEA.
  User: Abdelmalek
  Date: 04/10/2021
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>


<!DOCTYPE html>
<html lang="fr">
<head>
    <title>INSTITUT ALGERIEN DU PETROLE</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="mat/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="mat/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="mat/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="mat/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="mat/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="mat/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="mat/css/util.css">
    <link rel="stylesheet" type="text/css" href="mat/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100" style="height: 600px;">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="mat/images/img-01.png" alt="IMG">
            </div>

            <form method="get" action="Matricule" class="login100-form validate-form">

					<span class="login100-form-title">

                        Recherche de bulletin de paie par matricule:

					</span>

                <div class="wrap-input100 validate-input">
                    <input name="type" type="hidden" value="REmat">
                    <input class="input100" id="mat" name="mat" required  minlength="6" maxlength="6" size="6" placeholder="Matricule">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i  class="fa fa-id-badge" aria-hidden="true"></i>
						</span>
                </div>



                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Rechercher
                    </button>
                    <p style="color: red;font-size: 20px">${invalidUname}</p>
                </div>
                <br><br><br>


            </form>
        </div>
    </div>
</div>

<br><br><br>
<br>


<!--===============================================================================================-->
<script src="mat/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="mat/vendor/bootstrap/js/popper.js"></script>
<script src="mat/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="mat/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="mat/vendor/tilt/tilt.jquery.min.js"></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>

