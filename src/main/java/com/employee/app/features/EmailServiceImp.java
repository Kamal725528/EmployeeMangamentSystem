package com.employee.app.features;

import com.employee.app.entity.Employee;
import com.employee.app.repository.EmployeeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override

    public void sendEmailToManager(Employee employee){

        String level1ManagerId = employee.getReportsTo();

        // Fetch level 1 manager details
        Optional<Employee> level1Manager = employeeRepository.findById(Integer.parseInt(level1ManagerId));

        if (level1Manager.isPresent()) {
            Employee manager = level1Manager.get();

            // Compose email message
            String subject = "New Employee Notification";
            String message = String.format("%s will now work under you. Mobile number is %s and email is %s",
                    employee.getEmployeeName(), employee.getPhoneNumber(), employee.getEmail());

            // Send email
            sendEmail(manager.getEmail(), subject, message);
        }

    }

    @Autowired
    private JavaMailSender sender;
    public void sendEmail(String to, String subject, String body){


        MimeMessage message = sender.createMimeMessage();

        try {

            MimeMessageHelper messageHelper = new MimeMessageHelper(message , true);

            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(body);
            sender.send(message);

        }
        catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
