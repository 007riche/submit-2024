package com.todo.company.hai704.restapi.service.h2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "SERVICES")
public class H2Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "stars", nullable = false, length = 255)
    private Double stars;
    @Column(name = "country", nullable = false, length = 255)
    private String country;
    @Column(name = "city", nullable = false, length = 255)
    private String city;
    @Column(name = "street", nullable = false, length = 255)
    private String street;
    @Column(name = "streetNumber", nullable = false, length = 255)
    private Integer streetNumber;
    @Column(name = "gpsPosition", nullable = false, length = 255)
    private String gpsPosition;
    @Column(name = "hotelImgUrl", nullable = false, length = 255)
    private String hotelImgUrl;
    @Column(name = "HOTEL_DOMAIN", nullable = false, length = 255)
    private String HOTEL_DOMAIN;
    @Column(name = "BOOKING_URL", nullable = false, length = 255)
    private String BOOKING_URL;
    @Column(name = "BROWSING_URL", nullable = false, length = 255)
    private String BROWSING_URL;
    @Column(name = "PARTNERS_URL", nullable = false, length = 255)
    private String PARTNERS_URL;
    @Transient
    private int PortNumber;
    @Transient
    private static H2Hotel instance;

    public H2Hotel() {
    }

    public H2Hotel(String name, Double stars, String city, String street, int streetNumber) {
        this.name = name;
        this.stars = stars;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.HOTEL_DOMAIN = this.name.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim()+
                            this.city.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getGpsPosition() {
        return gpsPosition;
    }

    public String getBOOKING_URL() {
        return BOOKING_URL;
    }

    public void setBOOKING_URL(String BOOKING_URL) {
        this.BOOKING_URL = BOOKING_URL;
    }

    public String getBROWSING_URL() {
        return BROWSING_URL;
    }

    public void setBROWSING_URL(String BROWSING_URL) {
        this.BROWSING_URL = BROWSING_URL;
    }

    public String getPARTNERS_URL() {
        return PARTNERS_URL;
    }

    public void setPARTNERS_URL(String PARTNERS_URL) {
        this.PARTNERS_URL = PARTNERS_URL;
    }

    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition;
    }

    public String getHOTEL_DOMAIN() {
        return HOTEL_DOMAIN;
    }
    public void setHOTEL_DOMAIN() {
        this.HOTEL_DOMAIN = this.name.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim()+
                this.city.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim();
    }

    public String getHotelImgUrl() {
        return hotelImgUrl;
    }

    public void setHotelImgUrl(String hotelImgUrl) {
        this.hotelImgUrl = hotelImgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPortNumber() {
        return PortNumber;
    }

    public void setPortNumber(int portNumber) {
        PortNumber = portNumber;
    }

    public static synchronized H2Hotel getInstance() {
        if (instance == null) {
            instance = new H2Hotel();
        }
        return instance;
    }


}
