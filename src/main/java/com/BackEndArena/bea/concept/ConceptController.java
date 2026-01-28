package com.BackEndArena.bea.concept;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/concepts")
public class ConceptController {
    private ConceptService conceptService;

    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    public ResponseEntity<Concept> createConcept(@RequestParam String title
            ,@RequestParam String description
            , @RequestParam  ConceptDifficulty conceptDifficulty
            ,@RequestParam Set<Integer> javaVersions
            ,@RequestParam Long categoryId
            ,@RequestParam boolean transformationConcept) {

        return ResponseEntity.ok(conceptService.createConcept(title
                , description, conceptDifficulty, javaVersions,
                categoryId, transformationConcept));
    }

    @GetMapping
    public ResponseEntity<List<Concept>> getAllConcepts() {
        return ResponseEntity.ok(conceptService.getAllConcepts());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Concept>> getByCategory(
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(
                conceptService.getAllConceptsByCategory(categoryId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concept> getById(@PathVariable Long id) {
        return ResponseEntity.ok(conceptService.getConceptById(id));
    }


}
