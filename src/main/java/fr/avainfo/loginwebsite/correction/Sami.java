package fr.avainfo.loginwebsite.correction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "cookie", value = "/cookie")
public class Sami extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on check dans la requête avec le request pour voir si dans nos cookies le cookie "firstTime" est toujours en vie
        if(isAlive("firstTime", request.getCookies())) {
            // si la première condition est validée, on check si le cookie "nomdecookie" existe et on le recupère via notre requête
            if(isAlive("nomdecookie", request.getCookies())) {
                // puis on appelle notre cookie.jsp grâce au Request Dispatcher et le forward
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            } else {
                //sinon on appelle le pluscookie.jsp
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
            // si les 2 premières conditions ne sont pas validées on ajoute un nouveau cookie "firstTime" avec comme valeur "coucou"
            // et on appelle notre cookie.jsp
        } else {
            response.addCookie(new Cookie("firstTime", "coucou"));
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Variable qui récupère le paramètre "timer"
        String timerMethod = request.getParameter("timer");

        // si le timerMethod est null on l'initie à rien
        if(timerMethod == null)
            // si le timerMethod égale à "start"
            timerMethod = "";
        if(timerMethod.equals("start")) {
            // et que le cookie "nomdecookie" n'est pas Alive
            if(!isAlive("nomdecookie", request.getCookies())) {
                // alors je créer un nouveau cookie "nomdecookie" avec comme valeur "valeurdecookie" que je set à 5sec
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                // une fois le cookie créé je renvoi qu'il a bien été créé car les conditions ont étés respectées
                System.out.println("Le cookie vient d'etre créé !");
            } else {
                //sinon je renvoi ça
                System.out.println("Le cookie est déjà créé !");
            }
            // si le timerethod = "stop"
        } else if(timerMethod.equals("stop")) {
            // j'appelle la méthode deleteCookie pour supprimer le cookie avec le name "nomdecookie" et la request
            deleteCookie("nomdecookie", request.getCookies());
        } else {
            // sinon je print ceci
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnue !");
        }
        // Je recupère le cookiejsp et lui envoi via le forward la response
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    // Méthode qui prend en paramètre le cookieName et un array de cookie
    // Si le nom du cookie envoyé est équivalent au nom de notre cookie
    // On set son MaxAge à 0 pour qu'il soit supprimé
    public void deleteCookie(String cookieName, Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    // Méthode qui prend en paramètre le cookieName et un array de cookie
    // Initialisation du booléen alive en statut false
    // Si l'objet correspond au name du cookie1 et au name du cookie on set la variable alive à true
    // Le break sert à stopper la verification du if
    // On retourne le statut de la variable alive après le conditionnement
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