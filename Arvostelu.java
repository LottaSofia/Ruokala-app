package com.example.lottasofiatuominen.yliopistonruokalat;

public class Arvostelu {

    // class that has in it the reviewer's name, review text and rating

    private String arvostelu;
    private float arvosana;
    private String arvostelija;

    public Arvostelu() {
        arvostelu = null;
        arvosana = 0;
    }
    public Arvostelu(String arvostelu, float arvosana, String arvostelija) {
        this.arvostelu = arvostelu;
        this.arvosana = arvosana;
        this.arvostelija = arvostelija;
    }

    public float getArvosana() {
        return arvosana;
    }
    public String getArvostelu() {
        return arvostelu;
    }

    public String getArvostelija() {
        return arvostelija;
    }

}
