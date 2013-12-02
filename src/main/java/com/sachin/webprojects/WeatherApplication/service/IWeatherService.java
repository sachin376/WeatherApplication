package com.sachin.webprojects.WeatherApplication.service;

import com.sachin.webprojects.WeatherApplication.exceptoins.URLNotFoundException;
import com.sachin.webprojects.WeatherApplication.exceptoins.ZipCodeNotFoundException;

public interface IWeatherService {

    public WeatherReportDTO fetchWeatherReportByZip(String zipCode) throws ZipCodeNotFoundException, URLNotFoundException;
}
