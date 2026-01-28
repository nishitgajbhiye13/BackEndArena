package com.BackEndArena.bea.concept;


import com.BackEndArena.bea.attempt.ProblemAttemptRepository;
import com.BackEndArena.bea.category.Category;
import com.BackEndArena.bea.category.CategoryRepository;
import com.BackEndArena.bea.concept.dto.ConceptProgressResponse;
import com.BackEndArena.bea.concept.enums.ConceptStatus;
import com.BackEndArena.bea.exception.ResourceNotFoundException;
import com.BackEndArena.bea.problem.Problem;
import com.BackEndArena.bea.problem.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ConceptServiceImpl implements ConceptService {

    private final CategoryRepository categoryRepository;
    private final ConceptRepository conceptRepository;
    private final ProblemRepository problemRepository;
    private final ProblemAttemptRepository problemAttemptRepository;

    public ConceptServiceImpl(ConceptRepository conceptRepository, CategoryRepository categoryRepository, ProblemRepository problemRepository, ProblemAttemptRepository problemAttemptRepository) {
        this.conceptRepository = conceptRepository;
        this.categoryRepository = categoryRepository;
        this.problemRepository = problemRepository;
        this.problemAttemptRepository = problemAttemptRepository;

    }


    @Override
    public Concept createConcept(String title
            , String description
            , ConceptDifficulty conceptDifficulty
            , Set<Integer> javaVersions
            , Long categoryId
            , boolean transformationConcept) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category not found"));
        Concept concept = new Concept();
        concept.setTitle(title);
        concept.setDescription(description);
        concept.setConceptDifficulty(conceptDifficulty);
        concept.setJavaVersions(javaVersions);
        concept.setCategory(category);
        concept.setTransformationConcept(transformationConcept);
        return conceptRepository.save(concept);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Concept> getAllConcepts() {
        return conceptRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Concept> getAllConceptsByCategory(Long categoryId) {
        return conceptRepository.findByCategoryId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Concept getConceptById(Long id) {
        return conceptRepository.findById(id).orElseThrow(()->new RuntimeException("Concept not found"));
    }

    @Override

    public Concept publishConcept(Long conceptId, Long adminId) {
        Concept concept = conceptRepository.findById(conceptId)
                .orElseThrow(() -> new ResourceNotFoundException("Concept not found"));

        if (concept.getStatus() == ConceptStatus.PUBLISHED) {
            throw new IllegalStateException("Concept already published");
        }

        if (concept.getProblems().size() < 3) {
            throw new IllegalStateException("Concept must have at least 3 problems to publish");
        }

        concept.setStatus(ConceptStatus.PUBLISHED);
        concept.setPublishedAt(Instant.now());
        concept.setPublishedBy(adminId);
        return null;
    }

    //==========Admin========
    @Override
    @Transactional(readOnly = true)
    public List<Concept> getAllConceptsForAdmin() {
        return conceptRepository.findAll();
    }

    //==========User==========
    @Override
    @Transactional(readOnly = true)
    public List<Concept> getVisibleConceptsForUser() {
        return conceptRepository.findByStatus(ConceptStatus.PUBLISHED);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Problem> getPublishedProblemsByConcept(Long conceptId) {

        Concept concept = conceptRepository.findById(conceptId)
                .orElseThrow(()->new RuntimeException("Concept not found"));

        if (concept.getStatus() != ConceptStatus.PUBLISHED) {
            throw new ResourceNotFoundException("Concept not found");
        }
        return problemRepository.findByConceptId(conceptId);
    }

    @Override
    public ConceptProgressResponse getConceptProgress(Long conceptId, Long userId) {

        Concept concept = conceptRepository.findById(conceptId)
                .orElseThrow(() -> new ResourceNotFoundException("Concept not found with id " + conceptId));
        if (concept.getStatus() != ConceptStatus.PUBLISHED) {
            throw new ResourceNotFoundException("Concept not available right now");
        }

        long totalProblems = problemRepository.countByConceptId(conceptId);

        long attemptedProblems = problemAttemptRepository.countDistinctProblemsByUserAndConcept(
                        userId, conceptId
                );

        int progressPercentage = totalProblems == 0
                ? 0
                :(int) ((attemptedProblems * 100) / totalProblems);

        ConceptProgressResponse response = new ConceptProgressResponse();
        response.setConceptId(conceptId);
        response.setTotalProblems((int) totalProblems);
        response.setAttemptedProblems((int) attemptedProblems);
        response.setProgressPercentage(progressPercentage);
        response.setCompleted(totalProblems > 0 && attemptedProblems == totalProblems);

        return response;

    }
}
