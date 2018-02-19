package ru.dinis.library.listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Create by dinis of 08.11.17.
 */
public class ContextListener implements ServletContextListener {

    private HashMap<String, HttpSession> sessionMap = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("sessionMap", sessionMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("sessionMap");
        sessionMap = null;
    }
}
