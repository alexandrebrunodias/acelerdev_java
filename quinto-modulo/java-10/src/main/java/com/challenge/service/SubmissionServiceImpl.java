package com.challenge.service;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionServiceInterface {

    private final SubmissionRepository repository;

    public SubmissionServiceImpl(SubmissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
        return BigDecimal.valueOf(repository.findHigherScoreByChallengeId(challengeId));
    }

    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return repository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

    @Override
    public Submission save(Submission submission) {
        return repository.save(submission);
    }
}
