package com.avanade.rpg.repository;

import com.avanade.rpg.model.dto.LogBatalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryLogBatalhas extends JpaRepository<LogBatalha, Integer> {
    List<LogBatalha> findAllByBatalha(Integer id);
}
