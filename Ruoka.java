package com.example.lottasofiatuominen.yliopistonruokalat;

import java.util.ArrayList;

public class Ruoka {
    private String ruoka;
    Arvostelu new_arvostelu;
    ArrayList<Arvostelu> arvostelut = new ArrayList<>();

    public Ruoka(String r) {
        ruoka = r;
    }
    public void UusiArvostelu(String a1, float a2, String name) {
        new_arvostelu = new Arvostelu(a1,a2, name);
        arvostelut.add(new_arvostelu);
    }


    public String getRuoka() {
        return ruoka;
    }

    public ArrayList<Arvostelu> getArvostelut() {
        return arvostelut;
    }


    public String toString() {
        return ruoka;
    }

}
