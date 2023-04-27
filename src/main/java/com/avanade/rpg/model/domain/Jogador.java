package com.avanade.rpg.model.domain;

import com.avanade.rpg.model.*;

public class Jogador {

    public String nome;
    public Personagem heroi;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public int atacar() {
        return 0;
    }

    public int defender() {
        return 0;
    }

}
