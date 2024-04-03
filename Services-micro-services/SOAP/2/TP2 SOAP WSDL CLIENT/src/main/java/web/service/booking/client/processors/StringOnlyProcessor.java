package web.service.booking.client.processors;

import web.service.booking.client.exceptions.StringOnlyException;

import java.io.BufferedReader;
import java.io.IOException;

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
            userIn = userIn.replaceAll("' '|-", "");
            if (!userIn.chars().allMatch(Character::isLetter))
                try {
                    throw new StringOnlyException();
                } catch (StringOnlyException e) {
                    setExceptionMesseage("NE CONTIENT PAS QUE DES LETTRES");
                }
            return !userIn.isBlank() && userIn.chars().allMatch(Character::isLetter);
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
