package org.unnamedgroup.restapi.model;

import java.sql.Time;

/**
 *
 */
public class PalinsestoCanaliProgrammi {
    private String canale;
    private String programma;
    private String genere;
    private Time data_inizio;
    private Time data_fine;
    private String url;

    public PalinsestoCanaliProgrammi(){};

    public PalinsestoCanaliProgrammi(String canale, String programma, String genere, Time data_inizio, Time data_fine, String url) {
        this.canale = canale;
        this.programma = programma;
        this.genere = genere;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.url = url;
    }

    public String getProgramma() {
        return programma;
    }

    public void setProgramma(String programma) {
        this.programma = programma;
    }

    public String getCanale() {
        return canale;
    }

    public void setCanale(String canale) {
        this.canale = canale;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Time getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(Time data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Time getData_fine() {
        return data_fine;
    }

    public void setData_fine(Time data_fine) {
        this.data_fine = data_fine;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
