package fr.avainfo.loginwebsite;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

import fr.avainfo.loginwebsite.thread.SessionDeleter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hi")
public class HelloServlet extends HttpServlet {
    String message;
    String signIn;
    private String usersname;
    private String userspassword;

    public void init() {
        message = "Sign in to your account ";
        signIn = "SIGN IN";
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersname = req.getParameter("username");
        req.getSession().setAttribute("username", usersname);
        userspassword = req.getParameter("userpassword");
        req.getSession().setAttribute("userpassword", userspassword);
        System.out.println(req.getParameter("check"));


        if ((boolean) req.getSession().getAttribute("connected")) {
            req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersname = req.getParameter("username");
        userspassword = req.getParameter("userpassword");
        req.getSession().setAttribute("username", usersname);
        req.getSession().setAttribute("userpassword", userspassword);

        ////////////SESSION///////////////
        // - username : test
        // - userpassword : password
        //////////////////////////////////

        if (usersname == null)
            usersname = "";
        if (userspassword == null)
            userspassword = "";

        if (usersname.equals("vraiutilisateurs") && userspassword.equals("vraimotdepasse")) {
            System.out.println(usersname);
            System.out.println(userspassword);
            if(req.getParameter("check") != null) {
                req.getSession().setAttribute("connected", "true");
            }

//            Thread thread = Thread.startVirtualThread(new SessionDeleter(req.getSession(), "connected"));
//            thread.start();

            req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
        } else {
            System.out.println(usersname);
            System.out.println(userspassword);
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }

    }


    @Override
    public void destroy() {
        super.destroy();
    }
}