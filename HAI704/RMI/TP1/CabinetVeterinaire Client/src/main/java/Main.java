
import Common.hai704.TP1.Cabinet.Veterinaire.IAnimal;
import Common.hai704.TP1.Cabinet.Veterinaire.ICabinet;
import Common.hai704.TP1.Cabinet.Veterinaire.ISpecy;
import hai704.TP1.CabinetVeterinaireClient.GUI.app.Application;
import hai704.TP1.CabinetVeterinaireClient.GUI.component.splashScreen.SplashScreen;

import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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


            ICabinet pubCabinet = (ICabinet) registry.lookup("ICabinet");
            SplashScreen loading = new SplashScreen();
            Thread.sleep(1000);
            boolean loaded= loading.main();

            if (loaded) {
                loading.close();
                loading.setVisible(false);
            }

        /**
         * Launch the application.
         */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application window = new Application(pubCabinet);
//                    window.setService(pubCabinet);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}