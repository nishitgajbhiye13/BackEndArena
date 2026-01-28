package com.BackEndArena.bea.problem;

import com.BackEndArena.bea.concept.Concept;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    // Short title shown in UI
    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    // Actual problem description
    @Column(nullable = false, length = 5000)
    @Getter
    @Setter
    private String statement;

    // BEGINNER / INTERMEDIATE / ADVANCED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private ProblemDifficulty difficulty;

    // Marks real-world / interview-style problems
    @Column(nullable = false)
    @Getter
    @Setter
    private boolean industryBased = false;

    // Each problem belongs to ONE concept
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id", nullable = false)
    @Getter
    @Setter
    private Concept concept;

    @Column(nullable = false, updatable = false)
    @Getter
    private Instant createdAt;

    protected Problem() {}

    public Problem(
            String title,
            String description,
            Concept concept,
            ProblemDifficulty difficulty
    ) {
        this.title = title;
        this.statement = description;
        this.concept = concept;
        this.difficulty = difficulty;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }


}
