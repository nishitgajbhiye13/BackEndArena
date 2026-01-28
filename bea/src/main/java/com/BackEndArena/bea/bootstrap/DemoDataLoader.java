package com.BackEndArena.bea.bootstrap;

import com.BackEndArena.bea.category.Category;
import com.BackEndArena.bea.category.CategoryRepository;
import com.BackEndArena.bea.concept.Concept;
import com.BackEndArena.bea.concept.ConceptDifficulty;
import com.BackEndArena.bea.concept.ConceptRepository;
import com.BackEndArena.bea.concept.enums.ConceptStatus;
import com.BackEndArena.bea.problem.Problem;
import com.BackEndArena.bea.problem.ProblemDifficulty;
import com.BackEndArena.bea.problem.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DemoDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ConceptRepository conceptRepository;
    private final ProblemRepository problemRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1️⃣ Category
        Category javaCategory = categoryRepository.findByName("Java Basics")
                .orElseGet(() -> categoryRepository.save(
                        new Category(
                                "Java Basics",
                                "Core Java fundamentals"
                        )

                ));

        // 2️⃣ Concept
        Concept oopsConcept = conceptRepository.findByTitleIgnoreCase("OOPS Fundamentals")
                .orElseGet(() -> conceptRepository.save(
                        new Concept(
                                "OOPS Fundamentals",
                                "Object Oriented Programming basics",
                                ConceptDifficulty.BEGINNER,
                                javaCategory
                        )
                ));

        Problem sumProblem = problemRepository.findByTitle("Add Two Numbers")
                .orElseGet(() -> problemRepository.save(
                        new Problem(
                                "Add Two Numbers",
                                "Read two integers and print their sum",
                                oopsConcept,
                                ProblemDifficulty.BEGINNER
                        )
                ));


        System.out.println("✅ Demo data loaded successfully");
    }

    }

