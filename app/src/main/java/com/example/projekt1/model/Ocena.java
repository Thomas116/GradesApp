package com.example.projekt1.model;

import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;

public class Ocena {
    private String nazwa;
    @Min(2)
    @Max(5)
    private Integer wartosc;

    public Ocena(String nazwa, Integer wartosc) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }

    public Integer getWartosc() {
        return wartosc;
    }

    public void setWartosc(Integer wartosc) {
        this.wartosc = wartosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
