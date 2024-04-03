package com.example.tp1.models;

import android.text.Editable;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String professionalDomain;
    private String phoneNumber;

    public Person() {
    }

    public Person(String firstName, String lastName, String professionalDomain, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalDomain = professionalDomain;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfessionalDomain() {
        return professionalDomain;
    }

    public void setProfessionalDomain(String professionalDomain) {
        this.professionalDomain = professionalDomain;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
