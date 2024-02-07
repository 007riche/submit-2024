package Serveur.hai704.TP1.Cabinet.Vetetrinaire.server;

//import Common.hai704.TP1.Cabinet.Veterinaire.IAnimal;
//import Common.hai704.TP1.Cabinet.Veterinaire.ICabinet;
//import Common.hai704.TP1.Cabinet.Veterinaire.ISpecy;
import Common.hai704.TP1.Cabinet.Veterinaire.IAnimal;
import Common.hai704.TP1.Cabinet.Veterinaire.ICabinet;
import Common.hai704.TP1.Cabinet.Veterinaire.ISpecy;
import Serveur.hai704.TP1.Cabinet.Vetetrinaire.service.IAnimalImplementation;
import Serveur.hai704.TP1.Cabinet.Vetetrinaire.service.ICabinetImplementation;
import Serveur.hai704.TP1.Cabinet.Vetetrinaire.service.ISpecyImplementation;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClassLoader;
import java.util.Scanner;

public class Server {
    public static String hostWithoutProto;
//    public static <IAnimal, ISpecy, ICabinet> void main(String[] args) {
public static  void main(String[] args) {
//        String CodeBaseJARURL="";

        final Integer DEFAULTPORT = 1099;
        int userDefinedPort=1099;


        Registry registry;
        try {
            IAnimalImplementation animalImplementationObj = new IAnimalImplementation();
            ISpecyImplementation specyObj = new ISpecyImplementation();
            ICabinetImplementation cabinetImplementation = new ICabinetImplementation();

            System.out.println("|||||||  ||||||||  ||      ||  ||||||||  ||||||");
            System.out.println("||       ||        ||      ||  ||        ||    ||");
            System.out.println("|||||||  ||||       ||    ||   ||||      ||||||");
            System.out.println("     ||  ||          ||  ||    ||        ||  ||");
            System.out.println("|||||||  ||||||||     ||||     ||||||||  ||    ||    ||  ||   || ");
            Scanner scanner = new Scanner(System.in);


// not a viable way of implementing
//            System.out.println("Entrez l'URL le chemin absolu du repertoire: ");
//
//            String input = "C:\\Users\\richard\\Documents\\Master 1 20232024\\HAI704\\RMI\\TP1\\CabinetVeterinaire Serveur\\target\\CabinetVeterinaireServeur-1.0-SNAPSHOT.jar";
//            ///scanner.nextLine();
//            input = input.trim();
//            input =  ((input.endsWith("/") || input.endsWith("\\"))) ? input.substring(0, input.length() -1): input; // sans le slash de fin
//            URL url = new URL("file://"+input+"/CabinetVeterinaire_Common-final.jar");
//
//            CodeBaseJARURL = String.valueOf(url);
//
//            System.out.println(CodeBaseJARURL);
//
//            System.setProperty(	"java.rmi.server.codebase",	CodeBaseJARURL	);

            System.out.println("Before Input: "+userDefinedPort);

            registry = LocateRegistry.createRegistry(userDefinedPort);


// not a viable way of implementing
//            Class IAnimal = RMIClassLoader.loadClass(CodeBaseJARURL,"IAnimal");
//
//            Class ISpecy = RMIClassLoader.loadClass(CodeBaseJARURL,"ISpecy");
//
//            Class ICabinet = RMIClassLoader.loadClass(CodeBaseJARURL,"ICabinet");

            // Publishing IAnimal
            registry.bind("IAnimal", animalImplementationObj);
//            System.err.println("Server UP ...");
            IAnimal pub = (IAnimal) registry.lookup("IAnimal");

            // Publishing ISpecy
            registry.bind("ISpecy", specyObj);
            System.err.println("Server UP ...");
            ISpecy pubSpecy = (ISpecy) registry.lookup("ISpecy");

            // Publishing ICabinet
            registry.bind("ICabinet", cabinetImplementation);
            System.err.println("Server UP ...");
            ICabinet pubCabinet = (ICabinet) registry.lookup("ICabinet");

            System.err.println(pub);
            System.err.println(pubSpecy);
            System.err.println(pubCabinet);

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

//<dependency>
//<groupId>hai704.TP1.CabinetVeterinaire</groupId>
//<artifactId>CabinetVeterinaireCommon</artifactId>
//<version>final</version>
//<scope>compile</scope>
//</dependency>


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