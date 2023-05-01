package com.avanade.rpg.repository;

import com.avanade.rpg.model.dto.Personagem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagensRepository extends JpaRepository<Personagem, Integer> {
}
