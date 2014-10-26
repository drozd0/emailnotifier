package ru.home.emailnotifier.worker;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.emailnotifier.authholder.AuthenticationHolder;
import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Attachment;
import ru.home.emailnotifier.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseEmailWorker implements EmailWorker {
    private final static Logger logger = LoggerFactory.getLogger(BaseEmailWorker.class);
    protected AuthenticationHolder authenticationHolder;

    public BaseEmailWorker(){
        // do nothing
    }

    public BaseEmailWorker(AuthenticationHolder authenticationHolder){
        this.authenticationHolder = authenticationHolder;
    }
    @Override
    public void setAuthenticationHolder(AuthenticationHolder authenticationHolder) {
        this.authenticationHolder = authenticationHolder;
    }

    @Override
    public Map<String, EmailNotifierException> sendPlainTextEmail(String message, String... emails) throws EmailNotifierException {
        try{
            Email emailTemplate = buildPlainTextEmail(message, authenticationHolder);
            return send(emailTemplate, emails);
        }catch(EmailException e){
            throw new EmailNotifierException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, EmailNotifierException> sendHtmlTextEmail(String htmlMessage, String... emailAddresses) throws EmailNotifierException {
        return null;
    }

    @Override
    public Map<String, EmailNotifierException> sendEmailWithAttachments(List<Attachment> attachments, String... emailAddresses) throws EmailNotifierException {
        return null;
    }

    @Override
    public Map<String, EmailNotifierException> sendEmailWithAttachment(Attachment attachment, String... emailAddresses) throws EmailNotifierException {
        return null;
    }

    protected Email buildPlainTextEmail(String message, AuthenticationHolder holder) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(holder.getHost());
        email.setSmtpPort(Constants.SMTP_PORT);
        email.setAuthenticator(new DefaultAuthenticator(holder.getUsername(), holder.getPassword()));
        email.setFrom(holder.getFrom());
        email.setSubject(holder.getSubject());
        email.setMsg(message);
        email.setSSL(true);
        return email;
    }

    private Map<String, EmailNotifierException> send(Email emailTemplate, String...emailAddresses){
        Map<String, EmailNotifierException> result = new HashMap<String, EmailNotifierException>(1);
        for(String email : emailAddresses){
            try{
                emailTemplate.addTo(email).send();
            }catch (EmailException ex){
                logger.error("Cannot send email to {}", email, ex);
                result.put(email, new EmailNotifierException(ex.getMessage(), ex.getCause()));
            }
        }
        return result;
    }
}
