package fr.avainfo.loginwebsite.correction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "cookie", value = "/cookie")
public class Oceane extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // vérifie si isAlive retourne true pour le cookie firstTime
        // sinon ajoute le cookie firstTime et redirige vers la page /cookie.jsp
        if(isAlive("firstTime", request.getCookies())) {
            // vérifie si isAlive retourne true pour le cookie nomdecookie
            //si oui : redirige à /cookie.jsp
            //si non redirige à /pluscookie.jsp
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
        //initialise timerMethod avec ce qu'on récupère de timer
        String timerMethod = request.getParameter("timer");

        // si timerMethod est null -> initialise à vide
        if(timerMethod == null)
            timerMethod = "";
        // si timerMethod est égal à start faire
        if(timerMethod.equals("start")) {
            // si isALive retourne faux pour nomdecookie
            // créé et ajoute un cookie nomdecookie qui a une durée de 5 seconde avec une valeur de valeur de cookie
            // sinon écrit dans la console Le cookie est déjà créer !
            if(!isAlive("nomdecookie", request.getCookies())) {
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                // test console de vérification de création
                System.out.println("Le cookie viens d'etre créer !");
            } else {
                System.out.println("Le cookie est déjà créer !"); //
            }
        } else if(timerMethod.equals("stop")) {
            // supprime le cookie nomdecookie
            deleteCookie("nomdecookie", request.getCookies());
        } else {
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnu !");
        }
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    // prend un nom de cookie et un tableau de cookies en paramètre
    public void deleteCookie(String cookieName, Cookie[] cookies) {
        //passe dans le tableau de cookie
        for(Cookie cookie : cookies) {
            // si le nom du cookie dans le tableau est égal au nom du cookie en paramètre
            // il initialise la durée de ce cookie à 0
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    // prend un nom de cookie et un tableau de cookies en paramètre
    // retourne un boolean qui permet de savoir si le cookie est présent ou non dans le tableau
    public boolean isAlive(String cookieName, Cookie[] cookies) {
        boolean alive = false;
        // passe dans le tableau et s'arrête si le nom du cookie est égal au nom du cookie dans les paramètres de la fonction
        for(Cookie cookie1 : cookies) {
            if(Objects.equals(cookie1.getName(), cookieName)) {
                alive = true;
                break;
            }
        }
        return alive;
    }
}