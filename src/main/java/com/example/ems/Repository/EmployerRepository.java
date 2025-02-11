package com.example.ems.Repository;

import com.example.ems.Entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    // Custom query methods can be added here if needed
}
