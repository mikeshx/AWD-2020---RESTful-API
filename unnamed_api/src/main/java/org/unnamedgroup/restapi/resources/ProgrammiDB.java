package org.unnamedgroup.restapi.resources;

import org.unnamedgroup.restapi.model.Palinsesto;
import org.unnamedgroup.restapi.model.PalinsestoCanaliProgrammi;
import org.unnamedgroup.restapi.model.Programma;
import org.unnamedgroup.restapi.security.DBManager;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public  class ProgrammiDB {

        public static String getTitoloFromId(int id) throws SQLException {

            String cercato = "";
            Connection dbConnection = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            dbConnection = DBManager.getDBConenction();
            pst = dbConnection.prepareStatement("SELECT titolo FROM programma WHERE id_programma = ? ");


            pst.setInt(1, id);


            rs = pst.executeQuery();

            if (rs.next()) {
                cercato= rs.getString("titolo");


            }

            dbConnection.close();
            return cercato;
    }


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
                    rs.getBoolean("is_serie"), rs.getInt("num_stagione_serie"),
                            rs.getInt("num_episodio_serie"), "http://localhost:8080/unnamed_api-1.0-SNAPSHOT/rest/programmi/"+id);


        }

        dbConnection.close();
        return cercato;
    }

    public static String getTitolo(int id) throws SQLException, ParseException {

            String titolo = "";
        Programma cercato = new Programma();
        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
        pst = dbConnection.prepareStatement("SELECT titolo  FROM programma WHERE id_programma = ? ");


        pst.setInt(1, id);


        rs = pst.executeQuery();

        if (rs.next()) {
            titolo= rs.getString("titolo");


        }

        dbConnection.close();
        return titolo;
    }

    public static boolean checkIfSeries(int prog_id) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        dbConnection = DBManager.getDBConenction();
        // pst = dbConnection.prepareStatement("SELECT * FROM palinsesto INNER JOIN programma ON palinsesto.id_programma=programma.id_programma where programma.id_programma = ?");
        pst = dbConnection.prepareStatement("SELECT * FROM programma  where id_programma = ?");


        pst.setInt(1, prog_id);


        rs = pst.executeQuery();

        if (rs.next()) {
            if (rs.getBoolean("is_serie")) return true;
        }
        return false;
    }

    public static ArrayList<PalinsestoCanaliProgrammi> getEpisodiProgramma(int id) throws SQLException, ParseException {

        ArrayList<PalinsestoCanaliProgrammi> cercato = new ArrayList<>();
        Connection dbConnection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        dbConnection = DBManager.getDBConenction();
       // pst = dbConnection.prepareStatement("SELECT * FROM palinsesto INNER JOIN programma ON palinsesto.id_programma=programma.id_programma where programma.id_programma = ?");
        pst = dbConnection.prepareStatement("SELECT * FROM palinsesto INNER JOIN canale ON palinsesto.id_canale=canale.id_canale INNER JOIN programma ON palinsesto.id_programma=programma.id_programma where programma.titolo = ?");


        pst.setString(1, getTitoloFromId(id));


        rs = pst.executeQuery();

      if (checkIfSeries(id)) {
          while (rs.next()) {
              Palinsesto pal_temp = new Palinsesto(rs.getInt("id_palinsesto"), rs.getInt("id_canale"), rs.getInt("id_programma"),
                      rs.getTime("ora_inizio"), rs.getTime("ora_fine"),
                      rs.getString("fascia_oraria"), rs.getDate("giorno_messa_in_onda")
              );

              Programma prog_temp = ProgrammiDB.getProgramma(pal_temp.getId_programma());

              PalinsestoCanaliProgrammi temp = new PalinsestoCanaliProgrammi(CanaliDB.getNome(pal_temp.getId_canale()), prog_temp.getTitolo(), prog_temp.getGenere(),
                      pal_temp.getOra_inizio(), pal_temp.getOra_fine(), prog_temp.getUrl_programma(), true ,prog_temp.getNum_stagione_serie(), prog_temp.getNum_episodio_serie());
              cercato.add(temp);
          }

      }





        dbConnection.close();
        return cercato;
    }

}
