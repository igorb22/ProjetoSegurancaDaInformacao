package com.example.segurados.model;

import com.google.gson.annotations.SerializedName;

public class RankingViewModel {
    @SerializedName("nomeUsuario")
    private String nomeUsuario;
    @SerializedName("pontos")
    private int pontos;
    @SerializedName("perfil")
    private String perfil;

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
}
