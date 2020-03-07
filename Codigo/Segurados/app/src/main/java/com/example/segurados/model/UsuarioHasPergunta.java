package com.example.segurados.model;

public class UsuarioHasPergunta {
    private int idPergunta;
    private int idUsuario;
    private boolean acertou;

    public UsuarioHasPergunta(int idPergunta, int idUsuario, boolean acertou) {
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

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public boolean isAcertou() {
        return acertou;
    }
}
