package hai704.TP1.CabinetVet.internal.data;

import java.io.Serializable;

public class Specy implements Serializable {
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
