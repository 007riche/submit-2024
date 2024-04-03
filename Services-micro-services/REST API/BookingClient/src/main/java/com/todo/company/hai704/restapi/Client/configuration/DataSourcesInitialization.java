package com.todo.company.hai704.restapi.Client.configuration;


import com.todo.company.hai704.restapi.Client.entity.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourcesInitialization
{
    ConfigurationBackupService backupService = new ConfigurationBackupService();
    static String propertiesBackUpFile = "/backup.properties";
    static String runningApplicationPropertiesFile = "/application.properties";
    static String resPath = "";
    private  ConfigurableEnvironment environment;
    private  DatabaseConfigurationProperties sourcesProperties;
    private URL fileurl;

    @Autowired
    public DataSourcesInitialization(ConfigurableEnvironment environment, DatabaseConfigurationProperties publisherDB) {
        this.environment = environment;
        this.sourcesProperties = publisherDB;
        this.fileurl = DataSourcesInitialization.class.getResource(propertiesBackUpFile);
        resPath = getResourcesPath();
        System.err.println("resPath in datainitialization:"+resPath);
        if (this.fileurl == null && (this.sourcesProperties == null)) {
//            backupService.backupConfigurationToFile(resPath+runningApplicationPropertiesFile,
//                    resPath+runningApplicationPropertiesFile);
        } else {
            updateDataSourceProperties();
        }
    }

    public static DataSourcesInitialization createDataSourcesInitialization(ConfigurableEnvironment environment,
                                                                            DatabaseConfigurationProperties publisherDB) {
        return new DataSourcesInitialization(environment, publisherDB);
    }


    private void updateDataSourceProperties() {
        Properties newAppDataSourcesProperties = new Properties();


        newAppDataSourcesProperties.setProperty("spring.application.name","BookingPublisherService");

        newAppDataSourcesProperties.setProperty("# Mysql datasource configurations","");

        // MySql data source
        newAppDataSourcesProperties.setProperty("spring.datasource.url",
                this.sourcesProperties.getHostedProtocolURLEndingWithPortNumber());
        newAppDataSourcesProperties.setProperty("spring.datasource.username",
                this.sourcesProperties.getUsername());
        newAppDataSourcesProperties.setProperty("spring.datasource.password",
                this.sourcesProperties.getPassword());
//        newAppDataSourcesProperties.setProperty("#spring.datasource.driver-class-name",
//                "com.mysql.cj.jdbc.Driver");
        newAppDataSourcesProperties.setProperty("spring.jpa.hibernate.ddl-auto",
                "update");
        newAppDataSourcesProperties.setProperty("spring.jpa.properties.hibernate.show-sql",
                "true");



        newAppDataSourcesProperties.setProperty("server.port", String.valueOf(Agency.getInstance().getPort()));

        environment.getPropertySources().addFirst(new PropertiesPropertySource("newAppDataSourcesProperties", newAppDataSourcesProperties));

        backupService.backupConfigurationToFile(newAppDataSourcesProperties, resPath+runningApplicationPropertiesFile);

//        System.err.println("Final loaded env: "+newAppDataSourcesProperties.toString());

        for (Map.Entry<Object, Object> entry : newAppDataSourcesProperties.entrySet()) {
            System.err.println("Property Key: " + entry.getKey() + ", Value: " + entry.getValue());
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

}
