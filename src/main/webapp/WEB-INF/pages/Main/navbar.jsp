<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-3 left_col" xmlns:form="http://www.w3.org/1999/html">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Spring - Pong</span></a>
        </div>

        <div class="clearfix"></div>

        <div class="profile clearfix">
            <div class="profile_pic">
                <spring:url value="/resources/img/profiles/${currentUser.id}.jpeg" var="urlImg" />
                <img src="${urlImg}" alt="${currentUser.surname} ${currentUser.name}" class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>Bienvenue,</span>
                <h2>${currentUser.surname} ${currentUser.name}</h2>
            </div>
        </div>

        <br />

        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                    <li><a href="/SpringPong/profile/${currentUser.id}"><i class="fa fa-home"></i> Mon profil</a></li>
                    <li><a><i class="fa fa-cubes"></i> Mes compétitions <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <c:forEach items="${currentUserParticipations}" var="currentUserParticipation">
                                <li><a href="/SpringPong/competition/${currentUserParticipation.competition.id}">${currentUserParticipation.competition.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li><a data-toggle="modal" data-target="#createCompetition"><i class="fa fa-plus-circle"></i> Créer une compétition </a></li>
                </ul>
            </div>

        </div>

        <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Rechercher">
                <i class="fa fa-search" ></i>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Informations">
                <i class="fa fa-info-circle" ></i>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Réglages">
                <i class="fa fa-cog" ></i>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Déconnexion" href="/SpringPong/deconnection">
                <i class="fa fa-power-off" ></i>
            </a>
        </div>
    </div>
</div>

<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <spring:url value="/resources/img/profiles/${currentUser.id}.jpeg" var="urlImg" />
                        <img src="${urlImg}" alt="${currentUser.surname} ${currentUser.name}">${currentUser.surname} ${currentUser.name}
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="/SpringPong/profile/${currentUser.id}">Profil</a></li>
                        <li><a href="#">Réglages</a></li>
                        <li><a href="/SpringPong/deconnection"><i class="fa fa-power-off pull-right"></i>Déconnexion</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>

<div class="modal fade" id="createCompetition" tabindex="-1" role="dialog" aria-labelledby="createCompetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Créer une competition</h4>
            </div>

            <form:form modelAttribute="newCompetition" method="post" action="/SpringPong/addCompetition">
                <div class="modal-body">
                    <form:input id="name" type="text" class="form-control" path="name" placeholder="Nom de la competition" />
                    <form:textarea id="description" type="text" class="form-control" path="description" placeholder="Description de la compétition" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-primary">Ajouter la compétition</button>
                </div>
            </form:form>

        </div>
    </div>
</div>