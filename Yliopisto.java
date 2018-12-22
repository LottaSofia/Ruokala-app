package com.example.lottasofiatuominen.yliopistonruokalat;

import java.util.ArrayList;

public class Yliopisto {
    private ArrayList<Ravintola> ravintolat = new ArrayList<>();
    private static Yliopisto bd = new Yliopisto();
    Ravintola new_ravintola;

    public static Yliopisto getInstance() {
        return bd;
    }
    public Yliopisto() {
        String[] R0 = {};
        String[] R1 = {"Makkara","Jauhelihakeitto"};
        String[] R2 = {"Kanakastike","Kasvislasagne"};
        String[] R3 = {"Juuresratatouille","Kinkku salaatti"};
        String[] R4 = {"Uunilohi","Kasviskeitto"};

        new_ravintola = new Ravintola("Valitse ravintola", 0, R0);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("Laseri", 1, R1);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("Lut Buffet", 2, R2);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("AMK", 3, R3);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("Ylioppilastalo", 4, R4);
        ravintolat.add(new_ravintola);
    }

    public ArrayList<Ravintola> getRavintolat() {
        return ravintolat;
    }

}
