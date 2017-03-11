<%-- Created by IntelliJ IDEA. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Spring Pong</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
        <link href="${bootstrap}" type="text/css" rel="stylesheet">

        <spring:url value="/resources/css/font-awesome-4.7.0/css/font-awesome.css" var="fontawesome" />
        <link href="${fontawesome}" type="text/css" rel="stylesheet">

        <spring:url value="/resources/css/style.css" var="mainCss" />
        <link href="${mainCss}" rel="stylesheet" />

    </head>
    <body style="background:url(resources/img/background-springpong.jpg) no-repeat center center fixed; background-size:cover; ">
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="/SpringPong" class="navbar-brand">Spring Pong</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${!empty sessionScope.userSession}">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <strong>Mon compte</strong>
                                        <i class="fa fa-chevron-down" aria-hidden="true"></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <div class="navbar-login">
                                                <div class="row">
                                                    <div class="col-lg-4">
                                                        <p class="text-center">
                                                            <i class="fa fa-user-circle-o icon-size" aria-hidden="true"></i>
                                                        </p>
                                                    </div>
                                                    <div class="col-lg-8">
                                                        <p class="text-left"><strong>${sessionScope.userSession.surname} ${sessionScope.userSession.name}</strong></p>
                                                        <p class="text-left small">${sessionScope.userSession.mail}</p>
                                                        <p class="text-left">
                                                            <a href="/SpringPong/profile/${sessionScope.userSession.id}" class="btn btn-primary btn-block btn-sm">Mon profil</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="divider"></li>
                                        <li>
                                            <div class="navbar-login navbar-login-session">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <p>
                                                            <a href="/SpringPong/deconnection" class="btn btn-danger btn-block">Déconnexion</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="navbar-login navbar-login-session">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <p>
                                                            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                                                                Créer une compétition
                                                            </button>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <form:form class="form-inline pull-xs-right" modelAttribute="UserConnection" method="post" action="/SpringPong/connection">
                                    <form:input id="mail" type="email" class="form-control" path="mail" placeholder="Adresse email" />
                                    <form:input id="password" type="password" class="form-control" path="password" placeholder="Mot de passe" />
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-sign-in fa-lg fa-fw"></i> Connexion</button>
                                </form:form>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
        
        <div class="container">
            <div class="col-md-offset-6 col-md-6">

                <form:form class="inscriptionForm" modelAttribute="UserInscription" method="post" action="/SpringPong/addUser">
                    <div class="form-group">
                        <label for="name">Nom :</label>
                        <form:input type="text" class="form-control" id="name" path="name" placeholder="Nom" />
                    </div>
                    <div class="form-group">
                        <label for="surname">Prenom :</label>
                        <form:input type="text" class="form-control" id="surname" path="surname" placeholder="Prénom" />
                    </div>
                    <div class="form-group">
                        <label for="mail">Adresse mail :</label>
                        <form:input type="email" class="form-control" id="mail" path="mail" placeholder="exemple@exemple.fr" />
                    </div>
                    <div class="form-group">
                        <label for="password">Mot de passe : </label>
                        <form:input type="password" class="form-control" id="password" path="password" placeholder="Mot de passe" />
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Confirmation de mot de passe : </label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirmation de mot de passe" />
                    </div>
                    <button type="submit" class="btn btn-primary">s'inscrire</button>
                </form:form>

            </div>
        </div>
    </body>
</html>