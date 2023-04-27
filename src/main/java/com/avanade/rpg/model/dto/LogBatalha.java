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
@Table(name = "Log_Batalhas")
public class LogBatalha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Log")
    private Integer idLog;
    @Column(name = "Batalha")
    private Integer batalha;
    @Column(name = "Nome_Avatar")
    private String nomeAvatar;
    @Column(name = "Ataque_Avatar")
    private Integer ataqueAvatar;
    @Column(name = "Defesa_Avatar")
    private Integer defesaAvatar;
    @Column(name = "Avatar_Vida")
    private Integer avatarVida;
    @Column(name = "Nome_Inimigo")
    private String nomeInimigo;
    @Column(name = "Ataque_Inimigo")
    private Integer ataqueInimigo;
    @Column(name = "Defesa_Inimigo")
    private Integer defesaInimigo;
    @Column(name = "Inimigo_Vida")
    private Integer inimigoVida;
    @Column(name = "Iniciativa")
    private Integer iniciativa;
    @Column(name = "Turno")
    private Integer turno;
    @Column(name = "Vencedor")
    private String vencedor;
}