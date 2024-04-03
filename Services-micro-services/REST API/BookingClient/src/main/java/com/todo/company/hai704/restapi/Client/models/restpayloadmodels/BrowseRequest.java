package com.todo.company.hai704.restapi.Client.models.restpayloadmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BrowseRequest {

    private String idAgency;
    private String password;
    private Date arrivalDate;
    private Date departureDate;
    private int numberPerson;

    private String loginId;
    private String FirstName;
    private String LastName;
    private List<String> chosenRooms;

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public List<String> getChosenRooms() {
        return chosenRooms;
    }

    public void setChosenRooms(List<String> chosenRooms) {
        this.chosenRooms = chosenRooms;
    }

    @Override
    public String toString() {
        return "BrowseRequest{" +
                "idAgency='" + idAgency + '\'' +
                ", password='" + password + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", numberPerson=" + numberPerson +
                ", loginId='" + loginId + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", chosenRooms=" + chosenRooms +
                '}';
    }
}
