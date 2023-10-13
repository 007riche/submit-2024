package hai704.TP1.CabinetVet.internal.service;


import hai704.TP1.CabinetVet.internal.service.models.AnimalWrapperServer;
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
        this.patientsCabinet = new ArrayList<AnimalWrapperServer>();
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
        for(Animal patient: this.patientsCabinet) {
            if (patient.getName().contains(name)) foundPatients.add(patient);
        }
        return foundPatients;
    }

    @Override
    public Animal getPatientByFullName(String fullName) throws RemoteException {
        Animal foundAnimal ;
        for(Animal patient: this.patientsCabinet) {
            if (patient.getFullName().toLowerCase().contentEquals(fullName.trim().toLowerCase())) foundAnimal=patient;
        }
        return null;
    }

    @Override
    public boolean addPatient(String name, String breed, String specyName) throws RemoteException {
        Animal newPatient = new Animal(name, "", breed, new Specy(specyName), new FollowUpFile(""));
        return this.patientsCabinet.add(newPatient);

    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile("New patient of Cabinet added at: "));
        return this.patientsCabinet.add(newPatient);
    }


//    @Override
//    public boolean addPatient(String name, String masterName, String breed, Specy specy) throws RemoteException {
//        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile("New patient of Cabinet added at: "));
//        return this.patientsCabinet.add(newPatient);
//    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, followUpFile);
        return this.patientsCabinet.add(newPatient);
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, String specyName, Double specyAverageLifeSpanInDays, FollowUpFile followUpFile) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, new Specy(specyName, specyAverageLifeSpanInDays), followUpFile);
        return this.patientsCabinet.add(newPatient);
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy, String followUpFile) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile(followUpFile));
        return this.patientsCabinet.add(newPatient);
    }

    @Override
    public boolean deletePatient(String fullName, String specyName) throws RemoteException {
        Animal patientToDelete = null;
        for (Animal patient: this.patientsCabinet) {
            if (patient.getFullName().toLowerCase().contentEquals(fullName.trim().toLowerCase())
                    && patient.getSpecy().getName().trim().toLowerCase().contentEquals(specyName.trim().toLowerCase()))
                patientToDelete = patient;
        }
        return this.patientsCabinet.remove(patientToDelete);
//        return this.patientsCabinet.removeIf();
    }

    @Override
    public boolean updatePatient() throws RemoteException {
        return false;
    }
}
