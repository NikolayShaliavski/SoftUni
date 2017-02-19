<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Sign In</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <form method="post">
            <label>Username
                <input type="text" name="username"/>
            </label>
            <label>Password
                <input type="password" name="password"/>
            </label>
            <input type="submit" name="signin" value="Sign In"/>
        </form>
    </body>
</html>
