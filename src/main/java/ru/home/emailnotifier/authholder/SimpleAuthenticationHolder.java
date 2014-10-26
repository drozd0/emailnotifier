package ru.home.emailnotifier.authholder;

public class SimpleAuthenticationHolder implements AuthenticationHolder {
    private String username;
    private String password;
    private String host;
    private String from;
    private String subject;


    public SimpleAuthenticationHolder(){
        // do nothing
    }

    public SimpleAuthenticationHolder(String username, String password, String host, String from, String subject) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.from = from;
        this.subject = subject;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
