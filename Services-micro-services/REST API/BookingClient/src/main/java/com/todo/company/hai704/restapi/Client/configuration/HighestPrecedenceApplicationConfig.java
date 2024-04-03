package com.todo.company.hai704.restapi.Client.configuration;


import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.processors.DoubleProcessor;
import com.todo.company.hai704.restapi.Client.processors.Integerprocessor;
import com.todo.company.hai704.restapi.Client.processors.StringOnlyProcessor;
import com.todo.company.hai704.restapi.Client.utilpack.ObjectToFileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

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
import java.util.Scanner;

import static com.todo.company.hai704.restapi.Client.utilpack.CustomUtility.getAvailablePort;
import static com.todo.company.hai704.restapi.Client.utilpack.CustomUtility.matchMysqlJdbcUrlWithDBName;


@Configuration
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HighestPrecedenceApplicationConfig {

    @Autowired
    private ConfigurableEnvironment environment;

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
    static String propertiesBackUpFile ="/backup.properties"; //    "backup.properties"; // resources/
    static String hotelNodeDataFile =  "/configs/instance.data"; // "instance.data"; //   resources/
    static String resPath = "";

    @Bean(name = "dataSourcesInitialization")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public DataSourcesInitialization dataSourcesInitialization() {

        intProcessor = new Integerprocessor(inputReader);
        doubleProcessor= new DoubleProcessor(inputReader);
        stringOnlyProcessor= new StringOnlyProcessor(inputReader);


        System.err.println("Path: "+ getResourcesPath());

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
            publisherDB = new DatabaseConfigurationProperties();
//            DatabaseConfigurationProperties sourcesProperties = new ArrayList<DatabaseConfigurationProperties>();
            System.out.println(">>>>>>>>>>>>      Initialisation du service  publication des nouveaux lances <<<<<<<<<<<<<<<");
            initializeServiceNode();
//            sourcesProperties.add(publisherDB);
            DataSourcesInitialization dataSourcesInitialization=
                    DataSourcesInitialization.createDataSourcesInitialization(environment, publisherDB);
            return dataSourcesInitialization;
        } else {
            DataSourcesInitialization dataSourcesInitialization=
                    DataSourcesInitialization.createDataSourcesInitialization(environment, null);
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

    public static boolean initializeServiceNode() {

        // Publishing DB
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONFIGURATION DES BASES DE DONNEES DU SERVICE PUBLICATION  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Connection conn=null;
        Scanner sc = new Scanner(System.in);
        String jdbcUrl=null;
        String username=null;
        String password=null;
        do {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Base de donnees de publication des noeuds de service <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
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

        Agency agency = Agency.getInstance();
        agency.setPort(port);

        objectToFileIOService.writeObjectToFile(publisherDB,  resPath+hotelNodeDataFile );

        return true;
    }
}
