package Serveur.hai704.TP1.Cabinet.Vetetrinaire.service;


import Common.hai704.TP1.Cabinet.Veterinaire.*;
import Serveur.hai704.TP1.Cabinet.Vetetrinaire.models.AnimalWrapperServer;
import Serveur.hai704.TP1.Cabinet.Vetetrinaire.models.FollowUpFileWrapperServer;
import Serveur.hai704.TP1.Cabinet.Vetetrinaire.models.SpecyWrapperServer;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ICabinetImplementation extends UnicastRemoteObject implements ICabinet {

    protected List<Animal> patientsCabinet;
    protected HashMap<String, IClient> veterinarians;

    private int lastTreshol=0;
    private int newTreshol=0;
    int[]  tresholds = new int[7];

    LocalDateTime lastCall;
    LocalDateTime now;


    public ICabinetImplementation() throws RemoteException {
//        this.patientsCabinet = new ArrayList<AnimalWrapperServer>();
        this.patientsCabinet = new ArrayList<Animal>();
        this.veterinarians = new HashMap<String, IClient>();

        tresholds[0] = 1;
        tresholds[1] = 3;
        tresholds[2] = 10;
        tresholds[3] = 50;
        tresholds[4] = 100;
        tresholds[5] = 500;
        tresholds[6] = 1000;
    }

    private  int calculateTimeDifference(LocalDateTime recent, LocalDateTime older) {
        return Duration.between(recent, older).toMinutesPart();
    }

    private void sendAlerts() {
        now = getNewLocalTime(); // each time the method is called trying to send alerts
        if((lastTreshol!=newTreshol && intInArray(tresholds, newTreshol)) // Each time new treshold gotten
                ||  (calculateTimeDifference(now, lastCall) >=10) ) // Each 10 Elapsed minutes, resend the same treshold (1 edge case)
        {
            lastCall=getNewLocalTime();
            lastTreshol=newTreshol;

            for (String key: veterinarians.keySet()) {
                try {
                    veterinarians.get(key).alertTreshold(lastTreshol);
                } catch (RemoteException e) {
//                    throw new RuntimeException(e);
                }
            }
        }

    }

    private boolean intInArray(int[] arr, int val) {
        return Arrays.stream(arr).anyMatch(value -> value == val);
    }

    private LocalDateTime getNewLocalTime() {
        return LocalDateTime.now();
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
        Animal foundAnimal = new Animal();
        for(Animal patient: this.patientsCabinet) {
            if (patient.getFullName().toLowerCase().contentEquals(fullName.trim().toLowerCase())) foundAnimal=patient;
        }
        return foundAnimal;
    }

    @Override
    public boolean addPatient(String name, String breed, String specyName) throws RemoteException {
        Animal newPatient = new Animal(name, "", breed, new Specy(specyName), new FollowUpFile(""));
        boolean response=this.patientsCabinet.add(newPatient);
        sendAlerts(); // if treshold,....
        return response;
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile());
        boolean response=this.patientsCabinet.add(newPatient);
        sendAlerts(); // if treshold,....
        return response;
//        return this.patientsCabinet.add(newPatient);
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, followUpFile);
        boolean response=this.patientsCabinet.add(newPatient);
        sendAlerts(); // if treshold,....
        return response;
//        return this.patientsCabinet.add(newPatient);
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, String specyName, Double specyAverageLifeSpanInDays, FollowUpFile followUpFile) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, new Specy(specyName, specyAverageLifeSpanInDays), followUpFile);
        boolean response=this.patientsCabinet.add(newPatient);
        sendAlerts(); // if treshold,....
        return response;
//        return this.patientsCabinet.add(newPatient);
    }

    @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy, String followUpFile) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile(followUpFile));
        boolean response=this.patientsCabinet.add(newPatient);
        sendAlerts(); // if treshold,....
        return response;
//        return this.patientsCabinet.add(newPatient);
    }



    @Override
    public boolean deletePatient(String fullName, String specyName) throws RemoteException {
        Animal patientToDelete = null;
        for (Animal patient: this.patientsCabinet) {
            if (patient.getFullName().toLowerCase().contentEquals(fullName.trim().toLowerCase())
                    && patient.getSpecy().getName().trim().toLowerCase().contentEquals(specyName.trim().toLowerCase()))
                patientToDelete = patient;
        }
        boolean response=this.patientsCabinet.remove(patientToDelete);
        sendAlerts(); // if treshold,....
        return response;
//        return this.patientsCabinet.remove(patientToDelete);
//        return this.patientsCabinet.removeIf();
    }

    @Override
    public boolean updatePatient(Animal animal, Animal newAnimal) throws RemoteException {
        FollowUpFileWrapperServer followUpFileToUpdate;
        SpecyWrapperServer specyWrapperToUpdate;
        AnimalWrapperServer animalToUpdate = new AnimalWrapperServer(animal);


        for (Animal current: this.patientsCabinet) {
            //un-wrapping hidden properties, here this ID is supposed to be invisible to the client side
            AnimalWrapperServer currentUnwrapped = new AnimalWrapperServer(current);
            if (currentUnwrapped.getID().contentEquals(animalToUpdate.getAnimalID())) {
                // Before update, contains IDs
//                followUpFileToUpdate = new FollowUpFileWrapperServer(animal.getFollowUpFile());
//                specyWrapperToUpdate = new SpecyWrapperServer(animal.getSpecy());
//
//                // New followUp and specy with IDs
//                FollowUpFile newFollowUp =


                current.setName(newAnimal.getName());
                current.setMasterName(newAnimal.getMasterName());
                current.setBreed(newAnimal.getBreed());

                //followUp
                current.getFollowUpFile().setContent(newAnimal.getFollowUpFile().getContent());

                //Specy
                current.getSpecy().setAverageLifeSpanInDays(newAnimal.getSpecy().getAverageLifeSpanInDays());
                current.getSpecy().setName(newAnimal.getSpecy().getName());
                
            }
            return true;
        }
        return false;
    }

    @Override
    public String signUpCabinetsClient(IClient client) throws RemoteException {
        String genKey = "ICab"+String.valueOf(System.currentTimeMillis());
        veterinarians.put(genKey, client);
        System.out.println("+1 Veto: "+genKey);
        return genKey;
    }

    @Override
    public boolean unSignCabinetsClient(IClient client, String ID) throws RemoteException {
        System.out.println("-1 Veto: "+ID);
        return veterinarians.remove(ID, client);
    }


}


/*
*     @Override
    public boolean addPatient(String name, String masterName, String breed, Specy specy) throws RemoteException {
        Animal newPatient = new Animal(name, masterName, breed, specy, new FollowUpFile("New patient of Cabinet added at: "));
        return this.patientsCabinet.add(newPatient);
    }*/