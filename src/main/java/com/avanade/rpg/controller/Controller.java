package com.avanade.rpg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avanade.rpg.model.dto.LogBatalha;
import com.avanade.rpg.service.ServiceLogBatalhas;
import com.avanade.rpg.model.Jogo;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/rpg")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class Controller {

	@PutMapping("/logBatalha")
	@ApiOperation("Jogar!")
	public void jogar() throws InterruptedException, IOException {
		Jogo.jogar();
	}

	@GetMapping("/logBatalha")
	@ApiOperation("Obter listagem dos Logs das Batalhas")
	public ResponseEntity<List<LogBatalha>> getAllLogBatalhas() {
		return new ResponseEntity<>(serviceLogBatalhas.findAll(), HttpStatus.OK);
	}

	@Autowired
	private ServiceLogBatalhas serviceLogBatalhas;
}
