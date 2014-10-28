package ru.home.emailnotifier.service;

import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;
import ru.home.emailnotifier.worker.EmailWorker;

import java.util.List;
import java.util.Map;

public class BaseEmailService implements EmailService {
    private EmailWorker worker;

    public BaseEmailService() {
    }

    public BaseEmailService(EmailWorker worker) {
        this.worker = worker;
    }

    @Override
    public void sendPlainTextEmail(String message, String email) throws EmailNotifierException {
        worker.sendPlainTextEmail(message, email);
    }

    @Override
    public void sendHtmlEmail(String message, String email) throws EmailNotifierException {
        worker.sendHtmlTextEmail(message, email);
    }

    @Override
    public void sendEmailWithAttachments(String message, Attachment attachment, String email) throws EmailNotifierException {
        worker.sendEmailWithAttachment(message, attachment, email);
    }

    @Override
    public void setEmailWorker(EmailWorker worker) {
        this.worker = worker;
    }
}
