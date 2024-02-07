package Serveur.hai704.TP1.Cabinet.Vetetrinaire.models;


import Common.hai704.TP1.Cabinet.Veterinaire.Specy;

public class SpecyWrapperServer extends Specy {
    public SpecyWrapperServer(String name) {
        super(name);
    }

    public SpecyWrapperServer(String name, Double averageLifeSpanInDays) {
        super(name, averageLifeSpanInDays);
    }

    public SpecyWrapperServer(String Id, String name, Double averageLifeSpanInDays) {
        super(Id, name, averageLifeSpanInDays);
    }

    public SpecyWrapperServer(Specy specy) {
        super(specy);
    }

    public String getID() {
        return super.getID();
    }

    public void setID(String ID) {
        super.setID(ID);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public Double getAverageLifeSpanInDays() {
        return super.getAverageLifeSpanInDays();
    }

    public void setAverageLifeSpanInDays(Double averageLifeSpanInDays) {
        super.setAverageLifeSpanInDays(averageLifeSpanInDays);
    }
}
