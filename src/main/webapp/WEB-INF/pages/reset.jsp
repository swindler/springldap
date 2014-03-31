<%--
  Created by IntelliJ IDEA.
  User: sw
  Date: 26.03.14
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset my pass</title>
</head>
<body>
<h1>${string}</h1>
<form method="post" action="/reset/">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="email" name="email"/>
    <input type="submit" value="Reset password">
</form>

</body>
</html>
