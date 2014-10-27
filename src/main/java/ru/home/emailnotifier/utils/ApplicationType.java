package ru.home.emailnotifier.utils;

public enum ApplicationType {
    PDF("application/pdf"), TXT("application/txt");

    private String abr;

    private ApplicationType(String type){
        this.abr = type;
    }

    public String getApplicationType(){
        return this.abr;
    }
}
