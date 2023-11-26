package com.copilot.repository;

import java.util.List;

import com.copilot.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepository extends JpaRepository<Squad, Long> {
  List<Squad> findByNameContainingIgnoreCase(String name);
}
