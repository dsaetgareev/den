package ru.dinis.test;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create by dinis of 31.10.17.
 */
public class TestDb {

    DataSource ds;

    public void check() {
        Connection conn = null;
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/library");
            conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException ex) {

            Logger.getLogger(TestDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TestDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(TestDb.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
