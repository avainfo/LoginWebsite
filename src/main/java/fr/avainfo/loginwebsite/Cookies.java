package fr.avainfo.loginwebsite;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

public class Cookies extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("message", "coucou");
        cookie.setMaxAge(getMaxAge(5));
        resp.addCookie(cookie);

        if(((Cookie[]) req.getCookies())[0].getMaxAge() == 0) {
            // redirection vers page Login
        } else {
            // redirection vers page Principal
        }

        req.getSession().setAttribute("message", "cookie");

        req.getSession().removeAttribute("message");
    }

    private static int getMaxAge(int minutes) {
        return 60 * minutes;
    }
}
