<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page de redirection</title>
</head>
<body>
<%
    System.out.println("Test1");
    // mémoriser la redirection dans un cookie
    Cookie newRedirectCookie = new Cookie("lastRedirect", Long.toString(new Date().getTime()));
    newRedirectCookie.setMaxAge(5);
    response.addCookie(newRedirectCookie);
    // Récupérer le cookie 'lastRedirect'
    Cookie[] cookies = request.getCookies();
    Cookie redirectCookie = null;
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("lastRedirect")) {
            redirectCookie = cookie;
            break;
        }
    }
    System.out.println("Test2");

    if (redirectCookie != null) {
        System.out.println("Test2");
        long lastRedirectTime = Long.parseLong(redirectCookie.getValue());
        long currentTime = new Date().getTime();

        if (currentTime - lastRedirectTime < 5000) {
            System.out.println("Test3");
            // Le temps depuis la dernière redirection est inférieur à 5 secondes
            // Redirection automatique vers other_page.jsp
            response.sendRedirect("other_page.jsp");

            return;
        }
    }


%>
<h1>Vous allez être redirigé vers la page other_page.jsp dans 5 secondes...</h1>

<meta http-equiv="refresh" content="5;url=other_page.jsp">
</body>
</html>