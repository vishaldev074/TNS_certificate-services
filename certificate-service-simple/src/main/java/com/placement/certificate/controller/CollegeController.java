package com.placement.certificate.controller;

import com.placement.certificate.entity.College;
import com.placement.certificate.repository.CollegeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {
    private final CollegeRepository repository;

    public CollegeController(CollegeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public College add(@RequestBody College college) {
        college.setId(null);
        return repository.save(college);
    }

    @GetMapping
    public List<College> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<College> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<College> update(@PathVariable Long id, @RequestBody College college) {
        return repository.findById(id).map(existing -> {
            existing.setName(college.getName());
            return ResponseEntity.ok(repository.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
