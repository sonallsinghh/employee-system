package com.example.ems.Service;

import com.example.ems.Entity.Employee;
import com.example.ems.Repository.EmployeeRepository;
import jakarta.persistence.Cacheable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees from database (Cache MISS)");
        return employeeRepository.findAll();
    }


    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {} from database (Cache MISS)", id);
        return employeeRepository.findById(id);
    }

    @CacheEvict(value = "employees", allEntries = true)
    public Employee createEmployee(Employee employee) {
        log.info("Creating a new employee: {}", employee);
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employees", allEntries = true)
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info("Updating employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setRole(employeeDetails.getRole());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setEmployer(employeeDetails.getEmployer());
        employee.setSkillSets(employeeDetails.getSkillSets());
        employee.setProjects(employeeDetails.getProjects());
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }
}
