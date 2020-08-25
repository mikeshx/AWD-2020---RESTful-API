package org.unnamedgroup.restapi.model;

import java.sql.Time;

import static java.sql.Types.NULL;

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
    private boolean isSerie;
    private int num_stagione;
    private int num_episodio;


    public PalinsestoCanaliProgrammi(){};

    public PalinsestoCanaliProgrammi(String canale, String programma, String genere, Time data_inizio, Time data_fine, String url) {
        this.canale = canale;
        this.programma = programma;
        this.genere = genere;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.url = url;
        this.isSerie=false;
        this.num_stagione=0;
        this.num_episodio=0;
    }

    public PalinsestoCanaliProgrammi(String canale, String programma, String genere, Time data_inizio, Time data_fine, String url, boolean isSerie, int num_stagione, int num_episodio) {
        this.canale = canale;
        this.programma = programma;
        this.genere = genere;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.url = url;
        this.isSerie=isSerie;
        this.num_stagione=num_stagione;
        this.num_episodio=num_episodio;
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

    public boolean isSerie() {
        return isSerie;
    }

    public void setSerie(boolean serie) {
        isSerie = serie;
    }

    public int getNum_stagione() {
        return num_stagione;
    }

    public void setNum_stagione(int num_stagione) {
        this.num_stagione = num_stagione;
    }

    public int getNum_episodio() {
        return num_episodio;
    }

    public void setNum_episodio(int num_episodio) {
        this.num_episodio = num_episodio;
    }
}
