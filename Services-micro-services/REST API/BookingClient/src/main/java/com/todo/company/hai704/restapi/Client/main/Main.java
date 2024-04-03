package com.todo.company.hai704.restapi.Client.main;

import com.todo.company.hai704.restapi.Client.BookingClientApplication;
import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.gui.components.application.MainFrame;
import com.todo.company.hai704.restapi.Client.gui.components.application.SplashScreen;
import com.todo.company.hai704.restapi.Client.processors.DoubleProcessor;
import com.todo.company.hai704.restapi.Client.processors.Integerprocessor;
import com.todo.company.hai704.restapi.Client.processors.StringOnlyProcessor;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

import static com.todo.company.hai704.restapi.Client.utilpack.CustomUtility.getAvailablePort;


@Service
public class Main {
    static Agency running;
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    static String filename = "Agency.txt";
    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    static Integerprocessor intProcessor;
    static DoubleProcessor doubleProcessor;
    static StringOnlyProcessor stringOnlyProcessor;
    static URL url;


    static String baseUrl = "http://localhost:";
    private static RestTemplate publisherProxy = new RestTemplate();
    static private int PORT=-1;
    private static String URI_AGENCY_POST=baseUrl+"8085"+"/publisher/api/comparison"+"/agency";
    private final PersistenecService persistenecService;


    @Autowired
    public Main(PersistenecService persistenecService) {
        this.persistenecService= persistenecService;
    }


    @Bean
    @DependsOnDatabaseInitialization
    public  void run() {

        int choice=-1;


        running = Agency.getInstance();
        intProcessor = new Integerprocessor(inputReader);
        doubleProcessor= new DoubleProcessor(inputReader);
        stringOnlyProcessor= new StringOnlyProcessor(inputReader);;

        url = Main.class.getResource(filename);
        if (url == null ) {

            try {
                url = Main.class.getResource("");

                 initializeServiceNode();
                System.out.println("Running Agency: "+running);

                File file = new File(url.toURI().getPath() + filename);
                FileWriter writer  = new FileWriter(file);
                BufferedWriter buffer = new BufferedWriter(writer);
                String agName = running.getAgencyName();

                buffer.write(agName);
                buffer.newLine();

                String agCity = running.getCity();
                buffer.write(agCity);
                buffer.newLine();
                buffer.close();

                running.setAGENCY_DB_NAME();

                Agency toPublish = new Agency();

                toPublish.setAgencyName(running.getAgencyName());
                toPublish.setCity(running.getCity());
                toPublish.setDiscoveryURL(baseUrl+running.getPort());

                boolean node = Boolean.TRUE.equals(publisherProxy.postForObject(URI_AGENCY_POST, toPublish, Boolean.class));
                if (node) {
                    logger.warn("Publication de l'agence dans le service Publicateur"+toPublish);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                System.out.println("Bad File's URI ");
            }

        }
        else {
            System.out.println("Redémarrage du agence encours ...");

            Agency retrieved = persistenecService.findAllAgencies().get(0);
            running.setId(retrieved.getId());
            running.setAgencyName(retrieved.getAgencyName());
            running.setCity(retrieved.getCity());
            running.setDiscoveryURL(retrieved.getDiscoveryURL());
            running.setAGENCY_DB_NAME(retrieved.getAGENCY_DB_NAME());

            System.out.println("Reloaded running Agency: "+running);
        }

        do {
            System.out.println("\n\nDEMARRAGE DE L'INTERFACE UTILISATEUR...");
            System.out.println("1.Demarrer en CLI");
//            System.out.println("2. Demarrer en GUI");
            System.out.println("0. Quitter");
            intProcessor.setExceptionMesseage("Ceci n'est pas un chiffre entier");
            intProcessor.setMesseage("\n Faites votre choix: ");
            try {
                choice = intProcessor.process();

            } catch (IOException e) {
                intProcessor.setMesseage("");
                e.printStackTrace();
            }

            switch (choice) {
                case 0:
                    System.out.println("Fin du programme");
                    System.exit(0);
                    break;
                case 1:
//                    if (url == null ) {
                        SplashScreen loading = new SplashScreen();
                        boolean loaded= loading.main();

                        if (loaded) {
                            loading.close();
                            loading.setVisible(false);
                        }

                    SwingUtilities.invokeLater(() -> {
                        MainFrame GUI = new MainFrame(persistenecService, running.getAgencyName()+" "+running.getCity());
                        GUI.setVisible(true);
                    });
                    break;
                default:
                    System.out.println("\n\nVEUILLEZ FAIRE UN CHOIX COHERENT LA PROCHAINE FOIS: ");
                    break;
            }

        } while (choice<=0 || choice>=2);

    }

    public Agency initializeServiceNode() {
        Agency agency = Agency.getInstance();
        String stringIput="";
        System.out.println("\t\t\t\t INITIALISATION D'UN NOUVEAU NOEUD AGENCE");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Hotel name
        do {
            System.out.println("Quel nom voulez-vous donner à l'Agence?: ");
            Scanner scanner = new Scanner(System.in);
            stringIput = scanner.nextLine();
        } while (stringIput.trim().isBlank());
        agency.setAgencyName(stringIput);
        stringIput = "";

        do {
            stringOnlyProcessor.setMesseage("Dans quel ville l'agence est implantée?: ");
            stringOnlyProcessor.setExceptionMesseage("Vous n'avez rien saisi");
            try {
                stringIput = stringOnlyProcessor.process();
            } catch (IOException e) {
                stringOnlyProcessor.setMesseage("");
                e.printStackTrace();
            }
        } while (stringIput.trim().isBlank());
        agency.setCity(stringIput);

        agency.setAGENCY_DB_NAME();
        stringIput = "";

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Quelle est l'url du service de publication?:");
            stringIput = scanner.nextLine().trim();
        } while (stringIput.trim().isBlank());

        agency.setDiscoveryURL(stringIput);


        persistenecService.saveAgency(agency); // Persistance in database;
        return agency;
    }

    public static String transformStarsToString(Double stars) {
        String generatedStars="";
        String filledStar = "\u2605";
        String outlinedStar = "\u2606";

        generatedStars += filledStar;
        for (int i = 1; i <5 ; i++) {
            stars-=1;
            if (stars>=1.0) {
                generatedStars+=filledStar;
            }
            else {
                generatedStars+=outlinedStar;
            }
        }

        return generatedStars;
    }
}

