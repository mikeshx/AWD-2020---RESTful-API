package org.unnamedgroup.restapi.security;

import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

public class DBManager {

    public static Connection getDBConenction(){

        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost/guida_tv?serverTimezone=UTC";
            Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, "root", "");

        } catch (SQLException | ClassNotFoundException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Check if the current user is admin
    public static boolean checkAdmin(String username) throws SQLException {

        Connection dbConnection = DBManager.getDBConenction();
        PreparedStatement pst = dbConnection.prepareStatement("SELECT is_admin FROM login WHERE username = ?");
        pst.setString(1, username);
        boolean result = false;
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            result = rs.getBoolean("is_admin");
        }
        dbConnection.close();
        return result;
    }
}
