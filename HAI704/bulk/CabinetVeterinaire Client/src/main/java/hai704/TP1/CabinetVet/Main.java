package hai704.TP1.CabinetVet;

import hai704.TP1.CabinetVeterinaire.Interface.IAnimal;
import hai704.TP1.CabinetVeterinaire.Interface.ISpecy;

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
            ISpecy iSpecy = (ISpecy) registry.lookup("ISpecy");
            System.err.println("Retrieved animal: "+iAnimalStub.getFullName());
            // End Q1

            // 1-Q4.
            System.out.println(iAnimalStub.getSpecyName());
            System.out.println(iAnimalStub.getAverageLifeSpanInDays());

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}