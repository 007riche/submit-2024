package com.todo.company.hai704.restapi.service.bookingpublisherservice.repository;


import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INodePublishServiceRepository extends JpaRepository<NodeService, Long> {
    List<NodeService> findByHotelCity(String city);
    List<NodeService> findByHotelCountry(String country);

    List<NodeService> findAllByHotelStars(Double stars);
}
