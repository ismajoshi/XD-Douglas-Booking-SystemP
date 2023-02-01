package com.csis3275.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
    private JavaMailSender sender;

    @GetMapping("/Email")
    public String sendMail(Model model) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("ScottLinden@me.com");
            helper.setText("Greetings :)");
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("error", "There was an error sending the email!");
        }
        sender.send(message);
        model.addAttribute("success", "The Email was sent!");
        
        return "Email";
    }
}
