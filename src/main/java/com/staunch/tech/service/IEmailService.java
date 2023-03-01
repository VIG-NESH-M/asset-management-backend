package com.staunch.tech.service;

import com.staunch.tech.dto.EmailDetails;
import com.staunch.tech.entity.Ticket;

public interface IEmailService {
    String sendSimpleMail(EmailDetails details);

    String sendUpdateMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}