package com.employee.app.controller;

import com.employee.app.entity.Employee;
import com.employee.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Integer> addEmployee(@RequestBody Employee employee) {
        Integer id = employeeService.addEmployee(employee);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
                                                           @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir) {

        List<Employee> employees = employeeService.getAllEmployees(page, size, sortBy, sortDir);
        return ResponseEntity.ok(employees);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        String result = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        String result = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{employeeId}/manager/{level}")
    public ResponseEntity<Employee> getNthLevelManager(@PathVariable int employeeId, @PathVariable int level) {
        Employee manager = employeeService.getNthLevelManager(employeeId, level);
        if (manager == null) {
            return ResponseEntity.notFound().build(); // Employee or manager not found
        }

        return ResponseEntity.ok(manager);
    }




}
