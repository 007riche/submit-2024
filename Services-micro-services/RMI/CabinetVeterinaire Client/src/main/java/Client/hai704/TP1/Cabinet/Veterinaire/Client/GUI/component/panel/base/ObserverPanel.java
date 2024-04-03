package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.DisplayAlertEvent;

import java.awt.*;


public class ObserverPanel extends Panel implements DisplayAlertEvent {

    private String sectionName="";
    private String previousWorkingSectionName="";

    public ObserverPanel(Color backgroungColor, Color foregroungColor, Dimension dimension) {
        super(backgroungColor, foregroungColor, dimension);
    }

    @Override
    public void displayAlert() {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, this.getSectionName());
    }

    @Override
    public void closeAlert() {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, this.getPreviousWorkingSectionName());
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getPreviousWorkingSectionName() {
        return previousWorkingSectionName;
    }

    public void setPreviousWorkingSectionName(String previousWorkingSectionName) {
        this.previousWorkingSectionName = previousWorkingSectionName;
    }
}
