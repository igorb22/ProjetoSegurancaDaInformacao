package com.example.segurados.model;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PontosUsuarioViewModel extends RealmObject {
    @PrimaryKey
    int id;
    @SerializedName("pontos")
    private int pontos;
    @SerializedName("tematicaModel")
    private Tematica tematica;

    public PontosUsuarioViewModel(){

    }
    public PontosUsuarioViewModel(int pontos, Tematica tematica) {
        this.tematica = tematica;
        this.pontos = pontos;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int autoIncrementId(){
        int key = 1;
        Realm realm = Realm.getDefaultInstance();
        try {
            if(realm.where(PontosUsuarioViewModel.class).max("id") != null)
            key = realm.where(PontosUsuarioViewModel.class).max("id").intValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }
}
