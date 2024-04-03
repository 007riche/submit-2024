package com.todo.company.hai704.restapi.service.exceptionhandlers.exceptions;

public class ConnectionFailure extends Exception{
    public ConnectionFailure() {
    }

    public ConnectionFailure(String message) {
        super(message);
    }
}
