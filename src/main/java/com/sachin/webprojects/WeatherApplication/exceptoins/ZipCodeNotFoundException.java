package com.sachin.webprojects.WeatherApplication.exceptoins;

public class ZipCodeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    String message;
    
    public ZipCodeNotFoundException(String message) {
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
        return (ZipCodeNotFoundException.class + message);
    }

}
