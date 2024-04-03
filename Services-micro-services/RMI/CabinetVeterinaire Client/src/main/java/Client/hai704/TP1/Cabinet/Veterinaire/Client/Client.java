package Client.hai704.TP1.Cabinet.Veterinaire.Client;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.AlertFromServerEvent;
import Common.hai704.TP1.Cabinet.Veterinaire.IClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {
    int currentThreshold = 0;

    static private List<AlertFromServerEvent> alertFromServerEventsObservers = new ArrayList<AlertFromServerEvent>(); // Alert events listeners
    @Override
    public void alertTreshold(int threshold) throws RemoteException {
            this.setCurrentThreshold(threshold);
            notifyAlertListeners();
    }

    public void attachAlertListener(AlertFromServerEvent newObserver) {
        alertFromServerEventsObservers.add(newObserver);
    }

    public void detachAlertListener(AlertFromServerEvent observer) {
        alertFromServerEventsObservers.remove(observer);
    }

    public void notifyAlertListeners() {
        for (AlertFromServerEvent o: alertFromServerEventsObservers) {
            o.onReceiveAlert(this.getCurrentThreshold());
        }
    }

    public int getCurrentThreshold() {
        return currentThreshold;
    }

    public void setCurrentThreshold(int currentThreshold) {
        this.currentThreshold = currentThreshold;
    }
}
