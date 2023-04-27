package com.avanade.rpg.model.domain;

public class Personagem {

    private String tipo;
    public String nome;
    private int vida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int qtdDados;
    private int facesDados;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getQtdDados() {
        return qtdDados;
    }

    public void setQtdDados(int qtdDados) {
        this.qtdDados = qtdDados;
    }

    public int getFacesDados() {
        return facesDados;
    }

    public void setFacesDados(int facesDados) {
        this.facesDados = facesDados;
    }

    public Personagem(String tipo, String nome, int vida, int forca, int defesa, int agilidade, int qtdDados,
            int facesDados) {
        this.tipo = tipo;
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.qtdDados = qtdDados;
        this.facesDados = facesDados;
    }

    public Personagem() {
        super();
    }
}
