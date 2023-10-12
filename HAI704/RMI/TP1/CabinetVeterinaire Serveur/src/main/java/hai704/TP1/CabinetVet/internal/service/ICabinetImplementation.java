package hai704.TP1.CabinetVet.internal.service;

import hai704.TP1.CabinetVeterinaire.Classes.Animal;
import hai704.TP1.CabinetVeterinaire.Classes.FollowUpFile;
import hai704.TP1.CabinetVeterinaire.Classes.Specy;
import hai704.TP1.CabinetVeterinaire.Interface.ICabinet;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ICabinetImplementation extends UnicastRemoteObject implements ICabinet {

    private List<Animal> patientsCabinet;


    protected ICabinetImplementation() throws RemoteException {
        this.patientsCabinet = new ArrayList<Animal>();
    }

    @Override
    public List<Animal> getAllPatients() throws RemoteException {
        return this.patientsCabinet;
    }

    @Override
    public Animal getPatientByName(String name) throws RemoteException {
        return null;
    }

    @Override
    public List<Animal> getPatientsByName(String name) throws RemoteException {
        List<Animal> foundPatients = new ArrayList<Animal>();
        for(Animal patient: patientsCabinet) {
            if (patient.getName().contains(name)) foundPatients.add(patient);
        }
        return foundPatients;
    }

    @Override
    public Animal getPatientByFullName(String fullName) throws RemoteException {
        return null;
    }

    @Override
    public boolean addPatient(String name, String breed, Specy specy) throws RemoteException {
        return false;
    }


    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile("New patient of Cabinet added at: "));
        return false;
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) throws RemoteException {
        return false;
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, String specyName, Double specyAverageLifeSpanInDays, FollowUpFile followUpFile) throws RemoteException {
        return false;
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy, String followUpFile) throws RemoteException {
        return false;
    }

    @Override
    public boolean deletePatient(String fullName, String specyName) throws RemoteException {
        return false;
    }

    @Override
    public boolean updatePatient() throws RemoteException {
        return false;
    }
}
