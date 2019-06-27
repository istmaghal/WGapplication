package com.example.wgapplication;

import java.io.Serializable;

public class Einkauf {
    private static final String TAG="Einkauf";
        private String artikel;
        private String menge;

    public Einkauf(String artikel, String menge){
        this.artikel = artikel;
        this.menge = menge;
    }

    public Einkauf() {
        this.artikel="null";
        this.menge="null";
    }

public void setArtikel(String artkl){
        this.artikel=artkl;
}
public  void setMenge(String men){
        this.menge=men;
}
    public String getArtikel(){
            return artikel;
        }

        public String getMenge(){
            return menge;
        }
}
