package com.todo.company.hai704.restapi.service.h2.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ROOMS")
public class H2Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "roomNumber", nullable = false, unique = true)
    private Integer roomNumber;
    @Column(name = "numberBed", nullable = false)
    private Integer numberBed;
    @Column(name = "basePrice", nullable = false)
    private Double basePrice;
    @Column(name = "imgName", nullable = false, length = 255)
    private String imgName;


//    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
//    private  H2Image image;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "imgId", referencedColumnName = "Id")
//    private H2Image image;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "imgId", referencedColumnName = "Id")
//    private H2Image image;

    @Transient
    private boolean available;


    // Entity Mapping
//    @ManyToOne
//    @JoinColumn(name = "hotelRoom_id")
    @Transient
    private H2Hotel runningNode;

//    @ManyToOne
//    @JoinColumn(name = "booking_id", referencedColumnName = "Id")
//    private H2Booking booking;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<H2Booking> bookings;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<H2Offer> offers;  // Refactoring here from soap

    public H2Room() {
    }

    public H2Room(Integer roomNumber, Integer numberBed, Double basePrice, String imgName, H2Image image, boolean available) {
        this.roomNumber = roomNumber;
        this.numberBed = numberBed;
        this.basePrice = basePrice;
        this.imgName = imgName;
//        this.image = image;
        this.available = available;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(Integer numberBed) {
        this.numberBed = numberBed;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

//    public H2Image getImage() {
//        return image;
//    }
//
//    public void setImage(H2Image image) {
//        this.image = image;
//    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<H2Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<H2Offer> offers) {
        this.offers = offers;
    }

    public H2Hotel getRunningNode() {
        return runningNode;
    }

    public void setRunningNode(H2Hotel runningNode) {
        this.runningNode = runningNode;
    }

    public List<H2Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<H2Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        H2Room h2Room = (H2Room) o;
        return isAvailable() == h2Room.isAvailable() && Objects.equals(getId(), h2Room.getId()) && Objects.equals(getRoomNumber(), h2Room.getRoomNumber()) && Objects.equals(getNumberBed(), h2Room.getNumberBed()) && Objects.equals(getBasePrice(), h2Room.getBasePrice()) && Objects.equals(getImgName(), h2Room.getImgName()) && Objects.equals(getBookings(), h2Room.getBookings()) && Objects.equals(getOffers(), h2Room.getOffers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoomNumber(), getNumberBed(), getBasePrice(), getImgName(), isAvailable(), getBookings(), getOffers());
    }

    @Override
    public String toString() {
        return "H2Room{" +
                "Id=" + Id +
                ", roomNumber=" + roomNumber +
                ", numberBed=" + numberBed +
                ", basePrice=" + basePrice +
                ", imgName='" + imgName + '\'' +
                ", available=" + available +
                ", bookings=" + bookings +
                ", offers=" + offers +
                '}';
    }
}
