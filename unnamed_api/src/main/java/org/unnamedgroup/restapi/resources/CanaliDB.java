package org.unnamedgroup.restapi.resources;

import org.apache.commons.dbutils.DbUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.unnamedgroup.restapi.model.Canale;
import org.unnamedgroup.restapi.model.Palinsesto;
import org.unnamedgroup.restapi.model.PalinsestoCanaliProgrammi;
import org.unnamedgroup.restapi.model.Programma;
import org.unnamedgroup.restapi.security.DBManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CanaliDB {
    public static ArrayList<String[]> getCanali()  throws SQLException {
        ArrayList<String[]> result = new ArrayList<>();


        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT * FROM canale");





        rs = pst.executeQuery();


        while (rs.next()) {
            String[] canale = new String[3];

                canale[0] = rs.getString("nome");
                canale[1] = String.valueOf(rs.getInt("id_canale"));
                canale[2] = "http://localhost:8080/unnamed_api-1.0-SNAPSHOT/rest/canali/"+  canale[1]   +"/palinsesto";
           result.add(canale);
        }

        dbConnection.close();
        return result;
    }



    public static String getNome(int id) throws SQLException {

        String result = "";
        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT * FROM canale WHERE id_canale = ? ");


        pst.setInt(1, id);

        rs = pst.executeQuery();

        if (rs.next()) {
           result = rs.getString("nome");;

        }
        dbConnection.close();

        return result;
    }


    public static int getNumberOfCanali() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT COUNT(*) AS numero_canali FROM canale");

        int numero_canali = 0;

        rs = pst.executeQuery();

        if (rs.next()) {
            numero_canali = rs.getInt("numero_canali");;

        }
        dbConnection.close();
        return numero_canali;
    }



}
