package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository repository;

    public CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<Company> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping
    private ResponseEntity<List<Company>> findByAccelerationId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long userId
    ) {
        if(Objects.nonNull(accelerationId))
            return ResponseEntity.ok(repository.findDistinctByCandidatesIdAccelerationId(accelerationId));
        if(Objects.nonNull(userId))
            return ResponseEntity.ok(repository.findByCandidatesIdUserId(userId));
        return ResponseEntity.badRequest().build();
    }

}
