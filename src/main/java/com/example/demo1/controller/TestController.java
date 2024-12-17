package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.web.bind.annotation.*;

import com.example.demo1.model.Employee;
import com.example.demo1.repository.EmployeeRepository;

import java.util.List;

@CrossOrigin("*")
@RestController(value = "test")
@RequestMapping("/api/v1/employees")
public class TestController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee Eemployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employee với id: " + id));
        Eemployee.setFirstName(employee.getFirstName());
        Eemployee.setLastName(employee.getLastName());
        Eemployee.setEmail(employee.getEmail());

        Employee updatedEmployee = employeeRepository.save(Eemployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employee với id: " + id));

        employeeRepository.delete(employee);
        return ResponseEntity.ok("Xóa thành công employee với id: " + id);
    }


}
