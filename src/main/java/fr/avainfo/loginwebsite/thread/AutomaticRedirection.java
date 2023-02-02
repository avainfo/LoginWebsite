package fr.avainfo.loginwebsite.thread;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AutomaticRedirection implements Runnable {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public AutomaticRedirection(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void run() {
        try {
            response.getWriter().println("test1");
            Thread.sleep(5000);
            response.getWriter().println("test2");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
