package web.service.booking.services;


import jakarta.jws.WebService;
import web.service.booking.data.*;
import web.service.booking.models.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@WebService(endpointInterface = "web.service.booking.services.BookingService")
public class BookingServiceImplementation implements BookingService{

    private Hotel hotel;
    private RoomDAO roomDAO ;
    private PartenershipDAO partenershipDAO;
    private BookingDAO bookingDAO;
    private ClassLoader classLoader = getClass().getClassLoader();


    public BookingServiceImplementation()  {
        this.hotel = Hotel.getInstance();
        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(this.hotel.getHOTEL_DOMAIN(),"soap","password");

        try {
            this.roomDAO = new RoomDAO(jdbcConnectionFactory.getConnectionDB());
            this.partenershipDAO = new PartenershipDAO(jdbcConnectionFactory.getConnectionDB());
            this.bookingDAO = new BookingDAO(jdbcConnectionFactory.getConnectionDB());
            this.hotel.setTemporaryRequestOffer(new ArrayList<HashMap<String, Offer>>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public BookingServiceImplementation(Hotel launchedHotel, String serviceURL ) {
    this.hotel = launchedHotel;
    JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(this.hotel.getHOTEL_DOMAIN(),"soap","password");
    try {
        this.roomDAO = new RoomDAO(jdbcConnectionFactory.getConnectionDB());
        this.partenershipDAO = new PartenershipDAO(jdbcConnectionFactory.getConnectionDB());
        this.bookingDAO = new BookingDAO(jdbcConnectionFactory.getConnectionDB());
        String sqlCommand = "INSERT INTO SERVICES (hotelName, hotelStars, hotelCountry, " +
                "hotelCity, hotelStreetNumber, hotelStreet, hotelGPS, hotelImageURL, hotelBookingServiceURL)\n" +
                "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT * FROM SERVICES WHERE hotelName=? AND hotelCity=?);";
        Connection conn = jdbcConnectionFactory.getConnectionDB("SOAPSERVICEDB","soap","password");
        PreparedStatement stmt = conn.prepareStatement(sqlCommand);
        stmt.setString(1, launchedHotel.getName().trim());
        stmt.setDouble(2, launchedHotel.getStars());
        stmt.setString(3, launchedHotel.getCountry().trim());
        stmt.setString(4, launchedHotel.getCity().trim());
        stmt.setInt(5, launchedHotel.getStreetNumber());
        stmt.setString(6, launchedHotel.getStreet().trim());
        stmt.setString(7, launchedHotel.getGpsPosition().trim());

        ClassLoader classLoader = getClass().getClassLoader();
        int choic = generateRandomInt(1, 8);
        String chosen = "img/hotel/"+choic+".jpg";
        File file = new File(classLoader.getResource(chosen).toURI());
//        File loaded = new File(chosen);
//        URI fileUri = loaded.toURI();
        URI fileUri = file.toURI();
        URL fileURL = fileUri.toURL();
        stmt.setString(8, fileURL.toString());
        stmt.setString(9, serviceURL.trim());
        stmt.setString(10, launchedHotel.getName().trim());
        stmt.setString(11, launchedHotel.getCity().trim());
        stmt.execute();
//            conn.close();
//        System.out.println(jdbcConnectionFactory.getConnectionDB());
    } catch (SQLException e) {
        System.out.println("New exception");
        e.printStackTrace();
    } catch (MalformedURLException e) {
        System.out.println("New exception, to url ");
        e.printStackTrace();
    } catch (URISyntaxException e) {
//        throw new RuntimeException(e);
    }
        this.roomDAO.createTable();
    this.bookingDAO.createTable();
    this.partenershipDAO.createTable();


    if (this.hotel.getHotelRooms() != null)
        this.hotel.getHotelRooms().forEach(room -> {
            int imgNumber = generateRandomInt(1, 20);
            int roomType = room.getNumberBed();
          int insertedKey =  this.roomDAO.insertImg(roomType, imgNumber, room);
            room.setImgId(insertedKey);
            System.err.println("Inserted Image key: "+insertedKey);
        System.out.println("Insertion Room status: "+this.roomDAO.add(room));
    });

    // generate Random booking
    if (this.bookingDAO.getAll().size() == 0 ) {
        List<Room> currentRooms = this.roomDAO.getAll();
        int size = currentRooms.size();
        if (size != 0 ) {
            List<Booking> rand = RandomBookingGenerator.generateRandomBookings(15, size);
            rand.forEach(booking -> {
                System.out.println("from BookImpl: Insertion Booking status: "+this.bookingDAO.add(booking));;
            });
        }
    }


    System.out.println(this.hotel.getName());
}


    @Override
    public Response makeBooking(String idAgency, String loginId, String password,
                                String FirstName, String LastName,
                                List<String> chosenRooms, String token) {

        Response response = new Response();
            if (isPartner(idAgency, password, loginId)) {
                System.out.println("idAgency: "+idAgency+
                        "   password: "+password+
                        "   loginId: "+loginId);
                List<String> ch = new ArrayList<String>();
                ch = chosenRooms;
//                System.err.println(ch.getClass().getName());
//                System.err.println("size chosen: ch:"+ch.size());
//
//                System.out.println("chosen Type: chosen: "+chosenRooms.getClass().getName());
//                System.err.println("Chosen :"+chosenRooms);
                Hotel runningHotel = Hotel.getInstance();
                AtomicInteger confirmedBook = new AtomicInteger(1);
                String unifiedBookingID = generateUUID();
                runningHotel.getTemporaryRequestOffer().forEach(stringOfferHashMap -> {
                    System.out.println("ID: "+stringOfferHashMap);
                });

                List<HashMap<String, Offer>> tempOffer =  runningHotel.getTemporaryRequestOffer();
//                System.err.println("Temp offer size:"+tempOffer.size());
//                System.out.println("In Booking: token: "+token+
//                        "\n tokenType:"+ token.getClass().getName()+
//                        "\n chosenList:"+ chosenRooms);
//                chosenRooms.forEach(s -> {
//                    System.out.println("Chos: "+ s + " type: "+s.getClass().getName());
//                });

                chosenRooms.forEach(s -> {
//                    System.err.println("Map in Bookings, Chosen, S="+s);
//                    System.out.println("Map in Bookings, Token="+token);
//                    System.out.println("tmpe Offer size:"+tempOffer.size());


//                    tempOffer.forEach( offerMap -> {
//                        System.out.println("offer key: "+token);
//
//                        offerMap.values().forEach(offer -> {
//                            System.out.println(" OfferID: "+offer.getOfferId());
//                        });
//                    });


                    tempOffer.forEach( offerMap -> {
                      if (chosenRooms.contains(offerMap.values().stream().findFirst().get().getOfferId())) {

                          Offer currentOffer = offerMap.values().stream().findFirst().get();
                          System.out.println(" OfferID: "+currentOffer.getOfferId());

                          Booking currentBooking =new Booking();

                          currentBooking.setBookingReference(unifiedBookingID);
                          currentBooking.setClientBookingFirstName(FirstName);
                          currentBooking.setClientBookingLastName(LastName);
                          currentBooking.setArrivalDate(currentOffer.getAvailabilityBegin());
                          currentBooking.setCheckoutDate(currentOffer.getCheckoutDate());
                          currentBooking.setNumberPersons(currentOffer.getNumberPerson());
                          currentBooking.setRoomId(currentOffer.getRoomNumber());
                          System.out.println("CurrentBooking: "+currentBooking);
                          System.err.println("From BookImpl:  Insertion status: "
                                  +this.bookingDAO.add(currentBooking)+currentBooking);
                          confirmedBook.getAndIncrement();
                      }
                    });
                });

                if (confirmedBook.get() == chosenRooms.size()) {
                    System.err.println("confirm booked: "+confirmedBook.get()+" chosen: "+chosenRooms.size()+"\nUnifiedID: "+unifiedBookingID);
                    response.setCode(200);
                    response.setExplaination(unifiedBookingID);
                } else {
                    System.err.println("confirm booked: "+confirmedBook.get()+" chosen: "+chosenRooms.size());
                    response.setCode(204);
                    response.setExplaination("La Reservation est partiellement faite");
                }
//                this.hotel.removeTemporaryRequestOffer(token);
                return response;
            }
        response.setCode(404);
        response.setExplaination("Votre offre n'est plus valable.");
//        this.hotel.removeTemporaryRequestOffer(token);
        return response;
    }

    public boolean isPartner(String idAgency, String password, String loginId) {
        Hotel runningHotel = Hotel.getInstance();
        for (int i = 0; i < runningHotel.getHotelPartnership().size(); i++) {
            if (runningHotel.getHotelPartnership().get(i).getIdAgency().contains(idAgency.trim())
                    && runningHotel.getHotelPartnership().get(i).getPassword().contains(password.trim())
                    && runningHotel.getHotelPartnership().get(i).getLoginId().contains(loginId.trim())) return true;
        }
        return false;
    }


    public  void reloadPartners() {
        System.err.println("reloadPartners()  called for restart");
    Hotel runningHotel = Hotel.getInstance();
    runningHotel.setHotelPartnership(this.partenershipDAO.getAll());
    }

    public  void reloadRooms() {
        System.err.println("reloadRooms()  called for restart");
        Hotel runningHotel = Hotel.getInstance();
        runningHotel.setHotelRooms(this.roomDAO.getAll());
        for (int i = 0; i < runningHotel.getHotelRooms().size(); i++) {
            System.out.println("Reloaded room img id: "+ runningHotel.getHotelRooms().get(i).getImgId());
        }
    }

    public  void reloadBookings() {
        System.err.println("reloadBookings()  called for restart");
        Hotel runningHotel = Hotel.getInstance();
        runningHotel.setHotleBooking(this.bookingDAO.getAll());
    }


    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static int generateRandomInt(int min, int max) {
        // Generate a random number between min (inclusive) and max (inclusive)
        int random = (int) (Math.random() * (max - min + 1)) + min;

        // Return the final random integer
        return random;
    }


}



