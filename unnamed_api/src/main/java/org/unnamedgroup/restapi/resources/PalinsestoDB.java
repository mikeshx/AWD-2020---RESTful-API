package org.unnamedgroup.restapi.resources;

import org.unnamedgroup.restapi.model.Palinsesto;
import org.unnamedgroup.restapi.model.PalinsestoCanaliProgrammi;
import org.unnamedgroup.restapi.model.Programma;
import org.unnamedgroup.restapi.security.DBManager;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




public class PalinsestoDB {
    public static ArrayList<PalinsestoCanaliProgrammi> getPalinsestoFromDate(String data) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> result = new ArrayList<>();


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
            Palinsesto pal_temp = new Palinsesto(rs.getInt("id_palinsesto"), rs.getInt("id_canale"), rs.getInt("id_programma"),
                    rs.getTime("ora_inizio"), rs.getTime("ora_fine"),
                    rs.getString("fascia_oraria"), rs.getDate("giorno_messa_in_onda")
                    );

            Programma prog_temp =  ProgrammiDB.getProgramma(pal_temp.getId_programma());

            PalinsestoCanaliProgrammi temp = new PalinsestoCanaliProgrammi(CanaliDB.getNome(pal_temp.getId_canale()), prog_temp.getTitolo(), prog_temp.getGenere(),
                    pal_temp.getOra_inizio(), pal_temp.getOra_fine(), prog_temp.getUrl_programma());
        result.add(temp);
        }

        dbConnection.close();
        return result;
    }

    public static ArrayList<PalinsestoCanaliProgrammi> getPalinsestoFromDateCanale(int canale, String data) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> result = new ArrayList<>();


        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");


        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT * FROM palinsesto WHERE giorno_messa_in_onda = ? AND id_canale = ?");


        pst.setDate(1, sqlDate);
        pst.setInt(2, canale);



        rs = pst.executeQuery();


        while (rs.next()) {
            Palinsesto pal_temp = new Palinsesto(rs.getInt("id_palinsesto"), rs.getInt("id_canale"), rs.getInt("id_programma"),
                    rs.getTime("ora_inizio"), rs.getTime("ora_fine"),
                    rs.getString("fascia_oraria"), rs.getDate("giorno_messa_in_onda")
            );

            Programma prog_temp =  ProgrammiDB.getProgramma(pal_temp.getId_programma());

            PalinsestoCanaliProgrammi temp = new PalinsestoCanaliProgrammi(CanaliDB.getNome(pal_temp.getId_canale()), prog_temp.getTitolo(), prog_temp.getGenere(),
                    pal_temp.getOra_inizio(), pal_temp.getOra_fine(), prog_temp.getUrl_programma());
            result.add(temp);
        }

        dbConnection.close();
        return result;
    }

    public static ArrayList<PalinsestoCanaliProgrammi> getPalinsestoFiltered(String filter, String search) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> result = new ArrayList<>();




        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        String SqlQuery = "SELECT * FROM palinsesto INNER JOIN canale ON palinsesto.id_canale=canale.id_canale INNER JOIN programma ON palinsesto.id_programma=programma.id_programma" +
                " WHERE " + filter + " like ? ";
        pst = dbConnection.prepareStatement(SqlQuery);

        System.out.print(SqlQuery);



        pst.setString(1, "%"+ search + "%");




        rs = pst.executeQuery();


        while (rs.next()) {
            Palinsesto pal_temp = new Palinsesto(rs.getInt("id_palinsesto"), rs.getInt("id_canale"), rs.getInt("id_programma"),
                    rs.getTime("ora_inizio"), rs.getTime("ora_fine"),
                    rs.getString("fascia_oraria"), rs.getDate("giorno_messa_in_onda")
            );

            Programma prog_temp =  ProgrammiDB.getProgramma(pal_temp.getId_programma());

            PalinsestoCanaliProgrammi temp = new PalinsestoCanaliProgrammi(CanaliDB.getNome(pal_temp.getId_canale()), prog_temp.getTitolo(), prog_temp.getGenere(),
                    pal_temp.getOra_inizio(), pal_temp.getOra_fine(), prog_temp.getUrl_programma());
            result.add(temp);
        }

        dbConnection.close();
        return result;
    }


}

