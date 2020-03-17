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

    public static Long autoIncrementId(){
        Long key = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            key = realm.where(PontosUsuarioViewModel.class).max("id").longValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }
}
