package hai704.TP1.CabinetVet.internal.service;




//import hai704.TP1.CabinetVeterinaire.Classes.Animal;
//import hai704.TP1.CabinetVeterinaire.Classes.Specy;
import hai704.TP1.CabinetVet.internal.service.models.AnimalWrapperServer;
import hai704.TP1.CabinetVeterinaire.Classes.Animal;
import hai704.TP1.CabinetVeterinaire.Classes.Specy;
import hai704.TP1.CabinetVeterinaire.Interface.IAnimal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class IAnimalImplementation extends UnicastRemoteObject implements IAnimal {
    // Exemple of service models

    // A single animal for question 1, Service on a single instance of Animal
    private AnimalWrapperServer seulPatient;
    private AnimalWrapperServer currentPatient;

    ISpecyImplementation specyService = new ISpecyImplementation();



    public IAnimalImplementation() throws RemoteException {
        this.seulPatient = new AnimalWrapperServer("Shepherd", "Jul", "Chihuahua", new Specy("Dog"));
    }

    public IAnimalImplementation(Animal animal) throws RemoteException {

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
    public String getFullNameTest() {
        return this.seulPatient.getFullName();
    }

    @Override
    public String getNameTest() {
        return this.seulPatient.getName();
    }

    @Override
    public boolean updateNameTest(String name) {
        this.seulPatient.setName(name);
        return true;
    }

    @Override
    public String getMasterNameTest() {
        return this.seulPatient.getMasterName();
    }

    @Override
    public boolean updateMasterNameTest(String masterName) {
        this.seulPatient.setMasterName(masterName);
        return true;
    }

    @Override
    public String getBreedTest() {
        return this.seulPatient.getBreed();
    }

    @Override
    public boolean updateBreedTest(String breed) {
        this.seulPatient.setBreed(breed);
        return true;
    }


    @Override
    public Specy getSpecyTest() throws RemoteException {
//        return this.seulPatient.getSpecy();
        return specyService.getSpecyByName(this.seulPatient.getName());
    }

    @Override
    public String getSpecyNameTest() throws RemoteException {
        return specyService.getSpecyName();
    }


    @Override
    public Double getAverageLifeSpanInDaysTest(String specyName) throws RemoteException {
        return specyService.getAverageLifeSpanInDays(this.seulPatient.getName());
    }

    @Override
    public Double getAverageLifeSpanInDaysTest() throws RemoteException {
        return specyService.getAverageLifeSpanInDays();
    }

//    @Override
//    public boolean updateSpecy(String specie) {
//        this.seulPatient.setSpecie(specie);
//        return true;
//    }

    // Q3. feature implementation
    @Override
    public String getFollowUpFileContentTest() throws RemoteException {
        return null;
    }

    @Override
    public boolean setFollowUpFileContentTest(String content) throws RemoteException {
        return false;
    }

    @Override
    public String getFullName() throws RemoteException {
        return null;
    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public boolean updateName(String name) throws RemoteException {
        return false;
    }

    @Override
    public String getMasterName() throws RemoteException {
        return null;
    }

    @Override
    public boolean updateMasterName(String masterName) throws RemoteException {
        return false;
    }

    @Override
    public String getBreed() throws RemoteException {
        return null;
    }

    @Override
    public boolean updateBreed(String breed) throws RemoteException {
        return false;
    }

    @Override
    public Specy getSpecy() throws RemoteException {
        return null;
    }

    @Override
    public String getSpecyName() throws RemoteException {
        return null;
    }

    @Override
    public Double getAverageLifeSpanInDays(String specyName) throws RemoteException {
        return null;
    }

    @Override
    public Double getAverageLifeSpanInDays() throws RemoteException {
        return null;
    }

    @Override
    public String getFollowUpFileContent() throws RemoteException {
        return null;
    }

    @Override
    public boolean setFollowUpFileContent(String content) throws RemoteException {
        return false;
    }
    // Q3. feature implementation




}
