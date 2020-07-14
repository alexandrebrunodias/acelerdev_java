package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, CandidateId> {

    List<Candidate> findById_CompanyId(Long companyId);
    List<Candidate> findById_AccelerationId(Long accelerationId);
}
