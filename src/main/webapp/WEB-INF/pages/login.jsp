<%--
  Created by IntelliJ IDEA.
  User: Artyom_Borkowsky
  Date: 3/24/14
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body onload='document.loginForm.j_username.focus();'>
<h3>Custom Login Page</h3>

<%

%>

<form name='loginForm' action="<c:url value="/j_spring_security_check"/>"
      method='POST'>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value=''>
            </td>
            <td><a href='<c:url value="/reset"/>'>Restore password </a> </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password' />
            </td>
        </tr>
        <tr>
            <td><input name="submit" type="submit"
                       value="submit" />
            </td>
            <td><input name="reset" type="reset" />
            </td>
        </tr>
    </table>
    <c:if test="${error=='true'}">
            <div style="background-color: indianred;">Error while login:${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
    </c:if>

</form>
</body>
</html>
