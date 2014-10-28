package ru.home.emailnotifier.service;

import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;
import ru.home.emailnotifier.worker.EmailWorker;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendPlainTextEmail(String message, String subject, String email) throws EmailNotifierException;
    void sendHtmlEmail(String message, String subject, String email) throws EmailNotifierException;
    void sendEmailWithAttachments(String message, String subject, Attachment attachment, String email) throws EmailNotifierException;
    void setEmailWorker(EmailWorker worker);
}
