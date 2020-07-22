package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    private final AccelerationRepository repository;

    public AccelerationController(AccelerationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<Acceleration> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping
    private ResponseEntity<List<Acceleration>> findAllByCompanyId(@RequestParam Long companyId) {
        return ResponseEntity.ok(repository.findByCandidatesIdCompanyId(companyId));
    }
}
