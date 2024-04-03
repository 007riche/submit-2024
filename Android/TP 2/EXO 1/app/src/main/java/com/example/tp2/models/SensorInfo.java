package com.example.tp2.models;

import java.io.Serializable;

public class SensorInfo implements Serializable {

    private String name;
    private String type;
    private String version;

    public SensorInfo() {
    }

    public SensorInfo(String name, String type, String version) {
        this.name = name;
        this.type = type;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
