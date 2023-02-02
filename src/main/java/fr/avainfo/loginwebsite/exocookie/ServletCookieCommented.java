package fr.avainfo.loginwebsite.exocookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * On créer une web servelet avec le name cookie et value cookie dans une classe public qui extend Httpservelete qui contient
 * 4 methodes doget dopost deleteCookie isAlive
 */
@WebServlet(name = "cookie", value = "/cookie")
public class ServletCookieCommented extends HttpServlet {

    /**
     * @param request La requête
     * @param response La réponse
     * @throws ServletException
     * @throws IOException
     * Avec la methode is alive qui prends en paramétre cookiename et un tableau de  cookies qu'on récupére grace à la
     * request et on boucle dedans avec la boucle foreach pour récupérer le premier cookie et lui assigner la valeur true
     * qui nous permet de  vérifier si on a visiter la page au moins une fois
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *
         */
        if(isAlive("firstTime", request.getCookies())) {
            if(isAlive("nomdecookie", request.getCookies())) {
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
        } else {
            response.addCookie(new Cookie("firstTime", "coucou"));
        }

        this.getServletContext().getRequestDispatcher("/cookie.jsp").forward(request, response);

    }

    /**
     * @param request La requête
     * @param response La réponse
     * @throws ServletException
     * @throws IOException
     * Intercepte les requete POST
     */
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

    /**
     * @param cookieName Le nom du cookie qu'on veut supprimer
     * @param cookies Le tableau qui contient les cookies
     * Supprime le cookies
     */
    public void deleteCookie(String cookieName, Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    /**
     * @param cookieName Le nom du cookie qu'on veut supprimer
     * @param cookies Le tableau qui contient les cookies
     * @return boolean - Vrai si le cookie existe sinon Faux
     * Check si le cookie est alive
     */
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
