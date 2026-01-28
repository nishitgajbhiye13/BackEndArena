package com.BackEndArena.bea.problem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Optional<Problem> findByTitle(String title);

    long countByConceptId(Long conceptId);

    List<Problem> findByConceptId(Long conceptId);
}
