package com.BackEndArena.bea.attempt;

import com.BackEndArena.bea.User.User;
import com.BackEndArena.bea.User.UserRepository;
import com.BackEndArena.bea.exception.AttemptAlreadyExsistsException;
import com.BackEndArena.bea.exception.ResourceNotFoundException;
import com.BackEndArena.bea.problem.Problem;
import com.BackEndArena.bea.problem.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProblemAttemptServiceImpl implements ProblemAttemptService {

    private final ProblemAttemptRepository problemAttemptRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;

    public ProblemAttemptServiceImpl(ProblemAttemptRepository problemAttemptRepository
            ,ProblemRepository problemRepository, UserRepository userRepository) {
        this.problemAttemptRepository = problemAttemptRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProblemAttempt startAttempt(Long problemId, Long userId) {
        if (problemAttemptRepository.existsByUserIdAndProblemId(userId, problemId)) {
            throw new AttemptAlreadyExsistsException(
                    "Attempt already exists for this problem"
            );
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Problem not found"));

        ProblemAttempt attempt = new ProblemAttempt(user, problem);

        return problemAttemptRepository.save(attempt);
    }
}

