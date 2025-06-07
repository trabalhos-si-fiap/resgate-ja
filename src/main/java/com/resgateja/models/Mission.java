package com.resgateja.models;

import com.resgateja.dtos.CreateMission;
import com.resgateja.dtos.UpdateMission;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "missions")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Embedded
    private Location location;
    private LocalDateTime startAt;

    @ManyToMany
    @JoinTable(
            name = "mission_volunteers",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> volunteers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator_id")
    private User coordinator;

    private Boolean isCompleted;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private boolean isActive;

    public Mission(CreateMission mission) {
        title = mission.title();
        description = mission.description();
        location = mission.location();
        startAt = mission.startAt();
        isActive = true;
    }

    public void update(UpdateMission missionDetails) {

        if (missionDetails.title() != null) {
            title = missionDetails.title();
        }

        if (missionDetails.description() != null) {
            description = missionDetails.description();
        }

        if (missionDetails.location() != null) {
            location = missionDetails.location();
        }

        if (missionDetails.isActive() != null) {
            isActive = missionDetails.isActive();
        }
    }

    public void delete() {
        isActive = false;
    }
}
