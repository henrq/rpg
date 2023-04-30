package com.avanade.rpg.repository;

import com.avanade.rpg.model.dto.Personagem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPersonagens extends JpaRepository<Personagem, Integer> {
    @Query(value = "SELECT * FROM Personagem WHERE Tipo='M'?", nativeQuery = true)
    public List<Personagem> findAllMonster();
}
