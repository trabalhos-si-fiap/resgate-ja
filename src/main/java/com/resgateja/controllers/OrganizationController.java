package com.resgateja.controllers;

import com.resgateja.dtos.CreateOrganization;
import com.resgateja.dtos.FullOrganizationResponse;
import com.resgateja.dtos.UpdateOrganization;
import com.resgateja.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<FullOrganizationResponse> create(@RequestBody CreateOrganization data) {
        return ResponseEntity.ok(organizationService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<FullOrganizationResponse>> listAll() {
        return ResponseEntity.ok(organizationService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullOrganizationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullOrganizationResponse> update(@PathVariable Long id, @RequestBody UpdateOrganization data) {
        return ResponseEntity.ok(organizationService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}