package com.todo.company.hai704.restapi.service.bookingpublisherservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class PublisherNotFoundExceptionAdvice {
    @ExceptionHandler(PublisherNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundExceptionAdvice(PublisherNotFoundException e) {
        return String.format("{\"%s\": \"%s\"}", "Erreur", e.getMessage());
    }
}
