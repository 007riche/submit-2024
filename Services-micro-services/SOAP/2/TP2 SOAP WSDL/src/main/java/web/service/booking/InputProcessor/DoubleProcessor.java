package web.service.booking.InputProcessor;

import java.io.BufferedReader;

public class DoubleProcessor extends Processor<Double>{

    public DoubleProcessor(BufferedReader inputReader) {
        super(inputReader);
        this.messeage = "Veuillez entrer un nombre: ";

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
                Double value = Double.parseDouble(userIn);
                return value instanceof Double;
            } catch (NumberFormatException e) {
                this.setExceptionMesseage("\nCECI N'EST PAS UN NOMBRE\n");
                return false;
            }
        };
    }

    @Override
    protected void setParser() {
        try {
            parser = Double.class.getMethod("parseDouble", String.class);
        } catch (SecurityException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }
}
