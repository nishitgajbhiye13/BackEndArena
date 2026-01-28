package com.BackEndArena.bea.concept;

import com.BackEndArena.bea.concept.dto.ConceptProgressResponse;
import com.BackEndArena.bea.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/concepts")
public class UserConceptController {

    private final ConceptService conceptService;

    public UserConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @GetMapping
    public ResponseEntity<List<Concept>> getVisibleConceptsForUser() {
        return ResponseEntity.ok(
                conceptService.getVisibleConceptsForUser()
        );
    }

    @GetMapping("/{conceptId}/progress")
    public ResponseEntity<ConceptProgressResponse> getConceptProgress(
            @PathVariable Long conceptId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        ConceptProgressResponse response =
                conceptService.getConceptProgress(
                        conceptId,
                        userDetails.getId()
                );

        return ResponseEntity.ok(response);
    }

}
