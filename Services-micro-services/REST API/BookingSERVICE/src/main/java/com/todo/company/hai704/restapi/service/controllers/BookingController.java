package com.todo.company.hai704.restapi.service.controllers;

import com.todo.company.hai704.restapi.service.BookingServiceApplication;
import com.todo.company.hai704.restapi.service.entity.Booking;
import com.todo.company.hai704.restapi.service.entity.Hotel;
import com.todo.company.hai704.restapi.service.entity.Offer;
import com.todo.company.hai704.restapi.service.entity.Partnership;
import com.todo.company.hai704.restapi.service.models.BrowseRequest;
import com.todo.company.hai704.restapi.service.models.Response;
import com.todo.company.hai704.restapi.service.services.PersistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.todo.company.hai704.restapi.service.utilpack.CustomUtility.generateUUID;

@RestController
@RequestMapping("/api/bookings")
@DependsOn("dataSourcesInitialization")
public class BookingController {

    @Autowired
    private PersistanceService persistanceService;

    private Logger logger = LoggerFactory.getLogger(BookingServiceApplication.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book")
    public String  makeBooking(@RequestBody BrowseRequest request) {
        String idAgency=request.getIdAgency();
        String loginId=request.getLoginId();
        String password= request.getPassword();
        String FirstName= request.getFirstName();
        String LastName=request.getLastName();

        List<String> chosenRooms = request.getChosenRooms();

        Partnership partner = persistanceService.findPartnershipByIdAgency(idAgency);
        if (partner.getIdAgency()
                .contentEquals(idAgency)
           && partner.getPassword()
                    .contentEquals(password)
        && partner.getLoginId()
                .contentEquals(loginId))
        {

            List<Offer> offers = persistanceService.findAllOffer();
            List<Offer> chosenOffers = new ArrayList<>();


            for (String offerid: chosenRooms) {
                Offer o = persistanceService.findFirstByOfferId(offerid);
                chosenOffers.add(o);
            }

            String  bookingRef = generateUUID().trim();
            Booking saved = new Booking();


            for (Offer offer : chosenOffers) {
                Booking booking = new Booking();
                booking.setBookingReference(bookingRef);
                booking.setClientBookingFirstName(FirstName);
                booking.setClientBookingLastName(LastName);
                booking.setArrivalDate(offer.getAvailabilityBegin());
                booking.setCheckoutDate(offer.getCheckoutDate());
                booking.setNumberPersons(offer.getNumberPerson());
                booking.setNumberBed(offer.getNumberPerson());
                booking.setRoom(offer.getRoom());

                saved = persistanceService.saveBooking(booking);
            }

            offers = persistanceService.findAllOffer();
            for (int i = 0; i < offers.size(); i++) {
                Offer offer = offers.get(i);
                if (offer.getOfferId().contains(idAgency)) {
                    persistanceService.deleteBooking(offer.getId());
                }
            }

            return saved.getBookingReference();
        }

        return "";
    }
}
