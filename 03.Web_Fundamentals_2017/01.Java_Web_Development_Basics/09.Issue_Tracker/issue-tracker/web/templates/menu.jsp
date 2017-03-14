<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Issue Tracker</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="home"><a href="/">Home <span class="sr-only">(current)</span></a></li>
                <c:set var="user" value="${sessionScope.currentUser}"></c:set>
                <c:choose>
                    <c:when test="${user != null}">
                        <li id="issues"><a href="/issue">Issues</a></li>
                    </c:when>
                    <c:otherwise>
                        <li id="issues"><a href="/login">Issues</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:set var="user" value="${sessionScope.currentUser}"></c:set>
                <c:choose>
                    <c:when test="${user == null}">
                        <li id="login"><a href="/login">Log In</a></li>
                    </c:when>
                    <c:otherwise>
                        <li id="logout"><a href="/logout">Log Out(${user.getUsername()})</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
