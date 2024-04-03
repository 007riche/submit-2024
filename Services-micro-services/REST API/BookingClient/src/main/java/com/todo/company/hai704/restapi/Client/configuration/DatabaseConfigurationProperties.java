package com.todo.company.hai704.restapi.Client.configuration;

import org.springframework.stereotype.Service;

@Service
public class DatabaseConfigurationProperties {
    private String hostedProtocolURLEndingWithPortNumber;
    private String DBName;
    private String username;
    private String password;
    private String driverClassName;

    public DatabaseConfigurationProperties() {
    }

    public DatabaseConfigurationProperties(String hostedProtocolURLEndingWithPortNumber,
                                           String DBName, String username, String password) {
        this.hostedProtocolURLEndingWithPortNumber = hostedProtocolURLEndingWithPortNumber;
        this.DBName = DBName;
        this.username = username;
        this.password = password;
    }

    public String getHostedProtocolURLEndingWithPortNumber() {
        return hostedProtocolURLEndingWithPortNumber;
    }

    public void setHostedProtocolURLEndingWithPortNumber(String hostedProtocolURLEndingWithPortNumber) {
        this.hostedProtocolURLEndingWithPortNumber = hostedProtocolURLEndingWithPortNumber;
    }

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
