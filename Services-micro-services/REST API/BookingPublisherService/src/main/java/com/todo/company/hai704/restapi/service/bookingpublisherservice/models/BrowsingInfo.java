package com.todo.company.hai704.restapi.service.bookingpublisherservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BrowsingInfo {
    private String city;
    private double minPrice;
    private double maxPrice;
    private int numberPerson;
    private Date from;
    private Date to;
    private int stars;

    public BrowsingInfo() {
    }

    public BrowsingInfo(String city,
                        double minPrice, double maxPrice,
                        int numberPerson, Date from, Date to,
                        int stars) {
        this.city = city;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.numberPerson = numberPerson;
        this.from = from;
        this.to = to;
        this.stars = stars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "BrowsingInfo {" +
                "city='" + city + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", numberPerson=" + numberPerson +
                ", from=" + from +
                ", to=" + to +
                ", stars=" + stars +
                '}';
    }
}
