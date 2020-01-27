package com.example.esame2019.common.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Utente {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;

    private String congnone;

    private int eta;

    private String email;

    @Ignore
    public Utente(int id){
        this.id = id;
    }

    public Utente(int id, String nome, String congnone, int eta, String email) {
        this.id = id;
        this.nome = nome;
        this.congnone = congnone;
        this.eta = eta;
        this.email = email;
    }

    @Ignore
    public Utente(String nome, String congnone, int eta, String email) {
        this.nome = nome;
        this.congnone = congnone;
        this.eta = eta;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCongnone() {
        return congnone;
    }

    public void setCongnone(String congnone) {
        this.congnone = congnone;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
