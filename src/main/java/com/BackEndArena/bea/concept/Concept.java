package com.BackEndArena.bea.concept;

import com.BackEndArena.bea.category.Category;
import com.BackEndArena.bea.concept.enums.ConceptStatus;
import com.BackEndArena.bea.problem.Problem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Concept")
public class Concept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String Title;

    @Column(nullable = false,length = 4000)
    @Getter
    @Setter
    private String Description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private ConceptDifficulty conceptDifficulty;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "concept_java_versions",
            joinColumns = @JoinColumn(name = "concept_id")
    )
    @Column(name = "java_version")
    @Getter
    @Setter
    private Set<Integer> javaVersions;

    @OneToMany(mappedBy = "concept", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Problem> problems;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @Getter
    @Setter
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private ConceptStatus status = ConceptStatus.DRAFT;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean transformationConcept = false;

    @Column(nullable = false, updatable = false)
    @Getter
    private Instant createdAt;

    @Column(nullable = false)
    @Getter
    private Instant updatedAt;

    @Column(name = "published_at")
    @Getter
    @Setter
    private Instant publishedAt;

    @Column(name = "published_by")
    @Getter
    @Setter
    private Long publishedBy;

    public Concept() {}

    public Concept(
            String title,
            String description,
            ConceptDifficulty difficulty,
            Category category
    ) {
        this.Title = title;
        this.Description = description;
        this.conceptDifficulty = difficulty;
        this.category = category;
    }


    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
