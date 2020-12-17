package com.example.clockon2.Prise;

public class Prise {

    private int num_prise;
    private String date,heure,descr;
    private String qte;
    private String ref_med;

    public Prise(int num_prise, String date, String heure, String descr, String qte, String ref_med) {
        this.num_prise = num_prise;
        this.date = date;
        this.heure = heure;
        this.descr = descr;
        this.qte = qte;
        this.ref_med = ref_med;
    }

    public Prise(String date, String heure, String descr, String qte, String ref_med) {
        this.date = date;
        this.heure = heure;
        this.descr = descr;
        this.qte = qte;
        this.ref_med = ref_med;
    }

    public int getNum_prise() {
        return num_prise;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getDescr() {
        return descr;
    }

    public String getQte() {
        return qte;
    }

    public String getRef_med() {
        return ref_med;
    }
}
