package org.unnamedgroup.restapi.model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Giuseppe
 */
public class Palinsesto {

    private int id_canale;
    private int id_programma;
    private Time ora_inizio;
    private Time ora_fine;
    private String fascia_oraria;
    private Date giorno_messa_in_onda;

    public Palinsesto(){}

    public Palinsesto(int id_canale, int id_programma, Time ora_inizio, Time ora_fine, String fascia_oraria, Date giorno_messa_in_onda) {
        this.id_canale = id_canale;
        this.id_programma = id_programma;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
        this.fascia_oraria = fascia_oraria;
        this.giorno_messa_in_onda = giorno_messa_in_onda;
    }

    public int getId_canale() {
        return id_canale;
    }

    public void setId_canale(int id_canale) {
        this.id_canale = id_canale;
    }

    public int getId_programma() {
        return id_programma;
    }

    public void setId_programma(int id_programma) {
        this.id_programma = id_programma;
    }

    public Time getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(Time ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public Time getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(Time ora_fine) {
        this.ora_fine = ora_fine;
    }

    public String getFascia_oraria() {
        return fascia_oraria;
    }

    public void setFascia_oraria(String fascia_oraria) {
        this.fascia_oraria = fascia_oraria;
    }

    public Date getGiorno_messa_in_onda() {
        return giorno_messa_in_onda;
    }

    public void setGiorno_messa_in_onda(Date giorno_messa_in_onda) {
        this.giorno_messa_in_onda = giorno_messa_in_onda;
    }
}
