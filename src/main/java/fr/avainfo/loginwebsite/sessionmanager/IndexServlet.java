package fr.avainfo.loginwebsite.sessionmanager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "index", value = "/index")
public class IndexServlet extends HttpServlet {

    private String desactivate;

    @Override
    public void init() throws ServletException {
        desactivate = "Activé";
        System.out.println("test");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals(desactivate, "Désactivé")) req.getRequestDispatcher("/page2.jsp").forward(req, resp);
        req.setAttribute("desactivate", desactivate);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        desactivate = (desactivate == "Désactivé") ? "Activé" : "Désactivé";
        req.getSession().setAttribute("desactivate", desactivate);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
