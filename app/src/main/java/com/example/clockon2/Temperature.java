package com.example.clockon2;

public class Temperature {

    private int num_temp;
    private int degree ;
    private String login ;
    private String date ;

    public Temperature(int num_temp, int degree, String login,String date) {
        this.num_temp = num_temp;
        this.degree = degree;
        this.login = login;
        this.date = date ;
    }

    public int getNum_temp() {
        return num_temp;
    }

    public int getDegree() {
        return degree;
    }

    public String getDate() {
        return date;
    }

    public String getLogin() {
        return login;
    }
}
