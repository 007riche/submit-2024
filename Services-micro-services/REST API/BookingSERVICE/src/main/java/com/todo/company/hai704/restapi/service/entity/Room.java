package com.todo.company.hai704.restapi.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ROOMS")
public class Room {

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
//    private  Image image;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "imgId", referencedColumnName = "Id")
//    private Image image;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "imgId", referencedColumnName = "Id")
    private Image image;

    @Transient
    private boolean available;


    // Entity Mapping
//    @ManyToOne
//    @JoinColumn(name = "hotelRoom_id")
    @Transient
    private Hotel runningNode;

//    @ManyToOne
//    @JoinColumn(name = "booking_id", referencedColumnName = "Id")
//    private Booking booking;

    @JsonManagedReference
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @JsonManagedReference
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Offer> offers;  // Refactoring here from soap

    public Room() {
    }

    public Room(Integer roomNumber, Integer numberBed, Double basePrice, String imgName, Image image, boolean available) {
        this.roomNumber = roomNumber;
        this.numberBed = numberBed;
        this.basePrice = basePrice;
        this.imgName = imgName;
        this.image = image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Hotel getRunningNode() {
        return runningNode;
    }

    public void setRunningNode(Hotel runningNode) {
        this.runningNode = runningNode;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return isAvailable() == room.isAvailable() && Objects.equals(getId(), room.getId()) && Objects.equals(getRoomNumber(), room.getRoomNumber()) && Objects.equals(getNumberBed(), room.getNumberBed()) && Objects.equals(getBasePrice(), room.getBasePrice()) && Objects.equals(getImgName(), room.getImgName()) && Objects.equals(getImage(), room.getImage()) && Objects.equals(getBookings(), room.getBookings()) && Objects.equals(getOffers(), room.getOffers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoomNumber(), getNumberBed(), getBasePrice(), getImgName(), getImage(), isAvailable(), getBookings(), getOffers());
    }

    @Override
    public String toString() {
        return "Room{" +
                "Id=" + Id +
                ", roomNumber=" + roomNumber +
                ", numberBed=" + numberBed +
                ", basePrice=" + basePrice +
                ", imgName='" + imgName + '\'' +
//                ", image=" + image +
                ", available=" + available +
                ", bookings=" + bookings +
                ", offers=" + offers +
                '}';
    }
}
