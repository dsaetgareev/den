package ru.dinis.library.database;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create by dinis of 31.10.17.
 */
public class DBManager {



    public static Connection getConnection() {
        Connection conn = null;
        try {
            DataSource ds = (DataSource) new InitialContext().lookup("jdbc/library");
            conn = ds.getConnection();
        } catch (SQLException | NamingException e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }

}
