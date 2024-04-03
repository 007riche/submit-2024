package com.todo.company.hai704.restapi.Client.processors;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public abstract class Processor<T> {

    protected String messeage;
    protected String exceptionMesseage;
    protected BufferedReader inputReader;
    protected Method parser;
    protected Predicate<String> isValid;
    protected T parameter;

    public Processor(BufferedReader inputReader) {
        this.inputReader = inputReader;
//        setMesseage("");
        setValidityCriterion();
        setParser();
    }
    public abstract void setMesseage(String messeage);
    public abstract void setExceptionMesseage(String exceptionMesseage);

    protected abstract void setValidityCriterion();

    protected abstract void setParser();

    public T process() throws IOException {
        System.out.print(messeage);
        String userInput = inputReader.readLine();
        while (!isValid.test(userInput)) {
            System.out.println(exceptionMesseage);
            System.out.print(messeage);
            userInput = this.inputReader.readLine();
        }
try {
    parameter = (T) parser.invoke(null, userInput);
} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
    exception.printStackTrace();
}
        return parameter;
    }


}
