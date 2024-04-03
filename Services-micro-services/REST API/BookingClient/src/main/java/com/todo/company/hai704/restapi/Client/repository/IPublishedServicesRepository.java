package com.todo.company.hai704.restapi.Client.repository;



import com.todo.company.hai704.restapi.Client.entity.NodeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPublishedServicesRepository extends JpaRepository<NodeService, Long> {

    NodeService findFirstByHotelName(String hotelName);
    NodeService findNodeServiceByHotelNameAndHotelAddress(String hotelName,  String hotelAddress);
    NodeService findNodeServiceByHotelNameAndHotelBookingServiceURL(String hotelName,  String hotelBookingServiceURL);
    NodeService findFirstByHotelNameAndIdAgencyAndHotelBookingServiceURL(String hotelName, String idAgency, String hotelBookingServiceURL);
}
