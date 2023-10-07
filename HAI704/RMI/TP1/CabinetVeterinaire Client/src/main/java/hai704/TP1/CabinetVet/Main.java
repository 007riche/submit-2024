package hai704.TP1.CabinetVet;

import hai704.TP1.CabinetVeterinaire.IAnimal;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);

            // For question 1, test
            IAnimal iAnimalStub = (IAnimal) registry.lookup("IAnimal");
            System.err.println("Retrieved animal: "+iAnimalStub.getFullName());
            // End Q1

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}