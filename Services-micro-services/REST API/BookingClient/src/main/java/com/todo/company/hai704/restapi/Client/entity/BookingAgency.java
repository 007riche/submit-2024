package com.todo.company.hai704.restapi.Client.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "AGENCYBOOKINGS")
public class BookingAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "bookingReference", nullable = false,  length = 255)
    private String bookingReference;
    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;
    @Column(name = "arrivalDate", nullable = false,  length = 255)
    private Date arrivalDate;
    @Column(name = "checkoutDate", nullable = false,  length = 255)
    private Date checkoutDate;
    @Column(name = "numberPersons", nullable = false,  length = 255)
    private int numberPersons;
    @Column(name = "mainPersonne", nullable = false,  length = 255)
    private String mainPersonne;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId", referencedColumnName = "Id")
    private Client client;


    public BookingAgency() {
    }

    public BookingAgency(String bookingReference, Double totalPrice,
                         Date arrivalDate, Date checkoutDate, int numberPersons, String mainPersonne) {
        this.bookingReference = bookingReference;
        this.totalPrice = totalPrice;
        this.arrivalDate = arrivalDate;
        this.checkoutDate = checkoutDate;
        this.numberPersons = numberPersons;
        this.mainPersonne = mainPersonne;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMainPersonne() {
        return mainPersonne;
    }

    public void setMainPersonne(String mainPersonne) {
        this.mainPersonne = mainPersonne;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumberPersons() {
        return numberPersons;
    }

    public void setNumberPersons(int numberPersons) {
        this.numberPersons = numberPersons;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingAgency that = (BookingAgency) o;
        return getId() == that.getId() && getNumberPersons() == that.getNumberPersons() && Objects.equals(getBookingReference(), that.getBookingReference()) && Objects.equals(getTotalPrice(), that.getTotalPrice()) && Objects.equals(getArrivalDate(), that.getArrivalDate()) && Objects.equals(getCheckoutDate(), that.getCheckoutDate()) && Objects.equals(getMainPersonne(), that.getMainPersonne()) && Objects.equals(getClient(), that.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookingReference(), getTotalPrice(), getArrivalDate(), getCheckoutDate(), getNumberPersons(), getMainPersonne(), getClient());
    }

    @Override
    public String toString() {
        return "BookingAgency{" +
                "Id=" + Id +
                ", bookingReference='" + bookingReference + '\'' +
                ", totalPrice=" + totalPrice +
                ", arrivalDate=" + arrivalDate +
                ", checkoutDate=" + checkoutDate +
                ", numberPersons=" + numberPersons +
                ", mainPersonne='" + mainPersonne + '\'' +
                ", client=" + client +
                '}';
    }
}
