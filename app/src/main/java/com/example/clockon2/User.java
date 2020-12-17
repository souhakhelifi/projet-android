package com.example.clockon2;

class User {
    private String login;
    private String mdp;
    private String nom;
    private String age;
    private String profil;

    public User(String login, String mdp, String nom, String age, String profil) {
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.age = age;
        this.profil = profil;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getAge() {
        return age;
    }

    public String getProfil() {
        return profil;
    }
}
