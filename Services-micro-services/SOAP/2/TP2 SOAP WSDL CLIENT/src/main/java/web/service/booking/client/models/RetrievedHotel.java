package web.service.booking.client.models;

public class RetrievedHotel {
    private String imgUrl;
    private String hotelName;
    private String hotelAdresse;
    private double stars;
    private double priceMin;
    private int roombeds;

    public RetrievedHotel() {
    }

    public RetrievedHotel(String imgUrl, String hotelName, String hotelAdresse,
                          double stars, double priceMin, int roombeds) {
        this.imgUrl = imgUrl;
        this.hotelName = hotelName;
        this.hotelAdresse = hotelAdresse;
        this.stars = stars;
        this.priceMin = priceMin;
        this.roombeds = roombeds;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAdresse() {
        return hotelAdresse;
    }

    public void setHotelAdresse(String hotelAdresse) {
        this.hotelAdresse = hotelAdresse;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public int getRoombeds() {
        return roombeds;
    }

    public void setRoombeds(int roombeds) {
        this.roombeds = roombeds;
    }
}
