package hai704.TP1.CabinetVeterinaire.Interface;

//import hai704.TP1.CabinetVet.internal.models.Specy;

import hai704.TP1.CabinetVeterinaire.Classes.Specy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISpecy extends Remote {

    Specy getSpecyByName(String name) throws RemoteException;
    String getSpecyName() throws RemoteException;
    Double getAverageLifeSpanInDays(String specyName) throws RemoteException;
    Double getAverageLifeSpanInDays() throws RemoteException;
    Specy getSpecyByNameTest(String name) throws RemoteException;
    String getSpecyNameTest() throws RemoteException;
    Double getAverageLifeSpanInDaysTest(String specyName) throws RemoteException;
    Double getAverageLifeSpanInDaysTest() throws RemoteException;
}
