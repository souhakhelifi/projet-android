package com.example.clockon2.Prog;

public class Programme {
    private int num_p;
    private String maladie;
    private String dateDeb;
    private String duree;

    public Programme(int num_p,String dateDeb, String duree,String maladie) {
        this.num_p=num_p;
        this.maladie = maladie;
        this.dateDeb = dateDeb;
        this.duree = duree;
    }

    public Programme(String maladie, String dateDeb, String duree) {
        this.maladie = maladie;
        this.dateDeb = dateDeb;
        this.duree = duree;
    }

    public int getNum_p() {
        return num_p;
    }

    public String getMaladie() {
        return maladie;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public String getDuree() {
        return duree;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
}
