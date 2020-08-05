package org.unnamedgroup.restapi.resources;

import org.unnamedgroup.restapi.model.Programma;
import org.unnamedgroup.restapi.security.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public  class ProgrammiDB {


        public static Programma getProgramma(int id) throws SQLException, ParseException {

            Programma cercato = new Programma();
            Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT *  FROM programma WHERE id_programma = ? ");


        pst.setInt(1, id);


        rs = pst.executeQuery();

        while (rs.next()) {
             cercato= new Programma(rs.getString("titolo"), rs.getString("descrizione"),
                    rs.getString("genere"), rs.getString("scheda_approfondimento"),
                    rs.getBoolean("is_serie"), rs.getInt("num_episodio_serie"),
                            rs.getInt("num_stagione_serie"));


        }

        dbConnection.close();
        return cercato;
    }

}
