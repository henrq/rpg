package com.avanade.rpg.repository;

import com.avanade.rpg.model.dto.LogBatalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLogBatalhas extends JpaRepository<LogBatalha, Integer> {
}
