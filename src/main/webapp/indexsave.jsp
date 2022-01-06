<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device.width,
    initial-scale=1.0">

    <link rel="stylesheet" href="css/font-awsome.min.css">
    <!--the link below was used for the external file of my css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Style/hy.css">
    <!--the link below was used for the external file of my js-->
    <script src="script.js"></script>
    <!-- the link below is used for the icone's-->
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/res/Layer0.png">
    <title> INSTITUT ALGERIEN DU PETROLE</title>
</head>

<body>
<div class="form-Bg">

    <div class="form-header">
        <img src="${pageContext.request.contextPath}/res/Layer1.png" class="logo">
        <h2>Digitalisation des Documents Administratifs</h2>
       
    </div>

    <form action="Login" method="post">


        <label class="emailBox invalid">
            <input type="text" id="text" name="uname" placeholder="SON..." required="required" ${uname}/>
            <span class="emailText invalid">${invalidUname}</span>
        </label>

        <label class="emailBox invalid">
            <input type="password" id="password" name="pass" placeholder="Mot de passe" required="required" ${pass}/>
            <span class="emailText invalid">${invalidPass}</span>
        </label>


        <div class="form-group">
            <button type="submit">Connexion</button>
        </div>
    </form>


</div>




<div class="animation-area"></div>
<ul class="box-area">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">
    <img src="${pageContext.request.contextPath}/res/Layer0.png">

</ul>


<%--<script>--%>
<%--    const email = document.getElementById("email");--%>
<%--    const password = document.getElementById("password");--%>

<%--    email.addEventListener('input', () => {--%>
<%--        const emailBox = document.querySelector('.emailBox');--%>
<%--        const emailText = document.querySelector('.emailText');--%>
<%--        const emailPattern = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$/;--%>

<%--        if (email.value.match(emailPattern)) {--%>
<%--            emailBox.classList.add('valid');--%>
<%--            emailBox.classList.remove('invalid');--%>
<%--            emailText.innerHTML = "Your Email Address in Valid";--%>
<%--        } else {--%>
<%--            emailBox.classList.add('invalid');--%>
<%--            emailBox.classList.remove('valid');--%>
<%--            emailText.innerHTML = "Must be a valid email address.";--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
</body>

<footer>


    <!-- < svg class="wavee" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
    <path fill="#F26526" fill-opacity="1" d="M0,192L40,208C80,224,160,256,240,261.3C320,267,400,245,480,240C560,235,640,245,720,234.7C800,224,880,192,960,197.3C1040,203,1120,245,1200,229.3C1280,213,1360,139,1400,101.3L1440,64L1440,320L1400,320C1360,320,1280,320,1200,320C1120,320,1040,320,960,320C880,320,800,320,720,320C640,320,560,320,480,320C400,320,320,320,240,320C160,320,80,320,40,320L0,320Z"></path>
  </svg>
-->

    <svg class="wavee" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
        <path fill="#F26526" fill-opacity="1" d="M0,256L40,234.7C80,213,160,171,240,154.7C320,139,400,149,480,176C560,203,640,245,720,245.3C800,245,880,203,960,202.7C1040,203,1120,245,1200,245.3C1280,245,1360,203,1400,181.3L1440,160L1440,320L1400,320C1360,320,1280,320,1200,320C1120,320,1040,320,960,320C880,320,800,320,720,320C640,320,560,320,480,320C400,320,320,320,240,320C160,320,80,320,40,320L0,320Z"></path></svg>
</footer>


</html>