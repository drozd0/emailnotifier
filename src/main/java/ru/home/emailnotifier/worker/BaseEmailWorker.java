package ru.home.emailnotifier.worker;

import org.apache.commons.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.emailnotifier.authholder.AuthenticationHolder;
import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;
import ru.home.emailnotifier.utils.Constants;

import java.io.IOException;

public class BaseEmailWorker implements EmailWorker {
    private final static Logger logger = LoggerFactory.getLogger(BaseEmailWorker.class);
    protected AuthenticationHolder authenticationHolder;

    public BaseEmailWorker(){
    }

    public BaseEmailWorker(AuthenticationHolder authenticationHolder){
        this.authenticationHolder = authenticationHolder;
    }
    @Override
    public void setAuthenticationHolder(AuthenticationHolder authenticationHolder) {
        this.authenticationHolder = authenticationHolder;
    }

    @Override
    public void sendPlainTextEmail(String message, String subject, String email) throws EmailNotifierException {
        try{
            Email emailTemplate = buildPlainTextEmail(message, subject, authenticationHolder);
            send(emailTemplate, email);
        }catch(EmailException e){
            throw new EmailNotifierException(e.getMessage(), e);
        }
    }

    @Override
    public void sendHtmlTextEmail(String htmlMessage, String subject, String email) throws EmailNotifierException {
        try{
            Email emailTemplate = buildHtmlTextEmail(htmlMessage, subject, authenticationHolder);
            send(emailTemplate, email);
        }catch(EmailException e){
            throw new EmailNotifierException(e.getMessage(), e);
        }
    }

    @Override
    public void sendEmailWithAttachment(String message, String subject, Attachment attachment, String email) throws EmailNotifierException {
        try{
            Email emailTemplate = buildEmailWithAttach(message, subject, attachment, authenticationHolder);
            send(emailTemplate, email);
        }catch(Exception e){
            logger.error("Cannot create emailTemplate!", e);
            throw new EmailNotifierException(e.getMessage(), e);
        }
    }

    protected Email buildEmailWithAttach(String message, String subject, Attachment attachment, AuthenticationHolder holder) throws EmailException, IOException, EmailNotifierException {
        MultiPartEmail email = new MultiPartEmail();
        ByteArrayDataSource dataSource = new ByteArrayDataSource(attachment.getInputStream(), attachment.getType().getApplicationType());
        email.attach(dataSource, attachment.getAttachmentName(), attachment.getAttachmentDescription());
        email.setHostName(holder.getHost());
        email.setSmtpPort(Constants.SMTP_PORT);
        email.setAuthenticator(new DefaultAuthenticator(holder.getUsername(), holder.getPassword()));
        email.setFrom(holder.getFrom());
        email.setSubject(subject == null ? holder.getSubject(): subject);
        email.setMsg(message);
        email.setSSL(true);
        return email;
    }

    protected Email buildPlainTextEmail(String message, String subject, AuthenticationHolder holder) throws EmailException, EmailNotifierException {
        Email email = new SimpleEmail();
        email.setHostName(holder.getHost());
        email.setSmtpPort(Constants.SMTP_PORT);
        email.setAuthenticator(new DefaultAuthenticator(holder.getUsername(), holder.getPassword()));
        email.setFrom(holder.getFrom());
        email.setSubject(subject == null ? holder.getSubject(): subject);
        email.setMsg(message);
        email.setSSL(true);
        return email;
    }

    protected Email buildHtmlTextEmail(String message, String subject, AuthenticationHolder holder) throws EmailException, EmailNotifierException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(holder.getHost());
        email.setSmtpPort(Constants.SMTP_PORT);
        email.setAuthenticator(new DefaultAuthenticator(holder.getUsername(), holder.getPassword()));
        email.setFrom(holder.getFrom());
        email.setSubject(subject == null ? holder.getSubject(): subject);
        email.setHtmlMsg(message);
        email.setSSL(true);
        return email;
    }

    private void send(Email emailTemplate, String email) throws EmailNotifierException{
        try{
            emailTemplate.addTo(email).send();
        }catch (EmailException ex){
            logger.error("Cannot send email to {}", email, ex);
            throw new EmailNotifierException(ex);
        }

    }
}
