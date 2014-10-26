package ru.home.emailnotifier.service;

import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;
import ru.home.emailnotifier.worker.EmailWorker;

import java.util.List;
import java.util.Map;

public interface EmailService {
    Map<String,EmailNotifierException> sendPlainTextEmail(String message, String...emailAddresses) throws EmailNotifierException;
    Map<String,EmailNotifierException> sendHtmlEmail(String message, String...emailAddresses) throws EmailNotifierException;
    Map<String,EmailNotifierException> sendEmailWithAttachments(List<Attachment> attachments, String... emailAddresses) throws EmailNotifierException;
    Map<String,EmailNotifierException> sendEmailWithAttachments(Attachment attachment, String...emailAddresses) throws EmailNotifierException;
    void setEmailWorker(EmailWorker worker);
}
