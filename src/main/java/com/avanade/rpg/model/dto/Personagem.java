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
}