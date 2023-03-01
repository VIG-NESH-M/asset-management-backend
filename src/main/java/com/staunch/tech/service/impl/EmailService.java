package com.staunch.tech.service.impl;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.staunch.tech.dto.EmailDetails;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
 

@Service
public class EmailService implements IEmailService {
 
    @Autowired
    private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}")
    private String sender;

    private static final String body = "Dear [[username]], \n \n You got a new ticket : [[title]]. \n \n Details :- [[content]] \n \n" +
            " Thanks & Regards, \n AMS Developers";

    private static final String updateBody = "Dear [[username]], \n \n Your Ticket is updated : [[title]]. \n \n Details :- [[content]] \n \n" +
            " Thanks & Regards, \n AMS Developers";

    /**
     *
     * @param details
     * @return
     */
    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            String messageBody = body.replace("[[username]]", details.getRecipientName()).replace("[[title]]", details.getSubject())
                    .replace("[[content]]", details.getMsgBody());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(messageBody);
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            throw new AssetManagementException("Error while sending the mail");
        }
    }

    /**
     *
     * @param details
     * @return
     */
    @Override
    public String sendUpdateMail(EmailDetails details) {
        try {
            String messageBody = updateBody.replace("[[username]]", details.getRecipientName()).replace("[[title]]", details.getSubject())
                    .replace("[[content]]", details.getMsgBody());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(messageBody);
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            throw new AssetManagementException("Error while sending the mail");
        }
    }

    /**
     *
     * @param details
     * @return
     */
    @Override
    public String sendMailWithAttachment(EmailDetails details)
    {
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());
            mimeMessageHelper.addAttachment(details.getAttachment().getName(), details.getAttachment());
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }
}