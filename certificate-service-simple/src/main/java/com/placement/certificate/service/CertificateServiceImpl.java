package com.placement.certificate.service;

import com.placement.certificate.entity.Certificate;
import com.placement.certificate.entity.College;
import com.placement.certificate.repository.CertificateRepository;
import com.placement.certificate.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository repository;
    private final CollegeRepository collegeRepository;

    public CertificateServiceImpl(CertificateRepository repository,
                                  CollegeRepository collegeRepository) {
        this.repository = repository;
        this.collegeRepository = collegeRepository;
    }

    @Override
    public Certificate add(Certificate certificate) {
        certificate.setId(null);
        certificate.setCollege(resolveCollege(certificate.getCollege()));
        return repository.save(certificate);
    }

    @Override
    public List<Certificate> getAll() {
        return repository.findAll();
    }

    @Override
    public Certificate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public Certificate update(Long id, Certificate certificate) {
        Certificate existing = getById(id);
        existing.setYear(certificate.getYear());
        existing.setCollege(resolveCollege(certificate.getCollege()));
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Certificate not found");
        }
        repository.deleteById(id);
    }

    private College resolveCollege(College college) {
        if (college == null || college.getId() == null) {
            throw new IllegalArgumentException("College id is required");
        }
        return collegeRepository.findById(college.getId())
                .orElseThrow(() -> new RuntimeException("College not found"));
    }
}
