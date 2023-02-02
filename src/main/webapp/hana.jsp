<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 01/02/2023
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %><%!
%>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form method="post" action="hello-servlet">
  <button type="submit"><%= ((String) request.getAttribute("stoptimer"))%></button>
</form>


</body>
</html>