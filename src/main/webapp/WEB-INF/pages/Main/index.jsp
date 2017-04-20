<%-- Created by IntelliJ IDEA. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring Pong - Connection</title>

    <%@ include file="/WEB-INF/pages/Main/head.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />

</head>
<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <h1>Connectez vous</h1>
                <form:form modelAttribute="UserConnection" method="post" action="/SpringPong/connection">
                    <div>
                        <form:input id="mail" type="email" class="form-control" path="mail" placeholder="Adresse email" />
                    </div>
                    <div>
                        <form:input id="password" type="password" class="form-control" path="password" placeholder="Mot de passe" />
                    </div>
                    <div>
                        <button type="submit" class="btn btn-default submit">Connexion</button>
                    </div>
                </form:form>

                <div class="clearfix"></div>

                <div class="separator">
                    <p class="change_link">Nouveau sur le site ?
                        <a href="#signup" class="to_register"> Créer un compte </a>
                    </p>

                    <div class="clearfix"></div>
                    <br />

                    <div>
                        <h1><i class="fa fa-paw"></i> Spring Pong</h1>
                    </div>
                </div>
            </section>
        </div>

        <div id="register" class="animate form registration_form">
            <section class="login_content">
                <form:form modelAttribute="UserInscription" method="post" action="/SpringPong/addUser">
                    <h1>Créer un compte</h1>
                    <div>
                        <form:input type="text" class="form-control" id="name" path="name" placeholder="Nom" />
                    </div>
                    <div>
                        <form:input type="text" class="form-control" id="surname" path="surname" placeholder="Prénom" />
                    </div>
                    <div>
                        <form:input type="email" class="form-control" id="mail" path="mail" placeholder="exemple@exemple.fr" />
                    </div>
                    <div>
                        <form:input type="password" class="form-control" id="password" path="password" placeholder="Mot de passe" />
                    </div>
                    <div>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirmation de mot de passe" />
                    </div>
                    <button type="submit" class="btn btn-default submit">s'inscrire</button>


                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">Vous avez déjà un compte ?
                            <a href="#signin" class="to_register"> Connectez-vous ! </a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-paw"></i> Spring Pong</h1>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
    </div>
</div>
</body>
</html>