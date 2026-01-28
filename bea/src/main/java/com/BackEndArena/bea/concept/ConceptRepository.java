package com.BackEndArena.bea.concept;

import com.BackEndArena.bea.concept.enums.ConceptStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConceptRepository extends JpaRepository<Concept, Long> {

    Optional<Concept> findByTitleIgnoreCase(String title);


    List<Concept> findByCategoryId(Long categoryId);
    List<Concept> findByConceptDifficulty(ConceptDifficulty conceptDifficulty);
    List<Concept> findByTransformationConceptTrue();
    List<Concept> findByStatus(ConceptStatus status);

}
