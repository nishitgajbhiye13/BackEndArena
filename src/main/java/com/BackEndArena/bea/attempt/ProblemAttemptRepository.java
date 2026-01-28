package com.BackEndArena.bea.attempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProblemAttemptRepository extends JpaRepository<ProblemAttempt,Long> {



    Optional<ProblemAttempt> findByUserIdAndProblemId(Long userId, Long problemId);

    boolean existsByUserIdAndProblemId(Long userId, Long problemId);


    @Query("""
    select count(distinct pa.problem.id)
    from ProblemAttempt pa
    where pa.user.id = :userId
      and pa.problem.concept.id = :conceptId
""")
    Long countDistinctProblemsByUserAndConcept(Long userId, Long conceptId);
}
