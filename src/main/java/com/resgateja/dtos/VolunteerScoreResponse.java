package com.resgateja.dtos;

import com.resgateja.models.VolunteerScore;

import java.time.LocalDateTime;

public record VolunteerScoreResponse(
        Long id,
        Long missionId,
        Long volunteerId,
        Long coordinatorId,
        String comment,
        Integer stars,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public VolunteerScoreResponse(VolunteerScore vs) {
        this(
                vs.getId(),
                vs.getMission().getId(),
                vs.getVolunteer().getId(),
                vs.getCoordinator().getId(),
                vs.getComment(),
                vs.getStars(),
                vs.getIsActive(),
                vs.getCreatedAt(),
                vs.getUpdatedAt()
        );
    }
}
