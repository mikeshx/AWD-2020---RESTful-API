package org.unnamedgroup.restapi.model;

public class Canale {
    private int id;
    private String name;

    public Canale(){};

    public Canale(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
