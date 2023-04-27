package com.avanade.rpg.model;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import com.avanade.rpg.model.domain.*;

public class Jogo {

    static Scanner sc = new Scanner(System.in);

    public static Personagem guerreiro = new Personagem("Heroi", "Guerreiro", 20, 7, 5, 6, 1, 12);
    public static Personagem barbaro = new Personagem("Heroi", "Barbaro", 21, 10, 2, 5, 2, 8);
    public static Personagem cavaleiro = new Personagem("Heroi", "Cavaleiro", 26, 6, 8, 3, 2, 6);

    public static Personagem orc = new Personagem("Monstro", "Orc", 42, 7, 1, 2, 3, 4);
    public static Personagem gigante = new Personagem("Monstro", "Gigante", 34, 10, 4, 4, 2, 6);
    public static Personagem lobisomen = new Personagem("Monstro", "Lobisomen", 34, 7, 4, 7, 2, 4);

    public static Personagem[] personagens = { guerreiro, barbaro, cavaleiro, orc, gigante, lobisomen };
    public static Personagem[] herois = { guerreiro, barbaro, cavaleiro };
    public static Personagem[] monstros = { orc, gigante, lobisomen };

    public static Personagem inimigo;
    public static Personagem avatar;
    public static Personagem inimigoClone;
    public static Personagem avatarClone;

    public static Personagem personagemAtaque;
    public static Personagem personagemDefesa;

    public static int pontosAtaque;
    public static int pontosDefesa;

    public Jogo() {
    }

