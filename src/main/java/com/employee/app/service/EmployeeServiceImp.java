package com.employee.app.service;

import com.employee.app.entity.Employee;
import com.employee.app.features.EmailService;
import com.employee.app.features.EmailServiceImp;
import com.employee.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public int addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        emailService.sendEmailToManager(savedEmployee);     // mail service

        return savedEmployee.getId();
    }

    @Override
    public List<Employee> getAllEmployees(int page, int size, String sortBy, String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size,sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.getContent();

    }



    @Override
    public String deleteEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        } catch (Exception e) {
            return "Error deleting employee: " + e.getMessage();
        }
    }

    @Override
    public String updateEmployee(int id, Employee employee) {
        try {
            if (employeeRepository.existsById(id)) {
                employee.setId(id);
                employeeRepository.save(employee);
                return "Employee updated successfully";
            } else {
                return "Employee not found";
            }
        } catch (Exception e) {
            return "Error updating employee: " + e.getMessage();
        }
    }

//    ****************************************************************************************************

    @Override
    public Employee getNthLevelManager(int employeeId, int level) {

        int i=0;
        int nextId=employeeId;
        while(i<level){
            Optional<Employee> employee= employeeRepository.findById(nextId);

            String managerId= employee.get().getReportsTo();
            nextId= Integer.parseInt(managerId);
            i++;
        }

        Optional<Employee> res=employeeRepository.findById(nextId);
        return res.get();

    }






}
