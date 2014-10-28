package ru.home.emailnotifier.authholder;

import ru.home.emailnotifier.exception.EmailNotifierException;

public interface AuthenticationHolder {
    String getUsername();
    String getPassword();
    public String getHost();
    public String getFrom();
    public String getSubject();
}
