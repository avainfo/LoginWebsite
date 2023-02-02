package fr.avainfo.loginwebsite.correction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Adrien extends HttpServlet {

    @Override
    // Methode Get pour vérifier la présence de 2 cookies" nommés firstTime" et "nomdecookie".
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Si le cookie "firstTime" existe et le cookie "nomdecookie" existe,
        // il transfère la demande à la page "/cookie.jsp".
        if(isAlive("firstTime", request.getCookies())) {
            if(isAlive("nomdecookie", request.getCookies())) {
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            }
            // Si le cookie "firstTime" existe et le cookie "nomdecookie" n'existe pas, il transfère la demande
            // à la page "/pluscookie.jsp".
            else {
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
        }
        //Si le cookie "firstTime" n'existe pas, création du cookie "firstTime" et transfère la demande
        // à la page "/cookie.jsp".
        else {
            response.addCookie(new Cookie("firstTime", "coucou"));
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //La méthode doPosT traite un paramètre "timer" passé dans la request
        String timerMethod = request.getParameter("timer");
        // Si le paramètre "timer" est égal à null il est initialisé avec une chaine de caractère vide.
        if(timerMethod == null)
            timerMethod = "";
        // si le paramètre "timer" est égal à "start", il crée le cookie "nomdecookie" s'il n'existe pas,
        if(timerMethod.equals("start")) {
            if(!isAlive("nomdecookie", request.getCookies())) {
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                System.out.println("Le cookie viens d'etre créer !");
                // sinon il affiche un message disant qu'il existe déjà.
            } else {
                System.out.println("Le cookie est déjà créer !");
            }
            // Sinon si le paramètre "timer" est égal à "stop",
            // il supprime le cookie "nomdecookie".
        } else if(timerMethod.equals("stop")) {
            deleteCookie("nomdecookie", request.getCookies());
            // Si le paramètre "timer" n'est pas reconnu, il affiche ce message.
        } else {
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnu !");
        }
        // Après avoir traité le paramètre "timer".La demande est transférée à la page "/cookie.jsp".
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    public void deleteCookie(String cookieName, Cookie[] cookies) {
        //La méthode deleteCookie prend un nom de cookie et un tableau de cookies,
        // et supprime le cookie avec le nom du cookie.

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    public boolean isAlive(String cookieName, Cookie[] cookies) {
        // La méthode isAlive prend un nom de cookie et un tableau de cookies et renvoie true si un cookie avec le nom donné existe.
        //  il renvoie false si il existe pas.
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