package com.example.segurados.model;

import com.google.gson.annotations.SerializedName;

public class Tematica {
    @SerializedName("idTematica")
    private int idTematica;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("descricao")
    private String descricao;

    public Tematica(int idTematica, String titulo, String descricao) {
        this.idTematica = idTematica;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(int idTematica) {
        this.idTematica = idTematica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
