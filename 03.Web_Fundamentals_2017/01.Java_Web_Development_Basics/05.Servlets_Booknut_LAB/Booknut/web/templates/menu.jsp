<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="booknut.models.bindingModels.LoginModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Menu</title>
    </head>
    <body>
        <nav>
            <a href="/">Home</a>
            <a href="/signup">Sign Up</a>
            <%--<a href="/signin">Sign In</a>--%>
            <%
                LoginModel loginModel = (LoginModel) session.getAttribute("LOGIN_MODEL");
                if (loginModel != null) {
                    String username = loginModel.getUserName();
                    request.setAttribute("USERNAME" ,username);
                }
            %>
            <c:set var="username" value="${USERNAME}" scope="session"/>
            <c:choose>
                <c:when test="${username != null}">
                    <a href="/signout">Sign Out(${username})</a>
                </c:when>
                <c:otherwise>
                    <a href="/signin">Sign In</a>
                </c:otherwise>
            </c:choose>
            <a href="/addbook">Add Book</a>
            <a href="/shelves">Shelves</a>
        </nav>
    </body>
</html>
