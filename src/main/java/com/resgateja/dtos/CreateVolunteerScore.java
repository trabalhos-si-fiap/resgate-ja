package com.resgateja.dtos;

public record CreateVolunteerScore(
        Long missionId,
        Long volunteerId,
        Long coordinatorId,
        String comment,
        Integer stars
) {
}
