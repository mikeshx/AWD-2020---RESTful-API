package org.unnamedgroup.restapi.model;



/**
 *
 * @author Giuseppe
 */
public class Programma {

    private String titolo;
    private String descrizione;
    private String genere;
    private String scheda_approfondimento;
    private boolean is_serie;
    private int num_stagione_serie;
    private int num_episodio_serie;

public Programma() {}

    public Programma(String titolo, String descrizione, String genere, String scheda_approfondimento, boolean is_serie, int num_stagione_serie, int num_episodio_serie) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.genere = genere;
        this.scheda_approfondimento = scheda_approfondimento;
        this.is_serie = is_serie;
        this.num_stagione_serie = num_stagione_serie;
        this.num_episodio_serie = num_episodio_serie;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getScheda_approfondimento() {
        return scheda_approfondimento;
    }

    public void setScheda_approfondimento(String scheda_approfondimento) {
        this.scheda_approfondimento = scheda_approfondimento;
    }

    public boolean isIs_serie() {
        return is_serie;
    }

    public void setIs_serie(boolean is_serie) {
        this.is_serie = is_serie;
    }

    public int getNum_stagione_serie() {
        return num_stagione_serie;
    }

    public void setNum_stagione_serie(int num_stagione_serie) {
        this.num_stagione_serie = num_stagione_serie;
    }

    public int getNum_episodio_serie() {
        return num_episodio_serie;
    }

    public void setNum_episodio_serie(int num_episodio_serie) {
        this.num_episodio_serie = num_episodio_serie;
    }
}
