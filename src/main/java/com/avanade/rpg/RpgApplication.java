package com.avanade.rpg;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.avanade.rpg.model.*;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

@SpringBootApplication
public class RpgApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		// SpringApplication.run(RpgApplication.class, args);
		Jogo.jogar();
	}
}
