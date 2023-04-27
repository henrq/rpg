package com.avanade.rpg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avanade.rpg.model.dto.LogBatalha;
import com.avanade.rpg.model.dto.Personagem;
import com.avanade.rpg.service.ServiceLogBatalhas;
import com.avanade.rpg.service.ServicePersonagens;

import java.util.List;

@RestController
@RequestMapping(value = "/rpg")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class Controller {

	@PutMapping("/logBatalha")
	@ApiOperation("Jogar!")
	public ResponseEntity<LogBatalha> jogar(@RequestBody LogBatalha batalhas) {
		serviceLogBatalhas.updateLogBatalha(batalhas);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/logBatalha")
	@ApiOperation("Obter listagem dos Logs das Batalhas")
	public ResponseEntity<List<LogBatalha>> getAllLogBatalhas() {
		return new ResponseEntity<>(serviceLogBatalhas.findAll(), HttpStatus.OK);
	}

	@GetMapping("/logbatalha/{batalha}")
	@ApiOperation("Obter historico do Log da Batalha pelo id do Log Batalha")
	public ResponseEntity<List<LogBatalha>> getByIdBatalha(@PathVariable Integer batalha) {
		return ResponseEntity.ok(serviceLogBatalhas.findByIdBatalha(batalha));
	}

	@Autowired
	private ServicePersonagens servicePersonagens;
	@Autowired
	private ServiceLogBatalhas serviceLogBatalhas;

	@PostMapping("/personagens")
	@ApiOperation("Criar Personagem")
	public ResponseEntity<Personagem> create(@RequestBody Personagem personagem) {
		return new ResponseEntity<>(servicePersonagens.create(personagem), HttpStatus.CREATED);
	}

	@PutMapping("/personagens")
	@ApiOperation("Atualizar Personagem")
	public ResponseEntity<Personagem> update(@RequestBody Personagem personagem) {
		return new ResponseEntity<>(servicePersonagens.update(personagem), HttpStatus.OK);
	}

	@GetMapping("/personagens")
	@ApiOperation("Obter listagem dos Personagens")
	public ResponseEntity<List<Personagem>> getAll() {
		return new ResponseEntity<>(servicePersonagens.findAll(), HttpStatus.OK);
	}

	@GetMapping("/personagens/{id}")
	@ApiOperation("Localizar Personagem pelo ID")
	public ResponseEntity<Personagem> getById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(servicePersonagens.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/personagens/{id}")
	@ApiOperation("Deletar Personagem pelo ID")
	public ResponseEntity<HttpStatus> update(@RequestHeader Integer id) {
		servicePersonagens.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/personagem/ataque/{id}")
	@ApiOperation("Listagem dos Ataques do Personagem por ID")
	public ResponseEntity<String> ataque(@PathVariable(value = "id") Integer id) {
		Personagem byId = servicePersonagens.findById(id);
		final StringBuffer informacao = new StringBuffer();
		informacao.append("O Personagem: ");
		informacao.append(byId.getNome().trim());
		informacao.append(" Possui uma Força de: ");
		informacao.append(byId.getForca());
		informacao.append(" e Agilidade de: ");
		informacao.append(byId.getAgilidade());
		informacao.append(". Podendo Atacar com um total de até:");
		informacao.append(12 + byId.getForca() + byId.getAgilidade());

		return new ResponseEntity<>(informacao.toString(), HttpStatus.OK);
	}

	@GetMapping("/personagem/defesa/{id}")
	@ApiOperation("Listagem de Defesas do Personagem por ID")
	public ResponseEntity<String> defesa(@PathVariable(value = "id") Integer id) {
		Personagem byId = servicePersonagens.findById(id);

		final StringBuffer informacao = new StringBuffer();
		informacao.append("O Personagem: ");
		informacao.append(byId.getNome().trim());
		informacao.append(" Possui Defesa de: ");
		informacao.append(byId.getDefesa());
		informacao.append(" e Agilidade de: ");
		informacao.append(byId.getAgilidade());
		informacao.append(". E Ataque é de:");
		informacao.append(12 + byId.getDefesa() + byId.getAgilidade());

		return new ResponseEntity<>(informacao.toString(), HttpStatus.OK);
	}

	@GetMapping("/personagem/danos/{id}")
	@ApiOperation("Listar Atributos de Danos do Personagem por ID")
	public ResponseEntity<String> danos(@PathVariable(value = "id") Integer id) {
		Personagem byId = servicePersonagens.findById(id);

		final StringBuffer informacao = new StringBuffer();
		informacao.append("O Personagem: ");
		informacao.append(byId.getNome().trim());
		informacao.append(" Possui Força de: ");
		informacao.append(byId.getForca());
		informacao.append(" e Agilidade de: ");
		informacao.append(byId.getAgilidade());
		informacao.append(". Podendo gerar um Dano de até:");
		informacao.append((byId.getQuantidadeDados() * byId.getFacesDados()) + byId.getForca());

		return new ResponseEntity<>(informacao.toString(), HttpStatus.OK);
	}

}
