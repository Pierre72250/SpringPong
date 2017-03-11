<%-- Created by IntelliJ IDEA. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@ include file="/WEB-INF/pages/Main/head.jsp" %>

    <title>SpringPong - Profil de ${userProfile.surname} ${userProfile.name}</title>

</head>

<body class="nav-md">
    <div class="container body">
        <div class="main_container">

            <%@ include file="/WEB-INF/pages/Main/navbar.jsp" %>

            <div class="right_col" role="main">
                <h2>${userProfile.surname} ${userProfile.name}</h2>
                <div class="col-md-4">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Compétition</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${participations}" var="participation">
                            <tr class='clickable-row' data-href='/SpringPong/competition/${participation.competition.id}'>
                                <td>${participation.competition.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <spring:url value="/resources/js/jquery-3.1.1.min.js" var="jquery" />
    <script type="text/javascript" src="${jquery}"></script>

    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
    <script type="text/javascript" src="${bootstrapjs}"></script>

    <spring:url value="/resources/js/theme.js" var="themejs" />
    <script type="text/javascript" src="${themejs}"></script>
</body>
</html>