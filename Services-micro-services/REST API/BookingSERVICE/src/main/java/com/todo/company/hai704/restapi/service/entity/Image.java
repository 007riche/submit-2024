package com.todo.company.hai704.restapi.service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "IMAGES")
public class Image {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "imgName", nullable = false, unique = true, length = 255)
    private String imgName;

    @Lob
    @Column(name = "IMG", nullable = false)
    private byte[] img;

//    @OneToOne
//    @JoinColumn(name = "image")
//    private Room room;
    @JsonManagedReference
    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "image")
    private Room room;

    public Image() {
    }

    public Image(String imgName, byte[] img) {
        this.imgName = imgName;
        this.img = img;
    }

    // Getters and Setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(getId(), image.getId()) && Objects.equals(getImgName(), image.getImgName()) && Arrays.equals(getImg(), image.getImg()) && Objects.equals(room, image.room);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getImgName(), room);
        result = 31 * result + Arrays.hashCode(getImg());
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "Id=" + Id +
                ", imgName='" + imgName + '\'' +
                ", img=" + Arrays.toString(img) +
                ", room=" + room +
                '}';
    }
}
