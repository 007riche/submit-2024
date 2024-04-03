package com.todo.company.hai704.restapi.Client.models.guidatamodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class RetrievedRoom {
    private String offerId;
    private Date from;
    private Date to;
    private int numberBed;
    private String imgName;
    private Double price;
    private int roomNumber;
    private int searchedNumberPerson;
    private String imgUrl;
    private boolean select;

    public RetrievedRoom() {
    }

    public RetrievedRoom(String offerId, Date from, Date to, String imgName, Double price, int roomNumber) {
        this.offerId = offerId;
        this.from = from;
        this.to = to;
        this.imgName = imgName;
        this.price = price;
        this.roomNumber = roomNumber;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public int getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(int numberBed) {
        this.numberBed = numberBed;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getSearchedNumberPerson() {
        return searchedNumberPerson;
    }

    public void setSearchedNumberPerson(int searchedNumberPerson) {
        this.searchedNumberPerson = searchedNumberPerson;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
