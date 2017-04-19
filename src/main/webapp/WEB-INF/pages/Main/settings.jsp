<%-- Created by IntelliJ IDEA. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring Pong</title>
    <%@ include file="/WEB-INF/pages/Main/head.jsp" %>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <%@ include file="/WEB-INF/pages/Main/navbar.jsp" %>

        <div class="right_col" role="main">
            <form:form class="userInformationForm" modelAttribute="userInformationForm" method="post" action="/SpringPong/updateUserInformations">
                <div class="form-group">
                    <label for="name">Nom :</label>
                    <form:input type="text" class="form-control" id="name" path="name" placeholder="Nom" />
                </div>
                <div class="form-group">
                    <label for="surname">Prenom :</label>
                    <form:input type="text" class="form-control" id="surname" path="surname" placeholder="Prénom" />
                </div>
                <div class="form-group">
                    <label for="surname">Date de naissance :</label>
                    <input type="date" class="form-control" placeholder="Date de naissance"/>
                </div>


                <button type="submit" class="btn btn-primary">Modifier mes informations personnelles</button>
            </form:form>

            <hr/>

            <form:form class="userLoginForm" modelAttribute="userLoginForm" method="post" action="/SpringPong/updateUserLogin">
                <div class="form-group">
                    <label for="name">Adresse email :</label>
                    <form:input type="text" class="form-control" id="name" path="mail" placeholder="mail" />
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe : </label>
                    <form:input type="password" class="form-control" id="password" path="password" placeholder="Mot de passe" />
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirmation de mot de passe : </label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirmation de mot de passe" />
                </div>
                <button type="submit" class="btn btn-primary">Modifier mes identifiants de connexion</button>
            </form:form>

        </div>

<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jquery" />
<script type="text/javascript" src="${jquery}"></script>

<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
<script type="text/javascript" src="${bootstrapjs}"></script>

<spring:url value="/resources/js/theme.js" var="themejs" />
<script type="text/javascript" src="${themejs}"></script>

</body>
</html>