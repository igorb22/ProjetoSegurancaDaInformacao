package com.example.segurados.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UsuarioHasPergunta extends RealmObject {
    @PrimaryKey
    @SerializedName("idPergunta")
    private int idPergunta;
    @SerializedName("idUsuario")
    private int idUsuario;
    @SerializedName("acertou")
    private int acertou;

    public UsuarioHasPergunta(){

    }
    public UsuarioHasPergunta(int idPergunta, int idUsuario, int acertou) {
        this.idPergunta = idPergunta;
        this.idUsuario = idUsuario;
        this.acertou = acertou;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setAcertou(int acertou) {
        this.acertou = acertou;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int isAcertou() {
        return acertou;
    }
}
