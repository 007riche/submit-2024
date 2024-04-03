package web.service.booking.services;


import jakarta.jws.WebService;
import web.service.booking.data.BookingDAO;
import web.service.booking.data.JDBCConnectionFactory;
import web.service.booking.data.PartenershipDAO;
import web.service.booking.data.RoomDAO;
import web.service.booking.models.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


@WebService(endpointInterface = "web.service.booking.services.BrowseAvailabilityService")
public class BrowseAvailabilityServiceImpl implements BrowseAvailabilityService {
    private Hotel hotel;
    private RoomDAO roomDAO ;
    private PartenershipDAO partenershipDAO;
    private BookingDAO bookingDAO;

    private List<HashMap<String, Offer>> temporaryRequestOffer;



    public BrowseAvailabilityServiceImpl(Hotel launchedHotel, String serviceURL) {
        this.hotel = launchedHotel;
        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(this.hotel.getHOTEL_DOMAIN(),"soap","password");
        try {
            this.roomDAO = new RoomDAO(jdbcConnectionFactory.getConnectionDB());
            this.partenershipDAO = new PartenershipDAO(jdbcConnectionFactory.getConnectionDB());
            this.bookingDAO = new BookingDAO(jdbcConnectionFactory.getConnectionDB());
            String sqlCommand = "UPDATE  SERVICES SET  hotelBrowsingServiceURL=?   WHERE hotelName=? AND hotelCity=? AND hotelCountry=?;";
            Connection conn = jdbcConnectionFactory.getConnectionDB("SOAPSERVICEDB","soap","password");
            PreparedStatement stmt = conn.prepareStatement(sqlCommand);
            stmt.setString(1, serviceURL);
            stmt.setString(2, launchedHotel.getName().trim());
            stmt.setString(3, launchedHotel.getCity().trim());
            stmt.setString(4, launchedHotel.getCountry().trim());
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println("New exception");
            e.printStackTrace();
        }
    }


    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // Here is where the magic happens
    @Override
//    public  List<Offer>
    public  List<Offer> browseAvailableRooms(String idAgency,
                                             String password,
                                             Date arrivalDate, Date departureDate,
                                             int numberPerson) {
        Hotel runningHotel = Hotel.getInstance();
        Instant timestamp = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Paris"); // Replace with the desired time zone ID
        ZonedDateTime zonedDateTime = timestamp.atZone(zoneId);

        String requestId = idAgency+":"+zonedDateTime.toString();

        runningHotel.setHotelPartnership(this.partenershipDAO.getAll());

        List<Offer> offers = new ArrayList<Offer>();

        if (isPartner(idAgency,password)) {

            List<Booking> currentBooked =runningHotel.getHotleBooking() ;
            System.err.println("Current booking size: "+currentBooked.size());


            List<Integer> listOfNonBookable = new ArrayList<>();
            List<Integer> listAddedToOfferList = new ArrayList<>();

            for (int i = 0; i < currentBooked.size(); i++) {
                Booking booking = currentBooked.get(i);

                if (booking.getCheckoutDate().getTime() < arrivalDate.getTime()) {
                    System.out.println("Tour: "+i);
                    System.err.println("booking.getCheckoutDate().getTime(): "+booking.getCheckoutDate().getTime()
                            +" arrivalDate.getTime(): "+ arrivalDate.getTime()+
                            " Recieved date: "+ arrivalDate +
                            " Comparing date: "+booking.getCheckoutDate().getTime());
                    Offer currentOffer = new Offer();

                    // Availability date
                    currentOffer.setAvailabilityBegin(arrivalDate);

                    // Checkout Date
                    currentOffer.setCheckoutDate(departureDate);

                    // Number of person
                    Room room = this.roomDAO.getAll().get(booking.getRoomId() - 1);
                    currentOffer.setNumberPerson(numberPerson);

                    // offer Id
                    currentOffer.setOfferId(generateUUID());

                    // Number of Beds
                    currentOffer.setNumberBed(room.getNumberBed());

                    // Discounted Price
                    currentOffer.setPrice(getRoomById(Integer.parseInt(String.valueOf(booking.getRoomId()))).getBasePrice()
                            - (getRoomById(Integer.parseInt(String.valueOf(booking.getRoomId()))).getBasePrice()
                            * getPartnerByIdAndPassword(idAgency, password).getDiscountRate()/100));

                    // Room number
                    currentOffer.setRoomNumber(getRoomById(Integer.parseInt(String.valueOf(booking.getRoomId()))).getRoomNumber());

                    // added to offer
                    listAddedToOfferList.add(currentOffer.getRoomNumber());

                    // Room image Url
                    currentOffer.setImgURL(getRoomById(Integer.parseInt(String.valueOf(booking.getRoomId()))).getImgName());

                    // store suggested offer in the hotel
                    HashMap<String, Offer> stampedMap = new HashMap<String, Offer>();
                    stampedMap.put(requestId, currentOffer);
                    runningHotel.addToTemporaryRequestOffer(stampedMap);

                    // add do response list
                    offers.add(currentOffer);
                }
                else {
                     listOfNonBookable.add(booking.getRoomId());
                }
            }
            List <Room> roomList = this.roomDAO.getAll();

            // Completing the offer list with these rooms with no booking in the search frame
            roomList.forEach(room ->  {
                if (!listOfNonBookable.contains(room.getRoomNumber()) && !listAddedToOfferList.contains(room.getRoomNumber()))
                {
                    Offer currentOffer = new Offer();

                    // Availability date
                    currentOffer.setAvailabilityBegin(arrivalDate);

                    // Checkout Date
                    currentOffer.setCheckoutDate(departureDate);

                    // Number of person
                    currentOffer.setNumberPerson(numberPerson);

                    // offer Id
                    currentOffer.setOfferId(generateUUID());

                    // Number of Beds
                    currentOffer.setNumberBed(room.getNumberBed());

                    // Discounted Price
                    currentOffer.setPrice(room.getBasePrice()
                            - (room.getBasePrice()
                            * getPartnerByIdAndPassword(idAgency, password).getDiscountRate()/100));

                    // Room number
                    currentOffer.setRoomNumber(room.getRoomNumber());
                    // added to offer

                    // Room image Url
                    currentOffer.setImgURL(room.getImgName());

                    // store suggested offer in the hotel
                    HashMap<String, Offer> stampedMap = new HashMap<String, Offer>();
                    stampedMap.put(requestId, currentOffer);
                    runningHotel.addToTemporaryRequestOffer(stampedMap);

                    // add do response list
                    offers.add(currentOffer);
                }
            });

            // SAme-Stamped Request ID
            Offer requestIdOffer = new Offer();
            requestIdOffer.setOfferId(requestId);
            offers.add(requestIdOffer);

            return offers;
        }

        return null;
    }

