package com.example.tp1.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


public class Journeys implements Serializable {
    private Date journeyDate;

    private String departureCity;
    private String arrivalCity;


    public Journeys(Date journeyDate, String departureCity, String arrivalCity) {
        this.journeyDate = journeyDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
