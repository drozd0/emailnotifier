package ru.home.emailnotifier.authholder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileAuthenticationHolder implements AuthenticationHolder {
    private static final Logger logger = LoggerFactory.getLogger(FileAuthenticationHolder.class);

    private String filePath;
    private Properties propertiesHolder;

    public FileAuthenticationHolder(){
    }

    public FileAuthenticationHolder(String filePath){
        this.filePath = filePath;
        buildPropertiesHolder(filePath);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        buildPropertiesHolder(filePath);
    }

    private void buildPropertiesHolder(String filePath) {
        InputStream inputStream = null;
        try{
            inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            propertiesHolder.load(inputStream);
        }catch(IOException e){
            logger.error(" ", e);
        }finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("Cannot close input stream of file[{}]", filePath, e);
                }
        }

    }

    @Override
    public String getUsername() throws EmailNotifierException{
        if(propertiesHolder.isEmpty())
            throw new EmailNotifierException("username cannot be undetermined!");
        return propertiesHolder.getProperty(Constants.USERNAME);
    }

    @Override
    public String getPassword() throws EmailNotifierException{
        if(propertiesHolder.isEmpty())
            throw new EmailNotifierException("password cannot be undetermined!");
        return propertiesHolder.getProperty(Constants.PASSWORD);
    }

    @Override
    public String getHost() throws EmailNotifierException{
        if(propertiesHolder.isEmpty())
            throw new EmailNotifierException("host cannot be undetermined!");
        return propertiesHolder.getProperty(Constants.HOST);
    }

    @Override
    public String getFrom() throws EmailNotifierException{
        if(propertiesHolder.isEmpty())
            throw new EmailNotifierException("from cannot be undetermined!");
        return propertiesHolder.getProperty(Constants.FROM);
    }

    @Override
    public String getSubject() throws EmailNotifierException{
        if(propertiesHolder.isEmpty())
            throw new EmailNotifierException("subject cannot be undetermined!");
        return propertiesHolder.getProperty(Constants.SUBJECT);
    }
}
