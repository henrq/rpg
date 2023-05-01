package com.avanade.rpg.service;

import com.avanade.rpg.exception.InvalidInputException;
import com.avanade.rpg.exception.ResourceNotFoundException;
import com.avanade.rpg.model.dto.Personagem;
import com.avanade.rpg.repository.PersonagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagensService {

    @Autowired
    private PersonagensRepository repositorio;

    public Personagem create(Personagem personagem) {
        personagem.setNome(personagem.getNome());
        personagem.setTipo(personagem.getTipo());
        personagem.setAgilidade(personagem.getAgilidade());
        personagem.setForca(personagem.getForca());
        personagem.setDefesa(personagem.getDefesa());
        personagem.setVida(personagem.getVida());
        personagem.setQuantidadeDados(personagem.getQuantidadeDados());
        personagem.setFacesDados(personagem.getFacesDados());
        return this.repositorio.save(personagem);
    }

    public List<Personagem> createAll(List<Personagem> personagens) {
        return this.repositorio.saveAll(personagens);
    }

    public List<Personagem> findAll() {
        return repositorio.findAll();
    }

    public Personagem findById(Integer id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personagem não encontrado como o ID: " + id));
    }

    public void delete(Integer id) {
        repositorio.deleteById(id);
    }

    public Personagem update(Personagem personagem) {
        if (personagem.getId() == null) {
            throw new InvalidInputException("ID não encontrado!");
        }
        return repositorio.save(personagem);
    }

    public HttpStatus createAll(List<Personagem> personagens, HttpStatus created) {
        return null;
    }

    public int findUltimoIdPersonagem() {
        return 0;
    }

}
