<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Form</title>
  </head>
  <body>
    <form method="post">
      <label>Username</label>
      <input type="text" name="username">
      <label>Password</label>
      <input type="password" name="password">
      <input type="submit" name="create" value="Create User">
    </form>

  User: ${user.username}
  </body>
</html>
