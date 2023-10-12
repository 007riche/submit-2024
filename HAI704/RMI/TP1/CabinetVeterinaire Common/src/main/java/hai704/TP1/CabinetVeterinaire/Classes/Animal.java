package hai704.TP1.CabinetVeterinaire.Classes;

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

    public String getFullName() {
        return this.name+" "+this.masterName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterName() {
        return this.masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
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


    public Specy getSpecy() {
        return specy;
    }

    public void setSpecy(Specy specy) {
        this.specy = specy;
    }

    public FollowUpFile getFollowUpFile() {
        return this.followUpFile;
    }

    public void setFollowUpFile(FollowUpFile followUpFile) {
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
