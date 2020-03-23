package com.example.segurados.model;

public class Usuario{
    private int idUsuario;
    private String nome;
    private String usuario;
    private String senha;
    private String perfil;

    public Usuario(){}

    public Usuario(int idUsuario, String nome, String usuario, String perfil,
                   String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.usuario = usuario;
        this.perfil = perfil;
        this.senha = senha;
    }

    public Usuario(String nome, String usuario, String perfil,
                   String senha) {
        this.nome = nome;
        this.usuario = usuario;
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
        this.usuario = email;
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
        return usuario;
    }

    public String getPerfil() {
        return perfil;
    }

    @Override
    public String toString() {
        return ("{ \n" +
                "\"idUsuario\": " +idUsuario+", \n"+
                "\"nome\": \"" +nome + "\",\n" +
                "\"email\": \"" +usuario+"\", \n" +
                "\"senha\": \"" +senha+"\", \n" +
                "\"perfil\": \"" +perfil+"\" \n"+
                "}");
    }
}
