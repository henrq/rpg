package com.avanade.rpg.repository;

import com.avanade.rpg.model.dto.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPersonagens extends JpaRepository<Personagem, Integer> {
}
