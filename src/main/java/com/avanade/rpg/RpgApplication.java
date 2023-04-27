package com.avanade.rpg;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.avanade.rpg.model.*;

import java.util.Scanner;
import java.util.Random;

@SpringBootApplication
public class RpgApplication {

	static int ataqueUsuario() {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Escolha seu ataque");
		System.out.println("(1) - Soco");
		System.out.println("(2) - Especial");
		int informacao = leitor.nextInt();
		leitor.close();
		return informacao;
	}

	static int jogarOsDados(int QuantidadeDados, int LadosDado) {
		Random gerador = new Random();
		int resultado = gerador.nextInt(QuantidadeDados * LadosDado) + 1;
		return resultado;
	}

	static int ataqueComputador() {
		Random gerador = new Random(0);
		return gerador.nextInt(3) + 1;
	}

	static void imprimeHP(int hpUsuario, int hpComputador, int contagemEspeciais) {
		System.out.println("====================");
		System.out.println("- HP Usuario: " + hpUsuario);
		System.out.println("- HP Computador: " + hpComputador);
		System.out.println("* Contagem Especiais: " + contagemEspeciais);
		System.out.println("====================");
	}

	static int batalha() {
		int hpUsuario = 150;
		int hpComputador;
		int contagemEspecial = 5;
		int escolhaAtaque;
		int i = 1;

		while (hpUsuario > 0) {
			hpComputador = 10 + i;

			System.out.println("====================");
			System.out.println("INICIO " + i);
			System.out.println("====================\n");

			while (hpUsuario > 0 && hpComputador > 0) {
				imprimeHP(hpUsuario, hpComputador, contagemEspecial);
				escolhaAtaque = ataqueUsuario();
				switch (escolhaAtaque) {
					case 1:
						System.out.println("Usuario aplicou um soco.");
						hpComputador -= 7;
						break;
					case 2:
						System.out.println("Usuario aplicou um ataque especial.");
						hpComputador -= 20;
						contagemEspecial--; // contagemespecial = contagemEspecial -1
						break;
					default:
						System.out.println("Opcao invalida");
						break;
				}
				if (hpComputador > 0) {
					escolhaAtaque = ataqueComputador();
					switch (escolhaAtaque) {
						case 1:
							System.out.println("Computador aplicou um soco.");
							hpUsuario -= 2 + (int) (i / 10);
							break;
						case 2:
							System.out.println("Computador aplicou um chute.");
							hpUsuario -= 3 + (int) (i / 10);
							contagemEspecial--; // contagemespecial = contagemEspecial -1
							break;
						case 3:
							System.out.println("Computador aplicou um ataque especial.");
							hpUsuario -= 4 + (int) (i / 20);
							break;
					}
				} else {
					System.out.println("Inimigo derrotado");
				}
			}
			if (hpUsuario > 0) {
				hpUsuario += 5;
				if (hpUsuario > 150) {
					hpUsuario = 150;
				}
				if (i % 10 == 0) {
					contagemEspecial++;
					if (contagemEspecial > 5) {
						contagemEspecial = 5;
					}
				}
			}
			i++;
		}
		return i;
	}

	public static void main(String[] args) {
		// Scanner leitor = new Scanner(System.in);
		// int continua = 1;
		// int recorde = 0;
		// while (continua == 1) {

		// int pontos = batalha();
		// System.out.println("Usuario chegou a " + pontos + " pontos.");
		// if (pontos > recorde) {
		// recorde = pontos;
		// }
		// System.out.println("RECORDE ATUAL = " + recorde);
		// System.out.println("Fim de jogo. Deseja continuar? (1) Sim (2) Nao");
		// continua = leitor.nextInt();
		// }
		// SpringApplication.run(RpgApplication.class, args);

		Jogo.escolherPersonagem();
		Jogo.escolherInimigo();
		Jogo.Batalha();
		Jogo.Iniciativa();
		Jogo.jogarDadosIniciativa();
		Jogo.guerrear();
	}
}
