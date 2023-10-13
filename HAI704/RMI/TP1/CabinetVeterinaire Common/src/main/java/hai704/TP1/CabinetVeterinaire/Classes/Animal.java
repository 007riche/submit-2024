package hai704.TP1.CabinetVeterinaire.Classes;

import hai704.TP1.CabinetVeterinaire.Classes.FollowUpFile;
import hai704.TP1.CabinetVeterinaire.Classes.Specy;

import java.io.Serializable;

public class Animal implements Serializable {
    private String ID; //Auto-generated, but not used
    private String name;
    private String masterName;
    private String breed;
//    private String specy;
    private Specy specy;
    private FollowUpFile followUpFile;



    public Animal(String name) {
        this.name = name;
    }

    public Animal(String name, String masterName, String breed, Specy specy) {
        this.name = name;
        this.masterName = masterName;
        this.breed = breed;
        this.specy = specy;
    }

    public Animal(String name, String masterName, String breed, Specy specy, FollowUpFile followUpFile) {
        this.name = name;
        this.masterName = masterName;
        this.breed = breed;
        this.specy = specy;
        this.followUpFile = followUpFile;
    }

    protected String getFullName() {
        return this.name+" "+this.masterName;
    }

    protected String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getMasterName() {
        return this.masterName;
    }

    protected void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    protected String getBreed() {
        return this.breed;
    }

    protected void setBreed(String breed) {
        this.breed = breed;
    }

    // In first version
//    public String getSpecy() {
//        return this.specy;
//    }
//
//    public void setSpecy(String specie) {
//        this.specy = specie;
//    }


    protected Specy getSpecy() {
        return specy;
    }

    protected void setSpecy(Specy specy) {
        this.specy = specy;
    }

    protected FollowUpFile getFollowUpFile() {
        return this.followUpFile;
    }

    protected void setFollowUpFile(FollowUpFile followUpFile) {
        this.followUpFile = followUpFile;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", masterName='" + masterName + '\'' +
                ", breed='" + breed + '\'' +
                ", specie='" + specy + '\'' +
                ", followUpFile=" + followUpFile +
                '}';
    }



}
