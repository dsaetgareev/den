package ru.dinis.library.servlets;


import ru.dinis.library.beans.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Create by dinis of 06.11.17.
 */
public class ShowImage extends HttpServlet {

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        OutputStream out = resp.getOutputStream();

        try {
            int index = Integer.valueOf(req.getParameter("index"));
            ArrayList<Book> list = (ArrayList<Book>) req.getSession(false).getAttribute("currentBookList");
            Book book = list.get(index);
            resp.setContentLength(book.getImage().length);
            out.write(book.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
