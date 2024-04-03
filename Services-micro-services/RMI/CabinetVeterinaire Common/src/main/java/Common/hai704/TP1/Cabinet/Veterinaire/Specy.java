package Common.hai704.TP1.Cabinet.Veterinaire;

import java.io.Serializable;

public class Specy implements Serializable {
    private String ID; //Auto-generated
    private String name;
    private Double averageLifeSpanInDays;

//    private Double averageLifeSpanInMonths;
//    private Double averageLifeSpanInYears;


    public Specy(String name) {
        this.ID=String.valueOf(System.currentTimeMillis());
        this.name = name;
        this.averageLifeSpanInDays=0.0;
    }

    public Specy(String name, Double averageLifeSpanInDays) {
        this.ID=String.valueOf(System.currentTimeMillis());
        this.name = name;
        this.averageLifeSpanInDays = averageLifeSpanInDays;
    }
    protected Specy(String Id,  String name, Double averageLifeSpanInDays) {
        this.ID=Id;
        this.name = name;
        this.averageLifeSpanInDays = averageLifeSpanInDays;
    }

    public Specy(Specy specy) {
        this.ID=specy.getID();
        this.name = specy.getName();
        this.averageLifeSpanInDays= specy.getAverageLifeSpanInDays();

        System.out.println("Un-wrapped Specy ID: "+this.ID);
    }

    protected String getID() {
        return ID;
    }

    protected void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageLifeSpanInDays() {
        return averageLifeSpanInDays;
    }

    public void setAverageLifeSpanInDays(Double averageLifeSpanInDays) {
        this.averageLifeSpanInDays = averageLifeSpanInDays;
    }

    @Override
    public String toString() {
        return "Specy{" +
                "name='" + name + '\'' +
                ", averageLifeSpanInDays=" + averageLifeSpanInDays +
                '}';
    }
}
