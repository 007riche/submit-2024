package com.todo.company.hai704.restapi.service.controllers;


import com.todo.company.hai704.restapi.service.BookingServiceApplication;
import com.todo.company.hai704.restapi.service.entity.*;
import com.todo.company.hai704.restapi.service.models.BrowseRequest;
import com.todo.company.hai704.restapi.service.models.UserRequestBody;
import com.todo.company.hai704.restapi.service.repository.IBookingRepository;
import com.todo.company.hai704.restapi.service.repository.IPartnershipRepository;
import com.todo.company.hai704.restapi.service.repository.IRoomRepository;
import com.todo.company.hai704.restapi.service.services.PersistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.todo.company.hai704.restapi.service.utilpack.CustomUtility.generateUUID;

@RestController
@RequestMapping("/api/browsing")
@DependsOn("dataSourcesInitialization")
public class BrowsingController {

    @Autowired
    private ResourceLoader resourceLoader;

    private Logger logger = LoggerFactory.getLogger(BookingServiceApplication.class);

    @Autowired
    private PersistanceService persistanceService;


//    @GetMapping("/rooms")  // /IdAgency/{IdAgency}/{}
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/rooms")
    public List<Offer> browseAvailableRooms(@RequestBody BrowseRequest request) {
        logger.info("Request body: "+request);
        Instant timestamp = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Paris"); // Replace with the desired time zone ID
        ZonedDateTime zonedDateTime = timestamp.atZone(zoneId);

        String requestId = request.getIdAgency()+":"+zonedDateTime.toString();

        List<Offer> offers = new ArrayList<Offer>();

        Partnership partner = persistanceService.findPartnershipByIdAgency(request.getIdAgency());
        if (partner.getIdAgency()
                .contentEquals(request.getIdAgency())) {

            List<Booking> currentBooked =persistanceService.findAllBooking();
//            System.err.println("Current booking size: "+currentBooked.size());
            logger.warn("Current booking size: "+currentBooked.size());
            List<Room> allRooms = persistanceService.findAllRooms();

            List<Room> listOfNonBookable = new ArrayList<>();

            for (int i = 0; i < currentBooked.size(); i++) {
                Booking booking = currentBooked.get(i);

                if (booking.getCheckoutDate().getTime() >= request.getArrivalDate().getTime()) {
                    Room bookingRooms = booking.getRoom();
//                    for (Room r : bookingRooms) {
                        if (!listOfNonBookable.contains(bookingRooms)) {
                            listOfNonBookable.add(bookingRooms);
                        }
//                    }
                }
            }

            for (Room room: allRooms){
                if (!listOfNonBookable.contains(room)) {
                    Offer offer = new Offer();
                    String offerId = requestId.trim() + generateUUID().trim();

                    // Availability date
                    offer.setAvailabilityBegin(request.getArrivalDate());

                    // Checkout Date
                    offer.setCheckoutDate(request.getDepartureDate());

                    offer.setRoom(room);

                    offer.setOfferId(offerId);

                    offer.setNumberPerson(request.getNumberPerson());

                    offer.setPrice(room.getBasePrice()
                            - (room.getBasePrice()*partner.getDiscountRate()/100));

                   String offerUrl = String.valueOf(getGenImageURL(room.getImgName()));

                   offer.setRoomUrl(offerUrl);

                   offer.setNumberBed(room.getNumberBed());

                   offers.add(offer);

                }
            }

            for (Offer offer : offers) {
                persistanceService.saveOffer(offer);
            }

            return offers;
        }

        return null;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/compare/room")
    public List<Offer> browseComparison(@RequestBody BrowseRequest request) {

        Room comparisonRoom = persistanceService.findRoomById(2);

        List<Offer> offers = new ArrayList<Offer>();

        Instant timestamp = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Paris"); // Replace with the desired time zone ID
        ZonedDateTime zonedDateTime = timestamp.atZone(zoneId);

        String requestId = request.getIdAgency()+":"+zonedDateTime.toString();
        Partnership partner = persistanceService.findPartnershipByIdAgency(request.getIdAgency());

        if (partner.getIdAgency()
                .contentEquals(request.getIdAgency())) {

            Offer offer = new Offer();
            String offerId = partner.getAgencyName();
            // Availability date
            offer.setAvailabilityBegin(request.getArrivalDate());
            // Checkout Date
            offer.setCheckoutDate(request.getDepartureDate());
            offer.setRoom(comparisonRoom);
            offer.setOfferId(offerId);
            offer.setNumberPerson(comparisonRoom.getNumberBed());
            offer.setPrice(comparisonRoom.getBasePrice()
                    - (comparisonRoom.getBasePrice() * partner.getDiscountRate() / 100));
            String offerUrl = String.valueOf(getGenImageURL(comparisonRoom.getImgName()));
            offer.setRoomUrl(offerUrl);
            offer.setNumberBed(comparisonRoom.getNumberBed());
            offers.add(offer);




            // Base offer without discount
            Offer baseOffer = new Offer();
            offerId = Hotel.getInstance().getName();
            // Availability date
            baseOffer.setAvailabilityBegin(request.getArrivalDate());
            // Checkout Date
            baseOffer.setCheckoutDate(request.getDepartureDate());
            baseOffer.setRoom(comparisonRoom);
            baseOffer.setOfferId(offerId);
            baseOffer.setNumberPerson(comparisonRoom.getNumberBed());
            // base price
            baseOffer.setPrice(comparisonRoom.getBasePrice());
            offerUrl = String.valueOf(getGenImageURL(comparisonRoom.getImgName()));
            baseOffer.setRoomUrl(offerUrl);
            baseOffer.setNumberBed(comparisonRoom.getNumberBed());
            offers.add(baseOffer);

            return offers;
        }
        return null;
    }

    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable int id) {
        return persistanceService.findFirstByRoomNumber(id);
    }

