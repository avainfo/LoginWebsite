<%@ page import="fr.avainfo.loginwebsite.bean.ClasseDeCours" %><%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 02/02/2023
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick=<% ((ClasseDeCours) session.getAttribute("classeDeCours")).setCurrentStudents(15); %>></button>
</body>
</html>
