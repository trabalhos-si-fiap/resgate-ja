package com.resgateja.services;

import com.resgateja.dtos.CreateVolunteerScore;
import com.resgateja.dtos.VolunteerScoreResponse;
import com.resgateja.models.Mission;
import com.resgateja.models.User;
import com.resgateja.models.VolunteerScore;
import com.resgateja.repositories.MissionRepository;
import com.resgateja.repositories.UserRepository;
import com.resgateja.repositories.VolunteerScoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerScoreService {

    @Autowired
    private VolunteerScoreRepository repository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private UserRepository userRepository;

    public VolunteerScoreResponse create(CreateVolunteerScore data) {
        Mission mission = missionRepository.findById(data.missionId())
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));
        User volunteer = userRepository.findById(data.volunteerId())
                .orElseThrow(() -> new EntityNotFoundException("Volunteer not found"));
        User coordinator = userRepository.findById(data.coordinatorId())
                .orElseThrow(() -> new EntityNotFoundException("Coordinator not found"));

        VolunteerScore vs = new VolunteerScore();
        vs.setMission(mission);
        vs.setVolunteer(volunteer);
        vs.setCoordinator(coordinator);
        vs.setComment(data.comment());
        vs.setStars(data.stars());
        vs.setIsActive(true);

        return new VolunteerScoreResponse(repository.save(vs));
    }

    public List<VolunteerScoreResponse> listAll() {
        return repository.findAll().stream()
                .map(VolunteerScoreResponse::new)
                .toList();
    }

    public VolunteerScoreResponse getById(Long id) {
        VolunteerScore vs = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Score not found"));
        return new VolunteerScoreResponse(vs);
    }

    public void delete(Long id) {
        VolunteerScore vs = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Score not found"));
        vs.setIsActive(false);
        repository.save(vs);
    }
}

