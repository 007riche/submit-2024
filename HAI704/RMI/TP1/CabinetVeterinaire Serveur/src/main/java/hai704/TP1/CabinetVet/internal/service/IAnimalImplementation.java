package hai704.TP1.CabinetVet.internal.service;

//import hai704.TP1.CabinetVet.IAnimal;
import hai704.TP1.CabinetVet.internal.data.Animal;
import hai704.TP1.CabinetVeterinaire.IAnimal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IAnimalImplementation extends UnicastRemoteObject implements IAnimal {
    // Exemple of service data

    // A single animal for question 1, Service on a single instance of Animal
    private Animal seulPatient;



    public IAnimalImplementation() throws RemoteException {
        this.seulPatient = new Animal("Shepherd", "Jul", "Chihuahua", "Dog");
    }

   // public void generatePatient() {
    //  this.patients.add(new Animal("roxy", "Billy", "sphinx", "Cat"));
    // }


//    @Override
//    public Animal getOneAnimal() throws RemoteException {
//        int randomIndex = new Random().nextInt(this.patients.size());
//        return this.patients.get(randomIndex);
//    }

    @Override
    public String getFullName() {
        return this.seulPatient.getFullName();
    }

    @Override
    public String getName() {
        return this.seulPatient.getName();
    }

    @Override
    public boolean updateName(String name) {
        this.seulPatient.setName(name);
        return true;
    }

    @Override
    public String getMasterName() {
        return this.seulPatient.getMasterName();
    }

    @Override
    public boolean updateMasterName(String masterName) {
        this.seulPatient.setMasterName(masterName);
        return true;
    }

    @Override
    public String getBreed() {
        return this.seulPatient.getBreed();
    }

    @Override
    public boolean updateBreed(String breed) {
        this.seulPatient.setBreed(breed);
        return true;
    }

    @Override
    public String getSpecie() {
        return this.seulPatient.getSpecie();
    }

    @Override
    public boolean updateSpecie(String specie) {
        this.seulPatient.setSpecie(specie);
        return true;
    }

    // Q3. feature implementation
    @Override
    public String getContent() throws RemoteException {
        return null;
    }

    @Override
    public boolean setContent(String content) throws RemoteException {
        return false;
    }
    // Q3. feature implementation
}
