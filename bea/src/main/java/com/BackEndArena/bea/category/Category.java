package com.BackEndArena.bea.category;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;

@Entity
@Table(
        name = "categories",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;




    @Column(nullable = false, unique = true)
    @Getter@Setter

    private String name;

    @Column(length = 1000)
    @Getter@Setter
    private String description;

    @Column(nullable = false, updatable = false)
    @Getter
    private Instant createdAt;


    @Column(nullable = false)
    @Getter
    private Instant updatedAt;

    protected Category() {} // JPA requirement

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
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
