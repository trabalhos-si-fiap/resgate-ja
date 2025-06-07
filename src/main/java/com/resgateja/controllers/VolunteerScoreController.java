package com.resgateja.controllers;

import com.resgateja.dtos.CreateVolunteerScore;
import com.resgateja.dtos.VolunteerScoreResponse;
import com.resgateja.services.VolunteerScoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteer-scores")
public class VolunteerScoreController {

    @Autowired
    private VolunteerScoreService service;

    @PostMapping
    public ResponseEntity<VolunteerScoreResponse> create(@RequestBody @Valid CreateVolunteerScore data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }

    @GetMapping
    public List<VolunteerScoreResponse> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerScoreResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}