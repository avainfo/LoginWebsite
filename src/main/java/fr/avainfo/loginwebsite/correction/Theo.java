package fr.avainfo.loginwebsite.correction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

// Setup setting like #[Route()] for Symfony (Name & path)
@WebServlet(name = "cookie", value = "/cookie")
public class Theo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Utilisattion de la fonction isAlive() pour vérifier si le cookie est toujours OP
        // Vérification sur les 2 cookies (firstTime et nomducookie)
        if(isAlive("firstTime", request.getCookies())) {
            if(isAlive("nomdecookie", request.getCookies())) {
                // Toujours en vie => redirection sur cookie.jsp
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            } else {
                // Sinon direction la page qui assigne les cookies
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
        } else {
            // ajout d'information dans le cookies + redirect sur cookie.jsp
            response.addCookie(new Cookie("firstTime", "coucou"));
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // récupération du paramètre d'URL Timer
        String timerMethod = request.getParameter("timer");

        // Vérification que la timerMethod ne soit pas null, puis on l'initalise à vide
        if(timerMethod == null)
            timerMethod = "";
        if(timerMethod.equals("start")) {
            // Si le cookie est toujours en vie
            if(!isAlive("nomdecookie", request.getCookies())) {
                // On init un nouveau cookies, on lui donne un maximum de 5 secondes, on en renvoie le cookie ainsi qu'un message de sucessfull
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                cookie.setMaxAge(5);
                response.addCookie(cookie);
                System.out.println("Le cookie viens d'etre créer !");
            } else {
                // Sinon on balance que le cookie est déjà créée
                System.out.println("Le cookie est déjà créer !");
            }
        } else if(timerMethod.equals("stop")) {
            // Si timerMathod est sur stop, on delete le cookie "nomdecookie"
            deleteCookie("nomdecookie", request.getCookies());
        } else {
            // Sinon oui lui dis qu'on ne trouve pas la method
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnu !");
        }
        // Et on redirige sur cookie.jsp
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    public void deleteCookie(String cookieName, Cookie[] cookies) {
        // Pour tout les cookies, on récupère celui qui nous intéresse, et on lui dis qu'il à une durée de vie de 0 seconde, donc il se supprime
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }
    public boolean isAlive(String cookieName, Cookie[] cookies) {
        boolean alive = false;

        // Pour vérifie si un cookie est présent, on vérifie juste le nom
        for(Cookie cookie1 : cookies) {
            if(Objects.equals(cookie1.getName(), cookieName)) {
                alive = true;
                break;
            }
        }
        return alive;
    }
}