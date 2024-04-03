package com.todo.company.hai704.restapi.service.configurations;


import com.todo.company.hai704.restapi.service.InputProcessor.DoubleProcessor;
import com.todo.company.hai704.restapi.service.InputProcessor.Integerprocessor;
import com.todo.company.hai704.restapi.service.InputProcessor.StringOnlyProcessor;
import com.todo.company.hai704.restapi.service.entity.Hotel;
import com.todo.company.hai704.restapi.service.entity.Room;
import com.todo.company.hai704.restapi.service.utilpack.ObjectToFileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.todo.company.hai704.restapi.service.utilpack.CustomUtility.*;

@Configuration
//@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//@ComponentScan(basePackages = "com.todo.company.hai704.restapi.service")
public class HighestPrecedenceApplicationConfig {

    @Autowired
    private ConfigurableEnvironment environment;

    static private Hotel runningNodeHotel;


    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    static Integerprocessor intProcessor;
    static DoubleProcessor doubleProcessor;
    static StringOnlyProcessor stringOnlyProcessor;

    private URL backupFileUrl;
    private URL instanceFileUrl;


    static DatabaseConfigurationProperties publisherDB;
    static DatabaseConfigurationProperties nodeDB;

    static ConfigurationBackupService backupService = new ConfigurationBackupService();
    static ObjectToFileIOService objectToFileIOService = new ObjectToFileIOService();
    static String propertiesBackUpFile ="/configs/backup.properties"; //    "backup.properties"; // resources/
    static String hotelNodeDataFile =  "/configs/instance.data"; // "instance.data"; //   resources/
    static String resPath = "";

    @Bean(name = "dataSourcesInitialization")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public DataSourcesInitialization dataSourcesInitialization() {

        intProcessor = new Integerprocessor(inputReader);
        doubleProcessor= new DoubleProcessor(inputReader);
        stringOnlyProcessor= new StringOnlyProcessor(inputReader);
        publisherDB = new DatabaseConfigurationProperties();
        nodeDB = new DatabaseConfigurationProperties();

//        System.err.println("Path: "+ getResourcesPath());

        resPath = getResourcesPath();

        Path path = Paths.get(resPath+propertiesBackUpFile);
        if (!Files.exists(path)) {
            new File(path.toAbsolutePath().toString());
            System.out.println("Chemin1: " + path.toAbsolutePath().toString());
        }

        Path path2 = Paths.get(resPath+hotelNodeDataFile);
        if (!Files.exists(path2)) {
//            File file =
            new File(path2.toAbsolutePath().toString());
            System.out.println("Chemin2: "+ path2.toAbsolutePath().toString());
        }

        this.backupFileUrl = HighestPrecedenceApplicationConfig.class.getResource(propertiesBackUpFile);
        this.instanceFileUrl = HighestPrecedenceApplicationConfig.class.getResource(hotelNodeDataFile);

        if (this.backupFileUrl==null && this.instanceFileUrl == null) {
//            List<DatabaseConfigurationProperties> sourcesProperties = new ArrayList<DatabaseConfigurationProperties>();
            System.out.println(">>>>>>>>>>>>      Initialisation du noeud de service      <<<<<<<<<<<<<<<");
            runningNodeHotel=initializeServiceNode();
//            sourcesProperties.add(publisherDB);
//            sourcesProperties.add(nodeDB);
            DataSourcesInitialization dataSourcesInitialization=DataSourcesInitialization.createDataSourcesInitialization(environment, publisherDB);
            return dataSourcesInitialization;
        } else {
            DataSourcesInitialization dataSourcesInitialization=DataSourcesInitialization.createDataSourcesInitialization(environment, null);
            return dataSourcesInitialization;
        }

    }

    public String getResourcesPath() {
        try {
            Resource resource = new ClassPathResource("");
            return resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            // Handle the exception as needed
            e.printStackTrace();
            return null;
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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Adresse de l'hôtel <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
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
        localHotel.setHOTEL_DOMAIN();
        integerInput = -1;


        // Publishing DB
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONFIGURATION DES BASES DE DONNEES DU NOEUD DE SERVICE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Connection conn=null;
        Scanner sc = new Scanner(System.in);
        String jdbcUrl=null;
        String username=null;
        String password=null;
        do {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Base de donnees du noeud Service <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("Le choix vous est laisse' de creer une base de donnees mysql avec un utilisateur qui a tous les droits sur celle-ci pour les operations requises");
            do {
                System.out.println("URL de la base de donnee (comportant le nom de la base de donnees, donc port inclus):");
                jdbcUrl = sc.next();
                jdbcUrl=jdbcUrl.trim();
            }while (!matchMysqlJdbcUrlWithDBName(jdbcUrl));

            int indexSlachBeforeDBName1 = jdbcUrl.lastIndexOf("/");
            if ( indexSlachBeforeDBName1 !=-1 ) {
                String dbname = jdbcUrl.substring(indexSlachBeforeDBName1+1);
                publisherDB.setHostedProtocolURLEndingWithPortNumber(jdbcUrl);
                publisherDB.setDBName(dbname);
            }

            do {
                System.out.println("Nom de l'utilisateur de la base de donnees (evitez les noms commencants ou terminant par des espaces):");
                username = sc.nextLine();
            } while (username.trim().isBlank());
            publisherDB.setUsername(username.trim());
            do {
                System.out.println("Mot de passe(idealement evitez aussi les espaces au debut et a la fin):");
                password =sc.nextLine();
            } while (password.trim().isBlank());
            publisherDB.setPassword(password.trim());
            try {
                conn = DriverManager.getConnection(publisherDB.getHostedProtocolURLEndingWithPortNumber(), publisherDB.getUsername(), publisherDB.getPassword());
            } catch (SQLException e) {
                System.err.println("Impossible de se connecter a la base de donnee de publication avec les infos fournies");
                System.err.println("URL: "+ publisherDB.getHostedProtocolURLEndingWithPortNumber());
                System.err.println("Utilisateur: "+ publisherDB.getUsername());
                System.err.println("mot de passe: "+publisherDB.getPassword());
            }

        } while (conn == null);
        System.out.println("informations correctes, Connection"+conn);

        // Port assigning
        System.out.println("Obtention d'un port disponible");
        int port = getAvailablePort();
        System.out.println("Nouveau port disponible: "+port);
        localHotel.setPortNumber(port);

        objectToFileIOService.writeObjectToFile( localHotel,  resPath+hotelNodeDataFile );

        return localHotel;
    }
}
