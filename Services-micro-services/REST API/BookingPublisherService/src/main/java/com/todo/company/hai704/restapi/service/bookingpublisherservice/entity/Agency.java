package com.todo.company.hai704.restapi.service.bookingpublisherservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AGENCY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "agencyName", nullable = false,  length = 255)
    private String agencyName;
    @Column(name = "city", nullable = false,  length = 255)
    private String city;

    @Column(name = "discoveryURL", nullable = false,  length = 255)
    private String discoveryURL;


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

    public String getDiscoveryURL() {
        return discoveryURL;
    }

    public void setDiscoveryURL(String discoveryURL) {
        this.discoveryURL = discoveryURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return getId() == agency.getId() && Objects.equals(getAgencyName(), agency.getAgencyName()) && Objects.equals(getCity(), agency.getCity()) && Objects.equals(getDiscoveryURL(), agency.getDiscoveryURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAgencyName(), getCity(), getDiscoveryURL());
    }

    @Override
    public String toString() {
        return "Agency{" +
                "Id=" + Id +
                ", agencyName='" + agencyName + '\'' +
                ", city='" + city + '\'' +
                ", discoveryURL='" + discoveryURL + '\'' +
                '}';
    }
}
