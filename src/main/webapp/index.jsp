
<!DOCTYPE html>
<html lang="fr">
<head>

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

    <!--the link below was used for the external file of my js-->
   
    <!-- the link below is used for the icone's-->
   
 <style type="text/css">
 	
  .login {
  min-height: 100vh;
}

.bg-image {
  background-image: url('${pageContext.request.contextPath}/img2/sonatrach-1.jpg');
  background-size: cover;
  background-position: center;
}

.login-heading {
  font-weight: 300;
  color: orange;
}

.btn-login {
  font-size: 0.9rem;
  letter-spacing: 0.05rem;
  padding: 0.75rem 1rem;
}



 </style>


	<title></title>


</head>


<body>
<div class="container-fluid ps-md-0">
  <div class="row g-0">
    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
    <div class="col-md-8 col-lg-6">
      <div class="login d-flex align-items-center py-5">
        <div class="container">
          <div class="row">
            <div class="col-md-9 col-lg-8 mx-auto">
              <h3 class="login-heading mb-4"> &nbsp;  &nbsp;  &nbsp;  INSTITUT ALGERIEN DU PETROLE <br>
                                           Digitalisation des Documents Administratifs</h3>

              <!-- Sign In Form -->
              <form action="Login" method="post">
                <div class="form-check mb-3">
                  <input type="text" id="text" name="uname" placeholder="SON..." required="required" />
                   <span class="emailText invalid">${invalidUname}</span>
                </div><br>
                  <div class="form-check mb-3">
                <input type="password" id="password" name="pass" placeholder="Mot de passe" required="required"/>
                <span class="emailText invalid">${invalidPass}</span>
                </label>
                <br>
                

                <div class="d-grid">
                	<br>
                  <button class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" type="submit">Connexion</button>
                  
                </div>

              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>