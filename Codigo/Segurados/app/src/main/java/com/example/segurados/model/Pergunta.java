package com.example.segurados.model;

public class Pergunta {
    private int idPergunta;
    private String questao;
    private String alternativa1;
    private String alternativa2;
    private String alternativa3;
    private String alternativa4;
    private int opcaoCorreta;
    private int pontuacao;
    private int tempo;
    private String dificuldade;
    private int tematicaIdTematica;


    public Pergunta(int idPergunta, String questao, String alternativa1, String alternativa2,
                    String alternativa3, String alternativa4, int opcaoCorreta, int pontuacao,
                    int tempo, String dificuldade, int tematicaIdTematica) {
        this.idPergunta = idPergunta;
        this.questao = questao;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.alternativa3 = alternativa3;
        this.alternativa4 = alternativa4;
        this.opcaoCorreta = opcaoCorreta;
        this.pontuacao = pontuacao;
        this.tempo = tempo;
        this.dificuldade = dificuldade;
        this.tematicaIdTematica = tematicaIdTematica;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public String getAlternativa3() {
        return alternativa3;
    }

    public void setAlternativa3(String alternativa3) {
        this.alternativa3 = alternativa3;
    }

    public String getAlternativa4() {
        return alternativa4;
    }

    public void setAlternativa4(String alternativa4) {
        this.alternativa4 = alternativa4;
    }

    public int getOpcaoCorreta() {
        return opcaoCorreta;
    }

    public void setOpcaoCorreta(int opcaoCorreta) {
        this.opcaoCorreta = opcaoCorreta;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getTematicaIdTematica() {
        return tematicaIdTematica;
    }

    public void setTematicaIdTematica(int tematicaIdTematica) {
        this.tematicaIdTematica = tematicaIdTematica;
    }
}
