package com.todo.company.hai704.restapi.service.h2.entities;


import com.todo.company.hai704.restapi.service.entity.Hotel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PARTNERS")
public class H2Partnership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idAgency", nullable = false,  length = 255, unique = true)
    private String idAgency;
    @Column(name = "agencyName", nullable = false, length = 255)
    private String agencyName;
    @Column(name = "loginId", nullable = false,  length = 255, unique = true)
    private String loginId;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "discountRate", nullable = false, length = 255)
    private Double discountRate;

    // Entity Mapping
//    @ManyToOne
//    @JoinColumn(name = "hotelRoom_id")
    @Transient
    private H2Hotel runningNode;

    public H2Partnership(String idAgency, String agencyName, String loginId, String password, Double discountRate) {
        this.idAgency = idAgency;
        this.agencyName = agencyName;
        this.loginId = loginId;
        this.password = password;
        this.discountRate = discountRate;
    }

    public H2Partnership() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }



    public H2Hotel getRunningNode() {
        return runningNode;
    }

    public void setRunningNode(H2Hotel runningNode) {
        this.runningNode = runningNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        H2Partnership that = (H2Partnership) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getIdAgency(), that.getIdAgency()) && Objects.equals(getAgencyName(), that.getAgencyName()) && Objects.equals(getLoginId(), that.getLoginId()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getDiscountRate(), that.getDiscountRate()) && Objects.equals(getRunningNode(), that.getRunningNode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdAgency(), getAgencyName(), getLoginId(), getPassword(), getDiscountRate(), getRunningNode());
    }

    @Override
    public String toString() {
        return "Partnership{" +
                "id=" + id +
                ", idAgency='" + idAgency + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", discountRate=" + discountRate +
                ", runningNode=" + runningNode +
                '}';
    }
}
