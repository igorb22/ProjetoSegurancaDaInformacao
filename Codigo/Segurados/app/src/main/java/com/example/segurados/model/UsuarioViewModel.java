package com.example.segurados.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UsuarioViewModel extends RealmObject {
    @PrimaryKey
    private int idUsuario;
    private String nome;
    private String perfil;
    private String email;
    private String token;


    public UsuarioViewModel(){}

    public UsuarioViewModel(int idUsuario, String nome, String email, String perfil,
                   String token) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSenha() {
        return token;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }

    @Override
    public String toString() {
        return (nome + " - " + email);
    }
}
