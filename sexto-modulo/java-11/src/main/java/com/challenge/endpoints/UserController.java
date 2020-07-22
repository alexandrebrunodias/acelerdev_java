package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @GetMapping
    private ResponseEntity<List<User>> findByaccelerationName(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false, name = "companyId") Long companyId
    ) {
        if(Strings.isNotBlank(accelerationName))
            return ResponseEntity.ok(repository.findByCandidatesIdAccelerationName(accelerationName));
        if(Objects.nonNull(companyId))
            return ResponseEntity.ok(repository.findByCandidatesIdCompanyId(companyId));
        return ResponseEntity.notFound().build();
    }
}