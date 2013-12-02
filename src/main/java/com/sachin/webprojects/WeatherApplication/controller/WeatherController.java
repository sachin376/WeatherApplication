package com.sachin.webprojects.WeatherApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sachin.webprojects.WeatherApplication.model.WeatherModel;
import com.sachin.webprojects.WeatherApplication.service.IWeatherService;
import com.sachin.webprojects.WeatherApplication.service.WeatherReportDTO;
import com.sachin.webprojects.WeatherApplication.service.WeatherReportDTO.CurrentObservation;
import com.sachin.webprojects.WeatherApplication.validator.WeatherValidator;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private static Logger slf4jLogger = LoggerFactory.getLogger(WeatherController.class);
    public static final String WEATHER_REPORT = "weatherReport";

    @Autowired
    WeatherValidator weatherValidator;

    @Autowired
    private IWeatherService weatherService;

    @RequestMapping(value = "/welcome.htm")
    public String initForm(ModelMap model) {
        slf4jLogger.info("inside init method of my controoler");
        WeatherModel weatherModel = new WeatherModel();
        model.addAttribute("weatherModel", weatherModel);
        model.addAttribute("showResultInfo", false);
        return WEATHER_REPORT;
    }

    @RequestMapping(value = "/getWeather.htm", method = RequestMethod.GET)
    public String getWeather(@ModelAttribute("weatherModel") WeatherModel weatherModel, BindingResult result, ModelMap model) throws Exception
    {
        slf4jLogger.info("inside submit method of my controller " + weatherModel.getZipCode());

        // to do - all kind of validations -- for alpha numeric
        weatherValidator.validate(weatherModel, result);
        if (result.hasErrors()) { // if validator failed
            return WEATHER_REPORT;
        }

        WeatherReportDTO weatherReportDto = weatherService.fetchWeatherReportByZip(weatherModel.getZipCode());
        WeatherModel updatedWeatherModel = copyDTOtoModel(weatherReportDto);
        model.addAttribute("weatherModel", updatedWeatherModel);
        model.addAttribute("showResultInfo", true);
        return WEATHER_REPORT;

    }

    private WeatherModel copyDTOtoModel(WeatherReportDTO weatherReportDto) {

        WeatherModel updatedWeatherModel = new WeatherModel();
        CurrentObservation current_observation = weatherReportDto.getCurrent_observation();
        if (current_observation != null) {
            updatedWeatherModel.setTemp_f(current_observation.getTemp_f());
            if (current_observation.getDisplay_location() != null) {
                updatedWeatherModel.setCity(current_observation.getDisplay_location().getCity());
                updatedWeatherModel.setState(current_observation.getDisplay_location().getState_name());
                updatedWeatherModel.setZipCode(current_observation.getDisplay_location().getZip());
            }
        }

        slf4jLogger.info("weatherModel " + updatedWeatherModel.getState());
        slf4jLogger.info("weatherModel " + updatedWeatherModel.getTemp_f());
        return updatedWeatherModel;
    }

    public void setWeatherValidator(WeatherValidator weatherValidator) {
        this.weatherValidator = weatherValidator;
    }

    public void setWeatherService(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }
}