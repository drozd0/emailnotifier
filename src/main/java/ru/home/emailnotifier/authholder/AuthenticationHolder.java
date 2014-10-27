package ru.home.emailnotifier.authholder;

public interface AuthenticationHolder {
    String getUsername();
    String getPassword();
    public String getHost();
    public String getFrom();
    public String getSubject();
}
