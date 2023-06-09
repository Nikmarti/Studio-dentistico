package com.example.stden;

public class Paziente {
    private String nome;
    private String cognome;
    private String problema;
    private String cf;

    private String tel;


    private String data;



    public Paziente() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }



    public void setCf(String cf) {
        this.cf = cf;
    }
    public String getCf() {
        return cf;
    }



    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getProblema() {
        return problema;
    }



    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String toString() {
        return getNome() + ",    " + getCognome() + ",    " + getCf() + ",    " + getProblema() + ", " + getTel() +", " + getData();
    }


}