    @GetMapping("/rooms/image/{imgName}")
    public URL getImageURL(@PathVariable String imgName) {
        imgName= imgName.trim();
//        ClassLoader classLoader = getClass().getClassLoader();
        String pathTest = "requestedIMG/";
        URL retURL = null;

//        try {
//           boolean found = persistanceService.saveImageToFile(imgName, pathTest, imgName+".jpg");
//           if (found) {
//               String imgPath = pathTest+imgName+".jpg";
//               File file = resourceLoader.getResource("classpath:" + imgPath).getFile();
//               retURL = file.toURI().toURL();
//           }
//        } catch (IOException e) {
////            throw new RuntimeException(e);
//        }

//        System.out.println("New file's url: "+retURL);
        try {
            retURL =persistanceService.saveImageToFile(imgName, pathTest, imgName+".jpg");
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
        logger.info("In gen URL: New file's url: "+retURL);
        return  retURL;
    }

    public URL getGenImageURL(String imgName) {
        imgName= imgName.trim();
//        ClassLoader classLoader = getClass().getClassLoader();
        String pathTest = "requestedIMG/";
        URL retURL = null;
//        System.err.println("Received image name: "+imgName);
        logger.error("Received image name: "+imgName);
//        try {
//            boolean found = persistanceService.saveImageToFile(imgName, pathTest, imgName+".jpg");
//            if (found) {
//                String imgPath = pathTest+imgName+".jpg";
////                System.out.println("Trying to retrieve img: "+imgPath);
//                logger.info("Trying to retrieve img: "+imgPath);
//                File file = resourceLoader.getResource("classpath:" + imgPath).getFile();
//                retURL = file.toURI().toURL();
//            } else {
////                System.out.println("Image not found");
//                logger.error("Image not found");
//            }
//        } catch (IOException e) {
////            System.out.println("Erro reading image");
//            logger.info("Error reading image");
////            throw new RuntimeException(e);
//        }
//
////        System.out.println("In gen URL: New file's url: "+retURL);
//        logger.info("In gen URL: New file's url: "+retURL);

        try {
            retURL =persistanceService.saveImageToFile(imgName, pathTest, imgName+".jpg");
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
        logger.info("In gen URL: New file's url: "+retURL);

        return  retURL;
    }
}
