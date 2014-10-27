package ru.home.emailnotifier.utils;

import java.io.InputStream;

public class Attachment {
    private InputStream inputStream;
    private ApplicationType type;
    private String attachmentName;
    private String attachmentDescription;

    public Attachment(InputStream inputStream, ApplicationType type, String attachmentName, String attachmentDescription) {
        this.inputStream = inputStream;
        this.type = type;
        this.attachmentName = attachmentName;
        this.attachmentDescription = attachmentDescription;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public ApplicationType getType() {
        return type;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public String getAttachmentDescription() {
        return attachmentDescription;
    }
}
