package fr.avainfo.loginwebsite.correction;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "CorrectionCookie", value = "/CorrectionCookie")
public class Sarah extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // si le cookie firstTime existe
        if(isAlive("firstTime", request.getCookies())) {
            //si le nomdecookie existe
            if(isAlive("nomdecookie", request.getCookies())) {
                //on realise une redirection vers la pages cookie.jsp
                request.getRequestDispatcher("/cookie.jsp").forward(request, response);
            } else { //sinon on redirige vers page pluscookie.jsp
                request.getRequestDispatcher("/pluscookie.jsp").forward(request, response);
            }
        } else { //sinon on ajoute le cookie firstTime avec la valeur coucou
            response.addCookie(new Cookie("firstTime", "coucou"));
            // on réalise une redirection vers la page cookie.jsp
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //on crée une varibla String dans laquelle on récupère le paramètre timer
        String timerMethod = request.getParameter("timer");
        //si le timerMethode a une valeur null il retourne la valeur vide ""
        if(timerMethod == null)
            timerMethod = "";
        //si la valeur de timermethode est égale à start
        if(timerMethod.equals("start")) {
            //et si le nomdecookie n'existe pas
            if(!isAlive("nomdecookie", request.getCookies())) {
                //on instencie le cookie avec un nom: nomdecookie et une valeur: valeurdecookie
                Cookie cookie = new Cookie("nomdecookie", "valeurdecookie");
                //on donne une vie de 5 seconde
                cookie.setMaxAge(5);
                //on ajoute le cookie
                response.addCookie(cookie);
                //on affiche dans la console un message
                System.out.println("Le cookie viens d'etre créer !");
            } else { //sinon on affiche un autre message
                System.out.println("Le cookie est déjà créer !");
            }
            //sinon si la valeur de timermethode est égale à stop
        } else if(timerMethod.equals("stop")) {
            //on supprime le cookie nomdecookie
            deleteCookie("nomdecookie", request.getCookies());
        } else { //sinon on affiche dans la console que la méthode n'est pas reconnu
            System.out.println("La méthode [" + timerMethod + "] n'est pas reconnu !");
        }
        //on réalise une redirection vers la page cookie.jsp
        request.getRequestDispatcher("/cookie.jsp").forward(request, response);
    }

    // Fonction qui permet de supprimer le cookie
    public void deleteCookie(String cookieName, Cookie[] cookies) {
        // pour chaque cookie s'il a comme nom cookieName on initialise sa durée de vie à 0
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
            }
        }
    }

    //fonction booléenne indiquant si le cookie est en vie ou pas
    public boolean isAlive(String cookieName, Cookie[] cookies) {
        //on initie la vie à false (vie inexistante)
        boolean alive = false;
        // pour chaque cookie
        for(Cookie cookie1 : cookies) {
            //Si on récupère le nom de cookie
            if(Objects.equals(cookie1.getName(), cookieName)) {
                // on initie sa vie à true
                alive = true;
                break;
            }
        }
        //on retourne la valeur de alive
        return alive;
    }

}