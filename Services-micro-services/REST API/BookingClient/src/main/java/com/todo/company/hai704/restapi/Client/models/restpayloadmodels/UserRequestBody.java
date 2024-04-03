package com.todo.company.hai704.restapi.Client.models.restpayloadmodels;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestBody {
    private String userName;
    private String password;
    private String agencyName;
    private String idAgency;





    // Getters and setters


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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Override
    public String toString() {
        return "UserRequestBody{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", idAgency='" + idAgency + '\'' +
                '}';
    }
}
