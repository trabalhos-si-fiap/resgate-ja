package com.resgateja.services;

import com.resgateja.dtos.CreateMission;
import com.resgateja.dtos.UpdateMission;
import com.resgateja.models.Mission;
import com.resgateja.models.User;
import com.resgateja.repositories.MissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Mission getById(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));
        return mission;
    }

    public Mission createMission(CreateMission mission) {
        var newMission = new Mission(mission);
        return missionRepository.save(newMission);
    }

    public void deleteMission(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new EntityNotFoundException("Mission not found");
        }

        var mission = missionRepository.getReferenceById(id);
        mission.delete();
        missionRepository.save(mission);
    }


    public Mission update(Long id, UpdateMission missionDetails) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        mission.update(missionDetails);
        return missionRepository.save(mission);
    }
}
