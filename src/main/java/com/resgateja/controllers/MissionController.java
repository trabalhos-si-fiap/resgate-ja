package com.resgateja.controllers;

import com.resgateja.dtos.CreateMission;
import com.resgateja.dtos.UpdateMission;
import com.resgateja.models.Mission;
import com.resgateja.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @GetMapping
    public List<Mission> getAllMissions() {
        return missionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMissionById(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody CreateMission mission) {
        // Todo: Coletar id do usuário e validar se é um coordenador
        return ResponseEntity.ok(missionService.createMission(mission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mission> updateMission(@PathVariable Long id, @RequestBody UpdateMission missionDetails) {
        return ResponseEntity.ok(missionService.update(id, missionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
}
