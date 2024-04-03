package Client.hai704.TP1.Cabinet.Veterinaire;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.app.Application;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.splashScreen.SplashScreen;
import Common.hai704.TP1.Cabinet.Veterinaire.Animal;
import Common.hai704.TP1.Cabinet.Veterinaire.IAnimal;
import Common.hai704.TP1.Cabinet.Veterinaire.ICabinet;
import Common.hai704.TP1.Cabinet.Veterinaire.ISpecy;


import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Main {
    public static void main(String[] args)  {



        try {


            URL fileUrl = Main.class.getClassLoader().getResource("security.policy");
//            assert fileUrl != null;

//            String filePath = new File(fileUrl.getFile()).getAbsolutePath();
            String path = Paths.get(fileUrl.toURI()).toAbsolutePath().toString();
            System.out.println("file path: "+path);
            System.setProperty("java.security.policy", path);
//
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }


            Registry registry = LocateRegistry.getRegistry(/*1099*/);

            System.out.println("Registry: "+registry);
            // For question 1, test
            IAnimal iAnimalStub = (IAnimal) registry.lookup("IAnimal");
            ISpecy iSpecy = (ISpecy) registry.lookup("ISpecy");
            System.err.println("Retrieved animal: "+iAnimalStub.getFullName());
            // End Q1

            // 1-Q4.
            System.out.println(iAnimalStub.getSpecyName());
            System.out.println(iAnimalStub.getAverageLifeSpanInDays());


            ICabinet pubCabinet = (ICabinet) registry.lookup("ICabinet");
            List<Animal> testRecup = pubCabinet.getAllPatients();


            System.out.println("From the main: "+ testRecup.toArray());
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
                            Application window = new Application(/*pubCabinet*/);
                            window.setService(pubCabinet);
                            window.frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


        } catch ( Exception e/*RemoteException | NotBoundException e*/) {
            e.printStackTrace();
//            System.out.println("Errrrrrr");
        }

    }
}