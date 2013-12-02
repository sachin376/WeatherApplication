package com.sachin.webprojects.WeatherApplication.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sachin.webprojects.WeatherApplication.exceptoins.URLNotFoundException;
import com.sachin.webprojects.WeatherApplication.exceptoins.ZipCodeNotFoundException;
import com.sachin.webprojects.WeatherApplication.service.WeatherReportDTO.Response.Error;

@Component
public class WeatherService implements IWeatherService {

    private static Logger slf4jLogger = LoggerFactory.getLogger(WeatherService.class);

    @Value("${rest.error.received}")
    private String REST_ERROR_RECEIVED;
    @Value("${domain.name}")
    private String DOMAIN_NAME;

    @Value("${api_path}")
    private String API_PATH;
    @Value("${json_xml}")
    private String JSON_XML;

    
    /**
     * Fetch the Weather Report for a given zip code
     * @param Zipcode
     * @return WeatherReportDTO
     * @throws ZipCodeNotFoundException, URLNotFoundException
     */
    @Override
    public WeatherReportDTO fetchWeatherReportByZip(String zipCode) throws ZipCodeNotFoundException, URLNotFoundException {

        RestTemplate restTemplate = createRestTemplate();
        URI url = createUrl(zipCode);

        WeatherReportDTO weatherReport = null;
        weatherReport = restTemplate.getForObject(url, WeatherReportDTO.class);

        Error error = weatherReport.getResponse().getError();
        if (error != null) {
            if (REST_ERROR_RECEIVED.equalsIgnoreCase(error.getType())) {
                throw new ZipCodeNotFoundException("Zipcode " + zipCode + ": Not found");
            }
            return weatherReport;
        }

        slf4jLogger.info(weatherReport.getCurrent_observation().getTemp_f());
        slf4jLogger.info(weatherReport.getCurrent_observation().getDisplay_location().getState_name());
        return weatherReport;

    }

    /**
     * Creates Rest Template using HttpMessageConverter
     * @return RestTemplate
     */
    private RestTemplate createRestTemplate() {
        // Create a Rest template
        RestTemplate restTemplate = new RestTemplate();
        // Create a list for the message converters
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        // Add the Jackson Message converter
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        // Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    /**
     * Creates URL based on the URI passed in.
     * @param zipCode
     * @throws URLNotFoundException
     */
    public URI createUrl(String zipCode) throws URLNotFoundException {
        StringBuilder sb = new StringBuilder();

        sb.append(DOMAIN_NAME);
        sb.append(API_PATH);
        sb.append(zipCode);
        sb.append(JSON_XML);

        slf4jLogger.info("URL is '{}'." + sb.toString());

        URI url;
        try {
            url = new URI(sb.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new URLNotFoundException("URL not found");
        }

        return url;
    }
}