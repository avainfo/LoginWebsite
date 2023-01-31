<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= ((String) request.getSession().getAttribute("name")) %>
</h1>
<br/>
<a href="login">Hello Servlet</a>
<% request.getTrailerFields().get("message"); %>
</body>
</html>