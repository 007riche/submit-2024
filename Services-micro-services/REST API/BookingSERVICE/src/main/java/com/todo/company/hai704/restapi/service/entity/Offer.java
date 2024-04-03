package com.todo.company.hai704.restapi.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "OFFERS")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "offerId", nullable = false,  length = 255, unique = true)
    private String offerId;
    @Column(name = "availabilityBegin", nullable = false)
    private Date availabilityBegin;
    @Column(name = "checkoutDate", nullable = false)
    private Date checkoutDate;
    @Column(name = "numberPerson", nullable = false)
    private Integer numberPerson;

    @Column(name = "numberBed", nullable = false)
    private Integer numberBed;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "roomUrl", nullable = false)
    private String roomUrl;
//    @ManyToOne
//    @JoinColumn(name = "offers")
//    private Room room;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public Offer() {
    }

    public Offer(String offerId, Date availabilityBegin, Date checkoutDate, Integer numberPerson) {
        this.offerId = offerId;
        this.availabilityBegin = availabilityBegin;
        this.checkoutDate = checkoutDate;
        this.numberPerson = numberPerson;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Integer getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(Integer numberBed) {
        this.numberBed = numberBed;
    }

    public Date getAvailabilityBegin() {
        return availabilityBegin;
    }

    public void setAvailabilityBegin(Date availabilityBegin) {
        this.availabilityBegin = availabilityBegin;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(Integer numberPerson) {
        this.numberPerson = numberPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(getId(), offer.getId()) && Objects.equals(getOfferId(), offer.getOfferId()) && Objects.equals(getAvailabilityBegin(), offer.getAvailabilityBegin()) && Objects.equals(getCheckoutDate(), offer.getCheckoutDate()) && Objects.equals(getNumberPerson(), offer.getNumberPerson()) && Objects.equals(getPrice(), offer.getPrice()) && Objects.equals(getRoom(), offer.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOfferId(), getAvailabilityBegin(), getCheckoutDate(), getNumberPerson(), getPrice(), getRoom());
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", offerId='" + offerId + '\'' +
                ", availabilityBegin=" + availabilityBegin +
                ", checkoutDate=" + checkoutDate +
                ", numberPerson=" + numberPerson +
                ", price=" + price +
                ", roomUrl='" + roomUrl + '\'' +
                ", room=" + room +
                '}';
    }
}
