package com.example.segurados.model;

public class Tematica {
    private int idTematica;
    private String titulo;
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
