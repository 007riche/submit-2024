package web.service.booking.InputProcessor;

import java.io.BufferedReader;

public class Integerprocessor extends Processor<Integer> {

    public Integerprocessor(BufferedReader inputReader) {
        super(inputReader);
this.messeage = "Veuillez entrer un chiffre: ";
this.exceptionMesseage = "Ceci n'est pas un chiffre";
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
    try {
        Integer value = Integer.parseInt(userIn);
        return value instanceof Integer;
    } catch (NumberFormatException e) {
        return false;
    }
};
    }

    @Override
    protected void setParser() {
try {
    parser = Integer.class.getMethod("parseInt", String.class);
} catch (SecurityException | NoSuchMethodException exception) {
    exception.printStackTrace();
}
    }
}
