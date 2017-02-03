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
        <div class="col-md-4">
            <h1>${userProfile.surname} ${userProfile.name}</h1>
        </div>
        <div class="col-md-8">
            <div class="container">
                <h3>Mes compétitions :</h3>
            </div>
        </div>
    </body>
</html>