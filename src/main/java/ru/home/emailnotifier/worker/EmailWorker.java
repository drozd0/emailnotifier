package ru.home.emailnotifier.worker;

import ru.home.emailnotifier.authholder.AuthenticationHolder;
import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;

import java.util.List;
import java.util.Map;

public interface EmailWorker {
    void setAuthenticationHolder(AuthenticationHolder authenticationHolder);
    Map<String,EmailNotifierException> sendPlainTextEmail(String message, String...emailAddresses) throws EmailNotifierException;
    Map<String,EmailNotifierException> sendHtmlTextEmail(String htmlMessage, String...emailAddresses) throws EmailNotifierException;
    Map<String,EmailNotifierException> sendEmailWithAttachment(String message, Attachment attachment, String...emailAddresses) throws EmailNotifierException;
}
