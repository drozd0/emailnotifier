package ru.home.emailnotifier.authholder;

public interface AuthenticationHolder {
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    public String getHost();
    public void setHost(String host);
    public String getFrom();
    public void setFrom(String from);
    public String getSubject();
    public void setSubject(String subject);
}
