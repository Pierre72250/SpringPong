<%-- Created by IntelliJ IDEA. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Spring Pong</title>

        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
        <link href="${bootstrap}" type="text/css" rel="stylesheet">
        <spring:url value="/resources/js/jquery-3.1.1.min.js" var="jquery" />
        <script type="text/javascript" src="${jquery}"></script>
        <spring:url value="/resources/css/style.css" var="mainCss" />
        <link href="${mainCss}" rel="stylesheet" />

    </head>
    <body style="background:url(resources/img/background-springpong.jpg) no-repeat center center fixed; background-size:cover; ">
        <div class="col-md-offset-6 col-md-6">
            <h1>Spring-Pong</h1>
            <p>Outil de gestion de compétition, gratuit et simple d'utilisation</p>
        </div>
    </body>
</html>