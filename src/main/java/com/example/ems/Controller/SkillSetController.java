package com.example.ems.Controller;

import com.example.ems.Entity.SkillSet;
import com.example.ems.Service.SkillSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skillsets")
public class SkillSetController {

    @Autowired
    private SkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<SkillSet>> getAllSkillSets() {
        List<SkillSet> skillSets = skillSetService.getAllSkillSets();
        return new ResponseEntity<>(skillSets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillSet> getSkillSetById(@PathVariable Long id) {
        return skillSetService.getSkillSetById(id)
                .map(skillSet -> new ResponseEntity<>(skillSet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SkillSet> createSkillSet(@RequestBody SkillSet skillSet) {
        SkillSet createdSkillSet = skillSetService.createSkillSet(skillSet);
        return new ResponseEntity<>(createdSkillSet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillSet> updateSkillSet(@PathVariable Long id, @RequestBody SkillSet skillSetDetails) {
        SkillSet updatedSkillSet = skillSetService.updateSkillSet(id, skillSetDetails);
        return new ResponseEntity<>(updatedSkillSet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkillSet(@PathVariable Long id) {
        skillSetService.deleteSkillSet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
