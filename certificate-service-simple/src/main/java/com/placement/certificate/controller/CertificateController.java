package com.placement.certificate.controller;

import com.placement.certificate.entity.Certificate;
import com.placement.certificate.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {
    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @PostMapping
    public Certificate add(@RequestBody Certificate certificate) {
        return service.add(certificate);
    }

    @GetMapping
    public List<Certificate> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Certificate getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Certificate update(@PathVariable Long id, @RequestBody Certificate certificate) {
        return service.update(id, certificate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
