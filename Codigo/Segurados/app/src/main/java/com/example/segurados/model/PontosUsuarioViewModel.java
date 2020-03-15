package com.example.segurados.model;

import com.google.gson.annotations.SerializedName;

public class PontosUsuarioViewModel {
    @SerializedName("pontos")
    private int pontos;
    @SerializedName("tematicaModel")
    private Tematica tematica;

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
}
