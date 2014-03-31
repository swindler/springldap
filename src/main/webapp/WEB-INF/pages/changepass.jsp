<%--
  Created by IntelliJ IDEA.
  User: Artyom_Borkowsky
  Date: 3/27/14
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
 ${key}
<p>
    ${username}
</p>
<form action="/reset/${key}" method="post">
    <label for="pass">Type new password</label>
    <input id="pass" name="password" type="password" />
    <label for="pass_rep">Repeat new password</label>
    <input id="pass_rep" name="password_repeat" type="password" />
    <input type="submit"/>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>
</body>
</html>
