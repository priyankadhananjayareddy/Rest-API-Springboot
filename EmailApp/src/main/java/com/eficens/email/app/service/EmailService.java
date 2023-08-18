package com.eficens.email.app.service;

import com.eficens.email.app.model.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public String sendEmail(EmailTemplate emailTemplate) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromAddress);
            mailMessage.setTo(emailTemplate.getToAddress());
            mailMessage.setText(emailTemplate.getEmailBody());
            mailMessage.setSubject(emailTemplate.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }

    }

    public String sendMailWithAttachment(EmailTemplate emailTemplate) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromAddress);
            mimeMessageHelper.setTo(emailTemplate.getToAddress());
            mimeMessageHelper.setText(emailTemplate.getSubject());
            mimeMessageHelper.setSubject(emailTemplate.getSubject());
            FileSystemResource file = new FileSystemResource(new File(emailTemplate.getFilePath()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            return "File has been sent Successfully to "+emailTemplate.getToAddress();
        } catch (MessagingException e) {
            return "Error while sending mail!!!";

        }
    }
}