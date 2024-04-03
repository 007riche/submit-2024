package com.todo.company.hai704.restapi.service.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SERVICES")
public class Hotel implements Serializable {

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
    private static Hotel instance;

    public Hotel() {
    }

    public Hotel(String name, Double stars, String city, String street, int streetNumber) {
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

    public static synchronized Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return getPortNumber() == hotel.getPortNumber() && Objects.equals(getId(), hotel.getId()) && Objects.equals(getName(), hotel.getName()) && Objects.equals(getStars(), hotel.getStars()) && Objects.equals(getCountry(), hotel.getCountry()) && Objects.equals(getCity(), hotel.getCity()) && Objects.equals(getStreet(), hotel.getStreet()) && Objects.equals(getStreetNumber(), hotel.getStreetNumber()) && Objects.equals(getGpsPosition(), hotel.getGpsPosition()) && Objects.equals(getHotelImgUrl(), hotel.getHotelImgUrl()) && Objects.equals(getHOTEL_DOMAIN(), hotel.getHOTEL_DOMAIN()) && Objects.equals(getBOOKING_URL(), hotel.getBOOKING_URL()) && Objects.equals(getBROWSING_URL(), hotel.getBROWSING_URL()) && Objects.equals(getPARTNERS_URL(), hotel.getPARTNERS_URL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStars(), getCountry(), getCity(), getStreet(), getStreetNumber(), getGpsPosition(), getHotelImgUrl(), getHOTEL_DOMAIN(), getBOOKING_URL(), getBROWSING_URL(), getPARTNERS_URL(), getPortNumber());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", gpsPosition='" + gpsPosition + '\'' +
                ", hotelImgUrl='" + hotelImgUrl + '\'' +
                ", HOTEL_DOMAIN='" + HOTEL_DOMAIN + '\'' +
                ", BOOKING_URL='" + BOOKING_URL + '\'' +
                ", BROWSING_URL='" + BROWSING_URL + '\'' +
                ", PARTNERS_URL='" + PARTNERS_URL + '\'' +
                ", PortNumber=" + PortNumber +
                '}';
    }
}
