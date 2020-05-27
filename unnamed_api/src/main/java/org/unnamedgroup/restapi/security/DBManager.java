package org.unnamedgroup.restapi.security;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static Connection getDBConenction(){

        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost/guida_tv";
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
}
