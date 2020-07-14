package com.challenge.service;

import com.challenge.entity.*;
import com.challenge.repository.CandidateRepository;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateServiceInterface {

    private final CandidateRepository repository;

    public CandidateServiceImpl(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Candidate> findById(CandidateId id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
        CandidateId id = new CandidateId(
                new User().setId(userId),
                new Acceleration().setId(accelerationId),
                new Company().setId(companyId)
        );
        return repository.findById(id);
    }

    @Override
    public List<Candidate> findByCompanyId(Long companyId) {
        return repository.findById_CompanyId(companyId);
    }

    @Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
        return repository.findById_AccelerationId(accelerationId);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }
}
