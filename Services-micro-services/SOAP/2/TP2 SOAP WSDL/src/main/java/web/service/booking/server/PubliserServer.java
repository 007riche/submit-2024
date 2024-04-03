package web.service.booking.server;




import jakarta.xml.ws.Endpoint;
import web.service.booking.InputProcessor.DoubleProcessor;
import web.service.booking.InputProcessor.Integerprocessor;
import web.service.booking.InputProcessor.StringOnlyProcessor;
import web.service.booking.data.JDBCConnectionFactory;
import web.service.booking.data.RandomBookingGenerator;
import web.service.booking.models.*;
import web.service.booking.services.BookingServiceImplementation;
import web.service.booking.services.BrowseAvailabilityServiceImpl;
import web.service.booking.services.PartnerShipManagementServcieImpl;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;


public class PubliserServer {

    static private Hotel runningNodeHotel;
    static String localData;

    static String filename = "Hotel.txt";
    static String hostURL = "http://localhost:";

    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    static Integerprocessor intProcessor;
    static DoubleProcessor doubleProcessor;
    static StringOnlyProcessor stringOnlyProcessor;




    public static void main(String[] args) {
        runningNodeHotel = Hotel.getInstance();
         intProcessor = new Integerprocessor(inputReader);
         doubleProcessor= new DoubleProcessor(inputReader);
         stringOnlyProcessor= new StringOnlyProcessor(inputReader);;

        URL url = PubliserServer.class.getResource(filename);
//        runningNodeHotel = initializeServiceNode();

        if (url == null) {
            try {
                url = PubliserServer.class.getResource("");

                runningNodeHotel = initializeServiceNode();
//                runningNodeHotel.setHotleBooking(RandomBookingGenerator.generateRandomBookings(15, ));
                runningNodeHotel.setHotelPartnership(new ArrayList<Partnership>());
                System.out.println("Initialisation de l'hôtel terminé");
                System.out.println("                Hotle:  "+runningNodeHotel.getName());
                System.out.println("       Domain:  " + runningNodeHotel.getHOTEL_DOMAIN() );
                File file = new File(url.toURI().getPath() + filename);
                FileWriter writer = new FileWriter(file);
                BufferedWriter buffer = new BufferedWriter(writer);

                buffer.write(runningNodeHotel.getName());
                buffer.newLine();

                buffer.write(runningNodeHotel.getCity());
                buffer.newLine();

                int NODE_PORT = getAvailablePort();
                String publishBookingURL = hostURL + NODE_PORT + "/"+runningNodeHotel.getHOTEL_DOMAIN()+"/booking" ;
                Endpoint.publish(publishBookingURL, new BookingServiceImplementation((Hotel) runningNodeHotel, publishBookingURL));
                System.err.println("BookingService Published");
                System.err.println("web service link: "+publishBookingURL);
                buffer.write(publishBookingURL);
                buffer.newLine();


                String publishBrowsingURL = hostURL + NODE_PORT + "/"+runningNodeHotel.getHOTEL_DOMAIN()+"/browse" ;
                Endpoint.publish(publishBrowsingURL, new BrowseAvailabilityServiceImpl((Hotel) runningNodeHotel, publishBrowsingURL));
                System.err.println("BrowsingService Published");
                System.err.println("web service link: "+publishBrowsingURL);
                buffer.write(publishBrowsingURL);
                buffer.newLine();

                String publishPartnersURL = hostURL + NODE_PORT + "/"+runningNodeHotel.getHOTEL_DOMAIN()+"/partners" ;
                Endpoint.publish(publishPartnersURL, new PartnerShipManagementServcieImpl((Hotel) runningNodeHotel, publishPartnersURL));
                System.err.println("PartnersService Published");
                System.err.println("web service link: "+publishPartnersURL);
                buffer.write(publishPartnersURL);
                buffer.newLine();


                System.out.println("Noeud initialisé avec succès");
                buffer.close();
            } catch (IOException | URISyntaxException e) {
                System.out.println("Impossible de créer le fichier local de sauvegarde.");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Redémarrage du service encours ...");
            System.out.println("VAr avant rechargement: "+ runningNodeHotel.getName());
            // read the data from the file and print it to the console
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(url.openStream()));
                String hotelName;
                String hotelCity;
                String line;
                for (int i = 1; i <= 2; i++) {
                    line = buffer.readLine();
                    if (i==1 && !line.isBlank()) {
                        runningNodeHotel.setName(line);
                        System.out.println("TO BE SET: "+line);
                    } else {
                        runningNodeHotel.setCity(line);
                        System.out.println("CITY TO BE SET: "+line);
                    }
                }

//                while ((line = buffer.readLine()) != null) {
//                    System.out.println(line);
//                }

                reloadHotelData();
                System.out.println("VAr apres rechargement: "+ runningNodeHotel.getHOTEL_DOMAIN());
                buffer.close();

                String publishBookingURL = runningNodeHotel.getBOOKING_URL();
                Endpoint.publish(publishBookingURL, new BookingServiceImplementation());
                System.err.println("BookingService Published");
                System.err.println("web service link: "+publishBookingURL);



                String publishBrowsingURL = runningNodeHotel.getBROWSING_URL();
                System.out.println(publishBrowsingURL);
                Endpoint.publish(publishBrowsingURL, new BrowseAvailabilityServiceImpl((Hotel) runningNodeHotel, publishBrowsingURL));
                System.err.println("BrowsingService Published");
                System.err.println("web service link: "+publishBrowsingURL);


                String publishPartnersURL = runningNodeHotel.getPARTNERS_URL(); ;
                Endpoint.publish(publishPartnersURL, new PartnerShipManagementServcieImpl((Hotel) runningNodeHotel, publishPartnersURL));
                System.err.println("PartnersService Published");
                System.err.println("web service link: "+publishPartnersURL);

            } catch (IOException e) {
                System.out.println("Erreur de lecture du fichier de sauvegarde ...");
                e.printStackTrace();
            }
        }


 }


    public static Hotel initializeServiceNode() {
        Hotel localHotel = Hotel.getInstance();
        String stringIput="";
        double doubleInput=-1.0;
        int integerInput=-1;
        String gps="Lat: ";

        List<Room> newRooms = new ArrayList<Room>();

        System.out.println("\t\t\t\t INITIALISATION D'UN NOUVEAU NOEUD SERVICE");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Hotel name
        do {
            stringOnlyProcessor.setMesseage("Quel nom voulez-vous donner à l'Hôtel?: ");
            stringOnlyProcessor.setExceptionMesseage("Vous n'avez rien saisi");
            try {
                stringIput = stringOnlyProcessor.process();
            } catch (IOException e) {
                stringOnlyProcessor.setMesseage("");
                e.printStackTrace();
            }
        } while (stringIput.trim().isBlank());
        localHotel.setName(stringIput);
        stringIput = "";

        // Hotel stars
       do {
           doubleProcessor.setMesseage("Quel nombre d'étoiles voulez-vous attribuer à cet hôtel?: ");
           doubleProcessor.setExceptionMesseage("Veuillez saisir un nombre entre 1 et 5");
           try {
               doubleInput = doubleProcessor.process();
           } catch (IOException e) {
               doubleProcessor.setMesseage("");
               e.printStackTrace();
           }
       } while (doubleInput<1.0 || doubleInput > 5.0);
        localHotel.setStars(doubleInput);
        doubleInput = -1.0;

        // Hotel Address
        System.out.println("***************************** Adresse de l'hôtel *****************************");
        // Hotel country
        do {
            stringOnlyProcessor.setMesseage("Dans quel pays l'hôtel est implanté?: ");
            stringOnlyProcessor.setExceptionMesseage("Vous n'avez rien saisi");
            try {
                stringIput = stringOnlyProcessor.process();
            } catch (IOException e) {
                stringOnlyProcessor.setMesseage("");
                e.printStackTrace();
            }
        } while (stringIput.trim().isBlank());
        localHotel.setCountry(stringIput);
        stringIput = "";

        // Hotel city
        do {
            stringOnlyProcessor.setMesseage("Dans quelle ville est implanté l'hôtel?: ");
            stringOnlyProcessor.setExceptionMesseage("Vous n'avez rien saisi");
            try {
                stringIput = stringOnlyProcessor.process();
            } catch (IOException e) {
                stringOnlyProcessor.setMesseage("");
                e.printStackTrace();
            }
        } while (stringIput.trim().isBlank());
        localHotel.setCity(stringIput);
        stringIput = "";

        // Hotel street address
        do {
            stringOnlyProcessor.setMesseage("Nom de la rue locale de l'hôtel: ");
            stringOnlyProcessor.setExceptionMesseage("Vous n'avez rien saisi");
            try {
                stringIput = stringOnlyProcessor.process();
            } catch (IOException e) {
                stringOnlyProcessor.setMesseage("");
                e.printStackTrace();
            }
        } while (stringIput.trim().isBlank());
        localHotel.setStreet(stringIput);
        stringIput = "";


        // Hotel street number
        intProcessor.setMesseage("Numéro de rue: ");
        try {
            integerInput = intProcessor.process();
        } catch (IOException e) {
            intProcessor.setMesseage("");
            e.printStackTrace();
        }
        localHotel.setStreetNumber(integerInput);
        integerInput = -1;

        // Hotel GPS Latitude
        intProcessor.setMesseage("Position GPS Latitude: ");
        try {
            integerInput = intProcessor.process();
        } catch (IOException e) {
            intProcessor.setMesseage("");
            e.printStackTrace();
        }
        gps += integerInput + "Long: ";
        integerInput = -1;

        // Hotel GPS Longitude
        intProcessor.setMesseage("Position GPS Longitude: ");
        try {
            integerInput = intProcessor.process();
        } catch (IOException e) {
            intProcessor.setMesseage("");
            e.printStackTrace();
        }
        gps += integerInput;
        localHotel.setGpsPosition(gps);
        integerInput = -1;

        // Hotel number of rooms
        do {
            intProcessor.setMesseage("Combien de chambre voulez-vous générer?: ");
            intProcessor.setExceptionMesseage("Un hotel doit avoir au minimum 3 chambres (restreindre à pas plus de 150 chambres)");
            try {
                integerInput = intProcessor.process();
            } catch (IOException e) {
                intProcessor.setMesseage("");
                e.printStackTrace();
            }

        } while(integerInput <3 || integerInput > 150);

        // additional config
        localHotel.setHOTEL_DOMAIN();
        localHotel.setHotelPartnership(new ArrayList<Partnership>());
        localHotel.setHotleBooking(RandomBookingGenerator.generateRandomBookings(15, integerInput));
        localHotel.setTemporaryRequestOffer(new ArrayList<HashMap<String, Offer>>());


        System.out.println("Initialisation encours ....");
        for (int i = 1; i <= integerInput; i++) {
            Room newRoom = generateNewRoom(i, localHotel.getStars(), i);
            int roomBedNumber = newRoom.getNumberBed();

            newRooms.add(newRoom);
        }
        localHotel.setHotelRooms(newRooms);
        return localHotel;
    }

    private static void reloadHotelData() {
//        Hotel run
        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory("SOAPSERVICEDB","soap","password");
        jdbcConnectionFactory.reloadHotel();
        BookingServiceImplementation bookImpl = null;
//        try {
            bookImpl = new BookingServiceImplementation();
            bookImpl.reloadPartners();
            bookImpl.reloadRooms();
            bookImpl.reloadBookings();
//        } catch (SQLException e) {
//            System.err.println("RELOAD FAILED");
//        }

    }

    // destroy entire DB
    public void destroyServiceDB(Hotel hotel) {
        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory("","soap","password");
        jdbcConnectionFactory.destroyDB(hotel.getHOTEL_DOMAIN(), "soap","password");
    }


    public static int getAvailablePort() {
        int startPort = 8080;
        int endPort = 9000;

        int currentPort = startPort;

        while (currentPort<=endPort) {
            if(isPortAvailable(currentPort)) {
                break;
            }
            currentPort++;
        }

        return currentPort;
    }

    public static boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            // Port available
            return true;
        } catch (IOException e) {
            // Port not available
            return false;
        }
    }

    public static double generateRandomDouble(double min, double max) {
        // Generate a random number between 0 and 1
        double random = Math.random();

        // Scale the random number to fit within the specified range
        double range = max - min;
        double scaledRandom = random * range;

        // Shift the scaled random number to the correct starting point
        double shiftedRandom = scaledRandom + min;

        // Return the final random number
        return shiftedRandom;
    }

    public static int generateRandomInt(int min, int max) {
        // Generate a random number between min (inclusive) and max (inclusive)
        int random = (int) (Math.random() * (max - min + 1)) + min;

        // Return the final random integer
        return random;
    }

    public static Room generateNewRoom(int roomNumber, double stars, int roomIndex) {
        Room genRoom = new Room();
        genRoom.setRoomNumber(roomNumber);
        String roomImBase = "roomNumber";
        genRoom.setImgName(roomImBase+roomIndex);
        if(stars<=3.0d)
        {
            genRoom.setNumberBed(generateRandomInt(1, 3));
            if (genRoom.getNumberBed() == 1 ) {
                genRoom.setBasePrice(generateRandomDouble(10.0, 20.0));

            } else {
                genRoom.setBasePrice(generateRandomDouble(11.0, 27.0));
            }
        } else {
            genRoom.setNumberBed(generateRandomInt(1, 2));
            if (stars == 4) {
                if (genRoom.getNumberBed() == 1 ) {
                    genRoom.setBasePrice(generateRandomDouble(25.0, 35.0));
                } else {
                    genRoom.setBasePrice(generateRandomDouble(28.99, 39.99));
                }
            } else {
                if (genRoom.getNumberBed() == 1 ) {
                    genRoom.setBasePrice(generateRandomDouble(44.99, 75.99));
                } else {
                    genRoom.setBasePrice(generateRandomDouble(55.99, 99.99));
                }
            }
        }

        return genRoom;
    }

}


