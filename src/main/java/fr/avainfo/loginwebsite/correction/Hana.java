package fr.avainfo.loginwebsite.correction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

// on créer une web servelet avec le name cookie et value cookie dans une classe public qui extend Httpservelete qui contient
// 4 methodes doget dopost deleteCookie isAlive
@WebServlet(name = "cookie", value = "/cookie")
public class Hana extends HttpServlet {
    //     la doget a deux paramétre la request et la response
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // avec la methode is alive qui prends en paramétre cookiename et un tableau de  cookies qu'on récupére grace à la
        // request et on boucle dedans avec la boucle foreach pour récupérer le premier cookie et lui assigner la valeur true
        // qui nous permet de  vérifier si on a visiter la page au moins une fois
        if(isAlive("firstTime", request.getCookies())) {
            // aprés avec la méthode alive on parcoure le tableau pour récupérer le cookie nomdecookie et faire une redirection
            if(isAlive("nomdecookie", request.getCookies())) {
                // vers la page cookie.jsp
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            } else {
                // sinon si le maximumAge du cookie est 0 on redirige vers la page pluscokie
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
        } else {
            // et si notre cookie firstTime n'est pas créer On le créer et on l'ajoute et on fait une redirection vers
            // la page /cookie.jsp
            response.addCookie(new Cookie("firstTime", "coucou"));
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on stock notre paramétre timer qu'on a définie de notre form dans action
        String timerMethod = request.getParameter("timer");

        // On vérifie si timer methode est null on renvoie une chaine de caractére vide
        if(timerMethod == null)
            timerMethod = "";

        // On vérifie si la méthode egale à start
        if(timerMethod.equals("start")) {
            // et le cookie n'est plus en vie
            if(!isAlive("nomdecookie", request.getCookies())) {
                // On Instancier un Objet cookie avec le nom et la valeur
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                // et on lui donne une valeur de maximumAge(5);
                cookie.setMaxAge(5);
                // ensuite on ajoute le cookie
                response.addCookie(cookie);
                System.out.println("Le cookie viens d'etre créer !");
            } else {
                // sinon on envoie un message dans la console en disant que c'est déja créer
                System.out.println("Le cookie est déjà créer !");
            }
            // et si la timerMethode est stop, on le supprime avec la methode deleteCookie boucle dans le tableau et vérifie si
            // le maxAge est a 0 il le supprime
        } else if(timerMethod.equals("stop")) {
            deleteCookie("nomdecookie", request.getCookies());
            // sinon on envoi un message d'erreur
        } else {
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnu !");
        }
        // Aprés la vérification des conditions on fait une redirection vers /cookie jsp
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