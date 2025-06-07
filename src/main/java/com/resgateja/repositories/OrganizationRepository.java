package com.resgateja.repositories;

import com.resgateja.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByDocument(String document);
    boolean existsByDocument(String document);
}
