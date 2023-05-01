package com.avanade.rpg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanade.rpg.model.dto.Personagem;
import com.avanade.rpg.service.PersonagensService;
import com.avanade.rpg.service.JogoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/rpg")
@Api(value = "RPG API REST")
@CrossOrigin(origins = "*")
public class PersonagemController {

    @Autowired
    private PersonagensService servicePersonagens;

    @Autowired
    private JogoService jogoService;

    @GetMapping("/jogar")
    @ApiOperation("Iniciar o Jogo RPG")
    public void Jogar() throws InterruptedException, IOException {
        List<Personagem> personagens = servicePersonagens.findAll();
        Predicate<Personagem> filtraMonstro = p -> p.getTipo().equalsIgnoreCase("Monstro");
        List<Personagem> monstros = personagens.stream().filter(filtraMonstro).toList();
        jogoService.jogar(personagens, monstros);
    }

    @GetMapping("/personagens")
    @ApiOperation("Listar Todos Personagens")
    public ResponseEntity<List<Personagem>> getAll() {
        return new ResponseEntity<>(servicePersonagens.findAll(), HttpStatus.OK);
    }

    @GetMapping("/personagens/{id}")
    @ApiOperation("Localizar Personagem pelo ID")
    public ResponseEntity<Personagem> getById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(servicePersonagens.findById(id), HttpStatus.OK);
    }

    @PostMapping("/personagens")
    @ApiOperation("Criar Personagem")
    public ResponseEntity<Personagem> create(@RequestBody Personagem personagem) {
        return new ResponseEntity<>(servicePersonagens.create(personagem), HttpStatus.CREATED);
    }

    @PostMapping("/personagens/carga")
    @ApiOperation("Carga de todos Personagem")
    public ResponseEntity<List<Personagem>> createAll() {
        List<Personagem> personagens = montarPersonagens();
        return new ResponseEntity<List<Personagem>>(servicePersonagens.createAll(personagens), HttpStatus.CREATED);
    }

    private List<Personagem> montarPersonagens() {
        Personagem guerreiro = new Personagem("Guerreiro", "Heroi", 6, 7, 5, 20, 1, 12);
        Personagem barbaro = new Personagem("Barbaro", "Heroi", 5, 10, 2, 21, 2, 8);
        Personagem cavaleiro = new Personagem("Cavaleiro", "Heroi", 3, 6, 8, 26, 2, 6);
        Personagem orc = new Personagem("Orc", "Monstro", 2, 7, 1, 42, 3, 4);
        Personagem gigante = new Personagem("Gigante", "Monstro", 4, 10, 4, 34, 2, 6);
        Personagem lobisomen = new Personagem("Lobisomen", "Monstro", 7, 7, 4, 34, 2, 4);

        List<Personagem> personagens = new ArrayList<>();
        personagens.add(guerreiro);
        personagens.add(barbaro);
        personagens.add(cavaleiro);
        personagens.add(orc);
        personagens.add(gigante);
        personagens.add(lobisomen);
        return personagens;
    }

    @PutMapping("/personagens")
    @ApiOperation("Atualizar Personagem")
    public ResponseEntity<Personagem> update(@RequestBody Personagem personagem) {
        return new ResponseEntity<>(servicePersonagens.update(personagem), HttpStatus.OK);
    }

    @DeleteMapping("/personagens/{id}")
    @ApiOperation("Deletar Personagem")
    public ResponseEntity<HttpStatus> update(@RequestHeader Integer id) {
        servicePersonagens.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/personagem/ataque/{id}")
    @ApiOperation("Poder de Ataque do Personagem")
    public ResponseEntity<String> ataque(@PathVariable(value = "id") Integer id) {
        Personagem byId = servicePersonagens.findById(id);
        final StringBuffer informacao = new StringBuffer();
        informacao.append("O Personagem: ");
        informacao.append(byId.getNome().trim());
        informacao.append(" Possui Força de: ");
        informacao.append(byId.getForca());
        informacao.append(" e Agilidade de: ");
        informacao.append(byId.getAgilidade());
        informacao.append(". Seu ataque pode ser de até: ");
        informacao.append(12 + byId.getForca() + byId.getAgilidade());

        return new ResponseEntity<>(informacao.toString(), HttpStatus.OK);
    }

    @GetMapping("/personagem/defesa/{id}")
    @ApiOperation("Poder de Defesa do Personagem")
    public ResponseEntity<String> defesa(@PathVariable(value = "id") Integer id) {
        Personagem byId = servicePersonagens.findById(id);

        final StringBuffer informacao = new StringBuffer();
        informacao.append("O Personagem: ");
        informacao.append(byId.getNome().trim());
        informacao.append(" Possui Defesa de: ");
        informacao.append(byId.getDefesa());
        informacao.append(" e Agilidade de: ");
        informacao.append(byId.getAgilidade());
        informacao.append(". Sua defesa pode ser de até:");
        informacao.append(12 + byId.getDefesa() + byId.getAgilidade());

        return new ResponseEntity<>(informacao.toString(), HttpStatus.OK);
    }

    @GetMapping("/personagem/danos/{id}")
    @ApiOperation("Poder de Danos do Personagem")
    public ResponseEntity<String> danos(@PathVariable(value = "id") Integer id) {
        Personagem byId = servicePersonagens.findById(id);

        final StringBuffer informacao = new StringBuffer();
        informacao.append("O Personagem: ");
        informacao.append(byId.getNome().trim());
        informacao.append(" Possui Força de: ");
        informacao.append(byId.getForca());
        informacao.append(" e Agilidade de: ");
        informacao.append(byId.getAgilidade());
        informacao.append(". Podendo gerar um Dano de até: ");
        informacao.append((byId.getQuantidadeDados() * byId.getFacesDados()) + byId.getForca());

        return new ResponseEntity<>(informacao.toString(), HttpStatus.OK);
    }
}
