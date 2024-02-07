package Serveur.hai704.TP1.Cabinet.Vetetrinaire.service;

import Common.hai704.TP1.Cabinet.Veterinaire.ISpecy;
import Common.hai704.TP1.Cabinet.Veterinaire.Specy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ISpecyImplementation extends UnicastRemoteObject implements ISpecy {

    private List<Specy> specyList = new ArrayList<Specy>();
    private  Specy uniqueSpecyTest = new Specy("Dog");

    public ISpecyImplementation() throws RemoteException {
        this.uniqueSpecyTest = new Specy("Dog", 1825.0);
    }


    @Override
    public Specy getSpecyByName(String name) throws RemoteException {
        return null;
    }

    @Override
    public String getSpecyName() throws RemoteException {
        return null;
    }

    @Override
    public Double getAverageLifeSpanInDays(String specyName) throws RemoteException {
        return null;
    }

    @Override
    public Double getAverageLifeSpanInDays() throws RemoteException {
        return null;
    }

    @Override
    public Specy getSpecyByNameTest(String name) throws RemoteException {
        if (name.contentEquals("Dog"))
            return this.uniqueSpecyTest;
        return null;
    }

    @Override
    public String getSpecyNameTest() throws RemoteException {
        return this.uniqueSpecyTest.getName();

    }

    @Override
    public Double getAverageLifeSpanInDaysTest(String specyName) throws RemoteException {
        if(specyName.contentEquals("Dog"))
            return this.uniqueSpecyTest.getAverageLifeSpanInDays();
        return -1.0;
    }

    @Override
    public Double getAverageLifeSpanInDaysTest() throws RemoteException {
        return this.uniqueSpecyTest.getAverageLifeSpanInDays();
//        return 0.0;
    }

}
