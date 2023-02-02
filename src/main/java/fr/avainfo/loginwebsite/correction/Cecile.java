package fr.avainfo.loginwebsite.correction;


import fr.avainfo.loginwebsite.exocookie.ServletCookieCommented;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "cookie", value = "/cookie")
public class Cecile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Cette Méthode verifie dans un premier temps si le cookie existe, sinon elle en crée "firstcookie"
        Si le cookie existe et qu'il a le nom défni dans dopost "nomdecookie", on reste sur la page cookie.jsp
        si le cookie est expiré on est renvoyé vers pluscookies.jsp
        */
        if(isAlive("firstTime", request.getCookies())) {
            if(isAlive("nomdecookie", request.getCookies())) {
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
        } else {
            response.addCookie(new Cookie("firstTime", "coucou"));
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
         * Cette fonction permet de lancer et stopper le timer
         * Elle verifie la méthode de requête
         * si la méthode est "start":
         * - Elle vérifie qu'il n'y a pas de cookie:
         *       - dans ce cas elle crée un nouveau cookie avec une durée de vie maximum et nom: "nomdecookie
         *       - sinon c'est que le cookie déjà
         * => Cela déclenche le timer
         * si la method est "stop": le timer est stoppé et le cookie est supprimé
         * si la méthode n'est pas reconnue, il y a juste un message d'erreur
         * Cette méthode renvoie vers la page cookie.jsp
         * */
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
        /*
         * Cette fonction supprime le cookie
         * */
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    public boolean isAlive(String cookieName, Cookie[] cookies) {
        /*
         * Cette fonction vérifie si le cokie existe
         * */
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