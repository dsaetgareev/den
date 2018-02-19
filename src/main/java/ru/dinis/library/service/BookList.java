package ru.dinis.library.service;


import ru.dinis.library.beans.Book;
import ru.dinis.library.database.DBManager;
import ru.dinis.library.enums.SearchType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create by dinis of 01.11.17.
 */
public class BookList {

    ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> getBooks(String str) {
        Connection conn = null;
        ResultSet rs = null;
        Statement stm = null;

        try {
            conn = DBManager.getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery(str);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setGenre(rs.getString("genre"));
                book.setIsbn(rs.getString("isbn"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setPublishDate(rs.getInt("publish_year"));
                book.setPublisher(rs.getString("publisher"));
                book.setImage(rs.getBytes("image"));
                books.add(book);
            }
        } catch (SQLException e) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    conn.close();
                    stm.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return this.books;
    }

    public ArrayList<Book> getAllBooks() {
        if (this.books.isEmpty()) {
            this.books = getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author," +
                    "g.name as genre, b.image from book b "
                    + "inner join author a on b.author_id=a.id "
                    + "inner join genre g on b.genre_id=g.id "
                    + "inner join publisher p on b.publisher_id=p.id");
        }
        return this.books;
    }

    public ArrayList<Book> getBooksByGenre(long id) {
        if (id == 0) {
            return this.getAllBooks();
        }
        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author," +
                "g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id "
                + "where genre_id=" + id + " order by b.name "
                + "limit 0,5");
    }

    public ArrayList<Book> getBooksByLetter(String letter) {
        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author," +
                "g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id "
                + "where substr(b.name,1,1)='" + letter + "' order by b.name "
                + "limit 0,5");
    }

    public ArrayList<Book> getBooksBySearch(String str, SearchType type) {
        StringBuilder sql = new StringBuilder("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author," +
                "g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id ");
        if (type == SearchType.AUTHOR) {
            sql.append("WHERE lower(a.fio) LIKE '%" + str.toLowerCase() + "%' ORDER BY b.name ");
        }
        if (type == SearchType.TITLE) {
            sql.append("WHERE lower(b.name) LIKE '%" + str.toLowerCase() + "%' ORDER BY b.name ");
        }
        sql.append("limit 0,5");

        return this.getBooks(sql.toString());
    }
}
