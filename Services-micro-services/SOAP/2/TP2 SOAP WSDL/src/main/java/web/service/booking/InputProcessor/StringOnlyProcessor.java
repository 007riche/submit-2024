package web.service.booking.InputProcessor;


import web.service.booking.exceptions.StringOnlyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class StringOnlyProcessor extends Processor<String> {
    public StringOnlyProcessor(BufferedReader inputReader) {
        super(inputReader);
//        this.messeage = "";
//        this.exceptionMesseage = "";
    }

    @Override
    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }

    @Override
    public void setExceptionMesseage(String exceptionMesseage) {
        this.exceptionMesseage = exceptionMesseage;
    }

    @Override
    protected void setValidityCriterion() {
        isValid = userIn -> {
//            userIn = userIn.replaceAll("' '|-", "");
            if (userIn.trim().isBlank())
                try {
                    throw new StringOnlyException();
                } catch (StringOnlyException e) {
                    setExceptionMesseage("VEUILLEZ SAISIR QUELQUE CHOSE");
                }
            return !userIn.isBlank();
        };
    }

    @Override
    protected void setParser() {
    }

    @Override
    public String process() throws IOException {
        System.out.print(messeage);
        String userInput = inputReader.readLine();
        while (!isValid.test(userInput)) {
            System.out.println(exceptionMesseage);
            System.out.print(messeage);
            userInput = this.inputReader.readLine();
        }
        return userInput;
    }
}
