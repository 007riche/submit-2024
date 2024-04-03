package com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.repository;


import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.entities.H2NodeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface H2NodePublishServiceRepository extends JpaRepository<H2NodeService, Long> {
    List<NodeService> findByHotelCity(String city);
    List<NodeService> findByHotelCountry(String country);

    List<NodeService> findByHotelStars(Double stars);
}
