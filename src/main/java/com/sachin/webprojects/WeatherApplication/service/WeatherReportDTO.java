package com.sachin.webprojects.WeatherApplication.service;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherReportDTO {

    @Autowired
    private CurrentObservation current_observation;
    @Autowired
    private Response response;

    public WeatherReportDTO() {
    }

    public CurrentObservation getCurrent_observation() {
        return current_observation;
    }

    public void setCurrent_observation(CurrentObservation current_observation) {
        this.current_observation = current_observation;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Response {

        private Error error;

        public Response() {
        }

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }

        public class Error {

            private String type;
            private String description;

            public Error() {
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CurrentObservation {

        private String temp_f;

        @Autowired
        private DisplayLocation display_location;

        public CurrentObservation() {
        }

        public String getTemp_f() {
            return temp_f;
        }

        public void setTemp_f(String temp_f) {
            this.temp_f = temp_f;
        }

        public DisplayLocation getDisplay_location() {
            return display_location;
        }

        public void setDisplay_location(DisplayLocation display_location) {
            this.display_location = display_location;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class DisplayLocation {

            private String city;
            private String state_name;
            private String zip;

            public DisplayLocation() {
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState_name() {
                return state_name;
            }

            public void setState_name(String state_name) {
                this.state_name = state_name;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }
        }
    }
}
