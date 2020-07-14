package com.challenge.service;

import com.challenge.entity.Challenge;
import com.challenge.repository.ChallengeRepository;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeServiceInterface {

    private final ChallengeRepository repository;

    public ChallengeServiceImpl(ChallengeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
        return repository.findAllByAccelerationsIdAndSubmissions_Id_UserId(accelerationId, userId);
    }

    @Override
    public Challenge save(Challenge challenge) {
        return repository.save(challenge);
    }
}
