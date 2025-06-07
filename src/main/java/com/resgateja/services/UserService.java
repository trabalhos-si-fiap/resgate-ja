package com.resgateja.services;

import com.resgateja.dtos.UpdateUser;
import com.resgateja.dtos.CreateUser;
import com.resgateja.dtos.UserResponse;
import com.resgateja.interfaces.HasOrganizations;
import com.resgateja.dtos.CreateOrganization;
import com.resgateja.models.Organization;
import com.resgateja.repositories.OrganizationRepository;
import com.resgateja.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import com.resgateja.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationRepository organizationRepository;


    private Set<Organization> findOrCreateOrganizations(HasOrganizations data) {
        Set<Organization> organizations = new HashSet<>();

        if (data.organizations() == null) {
            return organizations;
        }

        for (CreateOrganization orgData : data.organizations()) {
            Organization org = organizationRepository.findByDocument(orgData.document())
                    .orElseGet(() -> {
                        Organization newOrg = new Organization();
                        newOrg.setName(orgData.name());
                        newOrg.setDocument(orgData.document());
                        return organizationRepository.save(newOrg);
                    });
            organizations.add(org);
        }

        return organizations;
    }
    public UserResponse create(CreateUser data) {

        User user = new User(data);
        // Todo: usar Bcrypt para a senha antes de salvar
        // user.setPassword(passwordEncoder.encode(data.password()));

        var organizations = findOrCreateOrganizations(data);
        user.setOrganizations(organizations);

        return new UserResponse(userRepository.save(user));
    }

    public List<UserResponse> listAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .toList();
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserResponse(user);
    }

    public UserResponse update(Long id, UpdateUser data) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        var organizations = findOrCreateOrganizations(data);
        user.update(data, organizations);

        return new UserResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        var user = userRepository.getReferenceById(id);
        user.delete();
        userRepository.save(user);
    }
}

