package com.example.segurados.model;

public class TematicaViewModel {
    private int pontos;
    private Tematica tematica;

    public TematicaViewModel(int pontos, Tematica tematica) {
        this.pontos = pontos;
        this.tematica = tematica;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }
}
