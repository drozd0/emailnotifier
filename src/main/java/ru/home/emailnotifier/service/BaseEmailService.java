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
    public Map<String,EmailNotifierException> sendPlainTextEmail(String message, String... emailAddresses) throws EmailNotifierException {
        return worker.sendPlainTextEmail(message, emailAddresses);
    }

    @Override
    public Map<String,EmailNotifierException> sendHtmlEmail(String message, String... emailAddresses) throws EmailNotifierException {
        return worker.sendHtmlTextEmail(message, emailAddresses);
    }

    @Override
    public Map<String,EmailNotifierException> sendEmailWithAttachments(String message, Attachment attachment, String... emailAddresses) throws EmailNotifierException {
        return worker.sendEmailWithAttachment(message, attachment, emailAddresses);
    }

    @Override
    public void setEmailWorker(EmailWorker worker) {
        this.worker = worker;
    }
}
