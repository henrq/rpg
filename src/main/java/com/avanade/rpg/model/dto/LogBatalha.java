package com.avanade.rpg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Log_Batalhas")
public class LogBatalha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_LogBatalha")
    private Integer idLogBatalha;

    @Column(name = "Id_Batalha")
    private Integer idBatalha;
    @Column(name = "Turno")
    private Integer turno;
    @Column(name = "Nome_Avatar")
    private String nomeAvatar;
    @Column(name = "Nome_Inimigo")
    private String nomeInimigo;
    @Column(name = "Ataque")
    private Integer ataque;
    @Column(name = "Defesa")
    private Integer defesa;
    @Column(name = "Dano")
    private Integer dano;
    @Column(name = "Avatar_Vida")
    private Integer avatarVida;
    @Column(name = "Inimigo_Vida")
    private Integer inimigoVida;
    @Column(name = "Iniciativa")
    private String iniciativa;
    @Column(name = "Vencedor")
    private String vencedor;
    @Column(name = "Quando")
    private LocalDateTime quando;
}