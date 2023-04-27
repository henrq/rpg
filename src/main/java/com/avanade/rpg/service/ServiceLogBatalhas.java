package com.avanade.rpg.service;

import com.avanade.rpg.model.dto.LogBatalha;
import com.avanade.rpg.model.dto.Personagem;
import com.avanade.rpg.repository.RepositoryLogBatalhas;
import com.avanade.rpg.repository.RepositoryPersonagens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ServiceLogBatalhas {

    @Autowired
    private RepositoryLogBatalhas RepositoryLogLogBatalha;

    @Autowired
    private RepositoryPersonagens repositorioPersonagens;

    public List<LogBatalha> findAll() {
        return RepositoryLogLogBatalha.findAll();
    }

    public List<LogBatalha> findByIdBatalha(Integer batalha) {

        final List<LogBatalha> LogBatalha = RepositoryLogLogBatalha.findAllByBatalha(batalha);
        return LogBatalha;

    }

    public void updateLogBatalha(LogBatalha LogBatalha) {

        Optional<Personagem> avatar = repositorioPersonagens.findById(LogBatalha.getIdLog());
        if (avatar.isEmpty()) {
            return;
        }
        Optional<Personagem> inimigo = repositorioPersonagens.findById(LogBatalha.getIdLog());
        if (inimigo.isEmpty()) {
            return;
        }

        long horaAtual = System.currentTimeMillis();
        Integer partida = ((int) horaAtual) * -1;
        int jogada = 0;
        Integer turno = 0;
        Integer vidaAvatar = avatar.get().getVida();
        Integer vidainimigo = inimigo.get().getVida();
        String vencedor = "";
        LogBatalha.setIdLog(0);
        LogBatalha.setBatalha(partida);
        LogBatalha.setNomeAvatar(avatar.get().getNome());
        LogBatalha.setAvatarVida(avatar.get().getVida());
        LogBatalha.setInimigoVida(inimigo.get().getVida());
        LogBatalha.setIdLog(LogBatalha.getIdLog());
        LogBatalha.setNomeAvatar(LogBatalha.getNomeAvatar());
        LogBatalha.setNomeInimigo(LogBatalha.getNomeInimigo());
        LogBatalha.setAtaqueAvatar(0);
        LogBatalha.setDefesaAvatar(0);
        LogBatalha.setAtaqueInimigo(0);
        LogBatalha.setDefesaInimigo(0);
        LogBatalha.setTurno(0);
        LogBatalha.setVencedor("");

        RepositoryLogLogBatalha.save(LogBatalha);
        while (vencedor.equals("")) {
            turno++;

            if (jogada % 2 == 1) {

                if (LogBatalha.getAvatarVida() > 0) {
                    LogBatalha.setAvatarVida(LogBatalha.getAvatarVida());
                } else {
                    LogBatalha.setAvatarVida(avatar.get().getVida());
                }

                Random randomAtaque1 = new Random();
                int randomNumberAtaque1 = randomAtaque1.nextInt(12) + 1;
                int ataque1 = avatar.get().getForca() + avatar.get().getAgilidade() + randomNumberAtaque1;
                LogBatalha.setAtaqueAvatar(ataque1);

                Random randomDefesa2 = new Random();
                int randomNumberDefesa2 = randomDefesa2.nextInt(12) + 1;
                int defesa2 = inimigo.get().getDefesa() + inimigo.get().getAgilidade() + randomNumberDefesa2;
                LogBatalha.setDefesaInimigo(defesa2);

                if (ataque1 > defesa2) {
                    int totalDados = avatar.get().getQuantidadeDados() * avatar.get().getFacesDados();

                    Random randomDano1 = new Random();
                    int randomNumberDano1 = randomDano1.nextInt(totalDados) + avatar.get().getQuantidadeDados();
                    int danoinimigo = avatar.get().getForca() + randomNumberDano1;

                    vidainimigo = vidainimigo - danoinimigo;
                    LogBatalha.setInimigoVida(vidainimigo);
                }

                if (vidainimigo <= 0) {
                    vencedor = LogBatalha.getNomeAvatar();
                    LogBatalha.setVencedor(vencedor);
                } else {
                    LogBatalha.setVencedor("");
                }

                LogBatalha.setNomeAvatar(LogBatalha.getNomeAvatar());
                LogBatalha.setDefesaAvatar(LogBatalha.getDefesaAvatar());
                LogBatalha.setNomeInimigo(LogBatalha.getNomeInimigo());
                LogBatalha.setAtaqueInimigo(0);
                LogBatalha.setDefesaAvatar(0);

            } else {

                if (LogBatalha.getInimigoVida() > 0) {
                    LogBatalha.setInimigoVida(LogBatalha.getInimigoVida());
                } else {
                    LogBatalha.setInimigoVida(inimigo.get().getVida());
                }

                Random randomAtaque2 = new Random();
                int randomNumberAtaque2 = randomAtaque2.nextInt(12) + 1;
                int ataque2 = inimigo.get().getForca() + inimigo.get().getAgilidade() + randomNumberAtaque2;
                LogBatalha.setAtaqueInimigo(ataque2);

                Random randomDefesa1 = new Random();
                int randomNumberDefesa1 = randomDefesa1.nextInt(12) + 1;
                int defesa1 = avatar.get().getDefesa() + avatar.get().getAgilidade() + randomNumberDefesa1;
                LogBatalha.setDefesaAvatar(defesa1);

                if (ataque2 > defesa1) {
                    int totalDados = inimigo.get().getQuantidadeDados() * inimigo.get().getFacesDados();

                    Random randomDano2 = new Random();
                    int randomNumberDano2 = randomDano2.nextInt(totalDados) + inimigo.get().getQuantidadeDados();
                    int danoinimigo = avatar.get().getForca() + randomNumberDano2;

                    vidaAvatar = vidaAvatar - danoinimigo;
                    LogBatalha.setAvatarVida(vidaAvatar);
                }

                if (vidaAvatar <= 0) {
                    vencedor = LogBatalha.getNomeInimigo();
                    LogBatalha.setVencedor(vencedor);
                } else {
                    LogBatalha.setVencedor("");
                }

                LogBatalha.setNomeAvatar(LogBatalha.getNomeAvatar());
                LogBatalha.setDefesaAvatar(LogBatalha.getDefesaAvatar());
                LogBatalha.setNomeInimigo(LogBatalha.getNomeInimigo());
                LogBatalha.setAtaqueInimigo(LogBatalha.getAtaqueInimigo());
                LogBatalha.setAtaqueAvatar(0);
                LogBatalha.setDefesaInimigo(0);
            }

            jogada++;
            LogBatalha.setTurno(turno);
            RepositoryLogLogBatalha.save(LogBatalha);
        }
    }
}
