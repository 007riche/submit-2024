package com.todo.company.hai704.restapi.service.exceptionhandlers.exceptions;

public class InvalidCardNumberException extends Exception {
    public InvalidCardNumberException() {
    }

    public InvalidCardNumberException(String message) {
        super(message);
    }
}
