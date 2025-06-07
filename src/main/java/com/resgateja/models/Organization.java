package com.resgateja.models;


import com.resgateja.dtos.CreateOrganization;
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
@Table(name = "organization")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String document;

    private boolean isActive;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
    private List<User> users;

    public Organization(CreateOrganization createOrganization) {
        name = createOrganization.name();
        document = createOrganization.document();
        isActive = true;
    }
}
