package ru.home.emailnotifier.authholder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.emailnotifier.exception.EmailNotifierException;
import ru.home.emailnotifier.utils.Constants;

import java.io.*;
import java.util.Properties;

public class FileAuthenticationHolder implements AuthenticationHolder {
    private static final Logger logger = LoggerFactory.getLogger(FileAuthenticationHolder.class);

    private String filePath;
    private Properties propertiesHolder;

    public FileAuthenticationHolder(){
    }

    public FileAuthenticationHolder(String filePath){
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
         this.filePath = filePath;
    }

    /**
     * Invoke after setFilePath().
     */
    public void reloadProperties() {
        InputStream stream = null;
        try {
            logger.debug("Into reloadProperties.");
            propertiesHolder = new Properties();
            if(filePath == null)
                stream = getClass().getClassLoader().getResourceAsStream(Constants.EMAIL_CONFIG_FILE);
            else
                stream = new FileInputStream(filePath);
            propertiesHolder.load(stream);
        } catch (IOException e) {
            logger.error("Cannot reload properties.", e);
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("Failed cloase input stream.", e);
                }
            }
        }
    }

    @Override
    public String getUsername(){
        if(null == propertiesHolder)
            reloadProperties();
        return propertiesHolder.getProperty(Constants.USERNAME);
    }

    @Override
    public String getPassword(){
        if(null == propertiesHolder)
            reloadProperties();
        return propertiesHolder.getProperty(Constants.PASSWORD);
    }

    @Override
    public String getHost(){
        if(null == propertiesHolder)
            reloadProperties();
        return propertiesHolder.getProperty(Constants.HOST);
    }

    @Override
    public String getFrom(){
        if(null == propertiesHolder)
            reloadProperties();
        return propertiesHolder.getProperty(Constants.FROM);
    }

    @Override
    public String getSubject(){
        if(null == propertiesHolder)
            reloadProperties();
        return propertiesHolder.getProperty(Constants.SUBJECT);
    }
}
