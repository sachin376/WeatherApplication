package com.sachin.webprojects.WeatherApplication.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sachin.webprojects.WeatherApplication.model.WeatherModel;

@Component
public class WeatherValidator implements Validator {

    String DIGI_PATTERN = "[0-9]+";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> clazz) {
        // just validate the WeatherModel instances
        return WeatherModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode",
                "zipCode.required");

        WeatherModel weatherModel = (WeatherModel) target;

        String zipCode = weatherModel.getZipCode();
        if (zipCode != null && zipCode.length() != 5) {
            errors.rejectValue("zipCode", "zipCode.length");
        }

        // zipcode conatains numeric values only
        if (zipCode != null && !("".equals(zipCode.trim()))) {
            pattern = Pattern.compile(DIGI_PATTERN);
            matcher = pattern.matcher(zipCode);
            if (!matcher.matches()) {
                errors.rejectValue("zipCode", "zipCode.num.only");
            }
        }
    }
}