package com.example.projekt1.model;

import java.io.Serializable;

public class Dane implements Serializable {
    private String imie;
    private String nazwisko;
    private Integer liczbaOcen;
    private Double sredniaOcen;

    public Dane(String imie, String nazwisko, Integer liczbaOcen) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.liczbaOcen = liczbaOcen;
    }

    public Dane(String imie, String nazwisko, Integer liczbaOcen, Double sredniaOcen) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.liczbaOcen = liczbaOcen;
        this.sredniaOcen = sredniaOcen;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getLiczbaOcen() {
        return liczbaOcen;
    }

    public void setLiczbaOcen(Integer liczbaOcen) {
        this.liczbaOcen = liczbaOcen;
    }

    public Double getSredniaOcen() {
        return sredniaOcen;
    }

    public void setSredniaOcen(Double sredniaOcen) {
        this.sredniaOcen = sredniaOcen;
    }
}
