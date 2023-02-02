package fr.avainfo.loginwebsite.cookiemanager;

import fr.avainfo.loginwebsite.thread.AutomaticRedirection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "test", value = "/test")
public class Cookies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("message", "coucou");
        cookie.setMaxAge(35);
        resp.addCookie(cookie);

        PrintWriter out = resp.getWriter();
        out.println("test");
        System.out.println(req);
        Thread t = new Thread(new AutomaticRedirection(req, resp));
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("test");


//        req.getRequestDispatcher("hi").forward(req, resp);

//        boolean alive = false;
//
//        for (Cookie cookie1 : req.getCookies()) {
//            if(cookie1.getName() == "message") {
//                alive = true;
//                break;
//            }
//        }
//
//        if(alive) {
//            // Le cookie est en vie !
//        } else {
//            // Le cookie est dead !
//        }

//        if(((Cookie[]) req.getCookies())[0].getMaxAge() == 0) {
//            // redirection vers page Login
//        } else {
//            // redirection vers page Principal
//        }

        while (isAlive("message", req.getCookies())) {
            // Le cookie est en vie !
        }
        // code a éxecuté quand le cookie meurt
    }

    public boolean isAlive(String cookieName, Cookie[] cookies) {
        boolean alive = false;

        for (Cookie cookie1 : cookies) {
            if(cookie1.getName() == "message") {
                alive = true;
                break;
            }
        }
        return alive;
    }
}
