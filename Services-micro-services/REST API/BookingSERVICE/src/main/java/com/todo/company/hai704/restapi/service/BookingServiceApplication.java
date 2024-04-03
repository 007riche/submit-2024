package com.todo.company.hai704.restapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.company.hai704.restapi.service.InputProcessor.Integerprocessor;
import com.todo.company.hai704.restapi.service.entity.Booking;
import com.todo.company.hai704.restapi.service.entity.Hotel;
import com.todo.company.hai704.restapi.service.entity.Image;
import com.todo.company.hai704.restapi.service.entity.Room;
import com.todo.company.hai704.restapi.service.models.PublishPostReqNodeService;
import com.todo.company.hai704.restapi.service.services.PersistanceService;
import com.todo.company.hai704.restapi.service.utilpack.RandomBookingGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.todo.company.hai704.restapi.service.utilpack.CustomUtility.*;


@SpringBootApplication
public class BookingServiceApplication {

    @Autowired
    private ResourceLoader resourceLoader;
    private static Logger logger = LoggerFactory.getLogger(BookingServiceApplication.class);
    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    static Integerprocessor intProcessor = new Integerprocessor(inputReader);
    static private int PORT=-1;

    static String baseUrl = "http://localhost:";

//    @Autowired
    private static  RestTemplate publisherProxy = new RestTemplate();
//        = new RestTemplate() ;

    private static String URI_SERVICE_POST=baseUrl+"8085"+"/publisher/api"+"/services";



    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(BookingServiceApplication.class);
        logger.warn("Depuis SpringBoot, Port: "+PORT);

        app.setDefaultProperties(Collections
                .singletonMap("server.port", String.valueOf(PORT)));


        app.run(args);
//        SpringApplication.run(BookingServiceApplication.class, args);
    }

    @Bean
    @DependsOnDatabaseInitialization
    public CommandLineRunner initDatabase(PersistanceService service) {

        List<Room> foundRooms = service.findAllRooms();


        if (foundRooms.size() <= 0) {
            int integerInput=-1;
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

            List<Room> newRooms = new ArrayList<Room>();


            Hotel running = Hotel.getInstance();


            // Getting the port number
            PORT = running.getPortNumber();
            running.setHOTEL_DOMAIN();
            String  url = baseUrl+PORT;
            running.setBOOKING_URL(url+"/api/bookings");
            running.setBROWSING_URL(url+"/api/browsing");
            running.setPARTNERS_URL(url+"/api/partners");
            String imgPath = "img/hotel/"+Integer.toString(generateRandomInt(1,10))+".jpg";

            String hotelImgUrl="";try {
                service.saveImage(running.getHOTEL_DOMAIN(), imgPath);
                String pathTest = "requestedIMG/";
                hotelImgUrl = String.valueOf(service.saveImageToFile(running.getHOTEL_DOMAIN(), pathTest, running.getHOTEL_DOMAIN()+".jpg"));
            } catch (IOException e) {
//                throw new RuntimeException(e);
                System.err.println("Impossible d'enregistre et obtenir lurl de limage de l'hotel");
            }

            running.setHotelImgUrl(hotelImgUrl);
//                    "https://media.gettyimages.com/id/1333257950/fr/photo/image-rendue-num%C3%A9riquement-de-lint%C3%A9rieur-dun-h%C3%B4tel-cinq-%C3%A9toiles.jpg?s=1024x1024&w=gi&k=20&c=G9rfXaVYbGyyI5uCihFmzDM6jth7af9lXGrfPS8FOu4=");

            System.out.println("Hotel: "+running.toString());

            service.saveHotel(running);

            PublishPostReqNodeService castedHotel = new PublishPostReqNodeService();

            castedHotel.setHotelName(running.getName());
            castedHotel.setHotelStars(running.getStars());
            castedHotel.setHotelCountry(running.getCountry());
            castedHotel.setHotelCity(running.getCity());
            castedHotel.setHotelStreetNumber(running.getStreetNumber());
            castedHotel.setHotelStreet(running.getStreet());
            castedHotel.setHotelImageURL(running.getHotelImgUrl());
            castedHotel.setHotelBookingServiceURL(running.getBOOKING_URL());
            castedHotel.setHotelBrowsingServiceURL(running.getBROWSING_URL());
            castedHotel.setHotelPartnersServiceURL(running.getPARTNERS_URL());
            castedHotel.setHotelGPS(running.getGpsPosition());

            PublishPostReqNodeService node = publisherProxy.postForObject(URI_SERVICE_POST, castedHotel, PublishPostReqNodeService.class);
            logger.warn("Publication du service dans le service Publicateur"+node);

            for (int i = 1; i <= integerInput; i++) {
                Room newRoom = generateNewRoom(service, i, running.getStars(), i);
                newRooms.add(newRoom);
            }

            int finalIntegerInput = integerInput;

            return args -> {
                logger.info("Initialisation du noeud encours.... ");
                for (Room r : newRooms) {
                    logger.warn("Nouvelle chambre Aleatoirement generee: "+ service.saveRoom(r));
                }
                logger.info("Chambres enregistrees avec succes ");
                logger.error("Hotel: "+running);
                List<Booking> preloadedBooking =RandomBookingGenerator.generateRandomBookings(service,15, finalIntegerInput);
                for (Booking booking : preloadedBooking) {
                    logger.warn("Nouvelle reservation Aleatoirement generee: "+ service.saveBooking(booking));
                }
                logger.info("Enregistrement pre-chargees avec succes ");
                logger.info("Initialisation du noeud avec succes ");
                logger.info("Attente de requetes......");
            };
        }

        return args -> {
            logger.info("Redemarrage du noeud...");
            Hotel running = Hotel.getInstance();
            Hotel retrieved = service.findHotelById(1L);

            running.setName(retrieved.getName());
            running.setStars(retrieved.getStars());
            running.setCountry(retrieved.getCountry());
            running.setCity(retrieved.getCity());
            running.setStreet(retrieved.getStreet());
            running.setStreetNumber(retrieved.getStreetNumber());
            running.setGpsPosition(retrieved.getGpsPosition());
            running.setBOOKING_URL(retrieved.getBOOKING_URL());
            running.setBROWSING_URL(retrieved.getBROWSING_URL());
            running.setPARTNERS_URL(retrieved.getPARTNERS_URL());
            running.setHOTEL_DOMAIN();

            PORT= extractPort(running.getBOOKING_URL());
            running.setPortNumber(PORT);

            logger.warn("Instance redemarree: "+running);
            logger.warn("Port: "+PORT);
            logger.info("Attente de requetes......");
        };
    }




}
