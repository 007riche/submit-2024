package com.todo.company.hai704.restapi.service.bookingpublisherservice;

import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.entities.H2NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.repository.H2NodePublishServiceRepository;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.persistence.PersistenecService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.util.NodeServiceConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class BookingPublisherServiceApplication {
	private Logger logger = LoggerFactory.getLogger(BookingPublisherServiceApplication.class);
	@Autowired
	private H2NodePublishServiceRepository h2NodePublishServiceRepository;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BookingPublisherServiceApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8085"));
		app.run(args);
//		SpringApplication.run(BookingPublisherServiceApplication.class, args);
	}

//	@Bean
//	@DependsOnDatabaseInitialization
//	public CommandLineRunner initDatabase(PersistenecService service) {
//		System.err.println("To create the bean");
//
//		List<NodeService> services = service.findAll();
//		if (services.size()<=0 ) {
//			NodeService user1 = NodeService.createNodeService();
//			user1.setHotelName("Hotel 55");
//			user1.setHotelStars(4.5);
//			user1.setHotelCountry("togo");
//			user1.setHotelCity("lome");
//			user1.setHotelStreetNumber(25);
//			user1.setHotelStreet("montdage avenue");
//			user1.setHotelGPS("25~ 90*");
//			user1.setHotelImageURL("/api/img/1");
//			user1.setHotelBookingServiceURL("/api/booking");
//			user1.setHotelBrowsingServiceURL("/api/browsing");
//			user1.setHotelPartnersServiceURL("/api/partnering");
//
//			NodeService user2 = NodeService.createNodeService();
//			user2.setHotelName("Hotel 55");
//			user2.setHotelStars(4.5);
//			user2.setHotelCountry("france");
//			user2.setHotelCity("mtp");
//			user2.setHotelStreetNumber(25);
//			user2.setHotelStreet("montdage avenue");
//			user2.setHotelGPS("25~ 90*");
//			user2.setHotelImageURL("/api/img/1");
//			user2.setHotelBookingServiceURL("/api/booking");
//			user2.setHotelBrowsingServiceURL("/api/browsing");
//			user2.setHotelPartnersServiceURL("/api/partnering");
//
//			NodeService user3 = NodeService.createNodeService();
//			user3.setHotelName("Hotel 55");
//			user3.setHotelStars(4.5);
//			user3.setHotelCountry("france");
//			user3.setHotelCity("paris");
//			user3.setHotelStreetNumber(25);
//			user3.setHotelStreet("montdage avenue");
//			user3.setHotelGPS("25~ 90*");
//			user3.setHotelImageURL("/api/img/1");
//			user3.setHotelBookingServiceURL("/api/booking");
//			user3.setHotelBrowsingServiceURL("/api/browsing");
//			user3.setHotelPartnersServiceURL("/api/partnering");
//
//			H2NodeService h2user1 = NodeServiceConverter.convertToH2NodeService(user1);
//			H2NodeService h2user2 = NodeServiceConverter.convertToH2NodeService(user1);
//			H2NodeService h2user3 = NodeServiceConverter.convertToH2NodeService(user1);
//			return args -> {
//				logger.info("Preloading Mysql database with "+ service.save(user1));
//				logger.info("Preloading H2 database with "+ h2user1);
//				logger.info("Preloading Mysql database with "+ service.save(user2));
//				logger.info("Preloading H2 database with "+ h2user2);
//				logger.info("Preloading Mysql database with "+ service.save(user3));
//				logger.info("Preloading H2 database with "+ h2user3);
//			};
//		}
//
//		return args -> {
//			services.forEach(service1 -> {
//				H2NodeService h2user = NodeServiceConverter.convertToH2NodeService(service1);
//				logger.info("Reloading H2 database with "+ h2NodePublishServiceRepository.save(h2user));
//			} );
//		};
//	}

}
