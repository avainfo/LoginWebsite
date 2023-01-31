<%--
  Created by IntelliJ IDEA.
  User: Antonin
  Date: 31/01/2023
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%= request.getSession().getAttribute("name")%></h1>
<form action="login" method="get" >
    <label>
        <input type="name" name="username" value="username"  class="username" id="username"/>
    </label>
    <label>
        <input type="password" name="userpassword" value="userpassword"  class="userpassword" id="userpassword"/>
    </label>
    <input type="checkbox" name="check" id="check">
    <input type="submit" value="Bouton cliquer">
</form>
</body>
</html>
