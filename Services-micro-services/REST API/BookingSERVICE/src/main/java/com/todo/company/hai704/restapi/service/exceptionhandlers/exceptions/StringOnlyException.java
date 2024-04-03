package com.todo.company.hai704.restapi.service.exceptionhandlers.exceptions;

public class StringOnlyException extends Exception{
    public StringOnlyException() {
    }

    public StringOnlyException(String message) {
        super(message);
    }
}
