package com.example.lottasofiatuominen.ht;

public class Ravintola {
    private String nimi;
    private int ID;

    public Ravintola(String n, int r) {
        nimi = n;
        ID = r;
    }

    public void setNimi(String new_nimi) {
        nimi = new_nimi;
    }

    public void setID(int new_ID) {
        ID = new_ID;
    }

    public int getID() {
        return ID;
    }
    public String toString() {
        return nimi;
    }
}


