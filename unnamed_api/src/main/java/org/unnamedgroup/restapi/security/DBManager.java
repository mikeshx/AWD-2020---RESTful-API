package org.unnamedgroup.restapi.security;

import java.sql.*;

public class DBManager {

    public static Connection getDBConenction() {

        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost/guida_tv?serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, "root", "");

        } catch (SQLException | ClassNotFoundException ex) {
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

    // Add a new channel to the database
    public static boolean addChannel(String nome_canale) {
        try {

            Connection dbConnection = DBManager.getDBConenction();
            String query = " INSERT INTO canale (id_canale, nome) VALUES (NULL, ?)";

            PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
            preparedStmt.setString(1, nome_canale);

            int status = preparedStmt.executeUpdate();
            dbConnection.close();

            // If the rows are > 0 it means that the query has been executed successfully
            if (status > 0) return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    // Check if the name of a channel already exists
    public static boolean checkIfChannelExists(String nome_canale) throws SQLException {

        Connection dbConnection = DBManager.getDBConenction();

        PreparedStatement st = dbConnection.prepareStatement("select * from canale where nome = ?");
        st.setString(1, nome_canale);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    // Update the channel name by id
    public static boolean updateChannelInfo(int id_canale, String nuovo_nome) throws SQLException {

        Connection dbConnection = DBManager.getDBConenction();
        String query = "update canale set nome = ? where id_canale = ?";

        PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
        preparedStmt.setString(1, nuovo_nome);
        preparedStmt.setInt(2, id_canale);

        int status = preparedStmt.executeUpdate();
        dbConnection.close();

        // If the rows are > 0 it means that the query has been executed successfully
        if (status > 0) return true;

        return false;
    }
}

