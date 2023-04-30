package com.avanade.rpg.service;

import com.avanade.rpg.model.dto.LogBatalha;
import com.avanade.rpg.model.dto.Personagem;
import com.avanade.rpg.repository.RepositoryLogBatalhas;
import com.avanade.rpg.repository.RepositoryPersonagens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Service
public class ServiceLogBatalhas {
    public static Personagem[] personagens;

    public static Personagem[] herois;
    public static Personagem[] monstros;

    public static Personagem inimigo;
    public static Personagem avatar;
    public static Personagem inimigoClone;
    public static Personagem avatarClone;

    public static Personagem personagemAtaque;
    public static Personagem personagemDefesa;

    public static int pontosAtaque;
    public static int pontosDefesa;

    static Scanner sc = new Scanner(System.in);

    @Autowired
    private RepositoryLogBatalhas repositoryLogBatalha;

    @Autowired
    private RepositoryPersonagens repositoryPersonagens;

    public List<LogBatalha> findAll() {
        return repositoryLogBatalha.findAll();
    }

    public Personagem[] listarPersonagens() {
        return (Personagem[]) repositoryPersonagens.findAll().toArray();
    }

    public Personagem[] listarMonstros() {
        return (Personagem[]) repositoryPersonagens.findAllMonster().toArray();
    }

    public static void qualquerCoisaContinua() {
        System.out.print("\nDigite qualquer coisa para continuar -> ");
        sc.next();
    }

    public static void regras() throws InterruptedException, IOException {
        LimparConsole();
        printLinha();
        escrever("REGRAS DO JOGO RPG - AVATAR X INIMIGO");
        escrever("Ao iniciar, será necessário escolher um personagem (herói ou monstro).");
        escrever("Cada personagem tem seus atributos únicos. Escolha com calma o seu personagem.");
        escrever("Os personagens possuem Pontos de Vida (PV).");
        escrever("Se um personagem ficar com PV igual ou abaixo de zero entao o oponente será o vencedor.");
        escrever("O dano causado por um ataque depende da força do atacante e da defesa do defensor, enquanto o dano");
        escrever("recebido depende da força do atacante e da resistência do defensor e da eficácia de sua defesa.");
        printLinha();
        qualquerCoisaContinua();
    }

    public static void Batalha() throws InterruptedException, IOException {
        LimparConsole();
        printCabecalho("A BATALHA VAI COMECAR!");
        escrever("AVATAR:" + avatar.getNome() + " X " + "INIMIGO:" + inimigo.getNome());
    }

    public static void Iniciativa() {
        printCabecalho(
                "Precisamos definir quem vai começar o jogo atacando ou como chamamos no RPG, quem terá a iniciativa.\nPara isso, jogue um dado de 20 faces (número possível de 1 a 20).\nNao temos empates e quem tirar o maior valor terá a iniciativa.");
    }

    private static void responderSimOuNao() {

        int input = 2;
        do {
            System.out.println("(1) Sim.");
            System.out.println("(2) Nao.");
            input = (int) ler("Resposta: ->", 2);
        } while (input != 1);
    }

    static int jogarDados(int QuantidadeDados, int LadosDado) {
        Random gerador = new Random();
        int resultado = gerador.nextInt(QuantidadeDados * LadosDado) + 1;
        return resultado;
    }

    public static void jogarDadosIniciativa() throws InterruptedException, IOException {
        escrever("Agora AVATAR " + avatar.getNome() + " jogue o dado de 20 faces.");
        escrever("Esta pronto?");
        responderSimOuNao();
        int pontosAvatar = jogarDados(1, 20);
        escrever("Voce obteve " + pontosAvatar + " pontos.");

        escrever("Agora é a vez do seu INIMIGO " + inimigo.getNome() + " joguar o dado de 20 faces.");
        escrever("Esta pronto?");
        responderSimOuNao();
        int pontosInimigo = jogarDados(1, 20);
        escrever("O seu inimigo obteve " + pontosInimigo + " pontos.");

        if (pontosAvatar > pontosInimigo) {
            escrever(avatar.getNome() + " voce esta com sorte, a iniciativa da batalha é sua.");
            personagemAtaque = avatar;
            personagemDefesa = inimigo;
        } else {
            escrever("Lamento " + avatar.getNome() + ", pois a iniciativa da batalha cabera ao seu inimigo "
                    + inimigo.getNome()
                    + ".");
            personagemAtaque = inimigo;
            personagemDefesa = avatar;
        }
    }

