package ru.dinis.library.beans;


import ru.dinis.library.database.DBManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Create by dinis of 01.11.17.
 */
public class Book {

    private long id;

    private String name;

    private byte[] content;

    private int pageCount;

    private String isbn;

    private String genre;

    private String author;

    private int publishDate;

    private String publisher;

    private byte[] image;

    public Book(long id, String name, byte[] content, int pageCount, String isbn, String genre, String author,
                int publishDate, String publisher, byte[] image) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.image = image;
    }

    public Book() {

    }

    public void fillPdfContent() {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT content FROM book WHERE book.id=" + this.getId());
            while (rs.next()) {
                this.setContent(rs.getBytes("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    stm.close();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
