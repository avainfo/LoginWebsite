<jsp:useBean id="firstBean" scope="request" type="fr.avainfo.loginwebsite.bean.HelloWorldBean"/>
<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 02/02/2023
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Title</title>
</head>
<body>
<div class="center">
    <h1>Cookie</h1>
</div>
<div class="underbox">
    <form action="cookie?timer=stop" method="post">
        <button type="submit">Arreter le timer</button>
    </form>
    <form action="cookie?timer=start" method="post">
        <button type="submit">Démarrer le timer</button>
    </form>
<%--    <button onclick="${((firstBean) pageContext.getAttribute("firstBean")).}">${firstBean.message}</button>--%>
</div>
</body>
</html>
