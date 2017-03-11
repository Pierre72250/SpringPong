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
                    <h3>${currentCompetition.name}</h3>
                    <p>${currentCompetition.description}</p>
                    <c:choose>
                        <c:when test="${participe == false}">
                            <a href="/SpringPong/join/${currentCompetition.id}" class="btn btn-primary">Rejoindre la compétition</a>
                        </c:when>
                        <c:otherwise>
                            <a data-toggle="modal" data-target="#addResult" class="btn btn-primary"><i class="fa fa-plus-circle"></i> Entrer un résultat </a>
                        </c:otherwise>
                    </c:choose>
                    <div class="col-md-4">
                        <h3>Membres de la compétition : </h3>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Prénom</th>
                                <th>Nom</th>
                                <th>Score</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${participations}" var="participation">
                                <tr class='clickable-row' data-href='/SpringPong/profile/${participation.user.id}'>
                                    <td>${participation.user.name}</td>
                                    <td>${participation.user.surname}</td>
                                    <c:set var="elo" value="${'eloJ'.concat(participation.user.id)}"/>
                                    <td>${requestScope[elo]}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <h3>Historique des matchs : </h3>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Joueur 1</th>
                                <th>Score</th>
                                <th>Joueur 2</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${currentResultats}" var="resultat">
                                <tr>
                                    <td>${resultat.user_1.name} ${resultat.user_1.surname}</td>
                                    <td>${resultat.scoreUser_1} / ${resultat.scoreUser_2}</td>
                                    <td>${resultat.user_2.name} ${resultat.user_2.surname}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>

        <c:if test="${participe == true}">

            <div class="modal fade bd-example-modal-lg" id="addResult" tabindex="-1" role="dialog" aria-labelledby="addResult">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Ajouter un match</h4>
                        </div>

                        <form:form modelAttribute="newResultat" method="post" action="/SpringPong/addResult">

                            <div class="modal-body">
                                <div class="row">
                                    <form:select id="user_1" name="user_1" path="user_1">
                                        <c:forEach items="${participations}" var="participation">
                                            <option value="${participation.user.id}">${participation.user.name} ${participation.user.surname}</option>
                                        </c:forEach>
                                    </form:select>
                                    <form:input type="hidden" id="idCompetition" path="competition" value="${currentCompetition.id}" />
                                    <form:input type="text" class="test" id="scoreUser_1" path="scoreUser_1" />
                                    <span  class="test">VS</span>
                                    <form:input type="text" class="test" id="scoreUser_2" path="scoreUser_2" />
                                    <form:select id="user_2" name="user_2" path="user_2">
                                        <c:forEach items="${participations}" var="participation">
                                            <option value="${participation.user.id}">${participation.user.name} ${participation.user.surname}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                <button type="submit" class="btn btn-primary">Ajouter le match</button>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
        </c:if>

        <spring:url value="/resources/js/jquery-3.1.1.min.js" var="jquery" />
        <script type="text/javascript" src="${jquery}"></script>

        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <script type="text/javascript" src="${bootstrapjs}"></script>

        <spring:url value="/resources/js/theme.js" var="themejs" />
        <script type="text/javascript" src="${themejs}"></script>
    </body>
</html>