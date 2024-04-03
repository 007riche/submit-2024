//package com.todo.company.hai704.restapi.service.InputProcessor;
//
//import com.todo.company.hai704.restapi.service.exceptionhandlers.exceptions.ConnectionFailure;
//
//import java.io.BufferedReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionRocessor extends Processor<Connection>{
//
//    private String jdbcUrl;
//    private String username;
//    private String password;
//
//
//    public ConnectionRocessor(BufferedReader inputReader,  String username, String password) {
//
//        super(inputReader);
//    }
//
//
//
//
//
//    @Override
//    public void setMesseage(String messeage) {
//        this.messeage = messeage;
//    }
//
//    @Override
//    public void setExceptionMesseage(String exceptionMesseage) {
//        this.exceptionMesseage = exceptionMesseage;
//    }
//
//    @Override
//    protected void setValidityCriterion() {
//        isValid = (this.jdbcUrl, this.username, this.password) -> {};
//    }
//
//    @Override
//    protected void setParser() {
//
//    }
//
//    @Override
//    public Connection process() {
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
//            return connection;
//        } catch (SQLException e) {
//            System.err.println("Impossible se connecter avec les informations fournies");
//            return null;
//        }
//    }
//
//}
