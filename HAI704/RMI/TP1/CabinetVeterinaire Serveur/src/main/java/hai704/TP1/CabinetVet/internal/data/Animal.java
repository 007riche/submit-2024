package hai704.TP1.CabinetVet.internal.data;

import java.io.Serializable;

public class Animal implements Serializable {
    private String name;
    private String masterName;
    private String breed;
    private String specy;
    private FollowUpFile followUpFile;



    public Animal(String name) {
        this.name = name;
    }

    public Animal(String name, String masterName, String breed, String specie) {
        this.name = name;
        this.masterName = masterName;
        this.breed = breed;
        this.specy = specie;
    }

    public Animal(String name, String masterName, String breed, String specie, FollowUpFile followUpFile) {
        this.name = name;
        this.masterName = masterName;
        this.breed = breed;
        this.specy = specie;
        this.followUpFile = followUpFile;
    }

    public String getFullName() {
        return this.name+" "+this.masterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecie() {
        return specy;
    }

    public void setSpecie(String specie) {
        this.specy = specie;
    }

    public FollowUpFile getFollowUpFile() {
        return followUpFile;
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
