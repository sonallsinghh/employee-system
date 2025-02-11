package com.example.ems.Repository;

import com.example.ems.Entity.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSetRepository extends JpaRepository<SkillSet, Long> {
    // Custom query methods can be added here if needed
}
