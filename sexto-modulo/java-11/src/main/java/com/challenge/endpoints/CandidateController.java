package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateRepository repository;

    @Autowired
    private CandidateMapper mapper;

    public CandidateController(CandidateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    private ResponseEntity<CandidateDTO> findById(
            @PathVariable Long userId,
            @PathVariable Long accelerationId,
            @PathVariable Long companyId
    ) {
        Candidate candidate = repository
                .findByIdUserIdAndIdCompanyIdAndIdAccelerationId(userId, companyId, accelerationId).get();
        return ResponseEntity.ok().body(mapper.map(candidate));
    }

    @GetMapping
    private ResponseEntity<List<CandidateDTO>> findAllByCompanyId(@RequestParam Long companyId) {
        List<Candidate> candidates = repository.findByIdCompanyId(companyId);
        return ResponseEntity.ok(mapper.map(candidates));
    }

}
