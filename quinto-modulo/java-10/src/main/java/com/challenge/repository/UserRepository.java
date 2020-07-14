package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCandidates_Id_AccelerationName(String name);
    List<User> findByCandidates_Id_CompanyId(Long companyId);

}
