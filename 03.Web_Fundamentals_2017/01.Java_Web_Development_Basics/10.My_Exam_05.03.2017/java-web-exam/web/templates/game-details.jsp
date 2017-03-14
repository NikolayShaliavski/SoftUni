<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <c:if test="${loggedUser.role == \"USER\"}">
                        <li class="nav-item">
                            <a class="nav-link" href="/cart">Cart</a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav">
                    <c:if test="${loggedUser.role == \"ADMIN\"}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="http://example.com" id="AdminDropdownMenuLink"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Admin
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="/games">Games</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Logout(ADMIN)</a>
                        </li>
                    </c:if>
                    <c:if test="${loggedUser.role == \"USER\"}">
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Logout(${loggedUser.email})</a>
                        </li>
                    </c:if>
                    <c:if test="${loggedUser == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register">Register</a>
                        </li>
                    </c:if>
                </ul>
            </div>

        </div>
    </nav>
</header>

<main>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="display-3">${gameView.title}</h1>
                <br/>

                <iframe width="560" height="315" src="https://www.youtube.com/embed/${gameView.trailer}" frameborder="0"
                        allowfullscreen></iframe>

                <br/>
                <br/>

                <p>${gameView.description}</p>
                <p><strong>Price</strong> - ${gameView.price}&euro;</p>
                <p><strong>Size</strong> - ${gameView.size} GB</p>
                <p><strong>Release Date</strong> -
                    <fmt:formatDate value="${gameView.releaseDate}" pattern="yyyy-MM-dd"/>
                </p>
                <button class="btn btn-outline-primary" onclick="history.back()">Back</button>
                <c:if test="${loggedUser.role == \"ADMIN\"}">
                    <a class="btn btn-warning" href="/edit">Edit</a>
                    <a class="btn btn-danger" href="/delete">Delete</a>
                </c:if>
                <c:if test="${loggedUser.role == \"USER\"}">
                    <a class="btn btn-primary" href="/buy/${game.id}">Buy</a>
                </c:if>
                <br/>
                <br/>

            </div>
        </div>
    </div>
</main>
<br/>

<footer>
    <div class="container modal-footer">
        <p>&copy; 2017 - Software University Foundation</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/static/scripts/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</body>
</html>
