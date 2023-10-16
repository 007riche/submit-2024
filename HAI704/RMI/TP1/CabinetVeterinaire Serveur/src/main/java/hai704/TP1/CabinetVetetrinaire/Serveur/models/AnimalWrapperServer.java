package hai704.TP1.CabinetVetetrinaire.Serveur.models;

public class AnimalWrapperServer extends Animal {

    // L'objectif de cette classe enveloppante est d'acceder
    // aux accesseurs et modificateurs de la classe animal partagee avec les clients du serveur RMI
    // L'idee est de se proteger des access non controlles faits du cote client des attributs et methode definies sur les classes partagees

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