    public static void recarregarVidasPersonagens(int guerreiroVida, int barbaroVida, int cavaleiroVida, int orcVida,
            int giganteVida,
            int lobisomenVida) {
        guerreiro.setVida(guerreiroVida);
        barbaro.setVida(barbaroVida);
        cavaleiro.setVida(cavaleiroVida);
        orc.setVida(orcVida);
        gigante.setVida(giganteVida);
        lobisomen.setVida(lobisomenVida);
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

    public static void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    public static void resposta(String mensagem) {
        System.out.print(mensagem);
    }

    public static void LimparConsole() throws InterruptedException, IOException {
        // for (int i = 0; i < 4; i++) {
        // System.out.println();
        // }
        // Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    public static void printLinha() {
        for (int i = 0; i < 110; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void printCabecalho(String titulo) {
        printLinha();
        System.out.println(titulo);
        printLinha();
    }

    public static void qualquerCoisaContinua() {
        System.out.print("\nDigite qualquer coisa aqui continuar -> ");
        sc.next();
    }

    static int jogarDados(int QuantidadeDados, int LadosDado) {
        Random gerador = new Random();
        int resultado = gerador.nextInt(QuantidadeDados * LadosDado) + 1;
        return resultado;
    }

    public static void Iniciativa() {
        printCabecalho(
                "Precisamos definir quem vai começar o jogo atacando ou como chamamos no RPG, quem terá a iniciativa.\nPara isso, jogue um dado de 20 faces (número possível de 1 a 20).\nNao temos empates e quem tirar o maior valor terá a iniciativa.");
    }

    public static void jogarDadosIniciativa() throws InterruptedException, IOException {
        escrever("Agora AVATAR " + avatar.nome + " jogue o dado de 20 faces.");
        escrever("Esta pronto?");
        responderSimOuNao();
        int pontosAvatar = jogarDados(1, 20);
        escrever("Voce obteve " + pontosAvatar + " pontos.");

        escrever("Agora é a vez do seu INIMIGO " + inimigo.nome + " joguar o dado de 20 faces.");
        escrever("Esta pronto?");
        responderSimOuNao();
        int pontosInimigo = jogarDados(1, 20);
        escrever("O seu inimigo obteve " + pontosInimigo + " pontos.");

        if (pontosAvatar > pontosInimigo) {
            escrever(avatar.nome + " voce esta com sorte, a iniciativa da batalha é sua.");
            personagemAtaque = avatar;
            personagemDefesa = inimigo;
        } else {
            escrever("Lamento " + avatar.nome + ", pois a iniciativa da batalha cabera ao seu inimigo " + inimigo.nome
                    + ".");
            personagemAtaque = inimigo;
            personagemDefesa = avatar;
        }
    }

    public static void jogarDadosAvatar() {
        printCabecalho("Agora é a vez do AVATAR " + avatar.nome + " jogar os seus " + avatar.getQtdDados() + " de "
                + avatar.getFacesDados() + " faces.");
    }

    public static void Batalha() throws InterruptedException, IOException {
        LimparConsole();
        printCabecalho("A BATALHA VAI COMECAR!");
        escrever("AVATAR:" + avatar.nome + " X " + "INIMIGO:" + inimigo.nome);
    }

    public static String espacosADireita(String nome) {
        String resultado = String.format("%-15.15s", nome);
        return resultado;
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
                escrever("(" + ++i + ")" + espacosADireita(personagem.nome)
                        + espacosADireita(" Vidas: " + personagem.getVida())
                        + espacosADireita(" Forca: " + personagem.getForca())
                        + espacosADireita(" Defesa: " + personagem.getDefesa())
                        + espacosADireita(" Agilidade: " + personagem.getAgilidade())
                        + espacosADireita(" QDados: " + personagem.getQtdDados())
                        + espacosADireita(" FDados: " + personagem.getFacesDados()));
            }
            personagemCerto = escolherResposta(personagemCerto);
        } while (!personagemCerto);

    }

    private static boolean escolherResposta(boolean personagemCerto) throws InterruptedException, IOException {
        printLinha();
        int maximo = personagens.length;
        int personagem = ler("Resposta: ->", maximo);
        LimparConsole();
        printCabecalho("Seu personagem é " + personagens[personagem - 1].nome + ".\n Esta correto?");
        System.out.println("(1) Sim!");
        System.out.println("(2) Nao, quero mudar o personagem.");
        int input = ler("Resposta: ->", 2);
        if (input == 1) {
            personagemCerto = true;
            avatar = personagens[personagem - 1];
        }
        return personagemCerto;
    }

    private static void responderSimOuNao() {

        int input = 2;
        do {
            System.out.println("(1) Sim.");
            System.out.println("(2) Nao.");
            input = (int) ler("Resposta: ->", 2);
        } while (input != 1);
    }

    public static int Atacar() throws InterruptedException, IOException {

        if (avatar == personagemAtaque) {
            printLinha();
            escrever(personagemAtaque.nome + " esta pronto para atacar com sua(s) " + personagemAtaque.getVida()
                    + " vidas?");
            responderSimOuNao();
        }

        pontosAtaque = jogarDados(1, 12) + personagemAtaque.getForca() + personagemAtaque.getAgilidade();
        printLinha();
        escrever("ATAQUE--> " + personagemAtaque.nome + " o seu ataque foi de " + pontosAtaque + " pontos.");

        return pontosAtaque;
    }

    public static int Defender() throws InterruptedException, IOException {

        if (avatar == personagemDefesa) {
            escrever(personagemDefesa.nome + " esta pronto para defender sua(s) " +
                    personagemDefesa.getVida() + " vidas?");
            responderSimOuNao();
        }

        pontosDefesa = jogarDados(1, 12) + personagemDefesa.getDefesa() + personagemDefesa.getAgilidade();
        printLinha();
        escrever("DEFESA--> " + personagemDefesa.nome + " a sua defesa foi de " + pontosDefesa + " pontos.");

        // LimparConsole();
        return pontosDefesa;
    }

    public static boolean CalcularDano() {
        boolean morreu = false;

        if (pontosAtaque > pontosDefesa) {
            int pontosDano = jogarDados(personagemDefesa.getQtdDados(), personagemDefesa.getFacesDados());
            printLinha();

            personagemDefesa.setVida(personagemDefesa.getVida() - pontosDano);
            escrever(personagemDefesa.nome + " Danos = " + pontosDano + " e ficou com " + personagemDefesa.getVida()
                    + " vidas");

            if (personagemDefesa.getVida() <= 0) {
                printLinha();
                escrever(personagemDefesa.nome + " com esse ataque acabou de morrer.");
                printLinha();
                escrever("O VENCEDOR DA BATALHA É " + personagemAtaque.nome + ", COM " + personagemAtaque.getVida()
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

    public static void escolherInimigo() throws InterruptedException, IOException {
        printLinha();
        boolean personagemCerto = false;
        do {
            LimparConsole();
            int i = 0;
            escrever("Escolha o seu Inimigo (Monstro)");
            printLinha();
            for (Personagem personagem : monstros) {
                escrever("(" + ++i + ")" + espacosADireita(personagem.nome)
                        + espacosADireita(" Vidas: " + personagem.getVida())
                        + espacosADireita(" Forca: " + personagem.getForca())
                        + espacosADireita(" Defesa: " + personagem.getDefesa())
                        + espacosADireita(" Agilidade: " + personagem.getAgilidade())
                        + espacosADireita(" QDados: " + personagem.getQtdDados())
                        + espacosADireita(" FDados: " + personagem.getFacesDados()));
            }
            printLinha();
            int maximo = monstros.length;
            int personagem = ler("Resposta: ->", maximo);
            LimparConsole();
            printCabecalho("Seu inimigo é " + monstros[personagem - 1].nome + ".\n Esta correto?");
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
            escolherPersonagem();
            escolherInimigo();
            Batalha();
            Iniciativa();
            jogarDadosIniciativa();
            guerrear();
            recarregarVidasPersonagens(20, 21, 26, 42, 34, 34);
            escrever("Deseja Continuar Jogando?");
            continuaJogando = continuarSimOuNao();
        } while (continuaJogando);
    }
}
