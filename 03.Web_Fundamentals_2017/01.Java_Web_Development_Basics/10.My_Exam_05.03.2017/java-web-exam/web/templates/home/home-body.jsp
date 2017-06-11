<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Game Store</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<c:set var="loggedUser" value="${sessionScope.loggedUser}"></c:set>
<body>
<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">SoftUni Store</h1></div>
                <c:if test="${loggedUser.role == \"USER\"}">
                    <form class="form-inline">
                        Filter:
                        <input type="submit" name="filter" class="btn btn-link" value="All"/>
                        <input type="submit" name="filter" class="btn btn-link" value="Owned"/>
                    </form>
                </c:if>
                <c:set value="${1}" var="counter"/>
                <div class="card-group">
                    <c:forEach items="${games}" var="game">

                        <div class="card col-4 thumbnail">
                            <img class="card-image-top img-fluid img-thumbnail"
                                 onerror="this.src='https://i.ytimg.com/vi/BqJyluskTfM/maxresdefault.jpg';"
                                 src=${game.thumbnail}>

                            <div class="card-block">
                                <h4 class="card-title">${game.title}</h4>
                                <p class="card-text"><strong>Price</strong> - ${game.price} &euro;</p>
                                <p class="card-text"><strong>Size</strong> - ${game.size} GB</p>
                                <p class="card-text">${game.description}</p>
                            </div>

                            <div class="card-footer">
                                <c:if test="${loggedUser.role == \"ADMIN\"}">
                                    <a class="card-button btn btn-warning" name="edit" href="/edit-game/${game.id}">Edit</a>
                                    <a class="card-button btn btn-danger" name="delete" href="/delete-game/${game.id}">Delete</a>
                                </c:if>
                                <a class="card-button btn btn-outline-primary" name="info" href="/game-details/${game.id}">Info</a>

                                <c:if test="${loggedUser.role == \"USER\" &&
                                !loggedUser.containsGame(game.id) &&
                                !loggedUser.containsGameInCart(game.id)}">
                                    <a class="card-button btn btn-primary" name="buy" href="/buy/${game.id}">Buy</a>
                                </c:if>
                            </div>
                        </div>

                        <c:if test="${counter % 3 == 0}">
                            </div>
                            <div class="card-group">
                        </c:if>
                        <c:set value="${counter + 1}" var="counter"/>
                    </c:forEach>
                </div>
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