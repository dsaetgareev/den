package ru.dinis.library.servlets;


import ru.dinis.library.beans.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Create by dinis of 08.11.17.
 */
public class PdfContent extends HttpServlet {

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        OutputStream out = resp.getOutputStream();
        try {
            int index = Integer.valueOf(req.getParameter("index"));
            HashMap sessionMap = (HashMap) getServletContext().getAttribute("sessionMap");
            HttpSession session = (HttpSession)sessionMap.get(req.getParameter("session_id"));

            ArrayList<Book> list = (ArrayList<Book>) session.getAttribute("currentBookList");
            Book book = list.get(index);
            book.fillPdfContent();
            resp.setContentLength(book.getContent().length);
            out.write(book.getContent());

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
}
