package com.sachin.webprojects.WeatherApplication.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;

import com.sachin.webprojects.WeatherApplication.model.WeatherModel;

public class WeatherValidatorTest {

    private WeatherValidator weatherValidator;
    private WeatherModel weatherModel;

    @Before
    public void setup() {

        this.weatherValidator = new WeatherValidator();
        this.weatherModel = new WeatherModel();
    }

    @Test
    public void testValidate_ZipcodeEmpty() {

        this.weatherModel.setZipCode("");
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(weatherModel, "myBinding");

        weatherValidator.validate(weatherModel, errors);
        String code = errors.getFieldError("zipCode").getCode();
        Assert.assertEquals(true, "zipCode.required".equalsIgnoreCase(code));
        Assert.assertEquals(false, "zipCode.num.only".equalsIgnoreCase(code));

        Assert.assertEquals("zipCode.required", code);
        Assert.assertNotEquals("zipCode.num.only", code);
    }

    @Test
    public void testValidate_ZipCodeLength_Big() {

        this.weatherModel.setZipCode("123456789");
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(weatherModel, "myBinding");
        weatherValidator.validate(weatherModel, errors);

        String code = errors.getFieldError("zipCode").getCode();
        Assert.assertNotEquals("zipCode.required", code);
        Assert.assertEquals("zipCode.length", code);
        Assert.assertNotEquals("zipCode.num.only", code);
    }

    @Test
    public void testValidate_ZipCodeLength_Small() {

        this.weatherModel.setZipCode("123");
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(weatherModel, "myBinding");
        weatherValidator.validate(weatherModel, errors);

        String code = errors.getFieldError("zipCode").getCode();
        Assert.assertNotEquals("zipCode.required", code);
        Assert.assertEquals("zipCode.length", code);
        Assert.assertNotEquals("zipCode.num.only", code);

    }

    @Test
    public void testValidate_ZipCodeLength_NonNumeric() {

        this.weatherModel.setZipCode("asd12");
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(weatherModel, "myBinding");
        weatherValidator.validate(weatherModel, errors);

        String code = errors.getFieldError("zipCode").getCode();
        Assert.assertNotEquals("zipCode.required", code);
        Assert.assertNotEquals("zipCode.length", code);
        Assert.assertEquals("zipCode.num.only", code);
    }
}
