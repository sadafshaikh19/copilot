package com.copilot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.copilot.model.Squad;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.copilot.repository.SquadRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SquadController {

  @Autowired
  SquadRepository squadRepository;

  @GetMapping("/squads")
  public ResponseEntity<List<Squad>> getAllSquads(@RequestParam(required = false) String name) {
    try {
      List<Squad> squads = new ArrayList<Squad>();

      if (name == null)
        squadRepository.findAll().forEach(squads::add);
      else
        squadRepository.findByNameContainingIgnoreCase(name).forEach(squads::add);

      if (squads.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(squads, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/squads/{id}")
  public ResponseEntity<Squad> getSquadById(@PathVariable("id") long id) {
    Optional<Squad> squadData = squadRepository.findById(id);

    if (squadData.isPresent()) {
      return new ResponseEntity<>(squadData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/squads")
  public ResponseEntity<Squad> createSquad(@RequestBody Squad squad) {
    try {
      Squad _squad = squadRepository.save(new Squad(squad.getName(), squad.getDescription(), squad.getProductOwner()));
      return new ResponseEntity<>(_squad, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/squads/{id}")
  public ResponseEntity<Squad> updateSquad(@PathVariable("id") long id, @RequestBody Squad squad) {
    Optional<Squad> squadData = squadRepository.findById(id);

    if (squadData.isPresent()) {
      Squad _squad = squadData.get();
      _squad.setName(squad.getName());
      _squad.setDescription(squad.getDescription());
      _squad.setProductOwner(squad.getProductOwner());
      return new ResponseEntity<>(squadRepository.save(_squad), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/squads/{id}")
  public ResponseEntity<HttpStatus> deleteSquad(@PathVariable("id") long id) {
    try {
      squadRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/squads")
  public ResponseEntity<HttpStatus> deleteAllSquads() {
    try {
      squadRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
