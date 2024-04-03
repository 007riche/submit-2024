package web.service.booking.models;

public class Room {
    private int roomNumber;
    private int numberBed;
    private double basePrice;
    private boolean available; // to be transient

    private String imgName;
    private  int imgId; // retrieved

    public Room() {
    }

    public Room(int roomNumber, int numberBed, double basePrice
//            , boolean available
    ) {
        this.roomNumber = roomNumber;
        this.numberBed = numberBed;
        this.basePrice = basePrice;
//        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(int numberBed) {
        this.numberBed = numberBed;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room {" +
                "roomNumber=" + roomNumber +
                ", numberBed=" + numberBed +
                ", basePrice=" + basePrice +
                ", available=" + available +
                '}';
    }
}
