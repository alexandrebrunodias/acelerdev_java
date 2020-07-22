package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.repository.ChallengeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeRepository repository;

    public ChallengeController(ChallengeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private ResponseEntity<List<Challenge>> findAllByAccelerationIdAndUserId(
            @RequestParam Long accelerationId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(repository.findByAccelerationIdAndUserId(accelerationId, userId));
    }
}
