package com.placement.certificate.service;

import com.placement.certificate.entity.Certificate;
import java.util.List;

public interface CertificateService {
    Certificate add(Certificate certificate);
    List<Certificate> getAll();
    Certificate getById(Long id);
    Certificate update(Long id, Certificate certificate);
    void delete(Long id);
}
