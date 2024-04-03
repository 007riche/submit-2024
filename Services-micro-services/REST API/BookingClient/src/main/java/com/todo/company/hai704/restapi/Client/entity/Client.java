package com.todo.company.hai704.restapi.Client.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "lastName", nullable = false,  length = 255)
    private String lastName;
    @Column(name = "firstName", nullable = false,  length = 255)
    private String firstName;
    @Column(name = "cardNumber", nullable = false,  length = 255)
    private String cardNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BookingAgency> booking;

    public Client() {
    }

    public Client(String lastName, String firstName, String cardNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.cardNumber = cardNumber;
        booking=new ArrayList<>();
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<BookingAgency> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingAgency> booking) {
        this.booking = booking;
    }

    public void addBooking(BookingAgency booking) {
        this.booking.add(booking);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) && Objects.equals(getLastName(), client.getLastName()) && Objects.equals(getFirstName(), client.getFirstName()) && Objects.equals(getCardNumber(), client.getCardNumber()) && Objects.equals(getBooking(), client.getBooking());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastName(), getFirstName(), getCardNumber(), getBooking());
    }

    @Override
    public String toString() {
        return "Client {" +
                "Id=" + Id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", booking=" + booking +
                '}';
    }
}
