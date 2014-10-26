package ru.home.emailnotifier.exception;

public class EmailNotifierException extends Exception {

    private final Throwable rootCause;

    public EmailNotifierException()
    {
        super();
        this.rootCause = null;
    }

    public EmailNotifierException(String msg)
    {
        super(msg);
        this.rootCause = null;
    }

    public EmailNotifierException(Throwable rootCause)
    {
        super((rootCause == null) ? null : rootCause.getMessage());
        this.rootCause = rootCause;
    }

    public EmailNotifierException(String msg, Throwable rootCause)
    {
        super(msg);
        this.rootCause = rootCause;
    }

    public Throwable getCause()
    {
        return rootCause;
    }
}
