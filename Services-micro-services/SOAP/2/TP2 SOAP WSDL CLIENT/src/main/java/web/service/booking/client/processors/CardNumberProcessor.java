package web.service.booking.client.processors;

import web.service.booking.client.exceptions.StringOnlyException;

import java.io.BufferedReader;
import java.io.IOException;

public class CardNumberProcessor extends Processor<String>{
    public CardNumberProcessor(BufferedReader inputReader) {
        super(inputReader);
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
            userIn = userIn.replaceAll(" ","");
            if (!userIn.chars().allMatch(Character::isDigit))
                try {
                    throw new StringOnlyException();
                } catch (StringOnlyException e) {
                    setExceptionMesseage("LE NUMERO DE CARTE DOIT CONTENIR UNQUEMENT QUE DES CHIFFRES");
                }
            return !userIn.isBlank() && userIn.length()>0 && userIn.length()<17 ;
        };
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

    @Override
    protected void setParser() {

    }
}
