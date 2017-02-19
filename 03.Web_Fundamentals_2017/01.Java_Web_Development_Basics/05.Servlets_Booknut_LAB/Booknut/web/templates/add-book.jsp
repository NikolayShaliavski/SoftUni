<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Book</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <form method="post">
            <label>Title
                <input type="text" name="title"/>
            </label>
            <label>Author
                <input type="text" name="author"/>
            </label>
            <label>Pages
                <input type="text" name="pages"/>
            </label>
            <input type="submit" name="add" value="Add">
        </form>
    </body>
</html>
