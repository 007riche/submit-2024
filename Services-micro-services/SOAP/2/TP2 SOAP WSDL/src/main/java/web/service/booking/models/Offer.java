package web.service.booking.models;

import java.util.Date;

public class Offer {
    private String offerId;
    private Double Price;
    private int roomNumber;
    private int numberBed;

    private String imgURL;
    private Date availabilityBegin;
    private Date checkoutDate;

    private int numberPerson;

    public Offer() {
    }

    public Offer(String offerId, Double price, int roomNumber,
                 int numberBed, Date availabilityBegin) {
        this.offerId = offerId;
        Price = price;
        this.roomNumber = roomNumber;
        this.numberBed = numberBed;
        this.availabilityBegin = availabilityBegin;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(int numberBed) {
        this.numberBed = numberBed;
    }

    public Date getAvailabilityBegin() {
        return availabilityBegin;
    }

    public void setAvailabilityBegin(Date availabilityBegin) {
        this.availabilityBegin = availabilityBegin;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (roomNumber != offer.roomNumber) return false;
        if (getNumberBed() != offer.getNumberBed()) return false;
        if (!getOfferId().equals(offer.getOfferId())) return false;
        if (!getPrice().equals(offer.getPrice())) return false;
        return getAvailabilityBegin().equals(offer.getAvailabilityBegin());
    }

    @Override
    public int hashCode() {
        int result = getOfferId().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + getNumberBed();
        result = 31 * result + getAvailabilityBegin().hashCode();
        return result;
    }

}
