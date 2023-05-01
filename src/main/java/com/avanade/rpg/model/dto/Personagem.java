package com.avanade.rpg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Personagens")
public class Personagem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "Agilidade")
    private Integer agilidade;
    @Column(name = "Forca")
    private Integer forca;
    @Column(name = "Defesa")
    private Integer defesa;
    @Column(name = "Vida")
    private Integer vida;
    @Column(name = "Quantidade_Dados")
    private Integer quantidadeDados;
    @Column(name = "Faces_Dados")
    private Integer facesDados;

    public Personagem(String nome, String tipo, Integer agilidade, Integer forca, Integer defesa, Integer vida,
            Integer quantidadeDados, Integer facesDados) {
        this.nome = nome;
        this.tipo = tipo;
        this.agilidade = agilidade;
        this.forca = forca;
        this.defesa = defesa;
        this.vida = vida;
        this.quantidadeDados = quantidadeDados;
        this.facesDados = facesDados;
    }

}