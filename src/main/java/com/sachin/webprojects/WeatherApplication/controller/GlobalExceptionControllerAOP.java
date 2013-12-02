package com.sachin.webprojects.WeatherApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sachin.webprojects.WeatherApplication.exceptoins.URLNotFoundException;
import com.sachin.webprojects.WeatherApplication.exceptoins.ZipCodeNotFoundException;
import com.sachin.webprojects.WeatherApplication.model.WeatherModel;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionControllerAOP {

    private static Logger slf4jLogger = LoggerFactory.getLogger(GlobalExceptionControllerAOP.class);
    public static final String WEATHER_REPORT = "weatherReport";

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "this is Exception.class");
        return model;
    }

    @ExceptionHandler(ZipCodeNotFoundException.class)
    public ModelAndView handleZipCodeNotFoundException(ZipCodeNotFoundException ex) {
        slf4jLogger.info("*************inisde exception AOP*****************");
        ModelAndView model = new ModelAndView(WEATHER_REPORT);
        model.addObject("errMsg", ex.getMessage());
        model.addObject("weatherModel", new WeatherModel());
        model.addObject("showResultInfo", false);
        return model;

    }

    @ExceptionHandler(URLNotFoundException.class)
    public ModelAndView handleURLNotFoundException(URLNotFoundException ex) {
        ModelAndView model = new ModelAndView(WEATHER_REPORT);
        model.addObject("errMsg", ex.getMessage());
        model.addObject("weatherModel", new WeatherModel());
        model.addObject("showResultInfo", false);
        return model;

    }
}
