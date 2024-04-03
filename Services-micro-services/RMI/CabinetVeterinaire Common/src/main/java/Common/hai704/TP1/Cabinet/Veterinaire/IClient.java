package Common.hai704.TP1.Cabinet.Veterinaire;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void alertTreshold(int threshold) throws RemoteException;
}
