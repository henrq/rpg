package com.avanade.rpg.service;

import com.avanade.rpg.model.dto.LogBatalha;
import com.avanade.rpg.model.dto.Personagem;
import com.avanade.rpg.repository.LogBatalhasRepository;
import com.avanade.rpg.repository.PersonagensRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LogBatalhasService {

    @Autowired
    private LogBatalhasRepository repositoryLogBatalha;

    @Autowired
    private PersonagensRepository repositoryPersonagens;

    public List<LogBatalha> findAll() {
        return repositoryLogBatalha.findAll();
    }

    public void updateLogBatalha(LogBatalha LogBatalha) {

        Optional<Personagem> avatar = repositoryPersonagens.findById(LogBatalha.getIdLogBatalha());
        if (avatar.isEmpty()) {
            return;
        }
        Optional<Personagem> inimigo = repositoryPersonagens.findById(LogBatalha.getIdLogBatalha());
        if (inimigo.isEmpty()) {
            return;
        }

        long horaAtual = System.currentTimeMillis();
        Integer partida = ((int) horaAtual) * -1;
        int jogada = 0;
        Integer turno = 0;
        Integer vidaAvatar = avatar.get().getVida();
        Integer vidaInimigo = inimigo.get().getVida();
        String vencedor = "";
        LogBatalha.setIdLogBatalha(0);
        LogBatalha.setIdBatalha(partida);
        LogBatalha.setNomeAvatar(avatar.get().getNome());
        LogBatalha.setAvatarVida(vidaAvatar);
        LogBatalha.setInimigoVida(vidaInimigo);
        LogBatalha.setIdLogBatalha(LogBatalha.getIdLogBatalha());
        LogBatalha.setNomeAvatar(LogBatalha.getNomeAvatar());
        LogBatalha.setNomeInimigo(LogBatalha.getNomeInimigo());
        LogBatalha.setAtaque(0);
        LogBatalha.setDefesa(0);
        LogBatalha.setTurno(0);
        LogBatalha.setVencedor("");

        repositoryLogBatalha.save(LogBatalha);
        while (vencedor.equals("")) {
            turno++;

            if (jogada % 2 == 1) {

                if (LogBatalha.getAvatarVida() > 0) {
                    LogBatalha.setAvatarVida(LogBatalha.getAvatarVida());
                } else {
                    LogBatalha.setAvatarVida(avatar.get().getVida());
                }

                Random randomAtaque = new Random();
                int randomNumberAtaque = randomAtaque.nextInt(12) + 1;
                int ataque = avatar.get().getForca() + avatar.get().getAgilidade() + randomNumberAtaque;
                LogBatalha.setAtaque(ataque);

                Random randomDefesa = new Random();
                int randomNumberDefesa = randomDefesa.nextInt(12) + 1;
                int defesa = inimigo.get().getDefesa() + inimigo.get().getAgilidade() + randomNumberDefesa;
                LogBatalha.setDefesa(defesa);

                if (ataque > defesa) {
                    Random randomDano = new Random();
                    int randomNumberDano = randomDano
                            .nextInt(avatar.get().getQuantidadeDados() * avatar.get().getFacesDados())
                            + avatar.get().getQuantidadeDados();
                    int danoinimigo = avatar.get().getForca() + randomNumberDano;

                    vidaInimigo = vidaInimigo - danoinimigo;
                    LogBatalha.setInimigoVida(vidaInimigo);
                }

                if (vidaInimigo <= 0) {
                    vencedor = LogBatalha.getNomeAvatar();
                    LogBatalha.setVencedor(vencedor);
                } else {
                    LogBatalha.setVencedor("");
                }

                LogBatalha.setNomeAvatar(LogBatalha.getNomeAvatar());
                LogBatalha.setDefesa(LogBatalha.getDefesa());
                LogBatalha.setNomeInimigo(LogBatalha.getNomeInimigo());
                LogBatalha.setAtaque(0);
                LogBatalha.setDefesa(0);

            }
            jogada++;
            LogBatalha.setTurno(turno);
            repositoryLogBatalha.save(LogBatalha);
        }
    }
}
