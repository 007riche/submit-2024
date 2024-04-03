package com.todo.company.hai704.restapi.Client;

import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.main.Main;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class BookingClientApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(BookingClientApplication.class);
    private final Main main;
    static private int PORT=-1;

    public BookingClientApplication(Main main) {
        this.main = main;
    }

    public static void main(String[] args) {

        // Disable headless mode
        System.setProperty("java.awt.headless", "false");
        SpringApplication app = new SpringApplication(BookingClientApplication.class);

        app.setDefaultProperties(Collections
                .singletonMap("server.port", String.valueOf(PORT)));

        app.run(args);
    }

    @Override
    @DependsOnDatabaseInitialization
    public void run(String... args) {
        main.run();
    }

    @Bean
    @DependsOnDatabaseInitialization
    public CommandLineRunner initDatabase(PersistenecService service) {
        Agency agency = service.findAllAgencies().get(0);

        PORT = agency.getPort();

        return args -> {
            logger.info("NUMERO DE PORT"+PORT);
        };
    }


}
