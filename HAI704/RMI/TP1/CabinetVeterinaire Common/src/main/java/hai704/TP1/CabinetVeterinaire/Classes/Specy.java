package hai704.TP1.CabinetVeterinaire.Classes;

import java.io.Serializable;

public class Specy implements Serializable {
    private String ID; //Auto-generated, but not used
    private String name;
    private Double averageLifeSpanInDays;
//    private Double averageLifeSpanInMonths;
//    private Double averageLifeSpanInYears;


    public Specy(String name) {
        this.name = name;
    }

    public Specy(String name, Double averageLifeSpanInDays) {
        this.name = name;
        this.averageLifeSpanInDays = averageLifeSpanInDays;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Double getAverageLifeSpanInDays() {
        return averageLifeSpanInDays;
    }

    protected void setAverageLifeSpanInDays(Double averageLifeSpanInDays) {
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