    public static int Atacar() throws InterruptedException, IOException {

        if (avatar == personagemAtaque) {
            printLinha();
            escrever(personagemAtaque.getNome() + " esta pronto para atacar com sua(s) " + personagemAtaque.getVida()
                    + " vidas?");
            responderSimOuNao();
        }

        pontosAtaque = jogarDados(1, 12) + personagemAtaque.getForca() + personagemAtaque.getAgilidade();
        printLinha();
        escrever("ATAQUE--> " + personagemAtaque.getNome() + " o seu ataque foi de " + pontosAtaque + " pontos.");

        return pontosAtaque;
    }

    public static int Defender() throws InterruptedException, IOException {

        if (avatar == personagemDefesa) {
            escrever(personagemDefesa.getNome() + " esta pronto para defender sua(s) " +
                    personagemDefesa.getVida() + " vidas?");
            responderSimOuNao();
        }

        pontosDefesa = jogarDados(1, 12) + personagemDefesa.getDefesa() + personagemDefesa.getAgilidade();
        printLinha();
        escrever("DEFESA--> " + personagemDefesa.getNome() + " a sua defesa foi de " + pontosDefesa + " pontos.");

        // LimparConsole();
        return pontosDefesa;
    }

    public static boolean CalcularDano() {
        boolean morreu = false;

        if (pontosAtaque > pontosDefesa) {
            int pontosDano = jogarDados(personagemDefesa.getQuantidadeDados(), personagemDefesa.getFacesDados());
            printLinha();

            personagemDefesa.setVida(personagemDefesa.getVida() - pontosDano);
            escrever(
                    personagemDefesa.getNome() + " Danos = " + pontosDano + " e ficou com " + personagemDefesa.getVida()
                            + " vidas");

            if (personagemDefesa.getVida() <= 0) {
                printLinha();
                escrever(personagemDefesa.getNome() + " com esse ataque acabou de morrer.");
                printLinha();
                escrever("O VENCEDOR DA BATALHA É " + personagemAtaque.getNome() + ", COM " + personagemAtaque.getVida()
                        + " VIDAS.");
                morreu = true;
            }
        }

        return morreu;
    }

    public static void mostrarPlacar() {
        escrever("PLACAR DA BATALHA: AVATAR [" + avatar.getVida() + "] X [" + inimigo.getVida() + "] INIMIGO");
    }

    public static void guerrear() throws InterruptedException, IOException {

        boolean terminajogo = false;

        Personagem personagemMudanca = avatar;

        do {
            Atacar();
            Defender();
            terminajogo = CalcularDano();
            personagemMudanca = personagemAtaque;
            personagemAtaque = personagemDefesa;
            personagemDefesa = personagemMudanca;
            mostrarPlacar();

        } while (!terminajogo);

        printLinha();
        escrever("THE END, A BATALHA TERMINOU...");

    }

    private static boolean continuarSimOuNao() {
        System.out.println("(1) Sim.");
        System.out.println("(2) Nao.");
        System.out.println("(Qualquer coisa) Sim.");
        int input = (int) ler("Resposta: ->", 2);
        if (input == 2)
            return false;
        else
            return true;
    }

    public static void jogar() throws InterruptedException, IOException {
        boolean continuaJogando = true;
        do {
            // this.CarregarPersonagensEMonstros();
            regras();
            escolherPersonagem();
            escolherInimigo();
            Batalha();
            Iniciativa();
            jogarDadosIniciativa();
            guerrear();
            escrever("Deseja Continuar Jogando?");
            continuaJogando = continuarSimOuNao();
        } while (continuaJogando);
    }

