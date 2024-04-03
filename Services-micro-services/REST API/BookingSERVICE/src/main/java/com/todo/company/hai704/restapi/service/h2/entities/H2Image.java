package com.todo.company.hai704.restapi.service.h2.entities;

import com.todo.company.hai704.restapi.service.entity.Room;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "IMAGES")
public class H2Image {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "imgName", nullable = false,  unique = true, length = 255)
    private String imgName;

//    @Lob
//    @Column(name = "IMG", nullable = false)
//    private byte[] img;

//    @OneToOne
//    @JoinColumn(name = "image")
//    private H2Room room;

//    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
////    @JoinColumn(name = "image")
//    private H2Room room;

    public H2Image() {
    }

    public H2Image(String imgName, byte[] img) {
        this.imgName = imgName;
//        this.img = img;
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

//    public byte[] getImg() {
//        return img;
//    }
//
//    public void setImg(byte[] img) {
//        this.img = img;
//    }
//
//    public H2Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(H2Room room) {
//        this.room = room;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        H2Image h2Image = (H2Image) o;
        return Objects.equals(getId(), h2Image.getId()) && Objects.equals(getImgName(), h2Image.getImgName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImgName());
    }

    @Override
    public String toString() {
        return "H2Image{" +
                "Id=" + Id +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
