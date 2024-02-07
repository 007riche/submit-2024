package Serveur.hai704.TP1.Cabinet.Vetetrinaire.models;


import Common.hai704.TP1.Cabinet.Veterinaire.Animal;
import Common.hai704.TP1.Cabinet.Veterinaire.FollowUpFile;
import Common.hai704.TP1.Cabinet.Veterinaire.Specy;

import java.rmi.server.RMIClassLoader;

public class AnimalWrapperServer extends Animal {

    // L'objectif de cette classe enveloppante est d'acceder
    // aux accesseurs et modificateurs de la classe animal partagee avec les clients du serveur RMI
    // L'idée est de se protéger des access non controllés faits du cote client des attributs
    // et méthode définies sur les classes partagées dans le codebase

//    public AnimalWrapperServer(String name) {
//        super(name);
//    }
//
//    public AnimalWrapperServer(String name, String masterName, String breed, Specy specy) {
//        super(name, masterName, breed, specy);
//    }



    public AnimalWrapperServer(String Id, String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) {
        super(Id, name, masterName, breed, specy, followUpFile);
    }

    public AnimalWrapperServer(Animal animal) {
      super(animal);
    }

  public String getAnimalID() {
        return super.getID();
  }

    public String getID() {
        return super.getID();
    }

    public void setID(String ID) {
         super.setID(ID);
    }

    @Override
    public String toString() {
        return "AnimalWrapperServer{"+
                super.toString()+"}";
    }
}


/*
*   public String getFullName() {
        return super.getFullName();
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getMasterName() {
        return  super.getMasterName();
    }

    public void setMasterName(String masterName) {
        super.setMasterName(masterName);
    }

    public String getBreed() {
        return super.getBreed();
    }

    public void setBreed(String breed) {
        super.setBreed(breed);
    }


    public Specy getSpecy() {
        return super.getSpecy();
    }

    public void setSpecy(Specy specy) {
        super.setSpecy(specy);
    }

    public FollowUpFile getFollowUpFile() {
        return this.getFollowUpFile();
    }

    public void setFollowUpFile(FollowUpFile followUpFile) {
        super.setFollowUpFile(followUpFile);
    }*/