    public static void printLinha() {
        for (int i = 0; i < 110; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void LimparConsole() throws InterruptedException, IOException {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    public static void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    public static String espacosADireita(String nome) {
        String resultado = String.format("%-15.15s", nome);
        return resultado;
    }

    public static int ler(String prompt, int maximo) {
        int input;

        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(sc.next());
            } catch (Exception e) {
                input = 1;
                System.out.println("Digite um número inteiro entre 1 e " + maximo + "!");
            }
        } while (input < 1 || input > maximo);
        return input;
    }

    public static void printCabecalho(String titulo) {
        printLinha();
        System.out.println(titulo);
        printLinha();
    }

    private static boolean escolherResposta(boolean personagemCerto) throws InterruptedException, IOException {
        printLinha();
        int maximo = personagens.length;
        int personagemResposta = ler("Resposta: ->", maximo);
        LimparConsole();
        printCabecalho("Seu personagem é " + personagens[personagemResposta - 1].getNome() + ".\n Esta correto?");
        System.out.println("(1) Sim!");
        System.out.println("(2) Nao, quero mudar o personagem.");
        int input = ler("Resposta: ->", 2);
        if (input == 1) {
            personagemCerto = true;
            avatar = personagens[personagemResposta - 1];
        }
        return personagemCerto;
    }

    public void CarregarPersonagensEMonstros() {
        personagens = listarPersonagens();
        monstros = listarMonstros();
    }

    public static void escolherPersonagem() throws InterruptedException, IOException {

        printLinha();

        boolean personagemCerto = false;
        do {
            LimparConsole();
            int i = 0;
            escrever("Escolha o seu Personagem (Heroi ou Monstro)");
            printLinha();
            for (Personagem personagem : personagens) {
                escrever("(" + ++i + ")" + espacosADireita(personagem.getNome())
                        + espacosADireita(" Vidas: " + personagem.getVida())
                        + espacosADireita(" Forca: " + personagem.getForca())
                        + espacosADireita(" Defesa: " + personagem.getDefesa())
                        + espacosADireita(" Agilidade: " + personagem.getAgilidade())
                        + espacosADireita(" QDados: " + personagem.getQuantidadeDados())
                        + espacosADireita(" FDados: " + personagem.getFacesDados()));
            }
            personagemCerto = escolherResposta(personagemCerto);
        } while (!personagemCerto);

    }

    public static void escolherInimigo() throws InterruptedException, IOException {
        printLinha();
        boolean personagemCerto = false;
        do {
            LimparConsole();
            int i = 0;
            escrever("Escolha o seu Inimigo (Monstro)");
            printLinha();
            for (Personagem personagem : monstros) {
                escrever("(" + ++i + ")" + espacosADireita(personagem.getNome())
                        + espacosADireita(" Vidas: " + personagem.getVida())
                        + espacosADireita(" Forca: " + personagem.getForca())
                        + espacosADireita(" Defesa: " + personagem.getDefesa())
                        + espacosADireita(" Agilidade: " + personagem.getAgilidade())
                        + espacosADireita(" QDados: " + personagem.getQuantidadeDados())
                        + espacosADireita(" FDados: " + personagem.getFacesDados()));
            }
            printLinha();
            int maximo = monstros.length;
            int personagem = ler("Resposta: ->", maximo);
            LimparConsole();
            printCabecalho("Seu inimigo é " + monstros[personagem - 1].getNome() + ".\n Esta correto?");
            System.out.println("(1) Sim!");
            System.out.println("(2) Nao, quero mudar meu inimigo.");
            int input = ler("Resposta: ->", 2);
            ;
            if (input == 1) {
                personagemCerto = true;
                inimigo = monstros[personagem - 1];
            }
        } while (!personagemCerto);
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
