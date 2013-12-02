package com.sachin.webprojects.WeatherApplication.exceptoins;

public class URLNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    String message;
    
    public URLNotFoundException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String setMessage() {
        return message;
    }

    @Override
    public String toString() {
        return (URLNotFoundException.class + message);
    }

}
