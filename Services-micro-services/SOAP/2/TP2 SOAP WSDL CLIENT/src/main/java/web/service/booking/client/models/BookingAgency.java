package web.service.booking.client.models;

import java.util.Date;

public class BookingAgency {
    private int bookingSNo;
    private String bookingReference;
    private Double totalPrice;
    private Date arrivalDate;

    private Date checkoutDate;
    private int numberPersons;
    private String mainPersonne;
    private int clientId;

    public BookingAgency() {
    }

    public BookingAgency(int bookingSNo, String bookingReference, Double totalPrice, String mainPersonne) {
        this.bookingSNo = bookingSNo;
        this.bookingReference = bookingReference;
        this.totalPrice = totalPrice;
        this.mainPersonne = mainPersonne;
    }


    public int getBookingSNo() {
        return bookingSNo;
    }

    public void setBookingSNo(int bookingSNo) {
        this.bookingSNo = bookingSNo;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMainPersonne() {
        return mainPersonne;
    }

    public void setMainPersonne(String mainPersonne) {
        this.mainPersonne = mainPersonne;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setNumberPersons(int numberPersons) {
        this.numberPersons = numberPersons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingAgency that)) return false;

        if (getBookingSNo() != that.getBookingSNo()) return false;
        if (!getBookingReference().equals(that.getBookingReference())) return false;
        if (!getTotalPrice().equals(that.getTotalPrice())) return false;
        return getMainPersonne().equals(that.getMainPersonne());
    }

    @Override
    public int hashCode() {
        int result = getBookingSNo();
        result = 31 * result + getBookingReference().hashCode();
        result = 31 * result + getTotalPrice().hashCode();
        result = 31 * result + getMainPersonne().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BookingAgency{" +
                "bookingSNo=" + bookingSNo +
                ", bookingReference='" + bookingReference + '\'' +
                ", totalPrice=" + totalPrice +
                ", arrivalDate=" + arrivalDate +
                ", checkoutDate=" + checkoutDate +
                ", numberPersons=" + numberPersons +
                ", mainPersonne='" + mainPersonne + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
