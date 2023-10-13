package hai704.TP1.CabinetVet.internal.service.models;

import hai704.TP1.CabinetVeterinaire.Classes.Specy;

public class SpecyWrapperServer extends Specy {
    public SpecyWrapperServer(String name) {
        super(name);
    }

    public SpecyWrapperServer(String name, Double averageLifeSpanInDays) {
        super(name, averageLifeSpanInDays);
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
