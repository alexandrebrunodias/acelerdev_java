package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionMapper mapper;
    private final SubmissionRepository repository;

    public SubmissionController(SubmissionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private ResponseEntity<List<SubmissionDTO>> findAllByChallengeIdAndAccelerationId(
            @RequestParam Long challengeId,
            @RequestParam Long accelerationId
    ) {
        List<Submission> submissions = repository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
        return ResponseEntity.ok(mapper.map(submissions));
    }
}
