package web.service.booking.client;


import web.service.booking.client.data.BookingHistoryDAO;
import web.service.booking.client.data.ClientDAO;
import web.service.booking.client.data.JDBCConnectionFactory;
import web.service.booking.client.data.ServiceInfoDAO;
import web.service.booking.client.gui.components.application.MainFrame;
import web.service.booking.client.gui.components.application.SplashScreen;
import web.service.booking.client.models.Agency;
import web.service.booking.client.models.BookingAgency;
import web.service.booking.client.models.Client;
import web.service.booking.client.models.Service;
import web.service.booking.client.processors.DoubleProcessor;
import web.service.booking.client.processors.Integerprocessor;
import web.service.booking.client.processors.StringOnlyProcessor;
import web.service.booking.client.services.NodeServiceUsage;
import web.service.booking.client.services.Services;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Agency running;
    static BookingHistoryDAO bookingHistoryDAO;
    static ClientDAO clientDAO;
    static ServiceInfoDAO serviceDAO;

    private static NodeServiceUsage nodeServiceUsage ;

    static String SERVICES_DB = "SOAPSERVICEDB";
    static String DB_USER = "soap";
    static String DB_PASSWORD = "password";

    static String filename = "Agency.txt";
    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    static Integerprocessor intProcessor;
    static DoubleProcessor doubleProcessor;
    static StringOnlyProcessor stringOnlyProcessor;
    static URL url;

    public static void main(String[] args) {

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
                System.out.println("Running Name: "+running.getAgencyName());
                System.out.println("Running city: "+running.getCity());
                List<Service> allServices = new ArrayList<>();
                List<BookingAgency> bookingAgencies = new ArrayList<BookingAgency>();
                List<Client> agencyClient = new ArrayList<Client>();


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
//                Services externalAvailService = Services.getInstance(SERVICES_DB, DB_USER, DB_PASSWORD);
//                Services internalService = Services.getInstance();

                running.setAGENCY_DB_NAME();

                JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(running.getAGENCY_DB_NAME().trim().toUpperCase(),
                        "soap", "password", false);
                clientDAO = new ClientDAO(jdbcConnectionFactory.getConnectionDB());
                serviceDAO = new ServiceInfoDAO(jdbcConnectionFactory.getConnectionDB());
                serviceDAO.createTable();
                nodeServiceUsage = NodeServiceUsage.getInstance(serviceDAO);
                bookingHistoryDAO = new BookingHistoryDAO(jdbcConnectionFactory.getConnectionDB(), clientDAO);

                Services externalAvailService = Services.getInstance();
                System.out.println("Loading All services: "+externalAvailService.loadAvailableServices(serviceDAO));

                allServices = running.getAllServices();



                running.setUsedServices(allServices);
                running.setAgencyClients(agencyClient);
                running.setAllBoonking(bookingAgencies);

                for (Service service: allServices)   {
                    System.out.println("Service hotel name: "+service.getHotelName());
                    System.out.println("Service hotel's city: "+service.getHotelCity());
                    System.out.println("Service hotel's Booking URL: "+service.getHotelBookingServiceURL());
                    System.out.println("Service hotel's image URL: "+service.getHotelImageUrl());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                System.out.println("Bad File's URI ");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        else {
            System.out.println("Redémarrage du agence encours ...");
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(url.openStream()));
                String agencyName;
                String agencyCity;
                String line;

                for (int i = 1; i <=2 ; i++) {
                    line = buffer.readLine();
                    if (i==1 && !line.isBlank()) {
                        running.setAgencyName(line.trim());
                        System.out.println("TO BE SET: "+line);
                    } else {
                        running.setCity(line.trim());
                        System.out.println("CITY TO BE SET: "+line);
                    }
                }

                running.setAGENCY_DB_NAME();
                System.out.println("Redemarre' : "+running.getCity());
                Services externalAvailService = Services.getInstance("SOAPSERVICEDB", "soap", "password");
//                Services internalService = new Services(running.getAGENCY_DB_NAME().trim().toUpperCase(), "soap", "password");

                JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(
                        running.getAGENCY_DB_NAME().trim().toUpperCase(),
                        "soap", "password", true);
                clientDAO = new ClientDAO(jdbcConnectionFactory.getConnectionDB());
                serviceDAO = new ServiceInfoDAO(jdbcConnectionFactory.getConnectionDB());
                nodeServiceUsage = NodeServiceUsage.getInstance(serviceDAO);
                bookingHistoryDAO = new BookingHistoryDAO(jdbcConnectionFactory.getConnectionDB(), clientDAO);

                System.out.println("Loading All services: "+externalAvailService.loadAvailableServices(serviceDAO));

                List<Service> allServices = running.getAllServices();

                for (Service service: allServices)   {
                    System.out.println("Reload Service hotel name: "+service.getHotelName());
                    System.out.println("Reload Service hotel's city: "+service.getHotelCity());
                    System.out.println("Reload Service hotel's Booking URL: "+service.getHotelBookingServiceURL());
                    System.out.println("Reload Service hotel's image URL: "+service.getHotelImageUrl());
                }

//                running.setUsedServices(allServices);
                running.setAgencyClients(clientDAO.getAll());
                running.setAllBoonking(bookingHistoryDAO.getAll());

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

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
//                case 1:
//                    CLI cli =new CLI();
//                    cli.launch();
//                    break;
                case 1:
//                    if (url == null ) {
                        SplashScreen loading = new SplashScreen();
                        boolean loaded= loading.main();

                        if (loaded) {
                            loading.close();
                            loading.setVisible(false);
                        }
//                    }
//                    else {
//                        SplashScreen loading = new SplashScreen();
//                        //                            Thread.sleep(500);
//                        System.err.println("Deja instancie");
//                        boolean loaded= loading.main();
//
//                        if (loaded) {
//                            loading.close();
//                            loading.setVisible(false);
//                        }
//                    }
                    MainFrame GUI = new MainFrame(running.getAgencyName());
                    GUI.setVisible(true);
                    break;
                default:
                    System.out.println("\n\nVEUILLEZ FAIRE UN CHOIX COHERENT LA PROCHAINE FOIS: ");
                    break;
            }

        } while (choice<=0 || choice>=2);

    }

    public static Agency initializeServiceNode() {
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

