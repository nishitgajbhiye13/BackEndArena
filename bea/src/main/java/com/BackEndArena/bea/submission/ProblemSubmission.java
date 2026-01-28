package com.BackEndArena.bea.submission;

import com.BackEndArena.bea.attempt.ProblemAttempt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "problem_submissions")
public class ProblemSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attempt_id", nullable = false)
    @Getter
    private ProblemAttempt attempt;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Getter
    private String code;

    @Column(nullable = false)
    @Getter
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private SubmissionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    @Getter
    @Setter
    private Verdict verdict;

    @Getter
    @Setter
    @Column(nullable = true)
    private Long totalExecutionTimeMs;



    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    @Column(nullable = false)
    private SubmissionResult result;

    @Column(nullable = false, updatable = false)
    @Getter
    @Setter
    private Instant submittedAt;

    @Getter
    @Setter
    private Instant completedAt;

    protected ProblemSubmission() {
        // JPA
    }

    public ProblemSubmission(
            ProblemAttempt attempt,
            String code,
            String language
    ) {
        this.attempt = attempt;
        this.code = code;
        this.language = language;
        this.status = SubmissionStatus.SUBMITTED;
        this.result=SubmissionResult.ACCEPTED;
        this.submittedAt = Instant.now();
    }

    public void markCompleted(Verdict verdict, long totalExecutionTimeMs) {

        this.verdict = verdict;
        this.totalExecutionTimeMs = totalExecutionTimeMs;

        this.status = SubmissionStatus.COMPLETED;

        this.completedAt = Instant.now();
    }
}
