package com.example.ems.Service;

import com.example.ems.Entity.Project;
import com.example.ems.Repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Cacheable(value = "projects")
    public List<Project> getAllProjects() {
        log.info("Fetching all projects from database (Cache MISS)");
        return projectRepository.findAll();
    }

    @Cacheable(value = "projects", key = "#id")
    public Optional<Project> getProjectById(Long id) {
        log.info("Fetching project with ID: {} from database (Cache MISS)", id);
        return projectRepository.findById(id);
    }

    @CacheEvict(value = "projects", allEntries = true)
    public Project createProject(Project project) {
        log.info("Creating a new project: {}", project);
        return projectRepository.save(project);
    }

    @CacheEvict(value = "projects", allEntries = true)
    public Project updateProject(Long id, Project projectDetails) {
        log.info("Updating project with ID: {}", id);
        Project project = projectRepository.findById(id).orElseThrow();
        project.setProjectName(projectDetails.getProjectName());
        return projectRepository.save(project);
    }

    @CacheEvict(value = "projects", allEntries = true)
    public void deleteProject(Long id) {
        log.info("Deleting project with ID: {}", id);
        projectRepository.deleteById(id);
    }
}
