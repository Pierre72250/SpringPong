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