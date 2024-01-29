package com.example.garagesystembackend.services.interfaces;

import org.springframework.mail.SimpleMailMessage;

public interface IEmailService {
    public void sendEmail(SimpleMailMessage email);
}
