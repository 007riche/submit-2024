package com.todo.company.hai704.restapi.Client.models.restpayloadmodels;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferResponse {
    private String offerId;
    private Date availabilityBegin;
    private Date checkoutDate;
    private Integer numberPerson;
    private Integer numberBed;
    private Double price;
    private String roomUrl;

    public OfferResponse() {
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
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

    public Integer getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(Integer numberBed) {
        this.numberBed = numberBed;
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
    public String toString() {
        return "OfferResponse{" +
                "offerId='" + offerId + '\'' +
                ", availabilityBegin=" + availabilityBegin +
                ", checkoutDate=" + checkoutDate +
                ", numberPerson=" + numberPerson +
                ", numberBed=" + numberBed +
                ", price=" + price +
                ", roomUrl='" + roomUrl + '\'' +
                '}';
    }
}
