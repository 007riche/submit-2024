package com.todo.company.hai704.restapi.Client.processors;


import java.io.BufferedReader;

public class RangedIntegerProcessor extends Processor<Integer>{
    private int lowerBound;
    private int upperBound;
    public RangedIntegerProcessor(BufferedReader inputReader) {
        super(inputReader);
        this.lowerBound = 0;
        this.upperBound = 0;
        this.messeage = "Faite votre choix: ";
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
                    if(!(value >= lowerBound && value <= upperBound))
                    {
                        this.setExceptionMesseage("\nCHOIX INVALIDE, VEUILLEZ FAIRE UN CHOIX VALIDE\n");
                    }
                return (value instanceof Integer && (value >= lowerBound && value <= upperBound));
            } catch (NumberFormatException e) {
                this.setExceptionMesseage("\nCECI N'EST PAS UN CHIFFRE\n");
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

    public void setValidityCriterion(int lowerBound, int upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
}

