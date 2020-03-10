package com.example.segurados.model;

import io.realm.RealmObject;

public class Usuario{
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String perfil;



    public Usuario(){}

    public Usuario(int idUsuario, String nome, String email, String perfil,
                   String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
        return ("{ \n" +
                "\"idUsuario\": " +idUsuario+", \n"+
                "\"nome\": \"" +nome + "\",\n" +
                "\"email\": \"" +email+"\", \n" +
                "\"senha\": \"" +senha+"\", \n" +
                "\"perfil\": \"" +perfil+"\" \n"+
                "}");
    }
}
