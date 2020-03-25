package com.example.segurados.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RankingViewModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String nomeUsuario;
    private int pontos;
    private String perfil;

    public RankingViewModel(){

    }
    public RankingViewModel(String nomeUsuario, int pontos, String perfil) {
        this.pontos = pontos;
        this.nomeUsuario = nomeUsuario;
        this.perfil = perfil;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
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
            if( realm.where(RankingViewModel.class).max("id") != null)
                key = realm.where(RankingViewModel.class).max("id").intValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }
}
