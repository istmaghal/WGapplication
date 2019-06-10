package com.example.wgapplication;

public class Event {


    String name;
    String ort;
    int tag;
    int monat;
    int jahr;
    int uhr;
    int minute;
    String beschreibung;

    public Event(String name, String ort, int tag, int monat, int jahr, int uhr, int minute, String beschreibung) {
this.name=name;
this.ort=ort;
this.tag=tag;
this.monat=monat;
this.jahr=jahr;
this.uhr=uhr;
this.minute=minute;
this.beschreibung=beschreibung;
    }

    public String getName() {
        return name;
    }

    public String getOrt() {
        return ort;
    }

    public int getTag() {
        return tag;
    }

    public int getMonat() {
        return monat;
    }

    public int getJahr() {
        return jahr;
    }

    public int getUhr() {
        return uhr;
    }

    public int getMinute() {
        return minute;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}