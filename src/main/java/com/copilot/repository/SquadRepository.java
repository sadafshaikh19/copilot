package com.copilot.repository;

import com.copilot.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SquadRepository extends JpaRepository<Squad, Long> {
    List<Squad> findByNameContainingIgnoreCase(String name);
}
