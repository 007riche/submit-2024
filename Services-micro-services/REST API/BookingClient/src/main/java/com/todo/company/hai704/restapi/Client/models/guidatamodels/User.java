package com.todo.company.hai704.restapi.Client.models.guidatamodels;

public class User {
    private String identifier;
    private String password;

    public User() {
    }

    public User(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
