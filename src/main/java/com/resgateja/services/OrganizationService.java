package com.resgateja.services;

import com.resgateja.dtos.CreateOrganization;
import com.resgateja.dtos.FullOrganizationResponse;
import com.resgateja.dtos.UpdateOrganization;
import com.resgateja.models.Organization;
import com.resgateja.repositories.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public FullOrganizationResponse create(CreateOrganization data) {
        if (organizationRepository.existsByDocument(data.document())) {
            throw new IllegalArgumentException("Documento já cadastrado.");
        }
        var organization = new Organization(data);
        return new FullOrganizationResponse(organizationRepository.save(organization));
    }

    public List<FullOrganizationResponse> listAll() {
        return organizationRepository.findAll().stream()
                .map(FullOrganizationResponse::new)
                .toList();
    }

    public FullOrganizationResponse getById(Long id) {
        var organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organização não encontrada"));
        return new FullOrganizationResponse(organization);
    }

    public FullOrganizationResponse update(Long id, UpdateOrganization data) {
        var organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organização não encontrada"));

        organization.setName(data.name());
        organization.setDocument(data.document());

        return new FullOrganizationResponse(organizationRepository.save(organization));
    }

    public void delete(Long id) {
        var organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organização não encontrada"));

        organization.setActive(false);
        organizationRepository.save(organization);
    }
}
