package fr.avainfo.loginwebsite.thread;

import jakarta.servlet.http.HttpSession;

public class SessionDeleter implements Runnable {

    private HttpSession session;
    private String sessionToChange;

    public SessionDeleter(HttpSession session, String sessionToDelete) {
        this.session = session;
        this.sessionToChange = sessionToDelete;
    }

    @Override
    public void run() {
        try {
            ////////////SESSION///////////////
            // - username : test
            // - userpassword : password
            // - connected : true
            //////////////////////////////////
            Thread.sleep(15000*60);
            session.setAttribute(sessionToChange, false);
            ////////////SESSION///////////////
            // - username : test
            // - userpassword : password
            //////////////////////////////////
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
