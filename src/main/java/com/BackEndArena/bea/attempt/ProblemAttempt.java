package com.BackEndArena.bea.attempt;

import com.BackEndArena.bea.User.User;
import com.BackEndArena.bea.problem.Problem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
        name = "problem_attempts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "problem_id"})
        }
)
public class ProblemAttempt {

    public ProblemAttempt(User user, Problem problem) {
        this.user = user;
        this.problem = problem;
        this.startedAt = Instant.now();
        this.status = AttemptStatus.STARTED;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "problem_id", nullable = false)
    @Getter
    private Problem problem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private AttemptStatus status;

    @Column(nullable = false, updatable = false)
    @Getter
    private Instant startedAt;

    @Getter
    @Setter
    private Instant completedAt;

    public ProblemAttempt() {

    }

    public void markCompleted() {
        this.status = AttemptStatus.COMPLETED;
        this.completedAt = Instant.now();
    }
}
