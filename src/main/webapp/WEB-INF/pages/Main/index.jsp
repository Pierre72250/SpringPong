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
    <body style="background:url(resources/img/background-springpong.jpg) no-repeat center center fixed; background-size:cover; ">
        <%@ include file="/WEB-INF/pages/Main/navbar.jsp" %>
        <div class="container">
            <div class="col-md-offset-6 col-md-6">

                <form:form class="inscriptionForm" modelAttribute="UserForm" method="post" action="/SpringPong/addUser">
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