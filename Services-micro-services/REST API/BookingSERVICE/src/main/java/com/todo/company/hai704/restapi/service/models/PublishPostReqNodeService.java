package com.todo.company.hai704.restapi.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishPostReqNodeService  {

    private String hotelName;
    private Double hotelStars;
    private String hotelCountry;
    private String hotelCity;

    private Integer hotelStreetNumber;

    private String hotelStreet;

    private String hotelGPS;

    private String hotelImageURL;

    private String hotelBookingServiceURL;

    private String hotelBrowsingServiceURL;

    private String hotelPartnersServiceURL;



    public PublishPostReqNodeService() {

    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Double getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(Double hotelStars) {
        this.hotelStars = hotelStars;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public Integer getHotelStreetNumber() {
        return hotelStreetNumber;
    }

    public void setHotelStreetNumber(Integer hotelStreetNumber) {
        this.hotelStreetNumber = hotelStreetNumber;
    }

    public String getHotelStreet() {
        return hotelStreet;
    }

    public void setHotelStreet(String hotelStreet) {
        this.hotelStreet = hotelStreet;
    }

    public String getHotelGPS() {
        return hotelGPS;
    }

    public void setHotelGPS(String hotelGPS) {
        this.hotelGPS = hotelGPS;
    }

    public String getHotelImageURL() {
        return hotelImageURL;
    }

    public void setHotelImageURL(String hotelImageURL) {
        this.hotelImageURL = hotelImageURL;
    }

    public String getHotelBookingServiceURL() {
        return hotelBookingServiceURL;
    }

    public void setHotelBookingServiceURL(String hotelBookingServiceURL) {
        this.hotelBookingServiceURL = hotelBookingServiceURL;
    }

    public String getHotelBrowsingServiceURL() {
        return hotelBrowsingServiceURL;
    }

    public void setHotelBrowsingServiceURL(String hotelBrowsingServiceURL) {
        this.hotelBrowsingServiceURL = hotelBrowsingServiceURL;
    }

    public String getHotelPartnersServiceURL() {
        return hotelPartnersServiceURL;
    }

    public void setHotelPartnersServiceURL(String hotelPartnersServiceURL) {
        this.hotelPartnersServiceURL = hotelPartnersServiceURL;
    }

    @Override
    public String toString() {
        return "PublishPostReqNodeService{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelStars=" + hotelStars +
                ", hotelCountry='" + hotelCountry + '\'' +
                ", hotelCity='" + hotelCity + '\'' +
                ", hotelStreetNumber=" + hotelStreetNumber +
                ", hotelStreet='" + hotelStreet + '\'' +
                ", hotelGPS='" + hotelGPS + '\'' +
                ", hotelImageURL='" + hotelImageURL + '\'' +
                ", hotelBookingServiceURL='" + hotelBookingServiceURL + '\'' +
                ", hotelBrowsingServiceURL='" + hotelBrowsingServiceURL + '\'' +
                ", hotelPartnersServiceURL='" + hotelPartnersServiceURL + '\'' +
                '}';
    }
}
