package com.todo.company.hai704.restapi.service.utilpack;


import com.todo.company.hai704.restapi.service.entity.*;
import com.todo.company.hai704.restapi.service.h2.entities.*;

public class EntityConverter {

    public static H2Booking convertToH2Booking(Booking booking) {
        H2Booking h2Booking = new H2Booking();
        h2Booking.setBookingReference(booking.getBookingReference());
        h2Booking.setClientBookingLastName(booking.getClientBookingLastName());
        h2Booking.setClientBookingFirstName(booking.getClientBookingFirstName());
        h2Booking.setArrivalDate(booking.getArrivalDate());
        h2Booking.setCheckoutDate(booking.getCheckoutDate());
        h2Booking.setNumberPersons(booking.getNumberPersons());
        // Conversion of relationships like rooms needs to be handled based on your logic
        return h2Booking;
    }

    public static H2Hotel convertToH2Hotel(Hotel hotel) {
        H2Hotel h2Hotel = new H2Hotel();
        h2Hotel.setName(hotel.getName());
        h2Hotel.setStars(hotel.getStars());
        h2Hotel.setCountry(hotel.getCountry());
        h2Hotel.setCity(hotel.getCity());
        h2Hotel.setStreet(hotel.getStreet());
        h2Hotel.setStreetNumber(hotel.getStreetNumber());
        h2Hotel.setGpsPosition(hotel.getGpsPosition());
        h2Hotel.setHotelImgUrl(hotel.getHotelImgUrl());
        h2Hotel.setHOTEL_DOMAIN();
        h2Hotel.setBOOKING_URL(hotel.getBOOKING_URL());
        h2Hotel.setBROWSING_URL(hotel.getBROWSING_URL());
        h2Hotel.setPARTNERS_URL(hotel.getPARTNERS_URL());
        // Conversion of relationships and transient fields needs to be handled based on your logic
        return h2Hotel;
    }

    public static H2Image convertToH2Image(Image image) {
        H2Image h2Image = new H2Image();
        h2Image.setImgName(image.getImgName());
//        h2Image.setImg(image.getImg());
        return h2Image;
    }

    public static H2Offer convertToH2Offer(Offer offer) {
        H2Offer h2Offer = new H2Offer();
        h2Offer.setOfferId(offer.getOfferId());
        h2Offer.setAvailabilityBegin(offer.getAvailabilityBegin());
        h2Offer.setCheckoutDate(offer.getCheckoutDate());
        h2Offer.setNumberPerson(offer.getNumberPerson());
        h2Offer.setNumberBed(offer.getNumberBed());
        // Set more fields if necessary
        return h2Offer;
    }

    public static H2Partnership convertToH2Partnership(Partnership partnership) {
        H2Partnership h2Partnership = new H2Partnership();
        h2Partnership.setIdAgency(partnership.getIdAgency());
        h2Partnership.setAgencyName(partnership.getAgencyName());
        h2Partnership.setLoginId(partnership.getLoginId());
        h2Partnership.setPassword(partnership.getPassword());
        h2Partnership.setDiscountRate(partnership.getDiscountRate());
        // Set more fields if necessary
        return h2Partnership;
    }

    public static H2Room convertToH2Room(Room room) {
        H2Room h2Room = new H2Room();
        h2Room.setRoomNumber(room.getRoomNumber());
        h2Room.setNumberBed(room.getNumberBed());
        h2Room.setBasePrice(room.getBasePrice());
        h2Room.setImgName(room.getImgName());
        h2Room.setAvailable(room.isAvailable());
        // Assuming you have a method to convert Image to H2Image
//         h2Room.setImage(convertToH2Image(room.getImage()));
        // Set more fields if necessary
        return h2Room;
    }
}
