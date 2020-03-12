package com.example.segurados.model;

import com.google.gson.annotations.SerializedName;

public class UsuarioHasPergunta {
    @SerializedName("idPergunta")
    private int idPergunta;
    @SerializedName("idJogador")
    private int idUsuario;
    @SerializedName("acertou")
    private int acertou;

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
