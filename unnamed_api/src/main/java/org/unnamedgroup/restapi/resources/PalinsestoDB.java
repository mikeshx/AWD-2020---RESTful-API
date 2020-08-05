package org.unnamedgroup.restapi.resources;

import org.unnamedgroup.restapi.model.Palinsesto;
import org.unnamedgroup.restapi.security.DBManager;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




public class PalinsestoDB {
    public static ArrayList<Palinsesto> getPalinsestoFromDate(String data) throws SQLException, ParseException {
        ArrayList<Palinsesto> Palinsesti = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");


        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT * FROM palinsesto WHERE giorno_messa_in_onda = ? ");


        pst.setDate(1, sqlDate);



        rs = pst.executeQuery();


        while (rs.next()) {
            Palinsesto temp = new Palinsesto(rs.getInt("id_canale"), rs.getInt("id_programma"),
                    rs.getTime("ora_inizio"), rs.getTime("ora_fine"),
                    rs.getString("fascia_oraria"), rs.getDate("giorno_messa_in_onda")
                    );

        Palinsesti.add(temp);
        }

        dbConnection.close();
        return Palinsesti;
    }


}

