package com.todo.company.hai704.restapi.service.bookingpublisherservice.controller;

import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.exception.PublisherNotFoundException;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.exception.ServiceNotFoundException;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.persistence.PersistenecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher/api")
public class PublisherController {

    @Autowired
    private PersistenecService persistenecService;

    private static Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @GetMapping("/services")
    private List<NodeService> getAllAvailblableServices() {
        logger.info("New Get All Available Services Request");
        return  persistenecService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/services")
    private NodeService recordNewService(@RequestBody NodeService service) {
        logger.info("NEW SERVICE RECORDING REQUEST: "+service);
        return persistenecService.save(service);
    }

    @GetMapping("/services/id/{id}")
    private NodeService getAllAvailblableServicesById(@PathVariable Long id) throws PublisherNotFoundException {
        logger.info("NEW GET ALL AVAILABLE SERVICES BY ID REQUEST: "+id);
        NodeService retrieved ;

        try {
            retrieved = persistenecService.findHotelById(id);
        } catch (ServiceNotFoundException e) {
            throw new PublisherNotFoundException("Impossible de trouver un service comportant cet Id");
        }
        return retrieved;
    }

    @GetMapping("/services/country/{country}")
    private List<NodeService> getAllAvailblableServicesByCountry(@PathVariable String country) {
        logger.info("New request of service available by country: "+country);
        return  persistenecService.findByHotelCountry(country);
    }

    @GetMapping("/services/country/{country}/city/{city}")
    private List<NodeService> getAllAvailblableServicesByCityInCountry(@PathVariable String country, @PathVariable String city) {
        logger.info("New request of services by country and by city: "+country+", "+city);
        return  persistenecService.findByCity(country, city);
    }

    @GetMapping("/services/city/{city}")
    private List<NodeService> getAllAvailblableServicesByCity(@PathVariable String city) {
        logger.info("New request of services by city: "+city);
        return  persistenecService.findByCityOfSameSameName(city);
    }

    @GetMapping("/services/stars/{stars}")
    private List<NodeService> getAllAvailblableServicesByStars(@PathVariable Double stars) {
        logger.info("New request of services by country and by stars: "+stars);
        return  persistenecService.findByHotelStars(stars);
    }
}
