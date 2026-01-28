package com.BackEndArena.bea.problem;

import com.BackEndArena.bea.concept.Concept;
import com.BackEndArena.bea.concept.ConceptRepository;
import com.BackEndArena.bea.concept.enums.ConceptStatus;
import com.BackEndArena.bea.exception.ResourceNotFoundException;
import com.BackEndArena.bea.problem.dto.CreateProblemRequest;
import com.BackEndArena.bea.problem.dto.ProblemResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {

    private ProblemRepository problemRepository;
    private ConceptRepository conceptRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository, ConceptRepository conceptRepository) {
        this.problemRepository = problemRepository;
        this.conceptRepository = conceptRepository;
    }

    @Override
    public Problem createProblem(CreateProblemRequest request) {

        Concept concept = conceptRepository.findById(request.getConceptId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Concept not found with id: " + request.getConceptId())
                );

        Problem problem = new Problem();
        problem.setTitle(request.getTitle());
        problem.setStatement(request.getStatement());
        problem.setDifficulty(request.getDifficulty());
        problem.setIndustryBased(request.isIndustryBased());
        problem.setConcept(concept);
        return problemRepository.save(problem);
    }

    @Override
    public List<ProblemResponseDto> getPublishedProblemsByConcept(Long conceptId) {

        Concept concept = conceptRepository.findById(conceptId).orElseThrow(()->
                new ResourceNotFoundException("Concept not found with id: "));

        if(concept.getStatus() != ConceptStatus.PUBLISHED) {
            throw new ResourceNotFoundException("Concept not found with id: ");
        }


        return problemRepository.findByConceptId(conceptId)
                .stream().map(problem -> {
                    ProblemResponseDto problemResponseDto = new ProblemResponseDto();
                    problemResponseDto.setId(problem.getId());
                    problemResponseDto.setTitle(problem.getTitle());
                    problemResponseDto.setDescription(problemResponseDto.getDescription());
                    problemResponseDto.setDifficulty(problem.getDifficulty().name());
                    return problemResponseDto;
                })
                .toList();
    }


}
