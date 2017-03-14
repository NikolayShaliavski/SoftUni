<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Game Store</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

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
                </ul>
            </div>

        </div>
    </nav>
</header>
<!--Body of the store-->

<main>
    <div class="container">
        <div class="row">
            <h2 class="m-1">All Games &ndash;&nbsp;</h2>
            <a href="/add-game" class="btn btn-warning m-1"><strong>+</strong> Add Game</a>
            <button class="btn btn-outline-primary m-1" onclick="history.back()">Back</button>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Size</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${gameViews}" var="game">
                    <tr class="table-warning">
                        <th scope="row">${game.id}</th>
                        <td>${game.title}</td>
                        <td>${game.size} GB</td>
                        <td>${game.price} &euro;</td>
                        <td>
                            <a href="/edit-game" class="btn btn-warning btn-sm">Edit</a>
                            <a href="/delete-game" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>


<!--Footer-->
<footer>
    <div class="container modal-footer">
        <p>&copy; 2017 - Software University Foundation</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/static/scripts/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</body>
</html>
