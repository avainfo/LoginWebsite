package fr.avainfo.loginwebsite.exocookie;

import fr.avainfo.loginwebsite.bean.ClasseDeCours;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "cookie", value = "/cookie")
public class ServletCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(isAlive("firstTime", request.getCookies())) {
//            if(isAlive("nomdecookie", request.getCookies())) {
//                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
//            } else {
//                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
//            }
//        } else {
//            response.addCookie(new Cookie("firstTime", "coucou"));
//        }


        //TODO : change oop
//        this.getServletContext().setAttribute("firstBean", );
        this.getServletContext().getRequestDispatcher("/cookie.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timerMethod = request.getParameter("timer");

        if(timerMethod == null)
            timerMethod = "";
        if(timerMethod.equals("start")) {
            if(!isAlive("nomdecookie", request.getCookies())) {
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                System.out.println("Le cookie viens d'etre créer !");
            } else {
                System.out.println("Le cookie est déjà créer !");
            }
        } else if(timerMethod.equals("stop")) {
            deleteCookie("nomdecookie", request.getCookies());
        } else {
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnu !");
        }
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    public void deleteCookie(String cookieName, Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    public boolean isAlive(String cookieName, Cookie[] cookies) {
        boolean alive = false;

        for(Cookie cookie1 : cookies) {
            if(Objects.equals(cookie1.getName(), cookieName)) {
                alive = true;
                break;
            }
        }
        return alive;
    }
}
