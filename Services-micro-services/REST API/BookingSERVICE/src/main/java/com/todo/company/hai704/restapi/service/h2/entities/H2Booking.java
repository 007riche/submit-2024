package com.todo.company.hai704.restapi.service.h2.entities;


import com.todo.company.hai704.restapi.service.entity.Hotel;
import com.todo.company.hai704.restapi.service.entity.Room;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "BOOKINGS")
public class H2Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookingReference", nullable = false, length = 255)
    private String bookingReference;
    @Column(name = "clientBookingLastName", nullable = false, length = 255)
    private String clientBookingLastName;
    @Column(name = "clientBookingFirstName", nullable = false, length = 255)
    private String clientBookingFirstName;
    @Column(name = "arrivalDate", nullable = false, length = 255)
    private Date arrivalDate;
    @Column(name = "checkoutDate", nullable = false, length = 255)
    private Date checkoutDate;
    @Column(name = "numberPersons", nullable = false, length = 255)
    private int numberPersons;

    @Column(name = "numberBed", nullable = false, length = 255)
    private int numberBed;

//    @ElementCollection
//    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
//    private List<Room> rooms;  // Refactoring here from soap

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomId", referencedColumnName = "Id")
    private H2Room room;

    public H2Booking() {
    }

    public H2Booking(String bookingReference, String clientBookingLastName, String clientBookingFirstName, Date arrivalDate, Date checkoutDate, int numberPersons) {
        this.bookingReference = bookingReference;
        this.clientBookingLastName = clientBookingLastName;
        this.clientBookingFirstName = clientBookingFirstName;
        this.arrivalDate = arrivalDate;
        this.checkoutDate = checkoutDate;
        this.numberPersons = numberPersons;
    }

    // Entity Mapping
//    @ManyToOne
//    @JoinColumn(name = "hotelRoom_id")
    @Transient
    private Hotel runningNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getClientBookingLastName() {
        return clientBookingLastName;
    }

    public void setClientBookingLastName(String clientBookingLastName) {
        this.clientBookingLastName = clientBookingLastName;
    }

    public String getClientBookingFirstName() {
        return clientBookingFirstName;
    }

    public void setClientBookingFirstName(String clientBookingFirstName) {
        this.clientBookingFirstName = clientBookingFirstName;
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


    public Hotel getRunningNode() {
        return runningNode;
    }

    public void setRunningNode(Hotel runningNode) {
        this.runningNode = runningNode;
    }

    public int getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(int numberBed) {
        this.numberBed = numberBed;
    }

    public H2Room getRoom() {
        return room;
    }

    public void setRoom(H2Room room) {
        this.room = room;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        H2Booking booking = (H2Booking) o;
        return getNumberPersons() == booking.getNumberPersons() && getNumberBed() == booking.getNumberBed() && Objects.equals(getId(), booking.getId()) && Objects.equals(getBookingReference(), booking.getBookingReference()) && Objects.equals(getClientBookingLastName(), booking.getClientBookingLastName()) && Objects.equals(getClientBookingFirstName(), booking.getClientBookingFirstName()) && Objects.equals(getArrivalDate(), booking.getArrivalDate()) && Objects.equals(getCheckoutDate(), booking.getCheckoutDate()) && Objects.equals(getRoom(), booking.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookingReference(), getClientBookingLastName(), getClientBookingFirstName(), getArrivalDate(), getCheckoutDate(), getNumberPersons(), getNumberBed(), getRoom());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingReference='" + bookingReference + '\'' +
                ", clientBookingLastName='" + clientBookingLastName + '\'' +
                ", clientBookingFirstName='" + clientBookingFirstName + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", checkoutDate=" + checkoutDate +
                ", numberPersons=" + numberPersons +
                ", numberBed=" + numberBed +
                ", room=" + room +
                '}';
    }
}


