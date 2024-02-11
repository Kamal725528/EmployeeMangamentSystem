package com.employee.app.features;

import com.employee.app.entity.Employee;

public interface EmailService {

    public void sendEmailToManager(Employee employee);
}
