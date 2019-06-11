package com.example.wgapplication;

import java.io.Serializable;

public class Einkauf {

        private String artikel;
        private String menge;

    public Einkauf(String artikel, String menge){
        this.artikel = artikel;
        this.menge = menge;
    }
        public String getArtikel(){
            return artikel;
        }

        public String getMenge(){
            return menge;
        }
}
