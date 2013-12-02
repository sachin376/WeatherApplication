package com.sachin.webprojects.WeatherApplication.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.sachin.webprojects.WeatherApplication.model.WeatherModel;
import com.sachin.webprojects.WeatherApplication.service.IWeatherService;
import com.sachin.webprojects.WeatherApplication.service.WeatherReportDTO;
import com.sachin.webprojects.WeatherApplication.service.WeatherReportDTO.CurrentObservation;
import com.sachin.webprojects.WeatherApplication.service.WeatherReportDTO.CurrentObservation.DisplayLocation;
import com.sachin.webprojects.WeatherApplication.validator.WeatherValidator;

public class WeatherControllerTest {

    @Mock
    private IWeatherService weatherService;

    @Mock
    private BindingResult mockBindingResult;

    @Mock
    private ModelMap model;

    @Mock
    WeatherValidator weatherValidator;

    @InjectMocks
    private WeatherController weatherController;

    private MockMvc mockMvc;

    private WeatherReportDTO dummyDTO;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        this.dummyDTO = createDumbyWeatherReportDTO();
        when(weatherService.fetchWeatherReportByZip(any(String.class))).thenReturn(this.dummyDTO);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void testInitForm() throws Exception {

        this.mockMvc.perform(get("/weather/welcome.htm"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(WeatherController.WEATHER_REPORT))
                .andExpect(model().attributeExists("weatherModel"))
                .andExpect(model().attributeExists("showResultInfo"));

    }

    @Test
    public void testGetWeather() throws Exception {

        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setZipCode("94117");
        model.addAttribute("weatherModel", weatherModel);

        this.mockMvc.perform(get("/weather/getWeather.htm").requestAttr("model", model))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(WeatherController.WEATHER_REPORT))
                .andExpect(model().attributeExists("weatherModel"))
                .andExpect(model().attributeExists("showResultInfo")).andExpect(model().attribute("showResultInfo", true));

    }

    private WeatherReportDTO createDumbyWeatherReportDTO() {
        WeatherReportDTO value = new WeatherReportDTO();
        CurrentObservation current_observation = value.new CurrentObservation();
        DisplayLocation display_location = current_observation.new DisplayLocation();
        display_location.setCity("San Francisco");
        display_location.setState_name("California");
        display_location.setZip("94117");
        current_observation.setDisplay_location(display_location);
        current_observation.setTemp_f("51.6");

        value.setCurrent_observation(current_observation);
        return value;
    }

}
