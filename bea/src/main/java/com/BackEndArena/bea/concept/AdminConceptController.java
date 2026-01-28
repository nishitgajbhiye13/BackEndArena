package com.BackEndArena.bea.concept;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/concepts")
@PreAuthorize("hasRole('ADMIN')")
public class AdminConceptController {

    private final ConceptService conceptService;

    public AdminConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }


    @GetMapping
    public ResponseEntity<List<Concept>> getAllConceptsForAdmin() {
        return ResponseEntity.ok(conceptService.getAllConceptsForAdmin());
    }
}
