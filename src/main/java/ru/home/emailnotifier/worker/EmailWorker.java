package ru.home.emailnotifier.worker;

import ru.home.emailnotifier.authholder.AuthenticationHolder;
import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;

import java.util.List;
import java.util.Map;

public interface EmailWorker {
    void setAuthenticationHolder(AuthenticationHolder authenticationHolder);
    void sendPlainTextEmail(String message,String subject, String email) throws EmailNotifierException;
    void sendHtmlTextEmail(String htmlMessage,String subject, String email) throws EmailNotifierException;
    void sendEmailWithAttachment(String message,String subject, Attachment attachment, String email) throws EmailNotifierException;
}
