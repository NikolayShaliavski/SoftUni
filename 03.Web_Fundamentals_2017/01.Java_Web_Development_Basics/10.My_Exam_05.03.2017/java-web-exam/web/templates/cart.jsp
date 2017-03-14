<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Game Store</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:set var="loggedUser" value="${sessionScope.loggedUser}"></c:set>
<header>
    <nav class="navbar navbar-toggleable-md navbar-light bg-warning">
        <div class="container">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="/">SoftUni Store</a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <%--<li class="nav-item">--%>
                    <%--<a class="nav-link" href="/cart">Cart</a>--%>
                    <%--</li>--%>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout(${loggedUser.email})</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>
</header>

<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">Your Cart</h1></div>
                <br/>
                <div class="list-group">
                    <c:forEach items="${games}" var="game">
                        <div class="list-group-item">
                            <a class="btn btn-outline-danger btn-lg" href="/cart/remove/${game.id}">X</a>
                            <div class="media col-3">
                                <figure class="pull-left">
                                    <a href="/game-details/${game.id}">
                                        <img class="card-image-top img-fluid img-thumbnail"
                                             onerror="this.src='https://i.ytimg.com/vi/BqJyluskTfM/maxresdefault.jpg';"
                                             src="${game.thumbnail}"></a>
                                </figure>
                            </div>
                            <div class="col-md-6">
                                <a href="/game-details/${game.id}"><h4 class="list-group-item-heading">${game.title}</h4></a>
                                <p class="list-group-item-text">${game.description}</p>
                            </div>
                            <div class="col-md-2 text-center mr-auto">
                                <h2>${game.price}&euro;</h2>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <br/>
                <div class="col-8 offset-2 my-3 text-center">
                    <h1><strong>Total Price - </strong> ${totalPrice} &euro;</h1>
                </div>
                <br/>
                <div class="col-8 offset-2 my-3">
                    <div class="input-group">
                        <div class="col-12">
                            <form method="post">
                                <input type="submit" class="btn btn-success btn-lg btn-block"
                                       value="Order"/>
                            </form>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-outline-primary btn-lg btn-block" onclick="history.back()">Back
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<footer>
    <div class="container modal-footer">
        <p>&copy; 2017 - Software University Foundation</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/static/scripts/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</body>
</html>
