package ru.dinis.library.service;


import ru.dinis.library.beans.Author;
import ru.dinis.library.database.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create by dinis of 31.10.17.
 */
public class AuthorList {

    private ArrayList<Author> authors = new ArrayList<Author>();

    public void fullAuthors() {
        ResultSet rs = null;
        Connection conn = DBManager.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT author.fio FROM author ORDER BY author.fio");
            rs = ps.executeQuery();
            while (rs.next()) {
                this.authors.add(new Author(rs.getString("fio")));
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public ArrayList<Author> getAuthors() {
        if (this.authors.isEmpty()) {
            fullAuthors();
        }
        return this.authors;
    }
}
