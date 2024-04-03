package com.todo.company.hai704.restapi.Client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "SERVICES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "hotelName", nullable = false,  length = 255)
    private String hotelName;
    @Column(name = "loginId", length = 255)
    private String loginId;
    @Column(name = "idAgency", length = 255)
    private String idAgency;
    @Column(name = "userName", length = 255)
    private String userName;
    @Column(name = "Password", length = 255)
    private String Password;
    @Column(name = "hotelImageURL", nullable = false,  length = 255)
    private String hotelImageURL;
    @Column(name = "hotelBookingServiceURL", nullable = false,  length = 255)
    private String hotelBookingServiceURL;
    @Column(name = "hotelBrowsingServiceURL", nullable = false,  length = 255)
    private String hotelBrowsingServiceURL;
    @Column(name = "hotelPartnersServiceURL", nullable = false,  length = 255)
    private String hotelPartnersServiceURL;

    @Column(name = "hotelAddress", nullable = false,  length = 255)
    private String hotelAddress;
    @Column(name = "hotelStars", nullable = false)
    private Double hotelStars;

    @Transient
    private String hotelCountry;

    @Transient
    private String hotelGPS;


    @Transient
    private String hotelCity;

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public Double getHotelStars() {
        return hotelStars;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public String getHotelGPS() {
        return hotelGPS;
    }

    public String getHotelAddress() {
        return this.hotelAddress;
    }

    public void setHotelAddress() {
        this.hotelAddress = getHotelCity()+", "+getHotelCountry()+". "+getHotelGPS();
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelStars(Double hotelStars) {
        this.hotelStars = hotelStars;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public void setHotelGPS(String hotelGPS) {
        this.hotelGPS = hotelGPS;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeService that = (NodeService) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getHotelName(), that.getHotelName()) && Objects.equals(getLoginId(), that.getLoginId()) && Objects.equals(getIdAgency(), that.getIdAgency()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getHotelImageURL(), that.getHotelImageURL()) && Objects.equals(getHotelBookingServiceURL(), that.getHotelBookingServiceURL()) && Objects.equals(getHotelBrowsingServiceURL(), that.getHotelBrowsingServiceURL()) && Objects.equals(getHotelPartnersServiceURL(), that.getHotelPartnersServiceURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHotelName(), getLoginId(), getIdAgency(), getUserName(), getPassword(), getHotelImageURL(), getHotelBookingServiceURL(), getHotelBrowsingServiceURL(), getHotelPartnersServiceURL());
    }

    @Override
    public String toString() {
        return "NodeService{" +
                "Id=" + Id +
                ", hotelName='" + hotelName + '\'' +
                ", loginId='" + loginId + '\'' +
                ", idAgency='" + idAgency + '\'' +
                ", userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                ", hotelImageURL='" + hotelImageURL + '\'' +
                ", hotelBookingServiceURL='" + hotelBookingServiceURL + '\'' +
                ", hotelBrowsingServiceURL='" + hotelBrowsingServiceURL + '\'' +
                ", hotelPartnersServiceURL='" + hotelPartnersServiceURL + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", hotelStars=" + hotelStars +
                '}';
    }
}
