package web.service.booking.client.models;

import java.util.Objects;

public class Service {
    private String  hotelName; // Set
    private String hotelCity;
    private Double hotelStars;
    private String hotelImageUrl;
    private String hotelAddress;
    private String hotelBookingServiceURL; // Set
    private String hotelBrowsingServiceURL; // Set
    private String hotelPartnersServiceURL; // Set
    private String loginId; // Set
    private String idAgency; // Set
    private String userName; // Set
    private String Password; // Set

    private int currentRoomAvail;
    private double currentMinPrice;


    public Service() {
    }

    public Service(String hotelName, String hotelCity, Double hotelStars, String hotelImageUrl, String hotelBookingServiceURL, String hotelBrowsingServiceURL, String hotelPartnersServiceURL, String loginId, String idAgency, String userName, String password) {
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.hotelStars = hotelStars;
        this.hotelImageUrl = hotelImageUrl;
        this.hotelBookingServiceURL = hotelBookingServiceURL;
        this.hotelBrowsingServiceURL = hotelBrowsingServiceURL;
        this.hotelPartnersServiceURL = hotelPartnersServiceURL;
        this.loginId = loginId;
        this.idAgency = idAgency;
        this.userName = userName;
        Password = password;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public Double getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(Double hotelStars) {
        this.hotelStars = hotelStars;
    }

    public String getHotelImageUrl() {
        return hotelImageUrl;
    }

    public void setHotelImageUrl(String hotelImageUrl) {
        this.hotelImageUrl = hotelImageUrl;
    }

    public String getHotelBookingServiceURL() {
        return hotelBookingServiceURL;
    }

    public void setHotelBookingServiceURL(String hotelBookingServiceURL) {
        this.hotelBookingServiceURL = hotelBookingServiceURL;
    }

    public String getHotelBrowsingServiceURL() {
        return hotelBrowsingServiceURL;
    }

    public void setHotelBrowsingServiceURL(String hotelBrowsingServiceURL) {
        this.hotelBrowsingServiceURL = hotelBrowsingServiceURL;
    }

    public String getHotelPartnersServiceURL() {
        return hotelPartnersServiceURL;
    }

    public void setHotelPartnersServiceURL(String hotelPartnersServiceURL) {
        this.hotelPartnersServiceURL = hotelPartnersServiceURL;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getCurrentRoomAvail() {
        return currentRoomAvail;
    }

    public void setCurrentRoomAvail(int currentRoomAvail) {
        this.currentRoomAvail = currentRoomAvail;
    }

    public double getCurrentMinPrice() {
        return currentMinPrice;
    }

    public void setCurrentMinPrice(double currentMinPrice) {
        this.currentMinPrice = currentMinPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return currentRoomAvail == service.currentRoomAvail
                && Double.compare(currentMinPrice, service.currentMinPrice) == 0
                && Objects.equals(hotelName, service.hotelName)
                && Objects.equals(hotelCity, service.hotelCity)
                && Objects.equals(hotelStars, service.hotelStars)
                && Objects.equals(hotelImageUrl, service.hotelImageUrl)
                && Objects.equals(hotelBookingServiceURL, service.hotelBookingServiceURL)
                && Objects.equals(hotelBrowsingServiceURL, service.hotelBrowsingServiceURL)
                && Objects.equals(hotelPartnersServiceURL, service.hotelPartnersServiceURL)
                && Objects.equals(loginId, service.loginId)
                && Objects.equals(idAgency, service.idAgency)
                && Objects.equals(userName, service.userName)
                && Objects.equals(Password, service.Password);
    }

    @Override
    public int hashCode() {
        int result = getHotelName().hashCode();
        result = 31 * result + getHotelCity().hashCode();
        result = 31 * result + getHotelStars().hashCode();
        result = 31 * result + getHotelImageUrl().hashCode();
        result = 31 * result + getHotelBookingServiceURL().hashCode();
        result = 31 * result + getHotelBrowsingServiceURL().hashCode();
        result = 31 * result + getHotelPartnersServiceURL().hashCode();
        result = 31 * result + getLoginId().hashCode();
        result = 31 * result + getIdAgency().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelCity='" + hotelCity + '\'' +
                ", hotelStars=" + hotelStars +
                ", hotelImageUrl='" + hotelImageUrl + '\'' +
                ", hotelBookingServiceURL='" + hotelBookingServiceURL + '\'' +
                ", hotelBrowsingServiceURL='" + hotelBrowsingServiceURL + '\'' +
                ", hotelPartnersServiceURL='" + hotelPartnersServiceURL + '\'' +
                ", loginId='" + loginId + '\'' +
                ", idAgency='" + idAgency + '\'' +
                ", userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                ", currentRoomAvail=" + currentRoomAvail +
                ", currentMinPrice=" + currentMinPrice +
                '}';
    }
}
