package com.example.wgapplication;

public class Besuch {

    Besuch(String art,int tV,int mV,int jV,int tB,int mB,int jB){
        beziehungsArt=art;
        vonTag=tV;
        vonMonat=mV;
        vonJahr=jV;
        bisTag=tB;
        bisMonat=mB;
        bisJahr=jB;
    }
    String name;
    String beziehungsArt;
    int vonTag;
    int vonMonat;
    int vonJahr;
    int bisTag;
    int bisMonat;
    int bisJahr;

    public String getName() {
        return name;
    }

    public String getBeziehungsArt() {
        return beziehungsArt;
    }

    public int getVonTag() {
        return vonTag;
    }

    public int getVonMonat() {
        return vonMonat;
    }

    public int getVonJahr() {
        return vonJahr;
    }

    public int getBisTag() {
        return bisTag;
    }

    public int getBisMonat() {
        return bisMonat;
    }

    public int getBisJahr() {
        return bisJahr;
    }

}
