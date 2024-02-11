package com.employee.app.service;

import com.employee.app.entity.Employee;

import java.util.List;

public interface EmployeeService {

    int addEmployee(Employee employee);

    List<Employee> getAllEmployees(int page, int size, String sortBy, String sortDir);


    String updateEmployee(int id, Employee employee);

    String deleteEmployee(int id);

    Employee getNthLevelManager(int employeeId, int level);

}

