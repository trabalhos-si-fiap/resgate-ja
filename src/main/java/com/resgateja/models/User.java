package com.resgateja.models;

import com.resgateja.dtos.CreateUser;
import com.resgateja.dtos.UpdateUser;
import com.resgateja.infra.security.RoleGrantedAuthority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 35, message = "O atributo 'name' deve conter no minimo 3 e no máximo 35 caracteres")
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 8, message = "O atributo Senha deve conter no minimo 8 caracteres")
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(mappedBy = "volunteers", fetch = FetchType.LAZY)
    private List<Mission> acceptedMissions;

    @OneToMany(mappedBy = "coordinator", fetch = FetchType.LAZY)
    private List<Mission> postedMissions;
    @OneToMany(mappedBy = "volunteer", fetch = FetchType.LAZY)
    private List<VolunteerScore> receivedScores;

    @OneToMany(mappedBy = "coordinator", fetch = FetchType.LAZY)
    private List<VolunteerScore> givenScores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "organization_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
    private Set<Organization> organizations;

    private boolean isActive;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User(CreateUser data) {
        name = data.name();
        email = data.email();
        password = data.password();
        role = UserRole.VOLUNTEER;
        isActive = true;
    }


    public void update(UpdateUser data, Set<Organization> organizations) {
        if (data.name() != null) {
            name = data.name();
        }

        if (data.password() != null) {
            password = data.password();
        }

        if (organizations != null) {
            this.organizations = organizations;
        }
    }

    public void delete() {
        isActive = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new RoleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
