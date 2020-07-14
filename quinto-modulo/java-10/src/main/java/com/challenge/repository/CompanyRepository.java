package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findDistinctByCandidates_Id_AccelerationId(Long accelerationId);
    List<Company> findByCandidates_Id_UserId(Long userId);
}
