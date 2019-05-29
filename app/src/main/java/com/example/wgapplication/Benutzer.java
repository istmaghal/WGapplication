package com.example.wgapplication;

public class Benutzer {

    private String benutzerName;
    private String password;
    private String nachname;
    private String vorname;
    private String geburtstag;
    private Boolean itsmane;
    private String telefon;
    private String email;
    private String beschaeftigung;
    private String F_Hochschule;
    private String wohntSeit;

    public Benutzer(String aname, String apassword) {
        this.benutzerName = aname;
        this.password = apassword;
        this.nachname = "nicht definiert";
        this.vorname = "nicht definiert";
        this.geburtstag = "nicht definiert";
        this.itsmane    = true;
        this.telefon = "nicht definiert";
        this.email = "nicht definiert";
        this.beschaeftigung = "nicht definiert";
        this.F_Hochschule = "nicht definiert";
        this.wohntSeit = "nicht definiert";
    }
    public Benutzer(String benutzername, String apassword, String nachname, String vorname, String geburtstag, Boolean itsmane,
                    String atelefon, String aemail, String abeschaeftigung, String aF_Hochschule, String awohntSeit){
        this.benutzerName = benutzername;
        this.password = apassword;
        this.nachname = nachname;
        this.vorname  = vorname;
        this.geburtstag = geburtstag;
        this.itsmane = itsmane;
        this.telefon = atelefon;
        this.email = aemail;
        this.beschaeftigung = abeschaeftigung;
        this.F_Hochschule = aF_Hochschule;
        this.wohntSeit = awohntSeit;
    }

    public Benutzer(String benutzerName){
        this.benutzerName = benutzerName;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public void setBenutzerName(String name) {
        this.benutzerName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String name) {
        this.nachname = name;
    }
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String name) {
        this.vorname = name;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBeschaeftigung() {
        return beschaeftigung;
    }

    public void setBeschaeftigung(String beschaeftigung) {
        this.beschaeftigung = beschaeftigung;
    }

    public String getF_Hochschule() {
        return F_Hochschule;
    }

    public void setF_Hochschule(String f_Hochschule) {
        F_Hochschule = f_Hochschule;
    }

    public String getWohntSeit() {
        return wohntSeit;
    }

    public void setWohntSeit(String wohntSeit) {
        this.wohntSeit = wohntSeit;
    }

    public Boolean getItsmane() {
        return itsmane;
    }

    public void setItsmane(Boolean itsmane) {
        this.itsmane = itsmane;
    }
}
