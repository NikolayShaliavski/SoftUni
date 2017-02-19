<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Book</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <c:set var="book" value="${BOOK_VIEW}"/>
        <form method="post">
            <label>Title
                <input type="text" name="title" value="${book.title}" readonly/>
            </label>
            <label>Author
                <input type="text" name="author" value="${book.author}"/>
            </label>
            <label>Pages
                <input type="text" name="pages" value="${book.pages}"/>
            </label>
            <input type="submit" name="edit" value="Edit"/>
        </form>
    </body>
</html>
