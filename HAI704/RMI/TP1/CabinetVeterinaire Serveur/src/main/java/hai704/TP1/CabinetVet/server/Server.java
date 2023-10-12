package hai704.TP1.CabinetVet.server;

import hai704.TP1.CabinetVet.internal.service.IAnimalImplementation;
import hai704.TP1.CabinetVet.internal.service.ISpecyImplementation;
import hai704.TP1.CabinetVeterinaire.Interface.IAnimal;
import hai704.TP1.CabinetVeterinaire.Interface.ISpecy;


import java.io.File;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {
    public static String hostWithoutProto;
    public static void main(String[] args) {
        final Integer DEFAULTPORT = 1099;
        int userDefinedPort=1099;



        Registry registry;
        try {
            IAnimalImplementation animalImplementationObj = new IAnimalImplementation();
            ISpecy specyObj = new ISpecyImplementation();
            System.out.println("-----  ------ \\        / -----  ------ ");
            System.out.println("|      |       \\      /  |      |     | ");
            System.out.println("-----  |___     \\    /   -----  |___ /    ");
            System.out.println("    |  |         \\  /    |      |   \\");
            System.out.println("-----  |_____     \\/     |_____ |    \\");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Before Input: "+userDefinedPort);
//            System.out.println("Avez-vous un numero de port pour le registre? ( 0 ou Touche \"Entrée\" sinon):");
//             if (scanner.hasNextLine()) {
//                 userDefinedPort = scanner.nextInt();
//                 System.out.println("Between Input: "+userDefinedPort);
//                 registry = LocateRegistry.getRegistry(userDefinedPort);
//                 try {
//                     IAnimal pub = (IAnimal) registry.lookup("IAnimal");
//                     System.err.println("Existing and running registry on port: "+userDefinedPort);
//                 } catch (NotBoundException e) {
//
//                 }
//
//             }


//             else {
//                 scanner.close();
//                 registry = LocateRegistry.getRegistry(DEFAULTPORT);
//                 try {
//                     IAnimal pub = (IAnimal) registry.lookup("IAnimal");
//                     System.err.println("Existing and running registry on port: "+userDefinedPort);
//                 } catch (NotBoundException e) {
//
//                 }
//             }
//             scanner.close(); registry = LocateRegistry.getRegistry(userDefinedPort);
//            System.out.println("Suite");
//            System.out.println("After Input: "+userDefinedPort);
//            registry = LocateRegistry.getRegistry(userDefinedPort);
//            if(registry==null && userDefinedPort>0){
//                registry = LocateRegistry.createRegistry(userDefinedPort);
//                System.out.println("No existing registry on the port "+userDefinedPort);
//                System.out.println("trying to launch registry on the port "+userDefinedPort);
//            }
//            else {
//                registry = LocateRegistry.createRegistry(DEFAULTPORT);
//                System.out.println("Neither existing registry  on the port "+DEFAULTPORT);
//                System.out.println("trying to launch registry on the port "+DEFAULTPORT);
//            }

            registry = LocateRegistry.createRegistry(userDefinedPort);

            // Publishing IAnimal
            registry.bind("IAnimal", animalImplementationObj);
//            System.err.println("Server UP ...");
            IAnimal pub = (IAnimal) registry.lookup("IAnimal");

            // Publishing ISpecy
            registry.bind("ISpecy", specyObj);
            System.err.println("Server UP ...");
            ISpecy pubSpecy = (ISpecy) registry.lookup("ISpecy");

            System.err.println(pub);
            System.err.println(pubSpecy);

            // 1-Q2. Security policy
            URL fileUrl = Server.class.getClassLoader().getResource("security.policy");
            assert fileUrl != null;
            String filePath = new File(fileUrl.getFile()).getAbsolutePath();
//            System.out.println("file path: "+filePath);
            System.setProperty("java.security.policy", filePath);

        } catch (RemoteException e) {
//            throw new RuntimeException(e);
            System.err.println(e.toString());
        } catch (AlreadyBoundException e) {
//            throw new RuntimeException(e);
            System.err.println(e.toString());
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
