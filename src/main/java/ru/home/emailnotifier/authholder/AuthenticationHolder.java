package ru.home.emailnotifier.authholder;

import ru.home.emailnotifier.exception.EmailNotifierException;

public interface AuthenticationHolder {
    String getUsername() throws EmailNotifierException;
    String getPassword() throws EmailNotifierException ;
    public String getHost() throws EmailNotifierException;
    public String getFrom() throws EmailNotifierException;
    public String getSubject() throws EmailNotifierException;
}
