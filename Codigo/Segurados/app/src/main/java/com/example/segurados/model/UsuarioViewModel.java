package com.example.segurados.model;

import java.util.HashMap;

public class UsuarioViewModel {
    private int pontos;
    private Tematica tematica;

    public UsuarioViewModel(int pontos, Tematica tematica) {
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
