package hai704.TP1.CabinetVeterinaire.Interface;

import hai704.TP1.CabinetVeterinaire.Classes.Animal;
import hai704.TP1.CabinetVeterinaire.Classes.FollowUpFile;
import hai704.TP1.CabinetVeterinaire.Classes.Specy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICabinet extends Remote {

    List<Animal> getAllPatients() throws RemoteException;
    Animal getPatientByName(String name) throws RemoteException;
    List<Animal> getPatientsByName(String name) throws RemoteException;
    Animal getPatientByFullName(String fullName) throws RemoteException;
    boolean addPatient(String name, String breed, Specy specy) throws  RemoteException;
    boolean addPatient(String name, String masterName, String breed, Specy specy) throws  RemoteException;
    boolean addPatient(String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) throws  RemoteException;
    boolean addPatient(String name, String masterName, String breed, String specyName,Double specyAverageLifeSpanInDays, FollowUpFile followUpFile) throws  RemoteException;
    boolean addPatient(String name, String masterName, String breed, Specy specy, String followUpFile) throws  RemoteException;
    boolean deletePatient(String fullName, String specyName) throws RemoteException;
    boolean updatePatient() throws RemoteException;

}
