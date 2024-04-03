package web.service.booking.models;


import java.util.Date;

public class Booking {

    private String clientBookingLastName;
    private String clientBookingFirstName;
    private Date arrivalDate;
    private Date checkoutDate;
    private int numberPersons;

    private String bookingReference;
    private int roomId;


    public Booking() {
    }

    public Booking(String clientBookingLastName, String clientBookingFirstName,
                   Date arrivalDate, Date checkoutDate,
                   int numberPersons, String bookingReference, int roomId) {
        this.clientBookingLastName = clientBookingLastName;
        this.clientBookingFirstName = clientBookingFirstName;
        this.arrivalDate = arrivalDate;
        this.checkoutDate = checkoutDate;
        this.numberPersons = numberPersons;
        this.bookingReference = bookingReference;
        this.roomId = roomId;
    }

    public String getClientBookingLastName() {
        return clientBookingLastName;
    }

    public void setClientBookingLastName(String clientBookingLastName) {
        this.clientBookingLastName = clientBookingLastName;
    }

    public String getClientBookingFirstName() {
        return clientBookingFirstName;
    }

    public void setClientBookingFirstName(String clientBookingFirstName) {
        this.clientBookingFirstName = clientBookingFirstName;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumberPersons() {
        return numberPersons;
    }

    public void setNumberPersons(int numberPersons) {
        this.numberPersons = numberPersons;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "clientBookingLastName='" + clientBookingLastName + '\'' +
                ", clientBookingFirstName='" + clientBookingFirstName + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", checkoutDate=" + checkoutDate +
                ", numberPersons=" + numberPersons +
                ", bookingReference='" + bookingReference + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}


