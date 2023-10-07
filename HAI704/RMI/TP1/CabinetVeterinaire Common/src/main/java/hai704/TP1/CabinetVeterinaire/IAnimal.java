package hai704.TP1.CabinetVeterinaire;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {

    String getFullName() throws  RemoteException;

     String getName() throws  RemoteException;

    boolean updateName(String name) throws  RemoteException;

    String getMasterName() throws  RemoteException;

    boolean updateMasterName(String masterName) throws  RemoteException;

     String getBreed() throws  RemoteException;

    boolean updateBreed(String breed) throws  RemoteException;

     String getSpecie() throws  RemoteException;

    boolean updateSpecie(String specie) throws  RemoteException;

}
