package com.todo.company.hai704.restapi.Client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AGENCY")
public class Agency {

    @Transient
    private static Agency instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int Id;

    @Column(name = "agencyName", nullable = false,  length = 255)
    private String agencyName;
    @Column(name = "city", nullable = false,  length = 255)
    private String city;
    @Column(name = "AGENCY_DB_NAME", nullable = false,  length = 255)
    @JsonIgnore
    private String AGENCY_DB_NAME;

    @Column(name = "discoveryURL", nullable = false,  length = 255)
    private String discoveryURL;

//    @Transient
    @Column(name = "port", nullable = false)
    private int port;

    public static synchronized Agency getInstance() {
        if (instance == null) {
            instance = new Agency();
        }
        return instance;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAGENCY_DB_NAME() {
        this.AGENCY_DB_NAME = this.agencyName.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim()+
                this.city.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim();
    }

    public static void setInstance(Agency instance) {
        Agency.instance = instance;
    }

    public String getAGENCY_DB_NAME() {
        return AGENCY_DB_NAME;
    }

    public void setAGENCY_DB_NAME(String AGENCY_DB_NAME) {
        this.AGENCY_DB_NAME = AGENCY_DB_NAME;
    }

    public String getDiscoveryURL() {
        return discoveryURL;
    }

    public void setDiscoveryURL(String discoveryURL) {
        this.discoveryURL = discoveryURL;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return getId() == agency.getId() && Objects.equals(getAgencyName(), agency.getAgencyName()) && Objects.equals(getCity(), agency.getCity()) && Objects.equals(getAGENCY_DB_NAME(), agency.getAGENCY_DB_NAME()) && Objects.equals(getDiscoveryURL(), agency.getDiscoveryURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAgencyName(), getCity(), getAGENCY_DB_NAME(), getDiscoveryURL());
    }

    @Override
    public String toString() {
        return "Agency{" +
                "Id=" + Id +
                ", agencyName='" + agencyName + '\'' +
                ", city='" + city + '\'' +
                ", AGENCY_DB_NAME='" + AGENCY_DB_NAME + '\'' +
                ", discoveryURL='" + discoveryURL + '\'' +
                '}';
    }
}
