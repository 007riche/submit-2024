package web.service.booking.models;

//import web.service.booking.services.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hotel {
    private static Hotel instance;
    private String name;
    private Double stars;
    private String country;
    private String city;
    private String street;
    private int streetNumber;
    private String gpsPosition;
    private String hotelImgUrl;
    private List<Room> hotelRooms;
    private  List<Booking> hotleBooking;
    private List<Partnership> hotelPartnership;
    private List<HashMap<String, Offer>> temporaryRequestOffer;
    private String BOOKING_URL;
    private String BROWSING_URL;
    private String PARTNERS_URL;


    private String HOTEL_DOMAIN;


    public Hotel() {
    }

    public Hotel(String name, Double stars, String city, String street, int streetNumber) {
        this.name = name;
        this.stars = stars;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.HOTEL_DOMAIN = this.name.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim()+
                            this.city.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getGpsPosition() {
        return gpsPosition;
    }

    public String getBOOKING_URL() {
        return BOOKING_URL;
    }

    public void setBOOKING_URL(String BOOKING_URL) {
        this.BOOKING_URL = BOOKING_URL;
    }

    public String getBROWSING_URL() {
        return BROWSING_URL;
    }

    public void setBROWSING_URL(String BROWSING_URL) {
        this.BROWSING_URL = BROWSING_URL;
    }

    public String getPARTNERS_URL() {
        return PARTNERS_URL;
    }

    public void setPARTNERS_URL(String PARTNERS_URL) {
        this.PARTNERS_URL = PARTNERS_URL;
    }

    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition;
    }


    public List<Room> getHotelRooms() {
        return hotelRooms;
    }

    public void setHotelRooms(List<Room> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }


    public String getHOTEL_DOMAIN() {
        return HOTEL_DOMAIN;
    }
    public void setHOTEL_DOMAIN() {
        this.HOTEL_DOMAIN = this.name.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim()+
                this.city.replaceAll("'", "").replaceAll(" ", "").toLowerCase().trim();
    }

    public String getHotelImgUrl() {
        return hotelImgUrl;
    }

    public void setHotelImgUrl(String hotelImgUrl) {
        this.hotelImgUrl = hotelImgUrl;
    }

    public List<Room> getAllRoom() {
        return this.allRoom;
    }

    public List<Room> getAvailalbleRooms() {
        for (Room room : allRoom) {
            if (room.isAvailable()) {
                availalbleRooms.add(room);
            }
        }
        return availalbleRooms;
    }

  public   List<Room> allRoom = new ArrayList<Room>();



    List<Booking> bookings = new ArrayList<Booking>();
public  List<Room> availalbleRooms = new ArrayList<Room>();

    public void addRoom(int roomNumber, int numberBed, float price
//            , boolean available
    ) {
        allRoom.add(new Room(roomNumber, numberBed, price));
    }

    public List<Room> getAvailalbleRooms(List<Room> rooms) {
    List<Room> currentAvailableRooms = new ArrayList<Room>();
    for (Room room : rooms) {
        if (room.isAvailable()){
            currentAvailableRooms.add(room);
        }
    }
    return currentAvailableRooms;
}


    public void setTemporaryRequestOffer(List<HashMap<String, Offer>> temporaryRequestOffer) {
        this.temporaryRequestOffer = temporaryRequestOffer;
    }

    double getCurrentHotelMinPrice(List<Room> rooms) {
    double currentHotelRoomMinPrice = rooms.get(1).getBasePrice();
    double currentHotelRoomMaxPrice = rooms.get(1).getBasePrice();
    for (Room room : rooms) {
        if (currentHotelRoomMinPrice> room.getBasePrice()){
           currentHotelRoomMinPrice = room.getBasePrice();
        }
    }
    return currentHotelRoomMinPrice;
}

public double getCurrentAvailbaleHotelMinPrice() {

    return getCurrentHotelMinPrice(getAvailalbleRooms());
}

    public double getCurrentAvailbaleHotelMaxPrice() {
        return getHotelMaxPrice(getAvailalbleRooms());
    }

double getHotelMaxPrice(List<Room> rooms) {
        double currentHotelRoomMaxPrice = rooms.get(1).getBasePrice();
        for (Room room : rooms) {
            if (currentHotelRoomMaxPrice < room.getBasePrice()){
                currentHotelRoomMaxPrice = room.getBasePrice();
            }
        }
        return currentHotelRoomMaxPrice;
    }



    public static synchronized Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

    public List<Booking> getHotleBooking() {
        return hotleBooking;
    }

    public void setHotleBooking(List<Booking> hotleBooking) {
        this.hotleBooking = hotleBooking;
    }

    public void addToTemporaryRequestOffer(HashMap<String, Offer> stampedOffer) {
        this.temporaryRequestOffer.add(stampedOffer) ;
    }

    public void removeTemporaryRequestOffer(String key) {
        this.temporaryRequestOffer.forEach( stringOfferHashMap -> {
            if (stringOfferHashMap.containsKey(key)) {
                System.out.println("HASHMAPS DELETED: "+key);
                this.temporaryRequestOffer.remove(stringOfferHashMap);
            }

        });
    }

    public List<HashMap<String, Offer>> getTemporaryRequestOffer() {
        return temporaryRequestOffer;
    }

    public List<Partnership> getHotelPartnership() {
        return hotelPartnership;
    }

    public void setHotelPartnership(List<Partnership> hotelPartnership) {
        this.hotelPartnership = hotelPartnership;
    }
}
