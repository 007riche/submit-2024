package com.todo.company.hai704.restapi.service.bookingpublisherservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "SERVICES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeService  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false,  length = 255)
    private String hotelName;
    @Column(nullable = false,  length = 255)
    private Double hotelStars;
    @Column(nullable = false,  length = 255)
    private String hotelCountry;
    @Column(nullable = false,  length = 255)
    private String hotelCity;
    @Column(nullable = false,  length = 255)
    private Integer hotelStreetNumber;
    @Column(nullable = false,  length = 255)
    private String hotelStreet;
    @Column(nullable = false,  length = 255)
    private String hotelGPS;
    @Column(nullable = false,  length = 255)
    private String hotelImageURL;
    @Column(nullable = false,  length = 255)
    private String hotelBookingServiceURL;
    @Column(nullable = false,  length = 255)
    private String hotelBrowsingServiceURL;
    @Column(nullable = false,  length = 255)
    private String hotelPartnersServiceURL;



    public NodeService() {

    }

    public static NodeService createNodeService() {
        return new NodeService();
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
        return "NodeService{" +
                "Id=" + Id +
                ", hotelName='" + hotelName + '\'' +
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeService that = (NodeService) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getHotelName(), that.getHotelName()) && Objects.equals(getHotelStars(), that.getHotelStars()) && Objects.equals(getHotelCountry(), that.getHotelCountry()) && Objects.equals(getHotelCity(), that.getHotelCity()) && Objects.equals(getHotelStreetNumber(), that.getHotelStreetNumber()) && Objects.equals(getHotelStreet(), that.getHotelStreet()) && Objects.equals(getHotelGPS(), that.getHotelGPS()) && Objects.equals(getHotelImageURL(), that.getHotelImageURL()) && Objects.equals(getHotelBookingServiceURL(), that.getHotelBookingServiceURL()) && Objects.equals(getHotelBrowsingServiceURL(), that.getHotelBrowsingServiceURL()) && Objects.equals(getHotelPartnersServiceURL(), that.getHotelPartnersServiceURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHotelName(), getHotelStars(), getHotelCountry(), getHotelCity(), getHotelStreetNumber(), getHotelStreet(), getHotelGPS(), getHotelImageURL(), getHotelBookingServiceURL(), getHotelBrowsingServiceURL(), getHotelPartnersServiceURL());
    }
}