    @Override
    public URL getImageURL(String roomImgNumber) {
        URL genImgURL= null;
       String directory = "requestedIMG/";
        ClassLoader fileLoader = getClass().getClassLoader();
        //          if (  this.roomDAO.saveImageToFile(roomImgNumber,
//                    this.roomDAO.getImageData(roomImgNumber), directory) ) {
//              File file = new File(fileLoader.getResource(directory+roomImgNumber).toURI());
//              genImgURL = file.toURI().toURL();
//          }

        genImgURL = this.roomDAO.getImageURLFromRetrieveAndSave(roomImgNumber);
        return genImgURL;
    }

    public Room getRoomById(int id) {
        Hotel runningHotel = Hotel.getInstance();
        for (int i = 0; i < runningHotel.getHotelRooms().size(); i++) {
           if (runningHotel.getHotelRooms().get(i).getRoomNumber() == id)
               return runningHotel.getHotelRooms().get(i);
        }
        return null;
    }
    public Partnership getPartnerByIdAndPassword(String idAgency, String password) {
        Hotel runningHotel = Hotel.getInstance();
        for (int i = 0; i < runningHotel.getHotelPartnership().size(); i++) {
            if (runningHotel.getHotelPartnership().get(i).getIdAgency().contains(idAgency.trim())
                    && runningHotel.getHotelPartnership().get(i).getPassword().contains(password.trim()))
                return runningHotel.getHotelPartnership().get(i);
        }
        return null;
    }

    public boolean isPartner(String idAgency, String password) {
        Hotel runningHotel = Hotel.getInstance();
        for (int i = 0; i < runningHotel.getHotelPartnership().size(); i++) {
            if (runningHotel.getHotelPartnership().get(i).getIdAgency().contains(idAgency.trim())
                    && runningHotel.getHotelPartnership().get(i).getPassword().contains(password.trim()))
                return true;
        }
        return false;
    }

}
