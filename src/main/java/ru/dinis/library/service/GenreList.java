package ru.dinis.library.service;


import ru.dinis.library.beans.Genre;
import ru.dinis.library.database.DBManager;

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
public class GenreList {

    private ArrayList<Genre> genres = new ArrayList<Genre>();

    public void fullGenresList() {
        Connection conn = null;
        ResultSet rs = null;
        Statement stm = null;

        try {
            conn = DBManager.getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM genre ORDER BY name");

            while (rs.next()) {
                this.genres.add(new Genre(rs.getInt(1), rs.getString("name")));
            }
        } catch (SQLException e) {
            Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public ArrayList<Genre> getGenres() {
        if (this.genres.isEmpty()) {
            this.fullGenresList();
        }
        return this.genres;
    }
}
