package com.example.ems.Service;

import com.example.ems.Entity.SkillSet;
import com.example.ems.Repository.SkillSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SkillSetService {

    @Autowired
    private SkillSetRepository skillSetRepository;

    @Cacheable(value = "skillSets")
    public List<SkillSet> getAllSkillSets() {
        log.info("Fetching all skill sets from database (Cache MISS)");
        return skillSetRepository.findAll();
    }

    @Cacheable(value = "skillSets", key = "#id")
    public Optional<SkillSet> getSkillSetById(Long id) {
        log.info("Fetching skill set with ID: {} from database (Cache MISS)", id);
        return skillSetRepository.findById(id);
    }

    @CacheEvict(value = "skillSets", allEntries = true)
    public SkillSet createSkillSet(SkillSet skillSet) {
        log.info("Creating a new skill set: {}", skillSet);
        return skillSetRepository.save(skillSet);
    }

    @CacheEvict(value = "skillSets", allEntries = true)
    public SkillSet updateSkillSet(Long id, SkillSet skillSetDetails) {
        log.info("Updating skill set with ID: {}", id);
        SkillSet skillSet = skillSetRepository.findById(id).orElseThrow();
        skillSet.setSkillName(skillSetDetails.getSkillName());
        return skillSetRepository.save(skillSet);
    }

    @CacheEvict(value = "skillSets", allEntries = true)
    public void deleteSkillSet(Long id) {
        log.info("Deleting skill set with ID: {}", id);
        skillSetRepository.deleteById(id);
    }
}
