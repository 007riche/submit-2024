package hai704.TP1.CabinetVet.internal.service.models;

import hai704.TP1.CabinetVeterinaire.Classes.Animal;
import hai704.TP1.CabinetVeterinaire.Classes.FollowUpFile;
import hai704.TP1.CabinetVeterinaire.Classes.Specy;

public class AnimalWrapperServer extends Animal {

    public AnimalWrapperServer(String name) {
        super(name);
    }

    public AnimalWrapperServer(String name, String masterName, String breed, Specy specy) {
        super(name, masterName, breed, specy);
    }

    public AnimalWrapperServer(String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) {
        super(name, masterName, breed, specy, followUpFile);
    }

    public String getFullName() {
        return super.getFullName();
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getMasterName() {
        super.getMasterName();
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
    }

}
