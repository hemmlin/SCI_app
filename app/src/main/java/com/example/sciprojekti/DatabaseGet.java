package com.example.sciprojekti;

import java.io.Serializable;


public class DatabaseGet implements Serializable {

    private String name, kpl, vesi;
    private int id;

    public String getVesi() {
        return vesi;
    }

    public void setVesi(String city) {
        this.vesi = vesi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKpl() {
        return kpl;
    }

    public void setKpl(String hobby) {
        this.kpl = hobby;
    }
}
