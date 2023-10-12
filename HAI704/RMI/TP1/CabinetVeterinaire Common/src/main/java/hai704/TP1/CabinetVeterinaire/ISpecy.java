package hai704.TP1.CabinetVeterinaire;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISpecy extends Remote {

    String getName() throws RemoteException;

    Double getAverageLifeSpanInDays() throws RemoteException;
}
