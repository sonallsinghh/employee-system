package com.example.ems.Service;

import com.example.ems.Entity.Employer;
import com.example.ems.Repository.EmployerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Cacheable(value = "employers")
    public List<Employer> getAllEmployers() {
        log.info("Fetching all employers from database (Cache MISS)");
        return employerRepository.findAll();
    }

    @Cacheable(value = "employers", key = "#id")
    public Optional<Employer> getEmployerById(Long id) {
        log.info("Fetching employer with ID: {} from database (Cache MISS)", id);
        return employerRepository.findById(id);
    }

    @CacheEvict(value = "employers", allEntries = true)
    public Employer createEmployer(Employer employer) {
        log.info("Creating a new employer: {}", employer);
        return employerRepository.save(employer);
    }

    @CacheEvict(value = "employers", allEntries = true)
    public Employer updateEmployer(Long id, Employer employerDetails) {
        log.info("Updating employer with ID: {}", id);
        Employer employer = employerRepository.findById(id).orElseThrow();
        employer.setName(employerDetails.getName());
        employer.setAddress(employerDetails.getAddress());
        employer.setContactNumber(employerDetails.getContactNumber());
        employer.setEmail(employerDetails.getEmail());
        return employerRepository.save(employer);
    }

    @CacheEvict(value = "employers", allEntries = true)
    public void deleteEmployer(Long id) {
        log.info("Deleting employer with ID: {}", id);
        employerRepository.deleteById(id);
    }
}
