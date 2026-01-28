package com.BackEndArena.bea.concept;


import com.BackEndArena.bea.concept.dto.ConceptProgressResponse;
import com.BackEndArena.bea.problem.Problem;

import java.util.List;
import java.util.Set;


public interface ConceptService {

    Concept createConcept(String title
            , String description
            , ConceptDifficulty conceptDifficulty
            , Set<Integer> javaVersions
            ,Long categoryId
            ,boolean transformationConcept);

    List<Concept> getAllConcepts();

    List<Concept> getAllConceptsByCategory(Long categoryId);

    Concept getConceptById(Long id);

    Concept publishConcept(Long conceptId ,Long adminId);

    List<Concept> getAllConceptsForAdmin();

    List<Concept> getVisibleConceptsForUser();

    List<Problem> getPublishedProblemsByConcept(Long conceptId);

    ConceptProgressResponse getConceptProgress(Long conceptId,Long userId);

}
