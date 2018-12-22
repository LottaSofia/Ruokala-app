package com.example.lottasofiatuominen.yliopistonruokalat;

import java.util.ArrayList;

public class Ravintola {
    private String nimi;
    private int ID;
    private ArrayList<Ruoka> ruokalista = new ArrayList<>();
    Ruoka ruoka;

    public Ravintola(String n, int i, String[] list) {
        nimi = n;
        ID = i;

        for (int j=0; j<list.length;j++) {
            ruoka = new Ruoka(list[j]);
            ruokalista.add(ruoka);
        }
    }
    public String getNimi() {
        return nimi;
    }
    public int getID() {
        return ID;
    }
    public ArrayList<Ruoka> getRuokalista() {
        return ruokalista;
    }
    public String toString() {
        return nimi;
    }


}
