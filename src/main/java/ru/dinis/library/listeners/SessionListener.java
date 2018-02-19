package ru.dinis.library.listeners;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

/**
 * Create by dinis of 08.11.17.
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        HashMap<String, HttpSession> sessionMap = (HashMap<String, HttpSession>) context.getAttribute("sessionMap");
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    HttpSession session = se.getSession();
    ServletContext context = session.getServletContext();
    HashMap<String, HttpSession> sessionMap = (HashMap<String, HttpSession>)context.getAttribute("sessionMap");
    sessionMap.remove(session.getId());
    }
}
