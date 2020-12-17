package com.example.clockon2.Med;

public class Ligne {

    private String ref_med ;
    private int num_p;
    private String dateDeb;
    private String duree;

    public Ligne(String ref_med, int num_p, String dateDeb, String duree) {
        this.ref_med = ref_med;
        this.num_p = num_p;
        this.dateDeb = dateDeb;
        this.duree = duree;
    }

    public String getRef_med() {
        return ref_med;
    }

    public int getNum_p() {
        return num_p;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public String getDuree() {
        return duree;
    }
}
