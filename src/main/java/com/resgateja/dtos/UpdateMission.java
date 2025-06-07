package com.resgateja.dtos;

import com.resgateja.models.Location;

import java.time.LocalDateTime;

public record UpdateMission(
        String title,
        String description,
        Location location,
        LocalDateTime startAt,
        Boolean isActive
) {
}
